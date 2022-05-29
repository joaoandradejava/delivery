package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.ClienteCreateInput;
import com.joaoandrade.delivery.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteCreateInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Cliente toDomainModel(ClienteCreateInput clienteCreateInput) {
        return modelMapper.map(clienteCreateInput, Cliente.class);
    }
}
