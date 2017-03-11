/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Departamento;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class DepartamentoModel extends ListDataModel <Departamento> implements SelectableDataModel<Departamento>{
    
    
    public DepartamentoModel() {
    }

    public DepartamentoModel(List<Departamento> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Departamento t) {
       return t.getId();
    }

    @Override
    public Departamento getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Departamento obj = Departamento.getById(aux);
       return obj;
    }
    
}
