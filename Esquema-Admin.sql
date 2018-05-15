CREATE USER Administrador
       IDENTIFIED BY Admin_Huerta_2018
       DEFAULT TABLESPACE HUERTA_Data
       QUOTA 10M ON HUERTA_Data
       TEMPORARY TABLESPACE temp
       QUOTA 5M ON system;
       --PROFILE app_user
       --PASSWORD EXPIRE
---------------------------------------------
GRANT CONNECT TO Administrador;
---------------------------------------------
grant create session to Administrador;
grant create table to Administrador;
grant unlimited tablespace to Administrador;
grant create procedure to Administrador;
grant create synonym to Administrador;
grant create sequence to Administrador;