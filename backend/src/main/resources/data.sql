-- TABELA PESSOAS
INSERT INTO pessoas ( tipo_pessoa, id, cpf, data_criacao, email, nome, senha ) VALUES ('tecnico', 1, '19773136043', '2021-06-20', 'ze@email.com', 'Zé', '123');
INSERT INTO pessoas ( tipo_pessoa, id, cpf, data_criacao, email, nome, senha ) VALUES ('cliente', 2, '23573853404', '2021-06-20', 'fulano@email.com', 'Fulano', '123');

-- TABELA CHAMADO
INSERT INTO chamados ( id, data_abertura, data_fechamento, observacao, prioridade, status, titulo, cliente_id, tecnico_id) VALUES ( 1, '2021-06-20', null, 'Primeiro Chamado', 1, 1, 'Chamado 01', 2, 1 );