--Tabla que lleva la información de las provincias
CREATE TABLE provincia(
  id_provincia NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT provincia_nombre NOT NULL,
  id_pais NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE provincia
      ADD CONSTRAINT pk_provincia PRIMARY KEY (id_provincia)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE provincia
      ADD CONSTRAINT fk_provincia_pais FOREIGN KEY
      (id_pais) REFERENCES pais(id_pais);
    
--Comentarios de tabla y columna  
COMMENT ON TABLE provincia
IS 'Tabla que contiene la informacion de las provincias';

COMMENT ON COLUMN provincia.id_provincia
IS 'Identificador primario de las provincias.';

COMMENT ON COLUMN provincia.nombre
IS 'Nombre de la provincia.';

COMMENT ON COLUMN provincia.id_pais
IS 'Foreign key del pais al que pertenece la provincia.';