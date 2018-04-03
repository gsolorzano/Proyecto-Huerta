--Tabla que lleva la información de los paises
CREATE TABLE pais(
  id_pais NUMBER(6),
  nombre  VARCHAR2(20) CONSTRAINT pais_nombre NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE pais
      ADD CONSTRAINT pk_pais PRIMARY KEY (id_pais)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
--Comentarios de tabla y columna
COMMENT ON TABLE pais
IS 'Tabla que contiene la informacion de los paises';

COMMENT ON COLUMN pais.id_pais
IS 'Identificador primario de pais.';

COMMENT ON COLUMN pais.nombre
IS 'Nombre del pais.';