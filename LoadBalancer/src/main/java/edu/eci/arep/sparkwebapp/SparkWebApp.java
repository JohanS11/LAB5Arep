package edu.eci.arep.sparkwebapp;
import spark.Request;
import spark.Response;


import java.io.IOException;

import static spark.Spark.*;

/**
 * This is a simple WebApplication deployed in Heroku using SparkWeb.
 * @author Johan Arias
 */

public class SparkWebApp {

    private static HTTPClient httpClient = new HTTPClient();

    public static void main(String[] args) {
        port(getPort());
        staticFileLocation("/static");
        get("/", SparkWebApp::buildHomePage);
        get("/GetMessages", SparkWebApp::getBuild);
        post("/GetMessages", SparkWebApp::postBuild);
    }

    private static Object postBuild(Request request, Response response) throws IOException {

        httpClient.postMessage(request.body(),"/GetMessages");
        httpClient.roundRobin();
        return  "";
    }

    /**
     * @param req This is the object that represents the HTTP request
     *            in order to retrieve a resource from the web server.
     * @param res This is the object that represents the HTTP response
     *            given by the webserver
     * @return A string that will build the web page, in this case
     *          the resource located at /inputdata
     */
    private static String getBuild(Request req, Response res) throws IOException {
      res.status(200);
      res.type("application/json");
      String response = httpClient.getMessages("/GetMessages");
      httpClient.roundRobin();
      return response;
    }

    private static String buildHomePage(Request request, Response response) {
        response.redirect("index.html");
        return "";
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
