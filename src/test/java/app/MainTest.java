package app;

import app.controllers.BMIController;
import app.routes.Routes;
import app.services.BMIServices;
import app.validators.RequestValidators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final RequestValidators requestValidators = new RequestValidators();
    private final BMIServices bmiServices = new BMIServices();
    private final BMIController bmiController = new BMIController(bmiServices, requestValidators);

    @BeforeAll
    static void initialize(){
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBMIVerdictNormal() {
        // Arrange
        double bmi = 22.0;
        String expectedBMI = "Normalvægtig";
        // Act
        String actualBMI = bmiServices.getBMIVerdict(bmi);
        // Assert
        assertEquals(expectedBMI, actualBMI);
    }

    @Test
    void getBMIVerdictSkinny() {
        // Arrange
        double bmi = 18.0;
        String expectedBMI = "Undervægtig";
        // Act
        String actualBMI = bmiServices.getBMIVerdict(bmi);
        // Assert
        assertEquals(expectedBMI, actualBMI);
    }

    @Test
    void getBMI() {
        double bmi = bmiServices.getBMI(72, 184);
    }
}