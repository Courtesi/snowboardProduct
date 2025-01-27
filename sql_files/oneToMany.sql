-- ALTER TABLE address
-- ADD COLUMN customer_id BIGINT,
-- ADD CONSTRAINT fk_customer
-- FOREIGN KEY (customer_id)
-- REFERENCES customer (id);

-- UPDATE address
-- SET customer_id = (SELECT id FROM customer WHERE id = address.id);


-- INSERT INTO customer (first_name, last_name) VALUES ('Jane', 'Smith');

-- INSERT INTO address (street, city, state, customer_id) VALUES
-- ('789 Pine St', 'CityC', 'SC', 1),
-- ('101 Elm St', 'CityD', 'SC', 2);

-- Run after testing thoroughly


-- ALTER TABLE customer
-- DROP FOREIGN KEY customer_ibfk_1;

-- ALTER TABLE customer
-- DROP COLUMN address_id;
