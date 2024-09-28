package ufes.br.pedido.moduloDescontoEntrega;

import ufes.br.pedido.Pedido;
import java.util.HashMap;
import java.util.Map;

public class FormaDescontoValorPedido implements IFormaCalculoDescontoEntrega {

    private double limiteValorPedido;
    private static final double VALOR_DESCONTO = 5.0;

    public FormaDescontoValorPedido(double limiteValorPedido) {
        this.limiteValorPedido = limiteValorPedido;
    }

    @Override
    public Map<String, Double> calcularDesconto(Pedido pedido) {
        Map<String, Double> descontoData = new HashMap<>();
        if (pedido.getValorPedido() > this.limiteValorPedido) {
            descontoData.put("Desconto por Valor de Pedido", VALOR_DESCONTO);
        }
        return descontoData;
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        return pedido.getValorPedido() > this.limiteValorPedido && pedido.getDescontoConcedido() < 10;
    }
}
