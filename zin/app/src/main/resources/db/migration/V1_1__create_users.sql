CREATE TABLE users
(
    id       BIGINT       NOT NULL,
    username VARCHAR(15)  NOT NULL,
    street   VARCHAR(255) NOT NULL,
    zipcode  VARCHAR(5)   NOT NULL,
    city     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT UK_USER_USERNAME UNIQUE (username);
