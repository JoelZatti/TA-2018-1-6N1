package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ColaboradorDAO;
import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.dao.ProjetoDAO;
import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.modelo.Projeto;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;//Esse é o do @ViewScoped.
import javax.inject.Named;

/**
 *
 * @author Joel
 */
@Named(value = "controleProjeto")
@ViewScoped
public class ControleProjeto implements Serializable {

    @EJB
    private ProjetoDAO<Projeto> dao;
    private Projeto objeto;
    private Boolean editando;
    @EJB
    private SetorDAO<Setor> daoSetor;
    @EJB
    private ColaboradorDAO<Colaborador> daoColaborador;

    public ControleProjeto(){
        editando = false;
    }

    public String listar(){
        editando = false;
        return "/privado/projeto/listar?faces-redirect=true";
    }

    public void novo(){
        editando = true;
        objeto = new Projeto();
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
    
    public ProjetoDAO<Projeto> getDao() {
        return dao;
    }

    public void setDao(ProjetoDAO<Projeto> dao) {
        this.dao = dao;
    }

    public Projeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Projeto objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public SetorDAO<Setor> getDaoSetor() {
        return daoSetor;
    }

    public void setDaoSetor(SetorDAO<Setor> daoSetor) {
        this.daoSetor = daoSetor;
    }
    
    public ColaboradorDAO<Colaborador> getDaoColaborador() {
        return daoColaborador;
    }

    public void setDaoColaborador(ColaboradorDAO<Colaborador> daoColaborador) {
        this.daoColaborador = daoColaborador;
    }

}
