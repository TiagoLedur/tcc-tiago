CREATE TABLE movimentacoes_estoque (
    id BIGSERIAL PRIMARY KEY,
    ingrediente_id BIGINT REFERENCES ingredientes(id) ON DELETE CASCADE,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA')),
    quantidade DECIMAL(10,2) NOT NULL,
    observacao VARCHAR(500),
    data_movimentacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);