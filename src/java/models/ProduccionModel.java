/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;
import entities.fertilizacion.Produccion;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author VICTOR OQUENDO
 */
public class ProduccionModel extends ListDataModel <Produccion> implements SelectableDataModel<Produccion>{
    
    
    public ProduccionModel() {
    }

    public ProduccionModel(List<Produccion> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Produccion t) {
       return t.getId();
    }

    @Override
    public Produccion getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Produccion obj = Produccion.getById(aux);
       return obj;
    }
    
}
