package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.ClienteFullModelAssembler;
import com.joaoandrade.delivery.api.assembler.ClienteModelAssembler;
import com.joaoandrade.delivery.api.disassembler.ClienteCreateInputDisassembler;
import com.joaoandrade.delivery.api.disassembler.ClienteUpdateInputDisassembler;
import com.joaoandrade.delivery.api.input.ClienteCreateInput;
import com.joaoandrade.delivery.api.input.ClienteUpdateInput;
import com.joaoandrade.delivery.api.model.ClienteFullModel;
import com.joaoandrade.delivery.api.model.ClienteModel;
import com.joaoandrade.delivery.domain.filter.ClienteFilter;
import com.joaoandrade.delivery.domain.model.Cliente;
import com.joaoandrade.delivery.domain.service.CrudClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private CrudClienteService crudClienteService;

    @Autowired
    private ClienteFullModelAssembler clienteFullModelAssembler;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;

    @Autowired
    private ClienteCreateInputDisassembler clienteCreateInputDisassembler;

    @Autowired
    private ClienteUpdateInputDisassembler clienteUpdateInputDisassembler;

    @PreAuthorize("hasAnyRole('ADMINISTRADOR')")
    @GetMapping
    public Page<ClienteModel> buscarTodos(ClienteFilter clienteFilter, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Cliente> page = crudClienteService.buscarTodos(clienteFilter, pageable);

        return page.map(cliente -> clienteModelAssembler.toModel(cliente));
    }

    @GetMapping("/{id}")
    public ClienteFullModel buscarPorId(@PathVariable Long id) {
        Cliente cliente = crudClienteService.buscarPorId(id);

        return clienteFullModelAssembler.toModel(cliente);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ClienteFullModel inserir(@Valid @RequestBody ClienteCreateInput clienteCreateInput) {
        Cliente cliente = crudClienteService.inserir(clienteCreateInputDisassembler.toDomainModel(clienteCreateInput));

        return clienteFullModelAssembler.toModel(cliente);
    }


    @PutMapping("/{id}")
    public ClienteFullModel atualizar(@Valid @RequestBody ClienteUpdateInput clienteUpdateInput, @PathVariable Long id) {
        Cliente atual = crudClienteService.buscarPorId(id);
        clienteUpdateInputDisassembler.copyToDomainModel(clienteUpdateInput, atual);
        atual = crudClienteService.atualizar(atual);

        return clienteFullModelAssembler.toModel(atual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        crudClienteService.deletarPorId(id);
    }

}
