----Req.1: CRUD TiposUsuario

---Create
INSERT INTO TiposUsuario (id, tipo) VALUES (1, 'Cliente');
INSERT INTO TiposUsuario (id, tipo) VALUES (2, 'Recepcionista');
INSERT INTO TiposUsuario (id, tipo) VALUES (3, 'Empleado');
INSERT INTO TiposUsuario (id, tipo) VALUES (4, 'Administrador');
INSERT INTO TiposUsuario (id, tipo) VALUES (5, 'Gerente');

---Read
--All
SELECT * FROM TiposUsuario;

--ById
SELECT * FROM TiposUsuario
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE TiposUsuario 
SET tipo = 'Cliente' --Parametro tipo: String
WHERE id = 1; --Parametro id

--Delete
DELETE FROM TiposUsuario 
WHERE id = 5; --El id es el parametro.