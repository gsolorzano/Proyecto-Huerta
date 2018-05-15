--Paquete trueque y venta  
 
create or replace package ventatruequePaq as
--Venta
       procedure crearVenta (id_comprador number, res out number);
       procedure borrarVenta (id_venta number);
--Trueque
       function crearTrueque(id_trader number, id_huerta number) RETURN number;  
       procedure borrarTrueque(id_trueque number);
--Arbol
       procedure crearVentaArbol (id_arbol number, id_venta number);
       procedure borrarVentaArbol (id_arbol number, id_venta number);

       procedure crearTruequeArbolAA(id_arbol1 number, id_arbol2 number); 
       procedure crearTruequeArbolAH(id_arbol1 number, id_hortaliza number); 
      
       procedure borrarTruequeArbol(id_arbol number, id_trueque number);   


--Hortaliza
       procedure crearVentaHortaliza (id_hortaliza number, id_venta number);
       procedure borrarVentaHortaliza (id_hortaliza number, id_venta number);

       procedure crearTruequeHortalizaHH(id_hortaliza1 number,id_hortaliza2 number);
       procedure crearTruequeHortalizaHA(id_hortaliza1 number, id_arbol number);
       procedure borrarTruequeHortaliza (id_hortaliza number, id_trueque number);
end ventatruequePaq;


create or replace package body ventatruequePaq as
--Funcion
function numeroHuertaArbol(id_arbol number) return number is
         huertaNo number;
         begin
           select id_huerta
           into huertaNo
           from arbol
           where id_arbol =  id_arbol;
           return (huertaNo);
             Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_Arbol in NHA');
              WHEN others THEN
                   dbms_output.put_line('Error executing numeroHuertaArbol function');
         end;

function numeroHuertaHortaliza(id_hortaliza number) return number is
         huertaNo number;
         begin
           select id_huerta
           into huertaNo
           from hortaliza
           where id_hortaliza =  id_hortaliza ;
           return (huertaNo);
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_huerta in NHH');
              WHEN others THEN
                   dbms_output.put_line('Error executing numeroHuertaHortaliza function');
        end;
function precioArbol  (id_arbol number) return number is
         cantidad number;
         begin
           select precio
           into cantidad
           from arbol
           where id_arbol =  id_arbol;
           return (cantidad);
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_Arbol in PA');
              WHEN others THEN
                   dbms_output.put_line('Error executing Precio arbol function');
         end;

function precioHortaliza (id_hortaliza number) return number is
         cantidad number;
         begin
           select precio
           into cantidad
           from hortaliza
           where id_hortaliza =  id_hortaliza;
           return (cantidad);
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_hortaliza in PA');
              WHEN others THEN
                   dbms_output.put_line('Error executing Precio hortaliza function');
         end;
function cantidadHortaliza (id_hortaliza number) return number is
         p_cantidad number;
         begin
           select cantidad
           into p_cantidad
           from hortaliza
           where id_hortaliza =  id_hortaliza;
           return (p_cantidad);
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_hortaliza in PA');
              WHEN others THEN
                   dbms_output.put_line('Error executing Cantidad hortaliza function');
         end;

function cantidadArbol (id_arbol number) return number is
         p_cantidad number;
         begin
           select cantidad
           into p_cantidad
           from arbol
           where id_arbol =  id_arbol;
           return (p_cantidad);
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_arbol in PA');
              WHEN others THEN
                   dbms_output.put_line('Error executing Cantidad Arbol function');
         end;



procedure transaccion (p_huerta number,  ganancia number)  is
         begin
           update huerta
           set capital = capital + ganancia
           where id_huerta =  p_huerta;
         end;


--Venta
       procedure crearVenta (id_comprador number, res out number) is
         begin
           insert into venta (id_venta, fecha, id_comprador)
           values (s_venta.nextval, to_date(sysdate(),'DD/MM/YYYY'),id_comprador);
           commit;
           res:= s_venta.currval;
         EXCEPTION
           when others then
             dbms_output.put_line('ERROR');
         end;

       procedure borrarVenta (id_venta number) is
         begin
           delete from venta
           where id_venta =  id_venta;
           commit;
         Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_venta');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarVenta function');
         end;
--Trueque
       function crearTrueque(id_trader number, id_huerta number) return number is
         begin
           insert into trueque (id_trueque, fecha, id_comprador, id_huerta)
           values (s_trueque.nextval, to_date(sysdate(),'DD/MM/YYYY') ,id_trader, id_huerta );
           commit;
           return (s_trueque.currval);
         EXCEPTION
           when others then
             dbms_output.put_line('ERROR');
         end;

       procedure borrarTrueque(id_trueque number) is
         begin
           delete from trueque
           where id_trueque =  id_trueque;
           commit;
         Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_trueque');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarVenta function');
         end;


--Arbol
       procedure crearVentaArbol (id_arbol number, id_venta number) is
         precio number;
         huertaId number;
         pcantidadArbol number;
         begin
           insert into arbol_x_venta(id_arbol,id_venta)
           values (id_arbol, id_venta);
           precio:=  precioArbol(id_arbol);
           huertaID:= numeroHuertaArbol (id_arbol);
           transaccion(huertaID, precio);
           paqplantas.disminuirArbol(id_arbol);
           pcantidadArbol := cantidadArbol(id_arbol );
           IF pcantidadArbol = 0
               THEN paqplantas.borrarArbol(id_arbol);
            END IF;
           commit;
         EXCEPTION
           when others then
             dbms_output.put_line('ERROR CVA');
         end;

       procedure borrarVentaArbol (id_arbol number, id_venta number) is
         begin
           delete from arbol_x_venta
           where id_arbol =  id_arbol and id_venta = id_venta;
           commit;
         Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_arbol or id_venta');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarVentaArbol procedure ');
         end;
--Trueque arbol
       procedure crearTruequeArbolAA(id_arbol1 number, id_arbol2 number) is
         huerta1 number;
         huerta2 number;
         cantidad number;
         begin
           insert into arbol_x_trueque ( id_arbol, id_trueque)
           values (id_arbol1, s_trueque.currval);
           insert into arbol_x_trueque ( id_arbol, id_trueque)
           values (id_arbol2, s_trueque.currval);
           huerta1:= numeroHuertaArbol(id_arbol1);
           huerta2:= numeroHuertaArbol(id_arbol2);
           -----------------------------------------
           paqplantas.disminuirArbol(id_arbol1);
           insert into arbol (id_arbol, nombre,precio,extincion,id_huerta,id_reproduccion,id_xilema,
           id_cambium,id_corteza,foto,cantidad)
           select s_arbol.nextval,nombre, precio, extincion, huerta2, id_reproduccion, id_xilema,
           id_cambium,id_corteza,foto,1 from arbol where id_arbol = id_arbol1;
           cantidad:= cantidadArbol(id_arbol1);
           if cantidad =  0
             then paqplantas.borrarArbol(id_arbol1);
           end if;
           -----------------------------------------
           paqplantas.disminuirArbol(id_arbol2);
           insert into arbol (id_arbol, nombre,precio,extincion,id_huerta,id_reproduccion,id_xilema,
           id_cambium,id_corteza,foto,cantidad)
           select s_arbol.nextval,nombre, precio, extincion, huerta1, id_reproduccion, id_xilema,
           id_cambium,id_corteza,foto,1 from arbol where id_arbol = id_arbol2;
           cantidad:= cantidadArbol(id_arbol2);
           if cantidad =  0
             then paqplantas.borrarArbol(id_arbol2);
           end if;
           -----------------------------------------
         end;

         procedure crearTruequeArbolAH(id_arbol1 number, id_hortaliza number) is
         huerta1 number;
         huerta2 number;
         cantidad number;
         begin
           insert into arbol_x_trueque ( id_arbol, id_trueque)
           values (id_arbol1, s_trueque.currval);
           insert into hortaliza_x_trueque(id_hortaliza, id_trueque)
           values (id_hortaliza, s_trueque.currval);
           huerta1:= numeroHuertaArbol(id_arbol1);
           huerta2:= numeroHuertaHortaliza(id_hortaliza);
           -----------------------------------------
           paqplantas.disminuirArbol(id_arbol1);
           insert into arbol (id_arbol, nombre,precio,extincion,id_huerta,id_reproduccion,id_xilema,
           id_cambium,id_corteza,foto,cantidad)
           select s_arbol.nextval,nombre, precio, extincion, huerta2, id_reproduccion, id_xilema,
           id_cambium,id_corteza,foto,1 from arbol where id_arbol = id_arbol1;
           cantidad:= cantidadArbol(id_arbol1);
           if cantidad =  0
             then paqplantas.borrarArbol(id_arbol1);
           end if;
           -----------------------------------------
           paqplantas.disminuirHortaliza(id_hortaliza);
           insert into hortaliza(id_hortaliza,nombre,precio,id_huerta,id_color,id_tipo,id_propiedad,
           id_caracteristica,foto,cantidad)
           select s_hortaliza.nextval,nombre,precio,huerta1,id_color, id_tipo, id_propiedad,
           id_caracteristica, foto,1 from hortaliza where id_hortaliza = id_hortaliza;
           cantidad:= cantidadHortaliza(id_hortaliza);
           if cantidad =  0
             then paqplantas.borrarHortaliza(id_hortaliza);
           end if;

           -----------------------------------------
         end;

       procedure borrarTruequeArbol (id_arbol number, id_trueque number) is
         begin
           delete from arbol_x_trueque
           where id_arbol =id_arbol and id_trueque = id_trueque;
           commit;
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_arbol or id_trueque');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarTruequeArbol procedure ');
         end;

--Hortaliza
       procedure crearVentaHortaliza (id_hortaliza number, id_venta number) is
         precio number;
         huertaId number;
         pcantidadHortaliza number;
         begin
           insert into hortaliza_x_venta(id_hortaliza, id_venta)
           values (id_hortaliza, id_venta);
           precio:=  precioHortaliza(id_hortaliza);
           huertaID:= numeroHuertaHortaliza (id_hortaliza);
           transaccion(huertaID, precio);
           Paqplantas.disminuirHortaliza(id_hortaliza);
           pcantidadHortaliza := cantidadHortaliza(id_hortaliza);
           if pcantidadHortaliza  = 0
             then  paqPlantas.borrarHortaliza(id_hortaliza );
           end if;
           commit;
           EXCEPTION
           when others then
             dbms_output.put_line('ERROR');
         end;

       procedure borrarVentaHortaliza (id_hortaliza number, id_venta number) is
         begin
           delete from hortaliza_x_venta
           where id_hortaliza = id_hortaliza and id_venta = id_venta;
           commit;
         Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_hortaliza or id_venta');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarVentaHortaliza procedure ');
         end;

       procedure crearTruequeHortalizaHH(id_hortaliza1 number,
                                         id_hortaliza2 number) is
         huerta1 number;
         huerta2 number;
         cantidad number;
         begin
           insert into hortaliza_x_trueque (id_hortaliza, id_trueque)
           values (id_hortaliza1, s_trueque.currval);
           insert into hortaliza_x_trueque(id_hortaliza, id_trueque)
           values (id_hortaliza2,s_trueque.currval);
           huerta1:= numeroHuertaHortaliza(id_hortaliza1);
           huerta2:= numeroHuertaHortaliza(id_hortaliza2);
           -----------------------------------------
           paqplantas.disminuirHortaliza(id_hortaliza1);
           insert into hortaliza(id_hortaliza,nombre,precio,id_huerta,id_color,id_tipo,id_propiedad,
           id_caracteristica,foto,cantidad)
           select s_hortaliza.nextval,nombre,precio,huerta2,id_color, id_tipo, id_propiedad,
           id_caracteristica, foto,1 from hortaliza where id_hortaliza = id_hortaliza1;
           cantidad:= cantidadArbol(id_hortaliza1);
           if cantidad =  0
             then paqplantas.borrarHortaliza(id_hortaliza1);
           end if;
           -----------------------------------------
           paqplantas.disminuirHortaliza(id_hortaliza2);
           insert into hortaliza(id_hortaliza,nombre,precio,id_huerta,id_color,id_tipo,id_propiedad,
           id_caracteristica,foto,cantidad)
           select s_hortaliza.nextval,nombre,precio,huerta1,id_color, id_tipo, id_propiedad,
           id_caracteristica, foto,1 from hortaliza where id_hortaliza = id_hortaliza2;
           cantidad:= cantidadHortaliza(id_hortaliza2);
           if cantidad =  0
             then paqplantas.borrarHortaliza(id_hortaliza2);
           end if;
           Exception
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarTrueuqueHortaliza HA procedure ');

           -----------------------------------------
         end;

         procedure crearTruequeHortalizaHA(id_hortaliza1 number, id_arbol number) is
           huerta1 number;
           huerta2 number;
           cantidad number;
         begin
           insert into hortaliza_x_trueque (id_hortaliza, id_trueque)
           values (id_hortaliza1, s_trueque.currval);
           insert into arbol_x_trueque(id_arbol, id_trueque)
           values (id_arbol, s_trueque.currval);
           huerta1:= numeroHuertaHortaliza(id_hortaliza1);
           huerta2:= numeroHuertaArbol(id_arbol);
           -----------------------------------------
           paqplantas.disminuirHortaliza(id_hortaliza1);
           insert into hortaliza(id_hortaliza,nombre,precio,id_huerta,id_color,id_tipo,id_propiedad,
           id_caracteristica,foto,cantidad)
           select s_hortaliza.nextval,nombre,precio,huerta2,id_color, id_tipo, id_propiedad,
           id_caracteristica, foto,1 from hortaliza where id_hortaliza = id_hortaliza1;
           cantidad:= cantidadArbol(id_hortaliza1);
           if cantidad =  0
             then paqplantas.borrarHortaliza(id_hortaliza1);
           end if;
           -----------------------------------------
           paqplantas.disminuirArbol(id_arbol);
           insert into arbol (id_arbol, nombre,precio,extincion,id_huerta,id_reproduccion,id_xilema,
           id_cambium,id_corteza,foto,cantidad)
           select s_arbol.nextval,nombre, precio, extincion, huerta1, id_reproduccion, id_xilema,
           id_cambium,id_corteza,foto,1 from arbol where id_arbol = id_arbol;
           cantidad:= cantidadArbol(id_arbol);
           if cantidad =  0
             then paqplantas.borrarArbol(id_arbol);
           end if;
           commit;
           -----------------------------------------
           Exception
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarTrueuqueHortaliza HA procedure ');
         end;

       procedure borrarTruequeHortaliza (id_hortaliza number, id_trueque number) is
         begin
           delete from hortaliza_x_trueque
           where id_hortaliza = id_hortaliza and id_trueque  = id_trueque;
           commit;
           Exception
              WHEN no_data_found THEN
                   dbms_output.put_line('No such id_hortaliza or id_trueque ');
              WHEN others THEN
                   dbms_output.put_line('Error executing borrarTrueuqueHortaliza procedure ');
         end;
end ventatruequePaq;

create synonym pack_TruequeVenta for huerta.ventatruequePaq;

---Triggers 

create or replace trigger beforeInsertVenta
before insert on venta
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertVenta;
  


create or replace trigger beforeInsertTrueque
before insert on trueque
for each row
  begin
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertTrueque;
  
-----------------------------------------------------

create or replace trigger beforeInsertArbolVenta
before insert on arbol_x_venta
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
end beforeInsertArbolVenta;

create or replace trigger beforeInsertArbolTrueque
before insert on arbol_x_trueque
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertArbolTrueque;


create or replace trigger beforeInsertHortalizaVenta
before insert on hortaliza_x_venta
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertHortalizaVenta;
  
create or replace trigger beforeInsertHortalizaTrueque
before insert on hortaliza_x_trueque
for each row 
  begin 
    :new.creado_por:= USER;
    :new.fech_creacion:=SYSDATE;
  end beforeInsertHortalizaTrueque;