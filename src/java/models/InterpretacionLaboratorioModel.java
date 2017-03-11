/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.InterpretacionLaboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class InterpretacionLaboratorioModel extends ListDataModel <InterpretacionLaboratorio> implements SelectableDataModel<InterpretacionLaboratorio>{
    
    
    public InterpretacionLaboratorioModel() {
    }

    public InterpretacionLaboratorioModel(List<InterpretacionLaboratorio> list) {
        super(list);

    }
        
    @Override
    public Object getRowKey(InterpretacionLaboratorio t) {
       return t.getId();
    }

    @Override
    public InterpretacionLaboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       InterpretacionLaboratorio obj = InterpretacionLaboratorio.getInterpretacionLaboratorioById(aux);
       return obj;
    }
    
}
