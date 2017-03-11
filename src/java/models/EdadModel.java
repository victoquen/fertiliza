/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Edad;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class EdadModel extends ListDataModel <Edad> implements SelectableDataModel<Edad>{
    
    
    public EdadModel() {
    }

    public EdadModel(List<Edad> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Edad t) {
       return t.getId();
    }

    @Override
    public Edad getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Edad obj = Edad.getEdadById(aux);
       return obj;
    }
    
}
