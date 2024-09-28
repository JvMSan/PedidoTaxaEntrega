package ufes.br.pedido;

/**
 *
 * @author clayton
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private LocalDate data;
    private Cliente cliente;
    private final double taxaEntrega = 10.00;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDescontoEntrega;

    public Pedido(LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.cuponsDescontoEntrega = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double getValorPedido() {
        double valorTotal = 0.0;
        for (Item item : itens) {
            valorTotal += item.getValorTotal();
        }
        return valorTotal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public double getTaxaEntrega() {
        return taxaEntrega - getDescontoConcedido();
    }

    public void aplicarDesconto(CupomDescontoEntrega desconto) {
        double valorComDesconto = taxaEntrega - getDescontoConcedido() - desconto.getValorDesconto();
        if (valorComDesconto < 0) {
            desconto = new CupomDescontoEntrega("Parcial - " + desconto.getNomeMetodo(), desconto.getValorDesconto() + valorComDesconto);
        }
        cuponsDescontoEntrega.add(desconto);
    }

    public double getDescontoConcedido() {
        double descontoTotal = 0.0;
        for (CupomDescontoEntrega desconto : cuponsDescontoEntrega) {
            descontoTotal += desconto.getValorDesconto();
        }
        return descontoTotal;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega() {
        return cuponsDescontoEntrega;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido{");
        sb.append("data=").append(data);
        sb.append(", cliente=").append(cliente);
        sb.append(", taxaEntrega=").append(taxaEntrega);
        sb.append(", valorPedido=").append(getValorPedido());
        sb.append(", descontoConcedido=").append(getDescontoConcedido());
        sb.append(", itens=").append(itens);
        sb.append(", cuponsDescontoEntrega=").append(cuponsDescontoEntrega);
        sb.append('}');
        return sb.toString();
    }

}
