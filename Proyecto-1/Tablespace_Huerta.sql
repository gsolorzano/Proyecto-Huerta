--Crear el tablespace para trabajar sobre la BD
CREATE TABLESPACE HUERTA_data
       DATAFILE 'C:\app\Gabriel\oradata\Proyecto_Huerta\huertaData01.dbf'
       SIZE 10M
       REUSE
       AUTOEXTEND ON
       NEXT 512k
       MAXSIZE 200M;
--
-- PE: INDEX
--
--Crear un tables space de indices para las tablas
CREATE TABLESPACE huerta_Ind
       DATAFILE 'C:\app\Gabriel\oradata\Proyecto_Huerta\huertaInd01.dbf'
       SIZE 10M
       REUSE
       AUTOEXTEND ON
       NEXT 512k
       MAXSIZE 200M;