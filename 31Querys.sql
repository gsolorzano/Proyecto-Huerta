---------Query------------------------------

--1
--Listado de contacto del encargado del encargado principal y sus colaboradores
    
  select personas.nombre , personas.apellido, personas.cedula, us.username Usuario,personas.telefono, personas.huerta
  from (select name nombre, apellido,cedula,telefono, id_usuario, id_huerta huerta
        from persona
        where id_huerta = 1 -----cambiar 
        order by id_dedicacion
  ) personas
  inner join usuario us
  on personas.id_usuario =us.id_usuario;
  
  
  select id_huerta , count(*) total
  from persona
  where id_huerta = 1
  group by id_huerta
  order by id_huerta;

  
--2
--Listado de contacto de personas interesadas en lan huerta  

  select gent.name , gent.apellido, gent.cedula, us.username usuario, gent.huerta
  from (
       select name, apellido, cedula, id_usuario , id_huerta Huerta
       from persona
       where interesado = 1
  ) gent
  inner join usuario us
  on us.id_usuario = gent.id_usuario;
  
  select id_huerta , count(*) total
  from persona
  where interesado = 1
  group by id_huerta
  order by id_huerta;
  
--3
--Listado de plantas no abonadas 

select * from (
    select  hortaliza.nombre Hortaliza ,hort.fecha fecha 
    from (
          select id_hortaliza id_hortaliza , fecha  
          from hortaliza_x_abono ha
          where (select( to_date((select current_timestamp from dual), 'YYYY-MM-DD')-to_date(fecha,'YYYY-MM-DD')) 
                      from dual) >30 -----dias cambiar 
          order by ha.fecha asc
    )  hort
    inner join hortaliza 
    on hort.id_hortaliza = hortaliza.id_hortaliza
    ),(
    select   arbol.nombre Arbol, ab.fecha
    from (
          select id_arbol id_arbol, fecha 
          from abono_x_arbol aa
          where (select( to_date((select current_timestamp from dual), 'YYYY-MM-DD')-to_date(fecha,'YYYY-MM-DD')) 
                from dual) >30 -----dias cambiar 
          order by aa.fecha asc
    ) ab
inner join arbol
on arbol.id_arbol = ab.id_arbol
)

--4
--Listado de los usuarios cuyas claves no has sido modificados 


select persona.name nombre,persona.apellido, persona.cedula, usua.fecha
from (
      select id_usuario usuario , bit.fecha fecha
      from usuario
      inner join bitacora bit
      on usuario.id_bitacora =  bit.id_bitacora
      order by fecha asc     
) usua
inner join persona 
on persona.id_usuario  =usua.usuario
where (select( to_date((select current_timestamp from dual), 'YYYY-MM-DD')-to_date(fecha,'YYYY-MM-DD')) 
      from dual) >30 ----cambiar




--5
--Listado de hortalizas por clasificacion de color

    
select  color.nombre , col.total
from (
      select id_color , sum(id_color) total
      from hortaliza
      group by id_color
      order by id_color
) col
inner join color
on color.id_color = col.id_color  


select hot.nombre Hortaliza , id_color
      from hortaliza hot
      order by id_color asc 
      
select horto.hortaliza , color.nombre
from (
    select hot.nombre Hortaliza , id_color
    from hortaliza hot
    order by id_color asc  
) horto
inner join color
on horto.id_color = color.id_color






--6
--Clasificacion de hortalizas por parte comestible

select hor.nombre Hortaliza, tp.nombre Tipo
from (
      select htl.nombre nombre,htl.id_tipo Tipo
      from hortaliza htl
      order by id_tipo
) hor
inner join tipo tp
on tp.nombre = hor.tipo

  
select  tipo.nombre , col.total
from (
      select id_tipo , sum(id_tipo) total
      from hortaliza
      group by id_tipo
      order by id_tipo
) col
inner join tipo
on tipo.id_tipo = col.id_tipo  


--7
--Historico con las fechas de abono de las plantas 

--Hortaliza       
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
on hortaliza.id_hortaliza = his2.hortaliza

--Arbol
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
on arbol.id_arbol = his2.arbol


--8
--Listado de ventas con plantas 
--a
--Arbol 

select compra.venta, compra.fecha, compra.nombre Persona, arbol.nombre arbol, arbol.precio
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




--b
--Hortaliza

select compra.venta, compra.fecha, compra.nombre Persona, hor.nombre hortaliza, hor.precio
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


--9
--Listado de trueques co plantas
--Requesitos planta x planta 
          -- fecha 

      
   
--a Hortaliza - Arbol
select nn.nombre, nn.trueque, nn.fecha, ht.nombre hortaliza, arb.nombre arbol
from(
  select trade.trueque , trade.fecha , trade.nombre, hxt.id_hortaliza hortaliza, axt.id_arbol arbol
  from (
      select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre
       from (
            select id_trueque trueque, fecha , id_comprador trader 
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

--b 
--Arbol -Arbol
select nn.trueque, nn.fecha, nn.nombre, arb.nombre arbol1, arbx.nombre arbol2
from(
  select trade.trueque , trade.fecha , trade.nombre, arxt.id_arbol arbol1, axt.id_arbol arbol2
  from (
      select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre
       from (
            select id_trueque trueque, fecha , id_comprador trader 
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


--c Hortaliza - Hortaliza 

select nn.trueque, nn.fecha, nn.nombre, ht.nombre hortaliza1, htx.nombre hortaliza2
from(
  select trade.trueque , trade.fecha , trade.nombre, hxt.id_hortaliza hortaliza1, hrxt.id_hortaliza hortaliza2
  from (
      select vnt.trueque trueque, vnt.fecha fecha, persona.name nombre
       from (
            select id_trueque trueque, fecha , id_comprador trader 
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

-----------------------------------------------------------------------

--Extras comentarios y huertas 

select  huer.nombre huerta , cal.puntuacion , cal.comentario , cal.fecha
from (    select id_huerta, puntuacion, comentario, fecha 
          from calificacion
          where id_huerta = 1  ---------------cambiar 
) cal
inner join huerta huer
on huer.id_huerta = cal.id_huerta


--Colaboradores 

select admi.name Administrador , huer.nombre Huerta 
from (
      select name, id_huerta
      from persona 
      where id_dedicacion = 1      
) admi
inner join huerta huer
on huer.id_huerta = admi.id_huerta


 
