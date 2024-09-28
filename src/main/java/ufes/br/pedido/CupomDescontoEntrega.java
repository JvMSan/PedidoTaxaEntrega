package ufes.br.pedido;

/**
 *
 * @author clayton
 */
public class CupomDescontoEntrega {

    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoEntrega(String nomeMetodo, double valorDesconto) {
        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public String getNomeMetodo() {
        return nomeMetodo;
    }

    @Override
    public String toString() {
        return "CupomDescontoEntrega {" +
               "nomeMetodo='" + nomeMetodo + '\'' +
               ", valorDesconto=" + valorDesconto +
               '}';
    }
}
