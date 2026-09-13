CREATE TABLE films (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       film_id INTEGER UNIQUE NOT NULL,
                       film_name VARCHAR(255),
                       year INTEGER,
                       rating DOUBLE PRECISION,
                       description TEXT
);