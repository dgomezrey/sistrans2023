select SUM(total)
    from consumos
join habitaciones
on habitaciones.id = consumos.habitaciones_id

where habitaciones.numero = 201 and  to_char(consumos.fecha, 'YYYY') =  '2003'