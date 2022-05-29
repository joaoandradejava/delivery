package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.CategoriaInput;
import com.joaoandrade.delivery.domain.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoriaInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Categoria toDomainModel(CategoriaInput categoriaInput) {
        return modelMapper.map(categoriaInput, Categoria.class);
    }

    public void copyToDomainModel(CategoriaInput categoriaInput, Categoria categoria) {
        modelMapper.map(categoriaInput, categoria);
    }
}
