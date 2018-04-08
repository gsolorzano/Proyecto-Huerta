--Tabla que lleva la información de los arboles
create table arbol (
       id_arbol    number(6),
       nombre      varchar2(30) constraint arbol_nombre not null,
       precio      number(10)   constraint arbol_precio not null,
       extincion   number(1)    constraint arbol_extincion not null,
       foto        varchar2(200) constraint foto_arbol not null,
       id_huerta   number(6)    constraint arbol_huerta not null,
       id_reproduccion  number(6) constraint arbol_reproduccion not null,
       id_estructura    number(6) constraint arbol_estructura not null
);

--Declaración primary keys y Foreign keys
ALTER TABLE arbol
  ADD CONSTRAINT pk_arbol PRIMARY KEY (id_arbol)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
  
  
ALTER TABLE arbol
      ADD CONSTRAINT fk_huerta_arbol FOREIGN KEY
      (id_huerta) REFERENCES huerta(id_huerta);


ALTER TABLE arbol
      ADD CONSTRAINT fk_reproduccion_arbol FOREIGN KEY
      (id_reproduccion) REFERENCES reproduccion(id_reproduccion);


ALTER TABLE arbol
      ADD CONSTRAINT fk_estructura_arbol FOREIGN KEY
      (id_estructura) REFERENCES estructura(id_estructura);

--Comentarios de tabla y columna
COMMENT on TABLE arbol
is 'Tabla para determinar los arboles que exsiten en las huertas';

COMMENT on column arbol.id_arbol
is 'Columna para identificar cada uno de los arboles ';

COMMENT on column arbol.nombre
is 'Columna para darle nombre a los arboles ';
 
COMMENT on column arbol.precio
is 'Columna para explicar el costo de cada uno de los arboles';

COMMENT on column arbol.extincion
is 'Columna para vereficar si el arbol se encuentra en extincion';

COMMENT on column arbol.foto
is 'Columna para almacenar las fotos de los arboles';
 
COMMENT on column arbol.id_huerta
is 'Columna para brindar la informacion en cual huerta se presenta el arbol ';

COMMENT on column arbol.id_reproduccion
is 'Columna para brindar la informacion cual es el tipo de reproduccion del arbol';

COMMENT on column arbol.id_estructura 
is 'Columna para brindar la informacion de la estructura del arbol';