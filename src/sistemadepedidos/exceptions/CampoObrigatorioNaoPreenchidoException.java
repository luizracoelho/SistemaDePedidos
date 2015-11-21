package sistemadepedidos.exceptions;

/**
 * Exceção que verifica a obrigatoriedade de campos
 * @author LuizRicardo
 */
public class CampoObrigatorioNaoPreenchidoException extends RuntimeException {

    public CampoObrigatorioNaoPreenchidoException(String message) {
        super(message);
    }
    
}
