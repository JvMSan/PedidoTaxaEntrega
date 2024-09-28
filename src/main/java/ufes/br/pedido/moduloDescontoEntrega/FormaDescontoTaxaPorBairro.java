package ufes.br.pedido.moduloDescontoEntrega;

import ufes.br.pedido.Pedido;
import java.util.HashMap;
import java.util.Map;

public class FormaDescontoTaxaPorBairro implements IFormaCalculoDescontoEntrega {
    private String bairroCliente;

    @Override
    public Map<String, Double> calcularDesconto(Pedido pedido) {
        Map<String, Double> resultado = new HashMap<>();
        if (seAplica(pedido)) {
            double desconto = 0.0;
            switch (bairroCliente) {
                case "Centro":
                    desconto = 2.0;
                    break;
                case "Bela Vista":
                    desconto = 3.0;
                    break;
                case "Cidade Maravilhosa":
                    desconto = 1.5;
                    break;
            }
            resultado.put("Desconto por Bairro", desconto);
        }
        return resultado;
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        bairroCliente = pedido.getCliente().getBairro();
        if (bairroCliente == null) {
            throw new RuntimeException("O endereço do cliente deve ter um bairro");
        }
        return "Centro".equals(bairroCliente) || "Bela Vista".equals(bairroCliente) || "Cidade Maravilhosa".equals(bairroCliente);
    }
}
