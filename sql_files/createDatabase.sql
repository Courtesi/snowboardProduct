use nobsv2;

CREATE TABLE product(
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(255),
 description VARCHAR(255),
 price DOUBLE
);

INSERT INTO product (name, description, price) VALUES 
('Apple iPhone 14', 'Latest model with 256GB storage A15 Bionic chip, and improved camera system', 999.99),
('Samsung Galaxy S21', 'Flagship phone with 128GB storage, Exynos 2100, and triple camera setup', 799.99),
('Sony HW-1000XM4', 'Wireless noise-cancelling headphones with up to 30 hours of battery life', 3499.99);

select * from product;