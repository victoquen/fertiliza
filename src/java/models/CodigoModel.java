/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Codigo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class CodigoModel extends ListDataModel <Codigo> implements SelectableDataModel<Codigo>{
    
    
    public CodigoModel() {
    }

    public CodigoModel(List<Codigo> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Codigo t) {
       return t.getId();
    }

    @Override
    public Codigo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Codigo obj = Codigo.getById(aux);
       return obj;
    }
    
}
