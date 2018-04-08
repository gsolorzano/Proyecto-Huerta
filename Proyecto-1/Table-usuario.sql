--Tabla que lleva la información de los usuarios
create table usuario (
       id_usuario number(6),
       estado     number(1),
       username   varchar2(20)  constraint username_bitacora not null,
       clave      varchar2 (50) constraint clave_bitacora not null,
       id_bitacora number(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE usuario
  ADD CONSTRAINT pk_usuario PRIMARY KEY (id_usuario)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

ALTER TABLE usuario
      ADD CONSTRAINT fk_bitacora_usuario FOREIGN KEY
      (id_bitacora) REFERENCES bitacora(id_bitacora);

--Comentarios de tabla y columna
COMMENT ON TABLE usuario
is 'Tabla que contiene los usuario relacionados con las huertas de las cuales forman parte  ';

COMMENT ON COLUMN usuario.estado
is 'Identificador para saber si el usuario esta conectado';

COMMENT ON COLUMN usuario.id_usuario
is 'Identificacion de cada una de las tuplas que contienen la tabla usuario';

COMMENT ON COLUMN usuario.username
is 'Nombre de usuario de la persona';

COMMENT ON COLUMN usuario.clave
is 'Contrasena del usuario';

COMMENT ON COLUMN usuario.id_bitacora
is 'Columna de la relacion entre la bitacora y el usuario';