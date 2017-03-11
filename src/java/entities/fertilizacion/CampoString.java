/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class CampoString implements Serializable{
    String campo;

    public CampoString() {
        this.campo="";
    }

    
    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }
    
    
}
