package sistemadepedidos.exceptions;

/**
 * Exceção que informa que o Cliente não foi preenchido
 * @author LuizRicardo
 */
public class ClienteNaoInformadoException extends RuntimeException {

    public ClienteNaoInformadoException(String message) {
        super(message);
    }
    
}
