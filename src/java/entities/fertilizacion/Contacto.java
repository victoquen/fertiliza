/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import java.io.Serializable;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author pablo
 */
public class Contacto implements Serializable {

    String tipo;
    String nombre;
    String cargo;
    List<String> telefono;
    List<String> email;

    public Contacto() {
        this.tipo = "";
        this.nombre = "";
        this.cargo = "";
        this.telefono = new ArrayList<>();
        this.email = new ArrayList<>();
    }

    public Contacto(String tipo) {
        this.tipo = tipo;
        this.nombre = "";
        this.cargo = "";
        this.telefono = new ArrayList<>();
        this.email = new ArrayList<>();
    }

    public Contacto(String tipo, String nombre, String cargo, List<String> telefono, List<String> email) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.cargo = cargo;
        this.telefono = telefono;
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public List<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(List<String> telefono) {
        this.telefono = telefono;
    }    

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    

}
