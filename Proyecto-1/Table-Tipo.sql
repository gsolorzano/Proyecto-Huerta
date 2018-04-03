--Tabla que lleva la información de los tipos de hortalizas
CREATE TABLE tipo(
  id_tipo NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT tipo_nombre NOT NULL,
  descripcion VARCHAR2(20) CONSTRAINT tipo_descripcion NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE tipo
      ADD CONSTRAINT pk_tipo PRIMARY KEY (id_tipo)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Comentarios de tabla y columna     
COMMENT ON TABLE tipo
IS 'Tabla que contiene la informacion de los tipos';

COMMENT ON COLUMN tipo.id_tipo
IS 'Identificador primario del tipo.';

COMMENT ON COLUMN tipo.nombre
IS 'El nombre del tipo.';

COMMENT ON COLUMN tipo.descripcion
IS 'Descripcion del tipo.'; 