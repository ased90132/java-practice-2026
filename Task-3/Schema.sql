CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name CHAR(50) NOT NULL,           
    price INTEGER CHECK (price >= 0)   
);

INSERT INTO Product (name, price) VALUES 
('Ноутбук ASUS', 65000),
('Беспроводная мышь', 1500),
('Механическая клавиатура', 4500),
('Монитор 24 дюйма', 15000),
('Телевизор LG', 80000);

SELECT * FROM Product;
