--Creación del usuario huerta, encargado de la creación de las tablas

CREATE USER Huerta
       IDENTIFIED BY Huerta_2018
       DEFAULT TABLESPACE HUERTA_Data
       QUOTA 10M ON HUERTA_Data
       TEMPORARY TABLESPACE temp
       QUOTA 5M ON system;
       --PROFILE app_user
       --PASSWORD EXPIRE
---------------------------------------------
GRANT CONNECT TO Huerta;
---------------------------------------------
--Permisos necesarios para la creacion de tablas
grant create session to Huerta;
grant create table to Huerta;
grant unlimited tablespace to Huerta;