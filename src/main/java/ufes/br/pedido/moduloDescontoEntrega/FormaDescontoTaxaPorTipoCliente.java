package ufes.br.pedido.moduloDescontoEntrega;

import java.util.HashMap;
import java.util.Map;
import ufes.br.pedido.Pedido;

public class FormaDescontoTaxaPorTipoCliente implements IFormaCalculoDescontoEntrega {

    private final Map<String, Double> descontosPorTipoCliente;
    private String tipoCliente;

    public FormaDescontoTaxaPorTipoCliente() {
        descontosPorTipoCliente = new HashMap<>();
        descontosPorTipoCliente.put("Ouro", 3.0);
        descontosPorTipoCliente.put("Prata", 2.0);
        descontosPorTipoCliente.put("Bronze", 1.0);
    }

    @Override
    public Map<String, Double> calcularDesconto(Pedido pedido) {
        Map<String, Double> descontoData = new HashMap<>();
        tipoCliente = pedido.getCliente().getTipo();
        if (seAplica(pedido)) {
            descontoData.put("Desconto por Tipo de Cliente", descontosPorTipoCliente.get(tipoCliente));
        }
        return descontoData;
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        tipoCliente = pedido.getCliente().getTipo();
        return descontosPorTipoCliente.containsKey(tipoCliente);
    }
}
