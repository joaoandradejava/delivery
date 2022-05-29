package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.ClienteModel;
import com.joaoandrade.delivery.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteModel.class);
    }
}
