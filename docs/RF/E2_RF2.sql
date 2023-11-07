select servicios.nombre as servicio , count(reservasservicio.servicio) as numero_reservas
from reservasservicio 
join servicios on 
reservasservicio.servicio = servicios.id
where to_char(reservasservicio.fechainicio, 'YYYY') =  '2003'
group by servicios.nombre
order by numero_reservas