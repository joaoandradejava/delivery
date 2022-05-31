package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.*;
import com.joaoandrade.delivery.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientePedidoService {

    @Autowired
    private CrudClienteService crudClienteService;

    @Autowired
    private CrudClienteEnderecoService crudClienteEnderecoService;

    @Autowired
    private CrudProdutoService crudProdutoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public void fazerPedido(Pedido pedido, Long clienteId) {
        Cliente cliente = crudClienteService.buscarPorId(clienteId);
        pedido.setCliente(cliente);
        pedido.setEnderecoDeEntrega(crudClienteEnderecoService.buscarEnderecoDoCliente(clienteId, pedido.getEnderecoDeEntrega().getId()));

        for (ItemPedido itemPedido : pedido.getItens()) {
            Produto produto = crudProdutoService.buscarPorId(itemPedido.getId().getProduto().getId());
            itemPedido.getId().setProduto(produto);
            itemPedido.getId().setPedido(pedido);

            itemPedido.setPorcentagemDesconto(produto.getPorcentagemDesconto());
            itemPedido.setPrecoUnitario(produto.getPreco());

            if (!produto.verificarDisponibilidadeEstoque(itemPedido.getQuantidade())) {
                throw new SistemaException("Quantidade do produto não esta disponivel no estoque!");
            }

            produto.removerQuantidadeEstoque(itemPedido.getQuantidade());
        }

        pedidoRepository.save(pedido);
        System.out.println(pedido);

    }
}
