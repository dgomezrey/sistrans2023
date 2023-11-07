SELECT s.id, s.nombre
FROM Servicios s
WHERE s.id NOT IN (
    SELECT rs.servicios_id
    FROM Reservasservicio rs
    WHERE rs.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY rs.servicios_id, TRUNC(rs.fecha, 'IW')
    HAVING COUNT(*) >= 3
);


CREATE INDEX idx_reservasservicio_fecha ON Reservasservicio (fecha);