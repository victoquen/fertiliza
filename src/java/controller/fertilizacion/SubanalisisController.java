/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import controller.login.LoginBean;
import entities.Usuario;
import entities.fertilizacion.Laboratorio;
import entities.fertilizacion.Matriz;
import entities.fertilizacion.Subanalisis;
import entities.fertilizacion.UnidadMedida;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.SubanalisisModel;

/**
 *
 * @author VICTOR OQUENDO
 */
@Named
@ViewScoped
public class SubanalisisController implements Serializable {

    Subanalisis actual;
    Subanalisis selected;
    List<Subanalisis> listado;
    List<Subanalisis> filter;
    SubanalisisModel model;

    List<Matriz> listadoMatriz;
    List<UnidadMedida> listadoUnidadMedida;
    List<Laboratorio> listadoLaboratorio;
    
    Usuario usuario;
    Date fechaIn;
    String formatFechaIn;

    public SubanalisisController() {
        actual = new Subanalisis();
        listado = Subanalisis.getAllActivos();
        model = new SubanalisisModel(listado);

        actual.setCodigo(numeroSecuencialCodigo());
        
        
        fechaIn = new Date();
        SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        formatFechaIn = formateadorRec.format(fechaIn);
       

        listadoMatriz = Matriz.getAll();
        listadoUnidadMedida = UnidadMedida.getAll();
        listadoLaboratorio = Laboratorio.getAllLaboratorio();

        usuario = ((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(LoginBean.getUSER_KEY()));
       
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public String getFormatFechaIn() {
        return formatFechaIn;
    }

    public void setFormatFechaIn(String formatFechaIn) {
        this.formatFechaIn = formatFechaIn;
    }
    
    public List<Matriz> getListadoMatriz() {
        return listadoMatriz;
    }

    public void setListadoMatriz(List<Matriz> listadoMatriz) {
        this.listadoMatriz = listadoMatriz;
    }

    public List<UnidadMedida> getListadoUnidadMedida() {
        return listadoUnidadMedida;
    }

    public void setListadoUnidadMedida(List<UnidadMedida> listadoUnidadMedida) {
        this.listadoUnidadMedida = listadoUnidadMedida;
    }

    public List<Laboratorio> getListadoLaboratorio() {
        return listadoLaboratorio;
    }

    public void setListadoLaboratorio(List<Laboratorio> listadoLaboratorio) {
        this.listadoLaboratorio = listadoLaboratorio;
    }

    public Subanalisis getActual() {
        return actual;
    }

    public void setActual(Subanalisis actual) {
        this.actual = actual;
    }

    public Subanalisis getSelected() {
        return selected;
    }

    public void setSelected(Subanalisis selected) {
        this.selected = selected;
    }

    public List<Subanalisis> getListado() {
        return listado;
    }

    public void setListado(List<Subanalisis> listado) {
        this.listado = listado;
    }

    public List<Subanalisis> getFilter() {
        return filter;
    }

    public void setFilter(List<Subanalisis> filter) {
        this.filter = filter;
    }

    public SubanalisisModel getModel() {
        return model;
    }

    public void setModel(SubanalisisModel model) {
        this.model = model;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void save() {

        if (controlDatos(actual)) {

            actual.setUsuario(usuario.getIdUsuario());
            actual.setCodigo(numeroSecuencialCodigo());

            actual.save();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Ingresada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }

    }

    public void update() {
        if (controlDatos(selected)) {
           
            selected.setUsuario(usuario.getIdUsuario());
            selected.update();
            load();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Información Modificada"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }
    }

    public void load() {
        actual = new Subanalisis();
        listado = Subanalisis.getAllActivos();
        model = new SubanalisisModel(listado);

        listadoMatriz = Matriz.getAll();
        listadoUnidadMedida = UnidadMedida.getAll();
        listadoLaboratorio = Laboratorio.getAllLaboratorio();

        actual.setCodigo(numeroSecuencialCodigo());
    }

    Boolean controlDatos(Subanalisis obj) {
        Boolean res = true;

        if (obj.getNombre().equals("") || obj.getTat()==0 || obj.getMatriz() == null || obj.getMatriz().equals("") || obj.getUnidadMedida() == null || obj.getUnidadMedida().equals("") || obj.getLaboratorio() == null || obj.getLaboratorio().equals("")) {
            res = false;
        }
        return res;
    }

    int numeroSecuencialCodigo() {

        int res = Subanalisis.getMaxNumeroSecuencialCodigo() + 1;
        return res;
    }
}
