CREATE TABLE reports (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       report_id UUID NOT NULL DEFAULT gen_random_uuid() UNIQUE,
                       email VARCHAR(255) NOT NULL ,
                       status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
                       created_at TIMESTAMP,
                       updated_at TIMESTAMP,
                       error_message TEXT
);