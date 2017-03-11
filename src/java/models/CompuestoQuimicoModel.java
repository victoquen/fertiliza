/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.CompuestoQuimico;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class CompuestoQuimicoModel extends ListDataModel <CompuestoQuimico> implements SelectableDataModel<CompuestoQuimico>{
    
    
    public CompuestoQuimicoModel() {
    }

    public CompuestoQuimicoModel(List<CompuestoQuimico> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(CompuestoQuimico t) {
       return t.getId();
    }

    @Override
    public CompuestoQuimico getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       CompuestoQuimico obj = CompuestoQuimico.getCompuestoQuimicoById(aux);
       return obj;
    }
    
}
