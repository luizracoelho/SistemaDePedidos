package sistemadepedidos.exceptions;

/**
 * Exceção que checa se um preço é Zerado ou Negativo
 * @author LuizRicardo
 */
public class PrecoZeradoOuNegativoException extends RuntimeException {

    public PrecoZeradoOuNegativoException(String message) {
        super(message);
    }
}
