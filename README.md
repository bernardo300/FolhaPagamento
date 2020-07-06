# FolhaPagamento
Simples projeto folha de pagamento curso extensao iTDD
Crie suas tabelas com as queries abaixo
```javascript
CREATE TABLE funcionarios(
     id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
     nome VARCHAR(30) NOT NULL,
     funcao VARCHAR(12),
     salario FLOAT
     );
CREATE TABLE gratificacoes(
     id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
     id_funcionario INTEGER NOT NULL,
     tipo VARCHAR(12),
     valor FLOAT
     );
    INSERT INTO funcionarios(nome, funcao, salario) VALUES('Joao','Gerente', 4000);
    INSERT INTO funcionarios(nome, funcao, salario) VALUES('Pedro','Funcionario', 2500);
    INSERT INTO funcionarios(nome, funcao, salario) VALUES('Henrique','Funcionario', 2500);
    INSERT INTO gratificacoes(id_funcionario, tipo, valor) VALUES(1,'Desempenho', 40);
    INSERT INTO gratificacoes(id_funcionario, tipo, valor) VALUES(2,'Hora Extra', 40);
    INSERT INTO gratificacoes(id_funcionario, tipo, valor) VALUES(3,'Desempenho', 25);
    
```
