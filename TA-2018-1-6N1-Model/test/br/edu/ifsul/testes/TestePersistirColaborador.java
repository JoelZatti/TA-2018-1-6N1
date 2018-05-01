/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.testes;

import br.edu.ifsul.modelo.Colaborador;
import br.edu.ifsul.modelo.Projeto;
import br.edu.ifsul.modelo.Setor;
import br.edu.ifsul.modelo.Usuario;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Joel
 */
public class TestePersistirColaborador {

    EntityManagerFactory emf;
    EntityManager em;

    public TestePersistirColaborador() {
    }

    @Before
    public void setUp() {
        emf = Persistence.createEntityManagerFactory("TA-2018-1-6N1-ModelPU");
        em = emf.createEntityManager();
    }

    @After
    public void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    public void teste() {
        boolean exception = false;
        try {
            Projeto p = em.find(Projeto.class, 1);
            Colaborador c = new Colaborador();
            c.setGestor(true);
            c.setCargaHoraria(10);
            //c.setProjeto(em.find(Projeto.class, 1));
            c.setUsuario(em.find(Usuario.class, 1));
            p.adicionarColaboradores(c);
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            exception = true;
            e.printStackTrace();
        }
        Assert.assertEquals(false, exception);
    }

}
