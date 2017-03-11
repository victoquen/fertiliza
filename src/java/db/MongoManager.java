/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import java.io.Serializable;

/**
 *
 * @author pablo
 */
public class MongoManager implements Serializable{
    
    
    private static MongoManager mongoManager;
    
    MongoClient mongoClient;
    public MongoDatabase db;
    public boolean couldConnect;

    private MongoManager() {
        mongoClient = new MongoClient("localhost", 27017);
        db =  mongoClient.getDatabase("agrorum");
        couldConnect = true;
        
     
    }
    
    public static MongoManager getInstance(){
        if(mongoManager==null){
            mongoManager = new MongoManager();
        }
        
        return mongoManager;
    }
    
    public static void closeConnection(MongoClient m){
        m.close();
    }
}
