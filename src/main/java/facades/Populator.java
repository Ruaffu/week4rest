/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Address;
import entities.Person;
import entities.RenameMe;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getPersonFacade(emf);
        EntityManager em = emf.createEntityManager();
        Address add1 = new Address("TestStreet","4040","test");
        Address add2 = new Address("New Street","3040","Bo");
        Address add3 = new Address("Bob Street","3050","Selecta");
        Person p1 = new Person("Gordon", "Freeman", "555-BLACK-MESA");
        p1.setAddress(add1);
        Person p2 = new Person("Alyx", "Vance", "555-1234-1245");
        p2.setAddress(add2);
        Person p3 = new Person("Moe", "Szyslak", "7648-4377");
        p3.setAddress(add3);
        try
        {
            em.getTransaction().begin();
            em.persist(p1);
            em.persist(add1);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(p2);
            em.persist(add2);
            em.getTransaction().commit();
            em.getTransaction().begin();
            em.persist(p3);
            em.persist(add3);
            em.getTransaction().commit();

        }finally
        {
            em.close();
        }
//        fe.addPerson("Gordon", "Freeman", "555-BLACK-MESA");
//        fe.addPerson("Alyx", "Vance", "555-1234-1245");
//        fe.addPerson("Moe", "Szyslak", "7648-4377");

    }
    
    public static void main(String[] args) {
        populate();
    }
}
