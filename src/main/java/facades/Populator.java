/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import dtos.RenameMeDTO;
import entities.Employee;
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
        EntityManager em = emf.createEntityManager();
        EmployeeFacade ef = EmployeeFacade.getEmployeeFacade();


        Employee e1 = new Employee("Irene","Bagsværd",123000);
        Employee e2 = new Employee("Søren","Bagsværd",10000000);
        Employee e3 = new Employee("August","Bagsværd",19);

        em.getTransaction().begin();
        em.persist(e1);
        em.persist(e2);
        em.persist(e3);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }
    
    public static void main(String[] args) {
        populate();
    }
}
