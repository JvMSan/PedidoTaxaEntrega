package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Cliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();

    public Cliente cadastrarCliente(Cliente cliente) {
        clientes.add(cliente);
        return cliente;
    }
    
    public Cliente buscarClientePorNome(String nome) {
        return clientes.stream()
                .filter(cliente -> cliente.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
