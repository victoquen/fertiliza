/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import helpers.Canton;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class CantonModel extends ListDataModel <Canton> implements SelectableDataModel<Canton>{
    
    
    public CantonModel() {
    }

    public CantonModel(List<Canton> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Canton t) {
       return t.getId();
    }

    @Override
    public Canton getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Canton obj = Canton.getCantonById(aux);
       return obj;
    }
    
}
