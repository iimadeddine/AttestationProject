/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classe;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author imadeddine
 */
@Entity
public class Etudiant {
    @Id 
    @GeneratedValue
    private int id;
    private String codeInscription;
    private String nom;
    private Date dateNaissance;
    private String lieuNaissance;
    private String niveau;
    private String codeNationale;
    private Date dateOut;
    private String decision;
    private String numDossier;
    
    @ManyToOne
    private Etablissement  etablissement;

    public Etudiant() {
    }

    
    
    public Etudiant(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Etudiant(int id, String codeInscription, String nom, Date dateNaissance, String lieuNaissance, String niveau, String codeNationale, Date dateOut, String decision, String numDossier, Etablissement etablissement) {
        this.id = id;
        this.codeInscription = codeInscription;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.niveau = niveau;
        this.codeNationale = codeNationale;
        this.dateOut = dateOut;
        this.decision = decision;
        this.numDossier = numDossier;
        this.etablissement = etablissement;
    }

    public Etudiant(String codeInscription, String nom, Date dateNaissance, String lieuNaissance, String niveau, String codeNationale, Date dateOut, String decision, String numDossier, Etablissement etablissement) {
        this.codeInscription = codeInscription;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.niveau = niveau;
        this.codeNationale = codeNationale;
        this.dateOut = dateOut;
        this.decision = decision;
        this.numDossier = numDossier;
        this.etablissement = etablissement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodeInscription() {
        return codeInscription;
    }

    public void setCodeInscription(String codeInscription) {
        this.codeInscription = codeInscription;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getCodeNationale() {
        return codeNationale;
    }

    public void setCodeNationale(String codeNationale) {
        this.codeNationale = codeNationale;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(String numDossier) {
        this.numDossier = numDossier;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }
    
    

    
    

    
    
}
