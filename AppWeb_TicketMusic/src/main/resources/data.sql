/* INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Dua Lipa');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Lady Gaga');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Harry Styles');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Maluma');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'J Balvin');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Daddy Yankee');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Extremoduro');
INSERT INTO ARTISTA (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Guns N Roses');

INSERT INTO CIUDAD (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Sevilla');
INSERT INTO CIUDAD (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Madrid');
INSERT INTO CIUDAD (id, nombre) VALUES (NEXTVAL('hibernate_sequence'), 'Barcelona');

INSERT INTO RECINTO (id, aforo, nombre) VALUES (NEXTVAL('hibernate_sequence'), 500, 'Estado de la Cartuja');
INSERT INTO RECINTO (id, aforo, nombre) VALUES (NEXTVAL('hibernate_sequence'), 600, 'Auditorio Rocio Jurado');
INSERT INTO RECINTO (id, aforo, nombre) VALUES (NEXTVAL('hibernate_sequence'), 1000, 'Wizink Center');
INSERT INTO RECINTO (id, aforo, nombre) VALUES (NEXTVAL('hibernate_sequence'), 800, 'Palacio Vistalegre Arena');
INSERT INTO RECINTO (id, aforo, nombre) VALUES (NEXTVAL('hibernate_sequence'), 1500, 'Palau Sant Jordi');
 */






INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gómez Rubio', 'gomezrubio@gmail.com', '1980-12-05', 'Almería', 'Ana María', '2047');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Marín Romero', 'marinromero@gmail.com', '1975-03-11', 'Málaga', 'Jose', '858');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Ortiz Suarez', 'ortizsuarez@gmail.com', '1998-01-25', 'Huelva', 'Juan', '4633');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Díaz Blanco', 'diazblanco@gmail.com', '1950-04-27', 'Barcelona', 'Francisco Javier', '5118');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Torres Torres', 'torrestorres@gmail.com', '1950-11-14', 'Salamanca', 'Josefa', '2899');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Díaz Jiménez', 'diazjimenez@gmail.com', '1959-10-02', 'Jaén', 'Francisco', '3581');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Muñoz Ortiz', 'munozortiz@gmail.com', '2003-06-09', 'Cádiz', 'Javier', '5303');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Álvarez Ortiz', 'alvarezortiz@gmail.com', '1972-01-06', 'Salamanca', 'Marta', '5187');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Fernández Navarro', 'fernandeznavarro@gmail.com', '1993-01-15', 'Madrid', 'Carlos', '2162');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gil Suarez', 'gilsuarez@gmail.com', '1972-06-23', 'Murcia', 'Jesús', '2283');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Álvarez Martin', 'alvarezmartin@gmail.com', '1959-03-24', 'Valencia', 'Manuel', '7114');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'López Ramos', 'lopezramos@gmail.com', '1963-09-27', 'Valencia', 'Carmen', '7766');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Rodríguez Hernández', 'rodriguezhernandez@gmail.com', '1968-05-06', 'Granada', 'Carmen', '7693');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Navarro Serrano', 'navarroserrano@gmail.com', '1966-10-20', 'Alicante', 'María Ángeles', '5677');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Alonso Hernández', 'alonsohernandez@gmail.com', '1958-08-15', 'Badajoz', 'Juan', '4457');
/* INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Ortega López', 'ortegalopez@gmail.com', '1951-03-09', 'Córdoba', 'María Dolores', '6807');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Fernández Gutiérrez', 'fernandezgutierrez@gmail.com', '2002-03-15', 'Badajoz', 'Ana María', '6518');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gómez Muñoz', 'gomezmunoz@gmail.com', '1994-09-11', 'Málaga', 'María', '8145');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gutiérrez Molina', 'gutierrezmolina@gmail.com', '1966-05-04', 'Barcelona', 'Ana María', '8789');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gil Gómez', 'gilgomez@gmail.com', '2003-02-27', 'Huelva', 'María Carmen', '7569');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Jiménez Blanco', 'jimenezblanco@gmail.com', '2003-08-25', 'Mérida', 'Marta', '8478');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Ruiz Gómez', 'ruizgomez@gmail.com', '1964-05-23', 'Valencia', 'Cristina', '3380');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Domínguez Vázquez', 'dominguezvazquez@gmail.com', '1993-06-23', 'Salamanca', 'Francisco Javier', '7161');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Gutiérrez Sanz', 'gutierrezsanz@gmail.com', '1983-06-16', 'Madrid', 'José Luis', '90');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Hernández Martínez', 'hernandezmartinez@gmail.com', '1970-10-12', 'Granada', 'José Luis', '7414');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Ortiz González', 'ortizgonzalez@gmail.com', '1979-03-25', 'Madrid', 'Alejandro', '8263');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Castro López', 'castrolopez@gmail.com', '1982-12-14', 'Cádiz', 'José Luis', '4903');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Ramos Romero', 'ramosromero@gmail.com', '1979-05-25', 'Granada', 'María', '3018');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Serrano Castro', 'serranocastro@gmail.com', '1989-06-21', 'Barcelona', 'Carlos', '1513');
INSERT INTO USUARIO (id, admin, apellidos, email, fecha_nacimiento, localidad, nombre, password) 
		    VALUES (NEXTVAL('hibernate_sequence'), false, 'Hernández Delgado', 'hernandezdelgado@gmail.com', '1998-09-10', 'Bilbao', 'Carmen', '3273'); */