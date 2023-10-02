
--- Create 
insert into cuentaconsumos (reservaalojamiento_id, servicio_id)  values (1,2);
insert into cuentaconsumos (reservaalojamiento_id, servicio_id)  values (1,3);
insert into cuentaconsumos (reservaalojamiento_id, servicio_id)  values (1,4);
insert into cuentaconsumos (reservaalojamiento_id, servicio_id)  values (1,5);


--- Read 
select * from cuentaconsumos;
select * from cuentaconsumos where reservaalojamiento_id = 1 and servicio_id = 2;


--- Update
UPDATE cuentaconsumos
SET reservaalojamiento_id = 2, 
servicio_id = 4
where reservaalojamiento_id = 1 and
servicio_id = 2;

--- Delete 
delete from cuentaconsumos
where reservaalojamiento_id = 2 and servicio_id = 4



