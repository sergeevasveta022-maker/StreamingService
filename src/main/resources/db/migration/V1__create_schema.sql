CREATE TABLE films (
       id BIGINT AUTO_INCREMENT PRIMARY KEY,
       film_id INTEGER UNIQUE NOT NULL,
       film_name VARCHAR(255),
       year INTEGER,
       rating DOUBLE PRECISION,
       description TEXT

 )ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;