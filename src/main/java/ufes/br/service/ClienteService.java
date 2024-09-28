package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Cliente;
import ufes.br.repository.RepositorioCliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private int ultimoId = 0; // variável que controla o último ID que foi gerado
    private final RepositorioCliente repository;

    public ClienteService() {
        this.repository = RepositorioCliente.getInstance();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setId(gerarNovoId());
        repository.salvarCliente(cliente);
        return cliente;
    }
    
    public Cliente buscarCliente(int id) { return repository.buscarCliente(id); }

    private int gerarNovoId() {
        return ultimoId++; // incrementa o ID a cada novo cliente
    }
    public List<Cliente> getClientes() {
        return repository.getBancoDeDados();
    }
}
