CREATE TABLE bank (
  name VARCHAR (255) NOT NULL PRIMARY KEY,
  balance DECIMAL (19, 2) NOT NULL
);

INSERT INTO bank(name, balance) VALUES('Peach', 100.00);
INSERT INTO bank(name, balance) VALUES('Berry', 100.00);