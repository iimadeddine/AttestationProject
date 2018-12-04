/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author imadeddine
 */
@Entity
public class Etablissement {
 
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String nom;
    private String type;
    private String region;

    public Etablissement() {
    }

    public Etablissement(String nom,String region,String type) {
        this.nom = nom;
        this.region = region;
        this.type = type;
    }
    
    

    public Etablissement(int id, String nom, String type, String region) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    
    
    
    
    
}
