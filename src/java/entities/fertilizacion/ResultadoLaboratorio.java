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
 * @author pablo
 */
public class ResultadoLaboratorio implements Serializable {

    ObjectId id;

    ObjectId muestra;
    ObjectId cultivo;

    Date fechaEnvio;
    Date fechaRecepcion;
    Date fechaResultado;
    List<ResultadoLaboratorioAnalisisCompuesto> listaResultado;
    List<ResultadoLaboratorioAnalisisCompuesto> listaResultadoPaquetes;

    String fechaFormatRecepcion;
    String fechaFormatEnvio;
    String fechaFormatResultado;

    String leyendaMuestra;
    String leyendaCultivo;
    String leyendaMatriz;
    //String leyendaCompuestoQuimico;

    Integer controlCompleto;

    public ResultadoLaboratorio() {

        this.leyendaCultivo = "";
        this.leyendaMuestra = "";

        this.listaResultado = new ArrayList<>();
        this.listaResultadoPaquetes = new ArrayList<>();

        this.controlCompleto = 1;
    }

    public List<ResultadoLaboratorioAnalisisCompuesto> getListaResultadoPaquetes() {
        return listaResultadoPaquetes;
    }

    public void setListaResultadoPaquetes(List<ResultadoLaboratorioAnalisisCompuesto> listaResultadoPaquetes) {
        this.listaResultadoPaquetes = listaResultadoPaquetes;
    }

    public String getFechaFormatRecepcion() {
        return fechaFormatRecepcion;
    }

    public void setFechaFormatRecepcion(String fechaFormatRecepcion) {
        this.fechaFormatRecepcion = fechaFormatRecepcion;
    }

    public String getFechaFormatEnvio() {
        return fechaFormatEnvio;
    }

    public void setFechaFormatEnvio(String fechaFormatEnvio) {
        this.fechaFormatEnvio = fechaFormatEnvio;
    }

    public String getFechaFormatResultado() {
        return fechaFormatResultado;
    }

    public String getLeyendaMatriz() {
        return leyendaMatriz;
    }

    public void setLeyendaMatriz(String leyendaMatriz) {
        this.leyendaMatriz = leyendaMatriz;
    }

    public void setFechaFormatResultado(String fechaFormatResultado) {
        this.fechaFormatResultado = fechaFormatResultado;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getMuestra() {
        return muestra;
    }

    public void setMuestra(ObjectId muestra) {
        this.muestra = muestra;
    }

    public ObjectId getCultivo() {
        return cultivo;
    }

    public void setCultivo(ObjectId cultivo) {
        this.cultivo = cultivo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getLeyendaMuestra() {
        return leyendaMuestra;
    }

    public void setLeyendaMuestra(String leyendaMuestra) {
        this.leyendaMuestra = leyendaMuestra;
    }

    public String getLeyendaCultivo() {
        return leyendaCultivo;
    }

    public void setLeyendaCultivo(String leyendaCultivo) {
        this.leyendaCultivo = leyendaCultivo;
    }

    public Date getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(Date fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public List<ResultadoLaboratorioAnalisisCompuesto> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<ResultadoLaboratorioAnalisisCompuesto> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public ObjectId save() {
        List<Document> listObj = new ArrayList<>();
        List<Document> listObjPaquete = new ArrayList<>();

        for (int i = 0; i < listaResultado.size(); i++) {
            Document a = new Document().append("subanalisis", listaResultado.get(i).subanalisis).append("resultado", BDecimalToStr(listaResultado.get(i).resultado))
                    .append("unidad", listaResultado.get(i).unidad).append("dadobaja", listaResultado.get(i).dadoBaja);
            listObj.add(a);
        }

        for (int i = 0; i < listaResultadoPaquetes.size(); i++) {
            Document a = new Document().append("subanalisis", listaResultadoPaquetes.get(i).subanalisis).append("resultado", BDecimalToStr(listaResultadoPaquetes.get(i).resultado))
                    .append("unidad", listaResultadoPaquetes.get(i).unidad).append("dadobaja", listaResultadoPaquetes.get(i).dadoBaja);
            listObjPaquete.add(a);
        }

        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("resultadolaboratorio");

        Document obj = new Document("muestra", this.muestra).append("cultivo", this.cultivo)
                .append("fecharecepcion", this.fechaRecepcion)
                .append("fechaenvio", this.fechaEnvio).append("fecharesultado", this.fechaResultado).append("resultado", listObj).append("resultadoPaquete", listObjPaquete);

        table.insertOne(obj);

        return (ObjectId) obj.get("_id");
    }

    public void update() {

        List<Document> listObj = new ArrayList<>();
        List<Document> listObjPaquete = new ArrayList<>();

        for (int i = 0; i < listaResultado.size(); i++) {
            Document a = new Document().append("subanalisis", listaResultado.get(i).subanalisis).append("resultado", BDecimalToStr(listaResultado.get(i).resultado))
                    .append("unidad", listaResultado.get(i).unidad).append("dadobaja", listaResultado.get(i).dadoBaja);
            listObj.add(a);
        }

        for (int i = 0; i < listaResultadoPaquetes.size(); i++) {
            Document a = new Document().append("subanalisis", listaResultadoPaquetes.get(i).subanalisis).append("resultado", BDecimalToStr(listaResultadoPaquetes.get(i).resultado))
                    .append("unidad", listaResultadoPaquetes.get(i).unidad).append("dadobaja", listaResultadoPaquetes.get(i).dadoBaja);
            listObjPaquete.add(a);
        }

        MongoManager mongo = MongoManager.getInstance();
        mongo.db.getCollection("resultadolaboratorio").updateOne(new Document("_id", this.id), new Document("$set", new Document("muestra", this.muestra)
                .append("cultivo", this.cultivo)
                .append("fecharecepcion", this.fechaRecepcion)
                .append("fechaenvio", this.fechaEnvio).append("fecharesultado", this.fechaResultado)
                .append("resultado", listObj).append("resultadoPaquete", listObjPaquete)));

    }

    public static ResultadoLaboratorio getResultadoLaboratorioById(ObjectId id) {
        ResultadoLaboratorio obj = new ResultadoLaboratorio();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("resultadolaboratorio").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.muestra = (ObjectId) document.get("muestra");
                obj.cultivo = (ObjectId) document.get("cultivo");

                obj.fechaRecepcion = (Date) document.get("fecharecepcion");
                obj.fechaEnvio = (Date) document.get("fechaenvio");
                obj.fechaResultado = (Date) document.get("fecharesultado");

                if (obj.fechaRecepcion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatRecepcion = formateadorRec.format(obj.fechaRecepcion);
                } else {
                    obj.fechaFormatRecepcion = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                if (obj.fechaResultado != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatResultado = formateadorRec.format(obj.fechaResultado);
                } else {
                    obj.fechaFormatResultado = "---";
                }

                List<Document> comps = (List<Document>) document.get("resultado");
                for (int i = 0; i < comps.size(); i++) {

                    Document dbo = (Document) comps.get(i);
                    ResultadoLaboratorioAnalisisCompuesto aux = new ResultadoLaboratorioAnalisisCompuesto();
                    aux.subanalisis = (ObjectId) dbo.get("subanalisis");
                    aux.resultado = obj.StrToBDecimal(dbo.get("resultado").toString());
                    aux.unidad = dbo.get("unidad").toString();
                    aux.dadoBaja = dbo.get("dadobaja").toString();

                    aux.leyendaSubanalisis = Subanalisis.getById(aux.subanalisis).simbolo + "(" + Subanalisis.getById(aux.subanalisis).nombre + ")";
                    obj.listaResultado.add(aux);
                }

                List<Document> paquetes = (List<Document>) document.get("resultadoPaquete");
                for (int i = 0; i < paquetes.size(); i++) {

                    Document dbo = (Document) paquetes.get(i);
                    ResultadoLaboratorioAnalisisCompuesto aux = new ResultadoLaboratorioAnalisisCompuesto();
                    aux.subanalisis = (ObjectId) dbo.get("subanalisis");
                    aux.resultado = obj.StrToBDecimal(dbo.get("resultado").toString());
                    aux.unidad = dbo.get("unidad").toString();
                    aux.dadoBaja = dbo.get("dadobaja").toString();

                    aux.leyendaSubanalisis = Subanalisis.getById(aux.subanalisis).simbolo + "(" + Subanalisis.getById(aux.subanalisis).nombre + ")";
                    obj.listaResultadoPaquetes.add(aux);
                }

                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;

                obj.leyendaMuestra = MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).codigo
                        + "(" + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaCliente
                        + " - " + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaHacienda
                        + " - " + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaLote + ")";

                obj.leyendaMatriz = Matriz.getById(MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).getMatriz()).nombre;
            }

        });

        return obj;
    }

    public static List<ResultadoLaboratorio> getAllResultadoLaboratorio() {
        List<ResultadoLaboratorio> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("resultadolaboratorio").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                ResultadoLaboratorio obj = new ResultadoLaboratorio();
                obj.id = (ObjectId) document.get("_id");
                obj.muestra = (ObjectId) document.get("muestra");
                obj.cultivo = (ObjectId) document.get("cultivo");

                //DateFormat dfDateMedium = DateFormat.getDateInstance(DateFormat.MEDIUM);
                obj.fechaRecepcion = (Date) document.get("fecharecepcion");
                obj.fechaEnvio = (Date) document.get("fechaenvio");
                obj.fechaResultado = (Date) document.get("fecharesultado");

                if (obj.fechaRecepcion != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatRecepcion = formateadorRec.format(obj.fechaRecepcion);
                } else {
                    obj.fechaFormatRecepcion = "---";
                }

                if (obj.fechaEnvio != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatEnvio = formateadorRec.format(obj.fechaEnvio);
                } else {
                    obj.fechaFormatEnvio = "---";
                }

                if (obj.fechaResultado != null) {
                    SimpleDateFormat formateadorRec = new SimpleDateFormat("EEEE',' dd 'de' MMMM 'de' yyyy", new Locale("ES"));
                    obj.fechaFormatResultado = formateadorRec.format(obj.fechaResultado);
                } else {
                    obj.fechaFormatResultado = "---";
                }

                List<Document> comps = (List<Document>) document.get("resultado");
                for (int i = 0; i < comps.size(); i++) {

                    Document dbo = (Document) comps.get(i);
                    ResultadoLaboratorioAnalisisCompuesto aux = new ResultadoLaboratorioAnalisisCompuesto();
                    aux.subanalisis = (ObjectId) dbo.get("subanalisis");
                    aux.resultado = obj.StrToBDecimal(dbo.get("resultado").toString());
                    aux.unidad = dbo.get("unidad").toString();
                    aux.dadoBaja = dbo.get("dadobaja").toString();
                    
                    if(aux.dadoBaja.equals("No") && aux.resultado.equals(new BigDecimal(0))){
                        obj.controlCompleto=0;
                    }

                    aux.leyendaSubanalisis = Subanalisis.getById(aux.subanalisis).simbolo + "(" + Subanalisis.getById(aux.subanalisis).nombre + ")";
                    obj.listaResultado.add(aux);
                }

                List<Document> paquetes = (List<Document>) document.get("resultadoPaquete");
                for (int i = 0; i < paquetes.size(); i++) {

                    Document dbo = (Document) paquetes.get(i);
                    ResultadoLaboratorioAnalisisCompuesto aux = new ResultadoLaboratorioAnalisisCompuesto();
                    aux.subanalisis = (ObjectId) dbo.get("subanalisis");
                    aux.resultado = obj.StrToBDecimal(dbo.get("resultado").toString());
                    aux.unidad = dbo.get("unidad").toString();
                    aux.dadoBaja = dbo.get("dadobaja").toString();

                    aux.leyendaSubanalisis = Subanalisis.getById(aux.subanalisis).simbolo + "(" + Subanalisis.getById(aux.subanalisis).nombre + ")";
                    obj.listaResultadoPaquetes.add(aux);
                }

                obj.leyendaCultivo = Cultivo.getCultivoById(obj.cultivo).nombre;

                obj.leyendaMuestra = MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).codigo
                        + "(" + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaCliente
                        + " - " + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaHacienda
                        + " - " + MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).leyendaLote + ")";

                res.add(obj);

                obj.leyendaMatriz = Matriz.getById(MuestraLaboratorio.getMuestraLaboratorioById(obj.muestra).getMatriz()).nombre;
            }

        });

        return res;
    }

    public static List<ResultadoLaboratorio> getAllResultadoLaboratorioPendiente() {
        List<ResultadoLaboratorio> res = new ArrayList<>();

        List<ResultadoLaboratorio> listadoTotal = new ArrayList<>();
        listadoTotal = ResultadoLaboratorio.getAllResultadoLaboratorio();

        for (ResultadoLaboratorio obj : listadoTotal) {
            if(obj.controlCompleto==0){
                res.add(obj);
            }
        }

        return res;
    }
    
    public static List<ResultadoLaboratorio> getAllResultadoLaboratorioCompleto() {
        List<ResultadoLaboratorio> res = new ArrayList<>();

        List<ResultadoLaboratorio> listadoTotal = new ArrayList<>();
        listadoTotal = ResultadoLaboratorio.getAllResultadoLaboratorio();

        for (ResultadoLaboratorio obj : listadoTotal) {
            if(obj.controlCompleto==1){
                res.add(obj);
            }
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
