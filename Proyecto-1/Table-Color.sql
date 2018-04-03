--Tabla que lleva la información de los colores de las hortalizas, función de catálogo
CREATE TABLE color(
  id_color NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT color_nombre NOT NULL,
  descripcion VARCHAR2(20) CONSTRAINT descripcion_nombre NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE color
      ADD CONSTRAINT pk_color PRIMARY KEY (id_color)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Comentarios de tabla y columna      
COMMENT ON TABLE color
IS 'Tabla que contiene la informacion de los colores';

COMMENT ON COLUMN color.id_color
IS 'Identificador primario del color.';

COMMENT ON COLUMN color.nombre
IS 'El nombre del color.';

COMMENT ON COLUMN color.descripcion
IS 'Descripcion del color.'; 