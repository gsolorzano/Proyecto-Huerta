--Paquete Consultas



create or replace function diferenciaDias( p_fecha IN date ) RETURN number
    is
         dif date;
    begin
      dif:= to_date(sysdate(),'YYYY-MM-DD');
      return (dif - to_date(p_fecha,'YYYY-MM-DD'))/365;    
    end  temp;
    
select * from color

create or replace package paqConsultas as
  function contactosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR;
  function totalContactosHuerta( p_huerta  IN NUMBER ) RETURN number;
  function interesadosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR;
  function totalInteresadosHuerta( p_huerta  IN NUMBER ) RETURN number;
  function plantasNoAbonadas( p_inicio  IN DATE) RETURN SYS_REFCURSOR;
  function registroContrasena( p_inicio  IN DATE) RETURN SYS_REFCURSOR;
  function hortalizaColor RETURN SYS_REFCURSOR;
  function totalHortalizaColor RETURN SYS_REFCURSOR;
  function hortalizaTipo RETURN SYS_REFCURSOR;
  function totalHortalizaTipo RETURN SYS_REFCURSOR;
  function abonoHortaliza RETURN SYS_REFCURSOR;
  function abonoHortalizaPersona RETURN SYS_REFCURSOR;
  function abonoArbol RETURN SYS_REFCURSOR;
  function abonoArbolPersona RETURN SYS_REFCURSOR;
  function ventaArbol RETURN SYS_REFCURSOR;
  function ventaHortaliza RETURN SYS_REFCURSOR;
  function registroTruequeHA RETURN SYS_REFCURSOR;
  function registroTruequeAA RETURN SYS_REFCURSOR;
  function registroTruequeHH RETURN SYS_REFCURSOR;
  function diasMayoresTruequesAsc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR;
  function diasMayoresTruequesDesc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR;
  function diasMayoresVentasAsc(top in number ,fecha_inicio  IN number, fecha_final in number ) RETURN SYS_REFCURSOR;
  function diasMayoresVentasDesc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR;
end paqConsultas;

CREATE OR REPLACE PACKAGE BODY paqConsultas as

--1 Listado de contacto del encargado del encargado principal y sus colaboradores
  function contactosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select personas.nombre , personas.last_name, personas.cedula, us.username Usuario,personas.telefono
            from (select name nombre, last_name,cedula,telefono, id_usuario, id_huerta huerta
                  from persona
                  where id_huerta = p_huerta
                  order by id_dedicacion
            ) personas
            inner join usuario us
            on personas.id_usuario =us.id_usuario;
           RETURN l_return;
  END contactosHuerta;
--Total
  function totalContactosHuerta( p_huerta  IN NUMBER ) RETURN number
    is
           totalContactos number;
           cursor c_prueba(p_huerta in number) is select count(*) total from persona
           where id_huerta = p_huerta
           group by id_huerta
           order by id_huerta;
    begin
          for i in c_prueba(p_huerta) loop
            totalContactos := i.total;
          end loop;
          return totalContactos;
  end totalContactosHuerta;

--2 Listado de contacto de personas interesadas en la huerta
  function interesadosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select personas.nombre , personas.last_name, personas.cedula, us.username Usuario,personas.telefono
            from (select name nombre, last_name,cedula,telefono, id_usuario
                  from persona
                  where id_huerta = p_huerta and interesado = 1
                  order by id_dedicacion asc
            ) personas
            inner join usuario us
            on personas.id_usuario =us.id_usuario;
           RETURN l_return;
  END interesadosHuerta;
--Total
  function totalInteresadosHuerta( p_huerta  IN NUMBER ) RETURN number
    is
           totalInteresados number;
           cursor c_prueba(p_huerta in number) is
            select count(*) total
            from persona
            where interesado = 1 and id_huerta =  p_huerta
            group by id_huerta
            order by id_huerta;
    begin
          for i in c_prueba(p_huerta) loop
            totalInteresados := i.total;
          end loop;
          return totalInteresados;
  end totalInteresadosHuerta;

--3 --Listado de plantas no abonadas
function plantasNoAbonadas( p_inicio  IN DATE ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select * from (
            select  hortaliza.nombre Hortaliza ,hort.fecha fecha
            from (
                  select id_hortaliza id_hortaliza , fecha
                  from hortaliza_x_abono ha
                  where to_date(fecha,'YYYY-MM-DD') between to_date( p_inicio ,'YYYY-MM-DD') and to_date(sysdate,'YYYY-MM-DD')
                  order by ha.fecha asc
            )  hort
            inner join hortaliza
            on hort.id_hortaliza = hortaliza.id_hortaliza
            ),(
            select   arbol.nombre Arbol, ab.fecha
            from (
                  select id_arbol id_arbol, fecha
                  from abono_x_arbol aa
                  where to_date(fecha,'YYYY-MM-DD') between to_date(p_inicio,'YYYY-MM-DD') and to_date(sysdate(),'YYYY-MM-DD')
                  order by aa.fecha asc
            ) ab
          inner join arbol
          on arbol.id_arbol = ab.id_arbol
          );
          RETURN l_return;
     end plantasNoAbonadas;


--4 Listado de los usuarios cuyas claves no has sido modificados
function registroContrasena( p_inicio  IN date) RETURN SYS_REFCURSOR  ---dias
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select persona.name nombre,persona.last_name, persona.cedula, usua.fecha
            from (
                  select id_usuario usuario , bit.fecha fecha
                  from usuario
                  inner join Administrador.bitacora bit
                  on usuario.id_bitacora =  bit.id_bitacora
                  order by fecha asc
            ) usua
            inner join persona
            on persona.id_usuario  =usua.usuario
            where to_date(fecha,'YYYY-MM-DD') between to_date(p_inicio,'YYYY-MM-DD') and to_date(sysdate(),'YYYY-MM-DD');
           RETURN l_return;
end registroContrasena;




--5 Listado de hortalizas por clasificacion de color
----Hortaliza

function hortalizaColor RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select horto.hortaliza , color.nombre Color
            from (
                select hot.nombre Hortaliza , id_color
                from hortaliza hot
                order by id_color asc
            ) horto
            inner join color
            on horto.id_color = color.id_color;
           RETURN l_return;
end hortalizaColor;


--Total de hortalizas por color
function totalHortalizaColor RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select  color.nombre , col.total
            from (
                  select id_color , sum(id_color) total
                  from hortaliza
                  group by id_color
                  order by id_color
            ) col
            inner join color
            on color.id_color = col.id_color;
           RETURN l_return;
end totalHortalizaColor;

--6 --Clasificacion de hortalizas por parte comestible

function hortalizaTipo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select hor.nombre Hortaliza, tp.nombre Tipo
           from (
            select htl.nombre nombre,htl.id_tipo Tipo
            from hortaliza htl
            order by id_tipo
            ) hor
            inner join tipo tp
            on tp.nombre = hor.tipo;
           RETURN l_return;
end hortalizaTipo;

--Total

function totalHortalizaTipo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
          OPEN l_return FOR
          select  tipo.nombre , col.total
          from (
                select id_tipo , sum(id_tipo) total
                from hortaliza
                group by id_tipo
                order by id_tipo
          ) col
          inner join tipo
          on tipo.id_tipo = col.id_tipo ;
           RETURN l_return;
end totalHortalizaTipo;

--7 Historico con las fechas de abono de las plantas

--Hortaliza
function abonoHortaliza RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select his2.abono, hortaliza.nombre hortaliza,his2.fecha
            from (
                  select abono.nombre abono , his.id_hortaliza hortaliza,his.fecha fecha
                  from (
                       select id_abono  , id_hortaliza , fecha
                       from hortaliza_x_abono
                       order by fecha
                  ) his
                  inner join abono
                        on abono.id_abono = his.id_abono
            ) his2
            inner join hortaliza
            on hortaliza.id_hortaliza = his2.hortaliza;
           RETURN l_return;
end abonoHortaliza ;


--Arbol
function abonoArbol RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select his2.abono, arbol.nombre arbol,his2.fecha
            from (
                  select abono.nombre abono , his.id_arbol arbol ,his.fecha fecha
                  from (
                       select id_abono  , id_arbol , fecha
                       from abono_x_arbol
                       order by fecha
                  ) his
                  inner join abono
                        on abono.id_abono = his.id_abono
            ) his2
            inner join arbol
            on arbol.id_arbol = his2.arbol;
           RETURN l_return;
end abonoArbol ;


---8 Listado de ventas con plantas

--Arbol


function ventaArbol RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select huer.nombre Huerta, compra.fecha, compra.nombre Persona, arbol.nombre arbol, arbol.precio
            from (
                  select sell.venta venta,  sell.fecha fecha , sell.nombre nombre, arv.id_arbol id_arbol
                  from(select vnt.venta venta, vnt.fecha fecha, persona.name nombre
                        from (
                                  select id_venta venta, fecha , id_comprador comprador
                                  from venta
                                  order by fecha
                        ) vnt
                        inner join persona
                        on persona.cedula  = vnt.comprador
                  ) sell
                  inner join arbol_x_venta arv
                  on arv.id_venta = sell. venta
            ) compra
            inner join arbol
            on arbol.id_arbol = compra.id_arbol
            inner join huerta huer
            on huer.id_huerta = arbol.id_huerta;
           RETURN l_return;
end ventaArbol ;

--Hortaliza


function ventaHortaliza RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select huer.nombre Huerta, compra.fecha, compra.nombre Persona, hor.nombre hortaliza, hor.precio
           from (
           select sell.venta ,  sell.fecha, sell.nombre, hrv.id_hortaliza id_hortaliza
           from(select vnt.venta venta, vnt.fecha fecha, persona.name nombre
           from (
                      select id_venta venta, fecha , id_comprador comprador
                      from venta
                      order by fecha
            ) vnt
            inner join persona
            on persona.cedula  = vnt.comprador
            ) sell
            inner join hortaliza_x_venta hrv
            on hrv.id_venta = sell. venta
            ) compra
            inner join hortaliza hor
            on hor.id_hortaliza = compra.id_hortaliza
            inner join huerta huer
            on huer.id_huerta  = hor.id_huerta;

           RETURN l_return;
end ventaHortaliza ;


--9. Listado de trueques co plantas



--a Hortaliza - Arbol

function registroTruequeHA RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select huer.nombre huerta, nn.trueque, nn.fecha, ht.nombre hortaliza, arb.nombre arbol
            from(
              select trade.huerta huerta ,trade.trueque , trade.fecha , trade.nombre, hxt.id_hortaliza hortaliza, axt.id_arbol arbol
              from (
                  select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre, vnt.huerta huerta
                   from (
                        select id_trueque trueque, fecha , id_comprador trader, id_huerta huerta
                        from trueque
                        order by fecha asc
                    ) vnt
                    inner join persona
                    on persona.cedula  = vnt.trader
              )  trade
              inner join hortaliza_x_trueque hxt
              on hxt.id_trueque = trade.trueque
              inner join arbol_x_trueque axt
              on axt.id_trueque = trade.trueque
            ) nn
            inner join hortaliza ht
            on ht.id_hortaliza = nn.hortaliza
            inner join arbol arb
            on arb.id_arbol = nn.arbol
            inner join huerta huer
            on huer.id_huerta = nn.huerta;
           RETURN l_return;
end registroTruequeHA ;

--b Arbol -Arbol

function registroTruequeAA RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select huer.nombre huerta, nn.fecha, nn.nombre Persona, arb.nombre arbol1, arbx.nombre arbol2
            from(
              select trade.huerta huerta , trade.fecha , trade.nombre, arxt.id_arbol arbol1, axt.id_arbol arbol2
              from (
                  select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre, vnt.huerta huerta
                   from (
                        select id_trueque trueque, fecha , id_comprador trader, id_huerta huerta
                        from trueque
                        order by fecha asc
                    ) vnt
                    inner join persona
                    on persona.cedula  = vnt.trader
              )  trade
              inner join arbol_x_trueque arxt
              on arxt.id_trueque = trade.trueque
              inner join arbol_x_trueque axt
              on axt.id_trueque = trade.trueque
            ) nn
            inner join arbol arb
            on arb.id_arbol = nn.arbol1
            inner join arbol arbx
            on arbx.id_arbol = nn.arbol2
            inner join huerta huer
            on huer.id_huerta = nn.huerta;
           RETURN l_return;
end registroTruequeAA;

--c Hortaliza - Hortaliza


function registroTruequeHH RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select huer.nombre huerta, nn.fecha, nn.nombre persona, ht.nombre hortaliza1, htx.nombre hortaliza2
            from(
              select trade.huerta huerta , trade.fecha , trade.nombre, hxt.id_hortaliza hortaliza1, hrxt.id_hortaliza hortaliza2
              from (
                  select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre, vnt.huerta huerta
                   from (
                        select id_trueque trueque, fecha , id_comprador trader , id_huerta huerta
                        from trueque
                        order by fecha asc
                    ) vnt
                    inner join persona
                    on persona.cedula  = vnt.trader
              )  trade
              inner join hortaliza_x_trueque hxt
              on hxt.id_trueque = trade.trueque
              inner join hortaliza_x_trueque hrxt
              on hrxt.id_trueque = trade.trueque
            ) nn
            inner join hortaliza ht
            on ht.id_hortaliza = nn.hortaliza1
            inner join hortaliza htx
            on htx.id_huerta = nn.hortaliza2
            inner join huerta huer
            on huer.id_huerta =nn.huerta;
           RETURN l_return;
end registroTruequeHH ;


--10 Consulta de dias de mayores trueques

--Ascendente
function diasMayoresTruequesAsc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select * from
            (select fecha , count(fecha)
            from trueque
            where to_date(fecha,'DD-MM-YYYY') between
            to_date(fecha_inicio,'DD-MM-YYYY') and to_date(fecha_final,'DD-MM-YYYY')
            group by fecha
            order by fecha asc)
            where ROWNUM <=top;
           RETURN l_return;
 end diasMayoresTruequesAsc;

--Descendente
function diasMayoresTruequesDesc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select * from
            (select fecha , count(fecha)
            from trueque
            where to_date(fecha,'DD-MM-YYYY') between
            to_date(fecha_inicio,'DD-MM-YYYY') and to_date(fecha_final,'DD-MM-YYYY')
            group by fecha
            order by fecha desc)
            where ROWNUM <=top;
           RETURN l_return;
end diasMayoresTruequesDesc;


--11 Dias de mayores ventas

--Ascendente
function diasMayoresVentasAsc(top in number ,fecha_inicio  IN number, fecha_final in number ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select * from
            (select fecha , count(fecha)
            from venta
            where to_date(fecha,'DD-MM-YYYY') between
            to_date(fecha_inicio,'DD-MM-YYYY') and to_date(fecha_final,'DD-MM-YYYY')
            group by fecha
            order by fecha asc)
            where ROWNUM <=top;
           RETURN l_return;
end diasMayoresVentasAsc;

--Descendente
function diasMayoresVentasDesc(top in number ,fecha_inicio  IN number , fecha_final in number ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select * from
            (select fecha , count(fecha)
            from venta
            where to_date(fecha,'DD-MM-YYYY') between
            to_date(fecha_inicio,'DD-MM-YYYY') and to_date(fecha_final,'DD-MM-YYYY')
            group by fecha
            order by fecha desc)
            where ROWNUM <=top;
           RETURN l_return;
end diasMayoresVentasDesc;

---Extras para bitacora Abono

function abonoHortalizaPersona RETURN SYS_REFCURSOR
  is
         l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select his2.abono, hortaliza.nombre hortaliza,his2.fecha, pers.name nombre
         
            from (
                  select abono.nombre abono , his.id_hortaliza hortaliza,his.fecha fecha, bitAbono.Id_Persona persona
                  from (
                       select id_abono  , id_hortaliza , fecha, id_bitacora bit
                       from hortaliza_x_abono
                       order by fecha
                  ) his
                  inner join abono
                        on abono.id_abono = his.id_abono
                  inner join bitacora_abono bitAbono
                        on bitAbono.id_bitacora = his.bit
            ) his2
            inner join hortaliza
            on hortaliza.id_hortaliza = his2.hortaliza
            inner join persona pers
            on pers.id_persona = his2.persona;
           RETURN l_return;
end abonoHortalizaPersona;




function abonoArbolPersona RETURN SYS_REFCURSOR
  is
         l_return   SYS_REFCURSOR;
  begin
         OPEN l_return FOR
         select his2.abono, arbol.nombre arbol,his2.fecha, pers.name nombre 
            from (
                  select abono.nombre abono , his.id_arbol arbol ,his.fecha fecha, bitAbono.Id_Persona persona
                  from (
                       select id_abono  , id_arbol , fecha, id_bitacora bit
                       from abono_x_arbol
                       order by fecha
                  ) his
                  inner join abono
                        on abono.id_abono = his.id_abono
                  inner join bitacora_abono bitAbono
                        on bitAbono.id_bitacora = his.bit
            ) his2
            inner join arbol
            on arbol.id_arbol = his2.arbol
            inner join persona pers
            on pers.id_persona = his2.persona;
         RETURN l_return;
end abonoArbolPersona;



end paqConsultas;



