package sistemadepedidos.exceptions;

/**
 * Exceção que informa a obrigatoriedade de campos
 * @author LuizRicardo
 */
public class CampoObrigatorioNaoPreenchidoException extends RuntimeException {

    public CampoObrigatorioNaoPreenchidoException(String message) {
        super(message);
    }
    
}
