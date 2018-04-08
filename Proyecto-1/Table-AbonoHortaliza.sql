--Tabla que lleva la información de los abonos por hortalizas
CREATE TABLE hortaliza_x_abono(
  id_abono NUMBER(6),
  id_hortaliza NUMBER(6)
);

--Declaración primary keys y Foreign keys
ALTER TABLE hortaliza_x_abono
      ADD CONSTRAINT pk_hortaliza_x_abono PRIMARY KEY (id_hortaliza, id_abono)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE hortaliza_x_abono
      ADD CONSTRAINT fk_abono_hortaliza_x_abono FOREIGN KEY
      (id_abono) REFERENCES abono(id_abono);
      
ALTER TABLE hortaliza_x_abono
      ADD CONSTRAINT fk_hortaliza_x_abono FOREIGN KEY
      (id_hortaliza) REFERENCES hortaliza(id_hortaliza);
    
--Comentarios de tabla y columna  
COMMENT ON TABLE hortaliza_x_abono
IS 'Tabla que contiene la informacion de los abonos por hortalizas';

COMMENT ON COLUMN hortaliza_x_abono.id_hortaliza
IS 'Identificador primario de la hortaliza.';

COMMENT ON COLUMN hortaliza_x_abono.id_abono
IS 'Identificador primario de los abonos.';