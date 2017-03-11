/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Hacienda;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class HaciendaModel extends ListDataModel <Hacienda> implements SelectableDataModel<Hacienda>{
    
    
    public HaciendaModel() {
    }

    public HaciendaModel(List<Hacienda> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Hacienda t) {
       return t.getId();
    }

    @Override
    public Hacienda getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Hacienda obj = Hacienda.getHaciendaById(aux);
       return obj;
    }
    
}
