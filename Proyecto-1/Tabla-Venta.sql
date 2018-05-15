CREATE TABLE venta(
  id_venta NUMBER(6),
  fecha DATE DEFAULT SYSDATE CONSTRAINT venta_fecha NOT NULL,
  id_comprador NUMBER(6)
);

Alter table venta
      add creado_por varchar2 (100);

Alter table venta 
      add fech_creacion date ;

ALTER TABLE venta
      ADD CONSTRAINT FK_ID_COMPRADOR_VENTA FOREIGN KEY
      (id_comprador) REFERENCES persona(id_persona);

ALTER TABLE venta
      ADD CONSTRAINT pk_venta PRIMARY KEY (id_venta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
COMMENT ON TABLE venta
IS 'Tabla que contiene la informacion de las ventas';

COMMENT ON COLUMN venta.id_venta
IS 'Identificador primario de la venta.';

COMMENT ON COLUMN venta.fecha
IS 'Fecha de la venta.';