package br.edu.ifsul.controle;

import br.edu.ifsul.dao.ColaboradorDAO;
import br.edu.ifsul.dao.ProjetoDAO;
import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Projeto;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.modelo.Usuario;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Joel
 */
@Named(value = "controleProjeto")
@ViewScoped
public class ControleProjeto implements Serializable{

    @EJB
    private ProjetoDAO<Projeto> dao;
    private Projeto objeto;
    private Boolean editando;
    @EJB
    private SetorDAO<Setor> daoSetor;
    @EJB
    private UsuarioDAO<Usuario> daoUsuario; 
    private Boolean editandoColaborador; 
    private Colaborador colaborador;  
    private boolean novoColaborador;

    public ControleProjeto() {
        editando = false;
        editandoColaborador = false;
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
        try{
            objeto = dao.getObjectById(id);
            editando = true;
            //editandoPermissao = false;
        }catch (Exception e){
            Util.mensagemErro("Erro ao recuperar objeto: " + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id){
        try{
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        }catch (Exception e){
            Util.mensagemErro("Erro ao remover objeto: " + Util.getMensagemErro(e));
        }
    }

    public void salvar(){
        try{
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        }catch(Exception e){
            Util.mensagemErro("Erro ao persistir objeto " + Util.getMensagemErro(e));
        }
    }
    
    public void novoColaborador(){
          colaborador = new Colaborador();
          editandoColaborador = true;
          novoColaborador = true;
    }
    
//    public void salvarColaborador(){
//        if(objeto.getListaColaboradores().contains(colaborador)){
//            Util.mensagemErro("Este projeto já possui este colaborador!");
//        }else{
//            objeto.getListaColaboradores().add(colaborador);
//            Util.mensagemInformacao("Colaborador adicionado com sucesso!");
//        }
//        editandoColaborador = false;
//    }
    
    public void salvarColaborador(){
        if (colaborador.getId() == null) {
            if (novoColaborador) {
                objeto.adicionarColaborador(colaborador);
            }
        }
        editandoColaborador = false;
        Util.mensagemInformacao("Colaborador persistido com sucesso!");
    }
    
    public void alterarColaborador(int index){
        colaborador = objeto.getListaColaboradores().get(index);
        editandoColaborador = true;
        novoColaborador = false;
    }
    
    public void excluirColaborador(int index){
        objeto.removerColaborador(index);
        Util.mensagemInformacao("Colaborador removido com sucesso!");
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
    
    public Colaborador getColaborador() {
        return colaborador;
}

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Boolean getEditandoColaborador() {
        return editandoColaborador;
    }

    public void setEditandoColaborador(Boolean editandoColaborador) {
        this.editandoColaborador = editandoColaborador;
    }
  
    public UsuarioDAO<Usuario> getDaoUsuario() {
        return daoUsuario;
}

    public void setDaoUsuario(UsuarioDAO<Usuario> daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public boolean isNovoColaborador() {
        return novoColaborador;
    }

    public void setNovoColaborador(boolean novoColaborador) {
        this.novoColaborador = novoColaborador;
    }

  
}
