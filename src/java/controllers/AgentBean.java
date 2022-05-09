package controllers;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import models.AgentObject;
//importation de la classe DriverManager
import java.sql.DriverManager;
//importation de la classe Connection
import java.sql.Connection;
//importation de la classe ResultSet
import java.sql.ResultSet;
//importation de la classe SQLException
import java.sql.SQLException;
//importation de la classe Statement
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import service.Connexion;



@ManagedBean
@RequestScoped
public class AgentBean {
    public String nom;
    public String telephone;
    public String adresse;
    public String service;
    public String entite;

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

    public String getEntite() {
        return entite;
    }

    public void setEntite(String entite) {
        this.entite = entite;
    }
    
    public AgentBean() {
    }
    
    public List<AgentObject> agents(){
        List<AgentObject> list = new ArrayList();
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();

            ResultSet res = statement.executeQuery(
                "select nom, " +
                "telephone, " +
                "adresse, " +
                "service.sigle as service " +
                "from assujetti " +
                "inner join agent on assujetti.id=agent.id " +
                "inner join service on service.id = agent.id_service"
            );
            while(res.next()){
                list.add(new AgentObject(res.getString("nom"),
                        res.getString("telephone"),
                        res.getString("adresse"),
                        res.getString("service"))
                );                
            }
	     res.close();
            connexion.close();
        }
        catch(SQLException | ClassNotFoundException ex){
//            System.out.println(ex.getMessage());
            list = new ArrayList();
        } 

        return list;
    }
    
    public List<String> listService(){
        List<String> list = new ArrayList();
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();

            ResultSet res = statement.executeQuery(
                "select designation from service"
            );
            while(res.next()){
                list.add(
                    res.getString("designation")
                );                
            }
	     res.close();
            connexion.close();
        }
        catch(SQLException | ClassNotFoundException ex){
//            System.out.println(ex.getMessage());
            list = new ArrayList();
        } 

        return list;
    }
    
    public List<String> listEntite(){
        List<String> list = new ArrayList();
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();

            ResultSet res = statement.executeQuery(
                "select designation from entite"
            );
            while(res.next()){
                list.add(
                    res.getString("designation")
                );                
            }
	     res.close();
            connexion.close();
        }
        catch(SQLException | ClassNotFoundException ex){
//            System.out.println(ex.getMessage());
            list = new ArrayList();
        } 

        return list;
    }
    
    public void enregistrer(){
        /*
        AGENT DE LA DGRNK
        =================
        "INSERT INTO agentdgrnk(id,id_entite) SELECT (SELECT id from assujetti where nom='PALUKU MAFUNGULA Ezéchiel'),1;\n" +
        
        */
        String message="";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();
            int nbreEnregistrement = statement.executeUpdate(
                "BEGIN;" +
                "INSERT INTO assujetti(nom,sigle,telephone,adresse) " +
                "values (" +
                    "'" + this.nom + "'," +
                    "'-'," +
                    "'" + this.telephone + "'," +
                    "'" + this.adresse + "'" +
                 "); " +
                "INSERT INTO agent(id,id_service) " +
                "SELECT " +
                "(SELECT id from assujetti where nom='" + this.nom + "')," +
                "(select id from service where designation='" + this.service + "'); " +
                
                "COMMIT;" 
            );
            message = "Enregistement de l'agent effectué avec succès";
        }
        catch(SQLException | ClassNotFoundException ex){
            message = ex.getMessage();
        }    

        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", message));
        this.initialize();
    }
    
    public void enregistrerAgentDgrnk(){
        /*
        AGENT DE LA DGRNK
        =================
        "INSERT INTO agentdgrnk(id,id_entite) SELECT (SELECT id from assujetti where nom='PALUKU MAFUNGULA Ezéchiel'),1;\n" +
        
        */
        String message="";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();
            int nbreEnregistrement = statement.executeUpdate(
                "BEGIN;" +
                "INSERT INTO assujetti(nom,sigle,telephone,adresse) " +
                "values (" +
                    "'" + this.nom + "'," +
                    "'-'," +
                    "'" + this.telephone + "'," +
                    "'" + this.adresse + "'" +
                 "); " +
                "INSERT INTO agent(id,id_service) " +
                "SELECT " +
                "(SELECT id from assujetti where nom='" + this.nom + "'),1; " +
                "INSERT INTO agentdgrnk(id,id_entite) " +
                "SELECT " +
                "(SELECT id from assujetti where nom='" + this.nom + "')," +
                "(select id from entite where designation='" + this.entite + "');" +
                "COMMIT;" 
            );
            message = "Enregistement de l'agent effectué avec succès";
        }
        catch(SQLException | ClassNotFoundException ex){
            message = ex.getMessage();
        }    

        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", message));
        this.initialize();
    }
    
    public void initialize(){
        this.nom="";
        this.telephone="";
        this.adresse="";
        this.service="";
        this.entite="";
    }
}
