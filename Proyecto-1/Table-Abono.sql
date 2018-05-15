CREATE TABLE abono(
  id_abono NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT abono_nombre NOT NULL,
  descripcion VARCHAR2(500) CONSTRAINT abono_descripcion NOT NULL
);

Alter table abono
      add creado_por varchar2 (100);

Alter table abono
      add fech_creacion date ;

Alter table abono
      add editado_por varchar2 (100);

Alter table abono 
      add fech_edicion date ;

ALTER TABLE abono
      ADD CONSTRAINT pk_abono PRIMARY KEY (id_abono)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
COMMENT ON TABLE abono
IS 'Tabla que contiene la informacion de los abonos';

COMMENT ON COLUMN abono.id_abono
IS 'Identificador primario de los abonos.';

COMMENT ON COLUMN abono.nombre
IS 'Nombre del abono.';

COMMENT ON COLUMN abono.descripcion
IS 'Descripcion del abono.';