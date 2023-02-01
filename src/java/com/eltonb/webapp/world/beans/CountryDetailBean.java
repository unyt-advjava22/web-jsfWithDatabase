/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.world.beans;
import com.eltonb.webapp.world.model.Country;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
/**
 *
 * @author elton.ballhysa
 */

@ManagedBean(name = "countryDetailBean")
@RequestScoped
public class CountryDetailBean {
    
    private String countryCode;
    private Country country;
    
    public CountryDetailBean() {
    }
        
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        EntityManager em = emf.createEntityManager();
        country = em.find(Country.class, countryCode);        
    }
    
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    
    
    
}
