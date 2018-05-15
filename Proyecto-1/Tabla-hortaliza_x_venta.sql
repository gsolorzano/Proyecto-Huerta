CREATE TABLE hortaliza_x_venta(
  id_hortaliza NUMBER(6),
  id_venta NUMBER(6)
);

Alter table hortaliza_x_venta
      add creado_por varchar2 (100);

Alter table hortaliza_x_venta
      add fech_creacion date ;

ALTER TABLE hortaliza_x_venta
      ADD CONSTRAINT pk_hortaliza_x_venta PRIMARY KEY (id_hortaliza, id_venta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE hortaliza_x_venta
      ADD CONSTRAINT fk_hortaliza_hortaliza_x_venta FOREIGN KEY
      (id_hortaliza) REFERENCES hortaliza(id_hortaliza);
      
ALTER TABLE hortaliza_x_venta
      ADD CONSTRAINT fk_venta_hortaliza_x_venta FOREIGN KEY
      (id_venta) REFERENCES venta(id_venta);
      
COMMENT ON TABLE hortaliza_x_venta
IS 'Tabla que contiene la informacion de las ventas por hortalizas';

COMMENT ON COLUMN hortaliza_x_venta.id_hortaliza
IS 'Identificador primario de la hortaliza.';

COMMENT ON COLUMN hortaliza_x_venta.id_venta
IS 'Identificador primario de la venta.';