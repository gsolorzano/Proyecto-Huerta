--Tabla que lleva la información de las características de las hortalizas
create table caracteristica(
      id_caracteristica    number (6),
      nombre               varchar(20) CONSTRAINT caracteristica_nombre NOT NULL,
      descripcion          varchar(100)CONSTRAINT caracteristica_descripcion NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE caracteristica
  ADD CONSTRAINT pk_caracteristica PRIMARY KEY (id_caracteristica)
  USING INDEX
  TABLESPACE huerta_ind PCTFREE 20
  STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
  
--Comentarios de tabla y columna
COMMENT ON TABLE caracteristica
is 'Tabla para determinar si la hortaliza es medicinal, aromatica, etc'; 

COMMENT ON COLUMN caracteristica.id_caracteristica
is 'Columna para identificar la caracteristica de la hortaliza';

COMMENT ON COLUMN caracteristica.nombre
is 'Columna que pertenece al nombre de la caracteristica de la hortaliza';

COMMENT ON COLUMN caracteristica.descripcion
is 'Columna que se utiliza para describir la caracteristica de la hortaliza';