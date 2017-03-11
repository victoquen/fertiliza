/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.fertilizacion;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import db.MongoManager;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import static java.util.Arrays.asList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author pablo
 */
public class FuenteCompuestoAux implements Serializable {

    ObjectId id;

    ObjectId fuente;
    String fuenteSimbolo;
    BigDecimal humedadF;
    BigDecimal presentacionKgF;
    BigDecimal precioDoleSacoF;
    String unidadesPresentacion;
    BigDecimal totalF;
    List<CompuestoIngredienteAux> compuestos;
    
    BigDecimal aminoacidos;
    BigDecimal acidosHumicos;
    BigDecimal acidosfulvicos;
    BigDecimal auxinas;
    BigDecimal giberilinas;
    BigDecimal citocininas;
    BigDecimal materiaorganica;
    

    public FuenteCompuestoAux() {
        fuenteSimbolo = "";
        humedadF = new BigDecimal(0);
        presentacionKgF = new BigDecimal(0);
        precioDoleSacoF = new BigDecimal(0);
        unidadesPresentacion = "";
        totalF = new BigDecimal(0);
        compuestos = new ArrayList<>();
        aminoacidos = new BigDecimal(0);
        acidosHumicos = new BigDecimal(0);
        acidosfulvicos = new BigDecimal(0);
        auxinas = new BigDecimal(0);
        giberilinas = new BigDecimal(0);
        citocininas = new BigDecimal(0);
        materiaorganica = new BigDecimal(0);
        
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getFuenteSimbolo() {
        return fuenteSimbolo;
    }

    public void setFuenteSimbolo(String fuenteSimbolo) {
        this.fuenteSimbolo = fuenteSimbolo;
    }

    public ObjectId getFuente() {
        return fuente;
    }

    public void setFuente(ObjectId fuente) {
        this.fuente = fuente;
    }

    public BigDecimal getHumedadF() {
        return humedadF;
    }

    public void setHumedadF(BigDecimal humedadF) {
        this.humedadF = humedadF;
    }

    public BigDecimal getPresentacionKgF() {
        return presentacionKgF;
    }

    public void setPresentacionKgF(BigDecimal presentacionKgF) {
        this.presentacionKgF = presentacionKgF;
    }

    public BigDecimal getPrecioDoleSacoF() {
        return precioDoleSacoF;
    }

    public void setPrecioDoleSacoF(BigDecimal precioDoleSacoF) {
        this.precioDoleSacoF = precioDoleSacoF;
    }

    public List<CompuestoIngredienteAux> getCompuestos() {
        return compuestos;
    }

    public void setCompuestos(List<CompuestoIngredienteAux> compuestos) {
        this.compuestos = compuestos;
    }

    public BigDecimal getTotalF() {
        return totalF;
    }

    public void setTotalF(BigDecimal totalF) {
        this.totalF = totalF;
    }

    public BigDecimal getAminoacidos() {
        return aminoacidos;
    }

    public void setAminoacidos(BigDecimal aminoacidos) {
        this.aminoacidos = aminoacidos;
    }

    public BigDecimal getAcidosHumicos() {
        return acidosHumicos;
    }

    public void setAcidosHumicos(BigDecimal acidosHumicos) {
        this.acidosHumicos = acidosHumicos;
    }

    public BigDecimal getAcidosfulvicos() {
        return acidosfulvicos;
    }

    public void setAcidosfulvicos(BigDecimal acidosfulvicos) {
        this.acidosfulvicos = acidosfulvicos;
    }

    public BigDecimal getAuxinas() {
        return auxinas;
    }

    public void setAuxinas(BigDecimal auxinas) {
        this.auxinas = auxinas;
    }

    public BigDecimal getGiberilinas() {
        return giberilinas;
    }

    public void setGiberilinas(BigDecimal giberilinas) {
        this.giberilinas = giberilinas;
    }

    public BigDecimal getCitocininas() {
        return citocininas;
    }

    public void setCitocininas(BigDecimal citocininas) {
        this.citocininas = citocininas;
    }

    public BigDecimal getMateriaorganica() {
        return materiaorganica;
    }

    public void setMateriaorganica(BigDecimal materiaorganica) {
        this.materiaorganica = materiaorganica;
    }

    public String getUnidadesPresentacion() {
        return unidadesPresentacion;
    }

    public void setUnidadesPresentacion(String unidadesPresentacion) {
        this.unidadesPresentacion = unidadesPresentacion;
    }

    
    
    public void save() {
        List<Document> listComp = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();

        MongoCollection table = mongo.db.getCollection("fuentecompuesto");

        Document obj = new Document("fuente", this.fuente).append("fuentesimbolo", this.fuenteSimbolo)
                .append("humedadf", this.humedadF.toString()).append("presentacionkgf", this.presentacionKgF.toString())
                .append("preciodolesacof", this.precioDoleSacoF.toString()).append("totalf", this.totalF.toString())
                .append("unidadespresentacion", this.unidadesPresentacion).append("aminoacidos", this.aminoacidos.toString())
                .append("acidoshumicos", this.acidosHumicos.toString()).append("acidosfulvicos", this.acidosfulvicos.toString())
                .append("auxinas", this.auxinas.toString()).append("giberilinas", this.giberilinas.toString())
                .append("citocininas", this.citocininas.toString()).append("materiaorganica", this.materiaorganica.toString());

        for (int i = 0; i < compuestos.size(); i++) {
            Document a = new Document().append("idcompuesto", compuestos.get(i).idCompuesto).append("simbolocompuesto", compuestos.get(i).simboloCompuesto).append("ingredienteactivo", compuestos.get(i).ingredienteActivo.toString());
            listComp.add(a);
        }

        
        table.insertOne(obj.append("compuesto", asList(listComp)));

    }

    public void update() {
        List<Document> listComp = new ArrayList<>();
        
        for (int i = 0; i < compuestos.size(); i++) {
            Document a = new Document().append("idcompuesto", compuestos.get(i).idCompuesto).append("simbolocompuesto", compuestos.get(i).simboloCompuesto).append("ingredienteactivo", compuestos.get(i).ingredienteActivo.toString());
            listComp.add(a);
        }

        //Fuente before = getFuenteById(this.id);
        MongoManager mongo = MongoManager.getInstance();
        Document obj = new Document("$set", new Document("fuente", this.fuente).append("fuentesimbolo", this.fuenteSimbolo)
                .append("humedadf", this.humedadF.toString()).append("presentacionkgf", this.presentacionKgF.toString())
                .append("preciodolesacof", this.precioDoleSacoF.toString()).append("totalf", this.totalF.toString())
                .append("unidadespresentacion", this.unidadesPresentacion).append("aminoacidos", this.aminoacidos.toString())
                .append("acidoshumicos", this.acidosHumicos.toString()).append("acidosfulvicos", this.acidosfulvicos.toString())
                .append("auxinas", this.auxinas.toString()).append("giberilinas", this.giberilinas.toString())
                .append("citocininas", this.citocininas.toString()).append("materiaorganica", this.materiaorganica.toString()).append("compuesto", asList(listComp)) );

        

        mongo.db.getCollection("fuentecompuesto").updateOne(new Document("_id", this.id), obj);

    }

    public static FuenteCompuestoAux getFuenteCompuestoById(ObjectId id) {
        FuenteCompuestoAux obj = new FuenteCompuestoAux();

        MongoManager mongo = MongoManager.getInstance();

        FindIterable<Document> iterable = mongo.db.getCollection("fuentecompuesto").find(new Document("_id", id));

        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {

                obj.id = (ObjectId) document.get("_id");
                obj.fuente = (ObjectId) document.get("fuente");
                obj.fuenteSimbolo = document.get("fuentesimbolo").toString();
                obj.humedadF = obj.StrToBDecimal(document.get("humedadf").toString());
                obj.presentacionKgF = obj.StrToBDecimal(document.get("presentacionkgf").toString());
                obj.precioDoleSacoF = obj.StrToBDecimal(document.get("preciodolesacof").toString());
                obj.totalF = obj.StrToBDecimal(document.get("totalf").toString());
                
                obj.unidadesPresentacion = document.get("unidadespresentacion").toString();
                obj.aminoacidos = obj.StrToBDecimal(document.get("aminoacidos").toString());
                obj.acidosHumicos = obj.StrToBDecimal(document.get("acidoshumicos").toString());
                obj.acidosfulvicos = obj.StrToBDecimal(document.get("acidosfulvicos").toString());
                obj.auxinas = obj.StrToBDecimal(document.get("auxinas").toString());
                obj.giberilinas = obj.StrToBDecimal(document.get("giberilinas").toString());
                obj.citocininas = obj.StrToBDecimal(document.get("citocininas").toString());
                obj.materiaorganica = obj.StrToBDecimal(document.get("materiaorganica").toString());
                
                
                

                List<Document> comps = (List<Document>) document.get("compuesto");
                
                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);
                    for (int j = 0; j < res.size(); j++) {
                       
                        Document dbo = (Document) res.get(j);                        
                        CompuestoIngredienteAux aux = new CompuestoIngredienteAux();
                        aux.idCompuesto = (ObjectId) dbo.get("idcompuesto");
                        aux.simboloCompuesto = dbo.get("simbolocompuesto").toString();
                        aux.ingredienteActivo = obj.StrToBDecimal(dbo.get("ingredienteactivo").toString());

                        obj.compuestos.add(aux);
                    }
                }
            }

        });

        return obj;
    }

    public static List<FuenteCompuestoAux> getAllFuentesCompuestos() {
        List<FuenteCompuestoAux> res = new ArrayList<>();

        MongoManager mongo = MongoManager.getInstance();
        FindIterable<Document> iterable = mongo.db.getCollection("fuentecompuesto").find().sort(new Document("_id", -1));
        iterable.forEach(new Block<Document>() {
            @Override
            public void apply(final Document document) {
                FuenteCompuestoAux obj = new FuenteCompuestoAux();

                obj.id = (ObjectId) document.get("_id");
                obj.fuente = (ObjectId) document.get("fuente");
                obj.fuenteSimbolo = document.get("fuentesimbolo").toString();
                obj.humedadF = obj.StrToBDecimal(document.get("humedadf").toString());
                obj.presentacionKgF = obj.StrToBDecimal(document.get("presentacionkgf").toString());
                obj.precioDoleSacoF = obj.StrToBDecimal(document.get("preciodolesacof").toString());
                obj.totalF = obj.StrToBDecimal(document.get("totalf").toString());
                
                obj.unidadesPresentacion = document.get("unidadespresentacion").toString();
                obj.aminoacidos = obj.StrToBDecimal(document.get("aminoacidos").toString());
                obj.acidosHumicos = obj.StrToBDecimal(document.get("acidoshumicos").toString());
                obj.acidosfulvicos = obj.StrToBDecimal(document.get("acidosfulvicos").toString());
                obj.auxinas = obj.StrToBDecimal(document.get("auxinas").toString());
                obj.giberilinas = obj.StrToBDecimal(document.get("giberilinas").toString());
                obj.citocininas = obj.StrToBDecimal(document.get("citocininas").toString());
                obj.materiaorganica = obj.StrToBDecimal(document.get("materiaorganica").toString());

                List<Document> comps = (List<Document>) document.get("compuesto");

                //for (Iterator< Object> it = comps.iterator(); it.hasNext();) {
                for (int i = 0; i < comps.size(); i++) {

                    List<Document> res = (List<Document>) comps.get(i);

                    for (int j = 0; j < res.size(); j++) {
                        //BasicDBObject dbo     = ( BasicDBObject ) it.next();
                        Document dbo = (Document) res.get(j);
                        //Document dbo = (Document) it.next(); 

                        CompuestoIngredienteAux aux = new CompuestoIngredienteAux();
                        aux.idCompuesto = (ObjectId) dbo.get("idcompuesto");
                        aux.simboloCompuesto = dbo.get("simbolocompuesto").toString();
                        aux.ingredienteActivo = obj.StrToBDecimal(dbo.get("ingredienteactivo").toString());

                        aux.calculoCamposComplementarios(obj.totalF, obj.precioDoleSacoF, obj.presentacionKgF);
                        
                        obj.compuestos.add(aux);
                    }
                }
                res.add(obj);
            }

        });

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
