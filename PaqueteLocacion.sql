---
---Nota secuencias 

create or replace package paqLocacion as

--Pais
       procedure crearPais (nombre varchar2);
       procedure borrarPais (id_pais number);

--Provincia
       procedure crearProvincia(nombre varchar2, id_pais number);
       procedure borrarProvincia(id_provincia number);
--Canton
       procedure crearCanton(nombre varchar2, id_provincia number);
       procedure borrarCanton(id_canton number);

--Distrito
       procedure crearDistrito(nombre varchar2, id_canton number);
       procedure borrarDistrito(id_distrito number);

end paqLocacion;

create or replace PACKAGE BODY paqLocacion as
PROCEDURE crearPais (nombre varchar2)
IS
BEGIN
  INSERT INTO pais(id_pais, nombre)
  VALUES (s_pais.nextval, nombre);
  COMMIT;
END;
--------------------------------------------------------------------------------------
PROCEDURE borrarPais(id_pais NUMBER)
IS
BEGIN
  DELETE FROM pais
  WHERE pais.id_pais = id_pais;
  COMMIT;
  EXCEPTION 
      WHEN no_data_found THEN 
          dbms_output.put_line('No such country'); 
      WHEN others THEN 
          dbms_output.put_line('Error executing BorrarPais function ');
END;
---------------------------------------------------------------------------------------
PROCEDURE crearProvincia (nombre varchar2, id_pais NUMBER)
IS
BEGIN
  INSERT INTO provincia(id_provincia, nombre, id_pais)
  VALUES (s_provincia.nextval, nombre, id_pais);
  COMMIT;
END;
----------------------------------------------------------------------------------------
PROCEDURE borrarProvincia(id_provincia NUMBER)
IS
BEGIN
  DELETE FROM provincia
  WHERE provincia.id_provincia = id_provincia;
  COMMIT;
  EXCEPTION 
      WHEN no_data_found THEN 
          dbms_output.put_line('No such provincia'); 
      WHEN others THEN 
          dbms_output.put_line('Error executing BorrarProvincia function ');
END;
----------------------------------------------------------------------------------------
PROCEDURE crearCanton (nombre varchar2, id_provincia NUMBER)
IS
BEGIN
  INSERT INTO canton(id_canton, nombre, id_provincia)
  VALUES (s_canton.nextval, nombre, id_provincia);
  COMMIT;
END;
----------------------------------------------------------------------------------------
PROCEDURE borrarCanton(id_canton NUMBER)
IS
BEGIN
  DELETE FROM canton
  WHERE canton.id_canton = id_canton;
  COMMIT;
  EXCEPTION 
      WHEN no_data_found THEN 
          dbms_output.put_line('No such state (AKA Canton)'); 
      WHEN others THEN 
          dbms_output.put_line('Error executing BorrarCanton function ');
END;
----------------------------------------------------------------------------------------
PROCEDURE crearDistrito (nombre varchar2, id_canton NUMBER)
IS
BEGIN
  INSERT INTO distrito(id_distrito, nombre, id_canton)
  VALUES (s_distrito.nextval, nombre, id_canton);
  COMMIT;
END;
----------------------------------------------------------------------------------------
PROCEDURE borrarDistrito(id_distrito NUMBER)
IS
BEGIN
  DELETE FROM distrito
  WHERE distrito.id_distrito = id_distrito;
  COMMIT;
  EXCEPTION 
      WHEN no_data_found THEN 
          dbms_output.put_line('No such district'); 
      WHEN others THEN 
          dbms_output.put_line('Error executing BorrarDistrito function ');
END;
END paqLocacion;

---Triggers 

create or replace trigger beforeInsertDistrito
before insert on distrito
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertDistrito;

create or replace trigger beforeInsertCanton
before insert on canton
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertCanton;

create or replace trigger beforeInsertProvincia 
before insert on provincia 
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertProvincia;
  
create or replace trigger beforeInsertPais
before insert on pais
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertPais;