--Tabla que lleva la información de los arboles por trueque
CREATE TABLE arbol_x_trueque(
  id_arbol NUMBER(6),
  id_trueque NUMBER(6)
);

--Declaración primary keys y Foreign keys
ALTER TABLE arbol_x_trueque
      ADD CONSTRAINT pk_arbol_x_trueque PRIMARY KEY (id_arbol, id_trueque)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE arbol_x_trueque
      ADD CONSTRAINT fk_arbol_x_trueque FOREIGN KEY
      (id_arbol) REFERENCES arbol(id_arbol);
      
ALTER TABLE arbol_x_trueque
      ADD CONSTRAINT fk_trueque_arbol_x_trueque FOREIGN KEY
      (id_trueque) REFERENCES trueque(id_trueque);
     
--Comentarios de tabla y columna
COMMENT ON TABLE arbol_x_trueque
IS 'Tabla que contiene la informacion de los trueque por arboles';

COMMENT ON COLUMN arbol_x_trueque.id_arbol
IS 'Identificador primario del arbol.';

COMMENT ON COLUMN arbol_x_trueque.id_trueque
IS 'Identificador primario de los trueques.';