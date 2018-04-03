--Tabla que lleva la información de las huertas
CREATE TABLE huerta(
  id_huerta NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT huerta_nombre NOT NULL,
  latitud NUMBER(3,8) NOT NULL,
  longitud NUMBER(3,8) NOT NULL,
  id_distrito NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE huerta
      ADD CONSTRAINT pk_huerta PRIMARY KEY (id_huerta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE huerta
      ADD CONSTRAINT fk_huerta_distrito FOREIGN KEY
      (id_distrito) REFERENCES distrito(id_distrito);
     
--Comentarios de tabla y columna 
COMMENT ON TABLE huerta
IS 'Tabla que contiene la informacion de la huerta';

COMMENT ON COLUMN huerta.id_huerta
IS 'Identificador primario de las provincias.';

COMMENT ON COLUMN huerta.nombre
IS 'Nombre de la provincia.';

COMMENT ON COLUMN huerta.latitud
IS 'Latitud de la huerta.';

COMMENT ON COLUMN huerta.longitud
IS 'Longitud de la huerta.';

COMMENT ON COLUMN huerta.id_distrito
IS 'Foreign key del distrito al que pertenece la huerta.';