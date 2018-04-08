--Tabla que lleva la información de las estructuras de los árboles
create table estructura(
       id_estructura  number(6),
       nombre           varchar2(20)constraint estructura_nombre not null,
       descripcion      varchar2(150) constraint estructura_descripcion not null       
);

--Declaración primary keys y Foreign keys
ALTER TABLE estructura
  ADD CONSTRAINT pk_estructura PRIMARY KEY (id_estructura)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Comentarios de tabla y columna
COMMENT on TABLE estructura
is 'Tabla para determinar el tipo de corteza,xile y cambrium que poseen';

COMMENT on column estructura.id_estructura
is 'Identificador primario de la estructura';

COMMENT on column estructura.nombre
is 'Columna para darle nombre a los tipos de estrucuta ';
 
COMMENT on column estructura.descripcion
is 'Columna para explicar las caracteristicas de la estructura del arbol';