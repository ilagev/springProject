package rest.api.exceptions;

public class DivisionByZero extends Exception {

    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Error de Authorization en cabecera por formato erroneo, debe estar en Auth Basic";

    public static final int CODE = 002;

    public DivisionByZero() {
        this("");
    }

    public DivisionByZero(String detail) {
        super(DESCRIPTION + ". " + detail + ". CODE: " + CODE);
    }

}
