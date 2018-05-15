
--Paquete usuario 
--Secuencia bitacora 
---Nota secuencias 

CREATE SEQUENCE s_bitacoraUser
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;

create or replace package paqUsuario as

--Usuario
       procedure crearUsuario(username varchar2, contrasena varchar2);
       procedure editarUsuario(id_usuario number, username varchar2);
       procedure borrarUsuario(id_usuario number);

--Actualizar Estado
       procedure actualizarEstado(id_usuario number, cambio number);
             
--Cambiar contrasena 
       procedure cambiarContrasena(id_usuario number, nueva varchar2, vieja varchar2);
       procedure accesoCuenta(p_username varchar2,contrasena varchar2, return_code OUT number);
--Exception
       no_user Exception;
       pass_error Exception;     
end paqUsuario;

CREATE OR REPLACE PACKAGE BODY paqUsuario AS
--Usuario
       procedure crearUsuario(username varchar2, contrasena varchar2) is
         begin
           insert into Administrador.bitacora (id_bitacora, nuevaclave, viejaclave, fecha )
           values(s_bitacoraUser.nextval,contrasena,contrasena, to_date(sysdate(),'DD/MM/YYYY'));
           commit;
           insert into usuario (id_usuario,estado,username,clave, id_bitacora)
           values(s_persona.nextval,0,username,contrasena,s_bitacoraUser.currval);
           commit;
         end;
       procedure editarUsuario(id_usuario number, username varchar2) is
         begin
           update usuario
           set username =  username
           where id_usuario =  id_usuario;
           commit;
           EXCEPTION
           WHEN no_data_found THEN 
                dbms_output.put_line('No such user'); 
           WHEN others THEN 
                dbms_output.put_line('Error executing Editar Usuario procedure');  
         end;
       procedure borrarUsuario(id_usuario number) is
         begin
           delete from Usuario
           where id_usuario = id_usuario;
           EXCEPTION 
             WHEN no_data_found THEN 
                dbms_output.put_line('No such user'); 
             WHEN others THEN 
                dbms_output.put_line('Error executing Borrar Usuario procedure');  
         end;

--Actualizar Estado
       procedure actualizarEstado(id_usuario number, cambio number) is
         begin
           update usuario
           set estado =  cambio
           where id_usuario =  id_usuario;
           commit;
           EXCEPTION 
             WHEN no_data_found THEN 
                dbms_output.put_line('No such user'); 
             WHEN others THEN 
                dbms_output.put_line('Error executing Actualizar Usuario procedure, remember 1 online 0 offline ');  
         end;
--Cambiar contrasena

FUNCTION bitnumb( id_usuario NUMBER) RETURN NUMBER
  IS
      bit  NUMBER;
   BEGIN
      SELECT id_bitacora
         INTO bit
         FROM usuario
      where usuario.id_usuario = id_usuario;
      RETURN(bit);
      EXCEPTION 
             WHEN no_data_found THEN 
                dbms_output.put_line('No such user'); 
             WHEN others THEN 
                dbms_output.put_line('Error executing bitNum function ');  
   END;


procedure cambiarContrasena(id_usuario number, nueva varchar2, vieja varchar2)is
      bitnum number;
   begin
     update usuario
     set clave =  nueva
     where id_usuario =  id_usuario;
    
     bitnum:= bitnumb(id_usuario);
     update Administrador.bitacora
     set nuevaClave =  nueva,
     viejaClave =  vieja,
     fecha =  to_date(sysdate(), 'DD/MM/YYYY')
     where id_bitacora = bitnum;
     EXCEPTION 
             WHEN no_data_found THEN 
                dbms_output.put_line('No such user'); 
             WHEN others THEN 
                dbms_output.put_line('Error executing bitNum function '); 
    end;

procedure accesoCuenta(p_username varchar2,contrasena varchar2, return_code OUT number)is
  pass varchar2(100);
  cursor c_prueba(p_username in varchar2) is 
  select clave 
  from usuario
  where username = p_username;     
  begin
    for i in c_prueba(p_username) loop
            pass:= i.clave;
    end loop;
    if contrasena = pass
       THEN return_code := 1;
    else
       raise pass_error;
    end if;
    EXCEPTION
             WHEN no_data_found THEN
                dbms_output.put_line('No such user');
             When pass_error then
                 dbms_output.put_line('Password incorrect');
             WHEN others THEN
                dbms_output.put_line('Error executing Acceso Contrasena function ');
 
    end;

end paqUsuario;



----Triggers 


create or replace trigger beforeInsertUsuario
before insert on usuario 
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertUsuario;

create or replace trigger beforeInsertBitacora
before insert on administrador.bitacora
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertBitacora;

create or replace trigger beforeUpdateUsuario
before update on usuario
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateUsuario;
  


create or replace trigger beforeUpdateBitacora
before update on administrador.bitacora
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateBitacora;

create synonym pack_Usuario for huerta.paqUsuario;