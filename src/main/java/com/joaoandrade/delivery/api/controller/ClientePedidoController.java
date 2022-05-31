package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.input.PedidoCreateInput;
import com.joaoandrade.delivery.domain.exception.EnderecoNaoEncontradoException;
import com.joaoandrade.delivery.domain.exception.ProdutoNaoEncontradoException;
import com.joaoandrade.delivery.domain.exception.SistemaException;
import com.joaoandrade.delivery.domain.service.ClientePedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes/{clienteId}/pedidos")
public class ClientePedidoController {


    @Autowired
    private ClientePedidoService clientePedidoService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void fazerPedido(@Valid @RequestBody PedidoCreateInput pedidoCreateInput, @PathVariable Long clienteId) {
        try {
            clientePedidoService.fazerPedido(pedidoCreateInput.toDomainModel(), clienteId);
        } catch (ProdutoNaoEncontradoException | EnderecoNaoEncontradoException e) {
            throw new SistemaException(e.getMessage());
        }
    }
}
