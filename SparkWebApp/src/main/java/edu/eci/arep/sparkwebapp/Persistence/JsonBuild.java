package edu.eci.arep.sparkwebapp.Persistence;

import com.google.gson.Gson;
import edu.eci.arep.sparkwebapp.Message;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Json build.
 * @author Johan Sebastian Arias
 */
public class JsonBuild {

    /**
     * Get json string.
     *
     * @return the string
     */
    public static String toJson(ArrayList<Message> messages) {
        Gson gson = new Gson();
        return gson.toJson(messages);
    }

    public static Message toMessage(String data){
        Gson g = new Gson();
        Message message = g.fromJson(data,Message.class);
        if (message.getMensaje() == null) {
            return null;
        }
        return message;
    }
}