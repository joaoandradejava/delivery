package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.AdministradorCreateInput;
import com.joaoandrade.delivery.domain.model.Administrador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministradorCreateInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Administrador toDomainModel(AdministradorCreateInput administradorCreateInput) {
        return modelMapper.map(administradorCreateInput, Administrador.class);
    }
}
