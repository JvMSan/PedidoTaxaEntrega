package ufes.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufes.br.pedido.Cliente;
import ufes.br.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.cadastrarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }

    @GetMapping("/{clienteID}")
    public ResponseEntity<Cliente> getCliente(@PathVariable Integer clienteID) {
        Cliente cliente = clienteService.buscarCliente(clienteID);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<List<Cliente>> listarClientes() {
        return new ResponseEntity<>(clienteService.getClientes(), HttpStatus.OK); //ver com getBancoDeDados
    }
}
