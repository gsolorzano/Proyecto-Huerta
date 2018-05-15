-- Paquete Huerta   
create or replace package paqHuerta as
--Huerta
       procedure crearHuerta(nombre varchar2, latitud VARCHAR2, longitud VARCHAR2, id_distrito number, capital number  );
       procedure editarHuerta(id_huerta number,nombre varchar2, latitud VARCHAR2, longitud VARCHAR2, id_distrito number);
       procedure borrarHuerta(id_huerta number);

--Calificacion
       procedure crearCalificacion(puntuacion number, comentario varchar2, id_huerta number);
       procedure borrarCalificacion(id_calificacion number);
       procedure getPromedioHuerta(p_huerta number, res out number);
       
end paqHuerta;

select * from huerta

CREATE OR REPLACE PACKAGE BODY paqHuerta AS
--Huerta
          procedure crearHuerta (nombre varchar2, latitud VARCHAR2, longitud VARCHAR2,
                                 id_distrito number, capital number  ) is
            begin
              insert into huerta(id_huerta,nombre,latitud,longitud,id_distrito, capital)
              values (s_huerta.nextval, nombre, latitud, longitud, id_distrito, capital);
              commit;
            end crearHuerta;
            
          procedure editarHuerta(id_huerta number,nombre varchar2, latitud VARCHAR2, 
                    longitud VARCHAR2, id_distrito number) is
            begin
              update huerta
              set nombre = nombre,
              latitud = latitud,
              longitud = longitud,
              id_distrito = id_distrito
              where id_huerta = id_huerta;
              commit;
            end editarHuerta;

          procedure borrarHuerta(id_huerta number) is
            begin
              delete from huerta
              where id_huerta = id_huerta;
              commit;
              exception 
                WHEN no_data_found THEN 
                     dbms_output.put_line('No such huerta'); 
                WHEN others THEN 
                     dbms_output.put_line('Error executing HuertaPersona procedure  ');
            end borrarHuerta;

--Calificacion
          procedure crearCalificacion(puntuacion number, comentario varchar2,
                                       id_huerta number) is
          begin
            insert into calificacion (id_calificacion, puntuacion , comentario , fecha,
                                      id_huerta )
            values (s_calificacion.nextval,puntuacion, comentario, to_date(sysdate(),'DD/MM/YYYY'),id_huerta );
            commit;
          end crearCalificacion;

          procedure borrarCalificacion(id_calificacion number ) is
            begin
              delete from calificacion
              where id_calificacion = id_calificacion;
              commit;
              exception 
                WHEN no_data_found THEN 
                     dbms_output.put_line('No such calificacion'); 
                WHEN others THEN 
                     dbms_output.put_line('Error executing BorrarCalificacion procedure  ');
            end borrarCalificacion;
            
            procedure getPromedioHuerta(p_huerta number, res out number)
              is 
              promedio number;
              cursor c_prueba(p_huerta in number) is
              select avg(puntuacion) estimado
              from calificacion
              where id_huerta = p_huerta;
              begin 
                    for i in c_prueba(p_huerta) loop
                    promedio := i.estimado;
                    end loop;
              res:= promedio;
              end getPromedioHuerta; 
         
end paqHuerta;

---Triggers 

create or replace trigger beforeInsertHuerta
before insert on huerta 
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertHuerta;

create or replace trigger beforeInsertCalificacion
before insert on calificacion
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertCalificacion;

create or replace trigger beforeUpdateHuerta
before update on huerta
for each row 
  begin 
    :new.editado_por:= USER;
    :new.fech_edicion:=SYSDATE;
  end beforeUpdateHuerta;

create synonym pack_Huerta for huerta.paqHuerta