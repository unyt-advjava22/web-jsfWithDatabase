/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.world.beans;

import com.eltonb.webapp.world.model.Country;
import java.security.CodeSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author elton.ballhysa
 */
@ManagedBean(name="listCountriesBean")
@SessionScoped
public class ListCountriesBean {
    
    private String filterName;
    private String filterContinent;
            
    private List<Country> countries;
    
    public ListCountriesBean() {
        fillCountries();
    }

    public void fillCountries() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        EntityManager em = emf.createEntityManager();
        countries = em.createNamedQuery("Country.findAll").getResultList();        
    }

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getFilterName() {
        return filterName;
    }

    public void setFilterName(String filterName) {
        this.filterName = filterName;
    }

    public String getFilterContinent() {
        return filterContinent;
    }

    public void setFilterContinent(String filterContinent) {
        this.filterContinent = filterContinent;
    }

    private boolean isParameterPresent(String paramValue) {
        return paramValue != null && ! paramValue.trim().isEmpty();
    }
    
    private Query constructFilterQuery() {
        String ql = "SELECT c FROM Country c WHERE c.code = c.code ";
        if (isParameterPresent(filterName))
            ql += "AND LOWER(c.name) LIKE LOWER(:name)";
        if (isParameterPresent(filterContinent))
            ql += "AND c.continent = :continent";
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery(ql);

        if (isParameterPresent(filterName))
            query.setParameter("name", filterName + "%");
        if (isParameterPresent(filterContinent))
            query.setParameter("continent", filterContinent);

        return query;
    }
    
    public String filter() {
        Query query = constructFilterQuery();
        countries = query.getResultList();                
        return "";
    }
    
    public String removeCountry(String code) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        EntityManager em = emf.createEntityManager();
        Country country = em.find(Country.class, code);
        em.getTransaction().begin();
        em.remove(country);
        em.getTransaction().commit();
        
        countries = em.createNamedQuery("Country.findAll").getResultList();
        return "";
    }

}
