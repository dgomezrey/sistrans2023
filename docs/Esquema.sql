
CREATE TABLE cuentaconsumos (
    reservaalojamientoid NUMBER NOT NULL,
    servicioid           NUMBER NOT NULL
);

ALTER TABLE cuentaconsumos ADD CONSTRAINT cuentaconsumos_pk PRIMARY KEY ( reservaalojamientoid,
                                                                          servicioid );

CREATE TABLE establecimientos (
    servicioid          NUMBER NOT NULL,
    tipoestablecimiento VARCHAR2(255 BYTE),
    estilo              VARCHAR2(255 BYTE),
    capacidad           VARCHAR2(255 BYTE)
);

ALTER TABLE establecimientos
    ADD CHECK ( tipoestablecimiento IN ( 'Bar', 'Restaurante', 'Supermercado', 'Tienda' ) );

ALTER TABLE establecimientos ADD CONSTRAINT establecimientos_pk PRIMARY KEY ( servicioid );

CREATE TABLE gimnasios (
    servicioid      NUMBER NOT NULL,
    capacidad       NUMBER,
    nummaquinas     NUMBER,
    horarioservicio VARCHAR2(255 BYTE)
);

ALTER TABLE gimnasios ADD CONSTRAINT gimnasios_pk PRIMARY KEY ( servicioid );

CREATE TABLE habitaciones (
    id                 NUMBER NOT NULL,
    numero             NUMBER,
    piso               NUMBER,
    reservaalojamiento NUMBER,
    tipohabitacion     NUMBER NOT NULL
);

ALTER TABLE habitaciones ADD CONSTRAINT habitaciones_pk PRIMARY KEY ( id );

CREATE TABLE internet (
    servicioid  NUMBER NOT NULL,
    capacidad   NUMBER,
    costopordia NUMBER
);

ALTER TABLE internet ADD CONSTRAINT internet_pk PRIMARY KEY ( servicioid );

CREATE TABLE lavanderias (
    servicioid     NUMBER NOT NULL,
    tipoprenda     VARCHAR2(255 BYTE),
    costoporprenda FLOAT
);

ALTER TABLE lavanderias ADD CONSTRAINT lavanderias_pk PRIMARY KEY ( servicioid );

CREATE TABLE ofrecenproductos (
    establecimientoid NUMBER NOT NULL,
    productoid        NUMBER NOT NULL
);

ALTER TABLE ofrecenproductos ADD CONSTRAINT ofrecenproductos_pk PRIMARY KEY ( establecimientoid,
                                                                              productoid );

CREATE TABLE piscinas (
    servicioid      NUMBER NOT NULL,
    capacidad       NUMBER,
    profundidad     FLOAT,
    horarioservicio VARCHAR2(255 BYTE)
);

ALTER TABLE piscinas ADD CONSTRAINT piscinas_pk PRIMARY KEY ( servicioid );

CREATE TABLE planesconsumo (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE),
    descripcion VARCHAR2(255 BYTE),
    descuento   FLOAT
);

ALTER TABLE planesconsumo ADD CONSTRAINT planesconsumo_pk PRIMARY KEY ( id );

CREATE TABLE prestamos (
    servicioid    NUMBER NOT NULL,
    utensilio     VARCHAR2(255 BYTE),
    costoremplazo FLOAT
);

ALTER TABLE prestamos ADD CONSTRAINT prestamos_pk PRIMARY KEY ( servicioid );

CREATE TABLE productos (
    id     NUMBER NOT NULL,
    nombre VARCHAR2(255 BYTE),
    precio FLOAT
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id );

CREATE TABLE reservasalojamiento (
    id          NUMBER NOT NULL,
    fecha       DATE,
    numnoches   NUMBER,
    numadultos  NUMBER,
    nummenores  NUMBER,
    usuario     NUMBER,
    planconsumo NUMBER
);

ALTER TABLE reservasalojamiento ADD CONSTRAINT reservasalojamiento_pk PRIMARY KEY ( id );

CREATE TABLE reservasservicio (
    id                 NUMBER NOT NULL,
    fechainicio        DATE,
    fechafin           DATE,
    reservaalojamiento NUMBER,
    servicio           NUMBER
);

ALTER TABLE reservasservicio ADD CONSTRAINT reservasservicio_pk PRIMARY KEY ( id );

CREATE TABLE salones (
    servicioid     NUMBER NOT NULL,
    tiposalon      VARCHAR2(255 BYTE),
    tiempolimpieza NUMBER,
    costoporhora   FLOAT
);

ALTER TABLE salones
    ADD CHECK ( tiposalon IN ( 'SalonConferencia', 'SalonReunion' ) );

ALTER TABLE salones ADD CONSTRAINT salones_pk PRIMARY KEY ( servicioid );

CREATE TABLE servicios (
    id           NUMBER NOT NULL,
    nombre       VARCHAR2(255 BYTE),
    descripcion  VARCHAR2(255 BYTE),
    tiposervicio VARCHAR2(255 BYTE)
);

ALTER TABLE servicios
    ADD CHECK ( tiposervicio IN ( 'EstablecimientoConsumo', 'Gimnasio', 'Internet', 'Lavanderia', 'Piscina',
                                  'Prestamo', 'SPA', 'Salon' ) );

ALTER TABLE servicios ADD CONSTRAINT servicios_pk PRIMARY KEY ( id );

CREATE TABLE spas (
    servicioid NUMBER NOT NULL,
    duracion   NUMBER,
    costo      FLOAT
);

ALTER TABLE spas ADD CONSTRAINT spas_pk PRIMARY KEY ( servicioid );

CREATE TABLE tiposhabitacion (
    id            NUMBER NOT NULL,
    tipo          VARCHAR2(255 BYTE),
    capacidad     NUMBER,
    camas         NUMBER,
    costopornoche FLOAT
);

ALTER TABLE tiposhabitacion ADD CONSTRAINT tiposhabitacion_pk PRIMARY KEY ( id );

CREATE TABLE tiposusuario (
    id   NUMBER NOT NULL,
    tipo VARCHAR2(255 BYTE)
);

ALTER TABLE tiposusuario ADD CONSTRAINT tiposusuario_pk PRIMARY KEY ( id );

CREATE TABLE usuarios (
    id          NUMBER NOT NULL,
    nombre      VARCHAR2(255 BYTE),
    apellido    VARCHAR2(255 BYTE),
    documento   VARCHAR2(255 BYTE),
    tipousuario NUMBER
);

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY ( id );

ALTER TABLE cuentaconsumos
    ADD CONSTRAINT cuenta_reservaaloj_fk FOREIGN KEY ( reservaalojamientoid )
        REFERENCES reservasalojamiento ( id );

ALTER TABLE cuentaconsumos
    ADD CONSTRAINT cuenta_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE establecimientos
    ADD CONSTRAINT establecimiento_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE gimnasios
    ADD CONSTRAINT gimnasio_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitacion_reservaaloj_fk FOREIGN KEY ( reservaalojamiento )
        REFERENCES reservasalojamiento ( id );

ALTER TABLE habitaciones
    ADD CONSTRAINT habitacion_tipohabitacion_fk FOREIGN KEY ( tipohabitacion )
        REFERENCES tiposhabitacion ( id );

ALTER TABLE internet
    ADD CONSTRAINT internet_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE lavanderias
    ADD CONSTRAINT lavanderia_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE ofrecenproductos
    ADD CONSTRAINT ofreceprod_establecimiento_fk FOREIGN KEY ( establecimientoid )
        REFERENCES establecimientos ( servicioid );

ALTER TABLE ofrecenproductos
    ADD CONSTRAINT ofreceprod_producto_fk FOREIGN KEY ( productoid )
        REFERENCES productos ( id );

ALTER TABLE piscinas
    ADD CONSTRAINT piscina_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE prestamos
    ADD CONSTRAINT prestamo_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE reservasalojamiento
    ADD CONSTRAINT reservaaloj_planconsumo_fk FOREIGN KEY ( planconsumo )
        REFERENCES planesconsumo ( id );

ALTER TABLE reservasalojamiento
    ADD CONSTRAINT reservaalojamiento_usuario_fk FOREIGN KEY ( usuario )
        REFERENCES usuarios ( id );

ALTER TABLE reservasservicio
    ADD CONSTRAINT reservaservicio_reservaaloj_fk FOREIGN KEY ( reservaalojamiento )
        REFERENCES reservasalojamiento ( id );

ALTER TABLE reservasservicio
    ADD CONSTRAINT reservaservicio_servicio_fk FOREIGN KEY ( servicio )
        REFERENCES servicios ( id );

ALTER TABLE salones
    ADD CONSTRAINT salon_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE spas
    ADD CONSTRAINT spa_servicio_fk FOREIGN KEY ( servicioid )
        REFERENCES servicios ( id );

ALTER TABLE usuarios
    ADD CONSTRAINT usuario_tipousuario_fk FOREIGN KEY ( tipousuario )
        REFERENCES tiposusuario ( id );