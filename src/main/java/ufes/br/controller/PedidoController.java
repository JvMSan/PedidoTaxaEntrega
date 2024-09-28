package ufes.br.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ufes.br.pedido.CalculadoraDescontoService;
import ufes.br.pedido.Pedido;
import ufes.br.repository.RepositorioPedido;
import ufes.br.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> cadastrarPedido(@RequestBody Pedido pedido) {
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
