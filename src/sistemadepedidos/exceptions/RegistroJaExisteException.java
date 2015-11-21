package sistemadepedidos.exceptions;

/**
 * Exceção que informa que um registro não existe
 * @author LuizRicardo
 */
public class RegistroJaExisteException extends RuntimeException {

    public RegistroJaExisteException(String message) {
        super(message);
    }
    
}
