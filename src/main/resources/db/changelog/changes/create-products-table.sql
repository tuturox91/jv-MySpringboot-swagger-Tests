--liquibase formatted sql
--changeset <sniklz>:<create-products-table>
CREATE TABLE IF NOT EXISTS products
(
    id bigint auto_increment,
    title varchar(255) not null,
    price decimal      not null,
    CONSTRAINT product_pl PRIMARY KEY (id)
);

--rollback DROP TABLE products;