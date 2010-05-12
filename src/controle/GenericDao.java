/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Carlos Peixoto Mangueira Júnior
 */
public class GenericDao<T> {

    protected EntityManagerFactory emf;
    protected EntityManager em;
    protected EntityTransaction et;

    public GenericDao() {
        emf = Persistence.createEntityManagerFactory("Dept_MusicaPU");
    }

    public void persist(T objeto) {
        start();
        et.begin();
        em.persist(objeto);
        et.commit();
        em.close();
    }

    public void merge(T objeto) {
        start();
        et.begin();
        em.merge(objeto);
        et.commit();
        em.close();
    }

    public void remove(T objeto) {
        start();
        et.begin();
        em.remove(objeto);
        et.commit();
        em.close();
    }

    public void find(Class<T> classe, Long id) {
        start();
        et.begin();
        em.find(classe, id);
        et.commit();
        em.close();
    }

    private void start() {
        em = emf.createEntityManager();
        et = em.getTransaction();
    }
}
