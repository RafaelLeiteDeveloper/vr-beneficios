CREATE TABLE IF NOT EXISTS cartao (
    numero_cartao INT AUTO_INCREMENT PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    saldo decimal(15,2),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;                                                                                                                                                                                                                           CREATE TABLE IF NOT EXISTS cartao (
    numero_cartao INT AUTO_INCREMENT PRIMARY KEY,
    senha VARCHAR(255) NOT NULL,
    saldo decimal(15,2),
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)  ENGINE=INNODB;