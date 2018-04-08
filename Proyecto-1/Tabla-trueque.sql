--Tabla que lleva la información de los trueques
CREATE TABLE trueque(
  id_trueque NUMBER(6),
  fecha DATE DEFAULT SYSDATE CONSTRAINT trueque_fecha NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE trueque
      ADD CONSTRAINT pk_trueque PRIMARY KEY (id_trueque)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Comentarios de tabla y columna      
COMMENT ON TABLE trueque
IS 'Tabla que contiene la informacion de los truques';

COMMENT ON COLUMN trueque.id_trueque
IS 'Identificador primario del trueque.';

COMMENT ON COLUMN trueque.fecha
IS 'Fecha del trueque.';