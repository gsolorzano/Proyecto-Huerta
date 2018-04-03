--Tabla que lleva la información de las calificaciones de las huertas
CREATE TABLE calificacion(
  id_calificacion NUMBER(6),
  puntuacion NUMBER(6) NOT NULL,
  comentario VARCHAR2(100),
  fecha DATE DEFAULT SYSDATE CONSTRAINT calificacion_fecha NOT NULL,
  id_huerta NUMBER(6) NOT NULL
);

--Declaración primary keys y Foreign keys
ALTER TABLE calificacion
      ADD CONSTRAINT pk_calificacion PRIMARY KEY (id_calificacion)
      USING INDEX
      TABLESPACE huerta_Ind PCTFREE 20
      STORAGE (INITIAL 10K NEXT 10K PCTINCREASE 0);
      
ALTER TABLE calificacion
      ADD CONSTRAINT fk_calificacion_huerta FOREIGN KEY
      (id_huerta) REFERENCES huerta(id_huerta);
 
--Comentarios de tabla y columna     
COMMENT ON TABLE calificacion
IS 'Tabla que contiene la informacion de la calificacion';

COMMENT ON COLUMN calificacion.id_calificacion
IS 'Identificador primario de las calificacion.';

COMMENT ON COLUMN calificacion.puntuacion
IS 'Numero de 1 a 5 que indica la puntuacion.';

COMMENT ON COLUMN calificacion.comentario
IS 'El comentario opcional que puede dejar la persona.';

COMMENT ON COLUMN calificacion.fecha
IS 'Fecha en que se realizo el comentario.';

COMMENT ON COLUMN calificacion.id_huerta
IS 'Foreign key que guarda el id de la huerta en el comentario';