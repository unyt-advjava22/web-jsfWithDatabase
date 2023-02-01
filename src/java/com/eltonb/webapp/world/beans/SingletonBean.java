/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eltonb.webapp.world.beans;

import java.util.ArrayList;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.List;

/**
 *
 * @author elton.ballhysa
 */
@ManagedBean(name="singletonBean")
@ApplicationScoped
public class SingletonBean {
    
    private List<String> continents;

    public SingletonBean() {
        continents = new ArrayList<>();
        continents.add("Africa");
        continents.add("Antarctica");
        continents.add("Asia");
        continents.add("Europe");
        continents.add("North America");
        continents.add("Oceania");
        continents.add("South America");
    }

    public List<String> getContinents() {
        return continents;
    }
    
    
    
}
