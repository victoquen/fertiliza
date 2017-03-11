/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.superAdministrador;

import java.io.Serializable;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author pablo
 */
@ViewScoped
@Named
public class MenuSuperAdminController implements Serializable{
    
    String opIndex;
    String opUsuario;

    public MenuSuperAdminController() {
        this.opIndex = "indexSuperAdministrador";
        this.opUsuario = "crearUsuario";
    }
    
    
    private void reloadSession(){
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();
        
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.superAdministrador.UsuarioController-null-false");
    }
    
    
    public String goToOpIndex(){
        reloadSession();
        return this.opIndex;
    }
    public String goToOpUsuario(){
        reloadSession();
        return this.opUsuario;
    }
    
}
