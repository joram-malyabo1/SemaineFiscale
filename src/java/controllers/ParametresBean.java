package controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//importation de la classe DriverManager
import java.sql.DriverManager;
//importation de la classe Connection
import java.sql.Connection;
//importation de la classe SQLException
import java.sql.SQLException;
//importation de la classe Statement
import java.sql.Statement;
import service.Connexion;


@ManagedBean
@RequestScoped
public class ParametresBean {
            
    public ParametresBean() {
    }
    
    public void enregistrer(){
        
        String message="";
        try{
            Class.forName("org.postgresql.Driver");
            Connection connexion = DriverManager.getConnection(Connexion.url,Connexion.user,Connexion.password);
            Statement statement = connexion.createStatement();
            int nbreEnregistrement = statement.executeUpdate(
                "BEGIN; " +
                "INSERT INTO entite(id,designation,sigle,id_centre) " +
                "VALUES(1,'Direction Générale','DG',1), " +
                "(2,'Centre Opérationnel des Recettes de Goma','CORG',2), " +
                "(3,'Antenne du Port','AntP',2); " +
                        
                "INSERT INTO acte(id,libelle) values " +
                "(1,'IF'), " +
                "(2,'IRL'), " +
                "(3,'IV'), " +
                "(4,'ATPC'), " +
                "(5,'Autre taxe'); " +
                        
                "INSERT INTO service(id,designation,sigle) values " +
                "(1,'Direction Générale des Recettes du Nord-Kivu','DGR-NK'), " +
                "(2,'Direction Générale des Impôts','DGI'); " +
                "COMMIT;" 
            );
            message = "Enregistement des paramètres effectué avec succès";
        }
        catch(SQLException | ClassNotFoundException ex){
            message = ex.getMessage();
        }    

        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(FacesMessage.SEVERITY_INFO, "Sticky Message", message));
    }
       
}
