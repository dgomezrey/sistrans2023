SELECT h.id ,h.numero, COALESCE(SUM(c.total),0) as consumo_total FROM habitaciones h LEFT JOIN reservasalojamiento r ON h.id = r.habitaciones_id LEFT JOIN consumos c ON h.id = c.habitaciones_id WHERE r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY h.id, h.numero ORDER BY consumo_total desc FETCH FIRST 30 ROWS ONLY;--RFC1


SELECT s.id,s.nombre,COUNT(c.id)as cantidad
FROM servicios s
JOIN consumos c ON s.id = c.servicios_id
WHERE EXISTS (SELECT 1 FROM reservasalojamiento r WHERE c.habitaciones_id = r.id AND r.fechain BETWEEN TO_DATE('2021-01-01', 'YYYY-MM-DD') AND TO_DATE('2025-01-01', 'YYYY-MM-DD'))
GROUP BY s.id, s.nombre
ORDER BY count(c.id) DESC
FETCH FIRST 20 ROWS ONLY; --RFC2

SELECT h.id, h.numero, COALESCE(ROUND(100 * SUM(NVL(r.fechaout, SYSDATE) - r.fechain) / 365, 2), 0) AS "%" 
FROM habitaciones h
LEFT JOIN reservasalojamiento r ON h.id = r.habitaciones_id AND r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
GROUP BY  h.id, h.numero
ORDER BY h.id; --req 3

SELECT distinct s.nombre, s.descripcion, s.costoporunidad, s.horario, s.tiposervicio, s.capacidad FROM servicios s LEFT JOIN consumos c ON s.id = c.servicios_id 
WHERE (:costo1 IS NULL OR (s.costoporunidad BETWEEN :costo1 AND :costo2)) 
AND (:fecha1 IS NULL OR (c.fecha BETWEEN to_date(:fecha1,'YYYY-MM-DD') AND to_date(:fecha2,'YYYY-MM-DD')))
AND (:habitacion_id IS NULL OR c.habitaciones_id = :habitacion_id)
AND (:tiposervicio IS NULL OR s.tiposervicio = :tiposervicio) FETCH FIRST 30 ROWS ONLY; --req4


SELECT u.id, u.nombre, s.nombre as SERVICIO, c.total
FROM consumos c
JOIN reservasalojamiento ra ON c.habitaciones_id = ra.id
JOIN servicios s ON c.servicios_id = s.id
JOIN usuarios u ON ra.usuarios_id = u.id
WHERE  u.id = :usuarios_id
AND ra.fechain BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD'); --req5



SELECT fechain as Fecha, COUNT(*) AS ocupacion
FROM reservasalojamiento
GROUP BY fechain
ORDER BY ocupacion DESC
FETCH FIRST 10 ROWS ONLY;--6a

SELECT c.fecha, SUM(c.total) AS ingresos
FROM consumos c
GROUP BY c.fecha
ORDER BY ingresos DESC
FETCH FIRST 10 ROWS ONLY;--6b

SELECT fechain, COUNT(*) AS ocupacion
FROM reservasalojamiento
GROUP BY fechain
ORDER BY ocupacion ASC
FETCH FIRST 10 ROWS ONLY;--6c


SELECT u.id, u.nombre, u.email
FROM Usuarios u
WHERE u.id IN (
    SELECT r.usuarios_id
    FROM ReservasAlojamiento r
    WHERE r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY r.usuarios_id
    HAVING COUNT(DISTINCT r.fechain) >= 14
) OR u.id IN (
    SELECT c.id
    FROM Consumos c
    WHERE c.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY c.id
    HAVING SUM(c.total) > 15000000
); --req7


SELECT s.nombre,  COUNT(*) 
FROM Consumos c, servicios s 
WHERE (c.fecha >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id) 
GROUP BY s.nombre, TO_CHAR(c.FECHA, 'IYYY-IW')
HAVING COUNT(*) < 3; --req8

select nombre from servicios where id not in (select servicios_id from consumos) FETCH FIRST 30 ROWS ONLY; --aux8


SELECT  u.nombre,u.numdocumento,s.nombre,COUNT(c.numconsumos) AS cuenta
FROM consumos c
JOIN usuarios u ON c.usuarios_id = u.id JOIN servicios s ON c.servicios_id = s.id
WHERE c.fechaconsumo BETWEEN TO_DATE('2022-12-24','YYYY-MM-DD') AND TO_DATE('2023-12-24','YYYY-MM-DD') AND s.id = 1
GROUP BY DECODE(:agrupamiento, 'usuario', u.nombre, 'documento', u.numdocumento),u.nombre,s.nombre, u.numdocumento
ORDER BY DECODE(:ordenamiento, 'usuario', u.nombre,'documento', u.numdocumento, 'count', cuenta);

SELECT DISTINCT u.id, u.nombre, u.numdocumento, u.email FROM usuarios u
WHERE u.id IN (SELECT r.usuarios_id FROM reservashabitaciones r GROUP BY r.usuarios_id HAVING COUNT(DISTINCT TRUNC(r.fechainicio, 'Q')) = 4)
OR u.id IN (SELECT c.usuarios_id FROM consumos c JOIN servicios s ON c.servicios_id = s.id WHERE s.costoporunidad > 300000 GROUP BY c.usuarios_id HAVING COUNT(DISTINCT c.id) = COUNT(DISTINCT c.reservashabitaciones_id))
OR u.id IN (SELECT r.usuarios_id FROM reservasservicios r JOIN servicios s ON r.servicios_id = s.id WHERE (s.nombre = 'SPA' OR s.tiposervicio = 'Salon de reuniones') AND (r.fechafin - r.fechainicio) * 24 > 4 GROUP BY r.usuarios_id HAVING COUNT(DISTINCT r.id) = COUNT(DISTINCT r.servicios_id)); 

select * from consumos;