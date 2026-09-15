CREATE TABLE usuario (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    contrasena VARCHAR(255) NOT NULL
);

CREATE TABLE vehiculo (
    placa VARCHAR(10) PRIMARY KEY,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    version VARCHAR(50),
    color VARCHAR(30),
    num_puestos INTEGER,
    num_puertas INTEGER,
    combustible VARCHAR(30),
    kilometros DOUBLE PRECISION,
    cilindraje DOUBLE PRECISION,
    categoria VARCHAR(50)
);