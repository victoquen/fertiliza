/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.EtapaCultivo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class EtapaCultivoModel extends ListDataModel <EtapaCultivo> implements SelectableDataModel<EtapaCultivo>{
    
    
    public EtapaCultivoModel() {
    }

    public EtapaCultivoModel(List<EtapaCultivo> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(EtapaCultivo t) {
       return t.getId();
    }

    @Override
    public EtapaCultivo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       EtapaCultivo obj = EtapaCultivo.getById(aux);
       return obj;
    }
    
}
