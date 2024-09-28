package ufes.br.pedido.moduloDescontoEntrega;

import java.util.HashMap;
import java.util.Map;
import ufes.br.pedido.Item;
import ufes.br.pedido.Pedido;

public class FormaDescontoTipoItem implements IFormaCalculoDescontoEntrega {

    private final Map<String, Double> descontosPorTipoItem;

    public FormaDescontoTipoItem() {
        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("Alimentação", 5.0);
        descontosPorTipoItem.put("Educação", 2.0);
        descontosPorTipoItem.put("Lazer", 1.5);
    }

    @Override
    public Map<String, Double> calcularDesconto(Pedido pedido) {
        Map<String, Double> descontoData = new HashMap<>();
        double descontoTotal = 0;

        for (Item item : pedido.getItens()) {
            String tipoItem = item.getTipo();
            descontoTotal += descontosPorTipoItem.getOrDefault(tipoItem, 0.0);
        }

        descontoData.put("Desconto por Tipo de Item", descontoTotal);
        return descontoData;
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        if (pedido == null || pedido.getItens() == null) {
            return false;
        }

        for (Item item : pedido.getItens()) {
            if (this.descontosPorTipoItem.containsKey(item.getTipo())) {
                return true;
            }
        }
        return false;
    }
}
