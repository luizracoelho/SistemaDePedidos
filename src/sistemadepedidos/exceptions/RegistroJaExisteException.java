package sistemadepedidos.exceptions;

/**
 * Exceção que verifica a obrigatoriedade de campos
 * @author LuizRicardo
 */
public class RegistroJaExisteException extends RuntimeException {

    public RegistroJaExisteException(String message) {
        super(message);
    }
    
}
