CREATE TABLE address (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255)
);

CREATE TABLE customer (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    address_id BIGINT,
    FOREIGN KEY (address_id) REFERENCES address(id)
);

INSERT INTO address (street, city, state) VALUES ('123 Main St', 'Cityville', 'CA');

INSERT INTO customer (first_name, last_name, address_id) VALUES ('John', 'Doe', 1);