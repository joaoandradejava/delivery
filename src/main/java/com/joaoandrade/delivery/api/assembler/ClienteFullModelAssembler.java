package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.ClienteFullModel;
import com.joaoandrade.delivery.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteFullModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteFullModel toModel(Cliente cliente) {
        return modelMapper.map(cliente, ClienteFullModel.class);
    }
}
