package app.controllers;

import app.entities.Bmi;
import app.persistence.BmiMapper;
import app.persistence.ConnectionPool;
import app.services.BmiServices;
import app.validators.RequestValidation;
import io.javalin.http.Context;
import org.eclipse.jetty.webapp.OverrideDescriptor;

import java.sql.SQLException;
import java.time.LocalDateTime;

public class BmiController {

    private final BmiMapper bmiMapper;

    public BmiController(BmiMapper bmiMapper) {
        this.bmiMapper = bmiMapper;
    }

    public void renderBMI(Context ctx) {
        try {
            String name = ctx.formParam("name");
            RequestValidation.validateName(name);

            int weight = Integer.parseInt(ctx.formParam("weight"));
            int height = Integer.parseInt(ctx.formParam("height"));
            double bmi = BmiServices.getBMI(weight, height);
            String verdict = BmiServices.getBMIVerdict(bmi);
            LocalDateTime created = LocalDateTime.now();

            Bmi bmiEntity = new Bmi(height, weight, bmi, name, verdict, created);

            bmiMapper.createBmi(bmiEntity);

            ctx.attribute("bmiEntity", bmiEntity );
            ctx.attribute("error", "");
            ctx.render("result.html");
        } catch (NumberFormatException e) {
            ctx.attribute("error", "Husk at indtaste korrekte tal for vægt og højde");
            ctx.render("index.html");
        } catch(IllegalArgumentException e){
            ctx.attribute("error", e.getMessage());
            ctx.render("index.html");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
