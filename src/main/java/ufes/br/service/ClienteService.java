package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Cliente;
import ufes.br.repository.RepositorioCliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private final RepositorioCliente repository;

    public ClienteService() {
        this.repository = RepositorioCliente.getInstance();
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        repository.salvarCliente(cliente);
        return cliente;
    }
    
    public Cliente buscarCliente(Integer id) { return repository.buscarCliente(id); }

    public List<Cliente> getClientes() {
        return repository.getBancoDeDados();
    }
}
