/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import entities.fertilizacion.CompuestoIngredienteAux;
import entities.fertilizacion.CompuestoQuimico;
import entities.fertilizacion.Fuente;
import entities.fertilizacion.FuenteCompuestoAux;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.TableColumn.CellEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.CompuestoQuimicoModel;
import models.FuenteCompuestoAuxModel;
import models.FuenteModel;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
@Named
@ViewScoped
public class CombinacionFuenCompController implements Serializable {

    CompuestoQuimico selectedC;
    CompuestoQuimico actualC;
    List<CompuestoQuimico> listadoC;
    List<CompuestoQuimico> filterC;
    CompuestoQuimicoModel modelC;

    Fuente selectedF;
    Fuente actualF;
    List<Fuente> listadoF;
    List<Fuente> filterF;
    FuenteModel modelF;

    Boolean activeCompuesto;

    BigDecimal totalF;
    BigDecimal humedadF;
    BigDecimal presentacionKgF;
    BigDecimal precioDoleSacoF;

    BigDecimal porcentajeIngredienteActivo;
    BigDecimal porcentajePesoTotalPC;//calculado
    BigDecimal precioKgIngredienteActivo;//calculado
    BigDecimal kgIngredienteActivoConcentracionPC;//calculado
    BigDecimal kgIA;//calculado

    //campos crear fuentes componentes************************
    ObjectId cultivo;
    ObjectId hacienda;
    List<FuenteCompuestoAux> fuentesCompuestos;
    FuenteCompuestoAux actualFC;
    List<CompuestoIngredienteAux> compuestosIngrediente;
    CompuestoIngredienteAux actualCI;
    CompuestoIngredienteAux selectedCI;
    //*******************************************************
    
    FuenteCompuestoAux selectedFC;
    List<FuenteCompuestoAux> listadoFC;
    List<FuenteCompuestoAux> filterFC;
    FuenteCompuestoAuxModel modelFC;
    

    public CombinacionFuenCompController() {
        actualC = new CompuestoQuimico();
        listadoC = CompuestoQuimico.getAllCompuestoQuimicos();
        modelC = new CompuestoQuimicoModel(listadoC);

        actualF = new Fuente();
        listadoF = Fuente.getAllFuentes();
        modelF = new FuenteModel(listadoF);

        totalF = new BigDecimal(0.00);
        humedadF = new BigDecimal(0.00);
        presentacionKgF = new BigDecimal(0.00);
        precioDoleSacoF = new BigDecimal(0.00);

        activeCompuesto = false;

        fuentesCompuestos = new ArrayList<>();
        actualFC = new FuenteCompuestoAux();
        compuestosIngrediente = new ArrayList<>();
        actualCI = new CompuestoIngredienteAux();
        
        listadoFC = FuenteCompuestoAux.getAllFuentesCompuestos();
        modelFC = new FuenteCompuestoAuxModel(listadoFC);
    }

    public List<FuenteCompuestoAux> getListadoFC() {
        return listadoFC;
    }

    public void setListadoFC(List<FuenteCompuestoAux> listadoFC) {
        this.listadoFC = listadoFC;
    }

    public List<FuenteCompuestoAux> getFilterFC() {
        return filterFC;
    }

    public void setFilterFC(List<FuenteCompuestoAux> filterFC) {
        this.filterFC = filterFC;
    }

    public FuenteCompuestoAuxModel getModelFC() {
        return modelFC;
    }

    public void setModelFC(FuenteCompuestoAuxModel modelFC) {
        this.modelFC = modelFC;
    }

    
    public FuenteCompuestoAux getSelectedFC() {
        return selectedFC;
    }

    public void setSelectedFC(FuenteCompuestoAux selectedFC) {
        this.selectedFC = selectedFC;
    }

    public Boolean getActiveCompuesto() {
        return activeCompuesto;
    }

    public void setActiveCompuesto(Boolean activeCompuesto) {
        this.activeCompuesto = activeCompuesto;
    }

    public CompuestoQuimico getActualC() {
        return actualC;
    }

    public void setActualC(CompuestoQuimico actualC) {
        this.actualC = actualC;
    }

    public List<CompuestoQuimico> getListadoC() {
        return listadoC;
    }

    public void setListadoC(List<CompuestoQuimico> listadoC) {
        this.listadoC = listadoC;
    }

    public List<CompuestoQuimico> getFilterC() {
        return filterC;
    }

    public void setFilterC(List<CompuestoQuimico> filterC) {
        this.filterC = filterC;
    }

    public CompuestoQuimicoModel getModelC() {
        return modelC;
    }

    public void setModelC(CompuestoQuimicoModel modelC) {
        this.modelC = modelC;
    }

    public Fuente getActualF() {
        return actualF;
    }

    public void setActualF(Fuente actualF) {
        this.actualF = actualF;
    }

    public List<Fuente> getListadoF() {
        return listadoF;
    }

    public void setListadoF(List<Fuente> listadoF) {
        this.listadoF = listadoF;
    }

    public List<Fuente> getFilterF() {
        return filterF;
    }

    public void setFilterF(List<Fuente> filterF) {
        this.filterF = filterF;
    }

    public FuenteModel getModelF() {
        return modelF;
    }

    public void setModelF(FuenteModel modelF) {
        this.modelF = modelF;
    }

    public CompuestoQuimico getSelectedC() {
        return selectedC;
    }

    public void setSelectedC(CompuestoQuimico selectedC) {
        this.selectedC = selectedC;
    }

    public Fuente getSelectedF() {
        return selectedF;
    }

    public void setSelectedF(Fuente selectedF) {
        this.selectedF = selectedF;
    }

    public BigDecimal getTotalF() {
        return totalF;
    }

    public void setTotalF(BigDecimal totalF) {
        this.totalF = totalF;
    }

    public BigDecimal getHumedadF() {
        return humedadF;
    }

    public void setHumedadF(BigDecimal humedadF) {
        this.humedadF = humedadF;
    }

    public BigDecimal getPresentacionKgF() {
        return presentacionKgF;
    }

    public void setPresentacionKgF(BigDecimal presentacionKgF) {
        this.presentacionKgF = presentacionKgF;
    }

    public BigDecimal getPrecioDoleSacoF() {
        return precioDoleSacoF;
    }

    public void setPrecioDoleSacoF(BigDecimal precioDoleSacoF) {
        this.precioDoleSacoF = precioDoleSacoF;
    }

    public BigDecimal getPorcentajeIngredienteActivo() {
        return porcentajeIngredienteActivo;
    }

    public void setPorcentajeIngredienteActivo(BigDecimal porcentajeIngredienteActivo) {
        this.porcentajeIngredienteActivo = porcentajeIngredienteActivo;
    }

    public BigDecimal getPorcentajePesoTotalPC() {
        return porcentajePesoTotalPC;
    }

    public void setPorcentajePesoTotalPC(BigDecimal porcentajePesoTotalPC) {
        this.porcentajePesoTotalPC = porcentajePesoTotalPC;
    }

    public BigDecimal getPrecioKgIngredienteActivo() {
        return precioKgIngredienteActivo;
    }

    public void setPrecioKgIngredienteActivo(BigDecimal precioKgIngredienteActivo) {
        this.precioKgIngredienteActivo = precioKgIngredienteActivo;
    }

    public BigDecimal getKgIngredienteActivoConcentracionPC() {
        return kgIngredienteActivoConcentracionPC;
    }

    public void setKgIngredienteActivoConcentracionPC(BigDecimal kgIngredienteActivoConcentracionPC) {
        this.kgIngredienteActivoConcentracionPC = kgIngredienteActivoConcentracionPC;
    }

    public BigDecimal getKgIA() {
        return kgIA;
    }

    public void setKgIA(BigDecimal kgIA) {
        this.kgIA = kgIA;
    }

    public ObjectId getCultivo() {
        return cultivo;
    }

    public void setCultivo(ObjectId cultivo) {
        this.cultivo = cultivo;
    }

    public ObjectId getHacienda() {
        return hacienda;
    }

    public void setHacienda(ObjectId hacienda) {
        this.hacienda = hacienda;
    }

    public List<FuenteCompuestoAux> getFuentesCompuestos() {
        return fuentesCompuestos;
    }

    public void setFuentesCompuestos(List<FuenteCompuestoAux> fuentesCompuestos) {
        this.fuentesCompuestos = fuentesCompuestos;
    }

    public FuenteCompuestoAux getActualFC() {
        return actualFC;
    }

    public void setActualFC(FuenteCompuestoAux actualFC) {
        this.actualFC = actualFC;
    }

    public List<CompuestoIngredienteAux> getCompuestosIngrediente() {
        return compuestosIngrediente;
    }

    public void setCompuestosIngrediente(List<CompuestoIngredienteAux> compuestosIngrediente) {
        this.compuestosIngrediente = compuestosIngrediente;
    }

    public CompuestoIngredienteAux getActualCI() {
        return actualCI;
    }

    public void setActualCI(CompuestoIngredienteAux actualCI) {
        this.actualCI = actualCI;
    }

    public CompuestoIngredienteAux getSelectedCI() {
        return selectedCI;
    }

    public void setSelectedCI(CompuestoIngredienteAux selectedCI) {
        this.selectedCI = selectedCI;
    }

    public void activateCompuesto() {
        this.activeCompuesto = false;
    }

    public void deactivateCompuesto() {
        selectedC = null;

    }

    public void saveCompuesto() {
        this.selectedC = this.actualC;
        this.actualC.save();

        loadCompuesto();
    }

    public void loadCompuesto() {
        actualC = new CompuestoQuimico();
        listadoC = CompuestoQuimico.getAllCompuestoQuimicos();
        modelC = new CompuestoQuimicoModel(listadoC);
    }

    public void saveFuente() {
        this.selectedF = this.actualF;
        this.actualF.save();

        loadFuente();
    }

    public void loadFuente() {
        actualF = new Fuente();
        listadoF = Fuente.getAllFuentes();
        modelF = new FuenteModel(listadoF);
    }

//controles de los compuestos seleccionados con su ingrediente activo*****************************
    BigDecimal sumaTotalCompuestoCalculoCampos() {
        BigDecimal res = new BigDecimal(0);
        for (int i = 0; i < compuestosIngrediente.size(); i++) {

            res = res.add(compuestosIngrediente.get(i).getIngredienteActivo());
        }

        for (int j = 0; j < compuestosIngrediente.size(); j++) {

            compuestosIngrediente.get(j).calculoCamposComplementarios(res, precioDoleSacoF, presentacionKgF);
        }

        return res;
    }

    public void addCompuestos() {
        if (controlEmptyCompuesto(selectedC, porcentajeIngredienteActivo)) {

            if (controlExistenciaCompuesto()) {
                actualCI = new CompuestoIngredienteAux(selectedC.getId(), selectedC.getSimbolo(), porcentajeIngredienteActivo);

                compuestosIngrediente.add(actualCI);
                loadCompuestoIngrediente();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Compuesto ya seleccionado"));
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos compuesto"));
        }

    }

    void loadCompuestoIngrediente() {
        actualCI = new CompuestoIngredienteAux();
        selectedC = null;
        this.porcentajeIngredienteActivo = null;
    }

    Boolean controlEmptyCompuesto(CompuestoQuimico ob, BigDecimal n) {
        Boolean res = true; //continua
        if ((ob == null) || (n == null)) {
            res = false;//no continua
        }

        return res;
    }

    public Boolean controlExistenciaCompuesto() {

        Boolean res = true;//continua
        int cont = 0;
        while (cont < compuestosIngrediente.size()) {
            if (compuestosIngrediente.get(cont).getIdCompuesto().equals(selectedC.getId())) {
                res = false;
                cont = compuestosIngrediente.size();
            }
            cont++;
        }

        return res;
    }

    public void cambiarCompuesto() {
        int cont = 0;
        while (cont < compuestosIngrediente.size()) {
            if (compuestosIngrediente.get(cont).getIdCompuesto().equals(selectedCI.getIdCompuesto())) {

                compuestosIngrediente.get(cont).setIngredienteActivo(selectedCI.getIngredienteActivo());

                cont = compuestosIngrediente.size();
            }
            cont++;
        }
    }

    public void eliminarCompuesto() {
        int cont = 0;
        while (cont < compuestosIngrediente.size()) {
            if (compuestosIngrediente.get(cont).getIdCompuesto().equals(selectedCI.getIdCompuesto())) {

                compuestosIngrediente.remove(cont);

                cont = compuestosIngrediente.size();
            }
            cont++;
        }
    }
//***********************************************************************************************************************

    public void guardarFuentesCompuestosCombinados() {

        actualFC.setTotalF(sumaTotalCompuestoCalculoCampos());
        actualFC.setFuente(selectedF.getId());
        actualFC.setFuenteSimbolo(selectedF.getSimbolo());
        actualFC.setHumedadF(humedadF);
        actualFC.setPresentacionKgF(presentacionKgF);
        actualFC.setPrecioDoleSacoF(precioDoleSacoF);

        actualFC.setCompuestos(compuestosIngrediente);

        fuentesCompuestos.add(actualFC);

        loadFuentesCompuestosCombinados();

    }

    public void onCellEdit(CellEditEvent event) {
        //Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void editDatosFuente(int op) {

        BigDecimal auxop;
        if (op == 1) {

            for (int i = 0; i < fuentesCompuestos.size(); i++) {

                auxop = new BigDecimal(0);
                for (int j = 0; j < fuentesCompuestos.get(i).getCompuestos().size(); j++) {

                    auxop = auxop.add(fuentesCompuestos.get(i).getCompuestos().get(j).getIngredienteActivo());
                    fuentesCompuestos.get(i).getCompuestos().get(j).calculoCamposComplementarios(fuentesCompuestos.get(i).getTotalF(), fuentesCompuestos.get(i).getPrecioDoleSacoF(), fuentesCompuestos.get(i).getPresentacionKgF());

                }
                fuentesCompuestos.get(i).setTotalF(auxop);
            }

        } else {
            for (int i = 0; i < fuentesCompuestos.size(); i++) {

                for (int j = 0; j < fuentesCompuestos.get(i).getCompuestos().size(); j++) {
                    fuentesCompuestos.get(i).getCompuestos().get(j).calculoCamposComplementarios(fuentesCompuestos.get(i).getTotalF(), fuentesCompuestos.get(i).getPrecioDoleSacoF(), fuentesCompuestos.get(i).getPresentacionKgF());
                }
            }
        }

    }

    public void loadFuentesCompuestosCombinados() {
        actualFC = new FuenteCompuestoAux();
        selectedFC = new FuenteCompuestoAux();
        compuestosIngrediente = new ArrayList<>();

    }

    public void quitarCompuesto(ObjectId f, ObjectId c) {
        int i = 0;
        int j;
        while (i < fuentesCompuestos.size()) {
            if (fuentesCompuestos.get(i).getFuente().equals(f)) {
                j = 0;
                while (j < fuentesCompuestos.get(i).getCompuestos().size()) {
                    if (fuentesCompuestos.get(i).getCompuestos().get(j).getIdCompuesto().equals(c)) {

                        fuentesCompuestos.get(i).setTotalF(fuentesCompuestos.get(i).getTotalF().subtract(fuentesCompuestos.get(i).getCompuestos().get(j).getIngredienteActivo()));

                        fuentesCompuestos.get(i).getCompuestos().remove(j);

                        j = fuentesCompuestos.get(i).getCompuestos().size();
                    }
                    j++;
                }

                i = fuentesCompuestos.size();
            }

            i++;
        }
    }

    public void quitarFuente(ObjectId f) {
        int i = 0;

        while (i < fuentesCompuestos.size()) {
            if (fuentesCompuestos.get(i).getFuente().equals(f)) {

                fuentesCompuestos.remove(i);
                i = fuentesCompuestos.size();
            }

            i++;
        }
    }
    
    
    
    
    
    //GESTION BASE DATOS ********************************************************
    
    public void save(){
        
        if(controlDatos()){
            
            for(int i=0; i<fuentesCompuestos.size();i++){
                fuentesCompuestos.get(i).save();
            }
            
            
           
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "Fuente Ingresada"));
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ingresar y seleccionar datos obligatorios"));
        }        
        
    }
    
    
    
    Boolean controlDatos(){
        Boolean res = true;//continua
        
        if(fuentesCompuestos.size()==0){
            res = false; // no continua
        }
        
        return res;
    }
    //**************************************************************************
}
