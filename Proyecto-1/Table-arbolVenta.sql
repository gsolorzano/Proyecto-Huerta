--Tabla que lleva la información de los arboles por venta
CREATE TABLE arbol_x_venta(
  id_arbol NUMBER(6),
  id_venta NUMBER(6)
);

--Declaración primary keys y Foreign keys
ALTER TABLE arbol_x_venta
      ADD CONSTRAINT pk_arbol_x_venta PRIMARY KEY (id_arbol, id_venta)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE arbol_x_venta
      ADD CONSTRAINT fk_arbol_x_venta FOREIGN KEY
      (id_arbol) REFERENCES arbol(id_arbol);
      
ALTER TABLE arbol_x_venta
      ADD CONSTRAINT fk_venta_arbol_x_trueque FOREIGN KEY
      (id_venta) REFERENCES venta(id_venta);
  
--Comentarios de tabla y columna    
COMMENT ON TABLE arbol_x_venta
IS 'Tabla que contiene la informacion de los trueque por arboles';

COMMENT ON COLUMN arbol_x_venta.id_arbol
IS 'Identificador primario del arbol.';

COMMENT ON COLUMN arbol_x_venta.id_venta
IS 'Identificador primario de los venta.';