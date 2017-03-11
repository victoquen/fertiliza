/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Matriz;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class MatrizModel extends ListDataModel <Matriz> implements SelectableDataModel<Matriz>{
    
    
    public MatrizModel() {
    }

    public MatrizModel(List<Matriz> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Matriz t) {
       return t.getId();
    }

    @Override
    public Matriz getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Matriz obj = Matriz.getById(aux);
       return obj;
    }
    
}
