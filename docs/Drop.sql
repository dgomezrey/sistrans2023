DROP SEQUENCE tiposusuario_sequence;
DROP SEQUENCE usuarios_sequence;
DROP SEQUENCE tiposhabitacion_sequence;
DROP SEQUENCE habitaciones_sequence;
DROP SEQUENCE servicios_sequence;
DROP SEQUENCE planesconsumo_sequence;
DROP SEQUENCE reservasalojamiento_sequence;
DROP SEQUENCE reservasservicio_sequence;
DROP SEQUENCE consumos_sequence;
DROP SEQUENCE productos_sequence;

DROP TABLE consumos CASCADE CONSTRAINTS;

DROP TABLE habitaciones CASCADE CONSTRAINTS;

DROP TABLE ofrecenproductos CASCADE CONSTRAINTS;

DROP TABLE planesconsumo CASCADE CONSTRAINTS;

DROP TABLE productos CASCADE CONSTRAINTS;

DROP TABLE reservasalojamiento CASCADE CONSTRAINTS;

DROP TABLE reservasservicio CASCADE CONSTRAINTS;

DROP TABLE servicios CASCADE CONSTRAINTS;

DROP TABLE tiposhabitacion CASCADE CONSTRAINTS;

DROP TABLE tiposusuario CASCADE CONSTRAINTS;

DROP TABLE usuarios CASCADE CONSTRAINTS;

COMMIT;