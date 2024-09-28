package ufes.br.service;

import org.springframework.stereotype.Service;
import ufes.br.pedido.Cliente;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {

    private List<Cliente> clientes = new ArrayList<>();
    private int ultimoId = 0; // variável que controla o último ID que foi gerado

    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setId(gerarNovoId());  // define um novo ID gerado no cliente
        clientes.add(cliente);
        return cliente;
    }
    
    public Cliente buscarClientePorNome(String nome) {
        return clientes.stream()
                .filter(cliente -> cliente.getNome().equalsIgnoreCase(nome))
                .findFirst()
                .orElse(null);
    }

    private int gerarNovoId() {
        return ultimoId++; // incrementa o ID a cada novo cliente
    }
    public List<Cliente> getClientes() {
        return clientes;
    }
}
