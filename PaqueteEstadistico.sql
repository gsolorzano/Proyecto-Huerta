--Modulo Estadistico 


---Funciones 
create or replace function getHuertaArbol(id_arbol number) return number is
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
         end getHuertaArbol;

create or replace function getHuertaHortaliza(id_hortaliza number) return number is
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
        end getHuertaHortaliza;





create or replace package paqEstadistico as
 function persona018MG RETURN number;
 function persona1930MG RETURN number;
 function persona3045MG RETURN number;
 function persona4655MG RETURN number;
 function persona5565MG RETURN number;
 function persona6675MG RETURN number;
 function persona7585MG RETURN number;
 function persona85plusMG RETURN number; 
 function hortalizaColorMG RETURN SYS_REFCURSOR;
 function hortalizaTipoMG RETURN SYS_REFCURSOR;
 function arbolReproduccionMG RETURN SYS_REFCURSOR;
 function arbolXilemaMG RETURN SYS_REFCURSOR;
 function arbolCambiumMG RETURN SYS_REFCURSOR;
 function arbolCortezaMG RETURN SYS_REFCURSOR;
 function totalTruequesMG RETURN SYS_REFCURSOR;
 function totalVentasMG RETURN SYS_REFCURSOR;
 function topTruequesMG RETURN SYS_REFCURSOR;
 function topVentasHortalizaMG RETURN SYS_REFCURSOR;
 function topVentasArbolMG RETURN SYS_REFCURSOR;
end paqEstadistico;

create or replace package body paqEstadistico as

--Total de usuario de la huerta
function persona018MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 0 and 18
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona018MG;



function persona1930MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 19 and 30
     );
  BEGIN
           
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona1930MG;

function persona3045MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 30 and 45
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona3045MG;

function persona4655MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 46 and 55
     );
  BEGIN
           
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona4655MG;

function persona5565MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 55 and 65
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona5565MG;

function persona6675MG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 66 and 75
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona6675MG;

function persona7585MG RETURN number
IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 75 and 85
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona7585MG;

function persona85plusMG RETURN number
  IS
     cantidad number;
     cursor c_prueba is
     select sum(cantidad) total from(
     select e.age ,e.cantidad from (
     select age.edad age , count(edad) cantidad from (
         select extract (year from sysdate) - extract(year from  fechaNac) edad
         from persona
     ) age
     group by edad ) e
     where e.age between 85 and 200
     );
  BEGIN
           for i in c_prueba loop
             cantidad := i.total;
            end loop;
           RETURN cantidad;
end persona85plusMG;




--Total de plantas agrupadas por:

--MG math graphics
--Hortaliza por color
function hortalizaColorMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select col.nombre Color, colores.cantidad
           from (
            select id_color color, count(id_color) cantidad
            from hortaliza
            group by id_color
            order by id_color
           ) colores
           inner join color col
           on col.id_color =  colores.color;
           RETURN l_return;
end hortalizaColorMG;

--Hortaliza Comestible
function hortalizaTipoMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select tiposHot.Nombre,  tipos.cantidad
           from (
           select id_tipo tipo , count(id_tipo) cantidad
           from hortaliza
           group by id_tipo
           order by id_tipo
           ) tipos
           inner join tipo tiposHot
           on tiposHot.Id_Tipo =  tipos.tipo;
           RETURN l_return;
end hortalizaTipoMG;


--Arbol reproduccion

function arbolReproduccionMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select sex.nombre Reproduccion , rep.cantidad  cantidad
           from (
             select id_reproduccion reproduccion ,  count(id_reproduccion) cantidad
             from arbol
             group by id_reproduccion
             order by id_reproduccion
           ) rep
           inner join reproduccion sex
           on sex.id_reproduccion =  rep.reproduccion;
           RETURN l_return;
end arbolReproduccionMG;

--Arbol estructura

function arbolXilemaMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select xilem.nombre xilema , xil2.cantidad
           from (
             select id_xilema xilema , count(id_xilema) cantidad
             from arbol
             group by id_xilema
             order by id_xilema
           ) xil2
           inner join xilema xilem
           on xilem.id_xilema = xil2.xilema;
           RETURN l_return;
end arbolXilemaMG;



function arbolCambiumMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select camb.nombre cambium, cam.cantidad
           from (
             select id_cambium cambium, count(id_cambium) cantidad
             from  arbol
             group by id_cambium
             order by id_cambium
           ) cam
           inner join cambium camb
           on camb.id_cambium = cam.cambium;
           RETURN l_return;
end arbolCambiumMG;



function arbolCortezaMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select corte.nombre corteza, cort.cantidad
           from (
             select id_corteza corteza, count(id_corteza) cantidad
             from  arbol
             group by id_corteza
             order by id_corteza
           ) cort
           inner join corteza corte
           on corte.id_corteza = cort.corteza;
           RETURN l_return;
end arbolCortezaMG;

--total de trueque

function totalTruequesMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
             select ano , count(ano) cantidad
             from (select extract(year from fecha) ano
                   from trueque)
             group by ano;
           RETURN l_return;
end totalTruequesMG ;

--total de ventas

function totalVentasMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
             select ano , count(ano) cantidad
             from (select extract(year from fecha) ano
                   from venta)
             group by ano ;
           RETURN l_return;
end totalVentasMG ;

---top Trueques

function topTruequesMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select * from (
           select huer.nombre Huerta, sells.cantidad
           from(
             select id_huerta huerta,  count (id_huerta) cantidad
             from trueque
             group by id_huerta
             order by id_huerta asc
           ) sells
           inner join huerta huer
           on huer.id_huerta = sells.huerta
           )
           where ROWNUM <=5;
           RETURN l_return;
end topTruequesMG;

--top ventas

--hortaliza
function topVentasHortalizaMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select * from (select huertica.nombre huerta,campo.cantidad from (
               select huer.huerta huerta, count(huer.huerta) cantidad
               from(
                    select getHuertaHortaliza(id_hortaliza) huerta
                    from hortaliza_x_venta
               ) huer
               group by huer.huerta
           ) campo
           inner join huerta huertica
           on huertica.id_huerta = campo.huerta)
           where ROWNUM <=3;
           RETURN l_return;
end topVentasHortalizaMG;


--arbol
function topVentasArbolMG RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select * from (select huertica.nombre huerta,campo.cantidad from (
               select huer.huerta huerta, count(huer.huerta) cantidad
               from(
                    select getHuertaArbol(id_arbol) huerta
                    from arbol_x_venta
               ) huer
               group by huer.huerta
           ) campo
           inner join huerta huertica
           on huertica.id_huerta = campo.huerta)
           where ROWNUM <=3;
           RETURN l_return;
end topVentasArbolMG;

end paqEstadistico;