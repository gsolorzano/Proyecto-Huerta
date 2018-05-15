--Xilema 
create table xilema(
       id_xilema        number(6),
       nombre           varchar2(20)constraint xilema_nombre not null,
       descripcion      varchar2(300) constraint xilema_descripcion not null       
);

Alter table xilema
      add creado_por varchar2 (100);

Alter table xilema
      add fech_creacion date ;

ALTER TABLE xilema 
  ADD CONSTRAINT pk_xilema PRIMARY KEY (id_xilema)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Cambium
create table cambium(
       id_cambium        number(6),
       nombre           varchar2(20)constraint cambium_nombre not null,
       descripcion      varchar2(300) constraint cambium_descripcion not null       
);

Alter table cambium 
      add creado_por varchar2 (100);

Alter table cambium
      add fech_creacion date ;

ALTER TABLE cambium
  ADD CONSTRAINT pk_cambium PRIMARY KEY (id_cambium)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);  
  
--Corteza 

create table corteza (
       id_corteza       number(6),
       nombre           varchar2(20)constraint corteza_nombre not null,
       descripcion      varchar2(300) constraint corteza_descripcion not null       
);

Alter table corteza 
      add creado_por varchar2 (100);

Alter table corteza 
      add fech_creacion date ;

ALTER TABLE corteza
  ADD CONSTRAINT pk_corteza PRIMARY KEY (id_corteza)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);  


---------------Comentarios
COMMENT on TABLE xilema
is 'Tabla para determinar el tipo de xilema';

COMMENT on column xilema.id_xilema
is 'Identificador primario de la estructura';

COMMENT on column xilema.nombre
is 'Columna para darle nombre a los tipos de estrucuta ';
 
COMMENT on column xilema.descripcion
is 'Columna para explicar las caracteristicas de la estructura del arbol';




COMMENT on TABLE cambium
is 'Tabla para determinar el tipo de cambrium que poseen';

COMMENT on column cambium.id_cambium
is 'Identificador primario de la estructura';

COMMENT on column cambium.nombre
is 'Columna para darle nombre a los tipos de estrucuta ';
 
COMMENT on column cambium.descripcion
is 'Columna para explicar las caracteristicas de la estructura del arbol';



COMMENT on TABLE corteza
is 'Tabla para determinar el tipo de corteza que poseen';

COMMENT on column corteza.id_corteza
is 'Identificador primario de la estructura';

COMMENT on column corteza.nombre
is 'Columna para darle nombre a los tipos de estrucuta ';
 
COMMENT on column corteza.descripcion
is 'Columna para explicar las caracteristicas de la estructura del arbol';