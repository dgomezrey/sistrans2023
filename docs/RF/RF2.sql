----Req.2: CRUD Usuarios

---Create
INSERT INTO Usuarios (id, nombre, apellido, documento, tipoUsuario) VALUES (1, 'Nicolas', 'Riveros', '1558258052', 1);
INSERT INTO Usuarios (id, nombre, apellido, documento, tipoUsuario) VALUES (2, 'Santiago', 'Cely', '1964136963', 3);
INSERT INTO Usuarios (id, nombre, apellido, documento, tipoUsuario) VALUES (3, 'Gabriela', 'Soler', '1565337904', 5);

---Read
--All
SELECT * FROM Usuarios;

--ById
SELECT * FROM Usuarios
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE Usuarios
SET nombre = 'Nicolas', --Parametro nombre: String
apellido = 'Riveros 2', --Parametro apellido: String
documento = '1558258052', --Parametro documento: String
tipoUsuario = 1 --Parametro tipoUsuario: Integer
WHERE id = 1; --Parametro id

--Delete
DELETE FROM Usuarios
WHERE id = 3; --El id es el parametro.
