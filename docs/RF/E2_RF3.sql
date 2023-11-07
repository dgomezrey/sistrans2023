select habitaciones.numero
from reservasalojamiento
join habitaciones
on habitaciones.id = reservasalojamiento.habitaciones_id
where  to_char(reservasalojamiento.fechain, 'YYYY') =  '2003'


-- ya con este filtro con codigo se pueden comoarar las reservas que hay en el año para determinar el porcentaje de ocupacion 