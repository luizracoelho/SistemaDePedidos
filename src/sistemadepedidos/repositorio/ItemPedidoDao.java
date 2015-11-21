package sistemadepedidos.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import sistemadepedidos.app.ProdutoApp;
import sistemadepedidos.dominio.ItemPedido;

/**
 * Classe que modela a conexão com o banco de dados para ações do Item do Pedido
 *
 * @author LuizRicardo
 */
public class ItemPedidoDao {

    /**
     * Método que cria um Item do Pedido no banco de dados
     *
     * @param itemPedido Item do Pedido
     */
    public void Criar(ItemPedido itemPedido) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "INSERT INTO itempedido (produto, quant, vlUnitario, vlTotal, pedido) VALUES (?,?,?,?,?);";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, itemPedido.getProduto().getCodigo());
                preparedStmt.setDouble(2, itemPedido.getQuant());
                preparedStmt.setDouble(3, itemPedido.getVlUnit());
                preparedStmt.setDouble(4, itemPedido.getVlTotal());
                preparedStmt.setInt(5, itemPedido.getPedido());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que remove um Item do Pedido no banco de dados
     *
     * @param numeroPedido Id do Pedido
     */
    public void Remover(int numeroPedido) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Desabilitar o sql safe updates
                String query = "SET SQL_SAFE_UPDATES = 0;";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                // Executar o Prepared Stateament
                preparedStmt.execute();

                // Criar a query
                query = "DELETE FROM itempedido WHERE pedido=?;";

                //Informar a nova query
                preparedStmt = conn.prepareStatement(query);

                //Preecnher os parametros
                preparedStmt.setInt(1, numeroPedido);

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que lista os Itens de Pedido de um determinado Pedido
     *
     * @param numeroPedido Id do Pedido
     * @return Lista de Itens de Pedido
     */
    public List<ItemPedido> Listar(int numeroPedido) {
        try {
            // Conectar o banco de dados
            Connection conn = Dao.getConnection();

            //Criar lista de Itens de Pedidos
            List<ItemPedido> listaDeItensPedido = new ArrayList<ItemPedido>() {
            };

            //Criar a query
            String query = "SELECT * FROM itempedido WHERE pedido=?;";

            //Criar o Prepare Statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            //Preecnher o parametro
            preparedStmt.setInt(1, numeroPedido);

            //Executar o Comando
            preparedStmt.execute();

            // Criar o ResultSet
            ResultSet rs = preparedStmt.getResultSet();

            // Ler os registros
            while (rs.next()) {
                //Criar um novo Item de Pedido
                ItemPedido ip = new ItemPedido();

                //Instanciar a aplicação do Produto
                ProdutoApp produtoApp = new ProdutoApp();

                //Preencher Item Pedido
                ip.setId(rs.getInt("iditempedido"));
                ip.setProduto(produtoApp.Encontrar(rs.getInt("produto")));
                ip.setQuant(rs.getDouble("quant"));
                ip.setVlUnit(rs.getDouble("vlUnitario"));
                ip.setVlTotal(rs.getDouble("vlTotal"));
                ip.setPedido(rs.getInt("pedido"));

                //Adicionar Item do Pedido à lista
                listaDeItensPedido.add(ip);
            }

            //Fechar a Conexão
            Dao.closeConnection();

            //Retornar lista
            return listaDeItensPedido;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Item de Pedido no banco de dados
     *
     * @param idItemPedido Id do Item de Pedido
     * @return Item de Pedido
     */
    public ItemPedido Encontrar(int idItemPedido) {
        try {
            // Conectar o banco de dados
            Connection conn = Dao.getConnection();

            //Criar a query
            String query = "SELECT * FROM itempedido WHERE iditempedido=?;";

            //Criar o Prepare Statement
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            //Preecnher o parametro
            preparedStmt.setInt(1, idItemPedido);

            //Executar o Comando
            preparedStmt.execute();

            // Criar o ResultSet
            ResultSet rs = preparedStmt.getResultSet();

            // Ler os registros
            rs.next();

            //Criar um novo Item de Pedido
            ItemPedido ip = new ItemPedido();

            //Instanciar a aplicação do Produto
            ProdutoApp produtoApp = new ProdutoApp();

            //Preencher Item Pedido
            ip.setId(rs.getInt("iditempedido"));
            ip.setProduto(produtoApp.Encontrar(rs.getInt("produto")));
            ip.setQuant(rs.getDouble("quant"));
            ip.setVlUnit(rs.getDouble("vlUnitario"));
            ip.setVlTotal(rs.getDouble("vlTotal"));
            ip.setPedido(rs.getInt("pedido"));

            //Fechar a Conexão
            Dao.closeConnection();

            //Retornar lista
            return ip;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
