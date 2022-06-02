package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.EnderecoModelAssembler;
import com.joaoandrade.delivery.api.disassembler.EnderecoInputDisassembler;
import com.joaoandrade.delivery.api.input.EnderecoInput;
import com.joaoandrade.delivery.api.model.EnderecoModel;
import com.joaoandrade.delivery.domain.model.Cliente;
import com.joaoandrade.delivery.domain.model.Endereco;
import com.joaoandrade.delivery.domain.service.crud.CrudClienteEnderecoService;
import com.joaoandrade.delivery.domain.service.crud.CrudClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes/{clienteId}/enderecos")
public class ClienteEnderecoController {

    @Autowired
    private CrudClienteEnderecoService crudClienteEnderecoService;


    @Autowired
    private EnderecoModelAssembler enderecoModelAssembler;
    @Autowired
    private EnderecoInputDisassembler enderecoInputDisassembler;

    @Autowired
    private CrudClienteService crudClienteService;

    @GetMapping
    public List<EnderecoModel> buscarTodos(@PathVariable Long clienteId) {
        Cliente cliente = crudClienteService.buscarPorId(clienteId);

        return enderecoModelAssembler.toCollectionModel(cliente.getEnderecos());
    }

    @GetMapping("{enderecoId}")
    public EnderecoModel buscarEnderecoDoCliente(@PathVariable Long clienteId, @PathVariable Long enderecoId) {
        Endereco endereco = crudClienteEnderecoService.buscarEnderecoDoCliente(clienteId, enderecoId);

        return enderecoModelAssembler.toModel(endereco);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public EnderecoModel inserir(@Valid @RequestBody EnderecoInput enderecoInput, @PathVariable Long clienteId) {
        Endereco endereco = crudClienteEnderecoService.inserir(enderecoInputDisassembler.toDomainModel(enderecoInput), clienteId);

        return enderecoModelAssembler.toModel(endereco);
    }

    @PutMapping("/{enderecoId}")
    public EnderecoModel atualizar(@Valid @RequestBody EnderecoInput enderecoInput, @PathVariable Long clienteId, @PathVariable Long enderecoId) {
        Endereco endereco = crudClienteEnderecoService.buscarEnderecoDoCliente(clienteId, enderecoId);
        enderecoInputDisassembler.copyToDomainModel(enderecoInput, endereco);
        endereco = crudClienteEnderecoService.atualizar(endereco);

        return enderecoModelAssembler.toModel(endereco);
    }

    @DeleteMapping("{enderecoId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarEnderecoDoCliente(@PathVariable Long clienteId, @PathVariable Long enderecoId) {
        crudClienteEnderecoService.deletarEnderecoDoCliente(clienteId, enderecoId);
    }

}
