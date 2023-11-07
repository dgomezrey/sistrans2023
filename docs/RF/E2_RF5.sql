
SELECT
    sum(consumos.total)
FROM
         reservasalojamiento
    JOIN usuarios ON reservasalojamiento.usuarios_id = usuarios.id, isis2304d23202320.consumos
    JOIN habitaciones ON habitaciones.id = consumos.habitaciones_id
    JOIN reservasalojamiento on habitaciones.id = reservasalojamiento.habitaciones_id
    
    where usuarios.id = 1 and consumos.fecha -- rangos de fechas