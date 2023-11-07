SELECT 
    u.id AS usuario_id,
    u.nombre AS nombre_usuario
FROM 
    usuarios u
WHERE 
    NOT EXISTS (
        SELECT 1 
        FROM 
            reservasalojamiento ra 
        JOIN 
            habitaciones h ON ra.habitaciones_id = h.id
        JOIN 
            reservasservicio rs ON h.id = rs.habitaciones_id
        JOIN 
            servicios s ON rs.servicios_id = s.id
        JOIN 
            consumos c ON h.id = c.habitaciones_id
        WHERE 
            u.id = ra.usuarios_id AND
            c.fecha BETWEEN TO_DATE(:fecha_inicio, 'DD-MM-YYYY') AND TO_DATE(:fecha_fin, 'DD-MM-YYYY') AND
            s.nombre = :nombre_servicio
    )
ORDER BY 
    :criterio_ordenamiento;
    
CREATE INDEX idx_reservasalojamiento_habitaciones_id ON reservasalojamiento (habitaciones_id);
CREATE INDEX idx_consumos_fecha ON consumos (fecha);
CREATE INDEX idx_reservasservicio_servicios_id ON reservasservicio (servicios_id);