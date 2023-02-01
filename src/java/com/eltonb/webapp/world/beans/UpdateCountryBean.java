/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.world.beans;

import com.eltonb.webapp.world.model.Country;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author elton.ballhysa
 */

@ManagedBean(name="updateCountryBean")
@ViewScoped
public class UpdateCountryBean {
    
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
    
    public UpdateCountryBean() {
        
    }
    
    private EntityManager entityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WorldPU");
        return emf.createEntityManager();
    }
    
    @PostConstruct
    public void init() {
        EntityManager em = entityManager();
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.code = params.get("countryCode");
        Country country = em.find(Country.class, this.code);
        populateBeanFromModel(country);
    }
    
    private void populateBeanFromModel(Country country) {
        code = country.getCode();
        code2 = country.getCode2();
        name = country.getName();
        continent = country.getContinent();
        region = country.getRegion();
        surfaceArea = country.getSurfaceArea();
        if (country.getIndepYear() != null)
            independenceYear = country.getIndepYear();
        population = country.getPopulation();
        lifeExp = country.getLifeExpectancy();
        gnp = country.getGnp();
        localName = country.getLocalName();
        governForm = country.getGovernmentForm();
    }

    private void populateModelFromBean(Country country) {
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
    }
    
    public void update() {
        EntityManager em = entityManager();
        Country country = em.find(Country.class, this.code);
        populateModelFromBean(country);

        em.getTransaction().begin();
        em.merge(country);
        em.getTransaction().commit();
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("confirmationMessage", "Country saved!");
        listCountriesBean.fillCountries();
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
