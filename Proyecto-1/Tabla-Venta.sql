--Tabla que lleva la información de las ventas en general
CREATE TABLE venta(
  id_venta NUMBER(6),
  fecha DATE DEFAULT SYSDATE CONSTRAINT venta_fecha NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE venta
      ADD CONSTRAINT pk_venta PRIMARY KEY (id_venta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
    
--Comentarios de tabla y columna  
COMMENT ON TABLE venta
IS 'Tabla que contiene la informacion de las ventas';

COMMENT ON COLUMN venta.id_venta
IS 'Identificador primario de la venta.';

COMMENT ON COLUMN venta.fecha
IS 'Fecha de la venta.';