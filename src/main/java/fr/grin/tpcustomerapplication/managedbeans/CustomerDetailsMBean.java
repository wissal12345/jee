/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.grin.tpcustomerapplication.managedbeans;

import fr.grin.tpcustomerapplication.Customer;
import fr.grin.tpcustomerapplication.DiscountCode;
import fr.grin.tpcustomerapplication.session.CustomerManager;
import fr.grin.tpcustomerapplication.session.DiscountCodeManager;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author p
 */
@Named
@ViewScoped
public class CustomerDetailsMBean implements Serializable {
  private int idCustomer;
  private Customer customer;

  @EJB
  private CustomerManager customerManager;
  @EJB
  private DiscountCodeManager discountCodeManager;
  public int getIdCustomer() {
    return idCustomer;
  }

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

  /**
   * Retourne les détails du client courant (celui dans l'attribut customer de
   * cette classe), qu'on appelle une propriété (property)
   */
    public Customer getDetails() {
      return customer;
    }

  /**
   * Action handler - met à jour dans la base de données les données du client
   * contenu dans la variable d'instance customer.
   * @return la prochaine page à afficher, celle qui affiche la liste des clients.
   */
  public String update() {
    // Modifie la base de données.
    // Il faut affecter à customer.
    customer = customerManager.update(customer);
    return "CustomerList";
  }

  public void loadCustomer() {
    this.customer = customerManager.getCustomer(idCustomer);
  }
    /**
   * Retourne la liste de tous les DiscountCode.
     * @return 
   */  
  public List<DiscountCode> getDiscountCodes() {
    return discountCodeManager.getAllDiscountCodes();
  }
public Converter<DiscountCode> getDiscountCodeConverter() {
    return new Converter<DiscountCode>() {
      /**
       * Convertit une String en DiscountCode.
       *
       * @param value valeur à convertir
       */
      public DiscountCode getAsObject(FacesContext context, UIComponent component, String value) {
        return discountCodeManager.findById(value);
      }

      /**
       * Convertit un DiscountCode en String.
       *
       * @param value valeur à convertir
       */
      public String getAsString(FacesContext context, UIComponent component, DiscountCode value) {
        return value.getDiscountCode();
      }
    };
  }
}
