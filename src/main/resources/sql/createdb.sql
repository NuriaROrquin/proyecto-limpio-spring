INSERT INTO `disciplina` (`descripcion`)
VALUES ('Futbol'),
       ('Rugby'),
       ('Basquet'),
       ('Tae Kwon Do'),
       ('Karate'),
       ('Patinaje Artistico'),
       ('Tela'),
       ('Trapecio'),
       ('Tenis'),
       ('Yoga');


INSERT INTO Dificultad (descripcion)
VALUES ('Fácil'),
       ('Moderado'),
       ('Difícil');

INSERT INTO Lugar (latitud, longitud, descripcion, nombre)
VALUES (-34.615743, -58.503336, 'Cementerio de La Recoleta', 'La Recoleta'),
       (-34.608280, -58.373312, 'Obelisco de Buenos Aires', 'El Obelisco'),
       (-34.583861, -58.437057, 'Plaza de Mayo', 'Plaza de Mayo'),
       (-34.582521, -58.437147, 'Catedral Metropolitana de Buenos Aires', 'Catedral de Buenos Aires'),
       (-34.614846, -58.369218, 'Puerto Madero', 'Puerto Madero'),
       (-34.603738, -58.381570, 'Teatro Colón', 'Teatro Colón'),
       (-34.573226, -58.417232, 'Barrio de San Telmo', 'San Telmo'),
       (-34.591032, -58.392194, 'Barrio de La Boca', 'La Boca'),
       (-34.571952, -58.442219, 'Recoleta Mall', 'Recoleta Mall'),
       (-34.590746, -58.393014, 'Caminito', 'Caminito');

INSERT INTO `detalle` (`capacidad`, `hora_fin`, `hora_inicio`)
VALUES (15, '2023-06-08 10:30:00.000000', '2023-06-08 09:00:00.000000'),
       (10, '2023-06-08 12:30:00.000000', '2023-06-08 11:00:00.000000'),
       (20, '2023-06-08 14:30:00.000000', '2023-06-08 13:00:00.000000'),
       (12, '2023-06-08 16:30:00.000000', '2023-06-08 15:00:00.000000'),
       (8, '2023-06-08 18:30:00.000000', '2023-06-08 17:00:00.000000'),
       (16, '2023-06-08 20:30:00.000000', '2023-06-08 19:00:00.000000'),
       (10, '2023-06-09 11:30:00.000000', '2023-06-09 10:00:00.000000'),
       (20, '2023-06-09 13:30:00.000000', '2023-06-09 12:00:00.000000'),
       (15, '2023-06-09 15:30:00.000000', '2023-06-09 14:00:00.000000'),
       (8, '2023-06-09 17:30:00.000000', '2023-06-09 16:00:00.000000');


INSERT INTO Rol (id_rol, descripcion)
VALUES (1, 'admin'),
       (2, 'alumno'),
       (3, 'profesor');

INSERT INTO `usuario` (`id`, `activo`, `email`, `name`, `password`, `rol_id_rol`)
VALUES (1, 1, 'norquin@alumno.unlam.edu.ar', 'Nuri', 'nurinuri', 2),
       (2, 1, 'pabloantunez@hotmail.com', 'Pablo', '1234', 3),
       (3, 1, 'norquin@profesor.unlam.edu.ar', 'Nuri', 'nurinuri', 3),
       (4, 1, 'alumno@unlam.com.ar', 'AlumnoTest', '1234', 2);

INSERT INTO `estado` (`id_estado`, `descripcion`)
VALUES (1, "PENDIENTE"),
       (2, "EN_CURSO"),
       (3, "FINALIZADA"),
       (4, "CANCELADA");

INSERT INTO `clase` (`id_clase`, `fecha_baja`, `fecha`, `fecha_alta`, `edad_maxima`, `edad_minima`, `nombre`, `detail_id_detalle`, `difficulty_id_dificultad`,
                     `discipline_id_disciplina`, `place_id_lugar`, `professor_id`,`state_id_estado`,`is_calificada`)
VALUES (1, '2023-06-01 08:00:00.000000', '2023-06-01 09:00:00.000000', '2023-06-01 10:00:00.000000', 99, 6, 'Natación', 1, 1, 1, 1, 3, 1,false),
       (2, '2023-06-02 08:00:00.000000', '2023-06-02 09:00:00.000000', '2023-06-02 10:00:00.000000', 50, 5, 'Fútbol', 2, 2, 2, 2, 2, 2,false),
       (3, '2023-06-03 08:00:00.000000', '2023-06-03 09:00:00.000000', '2023-06-03 10:00:00.000000', 60, 8, 'Baloncesto', 3, 3, 3, 3, 3, 3,false),
       (4, '2023-06-04 08:00:00.000000', '2023-06-04 09:00:00.000000', '2023-06-04 10:00:00.000000', 70, 7, 'Karate', 4, 1, 4, 4, 2, 4,false),
       (5, '2023-06-05 08:00:00.000000', '2023-06-05 09:00:00.000000', '2023-06-05 10:00:00.000000', 18, 4, 'Gimnasia rítmica', 5, 2, 5, 5, 3, 1,false),
       (6, '2023-06-06 08:00:00.000000', '2023-06-06 09:00:00.000000', '2023-06-06 10:00:00.000000', 80, 6, 'Tenis', 6, 3, 6, 6, 2, 2,false),
       (7, '2023-06-07 08:00:00.000000', '2023-06-07 09:00:00.000000', '2023-06-07 10:00:00.000000', 55, 8, 'Béisbol', 7, 1, 7, 7, 3, 3,false),
       (8, '2023-06-08 08:00:00.000000', '2023-06-08 09:00:00.000000', '2023-06-08 10:00:00.000000', 99, 10, 'Atletismo', 8, 2, 8, 8, 2, 4,false);


INSERT INTO `alumnoclase` (`lesson_id_clase`, `user_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 4),
       (6, 4);

INSERT INTO `preferencias` (`discipline_id_disciplina`, `user_id`)
VALUES (1, 1),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
       (8, 1);