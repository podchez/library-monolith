CREATE TABLE IF NOT EXISTS account
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50)          NOT NULL,
    last_name  VARCHAR(50)          NOT NULL,
    email      VARCHAR(100)         NOT NULL UNIQUE,
    password   VARCHAR(100)         NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE IF NOT EXISTS role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS account_role
(
    account_id BIGINT NOT NULL REFERENCES account (id),
    role_id    INT    NOT NULL REFERENCES role (id),
    PRIMARY KEY (account_id, role_id)
);

CREATE TABLE IF NOT EXISTS genre
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS author
(
    id        BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS book
(
    id             BIGSERIAL PRIMARY KEY,
    title          VARCHAR(255)         NOT NULL,
    pages          INT                  NOT NULL,
    price_in_cents BIGINT               NOT NULL,
    is_available   BOOLEAN DEFAULT TRUE NOT NULL,
    genre_id       INT                  NOT NULL REFERENCES genre (id),
    author_id      BIGINT               NOT NULL REFERENCES author (id)
);
