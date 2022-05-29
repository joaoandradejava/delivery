package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.AdministradorModel;
import com.joaoandrade.delivery.domain.model.Administrador;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministradorModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public AdministradorModel toModel(Administrador administrador) {
        return modelMapper.map(administrador, AdministradorModel.class);
    }
}
