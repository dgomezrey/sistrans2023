----Req.7: ReservasAlojamiento

---Create
INSERT INTO ReservasAlojamiento (id, fecha, numNoches, numAdultos, numMenores, usuario, planConsumo) VALUES (1, TO_DATE('2023-09-25', 'YYYY-MM-DD'), 5, 2, 0, 1, 2);
INSERT INTO ReservasAlojamiento (id, fecha, numNoches, numAdultos, numMenores, usuario, planConsumo) VALUES (2, TO_DATE('2023-09-29', 'YYYY-MM-DD'), 2, 1, 1, 2, 2);
INSERT INTO ReservasAlojamiento (id, fecha, numNoches, numAdultos, numMenores, usuario, planConsumo) VALUES (3, TO_DATE('2023-09-30', 'YYYY-MM-DD'), 3, 2, 1, 3, 3);

---Read
--All
SELECT * FROM ReservasAlojamiento;

--ById
SELECT * FROM ReservasAlojamiento
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE ReservasAlojamiento
SET fecha = TO_DATE('2023-09-25', 'YYYY-MM-DD'), --Parametro fecha: Date
numNoches = 7, --Parametro numNoches: Integer
numAdultos = 2, --Parametro numAdultos: Integer
numMenores = 0, --Parametro numMenores: Integer
usuario = 1, --Parametro usuario: Integer
planConsumo = 1 --Parametro planConsumo: Integer
WHERE id = 1; --Parametro id

--Delete
DELETE FROM ReservasAlojamiento
WHERE id = 3; --El id es el parametro.