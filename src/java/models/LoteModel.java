/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Lote;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class LoteModel extends ListDataModel <Lote> implements SelectableDataModel<Lote>{
    
    
    public LoteModel() {
    }

    public LoteModel(List<Lote> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Lote t) {
       return t.getId();
    }

    @Override
    public Lote getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Lote obj = Lote.getLoteById(aux);
       return obj;
    }
    
}
