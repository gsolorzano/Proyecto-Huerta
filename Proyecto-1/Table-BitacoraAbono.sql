--Tabla que lleva la información de la bitacora de los abonos
CREATE TABLE bitacora_abono(
  id_bitacora NUMBER(6),
  fecha DATE DEFAULT SYSDATE CONSTRAINT bitacora_fecha NOT NULL,
  id_persona NUMBER(6) NOT NULL,
  id_abono NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE bitacora_abono
      ADD CONSTRAINT pk_bitacora_abono PRIMARY KEY (id_bitacora)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
  
--Comentarios de tabla y columna    
COMMENT ON TABLE bitacora_abono
IS 'Tabla que contiene la informacion de las bitacoras por persona de abono';

COMMENT ON COLUMN bitacora_abono.id_bitacora
IS 'Identificador primario de la bitacora de abonos.';

COMMENT ON COLUMN bitacora_abono.fecha
IS 'Fecha de la bitacora.';

COMMENT ON COLUMN bitacora_abono.id_persona
IS 'Foreign Key de la persona que llevo acabo el abono.';

COMMENT ON COLUMN bitacora_abono.id_abono
IS 'Foreign Key del abono usado.';