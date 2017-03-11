/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Variedad;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class VariedadModel extends ListDataModel <Variedad> implements SelectableDataModel<Variedad>{
    
    
    public VariedadModel() {
    }

    public VariedadModel(List<Variedad> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Variedad t) {
       return t.getId();
    }

    @Override
    public Variedad getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Variedad obj = Variedad.getVariedadById(aux);
       return obj;
    }
    
}
