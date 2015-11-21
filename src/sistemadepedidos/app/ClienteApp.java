package sistemadepedidos.app;

import java.util.List;
import sistemadepedidos.dominio.Cliente;
import sistemadepedidos.exceptions.CampoObrigatorioNaoPreenchidoException;
import sistemadepedidos.exceptions.RegistroJaExisteException;
import sistemadepedidos.repositorio.ClienteDao;

/**
 * Classe responsável pelas lógicas de negócio dos Clientes
 *
 * @author LuizRicardo
 */
public class ClienteApp {

    /**
     * Método que cria um Cliente
     *
     * @param cliente Cliente
     */
    public void Criar(Cliente cliente) {
        try {
            //Validar o cliente
            Validar(cliente);

            //Validar se o Nome já Existe
            if (RegistroJaExiste(cliente.getNome())) {
                throw new RegistroJaExisteException("Já existe um Cliente com este Nome.");
            }

            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Cria o cliente
            clienteDao.Criar(cliente);
        } catch (CampoObrigatorioNaoPreenchidoException conpe) {
            throw conpe;
        }
    }

    /**
     * Método que edita um Cliente
     *
     * @param cliente Cliente
     */
    public void Editar(Cliente cliente) {
        try {
            //Validar o cliente
            Validar(cliente);

            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Edita o cliente
            clienteDao.Editar(cliente);
        } catch (CampoObrigatorioNaoPreenchidoException conpe) {
            throw conpe;
        }
    }

    /**
     * Método que encontra um Cliente
     *
     * @param codigo Código do Cliente
     * @return Cliente
     */
    public Cliente Encontrar(int codigo) {
        try {
            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Encontrar o cliente e retornar
            return clienteDao.Encontrar(codigo);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que encontra um Cliente pelo nome
     *
     * @param nome Nome
     * @return Cliente
     */
    public Cliente Encontrar(String nome) {
        try {
            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Encontrar o cliente e retornar
            return clienteDao.Encontrar(nome);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que lista os Clientes
     *
     * @return Lista de Clientes
     */
    public List<Cliente> Listar() {
        try {
            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Listar os clientes e retornar
            return clienteDao.Listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que remove o Cliente pelo Código
     *
     * @param codigo Código
     */
    public void Remover(int codigo) {
        try {
            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Remover o cliente e retornar
            clienteDao.Remover(codigo);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que valida um Cliente
     *
     * @param cliente Cliente
     */
    public void Validar(Cliente cliente) {
        //Valdiar nome
        if (cliente.getNome().equals("")) {
            throw new CampoObrigatorioNaoPreenchidoException("O Campo Nome é Obrigatório");
        }
    }

    /**
     * Método que verifica se um regitro já existe ou não pelo nome
     *
     * @param nome Nome
     * @return True or False
     */
    private boolean RegistroJaExiste(String nome) {
        try {
            //Instanciar a camada Dao
            ClienteDao clienteDao = new ClienteDao();

            //Retornar se o registro existe ou não
            return clienteDao.RegistroJaExiste(nome);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
