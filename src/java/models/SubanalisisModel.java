/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Subanalisis;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class SubanalisisModel extends ListDataModel <Subanalisis> implements SelectableDataModel<Subanalisis>{
    
    
    public SubanalisisModel() {
    }

    public SubanalisisModel(List<Subanalisis> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Subanalisis t) {
       return t.getId();
    }

    @Override
    public Subanalisis getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Subanalisis obj = Subanalisis.getById(aux);
       return obj;
    }
    
}
