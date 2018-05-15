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
CREATE TABLESPACE huerta_Ind
       DATAFILE 'C:\app\Gabriel\oradata\Proyecto_Huerta\huertaInd01.dbf'
       SIZE 10M
       REUSE
       AUTOEXTEND ON
       NEXT 512k
       MAXSIZE 200M;