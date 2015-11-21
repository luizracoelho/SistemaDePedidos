package sistemadepedidos.exceptions;

/**
 * Exceção que verifica a obrigatoriedade de campos
 * @author LuizRicardo
 */
public class ClienteNaoInformadoException extends RuntimeException {

    public ClienteNaoInformadoException(String message) {
        super(message);
    }
    
}
