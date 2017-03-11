/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Cultivo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class CultivoModel extends ListDataModel <Cultivo> implements SelectableDataModel<Cultivo>{
    
    
    public CultivoModel() {
    }

    public CultivoModel(List<Cultivo> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Cultivo t) {
       return t.getId();
    }

    @Override
    public Cultivo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Cultivo obj = Cultivo.getCultivoById(aux);
       return obj;
    }
    
}
