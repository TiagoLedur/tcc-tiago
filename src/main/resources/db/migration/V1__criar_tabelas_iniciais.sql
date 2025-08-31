CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE ingredientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria_id BIGINT REFERENCES categorias(id) ON DELETE SET NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL DEFAULT 0,
    quantidade_total DECIMAL(10,2) NOT NULL DEFAULT 0,
    validade DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE fornecedores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100),
    cnpj VARCHAR(20) UNIQUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE entradas (
    id BIGSERIAL PRIMARY KEY,
    fornecedor_id BIGINT REFERENCES fornecedores(id) ON DELETE SET NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE itens_entrada (
    id BIGSERIAL PRIMARY KEY,
    entradas_id BIGINT REFERENCES entradas(id) ON DELETE CASCADE,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL
);

CREATE TABLE saidas (
    id BIGSERIAL PRIMARY KEY,
    data_saida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE itens_saida (
    id BIGSERIAL PRIMARY KEY,
    saida_id BIGINT REFERENCES saidas(id) ON DELETE CASCADE,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL
);