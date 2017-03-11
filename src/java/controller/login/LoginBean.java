/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.login;

import entities.Usuario;
import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

/**
 *
 * @author pablo
 */

@Named
@RequestScoped
public class LoginBean implements Serializable{
  
    private final static String USER_KEY = "auth_user";
    private static final long serialVersionUID = -2152389656664659476L;
    private String nombre;
    private String clave;
    private Integer tipo;
    private boolean logeado = false;

    public LoginBean() {
        this.nombre = "";
        this.clave = "";
        this.tipo = -1;
    }
    
    
    public static String getUSER_KEY() {
        return USER_KEY;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public boolean estaLogeado() {
        return logeado;
    }

    public void setLogeado(boolean logeado) {
        this.logeado = logeado;
    }
    
    
    public void doLogin(ActionEvent e) throws IOException {

        FacesMessage msg = null;

        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        String url = "";

        
           
            Usuario usuarioData = Usuario.getUsuarioByName(nombre);
            tipo = usuarioData.getTipo();

            if (isSuperadministrador(usuarioData)) {
                url = extContext.encodeActionURL(
                        context.getApplication().getViewHandler().getActionURL(context, "/superAdministrador/indexSuperAdministrador.xhtml"));
                extContext.getSessionMap().put(USER_KEY, usuarioData);

                extContext.redirect(url);
                return;
            }
            
            if (isFertilizacion(usuarioData)) {
                url = extContext.encodeActionURL(
                        context.getApplication().getViewHandler().getActionURL(context, "/fertilizacion/indexFertilizacion.xhtml"));
                extContext.getSessionMap().put(USER_KEY, usuarioData);

                extContext.redirect(url);
                return;
            }

            
        
        

        msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Login Error",
                "Credenciales no válidas ");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isSuperadministrador(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 0;
    }
    
    /**
     *
     * @param user
     * @param password
     * @return
     */
    private boolean isFertilizacion(Usuario user) {

        return nombre != null && nombre.equals(user.getNombre()) && clave != null
                && clave.equals(user.getPassword()) && user.getTipo() == 1;
    }
    
    
    


    
    /**
     *
     * @return
     */
    //Usuario POA
    public String getLoggedUserName() {
        return ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY)).toString();
    }

    public String getLoggedUsuarioNombre() {
        Usuario var = ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(USER_KEY));
        return var.getNombre();
    }
    
    
    /**
     *
     * @throws IOException
     */
    public void doLogout() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext extContext = context.getExternalContext();
        extContext.getSessionMap().remove(USER_KEY);
        String url = extContext.encodeActionURL(
                context.getApplication().getViewHandler().getActionURL(context, "/login.xhtml"));
        extContext.redirect(url);
    }
    
}
