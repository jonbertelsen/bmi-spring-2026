package app.validators;

public class RequestValidators {

    public void validateName(String name) {
        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("Navn skal have mindst to tegn");
        }
    }
}
