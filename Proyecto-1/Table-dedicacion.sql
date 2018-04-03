--Tabla que lleva la información de la dedicaciíon de cada persona
CREATE TABLE dedicacion(
  id_dedicacion NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT dedicacion_nombre NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE dedicacion
      ADD CONSTRAINT pk_dedicacion PRIMARY KEY (id_dedicacion)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
--Comentarios de tabla y columna
COMMENT ON TABLE dedicacion
IS 'Tabla que contiene la informacion de la dedicacion de las personas';

COMMENT ON COLUMN dedicacion.id_dedicacion
IS 'Identificador primario de la dedicacion.';

COMMENT ON COLUMN dedicacion.nombre
IS 'El nombre de la de dedicacion.';