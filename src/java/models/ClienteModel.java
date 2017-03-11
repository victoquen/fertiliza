/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import entities.fertilizacion.Cliente;
import java.util.List;
import javax.faces.model.ListDataModel;
import org.bson.types.ObjectId;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author pablo
 */
public class ClienteModel extends ListDataModel <Cliente> implements SelectableDataModel<Cliente>{
    
    
    public ClienteModel() {
    }

    public ClienteModel(List<Cliente> list) {
        super(list);

    }
    
    
    @Override
    public Object getRowKey(Cliente t) {
       return t.getId();
    }

    @Override
    public Cliente getRowData(String id) {
       ObjectId aux = new ObjectId(id);
       Cliente obj = Cliente.getClienteById(aux);
       return obj;
    }
    
}
