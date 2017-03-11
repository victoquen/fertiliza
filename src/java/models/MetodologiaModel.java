/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Metodologia;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Luduan
 */
public class MetodologiaModel extends ListDataModel <Metodologia> implements SelectableDataModel<Metodologia>{
    
    
    public MetodologiaModel() {
    }

    public MetodologiaModel(List<Metodologia> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Metodologia t) {
       return t.getId();
    }

    @Override
    public Metodologia getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Metodologia obj = Metodologia.getMetodologiaById(aux);
       return obj;
    }

}
