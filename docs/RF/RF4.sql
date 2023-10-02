----Req.4: CRUD Habitaciones

---Create
INSERT INTO Habitaciones (id, numero, piso, tipoHabitacion) VALUES (1, 403, 4, 5);
INSERT INTO Habitaciones (id, numero, piso, tipoHabitacion) VALUES (2, 806, 8, 3);
INSERT INTO Habitaciones (id, numero, piso, tipoHabitacion) VALUES (3, 1013, 10, 1);

---Read
--All
SELECT * FROM Habitaciones;

--ById
SELECT * FROM Habitaciones
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE Habitaciones
SET numero = 403, --Parametro numero: Integer
piso = 4, --Parametro piso: Integer
--reservaAlojamiento = , --Parametro reservaAlojamiento: Integer ------Requiere que ya exista una reserva Alojamiento
tipoHabitacion = 4 --Parametro tipoHabitacion: Integer
WHERE id = 1; --Parametro id

--Delete
DELETE FROM Habitaciones
WHERE id = 3; --El id es el parametro.