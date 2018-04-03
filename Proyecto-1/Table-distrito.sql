--Tabla que lleva la información de los distritos
CREATE TABLE distrito(
  id_distrito NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT distrito_nombre NOT NULL,
  id_canton NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE distrito
      ADD CONSTRAINT pk_distrito PRIMARY KEY (id_distrito)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
--Comentarios de tabla y columna
ALTER TABLE distrito
      ADD CONSTRAINT fk_distrito_canton FOREIGN KEY
      (id_canton) REFERENCES canton(id_canton);
      
COMMENT ON TABLE distrito
IS 'Tabla que contiene la informacion del distrito';

COMMENT ON COLUMN distrito.id_distrito
IS 'Identificador primario del distrito.';

COMMENT ON COLUMN distrito.nombre
IS 'Nombre del distrito.';

COMMENT ON COLUMN distrito.id_canton
IS 'Foreign key del canton al que pertenece el distrito.';