-- TABLA DE BOLETOS
CREATE TABLE tickets (
    id_ticket SERIAL PRIMARY KEY,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    seat_location VARCHAR(20),
    status VARCHAR(20) CHECK (estado IN ('Disponible', 'Vendido')) NOT NULL
);

-- TABLA DE VENTAS (PARA CREAR EL REPORTE)
CREATE TABLE sales (
    sale_id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    sale_datetime TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total_amount DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (sale_id)
);

-- TABLA QUE ALMACENA LOS BOLETOS VENDIDOS (combina ventas y tickets)
CREATE TABLE sold_tickets (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    sale_id BIGINT UNSIGNED NOT NULL,
    ticket_id BIGINT UNSIGNED NOT NULL,
    category VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    seat_number VARCHAR(20),
    PRIMARY KEY (id),
    FOREIGN KEY (sale_id) REFERENCES sales(sale_id),
    FOREIGN KEY (ticket_id) REFERENCES tickets(ticket_id)
);
