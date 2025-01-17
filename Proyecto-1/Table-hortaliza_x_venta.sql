CREATE TABLE hortaliza_x_trueque(
  id_hortaliza NUMBER(6),
  id_trueque NUMBER(6)
);

Alter table hortaliza_x_trueque
      add creado_por varchar2 (100);

Alter table hortaliza_x_trueque
      add fech_creacion date ;

ALTER TABLE hortaliza_x_trueque
      ADD CONSTRAINT pk_hortaliza_x_trueque PRIMARY KEY (id_hortaliza, id_trueque)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE hortaliza_x_trueque
      ADD CONSTRAINT fk_hortaliza_x_trueque FOREIGN KEY
      (id_hortaliza) REFERENCES hortaliza(id_hortaliza);
      
ALTER TABLE hortaliza_x_trueque
      ADD CONSTRAINT fk_trueque_hortaliza_x_trueque FOREIGN KEY
      (id_trueque) REFERENCES trueque(id_trueque);
      
COMMENT ON TABLE hortaliza_x_trueque
IS 'Tabla que contiene la informacion de los trueque por hortalizas';

COMMENT ON COLUMN hortaliza_x_trueque.id_hortaliza
IS 'Identificador primario de la hortaliza.';

COMMENT ON COLUMN hortaliza_x_trueque.id_trueque
IS 'Identificador primario de los trueques.';