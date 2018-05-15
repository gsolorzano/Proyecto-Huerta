--Paquete Abono 

--Secuencia 
--Abono 
CREATE SEQUENCE s_abono
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;
--Bitacora Bitacora Abono 
CREATE SEQUENCE s_bitabono
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  MAXVALUE 1000000000
  NOCACHE
  NOCYCLE;

create or replace package paqAbono as
--Abono
       procedure crearAbono(nombre varchar2, descripcion varchar2);
       procedure editarAbono(id_abono number, nombre varchar2, descripcion varchar2);
       procedure borrarAbono(id_abono number);
--Bitacora Abono 
       procedure crearRegistroAbono(id_abono number,id_persona number);
--ArbolXAbono 
       procedure registrarAbonoArbol(id_abono number, id_arbol number);
--HortalizaXAbono
       procedure registrarAbonoHortaliza(id_abono number, id_hortaliza number);
end paqAbono;

CREATE OR REPLACE PACKAGE BODY paqAbono AS
--Abono
    procedure crearAbono(nombre varchar2, descripcion varchar2) is
      begin
              insert into abono (id_abono, nombre, descripcion)
              values (s_abono.nextval, nombre, descripcion ); 
              commit;
      end;
    procedure editarAbono(id_abono number, nombre varchar2, descripcion varchar2)is
      begin
              update abono
              set nombre  = nombre,
                  descripcion =  descripcion
              where id_abono  = id_abono;
              commit;
      end;
    procedure borrarAbono(id_abono number) is
      begin
        delete from abono
        where id_abono =  id_abono;
        commit;
      exception 
                WHEN no_data_found THEN 
                     dbms_output.put_line('No such abono'); 
                WHEN others THEN 
                     dbms_output.put_line('Error executing Borrar Abono procedure  ');
      end;
      
--Bitacora Abono
       procedure crearRegistroAbono(id_abono number,id_persona number) is
         begin
           insert into bitacora_abono(id_bitacora ,fecha , id_persona, id_abono )
           values (s_bitabono.nextval, to_date(sysdate(),'DD/MM/YYYY'), id_persona, id_abono );
           commit;
         end;

--ArbolXAbono
       procedure registrarAbonoArbol(id_abono number, id_arbol number) is
         begin
           insert into abono_x_arbol(id_abono, id_arbol, fecha, id_bitacora)
           values (id_abono, id_arbol,to_date(sysdate(),'DD/MM/YYYY'), s_bitabono.currval );
           commit;
         end;
--HortalizaXAbono
       procedure registrarAbonoHortaliza(id_abono number, id_hortaliza number) is
         begin
           insert into hortaliza_x_abono(id_abono, id_hortaliza, fecha, id_bitacora)
           values (id_abono, id_hortaliza,to_date(sysdate(),'DD/MM/YYYY'), s_bitabono.currval );
           commit;
         end;

end paqAbono;



---Triggers 

create or replace trigger beforeInsertAbono
before insert on abono
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertAbono;
  
create or replace trigger beforeUpdateAbono 
before update on abono 
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateAbono;
------------------------------------------------------  

create or replace trigger beforeInsertBitacoraAbono
before insert on bitacora_abono
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertBitacoraAbono;
---------------------------------------------------------
create or replace trigger beforeInsertAbono_x_Arbol
before insert on abono_x_arbol
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertAbono_x_Arbol;

-------------------------------------------------------
create or replace trigger beforeInsertHortaliza_x_Abono
before insert on hortaliza_x_abono
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertHortaliza_x_Abono;

create synonym pack_Abono for huerta.paqAbono;