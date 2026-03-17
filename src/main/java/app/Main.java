package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import app.controllers.BMIController;
import app.routes.Routes;
import app.services.BMIServices;
import app.validators.RequestValidators;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
    public static void main(String[] args) {

        RequestValidators requestValidators = new RequestValidators();
        BMIServices bmiServices = new BMIServices();
        BMIController bmiController = new BMIController(bmiServices, requestValidators);
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