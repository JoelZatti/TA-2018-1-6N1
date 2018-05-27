package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ColaboradorDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;//Esse é o do @ViewScoped.
import javax.inject.Named;

/**
 *
 * @author Joel
 */
@Named(value = "controleColaborador")
@ViewScoped
public class ControleColaborador implements Serializable {

    @EJB
    private ColaboradorDAO<Colaborador> dao;
    private Colaborador objeto;
    private Boolean editando;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario;
    

    public ControleColaborador(){
        editando = false;
    }

    public String listar(){
        editando = false;
        return "/privado/colaborador/listar?faces-redirect=true";
    }

    public void novo(){
        editando = true;
        objeto = new Colaborador();
    }

    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public void salvar(){
        try {
            if (objeto.getId() == null){
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto: " + 
                    Util.getMensagemErro(e));
        }
    }

    public ColaboradorDAO<Colaborador> getDao() {
        return dao;
    }

    public void setDao(ColaboradorDAO<Colaborador> dao) {
        this.dao = dao;
    }

    public Colaborador getObjeto() {
        return objeto;
    }

    public void setObjeto(Colaborador objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
    }

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

}
