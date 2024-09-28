package ufes.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufes.br.pedido.CalculadoraDescontoService;
import ufes.br.pedido.Cliente;
import ufes.br.pedido.Item;
import ufes.br.pedido.Pedido;
import ufes.br.repository.RepositorioCliente;
import ufes.br.repository.RepositorioPedido;
import ufes.br.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Request request) {
        Cliente cliente = RepositorioCliente.getInstance().buscarCliente(request.clienteId());
        Pedido pedido = new Pedido(request.data(), cliente);
        for (Item item : request.itens()) { pedido.adicionarItem(item);}
        Pedido novoPedido = pedidoService.cadastrarPedido(pedido);
        return new ResponseEntity<>(novoPedido, HttpStatus.CREATED);
    }
    @GetMapping("/{pedidoID}")
    public ResponseEntity<Pedido> getDescontos(@PathVariable Integer pedidoID) {
        Pedido pedidoEncontrado = RepositorioPedido.getInstance().buscarCliente(pedidoID);
        return new ResponseEntity<>(pedidoEncontrado, HttpStatus.OK);
    }

    @PostMapping("/{pedidoID}/processar-descontos")
    public ResponseEntity<Pedido> processarDescontos(@PathVariable Integer pedidoID) {
        Pedido pedidoEncontrado  =  RepositorioPedido.getInstance().buscarCliente(pedidoID);
        CalculadoraDescontoService calculadoraDescontoService = new CalculadoraDescontoService();
        calculadoraDescontoService.processar(pedidoEncontrado);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
