package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.CategoriaModel;
import com.joaoandrade.delivery.domain.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CategoriaModel toModel(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaModel.class);
    }
}
