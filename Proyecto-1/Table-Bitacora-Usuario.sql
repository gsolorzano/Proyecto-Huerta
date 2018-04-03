--Tabla que lleva la información de la bitácora de los usuarios
create table bitacora(
       id_bitacora number(6),
       nuevaClave  varchar2(50) constraint bitacora_nc not null,
       viejaClave  varchar2(50) constraint bitacora_vc not null
);

--Declaración primary keys y Foreign keys
ALTER TABLE bitacora 
  ADD CONSTRAINT pk_bitacora PRIMARY KEY (id_bitacora)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

--Comentarios de tabla y columna
COMMENT ON TABLE bitacora
is 'Tabla que contiene las bitacora de los cambios de clave de los usuarios';

COMMENT ON COLUMN bitacora.id_bitacora
is 'Identificacion de cada una de las tuplas que contienen la tabla bitacora';

COMMENT ON COLUMN bitacora.nuevaclave
is 'La nueva clave del usuario';

COMMENT ON COLUMN bitacora.viejaclave
is 'La anterior clave del usuario';