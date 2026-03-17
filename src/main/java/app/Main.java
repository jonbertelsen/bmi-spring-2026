package app;

import app.config.SessionConfig;
import app.config.ThymeleafConfig;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
    public static void main(String[] args) {
        // Initializing Javalin and Jetty webserver

        Javalin app = Javalin.create(config -> {
                    config.staticFiles.add("/public");
                    config.jetty.modifyServletContextHandler(handler -> handler.setSessionHandler(SessionConfig.sessionConfig()));
                    config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
                })
                .start(7070);

        // Routing

        app.get("/", ctx -> ctx.render("index.html"));
        app.post("/result", ctx -> renderBMI(ctx));
    }

    private static void renderBMI(Context ctx) {
        try {
            String name = ctx.formParam("name");
            validateName(name);

            int weight = Integer.parseInt(ctx.formParam("weight"));
            int height = Integer.parseInt(ctx.formParam("height"));
            double bmi = getBMI(weight, height);
            ctx.attribute("bmi", bmi);
            ctx.attribute("name", name);
            ctx.attribute("verdict", getBMIVerdict(bmi));
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

private static void validateName(String name) {
    if (name == null || name.length() < 2) {
        throw new IllegalArgumentException("Navn skal have mindst to tegn");
    }
}

public static String getBMIVerdict(double bmi) {
    if (bmi <= 18.5)
        return "Undervægtig";
    if (bmi > 18.5 && bmi <= 24.9)
        return "Normalvægtig";
    if (bmi > 24.9 && bmi <= 29.9)
        return "Overvægt";
    return "Fedme";
}

public static double getBMI(int weight, int height){
        return weight / Math.pow(height / 100.0, 2);
}

}