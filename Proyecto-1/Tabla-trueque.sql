CREATE TABLE trueque(
  id_trueque NUMBER(6),
  fecha DATE DEFAULT SYSDATE CONSTRAINT trueque_fecha NOT NULL,
  id_comprador NUMBER(6) NOT NULL,
  id_huerta NUMBER(6) NOT NULL
);

Alter table trueque
      add creado_por varchar2 (100);

Alter table trueque
      add fech_creacion date ;

ALTER TABLE trueque
      ADD CONSTRAINT pk_trueque PRIMARY KEY (id_trueque)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

ALTER TABLE trueque
      ADD CONSTRAINT FK_ID_COMPRADOR_TRUEQUE FOREIGN KEY
      (id_comprador) REFERENCES persona(id_persona);
      
ALTER TABLE trueque
      ADD CONSTRAINT fk_trueque_idhuerta FOREIGN KEY
      (id_huerta) REFERENCES huerta(id_huerta);
      
COMMENT ON TABLE trueque
IS 'Tabla que contiene la informacion de los truques';

COMMENT ON COLUMN trueque.id_trueque
IS 'Identificador primario del trueque.';

COMMENT ON COLUMN trueque.fecha
IS 'Fecha del trueque.';