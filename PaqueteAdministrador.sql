CREATE SEQUENCE s_dedicacion
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_pais
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_provincia
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_canton
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_distrito
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_hortaliza
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_color
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_tipo
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;

CREATE SEQUENCE s_propiedad
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_caracteristica
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;

CREATE SEQUENCE s_arbol
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_reproduccion
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_xilema
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_cambium
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
  
CREATE SEQUENCE s_corteza
  START WITH 0
  INCREMENT BY 1
  MINVALUE 0
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
--Nota no se insertan los id_huerta y  otros debido a la creacion de la secuencia
---Procedimientos para administrador 

select * from hortaliza

create or replace package paqPlantas as
       --Hortaliza
       procedure crearHortaliza (nombre varchar2, precio number, foto BLOB,id_huerta number,
                                 	id_color number, id_tipo number,id_propiedad number,
                                  id_caracteristica number, cantidad number );
       
       procedure editarHortaliza ( id_hortaliza number  ,nombre varchar2, precio number, foto BLOB,id_huerta number,
                                 	id_color number, id_tipo number, id_propiedad number,id_caracteristica number);
       procedure borrarHortaliza(id_hortaliza number);

       --Color
       procedure crearColor (nombre varchar2, descripcion varchar2 );
       procedure borrarColor (id_color number);
       PROCEDURE activarColor (id_color number);

       --Tipo
       procedure crearTipo(nombre varchar2, descripcion varchar2);
       procedure borrarTipo(id_tipo number);
       PROCEDURE activarTipo (id_tipo number);

       --Propiedad
       procedure crearPropiedad(nombre varchar2, descripcion varchar2);
       procedure borrarPropiedad(id_propiedad number );
       PROCEDURE activarPropiedad (id_propiedad number);
       
       --Caracteristica
       procedure crearCaracteristica(nombre varchar2, descripcion varchar2);
       procedure borrarCaracteristica(id_caracteristica number );
       PROCEDURE activarCaracteristica (id_caracteristica number);


       --Arbol
       procedure crearArbol (nombre varchar2, precio number, extincion number, foto BLOB, id_huerta number,
                            id_reproduccion number, id_xilema number, id_cambium number, 
                            id_corteza number,cantidad number );
       procedure editarArbol (id_arbol number,nombre varchar2, precio number, extincion number, foto BLOB, id_huerta number,
                            id_reproduccion number, id_xilema number, id_cambium number, id_corteza number);
       procedure borrarArbol (id_arbol NUMBER);

       --Reproduccion
       procedure crearReproduccion(nombre varchar2, descripcion varchar2);

       --Xilema
       procedure crearXilema(nombre varchar2, descripcion varchar2);

       --Cambium
       procedure crearCambium(nombre varchar2, descripcion varchar2);

       --Corteza
       procedure crearCorteza(nombre varchar2, descripcion varchar2);
       
       
       --Cantidades
       procedure agregarHortaliza (id_hortaliza number ,p_cantidad number );
       procedure agregarArbol(id_arbol number, p_cantidad number);
       procedure disminuirHortaliza (id_hortaliza number);
       procedure disminuirArbol(id_arbol number);

end paqPlantas;


CREATE OR REPLACE PACKAGE BODY paqPlantas as
PROCEDURE crearHortaliza (nombre varchar2, precio number, foto BLOB,id_huerta number,
                          id_color number, id_tipo number,id_propiedad number,
                          id_caracteristica number,cantidad number )
IS
BEGIN
  INSERT INTO hortaliza(id_hortaliza, nombre, precio, foto, id_huerta, id_color, id_tipo, id_propiedad,
                        id_caracteristica,cantidad )
  VALUES (s_hortaliza.nextval, nombre, precio, foto, id_huerta, id_color, 
  id_tipo, id_propiedad, id_caracteristica,cantidad);
  COMMIT;
END;
---------------------------------------------------------------------------------------------------
PROCEDURE editarHortaliza (id_hortaliza NUMBER, nombre varchar2, precio number, foto BLOB,id_huerta number,
                            id_color number, id_tipo number, id_propiedad number,id_caracteristica number)
IS
BEGIN
  UPDATE hortaliza
  SET nombre = nombre, precio = precio, foto = foto,
  id_huerta = id_huerta, id_color = id_color, id_tipo = id_tipo, id_propiedad = id_propiedad, id_caracteristica = id_caracteristica
  WHERE hortaliza.id_hortaliza = id_hortaliza;
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE borrarHortaliza(id_hortaliza number)
IS
BEGIN
  DELETE FROM hortaliza
  WHERE hortaliza.id_hortaliza = id_hortaliza;
  COMMIT;
  exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such hortaliza');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarHortaliza procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearColor (nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO color(id_color, nombre, descripcion, activo)
  VALUES (s_color.nextval, nombre, descripcion, 1);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE borrarColor (id_color number)
IS
BEGIN
  UPDATE color 
  SET activo = 0
  WHERE color.id_color = id_color;
  COMMIT;
  exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such color');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarColor procedure  ');
END;

PROCEDURE activarColor (id_color number)
IS
BEGIN
  UPDATE color 
  SET activo = 1
  WHERE color.id_color = id_color;
  COMMIT;
  exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such color');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarColor procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearTipo (nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO tipo(id_tipo, nombre, descripcion, activo)
  VALUES (s_tipo.nextval, nombre, descripcion, 1);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE borrarTipo (id_tipo number)
IS
BEGIN
  UPDATE tipo 
  SET activo = 0
  WHERE tipo.id_tipo = id_tipo;
  COMMIT;
  exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such tipo');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarTipo procedure  ');
END;

PROCEDURE activarTipo (id_tipo number)
IS
BEGIN
  UPDATE tipo 
  SET activo = 1
  WHERE tipo.id_tipo = id_tipo;
  COMMIT;
  exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such tipo');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarTipo procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearPropiedad(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO propiedad(id_propiedad, nombre, descripcion, activo)
  VALUES (s_propiedad.nextval, nombre, descripcion, 1);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE borrarPropiedad (id_propiedad number)
IS
BEGIN
  UPDATE propiedad 
  SET activo = 0
  WHERE propiedad.id_propiedad = id_propiedad;
  COMMIT;
    exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such Propiedad');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarPropiedad procedure  ');
END;

PROCEDURE activarPropiedad (id_propiedad number)
IS
BEGIN
  UPDATE propiedad 
  SET activo = 1
  WHERE propiedad.id_propiedad = id_propiedad;
  COMMIT;
    exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such Propiedad');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarPropiedad procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearCaracteristica(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO caracteristica(id_caracteristica, nombre, descripcion, activo)
  VALUES (s_caracteristica.nextval, nombre, descripcion, 1);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE borrarCaracteristica (id_caracteristica number)
IS
BEGIN
  UPDATE caracteristica 
  SET activo = 0
  WHERE caracteristica.id_caracteristica = id_caracteristica;
  COMMIT;
    exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such caracteristica');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarCaracteristica procedure  ');
END;

PROCEDURE activarCaracteristica (id_caracteristica number)
IS
BEGIN
  UPDATE caracteristica 
  SET activo = 1
  WHERE caracteristica.id_caracteristica = id_caracteristica;
  COMMIT;
    exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such caracteristica');
        WHEN others THEN
             dbms_output.put_line('Error executing BorrarCaracteristica procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearArbol (nombre varchar2, precio number, extincion number, foto BLOB, id_huerta number,
                      id_reproduccion number, id_xilema number, id_cambium number, 
                      id_corteza number,cantidad number )
IS
BEGIN
  INSERT INTO arbol(id_arbol, nombre, precio, extincion, foto, id_huerta, id_reproduccion, id_xilema,
                    id_cambium, id_corteza,cantidad )
  VALUES (s_arbol.nextval, nombre, precio, extincion, foto, id_huerta, id_reproduccion, id_xilema,
                    id_cambium, id_corteza,cantidad );
  COMMIT;

END;

---------------------------------------------------------------------------------------------------
PROCEDURE editarArbol (id_arbol number,nombre varchar2, precio number, extincion number, foto BLOB, id_huerta number,
                      id_reproduccion number, id_xilema number, id_cambium number, id_corteza number)
IS
BEGIN
  update arbol
  SET nombre = nombre,
  precio = precio,
  extincion = extincion,
  foto = foto,
  id_huerta = id_huerta,
  id_reproduccion = id_reproduccion,
  id_xilema = id_xilema,
  id_cambium = id_cambium,
  id_corteza = id_corteza
  WHERE arbol.id_arbol = id_arbol;
  COMMIT;

END;

----------------------------------------------------------------------------------------------------
PROCEDURE borrararbol (id_arbol number)
IS
BEGIN
  DELETE FROM arbol
  WHERE arbol.id_arbol = id_arbol;
  COMMIT;
    exception
        WHEN no_data_found THEN
             dbms_output.put_line('No such arbol');
        WHEN others THEN
             dbms_output.put_line('Error executing arbol procedure  ');
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearReproduccion(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO reproduccion(id_reproduccion, nombre, descripcion)
  VALUES (s_reproduccion.nextval, nombre, descripcion);
  COMMIT;
END;

----------------------------------------------------------------------------------------------------
PROCEDURE crearXilema(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO xilema(id_xilema, nombre, descripcion)
  VALUES (s_xilema.nextval, nombre, descripcion);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearCambium(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO Cambium(id_cambium, nombre, descripcion)
  VALUES (s_cambium.nextval, nombre, descripcion);
  COMMIT;
END;
----------------------------------------------------------------------------------------------------
PROCEDURE crearCorteza(nombre varchar2, descripcion varchar2 )
IS
BEGIN
  INSERT INTO corteza(id_corteza, nombre, descripcion)
  VALUES (s_corteza.nextval, nombre, descripcion);
  COMMIT;
END;

procedure agregarHortaliza (id_hortaliza number ,p_cantidad number )
  is 
  begin 
    update hortaliza 
    set cantidad =  cantidad + p_cantidad
    where id_hortaliza =  id_hortaliza;
    commit;
END;
    
procedure agregarArbol(id_arbol number, p_cantidad number)
  IS 
  BEGIN 
    update arbol
    set cantidad =  cantidad + p_cantidad
    where id_arbol =  id_arbol;
    commit;
end;

procedure disminuirHortaliza (id_hortaliza number)
  is 
  begin 
    update hortaliza 
    set cantidad =  cantidad - 1
    where id_hortaliza =  id_hortaliza;
    commit;
END;

procedure disminuirArbol(id_arbol number)
  IS 
  BEGIN 
    update arbol
    set cantidad =  cantidad -1
    where id_arbol =  id_arbol;
    commit;
end;

END paqPlantas;



------Triggers--------

----Hortaliza 
create or replace trigger beforeInsertHortaliza
before insert on hortaliza
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertHortaliza;
  
create or replace trigger beforeUpdateHortaliza
before update on hortaliza
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateHortaliza;
  

create or replace trigger beforeInsertColor 
before insert on color
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertColor;

create or replace trigger beforeInsertTipo
before insert on tipo
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertTipo;


create or replace trigger beforeInsertPropiedad
before insert on propiedad 
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertPropiedad;

create or replace trigger beforeInsertCaracteristica
before insert on caracteristica
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertCaracteristica;

---Arbol


create or replace trigger beforeInsertArbol
before insert on arbol
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertArbol;
  
create or replace trigger beforeUpdateArbol
before update on arbol
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateArbol;
  

create or replace trigger beforeInsertReproduccion 
before insert on reproduccion
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertReproduccion;
  
create or replace trigger beforeInsertXilema
before insert on xilema
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertXilema;
  
  
create or replace trigger beforeInsertCambium
before insert on cambium
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertCambium;
  
create or replace trigger beforeInsertCorteza
before insert on corteza
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertCorteza;


create synonym pack_Planta for huerta.paqPlanta;