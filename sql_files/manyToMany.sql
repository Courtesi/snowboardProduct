use nobsv2;

-- CREATE TABLE customer_address (
--     customer_id BIGINT,
--     address_id BIGINT,
--     PRIMARY KEY (customer_id, address_id),
--     FOREIGN KEY (customer_id) REFERENCES customer(id),
--     FOREIGN KEY (address_id) REFERENCES address(id)
-- );

-- INSERT INTO customer_address (customer_id, address_id)
-- SELECT customer_id, id as address_id
-- FROM address;

-- EXECUTE AFTER TESTING THOROUGHLY --

-- ALTER TABLE address
-- DROP FOREIGN KEY fk_customer;

-- ALTER TABLE address
-- DROP COLUMN customer_id;