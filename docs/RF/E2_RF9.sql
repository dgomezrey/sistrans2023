
SELECT 
    u.id AS usuario_id,
    u.nombre AS nombre_usuario,
    s.nombre AS nombre_servicio,
    COUNT(c.id) AS numero_veces
FROM 
    usuarios u
JOIN 
    reservasalojamiento ra ON u.id = ra.usuarios_id
JOIN 
    habitaciones h ON ra.habitaciones_id = h.id
JOIN 
    reservasservicio rs ON h.id = rs.habitaciones_id
JOIN 
    servicios s ON rs.servicios_id = s.id
JOIN 
    consumos c ON h.id = c.habitaciones_id
WHERE 
    c.fecha BETWEEN TO_DATE(:fecha_inicio, 'DD-MM-YYYY') AND TO_DATE(:fecha_fin, 'DD-MM-YYYY')
GROUP BY 
    u.id, u.nombre, s.nombre
ORDER BY 
    :criterio_ordenamiento, u.nombre ASC;
    
CREATE INDEX idx_consumos_fecha ON consumos (fecha);
CREATE INDEX idx_reservasalojamiento_habitaciones_id ON reservasalojamiento (habitaciones_id);
    