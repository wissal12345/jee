/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package fr.grin.tpcustomerapplication.session;

import fr.grin.tpcustomerapplication.Customer;
import javax.ejb.Stateless;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
/**
 *
 * @author p
 */
@Stateless
public class CustomerManager {
 @PersistenceContext(unitName = "customerPU")
    private EntityManager em;
 
    public List<Customer> getAllCustomers() {
       Query query = em.createNamedQuery("Customer.findAll");
       return query.getResultList();
    }

    public Customer update(Customer customer) {
       return em.merge(customer);
    }

    public void persist(Customer customer) {
       em.persist(customer);
    }
}
