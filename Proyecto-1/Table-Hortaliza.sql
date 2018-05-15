CREATE TABLE hortaliza(
  id_hortaliza NUMBER(6),
  nombre VARCHAR2(20) CONSTRAINT hortaliza_nombre NOT NULL,
  precio NUMBER(20) NOT NULL,
  foto BLOB DEFAULT EMPTY_BLOB(),
  id_huerta NUMBER(6) NOT NULL,
  id_color NUMBER(6) NOT NULL,
  id_tipo NUMBER(6) NOT NULL,
  id_propiedad NUMBER(6) NOT NULL,
  id_caracteristica NUMBER(6)
);

Alter table hortaliza
      ADD  cantidad  number constraint cantidad_hortaliza_nn not null;

Alter table hortaliza
      add creado_por varchar2 (100);

Alter table hortaliza
      add fech_creacion date ;

Alter table hortaliza
      add editado_por varchar2 (100);

Alter table hortaliza
      add fech_edicion date ;

ALTER TABLE hortaliza
      ADD CONSTRAINT pk_hortaliza PRIMARY KEY (id_hortaliza)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
  
ALTER TABLE hortaliza
      ADD CONSTRAINT fk_hortaliza_huerta FOREIGN KEY
      (id_huerta) REFERENCES huerta(id_huerta);

ALTER TABLE hortaliza
      ADD CONSTRAINT fk_hortaliza_caracteristica FOREIGN KEY
      (id_caracteristica) REFERENCES caracteristica(id_caracteristica);
      
ALTER TABLE hortaliza
      ADD CONSTRAINT fk_hortaliza_color FOREIGN KEY
      (id_color) REFERENCES color(id_color);

ALTER TABLE hortaliza
      ADD CONSTRAINT fk_hortaliza_tipo FOREIGN KEY
      (id_tipo) REFERENCES tipo(id_tipo);
      
ALTER TABLE hortaliza
      ADD CONSTRAINT fk_propiedad_tipo FOREIGN KEY
      (id_propiedad) REFERENCES propiedad(id_propiedad);

COMMENT ON TABLE hortaliza
IS 'Tabla que contiene la informacion de las hortalizas';

COMMENT ON COLUMN hortaliza.id_hortaliza
IS 'Identificador primario de la hortaliza.';

COMMENT ON COLUMN hortaliza.nombre
IS 'El nombre de la hortaliza.';

COMMENT ON COLUMN hortaliza.precio
IS 'Precio de la hortaliza.';

COMMENT ON COLUMN hortaliza.foto
IS 'Foto ilustrativa de la hortaliza.';

COMMENT ON COLUMN hortaliza.id_color
IS 'Foreign key del id del color.';

COMMENT ON COLUMN hortaliza.id_tipo
IS 'Foreign key del id del tipo.';

COMMENT ON COLUMN hortaliza.id_huerta
IS 'Foreign key del id de huerta.';

COMMENT ON COLUMN hortaliza.id_caracteristica
IS 'Foreign key del id de la caracteristica.';

COMMENT ON COLUMN hortaliza.id_propiedad
IS 'Foreign key del id de la propiedad.';