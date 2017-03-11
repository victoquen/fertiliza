/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import helpers.Pais;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class PaisModel extends ListDataModel <Pais> implements SelectableDataModel<Pais>{
    
    
    public PaisModel() {
    }

    public PaisModel(List<Pais> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Pais t) {
       return t.getId();
    }

    @Override
    public Pais getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Pais obj = Pais.getPaisById(aux);
       return obj;
    }
    
}
