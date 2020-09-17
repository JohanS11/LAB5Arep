package edu.eci.arep.sparkwebapp.Persistence;

import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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
                "mongodb://chan:chan123@localhost:27017/?serverSelectionTimeoutMS=5000&connectTimeoutMS=10000&authSource=Arep" +
                        "&authMechanism=SCRAM-SHA-1&3t.uriVersion=3");
        mongoClient = new MongoClient(uri);
    }

    /**
     * Retrieve data array list.
     *
     * @return the array list
     */
    public ArrayList<String[]> retrieveData() {

        MongoDatabase db = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection = db.getCollection("Test");
        FindIterable fit = collection.find();
        ArrayList<String[]> rta = new ArrayList<>();
        ArrayList<Document> docs = new ArrayList<Document>();
        fit.into(docs);

        for (Document document : docs) {
            String name = document.get("mensaje").toString();
            String cuisine = document.get("fecha").toString();
            rta.add(new String[]{name, cuisine});

        }
        return rta;
    }

    public void AddMessage(String mensaje) {
        MongoDatabase db = mongoClient.getDatabase("Arep");
        MongoCollection<Document> collection = db.getCollection("Test");
        Document document = new Document();
        Date fecha = new Date();
        String date = fecha.toString();
        document.put("mensaje", mensaje);
        document.put("fecha", date);
        collection.insertOne(document);
    }
}
