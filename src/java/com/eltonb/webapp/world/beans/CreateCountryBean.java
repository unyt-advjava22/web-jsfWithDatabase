/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.world.beans;

import com.eltonb.webapp.world.model.Country;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author elton.ballhysa
 */

@ManagedBean(name="createCountryBean")
@RequestScoped
public class CreateCountryBean {
    
    @ManagedProperty(value="#{listCountriesBean}")
    private ListCountriesBean listCountriesBean;
    
    private String code;
    private String code2;
    private String name;
    private String continent;
    private String region;
    private float surfaceArea;
    private short independenceYear;
    private int population;
    private float lifeExp;
    private float gnp;
    private String localName;
    private String governForm;
    
    public CreateCountryBean() {
        
    }
    
    private Country initCountry() {
        Country country = new Country();
        country.setCode(this.code);
        country.setCode2(this.code2);
        country.setName(this.name);
        country.setContinent(this.continent);
        country.setRegion(this.region);
        country.setSurfaceArea(this.surfaceArea);
        country.setIndepYear(this.independenceYear);
        country.setPopulation(this.population);
        country.setLifeExpectancy(this.lifeExp);
        country.setGnp(this.gnp);
        country.setLocalName(this.localName);
        country.setGovernmentForm(this.governForm);        
        return country;
    }
    
    public String create() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        EntityManager em = emf.createEntityManager();
        Country country = initCountry();
        em.getTransaction().begin();
        em.persist(country);
        em.getTransaction().commit();
        
        listCountriesBean.fillCountries();
        return "index";
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public float getSurfaceArea() {
        return surfaceArea;
    }

    public void setSurfaceArea(float surfaceArea) {
        this.surfaceArea = surfaceArea;
    }

    public short getIndependenceYear() {
        return independenceYear;
    }

    public void setIndependenceYear(short independenceYear) {
        this.independenceYear = independenceYear;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public float getLifeExp() {
        return lifeExp;
    }

    public void setLifeExp(float lifeExp) {
        this.lifeExp = lifeExp;
    }

    public float getGnp() {
        return gnp;
    }

    public void setGnp(float gnp) {
        this.gnp = gnp;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    public String getGovernForm() {
        return governForm;
    }

    public void setGovernForm(String governForm) {
        this.governForm = governForm;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public void setListCountriesBean(ListCountriesBean listCountriesBean) {
        this.listCountriesBean = listCountriesBean;
    }

    
    
    
}
