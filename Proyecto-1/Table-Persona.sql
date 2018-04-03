--Tabla que lleva la información de las personas en general
create table persona (
       cedula   number(10),
       name     varchar2(20) constraint name_persona not null,
       email    VARCHAR2(25) CONSTRAINT employee_email_nn NOT NULL,
                CONSTRAINT   employee_email_uk   UNIQUE (email),
       telefono number(10) NOT NULL,
       fechaNac date DEFAULT SYSDATE CONSTRAINT fecha_persona_nn NOT NULL,
       nacionalidad number (6) NOT NULL,
       foto     varchar2(200) constraint foto_persona not null,
       interesado number(1) NOT NULL,
       id_huerta number(6) NOT NULL,
       id_dedicacion number(6) NOT NULL,
       id_usuario    number(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE persona
  ADD CONSTRAINT pk_persona PRIMARY KEY (cedula)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);

ALTER TABLE persona
      ADD CONSTRAINT fk_huerta_persona FOREIGN KEY
      (id_huerta) REFERENCES huerta(id_huerta);
      
ALTER TABLE persona
      ADD CONSTRAINT fk_usuario_persona FOREIGN KEY
      (id_usuario) REFERENCES usuario(id_usuario);
      
ALTER TABLE persona
      ADD CONSTRAINT fk_dedicacion_persona FOREIGN KEY
      (id_dedicacion) REFERENCES dedicacion(id_dedicacion);

--Comentarios de tabla y columna
COMMENT ON TABLE persona
is 'Tabla que contiene las personas de las huertas';

COMMENT ON COLUMN persona.cedula
is 'Identificacion de cada una de las de las personas de las huertas';

COMMENT ON COLUMN persona.name
is 'Nombre de la persona';

COMMENT ON COLUMN persona.email
is 'Email de la persona ';

COMMENT ON COLUMN persona.telefono
is 'Telefono de la persona';

COMMENT ON COLUMN persona.fechanac
is 'Fecha de nacimiento de la persona';

COMMENT ON COLUMN persona.nacionalidad
is 'Nacionalidad de la persona';

COMMENT ON COLUMN persona.foto
is 'Incluye el url de la foto de perfil de la persona';

COMMENT ON COLUMN persona.interesado
is 'Columna binaria para representar el interes de la persona en la huerta';

COMMENT ON COLUMN persona.id_huerta
is 'Representa la huerta en la que colabora';

COMMENT ON COLUMN persona.id_dedicacion
is 'Representa el rol de la persona';

COMMENT ON COLUMN persona.id_usuario
is 'Indica el usuario asociado a la persona ';