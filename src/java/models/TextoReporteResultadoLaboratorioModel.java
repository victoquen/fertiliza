/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.reportes.TextoReporteResultadoLaboratorio;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author Luduan
 */
public class TextoReporteResultadoLaboratorioModel extends ListDataModel <TextoReporteResultadoLaboratorio> implements SelectableDataModel<TextoReporteResultadoLaboratorio>{
    
    
    public TextoReporteResultadoLaboratorioModel() {
    }

    public TextoReporteResultadoLaboratorioModel(List<TextoReporteResultadoLaboratorio> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(TextoReporteResultadoLaboratorio t) {
       return t.getId();
    }

    @Override
    public TextoReporteResultadoLaboratorio getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       TextoReporteResultadoLaboratorio obj = TextoReporteResultadoLaboratorio.getById(aux);
       return obj;
    }
    
}
