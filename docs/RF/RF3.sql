----Req.3: CRUD TiposHabitacion

---Create
INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES (1, 'Suite presidencial', 8, 6, 2000.00);
INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES (2, 'Suite', 5, 3, 1000.00);
INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES (3, 'Familiar', 6, 4, 790.00);
INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES (4, 'Doble', 3, 2, 550.00);
INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES (5, 'Sencilla', 2, 1, 290.00);

---Read
--All
SELECT * FROM TiposHabitacion;

--ById
SELECT * FROM TiposHabitacion
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE TiposHabitacion
SET tipo = 'Suite presidencial  2', --Parametro tipo: String
capacidad = 8, --Parametro capacidad: Integer
camas = 6, --Parametro camas: Integer
costoPorNoche = 2000.00 --Parametro costoPorNoche: Float
WHERE id = 1; --Parametro id

--Delete
DELETE FROM TiposHabitacion
WHERE id = 5; --El id es el parametro.