CREATE SEQUENCE tiposusuario_sequence;
CREATE SEQUENCE usuarios_sequence;
CREATE SEQUENCE tiposhabitacion_sequence;
CREATE SEQUENCE habitaciones_sequence;
CREATE SEQUENCE servicios_sequence;
CREATE SEQUENCE planesconsumo_sequence;
CREATE SEQUENCE reservasalojamiento_sequence;
CREATE SEQUENCE reservasservicio_sequence;
CREATE SEQUENCE consumos_sequence;
CREATE SEQUENCE productos_sequence;

CREATE TABLE consumos (
    id              NUMBER NOT NULL,
    cantidad        NUMBER NOT NULL,
    fecha           DATE NOT NULL,
    total           FLOAT NOT NULL,
    habitaciones_id NUMBER NOT NULL,
    servicios_id    NUMBER,
    productos_id    NUMBER
);

ALTER TABLE consumos ADD CONSTRAINT consumos_pk PRIMARY KEY ( id );

CREATE TABLE habitaciones (
    id                 NUMBER NOT NULL,
    numero             NUMBER NOT NULL,
    piso               NUMBER NOT NULL,
    ocupada            NUMBER DEFAULT 0 NOT NULL,
    tiposhabitacion_id NUMBER NOT NULL
);

ALTER TABLE habitaciones
    ADD CHECK ( ocupada IN ( 0, 1 ) );

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( id );

CREATE TABLE ofrecenproductos (
    servicios_id NUMBER NOT NULL,
    productos_id NUMBER NOT NULL
);

ALTER TABLE ofrecenproductos ADD CONSTRAINT ofrecenproductos_pk PRIMARY KEY ( servicios_id,
                                                                              productos_id );

CREATE TABLE planesconsumo (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE) NOT NULL,
    descripcion VARCHAR2(255 BYTE) NOT NULL,
    descuento   FLOAT
);

ALTER TABLE planesconsumo ADD CONSTRAINT planesconsumo_pk PRIMARY KEY ( id );

CREATE TABLE productos (
    id     NUMBER NOT NULL,
    nombre VARCHAR2(255 BYTE) NOT NULL,
    precio FLOAT NOT NULL
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id );

CREATE TABLE reservasalojamiento (
    id               NUMBER NOT NULL,
    fechain          DATE NOT NULL,
    fechaout         DATE NOT NULL,
    numpersonas      NUMBER NOT NULL,
    usuarios_id      NUMBER NOT NULL,
    planesconsumo_id NUMBER,
    habitaciones_id  NUMBER NOT NULL
);

ALTER TABLE reservasalojamiento ADD CONSTRAINT reservasalojamiento_pk PRIMARY KEY ( id );

CREATE TABLE reservasservicio (
    id              NUMBER NOT NULL,
    fecha           DATE NOT NULL,
    horainicio      DATE NOT NULL,
    horafin         DATE NOT NULL,
    servicios_id    NUMBER NOT NULL,
    habitaciones_id NUMBER NOT NULL
);

ALTER TABLE reservasservicio ADD CONSTRAINT reservasservicio_pk PRIMARY KEY ( id );

CREATE TABLE servicios (
    id             NUMBER NOT NULL,
    nombre         VARCHAR2(255 BYTE) NOT NULL,
    descripcion    VARCHAR2(255 BYTE) NOT NULL,
    tiposervicio   VARCHAR2(255 BYTE) NOT NULL,
    costoporunidad FLOAT,
    horario        VARCHAR2(255 BYTE),
    capacidad      NUMBER
);

ALTER TABLE servicios
    ADD CHECK ( tiposervicio IN ( 'Bar', 'Gimnasio', 'Internet', 'Lavanderia', 'Piscina',
                                  'Prestamo', 'Restaurante', 'SPA', 'SalonConferencia', 'SalonReunion',
                                  'Supermercado', 'Tienda' ) );

ALTER TABLE servicios
    ADD CHECK ( horario IN ( 'manana', 'noche', 'tarde' ) );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( id );

CREATE TABLE tiposhabitacion (
    id            NUMBER NOT NULL,
    tipo          VARCHAR2(255 BYTE) NOT NULL,
    capacidad     NUMBER NOT NULL,
    camas         NUMBER NOT NULL,
    costopornoche FLOAT NOT NULL
);

ALTER TABLE tiposhabitacion ADD CONSTRAINT tiposhabitacion_pk PRIMARY KEY ( id );

CREATE TABLE tiposusuario (
    id   NUMBER NOT NULL,
    tipo VARCHAR2(255 BYTE) NOT NULL
);

ALTER TABLE tiposusuario ADD CONSTRAINT tiposusuario_pk PRIMARY KEY ( id );

CREATE TABLE usuarios (
    id              NUMBER NOT NULL,
    usuario         VARCHAR2(255 BYTE) UNIQUE NOT NULL,
    contrasena      VARCHAR2(255 BYTE) NOT NULL,
    nombre          VARCHAR2(255 BYTE) NOT NULL,
    email           VARCHAR2(255 BYTE) UNIQUE NOT NULL,
    tipodocumento   VARCHAR2(255 BYTE) NOT NULL,
    documento       VARCHAR2(255 BYTE) NOT NULL,
    tiposusuario_id NUMBER NOT NULL
);

ALTER TABLE usuarios
    ADD CHECK ( tipodocumento IN ( 'CC', 'CE', 'LICENSIA', 'OTRO', 'PASAPORTE' ) );

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( id );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_habitaciones_fk FOREIGN KEY ( habitaciones_id )
        REFERENCES habitaciones ( id );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_productos_fk FOREIGN KEY ( productos_id )
        REFERENCES productos ( id );

ALTER TABLE consumos
    ADD CONSTRAINT consumos_servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitaciones_tipos_fk FOREIGN KEY ( tiposhabitacion_id )
        REFERENCES tiposhabitacion ( id );

ALTER TABLE ofrecenproductos
    ADD CONSTRAINT ofrecenproductos_productos_fk FOREIGN KEY ( productos_id )
        REFERENCES productos ( id );

ALTER TABLE ofrecenproductos
    ADD CONSTRAINT ofrecenproductos_servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );

ALTER TABLE reservasalojamiento
    ADD CONSTRAINT reservasaloj_habi_fk FOREIGN KEY ( habitaciones_id )
        REFERENCES habitaciones ( id );

ALTER TABLE reservasalojamiento
    ADD CONSTRAINT reservasaloj_planes_fk FOREIGN KEY ( planesconsumo_id )
        REFERENCES planesconsumo ( id );

ALTER TABLE reservasalojamiento
    ADD CONSTRAINT reservasaloj_usuarios_fk FOREIGN KEY ( usuarios_id )
        REFERENCES usuarios ( id );

ALTER TABLE reservasservicio
    ADD CONSTRAINT reservasserv_hab_fk FOREIGN KEY ( habitaciones_id )
        REFERENCES habitaciones ( id );

ALTER TABLE reservasservicio
    ADD CONSTRAINT reservasservicio_servicios_fk FOREIGN KEY ( servicios_id )
        REFERENCES servicios ( id );

ALTER TABLE usuarios
    ADD CONSTRAINT usuarios_tiposusuario_fk FOREIGN KEY ( tiposusuario_id )
        REFERENCES tiposusuario ( id );
        
INSERT INTO TiposUsuario (id, tipo) VALUES ( tiposusuario_sequence.nextval , 'Cliente');
INSERT INTO TiposUsuario (id, tipo) VALUES ( tiposusuario_sequence.nextval , 'Empleado');
INSERT INTO TiposUsuario (id, tipo) VALUES ( tiposusuario_sequence.nextval , 'Recepcionista');
INSERT INTO TiposUsuario (id, tipo) VALUES ( tiposusuario_sequence.nextval , 'Administrador');
INSERT INTO TiposUsuario (id, tipo) VALUES ( tiposusuario_sequence.nextval , 'Gerente');

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'jreboll', '333', 'Juan Rebolledo', 'jrbol@gmail.com', 'CC', '123456789', 1);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'ahernandez', 'abeja', 'Alejandro Hernandez', 'alejoh@gmail.com', 'CC', '1234123123', 1);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'mcchaw', 'praga', 'Camila Chavez', 'camich24@outlook.com', 'CC', '987654321', 1);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'lcorredor', 'lcgm45', 'Laura Corredor', 'correlg@gmail.com', 'CC', '987987987', 1);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'empleado1', 'a', 'Empleado 1', 'e1@gmail.com', 'CC', '123', 2);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'empleado2', 'b', 'Empleado 2', 'e2@gmail.com', 'CC', '321', 2);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'empleado3', 'c', 'Empleado 3', 'e3@gmail.com', 'CC', '213', 2);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'empleado4', 'd', 'Empleado 4', 'e4@gmail.com', 'CC', '231', 2);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'recepcionista1', 'a', 'Recepcionista 1', 'r1@gmail.com', 'CC', '1234', 3);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'recepcionista2', 'b', 'Recepcionista 2', 'r2@gmail.com', 'CC', '4321', 3);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'a', 'a', 'Admin', 'admin@gmail.com', 'CC', '100', 4);

INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) 
VALUES ( usuarios_sequence.nextval , 'g', 'g', 'Gerente', 'gerente@gmail.com', 'CC', '101', 5);


        
INSERT INTO PlanesConsumo (id, nombre, descripcion) VALUES ( planesconsumo_sequence.nextval , 'Estandar', 'Estandar');

INSERT INTO Productos (id, nombre, precio) VALUES ( productos_sequence.nextval , 'Sin Producto', 0);

CREATE INDEX idx_reservasalojamiento_fechain ON ReservasAlojamiento (fechain);
CREATE INDEX idx_reservasservicio_fecha ON Reservasservicio (fecha);
CREATE INDEX idx_consumos_fecha ON consumos (fecha);
CREATE INDEX idx_reservasalojamiento_habitaciones_id ON reservasalojamiento (habitaciones_id);
CREATE INDEX idx_reservasservicio_servicios_id ON reservasservicio (servicios_id);

COMMIT;
