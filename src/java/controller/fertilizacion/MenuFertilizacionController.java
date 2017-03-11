/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.fertilizacion;

import java.io.Serializable;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;

/**
 *
 * @author pablo
 */
@ViewScoped
@Named
public class MenuFertilizacionController implements Serializable {

    String opIndex;
    String opCompuesto;
    String opFuente;

    String opCombinacionFuenteCompuesto;
    String opNuevoCombinacioFuenteCompuesto;

    String opCliente;
    String opNuevoCliente;
    String opModificarCliente;

    String opCultivo;
    String opVariedad;
    String opEdad;

    String opHacienda;
    String opLote;
    String opNuevaHacienda;
    String opNuevoLote;

    String opPais;
    String opProvincia;
    String opCanton;

    String opLaboratorio;
    String opInterpretacionLaboratorio;
    String opAnalisisLaboratorio;
    String opMuestraLaboratorio;
    String opCrearMuestraLaboratorio;
    String opResultadoLaboratorio;

    String opSondaTipo;
    String opMetodologia;

    String opReporteLaboratorio;

    String opMatriz;
    String opUnidadMedida;
    String opCourier;
    String opTipoAplicacionFuente;
    String opCodigo;

    String opSubanalisis;
    String opDepartamento;
    
    String opProfundidad;

    DefaultMenuModel modelMenu;

    public MenuFertilizacionController() {

        this.opIndex = "indexFertilizacion";
        this.opCompuesto = "crearCompuesto";
        this.opFuente = "crearFuente";
        this.opCombinacionFuenteCompuesto = "listadoCombinacionFuenComp";
        this.opNuevoCombinacioFuenteCompuesto = "crearCombinacionFuenComp";
        this.opCliente = "listadoCliente";
        this.opNuevoCliente = "crearCliente";
        this.opCultivo = "crearCultivo";
        this.opVariedad = "crearVariedad";
        this.opEdad = "crearEdad";
        this.opHacienda = "listadoHacienda";
        this.opLote = "crearLote";
        this.opNuevaHacienda = "crearHacienda";
        this.opNuevoLote = "crearLote";
        this.opPais = "crearPais";
        this.opProvincia = "crearProvincia";
        this.opCanton = "crearCanton";
        this.opLaboratorio = "crearLaboratorio";
        this.opInterpretacionLaboratorio = "crearInterpretacionLaboratorio";
        this.opAnalisisLaboratorio = "crearAnalisisLaboratorio";
        this.opMuestraLaboratorio = "listadoMuestraLaboratorio";
        this.opCrearMuestraLaboratorio = "crearMuestraLaboratorio";
        this.opResultadoLaboratorio = "crearResultadoLaboratorio";
        this.opSondaTipo = "crearSondaTipo";
        this.opMetodologia = "crearMetodologia";
        this.opReporteLaboratorio = "crearReporteLaboratorio";
        this.opMatriz = "crearMatriz";
        this.opUnidadMedida = "crearUnidadMedida";
        this.opCourier = "crearCourier";
        this.opTipoAplicacionFuente = "crearTipoAplicacionFuente";
        this.opCodigo = "crearCodigo";
        this.opSubanalisis = "crearSubanalisis";
        this.opModificarCliente = "modificarCliente";
        this.opDepartamento = "crearDepartamento";
        this.opProfundidad = "crearProfundidad";

        modelMenu = new DefaultMenuModel();

        DefaultSubMenu indexSubmenu = new DefaultSubMenu("INICIO");
        DefaultMenuItem item = new DefaultMenuItem("Inicio");
        item.setIcon("ui-icon-home");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        item.setId("inicio");
        indexSubmenu.addElement(item);
        indexSubmenu.setExpanded(true);
        
        modelMenu.addElement(indexSubmenu);
        //**********************************************************************
        //**********************************************************************
        DefaultSubMenu departementosSubmenu = new DefaultSubMenu("DEPARTAMENTOS");

        DefaultSubMenu agricolaSubmenu = new DefaultSubMenu("AGRICOLA");
        item = new DefaultMenuItem("Suelos y Sustratos");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);        

        item = new DefaultMenuItem("Aguas de Riego y Disoluciones Nutritivas");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);

        item = new DefaultMenuItem("Foliar y Fruto");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);

        item = new DefaultMenuItem("Microbiología y Fitopatología");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);

        item = new DefaultMenuItem("Res. de Plaguicidas y Contaminantes (Suelo y Agua de Riego)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);

        item = new DefaultMenuItem("Otros (Agronomía)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        agricolaSubmenu.addElement(item);
        departementosSubmenu.addElement(agricolaSubmenu);
        //**********************************************************************
        //**********************************************************************

        DefaultSubMenu alimentarioSubmenu = new DefaultSubMenu("ALIMENTARIO");
        item = new DefaultMenuItem("Residuos Plaguicidas y Contaminantes (Alimentos)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);

        item = new DefaultMenuItem("Nitratos, Bromatos y Bromuro");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);

        item = new DefaultMenuItem("Micotoxinas");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);

        item = new DefaultMenuItem("Microbiología (Seguridad y Alimentaria)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);

        item = new DefaultMenuItem("Metales (Alimentos)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);

        item = new DefaultMenuItem("Otros (Seguridad Alimentaria)");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        alimentarioSubmenu.addElement(item);
        
        departementosSubmenu.addElement(alimentarioSubmenu);
        //**********************************************************************
        //**********************************************************************

        DefaultSubMenu ambienteSubmenu = new DefaultSubMenu("AMBIENTE");
        item = new DefaultMenuItem("Aguas de Consumo");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        item.setAjax(false);
        ambienteSubmenu.addElement(item);

        item = new DefaultMenuItem("Microbiología Aguas de Consumo");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpIndex());
        ambienteSubmenu.addElement(item);
        item.setAjax(false);
        departementosSubmenu.addElement(ambienteSubmenu);

        modelMenu.addElement(departementosSubmenu);
        //**********************************************************************
        //**********************************************************************
        DefaultSubMenu cultivoSubmenu = new DefaultSubMenu("ADMIN CULTIVO");
        item = new DefaultMenuItem("Cultivo");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCultivo());
        item.setAjax(false);
        cultivoSubmenu.addElement(item);

        item = new DefaultMenuItem("Variedad");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpVariedad());
        item.setAjax(false);
        cultivoSubmenu.addElement(item);

        item = new DefaultMenuItem("Edad");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpEdad());
        item.setAjax(false);
        cultivoSubmenu.addElement(item);
        
        modelMenu.addElement(cultivoSubmenu);
        //**********************************************************************
        //**********************************************************************

        DefaultSubMenu clienteSubmenu = new DefaultSubMenu("ADMIN CLIENTE");
        item = new DefaultMenuItem("Cliente");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCliente());
        item.setAjax(false);
        clienteSubmenu.addElement(item);
        modelMenu.addElement(clienteSubmenu);
        //**********************************************************************
        //**********************************************************************

        DefaultSubMenu fuentesSubmenu = new DefaultSubMenu("ADMIN FUENTES");
        item = new DefaultMenuItem("Compuesto Quim.");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCompuesto());
        item.setAjax(false);
        fuentesSubmenu.addElement(item);

        item = new DefaultMenuItem("Fuente");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpFuente());
        item.setAjax(false);
        fuentesSubmenu.addElement(item);

        item = new DefaultMenuItem("Combinación Fuent-Comp");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCombinacionFuenteCompuesto());
        item.setAjax(false);
        fuentesSubmenu.addElement(item);
        
        modelMenu.addElement(fuentesSubmenu);
        //**********************************************************************
        //**********************************************************************
        
        DefaultSubMenu laboratorioSubmenu = new DefaultSubMenu("LABORATORIO AGRICOLA");
        item = new DefaultMenuItem("Muestras Laboratorio");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpMuestraLaboratorio());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Subanálsis");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpSubanalisis());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Paquete de Análisis");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpAnalisisLaboratorio());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Unidad Medida");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpUnidadMedida());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Courier");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCourier());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Resultados Laboratorio");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpResultadoLaboratorio());
        item.setAjax(false);
        laboratorioSubmenu.addElement(item);
        
        modelMenu.addElement(laboratorioSubmenu);        
        //**********************************************************************
        //**********************************************************************
        
        DefaultSubMenu ubicacionSubmenu = new DefaultSubMenu("ADMIN UBICACION");
        item = new DefaultMenuItem("País");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpPais());
        item.setAjax(false);
        ubicacionSubmenu.addElement(item);       
        
        item = new DefaultMenuItem("Provincia");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpProvincia());
        item.setAjax(false);
        ubicacionSubmenu.addElement(item);       
        
        item = new DefaultMenuItem("Cantón");
        item.setIcon("ui-icon-gear");
        item.setCommand(goToOpCanton());
        item.setAjax(false);
        ubicacionSubmenu.addElement(item);
        
        modelMenu.addElement(ubicacionSubmenu);
        //**********************************************************************
        //**********************************************************************
                
        DefaultSubMenu parametrizableSubmenu = new DefaultSubMenu("PARAMETRIZABLES");
        item = new DefaultMenuItem("Departamento");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpDepartamento());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Laboratorio");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpLaboratorio());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Interpretación Laboratorio");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpInterpretacionLaboratorio());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Metodologías");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpMetodologia());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Sondas Tipo");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpSondaTipo());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Matriz");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpMatriz());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Tipo Aplica. Fuente");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpTipoAplicacionFuente());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Profundidad");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpProfundidad());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Código");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpCodigo());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        item = new DefaultMenuItem("Reporte Laboratorio");
        item.setIcon("ui-icon-wrench");
        item.setCommand(goToOpReporteLaboratorio());
        item.setAjax(false);
        parametrizableSubmenu.addElement(item);
        
        
                
        modelMenu.addElement(parametrizableSubmenu);
        //**********************************************************************
        //**********************************************************************
        
        
        //**********************************************************************
        //**********************************************************************
        
        
        //**********************************************************************
        //**********************************************************************

    }

    public DefaultMenuModel getModelMenu() {
        return modelMenu;
    }

    public void setModelMenu(DefaultMenuModel modelMenu) {
        this.modelMenu = modelMenu;
    }

    private void reloadSession() {
        ExternalContext tmpEC;
        Map sMap;
        tmpEC = FacesContext.getCurrentInstance().getExternalContext();
        sMap = tmpEC.getSessionMap();

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CompuestoQuimicoController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.FuenteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CombinacionFuenCompController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.ClienteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CultivoController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.EdadController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.VariedadController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.HaciendaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.LoteController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.PaisController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.ProvinciaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CantonController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.AnalisisLaboratorioController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.InterpretacionLaboratorioController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.LaboratorioController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.MuestraLaboratorioController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.ResultadoLaboratorioController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.SondaTipoController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.MetodologiaController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.TextoReporteResultadoLaboratorioController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.MatrizController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.UnidadMedidaController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CourierController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.TipoAplicacionFuenteController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.CodigoController-null-false");

        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.SubanalisisController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.DepartamentoController-null-false");
        sMap.remove("org.jboss.weld.context.http.HttpSessionContext#org.jboss.weld.bean-web-ManagedBean-web-controllers.fertilizacion.ProfundidadController-null-false");
    }

    public String goToOpIndex() {
        reloadSession();
        return this.opIndex;
    }
    
    public String goToOpProfundidad(){
        reloadSession();
        return this.opProfundidad;
    }

    public String goToOpCompuesto() {
        reloadSession();
        return this.opCompuesto;
    }

    public String goToOpFuente() {
        reloadSession();
        return this.opFuente;
    }

    public String goToOpCombinacionFuenteCompuesto() {
        reloadSession();
        return this.opCombinacionFuenteCompuesto;
    }

    public String goToOpNuevoCombinacionFuenteCompuesto() {
        reloadSession();
        return this.opNuevoCombinacioFuenteCompuesto;
    }

    public String goToOpCliente() {
        reloadSession();
        return this.opCliente;
    }

    public String goToOpNuevoCliente() {
        reloadSession();
        return this.opNuevoCliente;
    }

    public String goToOpCultivo() {
        reloadSession();
        return this.opCultivo;
    }

    public String goToOpEdad() {
        reloadSession();
        return this.opEdad;
    }

    public String goToOpVariedad() {
        reloadSession();
        return this.opVariedad;
    }

    public String goToOpHacienda() {
        reloadSession();
        return this.opHacienda;
    }

    public String goToOpLote() {
        reloadSession();
        return this.opLote;
    }

    public String goToOpNuevaHacienda() {
        reloadSession();
        return this.opNuevaHacienda;
    }

    public String goToOpNuevoLote() {
        reloadSession();
        return this.opNuevoLote;
    }

    public String goToOpPais() {
        reloadSession();
        return this.opPais;
    }

    public String goToOpProvincia() {
        reloadSession();
        return this.opProvincia;
    }

    public String goToOpCanton() {
        reloadSession();
        return this.opCanton;
    }

    public String goToOpLaboratorio() {
        reloadSession();
        return this.opLaboratorio;
    }

    public String goToOpInterpretacionLaboratorio() {
        reloadSession();
        return this.opInterpretacionLaboratorio;
    }

    public String goToOpAnalisisLaboratorio() {
        reloadSession();
        return this.opAnalisisLaboratorio;
    }

    public String goToOpMuestraLaboratorio() {
        reloadSession();
        return this.opMuestraLaboratorio;
    }

    public String goToOpResultadoLaboratorio() {
        reloadSession();
        return this.opResultadoLaboratorio;
    }

    public String goToOpCrearMuestraLaboratorio() {
        reloadSession();
        return this.opCrearMuestraLaboratorio;
    }

    public String goToOpSondaTipo() {
        reloadSession();
        return this.opSondaTipo;
    }

    public String goToOpMetodologia() {
        reloadSession();
        return this.opMetodologia;
    }

    public String goToOpReporteLaboratorio() {
        reloadSession();
        return this.opReporteLaboratorio;
    }

    public String goToOpMatriz() {
        reloadSession();
        return this.opMatriz;
    }

    public String goToOpUnidadMedida() {
        reloadSession();
        return this.opUnidadMedida;
    }

    public String goToOpCourier() {
        reloadSession();
        return this.opCourier;
    }

    public String goToOpTipoAplicacionFuente() {
        reloadSession();
        return this.opTipoAplicacionFuente;
    }

    public String goToOpCodigo() {
        reloadSession();
        return this.opCodigo;
    }

    public String goToOpSubanalisis() {
        reloadSession();
        return this.opSubanalisis;
    }

    public String goToOpModificarCliente() {
        reloadSession();
        return this.opModificarCliente;
    }

    public String goToOpDepartamento() {
        reloadSession();
        return this.opDepartamento;
    }
}
