----Req.8: ReservasServicio

---Create
INSERT INTO ReservasServicio (id, fechaInicio, fechaFin, reservaAlojamiento, servicio) VALUES (1, TO_DATE('2023-10-01 10:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2023-10-01 11:30', 'YYYY-MM-DD HH24:MI'), 2, 1);
INSERT INTO ReservasServicio (id, fechaInicio, fechaFin, reservaAlojamiento, servicio) VALUES (2, TO_DATE('2023-09-29 17:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2023-09-29 21:00', 'YYYY-MM-DD HH24:MI'), 3, 2);
INSERT INTO ReservasServicio (id, fechaInicio, fechaFin, reservaAlojamiento, servicio) VALUES (3, TO_DATE('2023-10-01 08:00', 'YYYY-MM-DD HH24:MI'), TO_DATE('2023-10-01 16:30', 'YYYY-MM-DD HH24:MI'), 1, 3);

---Read
--All
SELECT * FROM ReservasServicio;

--ById
SELECT * FROM ReservasServicio
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE ReservasServicio
SET fechaInicio = TO_DATE('2023-10-01 10:00', 'YYYY-MM-DD HH24:MI'), --Parametro fechaInicio: Date
fechaFin = TO_DATE('2023-10-01 12:00', 'YYYY-MM-DD HH24:MI'), --Parametro fechaFin: Date
reservaAlojamiento = 2, --Parametro reservaAlojamiento: Integer
servicio = 1 --Parametro servicio: Integer
WHERE id = 1; --Parametro id

--Delete
DELETE FROM ReservasServicio
WHERE id = 3; --El id es el parametro.
