package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.AdministradorModelAssembler;
import com.joaoandrade.delivery.api.disassembler.AdministradorCreateInputDisassembler;
import com.joaoandrade.delivery.api.input.AdministradorCreateInput;
import com.joaoandrade.delivery.api.model.AdministradorModel;
import com.joaoandrade.delivery.domain.model.Administrador;
import com.joaoandrade.delivery.domain.service.crud.CrudAdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/administradores")
public class AdministradorController {

    @Autowired
    private AdministradorModelAssembler administradorModelAssembler;

    @Autowired
    private AdministradorCreateInputDisassembler administradorCreateInputDisassembler;

    @Autowired
    private CrudAdministradorService crudAdministradorService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public AdministradorModel inserir(@Valid @RequestBody AdministradorCreateInput administradorCreateInput) {
        Administrador administrador = crudAdministradorService.inserir(administradorCreateInputDisassembler.toDomainModel(administradorCreateInput));

        return administradorModelAssembler.toModel(administrador);
    }


}
