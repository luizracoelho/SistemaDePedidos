package sistemadepedidos.repositorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados
 * @author LuizRicardo
 */
public class Dao {

    public static String status = "Não conectado!";

    public Dao() {
    }

    public static Connection getConnection() {
        Connection connection = null; //atributo do tipo Connection 

        try { // Carregando o JDBC Driver padrão 
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName); // Configurando a nossa conexão com um banco de dados// 
            String serverName = "localhost"; //caminho do servidor do BD 
            String mydatabase = "sistemadepedidos"; //nome do seu banco de dados 
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "root"; //nome de um usuário de seu BD 
            String password = "admin"; //sua senha de acesso 

            connection = DriverManager.getConnection(url, username, password); //Testa sua conexão// 

            if (connection != null) {
                status = ("Conectado com sucesso!");
            } else {
                status = ("Não foi possivel realizar conexão");
            }

            return connection;

        } catch (ClassNotFoundException e) { //Driver não encontrado 
            System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) { //Não conseguindo se conectar ao banco 
            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }

    }

    //Método que retorna o status da sua conexão// 
    public static String statusConnection() {
        return status;
    }

    //Método que fecha sua conexão// 
    public static boolean closeConnection() {
        try {
            getConnection().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    } 

    //Método que reinicia sua conexão// 
    public static Connection ReiniciarConexao() {
        closeConnection();
        return getConnection();
    }

}
