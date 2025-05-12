CREATE TABLE colaboradores (
    id NUMBER(19) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR2(100) NOT NULL,
    genero VARCHAR2(50),
    raca VARCHAR2(50),
    pcd NUMBER(1) DEFAULT 0,
    data_contratacao DATE DEFAULT SYSDATE,
    treinamento_diversidade NUMBER(1) DEFAULT 0,
    cargo VARCHAR2(100),
    departamento VARCHAR2(100),
    data_criacao TIMESTAMP DEFAULT SYSTIMESTAMP,
    data_atualizacao TIMESTAMP DEFAULT SYSTIMESTAMP
);

-- Create index for faster searches
CREATE INDEX idx_colaboradores_genero ON colaboradores(genero);
CREATE INDEX idx_colaboradores_raca ON colaboradores(raca);
CREATE INDEX idx_colaboradores_pcd ON colaboradores(pcd);
CREATE INDEX idx_colaboradores_treinamento ON colaboradores(treinamento_diversidade);

-- Add comments to columns
COMMENT ON TABLE colaboradores IS 'Tabela de colaboradores para gestão de diversidade e inclusão';
COMMENT ON COLUMN colaboradores.nome IS 'Nome completo do colaborador';
COMMENT ON COLUMN colaboradores.genero IS 'Gênero do colaborador';
COMMENT ON COLUMN colaboradores.raca IS 'Raça/etnia do colaborador';
COMMENT ON COLUMN colaboradores.pcd IS 'Indica se o colaborador é pessoa com deficiência (1) ou não (0)';
COMMENT ON COLUMN colaboradores.data_contratacao IS 'Data de contratação do colaborador';
COMMENT ON COLUMN colaboradores.treinamento_diversidade IS 'Indica se o colaborador completou o treinamento de diversidade (1) ou não (0)';
COMMENT ON COLUMN colaboradores.cargo IS 'Cargo atual do colaborador';
COMMENT ON COLUMN colaboradores.departamento IS 'Departamento do colaborador'; 