package sistemadepedidos.app;

import java.util.List;
import sistemadepedidos.dominio.ItemPedido;
import sistemadepedidos.dominio.Pedido;
import sistemadepedidos.exceptions.ClienteNaoInformadoException;
import sistemadepedidos.exceptions.PrecoZeradoOuNegativoException;
import sistemadepedidos.repositorio.ItemPedidoDao;
import sistemadepedidos.repositorio.PedidoDao;

/**
 * Classe responsável pelas lógicas de negócio de um pedido
 *
 * @author LuizRicardo
 */
public class PedidoApp {

    /**
     * Método que cria um pedido
     *
     * @param pedido Pedido
     */
    public void Criar(Pedido pedido) {
        try {
            //Validar o Pedido
            Validar(pedido);

            //Instanciar a camada Dao do Pedido
            PedidoDao pedidoDao = new PedidoDao();

            //Cria o Pedido
            int numeroGerado = pedidoDao.Criar(pedido);

            //Instanciar a camada Dao do Item de Pedido
            ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

            //Percorrer os Itens do Pedido
            for (ItemPedido ip : pedido.getItensPedido()) {
                //Setar o número do Pedido
                ip.setPedido(numeroGerado);

                //Inserir o Item
                itemPedidoDao.Criar(ip);
            }
        } catch (PrecoZeradoOuNegativoException pzone) {
            throw pzone;
        }
    }

    /**
     * Método que edita um Pedido
     *
     * @param pedido Pedido
     */
    public void Editar(Pedido pedido) {
        try {
            //Validar o Pedido
            Validar(pedido);

            //Instanciar a camada Dao do Pedido
            PedidoDao pedidoDao = new PedidoDao();

            //Editar o Pedido
            pedidoDao.Editar(pedido);

            //Instanciar a camada Dao do Item de Pedido
            ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

            //Remover os Itens do Pedido
            itemPedidoDao.Remover(pedido.getNumero());

            //Percorrer os Itens do Pedido e Incluir os Itens
            for (ItemPedido ip : pedido.getItensPedido()) {
                //Setar o número do Pedido
                ip.setPedido(pedido.getNumero());

                //Inserir o Item
                itemPedidoDao.Criar(ip);
            }
        } catch (PrecoZeradoOuNegativoException pzone) {
            throw pzone;
        }
    }

    /**
     * Método que remove um Pedido
     *
     * @param numeroPedido Número do Pedido
     */
    public void Remover(int numeroPedido) {
        try {
            //Instanciar a camada Dao do Item de Pedido
            ItemPedidoDao itemPedidoDao = new ItemPedidoDao();

            //Remover os Itens do Pedido
            itemPedidoDao.Remover(numeroPedido);

            //Instanciar a camada Dao do Pedido
            PedidoDao pedidoDao = new PedidoDao();

            //Remover o Pedido
            pedidoDao.Remover(numeroPedido);

        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que encontra um Pedido
     *
     * @param numero Número do Pedido
     * @return Pedido
     */
    public Pedido Encontrar(int numero) {
        try {
            //Instanciar a camada Dao
            PedidoDao pedidoDao = new PedidoDao();

            //Encontrar o Pedido e retornar
            return pedidoDao.Encontrar(numero);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que lista os Pedidos
     *
     * @return Lista de Pedidos
     */
    public List<Pedido> Listar() {
        try {
            //Instanciar a camada Dao
            PedidoDao pedidoDao = new PedidoDao();

            //Listar os pedidos e retornar
            return pedidoDao.Listar();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Método que valida um Pedido
     *
     * @param pedido Pedido
     */
    public void Validar(Pedido pedido) {
        //Validar se o Cliente foi preenchido
        if (pedido.getCliente() == null) {
            throw new ClienteNaoInformadoException("O Cliente não foi informado.");
        }

        //Valdiar Valor Total 
        if (pedido.getValorTotal() <= 0) {
            throw new PrecoZeradoOuNegativoException("O Valor Total dever ser maior que Zero.");
        }

        //Validar Itens do Pedido
        if (pedido.getItensPedido().isEmpty()) {
            throw new ArrayIndexOutOfBoundsException("O Pedido deve possuir ao menos um Item.");
        }
    }
}
