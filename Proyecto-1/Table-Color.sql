CREATE TABLE color(
  id_color NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT color_nombre NOT NULL,
  descripcion VARCHAR2(300) CONSTRAINT descripcion_nombre NOT NULL,
  activo number(6) NOT NULL
);

Alter table color 
      add creado_por varchar2 (100);

Alter table color
      add fech_creacion date;

ALTER TABLE color
      ADD CONSTRAINT pk_color PRIMARY KEY (id_color)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
COMMENT ON TABLE color
IS 'Tabla que contiene la informacion de los colores';

COMMENT ON COLUMN color.id_color
IS 'Identificador primario del color.';

COMMENT ON COLUMN color.nombre
IS 'El nombre del color.';

COMMENT ON COLUMN color.descripcion
IS 'Descripcion del color.'; 