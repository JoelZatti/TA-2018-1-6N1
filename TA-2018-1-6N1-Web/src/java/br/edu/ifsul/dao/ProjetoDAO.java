/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Projeto;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author Joel
 */
@Stateful
public class ProjetoDAO<TIPO> extends DAOGenerico<Projeto> implements Serializable{

    public ProjetoDAO(){
        super();
        classePersistente = Projeto.class;
        ordem = "nome";
        maximoObjetos = 3;
    }

    public Projeto getObjectById(Object id) throws Exception {
        Projeto obj = em.find(Projeto.class, id);
        /*
        A linha obj.getListaColaboradores().size(); é necessaria para inicializar a coleção para quando ela for exibida na tela não gerar 
        um erro de lazyInicializationException
         */
        obj.getListaColaboradores().size();
        return obj;
    }
}
