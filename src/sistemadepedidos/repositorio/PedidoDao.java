package sistemadepedidos.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemadepedidos.app.ClienteApp;
import sistemadepedidos.dominio.Pedido;

/**
 * Classe que modela a conexão com o banco de dados para ações do Pedido
 *
 * @author LuizRicardo
 */
public class PedidoDao {

    /**
     * Método que cria um Pedido no banco de dados
     *
     * @param pedido Pedido
     * @return Número Gerado
     */
    public int Criar(Pedido pedido) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "INSERT INTO pedido (data, cliente, valorTotal) VALUES (?, ?, ?);";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                preparedStmt.setDate(1, new Date(pedido.getData().getTime()));
                preparedStmt.setInt(2, pedido.getCliente().getCodigo());
                preparedStmt.setDouble(3, pedido.getValorTotal());

                // Executar o Prepared Stateament
                preparedStmt.execute();

                //Criar o Result Set das Keys
                ResultSet rs = preparedStmt.getGeneratedKeys();

                //Ler próxima Key
                rs.next();

                //Retornar a Key
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    /**
     * Método que edita um Pedido no banco de dados
     *
     * @param pedido Pedido
     */
    public void Editar(Pedido pedido) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "UPDATE pedido SET data=?, cliente=?, valorTotal=? WHERE numero=?;";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setDate(1, new Date(pedido.getData().getTime()));
                preparedStmt.setInt(2, pedido.getCliente().getCodigo());
                preparedStmt.setDouble(3, pedido.getValorTotal());
                preparedStmt.setInt(4, pedido.getNumero());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que remove um Pedido no banco de dados
     *
     * @param numero Número do Pedido
     */
    public void Remover(int numero) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "DELETE FROM pedido WHERE numero=?;";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher os parametros
                preparedStmt.setInt(1, numero);

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que lista os Pedidos do banco de dados
     *
     * @return Lista de Pedidos
     */
    public List<Pedido> Listar() {
        try {
            // Conectar o banco de dados
            Connection conn = Dao.getConnection();

            //Criar lista de Pedidos
            List<Pedido> listaDePedidos = new ArrayList<Pedido>() {
            };

            //Criar a query
            String query = "SELECT * FROM pedido;";

            try (Statement st = conn.createStatement()) {
                // Criar o ResultSet
                ResultSet rs = st.executeQuery(query);

                // Ler os registros
                while (rs.next()) {
                    //Criar um novo Pedido
                    Pedido ped = new Pedido();

                    //Instanciar aplicação do Cliente
                    ClienteApp clienteApp = new ClienteApp();

                    //Preencher Cliente
                    ped.setNumero(rs.getInt("numero"));
                    ped.setData(rs.getDate("data"));
                    ped.setCliente(clienteApp.Encontrar(rs.getInt("cliente")));
                    ped.setValorTotal(rs.getDouble("valorTotal"));

                    //Adicionar Pedido à lista
                    listaDePedidos.add(ped);
                }
            }

            //Fechar a Conexão
            Dao.closeConnection();

            //Retornar lista
            return listaDePedidos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Pedido no banco de dados
     *
     * @param numero Número do Pedido
     * @return Pedido
     */
    public Pedido Encontrar(int numero) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM pedido WHERE numero = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setInt(1, numero);

                //Executar o Comando
                preparedStmt.execute();

                // Criar o ResultSet
                ResultSet rs = preparedStmt.getResultSet();

                // Ler o registro
                rs.next();

                //Criar um novo Pedido
                Pedido ped = new Pedido();

                //Instanciar a aplicação do CLiente
                ClienteApp clienteApp = new ClienteApp();

                //Preencher Pedido
                ped.setNumero(rs.getInt("numero"));
                ped.setData(rs.getDate("data"));
                ped.setCliente(clienteApp.Encontrar(rs.getInt("cliente")));
                ped.setValorTotal(rs.getDouble("valorTotal"));
                
                //Instanciar a Dao do Item de Pedido
                ItemPedidoDao itemPedidoDao = new ItemPedidoDao();
                
                //Preencher a Lista de Itens de Pedido
                ped.setItensPedido(itemPedidoDao.Listar(numero));

                //Retornar Pedido
                return ped;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
