-- Criando a tabela de Categorias
CREATE TABLE categorias (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

-- Criando a tabela de Ingredientes
CREATE TABLE ingredientes (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    categoria_id INT REFERENCES categorias(id) ON DELETE SET NULL,
    unidade_medida VARCHAR(20) NOT NULL, -- Ex: kg, litros, unidades
    preco DECIMAL(10,2) NOT NULL DEFAULT 0,
    quantidade_total DECIMAL(10,2) NOT NULL DEFAULT 0, -- Estoque atual do ingrediente
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Lotes de Ingredientes (para controle de validade)
CREATE TABLE lotes_ingredientes (
    id SERIAL PRIMARY KEY,
    ingrediente_id INT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL DEFAULT 0,
    data_validade DATE NOT NULL,
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Fornecedores
CREATE TABLE fornecedores (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100),
    criado_em TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Criando a tabela de Pedidos de Compra (ingredientes que o restaurante compra)
CREATE TABLE pedidos_compra (
    id SERIAL PRIMARY KEY,
    fornecedor_id INT REFERENCES fornecedores(id) ON DELETE SET NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL CHECK (status IN ('Pendente', 'Recebido', 'Cancelado'))
);

-- Criando a tabela de Itens do Pedido de Compra
CREATE TABLE itens_pedido_compra (
    id SERIAL PRIMARY KEY,
    pedido_id INT REFERENCES pedidos_compra(id) ON DELETE CASCADE,
    ingrediente_id INT REFERENCES ingredientes(id) ON DELETE CASCADE,
    quantidade DECIMAL(10,2) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL
);

-- Criando a tabela de Consumo de Ingredientes (quando um prato é feito, o sistema registra o consumo)
CREATE TABLE consumo_ingredientes (
    id SERIAL PRIMARY KEY,
    lote_id INT REFERENCES lotes_ingredientes(id) ON DELETE CASCADE,
    quantidade_utilizada DECIMAL(10,2) NOT NULL,
    data_consumo TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
