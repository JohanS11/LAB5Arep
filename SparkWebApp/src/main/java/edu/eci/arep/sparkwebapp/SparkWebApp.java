package edu.eci.arep.sparkwebapp;
import com.google.gson.Gson;
import edu.eci.arep.sparkwebapp.Persistence.*;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

/**
 * This is a simple WebApplication deployed in Heroku using SparkWeb.
 * @author Johan Arias
 */

public class SparkWebApp {

    private static DBConnection db = new DBConnection();

    public static void main(String[] args) {
        port(getPort());
        get("/GetMessages",(request, response) -> {
            response.status(200);
            response.type("application/json");
            try{
                System.out.println("DATA SIN FORMAR JSON" + db.retrieveData());
                System.out.println("DATA RETRIEVED " + JsonBuild.toJson(db.retrieveData()));
            }catch (Exception e){
                e.printStackTrace();
            }

            return JsonBuild.toJson(db.retrieveData());
        });
        post("/GetMessages",(req, response) -> {


            System.out.println("ADDING TO DATABASE");
            try{
                System.out.println("REQUEST BODY "+ req.body());
                if (JsonBuild.toMessage(req.body()) == null){
                    response.status(400);
                }
                db.AddMessage(JsonBuild.toMessage(req.body()));
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Failure at jsonBuild");
            }

            return "Successful post";
        });

    }

    /**
     * This method reads the default port as specified by the PORT variable in
     * the environment.
     *
     * Heroku provides the port automatically so you need this to run the
     * project on Heroku.
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

}
