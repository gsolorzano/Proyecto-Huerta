CREATE TABLE abono_x_arbol(
  id_abono NUMBER(6),
  id_arbol NUMBER(6),
  fecha date,
  id_bitacora number
);

ALTER TABLE abono_x_arbol
      ADD CONSTRAINT fk_idbitacora_AAbono FOREIGN KEY
      (id_bitacora) REFERENCES bitacora_abono(id_bitacora);


Alter table abono_x_arbol
      add creado_por varchar2 (100);

Alter table abono_x_arbol
      add fech_creacion date ;

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
      
COMMENT ON TABLE abono_x_arbol
IS 'Tabla que contiene la informacion de los trueque por hortalizas';

COMMENT ON COLUMN abono_x_arbol.id_abono
IS 'Identificador primario del abono.';

COMMENT ON COLUMN abono_x_arbol.id_arbol
IS 'Identificador primario de los arboles.';      