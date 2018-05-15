
--PaqUtilidades 

create or replace package paqUtilidades as

function personaInfo RETURN SYS_REFCURSOR;

function dedicacionInfo RETURN SYS_REFCURSOR;

function hortalizaInfo RETURN SYS_REFCURSOR;

function colorInfo RETURN SYS_REFCURSOR;
  
function tipoInfo RETURN SYS_REFCURSOR;

function propiedadInfo RETURN SYS_REFCURSOR;

function caracteristicaInfo RETURN SYS_REFCURSOR;

function arbolInfo RETURN SYS_REFCURSOR;

function reproduccionInfo RETURN SYS_REFCURSOR;

function xilemaInfo RETURN SYS_REFCURSOR;

function paisInfo RETURN SYS_REFCURSOR;

function provinciaInfo RETURN SYS_REFCURSOR;

function cantonInfo RETURN SYS_REFCURSOR;

function distritoInfo RETURN SYS_REFCURSOR;

function huertaInfo RETURN SYS_REFCURSOR;

function calificacionInfo RETURN SYS_REFCURSOR;

function cambiumInfo RETURN SYS_REFCURSOR;

function cortezaInfo RETURN SYS_REFCURSOR;

function usuarioInfo RETURN SYS_REFCURSOR;

function abonoInfo RETURN SYS_REFCURSOR;

function abonoXhortalizaInfo RETURN SYS_REFCURSOR;

function abonoXarbolInfo RETURN SYS_REFCURSOR;

function ventaInfo RETURN SYS_REFCURSOR;

function truequeInfo RETURN SYS_REFCURSOR;

function calificacionHuerta ( p_huerta  IN NUMBER)RETURN SYS_REFCURSOR;

function getLatitud (p_huerta in number) return VARCHAR2;

function getLongitud (p_huerta in number) return VARCHAR2;

function getImagePersona (id_persona in number  ) return blob;

procedure huertaInfoGeneral (p_huerta in number, res out  SYS_REFCURSOR );

function getImageArbol (id_arbol in number  ) return blob;

function getImageHortaliza (id_hortaliza in number  ) return blob;

function auditoriaPersona RETURN SYS_REFCURSOR;

function auditoriaHuerta RETURN SYS_REFCURSOR;

procedure hortalizaInfoGeneral ( p_hortaliza  IN NUMBER, res out  SYS_REFCURSOR );

procedure arbolInfoGeneral ( p_arbol  IN NUMBER, res out SYS_REFCURSOR);

function auditoriaGeneralInfo RETURN SYS_REFCURSOR;

procedure getactivacionTipo (ptipo in number, res out number);

procedure getactivacionCaracteristica (pcaracteristica  in number, res out number);

procedure getactivacionPropiedad  (ppropiedad   in number, res out number);

procedure getactivacionColor  (pcolor  in number, res out number);

function interesadosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR;

procedure hortalizasHuerta( p_huerta  IN NUMBER, res out  SYS_REFCURSOR );

procedure arbolHuerta( p_huerta  IN NUMBER, res out SYS_REFCURSOR);

procedure personaUser  (p_user  in varchar2 , res out Sys_refcursor);

end paqUtilidades;

create or replace package body paqUtilidades as

--Pais
function paisInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_pais, nombre
           from pais;
           RETURN l_return;
end paisInfo;

--Provincia
function provinciaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_provincia, nombre
           from provincia;
           RETURN l_return;
end provinciaInfo;

--Canton
function cantonInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_canton, nombre
           from canton;
           RETURN l_return;
end cantonInfo;

--Distrito

function distritoInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_distrito, nombre
           from distrito;
           RETURN l_return;
end distritoInfo;


--Huerta

function huertaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_huerta, nombre
           from huerta
           where id_huerta < > 0;
           RETURN l_return;
end huertaInfo;


procedure huertaInfoGeneral (p_huerta in number, res out  SYS_REFCURSOR )
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select nombre,latitud, longitud,id_distrito, capital
           from huerta
           where id_huerta = p_huerta;
           res:= l_return;
end huertaInfoGeneral;


--Calificacion
function calificacionInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_calificacion,puntuacion, comentario
           from calificacion;
           RETURN l_return;
end calificacionInfo;


--Persona


function personaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_persona, name
           from persona;
           RETURN l_return;
end personaInfo;

--Dedicacion


function dedicacionInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_dedicacion, nombre
           from dedicacion
           order by id_dedicacion;
           RETURN l_return;
end dedicacionInfo;

--Hortaliza
function hortalizaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_hortaliza, nombre
           from hortaliza;
           RETURN l_return;
end hortalizaInfo;


--Color
function colorInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_color, nombre
           from color;
           RETURN l_return;
end colorInfo;

--Tipo
function tipoInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_tipo, nombre
           from tipo;
           RETURN l_return;
end tipoInfo;



--Propiedad

function propiedadInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_propiedad, nombre
           from propiedad;
           RETURN l_return;
end propiedadInfo;


--Caracteristica

function caracteristicaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_caracteristica, nombre
           from caracteristica;
           RETURN l_return;
end caracteristicaInfo;


--Arbol
function arbolInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_arbol, nombre
           from arbol;
           RETURN l_return;
end arbolInfo;

--Reproduccion
function reproduccionInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_reproduccion, nombre
           from reproduccion;
           RETURN l_return;
end reproduccionInfo;


--xilema

function xilemaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_xilema, nombre
           from xilema;
           RETURN l_return;
end xilemaInfo;


--cambium

function cambiumInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_cambium, nombre
           from cambium;
           RETURN l_return;
end cambiumInfo;

--corteza
function cortezaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_corteza, nombre
           from corteza;
           RETURN l_return;
end cortezaInfo;


--Usuario

function usuarioInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_usuario,username
           from usuario;
           RETURN l_return;
end usuarioInfo;

--Abono

function abonoInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_abono,nombre
           from abono;
           RETURN l_return;
end abonoInfo;

--abonoXhortaliza
function abonoXhortalizaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_abono,id_hortaliza,fecha
           from hortaliza_x_abono;
           RETURN l_return;
end abonoXhortalizaInfo;



--abonoXarbol
function abonoXarbolInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_abono,id_arbol,fecha
           from abono_x_arbol;
           RETURN l_return;
end abonoXarbolInfo;

--venta

function ventaInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_venta,id_comprador,fecha
           from venta;
           RETURN l_return;
end ventaInfo;


--trueque
function truequeInfo RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select id_trueque,id_comprador,fecha
           from trueque;
           RETURN l_return;
end truequeInfo;


---

function calificacionHuerta ( p_huerta  IN NUMBER)RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select  huer.nombre huerta , cal.puntuacion , cal.comentario , cal.fecha
            from (    select id_huerta, puntuacion, comentario, fecha
                      from calificacion
                      where id_huerta = p_huerta
            ) cal
            inner join huerta huer
            on huer.id_huerta = cal.id_huerta;
           RETURN l_return;
end calificacionHuerta;


function getLatitud (p_huerta in number) return varchar2
  is
         latitudNo varchar2(10);
  begin
         select latitud
         into latitudNo
         from huerta
         where id_huerta = p_huerta;
         return (latitudNo);
end getLatitud;


function getLongitud (p_huerta in number) return varchar2
  is
         longitudNo varchar2(10);
  begin
         select longitud
         into longitudNo
         from huerta
         where id_huerta = p_huerta;
         return (longitudNo);
end getLongitud;

function getImagePersona (id_persona in number  ) return blob
  is
         imagen blob;
  begin
         select foto
         into imagen
         from persona
         where id_persona = id_persona;
         return (imagen);
  end getImagePersona;

function getImageArbol (id_arbol in number  ) return blob
  is
         imagen blob;
  begin
         select foto
         into imagen
         from arbol
         where id_arbol = id_arbol;
         return (imagen);
  end getImageArbol;

function getImageHortaliza (id_hortaliza in number  ) return blob
  is
         imagen blob;
  begin
         select foto
         into imagen
         from hortaliza
         where id_hortaliza = id_hortaliza;
         return (imagen);
  end getImageHortaliza;

--Campos Auditoria
function auditoriaPersona RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select name, creado_por , fech_creacion, editado_por,fech_edicion
           from persona ;
           RETURN l_return;
end auditoriaPersona;

function auditoriaHuerta RETURN SYS_REFCURSOR
  IS
      l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select nombre, creado_por , fech_creacion, editado_por,fech_edicion
           from huerta ;
           RETURN l_return;
end auditoriaHuerta;



procedure hortalizaInfoGeneral ( p_hortaliza  IN NUMBER, res out SYS_REFCURSOR)
  IS
      l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select horta.nombre, co.nombre, tip.nombre, pro.nombre,cara.nombre, horta.cantidad,horta.precio,  horta.foto
           from (select nombre,precio,id_color, id_tipo,id_propiedad, id_caracteristica, cantidad, foto
                  from hortaliza
                  where id_hortaliza = p_hortaliza) horta
           inner join color co
           on co.id_color =  horta.id_color
           inner join tipo tip
           on tip.id_tipo = horta.id_tipo
           inner join propiedad pro
           on pro.id_propiedad = horta.id_propiedad
           inner join caracteristica cara
           on cara.id_caracteristica = horta.id_caracteristica;           
           res :=  l_return;
end;

procedure arbolInfoGeneral ( p_arbol  IN NUMBER, res out SYS_REFCURSOR)
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select arb.nombre, arb.precio,arb.extincion,re.nombre,xil.nombre,cam.nombre,cor.nombre,
            arb.cantidad, arb.foto
           from (select nombre, precio, extincion, id_huerta huerta, id_reproduccion reproduccion, 
           id_xilema xilema,id_cambium cambium, id_corteza  corteza, foto, cantidad 
           from arbol where id_arbol  = p_arbol 
           ) arb
           inner join reproduccion re
           on re.id_reproduccion = arb.reproduccion
           inner join xilema xil 
           on xil.id_xilema =  arb.xilema
           inner join cambium cam
           on cam.id_cambium = arb.cambium
           inner join corteza cor
           on cor.id_corteza = arb.corteza;
           res:= l_return;
end arbolInfoGeneral;

function auditoriaGeneralInfo RETURN SYS_REFCURSOR
  IS
      l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select nombre_tabla,fechacreacion ,creadopor, accion
           from Administrador.auditoriaGeneral ;
           RETURN l_return;
end auditoriaGeneralInfo;

procedure getactivacionTipo (ptipo in number, res out number) as
  respuesta number;
  begin
    select activo
    into respuesta
    from tipo
    where id_tipo  =  ptipo;
    res :=  respuesta;
end getactivacionTipo;

procedure getactivacionCaracteristica (pcaracteristica  in number, res out number) as
  respuesta number;
  begin
    select activo
    into respuesta
    from caracteristica
    where id_caracteristica  =  pcaracteristica ;
    res :=  respuesta;
end getactivacionCaracteristica;

procedure getactivacionPropiedad  (ppropiedad   in number, res out number) as
  respuesta number;
  begin
    select activo
    into respuesta
    from propiedad
    where id_propiedad   =  ppropiedad ;
    res :=  respuesta;
end getactivacionPropiedad;

procedure getactivacionColor  (pcolor  in number, res out number) as
  respuesta number;
  begin
    select activo
    into respuesta
    from color
    where id_color   =  pcolor ;
    res :=  respuesta;
end getactivacionColor;

function interesadosHuerta( p_huerta  IN NUMBER ) RETURN SYS_REFCURSOR
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
           select p.name , p.last_name
           from( select id_persona persona
            from interesadoxhuerta
            where id_huerta =  p_huerta) indi
            inner join persona p 
            on p.id_persona = indi.persona;
           RETURN l_return;
  END interesadosHuerta;  
  
procedure hortalizasHuerta( p_huerta  IN NUMBER, res out SYS_REFCURSOR)
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select id_hortaliza ,   nombre 
            from  hortaliza 
            where id_huerta = p_huerta; 
            res :=  l_return;
 END;

procedure arbolHuerta( p_huerta  IN NUMBER, res out SYS_REFCURSOR)
  IS
     l_return   SYS_REFCURSOR;
  BEGIN
           OPEN l_return FOR
            select id_arbol ,   nombre 
            from  arbol 
            where id_huerta = p_huerta; 
            res :=  l_return;
end arbolHuerta;

procedure personaUser  (p_user  in varchar2 , res out Sys_refcursor)
  IS
     l_return   SYS_REFCURSOR;
     cel_usuario number;
     cursor c_prueba (p_user in varchar2) is
     select id_usuario usu from usuario where  username = p_user;
  BEGIN
    for i in c_prueba(p_user) loop 
      cel_usuario := i.usu;
    end loop;
    OPEN l_return FOR
           select id_persona, id_dedicacion
           from persona 
           where id_usuario = cel_usuario;
           res:= l_return;          
end personaUser;


end paqUtilidades;