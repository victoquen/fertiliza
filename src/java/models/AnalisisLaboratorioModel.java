package models;


import entities.fertilizacion.AnalisisLaboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pablo
 */
public class AnalisisLaboratorioModel extends ListDataModel <AnalisisLaboratorio> implements SelectableDataModel<AnalisisLaboratorio>{
    
    
    public AnalisisLaboratorioModel() {
    }

    public AnalisisLaboratorioModel(List<AnalisisLaboratorio> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(AnalisisLaboratorio t) {
       return t.getId();
    }

    @Override
    public AnalisisLaboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       AnalisisLaboratorio obj = AnalisisLaboratorio.getAnalisisLaboratorioById(aux);
       return obj;
    }
    
    
}
