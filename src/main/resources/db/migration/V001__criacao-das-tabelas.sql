

    create table administrador (
       id int8 not null,
        primary key (id)
    );


    create table categoria (
       id int8 generated by default as identity,
        nome varchar(255) not null,
        primary key (id)
    );

    create table cliente (
       cpf varchar(11),
        telefone varchar(11),
        id int8 not null,
        primary key (id)
    );

    create table endereco (
       id int8 generated by default as identity,
        bairro varchar(255),
        cep varchar(255),
        cidade varchar(255),
        complemento varchar(255),
        estado varchar(255),
        numero varchar(255),
        rua varchar(255),
        cliente_id int8,
        primary key (id)
    );

    create table imagem_produto (
       id int8 generated by default as identity,
        imagem text,
        produto_id int8,
        primary key (id)
    );


    create table item_pedido (
       porcentagem_desconto int4,
        preco_unitario numeric(19, 2) not null,
        quantidade int4 not null,
        pedido_id int8 not null,
        produto_id int8 not null,
        primary key (pedido_id, produto_id)
    );

    create table pedido (
       id int8 generated by default as identity,
        data timestamp not null,
        data_cancelamento timestamp,
        data_concluido timestamp,
        forma_pagamento varchar(21),
        status varchar(22),
        cliente_id int8 not null,
        endereco_de_entrega_id int8 not null,
        primary key (id)
    );


    create table perfil (
       usuario_id int8 not null,
        perfil_usuario varchar(255)
    );


    create table produto (
       id int8 generated by default as identity,
        descricao oid,
        is_tem_desconto boolean,
        is_tem_estoque boolean,
        nome varchar(255) not null,
        porcentagem_desconto int4,
        preco numeric(19, 2) not null,
        quantidade_estoque int4,
        categoria_id int8,
        primary key (id)
    );


    create table usuario (
       id int8 generated by default as identity,
        data_atualizacao timestamp,
        data_criacao timestamp,
        email varchar(255) not null,
        nome varchar(255) not null,
        senha varchar(255) not null,
        primary key (id)
    );


    alter table if exists administrador
       add constraint fk_administrador_id
       foreign key (id)
       references usuario;

    alter table if exists cliente
       add constraint fk_cliente_id
       foreign key (id)
       references usuario;

    alter table if exists endereco
       add constraint fk_endereco_cliente_id
       foreign key (cliente_id)
       references cliente;

    alter table if exists imagem_produto
       add constraint fk_imagem_produto_produto_id
       foreign key (produto_id)
       references produto;

    alter table if exists item_pedido
       add constraint fk_item_pedido_pedido_id
       foreign key (pedido_id)
       references pedido;

    alter table if exists item_pedido
       add constraint fk_item_pedido_produto_id
       foreign key (produto_id)
       references produto;

    alter table if exists pedido
       add constraint fk_pedido_cliente_id
       foreign key (cliente_id)
       references cliente;

    alter table if exists pedido
       add constraint fk_pedido_endereco_de_entrega_id
       foreign key (endereco_de_entrega_id)
       references endereco;

    alter table if exists perfil
       add constraint fk_perfil_usuario_id
       foreign key (usuario_id)
       references usuario;

    alter table if exists produto
       add constraint fk_produto_categoria_id
       foreign key (categoria_id)
       references categoria;

    alter table if exists categoria
       drop constraint if exists categoria_nome_unique;

	alter table if exists categoria
       add constraint categoria_nome_unique unique (nome);

    alter table if exists cliente
       drop constraint if exists cliente_cpf_unique;

    alter table if exists cliente
       add constraint cliente_cpf_unique unique (cpf);

    alter table if exists usuario
       drop constraint if exists usuario_email_unique;

    alter table if exists usuario
       add constraint usuario_email_unique unique (email);
