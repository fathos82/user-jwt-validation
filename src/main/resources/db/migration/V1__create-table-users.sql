CREATE TABLE "users"
(
    id  UUID PRIMARY KEY,
    username     VARCHAR(100) NOT NULL UNIQUE,
    email    VARCHAR(100) NOT NULL ,
    password VARCHAR(255) NOT NULL
);