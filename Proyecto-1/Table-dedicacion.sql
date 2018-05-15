CREATE TABLE dedicacion(
  id_dedicacion NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT dedicacion_nombre NOT NULL
);


Alter table dedicacion
      add creado_por varchar2 (100);

Alter table dedicacion 
      add fech_creacion date ;

ALTER TABLE dedicacion
      ADD CONSTRAINT pk_dedicacion PRIMARY KEY (id_dedicacion)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
COMMENT ON TABLE dedicacion
IS 'Tabla que contiene la informacion de la dedicacion de las personas';

COMMENT ON COLUMN dedicacion.id_dedicacion
IS 'Identificador primario de la dedicacion.';

COMMENT ON COLUMN dedicacion.nombre
IS 'El nombre de la de dedicacion.';