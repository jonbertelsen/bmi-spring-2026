package app.routes;

import app.controllers.BMIController;
import io.javalin.apibuilder.EndpointGroup;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Routes {

    private final BMIController bmiController;

    public Routes(BMIController bmiController) {
        this.bmiController = bmiController;
    }

    public EndpointGroup getRoutes() {
        return () -> {
            get("/", bmiController::frontPage);
            post("/result", bmiController::result);
        };
    }

    }
