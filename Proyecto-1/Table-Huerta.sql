CREATE TABLE huerta(
  id_huerta NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT huerta_nombre NOT NULL,
  latitud NUMBER(5,15) NOT NULL,
  longitud NUMBER(5,15) NOT NULL,
  id_distrito NUMBER(6) NOT NULL,
  capital NUMBER(10)
);

ALTER TABLE huerta
      ADD CONSTRAINT pk_huerta PRIMARY KEY (id_huerta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE huerta
      ADD CONSTRAINT fk_huerta_distrito FOREIGN KEY
      (id_distrito) REFERENCES distrito(id_distrito);

Alter table huerta
      add creado_por varchar2 (100);

Alter table huerta
      add fech_creacion date ;

Alter table huerta
      add editado_por varchar2 (100);

Alter table huerta
      add fech_edicion date ;
      
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