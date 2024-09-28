package ufes.br.pedido.moduloDescontoEntrega;

import ufes.br.pedido.Pedido;
import java.util.Map;

public interface IFormaCalculoDescontoEntrega {
    Map<String, Double> calcularDesconto(Pedido pedido);
    boolean seAplica(Pedido pedido);
}
