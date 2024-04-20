CREATE TABLE IF NOT EXISTS neurotechClients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT NOT NULL,
    income DOUBLE NOT NULL,
    creditModality VARCHAR(20)
);

INSERT INTO neurotechClients (name, age, income, creditModality) VALUES ('Bruno', 25, 5000, 'JUROS_FIXOS');
INSERT INTO neurotechClients (name, age, income, creditModality) VALUES ('Renata', 35, 12000, 'JUROS_VARIAVEIS');
INSERT INTO neurotechClients (name, age, income, creditModality) VALUES ('Alberto', 85, 16000, 'JUROS_VARIAVEIS');