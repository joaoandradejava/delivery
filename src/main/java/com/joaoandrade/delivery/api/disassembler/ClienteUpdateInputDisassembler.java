package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.ClienteUpdateInput;
import com.joaoandrade.delivery.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteUpdateInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public void copyToDomainModel(ClienteUpdateInput clienteUpdateInput, Cliente cliente) {
        modelMapper.map(clienteUpdateInput, cliente);
    }
}
