-----a-------
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

----------b-------------

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


----------------------c------------------------

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