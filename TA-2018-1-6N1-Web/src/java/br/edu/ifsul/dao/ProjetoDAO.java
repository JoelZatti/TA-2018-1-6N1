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
public class ProjetoDAO<TIPO> extends DAOGenerico<TIPO> implements Serializable{
    public ProjetoDAO(){
        super();
        super.setClassePersistente(Projeto.class);
    }
}
