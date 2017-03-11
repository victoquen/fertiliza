/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.SondaTipo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Luduan
 */
public class SondaTipoModel extends ListDataModel <SondaTipo> implements SelectableDataModel<SondaTipo>{
    
    
    public SondaTipoModel() {
    }

    public SondaTipoModel(List<SondaTipo> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(SondaTipo t) {
       return t.getId();
    }

    @Override
    public SondaTipo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       SondaTipo obj = SondaTipo.getSondaTipoById(aux);
       return obj;
    }
    
}
