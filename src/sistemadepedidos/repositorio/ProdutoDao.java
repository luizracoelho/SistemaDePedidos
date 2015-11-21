package sistemadepedidos.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemadepedidos.dominio.Produto;

/**
 * Classe que modela a conexão com o banco de dados para ações do Produto
 *
 * @author LuizRicardo
 */
public class ProdutoDao {

    /**
     * Método que cria um Produto no banco de dados
     *
     * @param produto Produto
     */
    public void Criar(Produto produto) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "INSERT INTO produto (descricao, ncm, grupo, preco) VALUES (?, ?, ?, ?);";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, produto.getDescricao());
                preparedStmt.setString(2, produto.getNcm());
                preparedStmt.setString(3, produto.getGrupo());
                preparedStmt.setDouble(4, produto.getPreco());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que edita um Produto no banco de dados
     *
     * @param produto Produto
     */
    public void Editar(Produto produto) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "UPDATE produto SET descricao=?, ncm=?, grupo=?, preco=? WHERE codigo=?;";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, produto.getDescricao());
                preparedStmt.setString(2, produto.getNcm());
                preparedStmt.setString(3, produto.getGrupo());
                preparedStmt.setDouble(4, produto.getPreco());
                preparedStmt.setInt(5, produto.getCodigo());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que remove um Produto do banco de dados
     *
     * @param codigo Código do Produto
     */
    public void Remover(int codigo) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "DELETE from produto WHERE codigo=?;";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher os parametros
                preparedStmt.setInt(1, codigo);

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que lista os Clientes do banco de dados
     *
     * @return Lista de Produtos
     */
    public List<Produto> Listar() {
        try {
            // Conectar o banco de dados
            Connection conn = Dao.getConnection();

            //Criar lista de Clientes
            List<Produto> listaDeProdutos = new ArrayList<Produto>() {
            };

            //Criar a query
            String query = "SELECT * FROM produto";

            try (Statement st = conn.createStatement()) {
                // Criar o ResultSet
                ResultSet rs = st.executeQuery(query);

                // Ler os registros
                while (rs.next()) {
                    //Criar um novo Produto
                    Produto prod = new Produto();

                    //Preencher Produto
                    prod.setCodigo(rs.getInt("codigo"));
                    prod.setDescricao(rs.getString("descricao"));
                    prod.setNcm(rs.getString("ncm"));
                    prod.setGrupo(rs.getString("grupo"));
                    prod.setPreco(rs.getDouble("preco"));

                    //Adicionar produto à lista
                    listaDeProdutos.add(prod);
                }
            }

            //Fechar a Conexão
            Dao.closeConnection();

            //Retornar lista
            return listaDeProdutos;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Produto no banco de dados
     *
     * @param codigo Código do Produto
     * @return Produto
     */
    public Produto Encontrar(int codigo) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM produto WHERE codigo = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setInt(1, codigo);

                //Executar o Comando
                preparedStmt.execute();

                // Criar o ResultSet
                ResultSet rs = preparedStmt.getResultSet();

                // Ler o registro
                rs.next();

                //Criar um novo Produto
                Produto prod = new Produto();

                //Preencher Produto
                prod.setCodigo(rs.getInt("codigo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setNcm(rs.getString("ncm"));
                prod.setGrupo(rs.getString("grupo"));
                prod.setPreco(rs.getDouble("preco"));

                //Retornar lista
                return prod;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Produto pela descricao no banco de dados
     *
     * @param descricao Descrição
     * @return Produto
     */
    public Produto Encontrar(String descricao) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM produto WHERE descricao = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setString(1, descricao);

                //Executar o Comando
                preparedStmt.execute();

                // Criar o ResultSet
                ResultSet rs = preparedStmt.getResultSet();

                // Ler o registro
                rs.next();

                //Criar um novo Produto
                Produto prod = new Produto();

                //Preencher Produto
                prod.setCodigo(rs.getInt("codigo"));
                prod.setDescricao(rs.getString("descricao"));
                prod.setNcm(rs.getString("ncm"));
                prod.setGrupo(rs.getString("grupo"));
                prod.setPreco(rs.getDouble("preco"));

                //Retornar lista
                return prod;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que verifica se um registro existe no banco de dados
     *
     * @param descricao Descrição
     * @return True or False
     */
    public boolean RegistroJaExiste(String descricao) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM produto WHERE descricao = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setString(1, descricao);

                //Executar o Comando
                preparedStmt.execute();

                // Criar o ResultSet
                ResultSet rs = preparedStmt.getResultSet();

                // Retornar se registro existe ou não
                return rs.isBeforeFirst();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
