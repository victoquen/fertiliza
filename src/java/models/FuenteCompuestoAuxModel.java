/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.FuenteCompuestoAux;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class FuenteCompuestoAuxModel extends ListDataModel <FuenteCompuestoAux> implements SelectableDataModel<FuenteCompuestoAux>{
    
    
    public FuenteCompuestoAuxModel() {
    }

    public FuenteCompuestoAuxModel(List<FuenteCompuestoAux> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(FuenteCompuestoAux t) {
       return t.getFuente();
    }

    @Override
    public FuenteCompuestoAux getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       FuenteCompuestoAux obj = FuenteCompuestoAux.getFuenteCompuestoById(aux);
       return obj;
       
    }
    
}
