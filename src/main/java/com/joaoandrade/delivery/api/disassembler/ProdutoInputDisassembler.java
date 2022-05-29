package com.joaoandrade.delivery.api.disassembler;

import com.joaoandrade.delivery.api.input.ProdutoInput;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Produto toDomainModel(ProdutoInput produtoInput) {
        return modelMapper.map(produtoInput, Produto.class);
    }

    public void copyToDomainModel(ProdutoInput produtoInput, Produto produto) {
        produto.setCategoria(new Categoria());

        modelMapper.map(produtoInput, produto);
    }
}
