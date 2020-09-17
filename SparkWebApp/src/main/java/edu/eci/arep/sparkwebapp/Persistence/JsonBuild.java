package edu.eci.arep.sparkwebapp.Persistence;

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
     * @param data the data
     * @return the string
     */
    public static JSONObject toJson(ArrayList<String[]> data) {
        JSONObject jsObject = new JSONObject();
        for (String[] i : data) {
            System.out.println("DATAAAaaaaa " + i);
            jsObject.put("mensaje", i[0]);
            jsObject.put("fecha", i[1]);
            System.out.println("jeissson "+ jsObject.toString());
        }

        return jsObject;
    }
}