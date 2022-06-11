package com.joaoandrade.delivery.domain.service;

import com.joaoandrade.delivery.domain.exception.EntidadeNaoEncontradaException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.*;
import com.joaoandrade.delivery.domain.repository.PedidoRepository;
import com.joaoandrade.delivery.domain.service.crud.CrudClienteEnderecoService;
import com.joaoandrade.delivery.domain.service.crud.CrudClienteService;
import com.joaoandrade.delivery.domain.service.crud.CrudProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private MessageSource messageSource;

    public Page<Pedido> buscarTodos(Long clienteId, Pageable pageable) {
        return pedidoRepository.buscarTodos(clienteId, pageable);
    }

    private Pedido buscarPorId(Long pedidoId) {
        String[] args = {"pedido", pedidoId.toString()};
        return pedidoRepository.findById(pedidoId).orElseThrow(() -> new EntidadeNaoEncontradaException(messageSource.getMessage("entidade.nao.encontrada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    public Pedido buscarPorId(Long clienteId, Long pedidoId) {
        crudClienteService.buscarPorId(clienteId);
        buscarPorId(pedidoId);

        String[] args = {"pedido", pedidoId.toString(), "cliente", clienteId.toString()};
        return pedidoRepository.buscarPedidoDoCliente(clienteId, pedidoId).orElseThrow(() -> new SistemaException(messageSource.getMessage("entidade.nao.esta.associada.substantivo.masculino", args, LocaleContextHolder.getLocale())));
    }

    @Transactional
    public Pedido fazerPedido(Pedido pedido, Long clienteId) {
        Cliente cliente = crudClienteService.buscarPorId(clienteId);
        pedido.setCliente(cliente);
        pedido.setEnderecoDeEntrega(crudClienteEnderecoService.buscarEnderecoDoCliente(clienteId, pedido.getEnderecoDeEntrega().getId()));

        for (ItemPedido itemPedido : pedido.getItens()) {
            Produto produto = crudProdutoService.buscarPorId(itemPedido.getId().getProduto().getId());
            itemPedido.getId().setProduto(produto);
            itemPedido.getId().setPedido(pedido);
            itemPedido.setPrecoUnitario(produto.getPrecoAtual());
            itemPedido.calcularValorTotal();

            if (!produto.verificarDisponibilidadeEstoque(itemPedido.getQuantidade())) {
                throw new SistemaException("Quantidade do produto não esta disponivel no estoque!");
            }

            produto.removerQuantidadeEstoque(itemPedido.getQuantidade());

        }

        pedido.calcularValorTotal();

        return pedidoRepository.save(pedido);
    }


    @Transactional
    public void cancelarPedido(Long clienteId, Long pedidoId) {
        Pedido pedido = buscarPorId(clienteId, pedidoId);

        pedido.cancelarPedido();
    }
}
