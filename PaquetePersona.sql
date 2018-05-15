---
---Nota secuencias 

create or replace package paqPersona as 
--Persona
       procedure crearPersona(cedula number, nombre varchar2,last_name varchar2, email varchar2,
                              telefono number, fechaNac date, nacionalidad number,
                             foto BLOB, id_huerta number, id_dedicacion number,
                             username varchar2, contrasena varchar2 );
       procedure editarPersona(id_persona number,cedula number, nombre varchar2,last_name varchar2, email varchar2,
                              telefono number, fechaNac date, nacionalidad number,
                             foto BLOB, id_huerta number, id_dedicacion number );
       procedure borrarPersona(id_persona number);

--Dedicacion
       procedure crearDedicacion(nombre varchar2);
       procedure borrarDedicacion(id_dedicacion number);
       
       procedure insertarInteresado(idpersona number, idhuerta number);
       procedure borrarInteresado(idpersona number, idhuerta number);
    procedure crearVisitante(nombre varchar2,apellido varchar2,nacionalidad number);

end paqPersona;

create or replace PACKAGE BODY paqPersona as
       procedure crearPersona(cedula number, nombre varchar2,last_name varchar2, email varchar2,
                              telefono number, fechaNac date, nacionalidad number,
                             foto BLOB, id_huerta number, id_dedicacion number,
                            username varchar2, contrasena varchar2 ) IS
        BEGIN
            paqusuario.crearUsuario(username, contrasena); 
          insert into persona(id_persona,cedula, name , email, telefono, fechanac, nacionalidad,
                              foto, id_huerta, id_dedicacion, id_usuario, last_name)
          VALUES (s_persona.currval,cedula, nombre, email, telefono, fechanac, nacionalidad, foto, id_huerta, id_dedicacion, s_persona.currval, last_name);
          COMMIT;
         END;
---------------------------------------------------------------------------------------------


procedure editarPersona(id_persona number , cedula number, nombre varchar2,last_name varchar2, email varchar2,
                              telefono number, fechaNac date, nacionalidad number,
                             foto BLOB, id_huerta number, id_dedicacion number
                             ) IS

        BEGIN
          UPDATE persona
          SET cedula = cedula, name = nombre, email = email, telefono = telefono,
              fechanac = fechanac, nacionalidad = nacionalidad, foto = foto,
              id_huerta = id_huerta, id_dedicacion = id_dedicacion, last_name = last_name
          WHERE id_persona = id_persona;
          COMMIT;
          EXCEPTION
            WHEN no_data_found THEN
                dbms_output.put_line('No such persona');
            WHEN others THEN
                dbms_output.put_line('Error executing Editar Persona procedure  ');

        END;
-----------------------------------------------------------------------------------------------
PROCEDURE borrarPersona(id_persona NUMBER)
IS
BEGIN
  DELETE FROM persona
  WHERE persona.id_persona = id_persona;
  COMMIT;
  exception 
  WHEN no_data_found THEN 
       dbms_output.put_line('No such persona'); 
  WHEN others THEN 
       dbms_output.put_line('Error executing Editar Persona procedure  ');
END;
-------------------------------------------------------------------------------------------------
PROCEDURE crearDedicacion(nombre varchar2)
IS
BEGIN
  INSERT INTO dedicacion(id_dedicacion, nombre)
  VALUES (s_dedicacion.nextval, nombre);
  COMMIT;
END;
--------------------------------------------------------------------------------------------------
PROCEDURE borrarDedicacion(id_dedicacion number)
IS
BEGIN
  DELETE FROM dedicacion
  WHERE dedicacion.id_dedicacion = id_dedicacion;
  COMMIT;
  exception 
  WHEN no_data_found THEN 
       dbms_output.put_line('No such dedicacion'); 
  WHEN others THEN 
       dbms_output.put_line('Error executing Borrar Dedicacion  procedure  ');
END;

procedure insertarInteresado(idpersona number, idhuerta number)
IS
BEGIN
insert into interesadoxhuerta(id_persona, id_huerta)
VALUES (idpersona, idhuerta);
COMMIT;
END;

procedure borrarInteresado(idpersona number, idhuerta number)
IS
BEGIN
delete from interesadoxhuerta 
where id_persona = idpersona AND id_huerta = idhuerta; 
END;

procedure crearVisitante(nombre varchar2,apellido varchar2,nacionalidad number) IS
        BEGIN
          insert into persona(id_persona,cedula, name , email, telefono, fechanac, nacionalidad,
                               id_huerta, id_dedicacion, id_usuario, last_name)
          VALUES (s_persona.nextval,0, nombre, 'noreply', 0, sysdate(), nacionalidad,
          0,0, 0, apellido);
          COMMIT;
         END;

END paqPersona;
-----------------------------------------------------------------------------------------------------------------------------------------------------


---Triggers 

create or replace trigger beforeInsertPersona
before insert on persona
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertPersona ;

create or replace trigger beforeInsertDedicacion
before insert on dedicacion
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertDedicacion;

create or replace trigger beforeUpdatePersona
before update on persona 
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdatePersona;

create synonym pack_Persona for huerta.paqPersona;