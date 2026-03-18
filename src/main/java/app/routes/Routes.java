package app.routes;

import app.controllers.BmiController;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.post;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Routes {

    private BmiController bmiController;

    public Routes(BmiController bmiController){
        this.bmiController = bmiController;
    }

    public EndpointGroup getRoutes(){
        return () -> {
            get("/", ctx -> ctx.render("index.html"));
            post("/result", ctx -> bmiController.renderBMI(ctx));
        };
    }
}
