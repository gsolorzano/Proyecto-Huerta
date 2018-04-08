--Tabla que lleva la información de los abonos por arbol
CREATE TABLE abono_x_arbol(
  id_abono NUMBER(6),
  id_arbol NUMBER(6)
);

--Declaración primary keys y Foreign keys
ALTER TABLE abono_x_arbol
      ADD CONSTRAINT pk_abono_x_arbol PRIMARY KEY (id_arbol, id_abono)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE abono_x_arbol
      ADD CONSTRAINT fk_abono_x_arbol_arbol FOREIGN KEY
      (id_arbol) REFERENCES arbol(id_arbol);
      
ALTER TABLE abono_x_arbol
      ADD CONSTRAINT fk_abono_x_arbol_abono FOREIGN KEY
      (id_abono) REFERENCES abono(id_abono);
      
--Comentarios de tabla y columna
COMMENT ON TABLE abono_x_arbol
IS 'Tabla que contiene la informacion de los trueque por hortalizas';

COMMENT ON COLUMN abono_x_arbol.id_abono
IS 'Identificador primario del abono.';

COMMENT ON COLUMN abono_x_arbol.id_arbol
IS 'Identificador primario de los arboles.';      