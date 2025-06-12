select * from cliente;

INSERT INTO especie VALUES (1, 'Perro');
INSERT INTO especie VALUES (2, 'Gato');
INSERT INTO especie VALUES (3, 'Conejo');
INSERT INTO especie VALUES (4, 'Hámster');
INSERT INTO especie VALUES (5, 'Ave');
INSERT INTO especie VALUES (6, 'Tortuga');
INSERT INTO especie VALUES (7, 'Pez');
INSERT INTO especie VALUES (8, 'Cobaya');
INSERT INTO especie VALUES (9, 'Hurón');
INSERT INTO especie VALUES (10, 'Erizo');

select * from especie;

-- Perro (especie_id = 1)
INSERT INTO raza (id, nombre, especie_id) VALUES (1, 'Labrador Retriever', 1);
INSERT INTO raza (id, nombre, especie_id) VALUES (2, 'Bulldog', 1);
INSERT INTO raza (id, nombre, especie_id) VALUES (3, 'Poodle', 1);
INSERT INTO raza (id, nombre, especie_id) VALUES (4, 'Chihuahua', 1);
INSERT INTO raza (id, nombre, especie_id) VALUES (5, 'Persa', 2);
INSERT INTO raza (id, nombre, especie_id) VALUES (6, 'Siamés', 2);
INSERT INTO raza (id, nombre, especie_id) VALUES (7, 'Maine Coon', 2);
INSERT INTO raza (id, nombre, especie_id) VALUES (8, 'Enano Holandés', 3);
INSERT INTO raza (id, nombre, especie_id) VALUES (9, 'Lop', 3);
INSERT INTO raza (id, nombre, especie_id) VALUES (10, 'Sirio', 4);
INSERT INTO raza (id, nombre, especie_id) VALUES (11, 'Roborowski', 4);
INSERT INTO raza (id, nombre, especie_id) VALUES (12, 'Periquito', 5);
INSERT INTO raza (id, nombre, especie_id) VALUES (13, 'Canario', 5);
INSERT INTO raza (id, nombre, especie_id) VALUES (14, 'Cacatúa', 5);
INSERT INTO raza (id, nombre, especie_id) VALUES (15, 'Tortuga de orejas rojas', 6);
INSERT INTO raza (id, nombre, especie_id) VALUES (16, 'Tortuga de tierra', 6);
INSERT INTO raza (id, nombre, especie_id) VALUES (17, 'Betta', 7);
INSERT INTO raza (id, nombre, especie_id) VALUES (18, 'Goldfish', 7);
INSERT INTO raza (id, nombre, especie_id) VALUES (19, 'Abisinia', 8);
INSERT INTO raza (id, nombre, especie_id) VALUES (20, 'Peruana', 8);
INSERT INTO raza (id, nombre, especie_id) VALUES (21, 'Estándar', 9);
INSERT INTO raza (id, nombre, especie_id) VALUES (22, 'Africano Pigmeo', 10);

select * from raza;

select * from veterinario;


select * from mascota;

insert into mascota (id, nombre, fecha_nacimiento, raza_id, cliente_id)
values (1, 'Felipe', '2023-01-01', 1, 1);


select * from especialidad;

select * from veterinario_especialidad;

select *
from remedio;


insert into estado_cita (id, nombre, descripcion) values (1, 'Reservada', 'La cita fue reservada');
insert into estado_cita (id, nombre, descripcion) values (2, 'Cancelada', 'La cita fue cancelada');
insert into estado_cita (id, nombre, descripcion) values (3, 'Completada', 'La cita fue completada');