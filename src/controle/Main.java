/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import dados.Aluno;
import dados.Instrumento;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Administrador
 */
public class Main {

    public static void main(String[] args) {

        Aluno a = new Aluno();
        a.setNome("Eu 2");
        a.setCelular("88332849");
        a.setCpf("70707239452");
        a.setEmail("email@email.com");
        a.setEndereco("Rua Ali");
        a.setMatricula("10521239");
        a.setTelefone("num tem");
        a.setNascimento(Date.valueOf("1987-09-12"));

        Instrumento i = new Instrumento();
        i.setNome("Guitarra");

//        GenericDao<Aluno> alunoDao = new GenericDao<Aluno>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Dept_MusicaPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();

        //inicia a transacao
        et.begin();

        try{
            em.persist(a);

            Set<Instrumento> set = new HashSet<Instrumento>();
            set.add(i);
            a.setInstrumentos(set);

            em.persist(i);

            em.merge(a);
            et.commit();
        }
        catch(Exception e) {
            System.out.println("***************************************");
            System.out.println(e.getMessage());
            System.out.println("***************************************");
            et.rollback();
        }

        finally{
            em.close();
        }

//        alunoDao.persist(a);

//        GenericDao<Instrumento> instrumentoDao = new GenericDao<Instrumento>();
        //instrumentoDao.persist(i);

//        alunoDao.merge(a);
    }
}
