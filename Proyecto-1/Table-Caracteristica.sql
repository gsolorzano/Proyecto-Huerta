create table caracteristica(
      id_caracteristica    number (6),
      nombre               varchar(20) CONSTRAINT caracteristica_nombre NOT NULL,
      descripcion          varchar(300)CONSTRAINT caracteristica_descripcion NOT NULL,
      activo number(6) NOT NULL
);

Alter table caracteristica
      add creado_por varchar2 (100);

Alter table caracteristica
      add fech_creacion date ;

ALTER TABLE caracteristica
  ADD CONSTRAINT pk_caracteristica PRIMARY KEY (id_caracteristica)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
  
COMMENT ON TABLE caracteristica
is 'Tabla para determinar si la hortaliza es medicinal, aromatica, etc'; 

COMMENT ON COLUMN caracteristica.id_caracteristica
is 'Columna para identificar la caracteristica de la hortaliza';

COMMENT ON COLUMN caracteristica.nombre
is 'Columna que pertenece al nombre de la caracteristica de la hortaliza';

COMMENT ON COLUMN caracteristica.descripcion
is 'Columna que se utiliza para describir la caracteristica de la hortaliza';