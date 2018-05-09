/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.SetorDAO;
import br.edu.ifsul.modelo.Setor;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;//Esse é o do @ViewScoped.
import javax.inject.Named;

/**
 *
 * @author Joel
 */
@Named(value = "controleSetor")
@ViewScoped
public class ControleSetor implements Serializable {

    @EJB
    private SetorDAO<Setor> dao;
    private Setor objeto;
    private Boolean editando;

    public ControleSetor() {
        editando = false;
    }

    public String listar() {
        editando = false;
        return "/privado/setor/listar?faces-redirect=true";
    }

    public void novo() {
        editando = true;
        objeto = new Setor();
    }

    public void alterar(Object id) {
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao recuperar objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void excluir(Object id) {
        try {
            objeto = dao.getObjectById(id);
            dao.remover(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public void salvar() {
        try {
            if (objeto.getId() == null) {
                dao.persist(objeto);
            } else {
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao presistir objeto: "
                    + Util.getMensagemErro(e));
        }
    }

    public SetorDAO<Setor> getDao() {
        return dao;
    }

    public void setDao(SetorDAO<Setor> dao) {
        this.dao = dao;
    }

    public Setor getObjeto() {
        return objeto;
    }

    public void setObjeto(Setor objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

}
