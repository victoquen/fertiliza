/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Laboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class LaboratorioModel extends ListDataModel <Laboratorio> implements SelectableDataModel<Laboratorio>{
    
    
    public LaboratorioModel() {
    }

    public LaboratorioModel(List<Laboratorio> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Laboratorio t) {
       return t.getId();
    }

    @Override
    public Laboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Laboratorio obj = Laboratorio.getLaboratorioById(aux);
       return obj;
    }
    
}
