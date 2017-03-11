/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.ResultadoLaboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class ResultadoLaboratorioModel extends ListDataModel <ResultadoLaboratorio> implements SelectableDataModel<ResultadoLaboratorio>{
    
    
    public ResultadoLaboratorioModel() {
    }

    public ResultadoLaboratorioModel(List<ResultadoLaboratorio> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(ResultadoLaboratorio t) {
       return t.getId();
    }

    @Override
    public ResultadoLaboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       ResultadoLaboratorio obj = ResultadoLaboratorio.getResultadoLaboratorioById(aux);
       return obj;
    }
    
}
