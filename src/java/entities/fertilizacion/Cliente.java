/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import db.MongoManager;
import helpers.Canton;
import helpers.Pais;
import helpers.Provincia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class Cliente implements Serializable {

    ObjectId id;
    String codigo;
    String nombre;
    String tipo;
    String rucCi;
    String mercado;
    String afacturar;
    String vendedor;
    String direccion;
    String tipoCliente; //  GENERICO,  PLANTECH

    ObjectId pais;
    ObjectId provincia;
    ObjectId canton;
    String sector;

    ObjectId departamento;
    String leyendaDepartamento;
    String observacionGenerico;
    String leyendaPais;
    String leyendaProvincia;
    String leyendaCanton;

    String atencionFactura;
    List<String> telefono;
    List<String> email;

    List<Hacienda> haciendas;

    public Cliente() {
        this.codigo = "";
        this.nombre = "";
        this.tipo = "";
        this.rucCi = "";
        this.afacturar = "";
        this.vendedor = "";
        this.direccion = "";
        this.sector = "";
        this.telefono = new ArrayList<>();
        this.atencionFactura = "";
        this.email = new ArrayList<>();
        this.mercado = "";
        this.tipoCliente = "";
        this.observacionGenerico = "";

        this.haciendas = new ArrayList<>();

    }

    public Cliente(String codigo, String nombre, String tipo, String rucCi, String mercado, String afacturar, String vendedor, String direccion, String sector, List<String> telefono, String atencionFactura, List<String> email) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rucCi = rucCi;
        this.mercado = mercado;
        this.afacturar = afacturar;
        this.vendedor = vendedor;
        this.direccion = direccion;
        this.sector = sector;
        this.telefono = telefono;
        this.atencionFactura = atencionFactura;
        this.email = email;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public ObjectId getDepartamento() {
        return departamento;
    }

    public void setDepartamento(ObjectId departamento) {
        this.departamento = departamento;
    }

    public String getLeyendaDepartamento() {
        return leyendaDepartamento;
    }

    public void setLeyendaDepartamento(String leyendaDepartamento) {
        this.leyendaDepartamento = leyendaDepartamento;
    }

    public List<Hacienda> getHaciendas() {
        return haciendas;
    }

    public void setHaciendas(List<Hacienda> haciendas) {
        this.haciendas = haciendas;
    }

    public String getMercado() {
        return mercado;
    }

    public void setMercado(String mercado) {
        this.mercado = mercado;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRucCi() {
        return rucCi;
    }

    public void setRucCi(String rucCi) {
        this.rucCi = rucCi;
    }

    public String getAfacturar() {
        return afacturar;
    }

    public void setAfacturar(String afacturar) {
        this.afacturar = afacturar;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public ObjectId getPais() {
        return pais;
    }

    public void setPais(ObjectId pais) {
        this.pais = pais;
    }

    public ObjectId getProvincia() {
        return provincia;
    }

    public void setProvincia(ObjectId provincia) {
        this.provincia = provincia;
    }

    public ObjectId getCanton() {
        return canton;
    }

    public void setCanton(ObjectId canton) {
        this.canton = canton;
    }

    public String getLeyendaPais() {
        return leyendaPais;
    }

    public void setLeyendaPais(String leyendaPais) {
        this.leyendaPais = leyendaPais;
    }

    public String getLeyendaProvincia() {
        return leyendaProvincia;
    }

    public void setLeyendaProvincia(String leyendaProvincia) {
        this.leyendaProvincia = leyendaProvincia;
    }

    public String getLeyendaCanton() {
        return leyendaCanton;
    }

    public void setLeyendaCanton(String leyendaCanton) {
        this.leyendaCanton = leyendaCanton;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getAtencionFactura() {
        return atencionFactura;
    }

    public void setAtencionFactura(String atencionFactura) {
        this.atencionFactura = atencionFactura;
    }

    public List<String> getTelefono() {
        return telefono;
    }

    public void setTelefono(List<String> telefono) {
        this.telefono = telefono;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public String getObservacionGenerico() {
        return observacionGenerico;
    }

    public void setObservacionGenerico(String observacionGenerico) {
        this.observacionGenerico = observacionGenerico;
    }

    
    public ObjectId save() {
        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("cliente");

        Document obj = new Document("codigo", this.codigo).append("nombre", this.nombre.toUpperCase()).append("tipo", this.tipo).append("rucci", this.rucCi).append("mercado", this.mercado)
                .append("afacturar", this.afacturar).append("vendedor", this.vendedor).append("direccion", this.direccion)
                .append("pais", this.pais).append("provincia", this.provincia).append("canton", this.canton)
                .append("sector", this.sector).append("atencionfactura", this.atencionFactura).append("tipoCliente", this.tipoCliente)
                .append("departamento", this.departamento).append("observacionGenerico", this.observacionGenerico);

        List<String> ltelf = this.telefono;
        StringBuilder auxtelf = new StringBuilder(75);
        for (int i = 0; i < ltelf.size(); i++) {
            auxtelf.append(ltelf.get(i)).append(";");
        }

        List<String> lmail = this.email;
        StringBuilder auxmail = new StringBuilder(75);
        for (int j = 0; j < lmail.size(); j++) {
            auxmail.append(lmail.get(j)).append(";");
        }

        table.insertOne(obj.append("telefono", auxtelf.toString()).append("email", auxmail.toString()));

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        Cliente before = getClienteById(this.id);

        MongoManager mongo = MongoManager.getInstance();

        List<String> ltelf = this.telefono;
        StringBuilder auxtelf = new StringBuilder(75);
        for (int i = 0; i < ltelf.size(); i++) {
            auxtelf.append(ltelf.get(i)).append(";");
        }

        List<String> lmail = this.email;
        StringBuilder auxmail = new StringBuilder(75);
        for (int j = 0; j < lmail.size(); j++) {
            auxmail.append(lmail.get(j)).append(";");
        }
        
        Document obj = new Document("$set", new Document("codigo", this.codigo)
                .append("nombre", this.nombre)
                .append("tipo", this.tipo)
                .append("rucci", this.rucCi)
                .append("mercado", this.mercado)
                .append("afacturar", this.afacturar)
                .append("vendedor", this.vendedor)
                .append("direccion", this.direccion)
                .append("pais", this.pais)
                .append("provincia", this.provincia)
                .append("canton", this.canton)
                .append("sector", this.sector)
                .append("atencionfactura", this.atencionFactura)
                .append("tipoCliente", this.tipoCliente)
                .append("departamento", this.departamento)
                .append("observacionGenerico", this.observacionGenerico)
                .append("telefono", auxtelf.toString())
                .append("email", auxmail.toString()));

        

        mongo.db.getCollection("cliente").updateOne(new Document("_id", this.id), obj);

    }

    public static Cliente getClienteByName(String name) {
        Cliente obj = new Cliente();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("cliente").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");

                obj.codigo = document.get("codigo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.rucCi = document.get("rucci").toString();
                obj.mercado = document.get("mercado").toString();
                obj.afacturar = document.get("afacturar").toString();
                obj.vendedor = document.get("vendedor").toString();
                obj.direccion = document.get("direccion").toString();
                obj.pais = (ObjectId) document.get("pais");
                obj.provincia = (ObjectId) document.get("provincia");
                obj.canton = (ObjectId) document.get("canton");
                obj.sector = document.get("sector").toString();
                obj.atencionFactura = document.get("atencionfactura").toString();

                obj.telefono = Arrays.asList(document.get("telefono").toString().split(";"));
                obj.email = Arrays.asList(document.get("email").toString().split(";"));

                obj.leyendaPais = Pais.getPaisById(obj.pais).getNombre();
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.provincia).getNombre();
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre();

                obj.tipoCliente = document.get("tipoCliente").toString();
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.observacionGenerico = document.getString("observacionGenerico");

                //obj.haciendas = Hacienda.getAllHaciendaByClienteId(obj.id);

            }

        });

        return obj;
    }

    public static Cliente getClienteById(ObjectId id) {
        Cliente obj = new Cliente();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("cliente").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.get("codigo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.rucCi = document.get("rucci").toString();
                obj.mercado = document.get("mercado").toString();
                obj.afacturar = document.get("afacturar").toString();
                obj.vendedor = document.get("vendedor").toString();
                obj.direccion = document.get("direccion").toString();
                obj.pais = (ObjectId) document.get("pais");
                obj.provincia = (ObjectId) document.get("provincia");
                obj.canton = (ObjectId) document.get("canton");
                obj.sector = document.get("sector").toString();
                obj.atencionFactura = document.get("atencionfactura").toString();
                obj.telefono = Arrays.asList(document.get("telefono").toString().split(";"));
                obj.email = Arrays.asList(document.get("email").toString().split(";"));

                obj.leyendaPais = Pais.getPaisById(obj.pais).getNombre();
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.provincia).getNombre();
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre();
                
                obj.tipoCliente = document.get("tipoCliente").toString();
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.observacionGenerico = document.getString("observacionGenerico");

                //obj.haciendas = Hacienda.getAllHaciendaByClienteId(obj.id);
            }

        });

        return obj;
    }

    public static List<Cliente> getAllClientes() {
        List<Cliente> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("cliente").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Cliente obj = new Cliente();
                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.get("codigo").toString();
                obj.nombre = document.get("nombre").toString();
                obj.tipo = document.get("tipo").toString();
                obj.rucCi = document.get("rucci").toString();
                obj.mercado = document.get("mercado").toString();
                obj.afacturar = document.get("afacturar").toString();
                obj.vendedor = document.get("vendedor").toString();
                obj.direccion = document.get("direccion").toString();
                obj.pais = (ObjectId) document.get("pais");
                obj.provincia = (ObjectId) document.get("provincia");
                obj.canton = (ObjectId) document.get("canton");
                obj.sector = document.get("sector").toString();
                obj.atencionFactura = document.get("atencionfactura").toString();
                obj.telefono = Arrays.asList(document.get("telefono").toString().split(";"));
                obj.email = Arrays.asList(document.get("email").toString().split(";"));

                obj.leyendaPais = Pais.getPaisById(obj.pais).getNombre();
                obj.leyendaProvincia = Provincia.getProvinciaById(obj.provincia).getNombre();
                obj.leyendaCanton = Canton.getCantonById(obj.canton).getNombre();
                
                obj.tipoCliente = document.get("tipoCliente").toString();
                obj.departamento = document.getObjectId("departamento");
                obj.leyendaDepartamento = Departamento.getById(obj.departamento).getNombre();
                obj.observacionGenerico = document.getString("observacionGenerico");

                obj.haciendas = Hacienda.getAllHaciendaByClienteId(obj.id);

                res.add(obj);
            }

        });

        return res;
    }

}
