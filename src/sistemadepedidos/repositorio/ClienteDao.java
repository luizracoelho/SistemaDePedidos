package sistemadepedidos.repositorio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import sistemadepedidos.dominio.Cliente;

/**
 * Classe que modela a conexão com o banco de dados para ações do Cliente
 *
 * @author LuizRicardo
 */
public class ClienteDao {

    /**
     * Método que cria um Cliente no banco de dados
     *
     * @param cliente Cliente
     */
    public void Criar(Cliente cliente) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "INSERT INTO cliente (nome, dataCadastro, rg, cpf, telefone, celular, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, cliente.getNome());
                preparedStmt.setDate(2, new Date(cliente.getDataCadastro().getTime()));
                preparedStmt.setString(3, cliente.getRg());
                preparedStmt.setString(4, cliente.getCpf());
                preparedStmt.setString(5, cliente.getTelefone());
                preparedStmt.setString(6, cliente.getCelular());
                preparedStmt.setString(7, cliente.getEmail());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que edita um Cliente no banco de dados
     *
     * @param cliente Cliente
     */
    public void Editar(Cliente cliente) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "UPDATE cliente SET nome=?, dataCadastro=?, rg=?, cpf=?, telefone=?, celular=?, email=? WHERE codigo=?";

                // Criar o prepare Staetatment
                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setString(1, cliente.getNome());
                preparedStmt.setDate(2, new Date(cliente.getDataCadastro().getTime()));
                preparedStmt.setString(3, cliente.getRg());
                preparedStmt.setString(4, cliente.getCpf());
                preparedStmt.setString(5, cliente.getTelefone());
                preparedStmt.setString(6, cliente.getCelular());
                preparedStmt.setString(7, cliente.getEmail());
                preparedStmt.setInt(8, cliente.getCodigo());

                // Executar o Prepared Stateament
                preparedStmt.execute();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Método que remove um Cliente do banco de dados
     *
     * @param codigo Código do Cliente
     */
    public void Remover(int codigo) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {
                // Criar a query
                String query = "DELETE FROM cliente WHERE codigo=?";

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
     * @return Lista de Clientes
     */
    public List<Cliente> Listar() {
        try {
            // Conectar o banco de dados
            Connection conn = Dao.getConnection();

            //Criar lista de Clientes
            List<Cliente> listaDeClientes = new ArrayList<Cliente>() {
            };

            //Criar a query
            String query = "SELECT * FROM cliente";

            try (Statement st = conn.createStatement()) {
                // Criar o ResultSet
                ResultSet rs = st.executeQuery(query);

                // Ler os registros
                while (rs.next()) {
                    //Criar um novo cliente
                    Cliente cli = new Cliente();

                    //Preencher Cliente
                    cli.setCodigo(rs.getInt("codigo"));
                    cli.setNome(rs.getString("nome"));
                    cli.setDataCadastro(rs.getDate("dataCadastro"));
                    cli.setRg(rs.getString("rg"));
                    cli.setCpf(rs.getString("cpf"));
                    cli.setTelefone(rs.getString("telefone"));
                    cli.setCelular(rs.getString("celular"));
                    cli.setEmail(rs.getString("email"));

                    //Adicionar cliente à lista
                    listaDeClientes.add(cli);
                }
            }

            //Fechar a Conexão
            Dao.closeConnection();

            //Retornar lista
            return listaDeClientes;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Cliente no banco de dados
     *
     * @param codigo Código do Cliente
     * @return Cliente
     */
    public Cliente Encontrar(int codigo) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM cliente WHERE codigo = ?";

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

                //Criar um novo cliente
                Cliente cli = new Cliente();

                //Preencher Cliente
                cli.setCodigo(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setDataCadastro(rs.getDate("dataCadastro"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setEmail(rs.getString("email"));

                //Retornar o CLiente
                return cli;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que encontra um Cliente pelo Nome no banco de dados
     *
     * @param nome Nome
     * @return Cliente
     */
    public Cliente Encontrar(String nome) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM cliente WHERE nome = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setString(1, nome);

                //Executar o Comando
                preparedStmt.execute();

                // Criar o ResultSet
                ResultSet rs = preparedStmt.getResultSet();

                // Ler o registro
                rs.next();

                //Criar um novo cliente
                Cliente cli = new Cliente();

                //Preencher Cliente
                cli.setCodigo(rs.getInt("codigo"));
                cli.setNome(rs.getString("nome"));
                cli.setDataCadastro(rs.getDate("dataCadastro"));
                cli.setRg(rs.getString("rg"));
                cli.setCpf(rs.getString("cpf"));
                cli.setTelefone(rs.getString("telefone"));
                cli.setCelular(rs.getString("celular"));
                cli.setEmail(rs.getString("email"));

                //Retornar o CLiente
                return cli;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Método que verifica se um Clientes existe no banco de dados
     *
     * @param nome Nome
     * @return True or False
     */
    public boolean RegistroJaExiste(String nome) {
        try {
            // Conectar ao Banco
            try (Connection conn = Dao.getConnection()) {

                //Criar a query
                String query = "SELECT * FROM cliente WHERE nome = ?";

                //Criar o Prepare Statement
                PreparedStatement preparedStmt = conn.prepareStatement(query);

                //Preecnher o parametro
                preparedStmt.setString(1, nome);

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
