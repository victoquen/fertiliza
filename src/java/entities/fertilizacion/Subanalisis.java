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
import entities.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author VICTOR OQUENDO
 */
public class Subanalisis implements Serializable {

    ObjectId id;
    Integer codigo;
    BigDecimal loq;
    Integer tat;
    BigDecimal costo;
    String codigoLaboratorio;
    String nombre;
    String simbolo;
    String metodo;
    String origen;
    String nota;
    ObjectId matriz;
    ObjectId unidadMedida;
    ObjectId laboratorio;
    ObjectId usuario;
    Date fechaIngreso;

    Integer darBaja; //0 activos-se muestra, 1 desactivados-no se muestra

    String leyendaMatriz;
    String leyendaUnidadMedida;
    String leyendaLaboratorio;
    String leyendaUsuario;
    String formatFechaIngreso;

    public Subanalisis() {
        this.loq = new BigDecimal(0);
        this.tat = 0;
        this.costo = new BigDecimal(0);
        this.fechaIngreso = new Date();

        this.codigoLaboratorio = "";
        this.nombre = "";
        this.simbolo = "";
        this.metodo = "";
        this.origen = "";
        this.nota = "";
        this.leyendaMatriz = "";
        this.leyendaUnidadMedida = "";
        this.leyendaLaboratorio = "";
        this.leyendaUsuario = "";
        this.formatFechaIngreso = "";
        this.darBaja = 0;
    }

    public Subanalisis(Integer codigo, String codigoLaboratorio, String nombre, String simbolo, String metodo, BigDecimal loq, Integer tat, BigDecimal costo, String origen, String nota, String leyendaMatriz, String leyendaUnidadMedida, String leyendaLaboratorio, String leyendaUsuario) {
        this.codigo = codigo;
        this.codigoLaboratorio = codigoLaboratorio;
        this.nombre = nombre;
        this.simbolo = simbolo;
        this.metodo = metodo;
        this.loq = loq;
        this.tat = tat;
        this.costo = costo;
        this.origen = origen;
        this.nota = nota;
        this.leyendaMatriz = leyendaMatriz;
        this.leyendaUnidadMedida = leyendaUnidadMedida;
        this.leyendaLaboratorio = leyendaLaboratorio;
        this.leyendaUsuario = leyendaUsuario;
    }

    public Integer getDarBaja() {
        return darBaja;
    }

    public void setDarBaja(Integer darBaja) {
        this.darBaja = darBaja;
    }

    public String getFormatFechaIngreso() {
        return formatFechaIngreso;
    }

    public void setFormatFechaIngreso(String formatFechaIngreso) {
        this.formatFechaIngreso = formatFechaIngreso;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getCodigoLaboratorio() {
        return codigoLaboratorio;
    }

    public void setCodigoLaboratorio(String codigoLaboratorio) {
        this.codigoLaboratorio = codigoLaboratorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public BigDecimal getLoq() {
        return loq;
    }

    public void setLoq(BigDecimal loq) {
        this.loq = loq;
    }

    public Integer getTat() {
        return tat;
    }

    public void setTat(Integer tat) {
        this.tat = tat;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public ObjectId getMatriz() {
        return matriz;
    }

    public void setMatriz(ObjectId matriz) {
        this.matriz = matriz;
    }

    public ObjectId getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(ObjectId unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public ObjectId getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(ObjectId laboratorio) {
        this.laboratorio = laboratorio;
    }

    public ObjectId getUsuario() {
        return usuario;
    }

    public void setUsuario(ObjectId usuario) {
        this.usuario = usuario;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getLeyendaMatriz() {
        return leyendaMatriz;
    }

    public void setLeyendaMatriz(String leyendaMatriz) {
        this.leyendaMatriz = leyendaMatriz;
    }

    public String getLeyendaUnidadMedida() {
        return leyendaUnidadMedida;
    }

    public void setLeyendaUnidadMedida(String leyendaUnidadMedida) {
        this.leyendaUnidadMedida = leyendaUnidadMedida;
    }

    public String getLeyendaLaboratorio() {
        return leyendaLaboratorio;
    }

    public void setLeyendaLaboratorio(String leyendaLaboratorio) {
        this.leyendaLaboratorio = leyendaLaboratorio;
    }

    public String getLeyendaUsuario() {
        return leyendaUsuario;
    }

    public void setLeyendaUsuario(String leyendaUsuario) {
        this.leyendaUsuario = leyendaUsuario;
    }

    public ObjectId save() {

        MongoManager mongo = MongoManager.getInstance();
        MongoCollection table = mongo.db.getCollection("subanalisis");
        Document obj = new Document("codigo", this.codigo).append("loq", BDecimalToStr(this.loq))
                .append("tat", this.tat).append("costo", BDecimalToStr(this.costo))
                .append("codigoLaboratorio", this.codigoLaboratorio).append("nombre", this.nombre.toUpperCase())
                .append("simbolo", this.simbolo).append("metodo", this.metodo.toUpperCase())
                .append("origen", this.origen.toUpperCase()).append("nota", this.nota)
                .append("matriz", this.matriz).append("unidadMedida", this.unidadMedida)
                .append("laboratorio", this.laboratorio).append("usuario", this.usuario)
                .append("fechaIngreso", this.fechaIngreso).append("darBaja", this.darBaja);
        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("codigo", this.codigo).append("loq", BDecimalToStr(this.loq))
                .append("tat", this.tat).append("costo", BDecimalToStr(this.costo))
                .append("codigoLaboratorio", this.codigoLaboratorio).append("nombre", this.nombre.toUpperCase())
                .append("simbolo", this.simbolo).append("metodo", this.metodo.toUpperCase())
                .append("origen", this.origen.toUpperCase()).append("nota", this.nota)
                .append("matriz", this.matriz).append("unidadMedida", this.unidadMedida)
                .append("laboratorio", this.laboratorio).append("usuario", this.usuario)
                .append("fechaIngreso", this.fechaIngreso).append("darBaja", this.darBaja));

        mongo.db.getCollection("subanalisis").updateOne(new Document("_id", this.id), obj);
    }

    public static Subanalisis getById(ObjectId id) {
        Subanalisis obj = new Subanalisis();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

            }

        });

        return obj;
    }

    public static Subanalisis getByName(String name) {
        Subanalisis obj = new Subanalisis();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("nombre", name));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();
            }

        });

        return obj;
    }

    public static List<Subanalisis> getAll() {
        List<Subanalisis> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find().sort(new Document("_id", -1));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Subanalisis obj = new Subanalisis();

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Subanalisis> getAllActivos() {
        List<Subanalisis> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("darBaja", 0)).sort(new Document("_id", -1));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Subanalisis obj = new Subanalisis();

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

                res.add(obj);
            }

        });

        return res;
    }
    
    public static List<Subanalisis> getAllActivosByMatriz(ObjectId matr) {
        List<Subanalisis> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("darBaja", 0).append("matriz", matr)).sort(new Document("_id", -1));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Subanalisis obj = new Subanalisis();

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

                res.add(obj);
            }

        });

        return res;
    }
    
    
    public static List<Subanalisis> getAllActivosByLaboratorioAndMatriz(ObjectId lab, ObjectId matr) {
        List<Subanalisis> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("darBaja", 0).append("laboratorio", lab).append("matriz", matr)).sort(new Document("nombre", 1));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Subanalisis obj = new Subanalisis();

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.get("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static List<Subanalisis> getAllDadosBaja() {
        List<Subanalisis> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find(new Document("darBaja", 1)).sort(new Document("_id", -1));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                Subanalisis obj = new Subanalisis();

                obj.id = (ObjectId) document.get("_id");
                obj.codigo = document.getInteger("codigo");
                obj.loq = obj.StrToBDecimal(document.get("loq").toString());
                obj.tat = document.getInteger("tat");
                obj.costo = obj.StrToBDecimal(document.getInteger("costo").toString());
                obj.codigoLaboratorio = document.get("codigoLaboratorio").toString();
                obj.nombre = document.get("nombre").toString();
                obj.simbolo = document.get("simbolo").toString();
                obj.metodo = document.get("metodo").toString();
                obj.origen = document.get("origen").toString();
                obj.nota = document.get("nota").toString();
                obj.matriz = (ObjectId) document.get("matriz");
                obj.unidadMedida = (ObjectId) document.get("unidadMedida");
                obj.laboratorio = (ObjectId) document.get("laboratorio");
                obj.usuario = (ObjectId) document.get("usuario");
                obj.fechaIngreso = (Date) document.get("fechaIngreso");
                obj.darBaja = document.getInteger("darBaja");

                if (obj.fechaIngreso != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.formatFechaIngreso = formateadorRec.format(obj.fechaIngreso);
                } else {
                    obj.formatFechaIngreso = "---";
                }

                obj.leyendaMatriz = Matriz.getById(obj.getMatriz()).getNombre();
                obj.leyendaUnidadMedida = UnidadMedida.getById(obj.getUnidadMedida()).getNombre();
                obj.leyendaLaboratorio = Laboratorio.getLaboratorioById(obj.getLaboratorio()).getNombre() + " [" + Laboratorio.getLaboratorioById(obj.getLaboratorio()).getPais() + "]";
                obj.leyendaUsuario = Usuario.getUsuarioById(obj.getUsuario()).getNombre();

                res.add(obj);
            }

        });

        return res;
    }

    public static Integer getMaxNumeroSecuencialCodigo() {

        Integer res = 0;
        Subanalisis obj = new Subanalisis();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("subanalisis").find().sort(new Document("codigo", -1)).limit(1);

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.codigo = document.getInteger("codigo");

            }

        });

        if (obj.codigo != null) {
            res = obj.codigo;
        }

        return res;
    }

    String BDecimalToStr(BigDecimal arg) {
        String res;
        res = arg.toString();
        return res;
    }

    BigDecimal StrToBDecimal(String arg) {
        BigDecimal res;
        res = new BigDecimal(arg);
        return res;
    }

}
