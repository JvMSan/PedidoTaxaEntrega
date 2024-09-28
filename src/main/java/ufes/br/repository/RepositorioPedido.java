package ufes.br.repository;

import ufes.br.pedido.Cliente;
import ufes.br.pedido.Pedido;

import java.util.ArrayList;

public class RepositorioPedido {
    private static RepositorioPedido repository;
    private final ArrayList<Pedido> database = new ArrayList<>();

    private RepositorioPedido() {
    }

    public static RepositorioPedido getInstance() {
        if (repository == null) {
            repository = new RepositorioPedido();
        }
        return repository;
    }
    public Pedido salvarPedido(Pedido pedido) {
        database.add(pedido);
        return pedido;
    }
    public ArrayList<Pedido> getBancoDeDados() {
        return database;
    }
    public Pedido buscarCliente(int id) {
        return database.get(id);
    }
}
