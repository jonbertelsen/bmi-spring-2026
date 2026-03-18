package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import app.controllers.BmiController;
import app.persistence.BmiMapper;
import app.persistence.ConnectionPool;
import app.routes.Routes;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.logging.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";
    private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
    private static final String DB = "bmi";

    private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

    public static void main(String[] args) {

        BmiMapper bmiMapper = new BmiMapper(connectionPool);
        BmiController bmiController = new BmiController(bmiMapper);
        Routes routes = new Routes(bmiController);

        Javalin app = Javalin.create(config -> {
                    config.staticFiles.add("/public");
                    config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(SessionConfig.sessionConfig()));
                    config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
                    config.router.apiBuilder(routes.getRoutes());
                })
                .start(7070);
    }

}