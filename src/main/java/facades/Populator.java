/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.RenameMe;
import javax.persistence.EntityManagerFactory;

import errorhandling.MissingInputException;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade fe = PersonFacade.getPersonFacade(emf);
        try
        {
            fe.addPerson("Gordon", "Freeman", "555-BLACK-MESA");
            fe.addPerson("Alyx", "Vance", "555-1234-1245");
            fe.addPerson("Moe", "Szyslak", "7648-4377");
        }catch (MissingInputException e){
            e.getMessage();
        }

    }
    
    public static void main(String[] args) {
        populate();
    }
}
