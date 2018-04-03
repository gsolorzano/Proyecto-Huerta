--Tabla que lleva la información de los cantones
CREATE TABLE canton(
  id_canton NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT canton_nombre NOT NULL,
  id_provincia NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE canton
      ADD CONSTRAINT pk_canton PRIMARY KEY (id_canton)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE canton
      ADD CONSTRAINT fk_canton_provincia FOREIGN KEY
      (id_provincia) REFERENCES provincia(id_provincia);
    
--Comentarios de tabla y columna  
COMMENT ON TABLE canton
IS 'Tabla que contiene la informacion de los cantones';

COMMENT ON COLUMN canton.id_canton
IS 'Identificador primario de los cantones.';

COMMENT ON COLUMN canton.nombre
IS 'Nombre del canton.';

COMMENT ON COLUMN canton.id_provincia
IS 'Foreign key de la provincia al que pertenece la provincia.';