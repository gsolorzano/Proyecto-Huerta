CREATE TABLE propiedad(
  id_propiedad NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT propiedad_nombre NOT NULL,
  descripcion VARCHAR2(300) CONSTRAINT propiedad_descripcion NOT NULL,
  activo number(6) NOT NULL
);

Alter table propiedad 
      add creado_por varchar2 (100);

Alter table propiedad
      add fech_creacion date ;

ALTER TABLE propiedad
      ADD CONSTRAINT pk_propiedad PRIMARY KEY (id_propiedad)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
COMMENT ON TABLE propiedad
IS 'Tabla que contiene la informacion de las propiedades';

COMMENT ON COLUMN propiedad.id_propiedad
IS 'Identificador primario de la propiedad.';

COMMENT ON COLUMN propiedad.nombre
IS 'El nombre de la propiedad.';

COMMENT ON COLUMN propiedad.descripcion
IS 'Descripcion de la propiedad.'; 