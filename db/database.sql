CREATE SCHEMA ;

-- Tabla cliente
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);

-- Tabla especie
CREATE TABLE especie (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla raza
CREATE TABLE raza (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    especie_id INTEGER NOT NULL,
    FOREIGN KEY (especie_id) REFERENCES especie(id)
);

-- Tabla mascota
CREATE TABLE mascota (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    fecha_nacimiento DATE,
    raza_id INTEGER NOT NULL,
    cliente_id INTEGER NOT NULL,
    FOREIGN KEY (raza_id) REFERENCES raza(id),
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

-- Tabla especialidad
CREATE TABLE especialidad (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla veterinario
CREATE TABLE veterinario (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    cedula VARCHAR(20) UNIQUE NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100)
);
ALTER TABLE veterinario
ADD COLUMN sueldo NUMERIC(10, 2);


-- Tabla intermedia veterinario_especialidad (muchos a muchos)
CREATE TABLE veterinario_especialidad (
    id SERIAL PRIMARY KEY,
    veterinario_id INTEGER NOT NULL,
    especialidad_id INTEGER NOT NULL,
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(id),
    FOREIGN KEY (especialidad_id) REFERENCES especialidad(id),
    UNIQUE (veterinario_id, especialidad_id) -- evita duplicados
);

-- Tabla remedio
CREATE TABLE remedio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Tabla estado_cita
CREATE TABLE estado_cita (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL,
    descripcion TEXT
);

-- Tabla cita
CREATE TABLE cita (
    id SERIAL PRIMARY KEY,
    mascota_id INTEGER NOT NULL,
    fecha TIMESTAMP NOT NULL,
    sintomas TEXT,
    estado_id INTEGER NOT NULL,
    FOREIGN KEY (mascota_id) REFERENCES mascota(id),
    FOREIGN KEY (estado_id) REFERENCES estado_cita(id)
);

-- Tabla intermedia cita_veterinario
CREATE TABLE cita_veterinario (
    id SERIAL PRIMARY KEY,
    cita_id INTEGER NOT NULL,
    veterinario_id INTEGER NOT NULL,
    FOREIGN KEY (cita_id) REFERENCES cita(id),
    FOREIGN KEY (veterinario_id) REFERENCES veterinario(id),
    UNIQUE (cita_id, veterinario_id) -- evita duplicados
);


-- Tabla intermedia cita_remedio
CREATE TABLE cita_remedio (
    id SERIAL PRIMARY KEY,
    cita_id INTEGER NOT NULL,
    remedio_id INTEGER NOT NULL,
    dosis VARCHAR(100),
    FOREIGN KEY (cita_id) REFERENCES cita(id),
    FOREIGN KEY (remedio_id) REFERENCES remedio(id)
);

-- Facturacion
-- Agregar precio a cita
ALTER TABLE cita
ADD COLUMN precio NUMERIC(10, 2) DEFAULT 0.00;

-- Agregar precio a remedio
ALTER TABLE remedio
ADD COLUMN precio NUMERIC(10, 2) DEFAULT 0.00;


CREATE TABLE factura (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);


CREATE TABLE factura_detalle (
    id SERIAL PRIMARY KEY,
    factura_id INTEGER NOT NULL,
    cita_id INTEGER,
    remedio_id INTEGER,
    cantidad INTEGER DEFAULT 1,
    subtotal NUMERIC(10,2) NOT NULL,
    FOREIGN KEY (factura_id) REFERENCES factura(id),
    FOREIGN KEY (cita_id) REFERENCES cita(id),
    FOREIGN KEY (remedio_id) REFERENCES remedio(id),
    CHECK (
        (cita_id IS NOT NULL AND remedio_id IS NULL)
        OR (cita_id IS NULL AND remedio_id IS NOT NULL)
    )
);
