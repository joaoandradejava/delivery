package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.EnderecoModel;
import com.joaoandrade.delivery.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EnderecoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public EnderecoModel toModel(Endereco endereco) {
        return modelMapper.map(endereco, EnderecoModel.class);
    }

}
