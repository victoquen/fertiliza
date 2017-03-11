/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.MuestraLaboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class MuestraLaboratorioModel extends ListDataModel <MuestraLaboratorio> implements SelectableDataModel<MuestraLaboratorio>{
    
    
    public MuestraLaboratorioModel() {
    }

    public MuestraLaboratorioModel(List<MuestraLaboratorio> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(MuestraLaboratorio t) {
       return t.getId();
    }

    @Override
    public MuestraLaboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       MuestraLaboratorio obj = MuestraLaboratorio.getMuestraLaboratorioById(aux);
       return obj;
    }
    
}
