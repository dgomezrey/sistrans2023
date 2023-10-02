----Req.5: Servicios

---Create
INSERT INTO Servicios (id, nombre, descripcion, tipoServicio) VALUES (1, 'SPA piso 3', 'Delicioso SPA en el piso 3. Abierto entre semana', 'SPA');
INSERT INTO SPAs (servicioId, duracion, costo) VALUES (1, 2, 15);

---Read
--All
SELECT * FROM Servicios;
SELECT * FROM SPAs;

--ById
SELECT * FROM Servicios
WHERE ID = 1; --El id es el parametro.

SELECT * FROM SPAs
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE Servicios
SET nombre = 'SPA piso 5', --Parametro nombre: String
descripcion = 'Delicioso SPA en el piso 5. Abierto entre semana', --Parametro descripcion: String
tipoServicio = 'SPA' --Parametro tipoServicio: String
WHERE id = 1; --Parametro id

UPDATE SPAs
SET duracion = 3, --Parametro duracion: Integer
costo = 17 --Parametro duracion: Float
WHERE id = 1; --Parametro id

--Delete
DELETE FROM SPAs
WHERE servicioId = 1; --El id es el parametro.

DELETE FROM Servicios
WHERE id = 1; --El id es el parametro.