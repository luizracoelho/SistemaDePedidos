package sistemadepedidos.dominio;

import java.util.Date;
import java.util.List;

/**
 * Classe que modela um Pedido do Sistema
 * @author LuizRicardo
 */
public class Pedido {
    //Atributo
    private int numero;
    private Date data;
    private Cliente cliente;
    private double valorTotal;
    private List<ItemPedido> itensPedido;
    
    //Getter and Setter
    public int getNumero() {
        return numero;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
