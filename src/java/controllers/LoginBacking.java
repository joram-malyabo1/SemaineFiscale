
package controllers;

import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class LoginBacking {

    @NotEmpty
    @Size(min = 8, message = "Le mot de passe doit avoir au mois 8 charactères")
    private String password;

    @NotEmpty
    @Email(message = "S'il vous plaît! Mettez un e-mail valide.")
    private String email;

    private FacesContext facesContext;
        
    public String submit() throws IOException {
        return "dashboard";
    }
   
    public String logout(){
        return "index";
    }
    
    public String dashboard(){
        return "dashboard";
    }

   // getters & setters

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }
    
}
