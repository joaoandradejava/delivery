package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.PedidoModelAssembler;
import com.joaoandrade.delivery.api.input.PedidoCreateInput;
import com.joaoandrade.delivery.api.model.PedidoFullModel;
import com.joaoandrade.delivery.api.model.PedidoModel;
import com.joaoandrade.delivery.domain.exception.EnderecoNaoEncontradoException;
import com.joaoandrade.delivery.domain.exception.ProdutoNaoEncontradoException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.model.Pedido;
import com.joaoandrade.delivery.domain.service.ClientePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes/{clienteId}/pedidos")
public class ClientePedidoController {


    @Autowired
    private ClientePedidoService clientePedidoService;


    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;

    @GetMapping
    public Page<PedidoModel> buscarTodos(@PathVariable Long clienteId, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable) {
        Page<Pedido> page = clientePedidoService.buscarTodos(clienteId, pageable);

        return page.map(pedido -> pedidoModelAssembler.toModel(pedido));
    }

    @GetMapping("/{pedidoId}")
    public PedidoFullModel buscarPorId(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        Pedido pedido = clientePedidoService.buscarPorId(clienteId, pedidoId);

        return PedidoFullModel.toModel(pedido);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public PedidoFullModel fazerPedido(@Valid @RequestBody PedidoCreateInput pedidoCreateInput, @PathVariable Long clienteId) {
        try {
            Pedido pedido = clientePedidoService.fazerPedido(pedidoCreateInput.toDomainModel(), clienteId);

            return PedidoFullModel.toModel(pedido);
        } catch (ProdutoNaoEncontradoException | EnderecoNaoEncontradoException e) {
            throw new SistemaException(e.getMessage());
        }
    }

    @PutMapping("/{pedidoId}/cancelamento")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void cancelarPedido(@PathVariable Long clienteId, @PathVariable Long pedidoId) {
        clientePedidoService.cancelarPedido(clienteId, pedidoId);
    }
}
