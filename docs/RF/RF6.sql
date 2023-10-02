----Req.6: PlanesConsumo 

---Create
INSERT INTO PlanesConsumo (id, nombre, descripcion, descuento) VALUES (1, 'Larga estadia', 'Para estadias mayores a 7 dias', 0.18);
INSERT INTO PlanesConsumo (id, nombre, descripcion) VALUES (2, 'Todo incluido', 'Descripcion plan2');
INSERT INTO PlanesConsumo (id, nombre, descripcion, descuento) VALUES (3, 'Tiempo compartido', 'Descripcion plan3', 0.1);

---Read
--All
SELECT * FROM PlanesConsumo;

--ById
SELECT * FROM PlanesConsumo
WHERE ID = 1; --El id es el parametro.

--Update
UPDATE PlanesConsumo
SET nombre = 'Tiempo compartido', --Parametro nombre: String
descripcion = 'Descripcion plan3', --Parametro descripcion: String
descuento = 0.1 --Parametro descuento: Float
WHERE id = 3; --Parametro id

--Delete
DELETE FROM PlanesConsumo
WHERE id = 3; --El id es el parametro.