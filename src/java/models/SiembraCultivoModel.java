/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.SiembraCultivo;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class SiembraCultivoModel extends ListDataModel <SiembraCultivo> implements SelectableDataModel<SiembraCultivo>{
    
    
    public SiembraCultivoModel() {
    }

    public SiembraCultivoModel(List<SiembraCultivo> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(SiembraCultivo t) {
       return t.getId();
    }

    @Override
    public SiembraCultivo getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       SiembraCultivo obj = SiembraCultivo.getById(aux);
       return obj;
    }
    
}
