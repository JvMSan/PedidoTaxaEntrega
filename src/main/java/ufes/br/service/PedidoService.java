package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Pedido;
import ufes.br.repository.RepositorioPedido;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {
    private int ultimoId = 0;
    private final RepositorioPedido repository;

    public PedidoService(){
        this.repository = RepositorioPedido.getInstance();
    }

    public Pedido cadastrarPedido(Pedido pedido) {
        pedido.setPedidoID(gerarNovoId());
        return repository.salvarPedido(pedido);
    }

    public Pedido buscarPedidoPorClienteId(int id) {
        return repository.getBancoDeDados().stream()
                .filter(pedido -> pedido.getCliente().getId() == id) // Comparação direta com o int
                .findFirst()
                .orElse(null);
    }

    private int gerarNovoId() {
        return ultimoId++;
    }

    public List<Pedido> getPedidos() { return repository.getBancoDeDados(); }
}
