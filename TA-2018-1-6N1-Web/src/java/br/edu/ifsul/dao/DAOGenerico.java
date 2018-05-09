/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Joel
 */
public class DAOGenerico<TIPO> implements Serializable{
    private List<TIPO> listaOjetos;
    private List<TIPO> listaTodos;
    @PersistenceContext(unitName = "TA-2018-1-6N1-WebPU")//Referencia o nome da unidade de persistência
    private EntityManager em;
    private Class classePersistente;
    
    public DAOGenerico(){
        
    }

    /**
     * @return the listaOjetos
     */
    public List<TIPO> getListaOjetos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
     /**
     * @return the listaTodos
     */
    public List<TIPO> getListaTodos() {
        String jpql = "from " + classePersistente.getSimpleName();
        return em.createQuery(jpql).getResultList();
    }
    
    public void persist(TIPO obj) throws Exception{
        em.persist(obj);
    }
    
    public void merge(TIPO obj) throws Exception{
        em.merge(obj);
    }
    
    public TIPO getObjectById(Object id) throws Exception{
        return (TIPO) em.find(classePersistente, id);
    }
    
    public void remover(TIPO obj) throws Exception{
        obj = em.merge(obj);//vincula com o banco para remover.
        em.remove(obj);
    }
    /**
     * @param listaOjetos the listaOjetos to set
     */
    public void setListaOjetos(List<TIPO> listaOjetos) {
        this.listaOjetos = listaOjetos;
    }

    /**
     * @param listaTodos the listaTodos to set
     */
    public void setListaTodos(List<TIPO> listaTodos) {
        this.listaTodos = listaTodos;
    }    
    

    /**
     * @return the em
     */
    public EntityManager getEm() {
        return em;
    }

    /**
     * @param em the em to set
     */
    public void setEm(EntityManager em) {
        this.em = em;
    }

    /**
     * @return the classePersistente
     */
    public Class getClassePersistente() {
        return classePersistente;
    }

    /**
     * @param classePersistente the classePersistente to set
     */
    public void setClassePersistente(Class classePersistente) {
        this.classePersistente = classePersistente;
    }
}
