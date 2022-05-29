package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.EnderecoInput;
import com.joaoandrade.delivery.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Endereco toDomainModel(EnderecoInput enderecoInput) {
        return modelMapper.map(enderecoInput, Endereco.class);
    }

    public void copyToDomainModel(EnderecoInput enderecoInput, Endereco endereco) {
        modelMapper.map(enderecoInput, endereco);
    }
}
