package edu.eci.arep.sparkwebapp.Persistence;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import edu.eci.arep.sparkwebapp.Message;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;

/**
 * The type Db connection.
 * @author Johan Sebastian Arias
 */
public class DBConnection {

    /**
     * The Mongo client.
     */
    public MongoClient mongoClient;
    /**
     * The Uri.
     */
    public MongoClientURI uri;

    /**
     * Instantiates a new Db connection.
     */
    public DBConnection() {

        //NOTE: Si quieres correr localmente este programa es necesario cambiar la direccion de abajo por "localhost"
        uri = new MongoClientURI(
                "mongodb://chan:chan123@arep-mongo:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=Arep" +
                        "&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    /**
     * Retrieve data array list.
     *
     * @return the array list
     */
    public ArrayList<Message> retrieveData() {

        MongoDatabase db = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection = db.getCollection("Test");
        FindIterable fit = collection.find();
        ArrayList<Message> rta = new ArrayList<>();
        ArrayList<Document> docs = new ArrayList<Document>();
        fit.into(docs);

        for (Document document : docs) {
            String message = document.get("mensaje").toString();
            String fecha = document.get("fecha").toString();
            rta.add(new Message(message,fecha));
        }
        ArrayList<Message> tenRegisterList = new ArrayList<>();
        for (int i = rta.size()-1 ;  i > rta.size() -11 && i > -1  ; i-- ){
            tenRegisterList.add(rta.get(i));

        }
        return tenRegisterList;
    }

    public void AddMessage(Message message) {
        MongoDatabase db = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection = db.getCollection("Test");
        Document document = new Document();
        Date fecha = new Date();
        String date = fecha.toString();
        document.put("mensaje", message.getMensaje());
        document.put("fecha", date);
        collection.insertOne(document);
    }
}
