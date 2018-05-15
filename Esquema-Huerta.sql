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
grant create session to Huerta;
grant create table to Huerta;
grant unlimited tablespace to Huerta;
grant create procedure to Huerta;
grant create synonym to Huerta;
grant create sequence to Huerta;