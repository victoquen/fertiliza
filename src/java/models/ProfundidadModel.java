/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Profundidad;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class ProfundidadModel extends ListDataModel <Profundidad> implements SelectableDataModel<Profundidad>{
    
    
    public ProfundidadModel() {
    }

    public ProfundidadModel(List<Profundidad> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Profundidad t) {
       return t.getId();
    }

    @Override
    public Profundidad getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Profundidad obj = Profundidad.getById(aux);
       return obj;
    }
    
}
