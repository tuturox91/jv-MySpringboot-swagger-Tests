--liquibase formatted sql
--changeset <sniklz>:<create-products-table>

INSERT INTO products (title, price) VALUES ('iPhone X', 1300);
INSERT INTO products (title, price) VALUES ('iPhone 9', 1000);
INSERT INTO products (title, price) VALUES ('iPhone 8', 900);
INSERT INTO products (title, price) VALUES ('iPhone 7', 800);
INSERT INTO products (title, price) VALUES ('iPhone 7', 830);
INSERT INTO products (title, price) VALUES ('iPhone 8', 940);
INSERT INTO products (title, price) VALUES ('iPhone 9', 1010);
INSERT INTO products (title, price) VALUES ('S22', 1300);
INSERT INTO products (title, price) VALUES ('S22', 1000);
INSERT INTO products (title, price) VALUES ('S21', 940);
INSERT INTO products (title, price) VALUES ('S20', 900);
INSERT INTO products (title, price) VALUES ('S19', 800);
INSERT INTO products (title, price) VALUES ('S18', 700);
INSERT INTO products (title, price) VALUES ('S18', 600);
INSERT INTO products (title, price) VALUES ('Redmi 9', 800);
INSERT INTO products (title, price) VALUES ('Redmi 9', 830);
INSERT INTO products (title, price) VALUES ('Redmi 9', 850);
INSERT INTO products (title, price) VALUES ('Redmi 8', 750);
INSERT INTO products (title, price) VALUES ('Redmi 7', 700);
INSERT INTO products (title, price) VALUES ('Redmi 6', 650);
INSERT INTO products (title, price) VALUES ('Redmi 5', 400);
INSERT INTO products (title, price) VALUES ('Redmi 4', 300);

--rollback DELETE FROM products;