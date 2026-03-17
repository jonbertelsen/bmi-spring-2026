package app.controllers;

import app.services.BMIServices;
import app.validators.RequestValidators;
import io.javalin.http.Context;

public class BMIController {

    private final BMIServices bmiServices;
    private final RequestValidators requestValidators;

    public BMIController(BMIServices bmiServices, RequestValidators requestValidators) {
        this.bmiServices = bmiServices;
        this.requestValidators = requestValidators;
    }

    public void frontPage(Context ctx){
        ctx.render("index.html");
    }

    public void result(Context ctx){
        try {
            String name = ctx.formParam("name");
            requestValidators.validateName(name);
            int weight = Integer.parseInt(ctx.formParam("weight"));
            int height = Integer.parseInt(ctx.formParam("height"));
            double bmi = bmiServices.getBMI(weight, height);
            ctx.attribute("bmi", bmi);
            ctx.attribute("name", name);
            ctx.attribute("verdict", bmiServices.getBMIVerdict(bmi));
            ctx.attribute("error", "");
            ctx.render("result.html");
        } catch (NumberFormatException e) {
            ctx.attribute("error", "Husk at indtaste korrekte tal for vægt og højde");
            ctx.render("index.html");
        } catch(IllegalArgumentException e){
            ctx.attribute("error", e.getMessage());
            ctx.render("index.html");
        }
    }

}
