package ufes.br.controller;

import ufes.br.pedido.Item;

import java.time.LocalDate;
import java.util.List;

public record Request (int clienteId, LocalDate data, List<Item> itens){
}
