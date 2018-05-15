--Permisos que se ejecutan con system

GRANT CONNECT TO Administrador;
grant create session to Administrador;
grant create table to Administrador;
grant unlimited tablespace to Administrador;
grant create procedure to Administrador;
grant create synonym to Administrador;
grant create sequence to Administrador;
grant create trigger to Administrador;

GRANT CONNECT TO Huerta;
---------------------------------------------
grant create session to Huerta;
grant create table to Huerta;
grant unlimited tablespace to Huerta;
grant create procedure to Huerta;
grant create synonym to Huerta;
grant create sequence to Huerta;
grant create trigger to Huerta;

grant CREATE ANY TRIGGER to Huerta;

--Permisos que se ejecutan desde Administrador
grant insert,select, update on bitacora to Huerta;
grant references on bitacora to Huerta;

grant insert,select, update on auditoriageneral to Huerta;
grant references on auditoriageneral to Huerta;
