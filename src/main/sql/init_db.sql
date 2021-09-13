ALTER TABLE IF EXISTS ONLY public.shopping_cart
    DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_for_order
    DROP CONSTRAINT IF EXISTS fk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS pk_product_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.product
    DROP CONSTRAINT IF EXISTS fk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.category
    DROP CONSTRAINT IF EXISTS pk_category_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.supplier
    DROP CONSTRAINT IF EXISTS pk_supplier_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.billing_info
    DROP CONSTRAINT IF EXISTS pk_billing_info_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.billing_info
    DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.products_for_order
    DROP CONSTRAINT IF EXISTS fk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.order
    DROP CONSTRAINT IF EXISTS pk_order_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.order
    DROP CONSTRAINT IF EXISTS fk_user_id CASCADE;
ALTER TABLE IF EXISTS ONLY public.user
    DROP CONSTRAINT IF EXISTS pk_user_id CASCADE;

DROP TABLE IF EXISTS public.category;
CREATE TABLE category
(
    id          serial NOT NULL,
    department  varchar(30),
    name        varchar(30),
    description text
);

DROP TABLE IF EXISTS public.supplier;
CREATE TABLE supplier
(
    id          serial NOT NULL,
    name        varchar(30),
    description text
);

DROP TABLE IF EXISTS public.product;
CREATE TABLE product
(
    id               serial  NOT NULL,
    default_price    integer,
    default_currency varchar(3),
    category_id      integer NOT NULL,
    supplier_id      integer NOT NULL,
    name             varchar(30),
    description      text
);

DROP TABLE IF EXISTS public.user;
CREATE TABLE "user"
(
    id        serial NOT NULL,
    email     varchar(50),
    password  varchar(50),
    timestamp timestamp without time zone
);

DROP TABLE IF EXISTS public.shopping_cart;
CREATE TABLE shopping_cart
(
    product_id integer NOT NULL,
    quantity   integer
);

DROP TABLE IF EXISTS public.order;
CREATE TABLE "order"
(
    id           serial  NOT NULL,
    user_id      integer NOT NULL,
    total_price  integer,
    order_status varchar(15),
    order_date   timestamp without time zone
);

DROP TABLE IF EXISTS public.products_for_order;
CREATE TABLE "products_for_order"
(
    product_id integer NOT NULL,
    order_id   integer NOT NULL
);

DROP TABLE IF EXISTS public.billing_info;
CREATE TABLE billing_info
(
    id      serial  NOT NULL,
    user_id integer NOT NULL,
    name    varchar(30),
    email   varchar(50),
    address varchar(50),
    city    varchar(30),
    state   varchar(15),
    zip     integer
);


ALTER TABLE ONLY category
    ADD CONSTRAINT pk_category_id PRIMARY KEY (id);

ALTER TABLE ONLY supplier
    ADD CONSTRAINT pk_supplier_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT pk_product_id PRIMARY KEY (id);

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY product
    ADD CONSTRAINT fk_supplier_id FOREIGN KEY (supplier_id) REFERENCES supplier (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY "user"
    ADD CONSTRAINT pk_user_id PRIMARY KEY (id);

ALTER TABLE ONLY shopping_cart
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY "order"
    ADD CONSTRAINT pk_order_id PRIMARY KEY (id);

ALTER TABLE ONLY "order"
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY "products_for_order"
    ADD CONSTRAINT fk_product_id FOREIGN KEY (product_id) REFERENCES product (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY "products_for_order"
    ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES "order" (id) ON
        DELETE
        CASCADE;

ALTER TABLE ONLY billing_info
    ADD CONSTRAINT pk_billing_info_id PRIMARY KEY (id);

ALTER TABLE ONLY billing_info
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES "user" (id) ON
        DELETE
        CASCADE;

INSERT INTO CATEGORY
VALUES (1, 'Programming', 'Personal', 'Debugging and script writing');
INSERT INTO CATEGORY
VALUES (2, 'Project building', 'Team', 'Complex project building');
SELECT pg_catalog.setval('category_id_seq', 3, true);

INSERT INTO SUPPLIER
VALUES (1, 'Code Cool', 'Programing School and Headhunter company');
INSERT INTO SUPPLIER
VALUES (2, 'Green Fox', 'Programing School');
SELECT pg_catalog.setval('supplier_id_seq', 3, true);

INSERT INTO PRODUCT
VALUES (1, 250, 'USD', 1, 1, 'Peti', '');
INSERT INTO PRODUCT
VALUES (2, 150, 'USD', 1, 1, 'Saz', '');
INSERT INTO PRODUCT
VALUES (3, 100, 'USD', 1, 1, 'Mate', '');
INSERT INTO PRODUCT
VALUES (4, 100, 'USD', 1, 1, 'Ichy', '');
INSERT INTO PRODUCT
VALUES (5, 250, 'USD', 1, 1, 'Balazs', '');
INSERT INTO PRODUCT
VALUES (6, 5, 'USD', 1, 2, 'Martin', '');
INSERT INTO PRODUCT
VALUES (7, 300, 'USD', 1, 2, 'Eric Cartman', '');
INSERT INTO PRODUCT
VALUES (8, 10, 'USD', 1, 2, 'Kenny McCormick', '');
INSERT INTO PRODUCT
VALUES (9, 800, 'USD', 2, 1, 'ProgramING shop', '');
INSERT INTO PRODUCT
VALUES (10, 500, 'USD', 2, 2, 'No IDEa', '');
SELECT pg_catalog.setval('product_id_seq', 11, true);
