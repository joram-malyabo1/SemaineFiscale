
package models;

public class AgentObject {
    private String nom;
    private String telephone;
    private String adresse;
    private String service;

    public AgentObject() {
    }

    public AgentObject(String nom, String telephone, String adresse, String service) {
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
        this.service = service;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
    
    
}
