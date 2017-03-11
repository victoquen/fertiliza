/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.TipoAplicacionFuente;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class TipoAplicacionFuenteModel extends ListDataModel <TipoAplicacionFuente> implements SelectableDataModel<TipoAplicacionFuente>{
    
    
    public TipoAplicacionFuenteModel() {
    }

    public TipoAplicacionFuenteModel(List<TipoAplicacionFuente> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(TipoAplicacionFuente t) {
       return t.getId();
    }

    @Override
    public TipoAplicacionFuente getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       TipoAplicacionFuente obj = TipoAplicacionFuente.getById(aux);
       return obj;
    }
    
}
