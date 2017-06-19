/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.HistoricoFertilizacion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class HistoricoFertilizacionModel extends ListDataModel <HistoricoFertilizacion> implements SelectableDataModel<HistoricoFertilizacion>{
    
    
    public HistoricoFertilizacionModel() {
    }

    public HistoricoFertilizacionModel(List<HistoricoFertilizacion> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(HistoricoFertilizacion t) {
       return t.getId();
    }

    @Override
    public HistoricoFertilizacion getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       HistoricoFertilizacion obj = HistoricoFertilizacion.getById(aux);
       return obj;
    }
    
}
