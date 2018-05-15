CREATE TABLE pais(
  id_pais NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT pais_nombre NOT NULL
);

ALTER TABLE pais
      ADD CONSTRAINT pk_pais PRIMARY KEY (id_pais)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
Alter table pais
      add creado_por varchar2 (100);

Alter table pais
      add fech_creacion date ;

COMMENT ON TABLE pais
IS 'Tabla que contiene la informacion de los paises';

COMMENT ON COLUMN pais.id_pais
IS 'Identificador primario de pais.';

COMMENT ON COLUMN pais.nombre
IS 'Nombre del pais.';