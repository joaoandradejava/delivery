package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.CategoriaModelAssembler;
import com.joaoandrade.delivery.api.input.CategoriaInput;
import com.joaoandrade.delivery.api.model.CategoriaModel;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.service.CrudCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CrudCategoriaService crudCategoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    @GetMapping
    public Page<CategoriaModel> buscarTodos(Pageable pageable) {
        Page<Categoria> page = crudCategoriaService.buscarTodos(pageable);

        return page.map(categoria -> categoriaModelAssembler.toModel(categoria));
    }

    @GetMapping("/{id}")
    public CategoriaModel buscarPorId(@PathVariable Long id) {
        Categoria categoria = crudCategoriaService.buscarPorId(id);

        return categoriaModelAssembler.toModel(categoria);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public void inserir(@Valid @RequestBody CategoriaInput categoriaInput){

    }

}
