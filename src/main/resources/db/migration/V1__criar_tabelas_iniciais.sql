-- Criando a tabela de Categorias
CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Criando a tabela de Ingredientes
CREATE TABLE ingredientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria_id BIGINT REFERENCES categorias(id) ON DELETE SET NULL,
    unidade_medida VARCHAR(20) NOT NULL, -- Ex: kg, litros, unidades
    preco DECIMAL(10,2) NOT NULL DEFAULT 0,
    quantidade_total DECIMAL(10,2) NOT NULL DEFAULT 0, -- Estoque atual do ingrediente
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Lotes de Ingredientes (para controle de validade)
CREATE TABLE lotes_ingredientes (
    id BIGSERIAL PRIMARY KEY,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL DEFAULT 0,
    data_validade DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Fornecedores
CREATE TABLE fornecedores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100),
    cnpj VARCHAR(20) UNIQUE,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Pedidos de Compra (ingredientes que o restaurante compra)
CREATE TABLE pedidos_compra (
    id BIGSERIAL PRIMARY KEY,
    fornecedor_id BIGINT REFERENCES fornecedores(id) ON DELETE SET NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'RECEBIDO', 'CANCELADO'))
);

-- Criando a tabela de Itens do Pedido de Compra
CREATE TABLE itens_pedido_compra (
    id BIGSERIAL PRIMARY KEY,
    pedido_id BIGINT REFERENCES pedidos_compra(id) ON DELETE CASCADE,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL
);

-- Criando a tabela de Consumo de Ingredientes (quando um prato é feito, o sistema registra o consumo)
CREATE TABLE consumo_ingredientes (
    id BIGSERIAL PRIMARY KEY,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade_utilizada DECIMAL(10,2) NOT NULL,
    data_consumo TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
