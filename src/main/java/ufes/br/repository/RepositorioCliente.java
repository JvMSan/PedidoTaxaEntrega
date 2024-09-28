package ufes.br.repository;

import ufes.br.pedido.Cliente;

import java.util.ArrayList;

public class RepositorioCliente {
    private static RepositorioCliente repository;
    private final ArrayList<Cliente> database = new ArrayList<>();

    private RepositorioCliente() {
    }

    public static RepositorioCliente getInstance() {
        if (repository == null) {
            repository = new RepositorioCliente();
        }
        return repository;
    }
    public Cliente salvarCliente(Cliente cliente) {
        database.add(cliente);
        return cliente;
    }
    public ArrayList<Cliente> getBancoDeDados() {
        return database;
    }
    public Cliente buscarCliente(int id) {
        return database.get(id);
    }
}
