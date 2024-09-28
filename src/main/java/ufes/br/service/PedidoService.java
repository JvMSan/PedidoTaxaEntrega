package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Pedido;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private List<Pedido> pedidos = new ArrayList<>();

    public Pedido cadastrarPedido(Pedido pedido) {
        pedidos.add(pedido);  // Adiciona o pedido diretamente
        return pedido;
    }

    public Pedido buscarPedidoPorCliente(String nomeCliente) {
        return pedidos.stream()
                .filter(pedido -> pedido.getCliente().getNome().equalsIgnoreCase(nomeCliente))
                .findFirst()
                .orElse(null);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
