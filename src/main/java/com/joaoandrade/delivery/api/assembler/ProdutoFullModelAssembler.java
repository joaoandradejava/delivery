package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.ProdutoFullModel;
import com.joaoandrade.delivery.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoFullModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoFullModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoFullModel.class);
    }
}
