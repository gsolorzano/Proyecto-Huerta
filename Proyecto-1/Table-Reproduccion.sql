create table reproduccion(
       id_reproduccion  number(6),
       nombre           varchar2(20) constraint reproduccion_nombre not null,
       descripcion      varchar2(300) constraint reproduccion_descripcion not null        
);

Alter table reproduccion
      add creado_por varchar2 (100);

Alter table reproduccion
      add fech_creacion date ;

ALTER TABLE reproduccion
  ADD CONSTRAINT pk_reproduccion PRIMARY KEY (id_reproduccion)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

COMMENT on TABLE reproduccion
is 'Tabla para dividir los metodos de reproduccion de los arboles';

COMMENT on column reproduccion.id_reproduccion
is 'Identificador principal de la reproduccion';

COMMENT on column reproduccion.nombre
is 'Columna para darle nombre a los procesos de reproduccion';
 
COMMENT on column reproduccion.descripcion
is 'Columna para explicar los procesos de reproduccion del arbol';