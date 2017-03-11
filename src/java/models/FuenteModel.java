/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Fuente;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class FuenteModel extends ListDataModel <Fuente> implements SelectableDataModel<Fuente>{
    
    
    public FuenteModel() {
    }

    public FuenteModel(List<Fuente> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Fuente t) {
       return t.getId();
    }

    @Override
    public Fuente getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Fuente obj = Fuente.getFuenteById(aux);
       return obj;
    }
    
}
