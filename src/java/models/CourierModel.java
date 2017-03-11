/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Courier;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class CourierModel extends ListDataModel <Courier> implements SelectableDataModel<Courier>{
    
    
    public CourierModel() {
    }

    public CourierModel(List<Courier> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Courier t) {
       return t.getId();
    }

    @Override
    public Courier getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Courier obj = Courier.getById(aux);
       return obj;
    }
    
}
