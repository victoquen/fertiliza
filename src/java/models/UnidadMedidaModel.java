/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.UnidadMedida;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class UnidadMedidaModel extends ListDataModel <UnidadMedida> implements SelectableDataModel<UnidadMedida>{
    
    
    public UnidadMedidaModel() {
    }

    public UnidadMedidaModel(List<UnidadMedida> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(UnidadMedida t) {
       return t.getId();
    }

    @Override
    public UnidadMedida getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       UnidadMedida obj = UnidadMedida.getById(aux);
       return obj;
    }
    
}
