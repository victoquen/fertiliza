/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.Provincia;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class ProvinciaModel extends ListDataModel <Provincia> implements SelectableDataModel<Provincia>{
    
    
    public ProvinciaModel() {
    }

    public ProvinciaModel(List<Provincia> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Provincia t) {
       return t.getId();
    }

    @Override
    public Provincia getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Provincia obj = Provincia.getProvinciaById(aux);
       return obj;
    }
    
}
