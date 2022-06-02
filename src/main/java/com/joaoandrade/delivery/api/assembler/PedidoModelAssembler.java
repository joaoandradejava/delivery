package com.joaoandrade.delivery.api.assembler;

import com.joaoandrade.delivery.api.model.ItemPedidoModel;
import com.joaoandrade.delivery.api.model.PedidoModel;
import com.joaoandrade.delivery.domain.model.ItemPedido;
import com.joaoandrade.delivery.domain.model.Pedido;
import com.joaoandrade.delivery.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PedidoModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoModel toModel(Pedido pedido) {
        PedidoModel pedidoModel = modelMapper.map(pedido, PedidoModel.class);
        pedidoModel.setItens(new ArrayList<>());

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getId().getProduto();
            ItemPedidoModel itemPedidoModel = new ItemPedidoModel();
            itemPedidoModel.setId(produto.getId());
            itemPedidoModel.setNome(produto.getNome());
            itemPedidoModel.setQuantidade(item.getQuantidade());
            itemPedidoModel.setPrecoUnitario(item.getPrecoUnitario());
            itemPedidoModel.setValorTotal(item.getValorTotal());

            pedidoModel.getItens().add(itemPedidoModel);
        }

        return pedidoModel;
    }
}
