-- Tabela cliente
CREATE TABLE cliente (
    cpf VARCHAR(11) NOT NULL,
    nome VARCHAR(70),
    telefone VARCHAR(11),
    PRIMARY KEY (cpf)
);

-- Tabela pedido
CREATE TABLE pedido (
    codigo SERIAL,
    cpf_cliente VARCHAR(11) NOT NULL,
    data_pedido TIMESTAMP,
    valor DOUBLE PRECISION,
    tipo_pagamento VARCHAR(15),
    status_pagamento VARCHAR(15),
    PRIMARY KEY (codigo),
    FOREIGN KEY (cpf_cliente) REFERENCES cliente(cpf)
);

-- Tabela entrega
CREATE TABLE entrega (
    codigo SERIAL,
    cod_pedido INT NOT NULL,
    data_entrega TIMESTAMP,
    endereco VARCHAR(50),
    status_entrega VARCHAR(15),
    codigo_seguranca VARCHAR(6),
    PRIMARY KEY (codigo),
    FOREIGN KEY (cod_pedido) REFERENCES pedido(codigo)
);

-- Tabela produto
CREATE TABLE produto (
    codigo SERIAL,
    nome VARCHAR(25),
    descricao VARCHAR(255),
    preco_unidade DOUBLE PRECISION,
    quantidade INT,
    ativo BOOLEAN,
    url_image VARCHAR(255),
    PRIMARY KEY (codigo)
);

-- Tabela da relação pedido-produto
CREATE TABLE pedido_produto (
    codigo SERIAL,
    cod_produto INT NOT NULL,
    cod_pedido INT NOT NULL,
    quantidade INT,
    preco_unidade DOUBLE PRECISION,
    nome_produto VARCHAR(25),
    PRIMARY KEY (codigo),
    FOREIGN KEY (cod_produto) REFERENCES produto(codigo),
    FOREIGN KEY (cod_pedido) REFERENCES pedido(codigo)
);
