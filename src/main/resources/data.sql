INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Joao Carlos', '9822334466', 'Rua da Felicidade, 20');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Pedro Souza', '9855334422', 'Rua da Alegria, 10');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Maria dos Remédios', '9833334567', 'Rua da Paz, 40');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Joao da Silva', '9822345678', 'Rua da Fantasia, 15');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Thiago Souza', '9855334422', 'Rua da Alegria, 10');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Mateus dos Remédios', '9833334567', 'Rua da Paz, 40');
INSERT INTO CLIENTE(nome, telefone, endereco) VALUES('Maria Vitória', '9822345678', 'Rua da Fantasia, 15');


INSERT INTO CIDADE(nome, taxa, uf) VALUES('São Luís', 60, 'MA');
INSERT INTO CIDADE(nome, taxa, uf) VALUES('São José de Ribamar', 70, 'MA');
INSERT INTO CIDADE(nome, taxa, uf) VALUES('Teresina', 50, 'PI');

INSERT INTO FRETE(descricao, peso, valor, cidade_id, cliente_id) VALUES ('Produtos Eletrônicos', 200, 440, 1, 2);
INSERT INTO FRETE(descricao, peso, valor, cidade_id, cliente_id) VALUES ('Alimentos nao perecíveis', 900, 200, 2, 3);
INSERT INTO FRETE(descricao, peso, valor, cidade_id, cliente_id) VALUES ('Produtos de Limpeza', 700, 200, 2, 4);
INSERT INTO FRETE(descricao, peso, valor, cidade_id, cliente_id) VALUES ('Carne Resfriada', 600, 200, 2, 2);
INSERT INTO FRETE(descricao, peso, valor, cidade_id, cliente_id) VALUES ('Medicamentos', 100, 200, 2, 4);
