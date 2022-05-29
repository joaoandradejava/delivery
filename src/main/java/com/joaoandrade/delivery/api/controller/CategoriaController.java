package com.joaoandrade.delivery.api.controller;

import com.joaoandrade.delivery.api.assembler.CategoriaModelAssembler;
import com.joaoandrade.delivery.api.disassembler.CategoriaInputDisassembler;
import com.joaoandrade.delivery.api.input.CategoriaInput;
import com.joaoandrade.delivery.api.model.CategoriaModel;
import com.joaoandrade.delivery.domain.exception.ErroInternoNoServidorException;
import com.joaoandrade.delivery.domain.model.Categoria;
import com.joaoandrade.delivery.domain.service.CrudCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CrudCategoriaService crudCategoriaService;

    @Autowired
    private CategoriaModelAssembler categoriaModelAssembler;

    @Autowired
    private CategoriaInputDisassembler categoriaInputDisassembler;

    @GetMapping
    public Page<CategoriaModel> buscarTodos(String nome, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Categoria> page = null;

        if (StringUtils.hasLength(nome)) {
            page = crudCategoriaService.buscarTodosPorNome(nome, pageable);
        } else {
            page = crudCategoriaService.buscarTodos(pageable);
        }

        return page.map(categoria -> categoriaModelAssembler.toModel(categoria));
    }

    @GetMapping("/{id}")
    public CategoriaModel buscarPorId(@PathVariable Long id) {
        Categoria categoria = crudCategoriaService.buscarPorId(id);

        return categoriaModelAssembler.toModel(categoria);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoriaModel inserir(@Valid @RequestBody CategoriaInput categoriaInput) {
        Categoria categoria = crudCategoriaService.inserir(categoriaInputDisassembler.toDomainModel(categoriaInput));

        return categoriaModelAssembler.toModel(categoria);
    }

    @PutMapping("/{id}")
    public CategoriaModel atualizar(@Valid @RequestBody CategoriaInput categoriaInput, @PathVariable Long id) {
        Categoria atual = crudCategoriaService.buscarPorId(id);
        categoriaInputDisassembler.copyToDomainModel(categoriaInput, atual);
        atual = crudCategoriaService.atualizar(atual);

        return categoriaModelAssembler.toModel(atual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        crudCategoriaService.deletarPorId(id);
    }

}
