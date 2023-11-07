SELECT u.id, u.nombre, u.email
FROM Usuarios u
WHERE u.id IN (
    SELECT r.usuarios_id
    FROM ReservasAlojamiento r
    WHERE r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY r.usuarios_id
    HAVING COUNT(DISTINCT r.fechain) >= 10
) OR u.id IN (
    SELECT c.id
    FROM Consumos c
    WHERE c.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE
    GROUP BY c.id
    HAVING SUM(c.total) > 15000000
);

CREATE INDEX idx_reservasalojamiento_fechain ON ReservasAlojamiento (fechain);
Drop index idx_reservasalojamiento_fechain;
