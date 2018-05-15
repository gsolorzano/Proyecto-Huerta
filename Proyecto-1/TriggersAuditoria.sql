-----Triggers para Auditoria General 

--Paquete Abono 

create or replace trigger beforeInsertAbonoAu
       before insert on abono
       begin
       insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
       values (s_auditoria.nextval,'Abono',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertAbonoAu;
  
create or replace trigger beforeUpdateAbonoAu
before update on abono 
begin
       insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
       values (s_auditoria.nextval,'Abono',to_date(sysdate(),'DD-MM-YYYY'),user, 'Update');
end beforeUpdateAbonoAuditoria;

create or replace trigger beforeInsertBitacoraAbonoAu
before insert on bitacora_abono
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'bitacora_abono',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertBitacoraAbonoAu;

create or replace trigger beforeInsertAbono_x_ArbolAu
before insert on abono_x_arbol
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'abono_x_arbol',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertAbono_x_ArbolAu;

create or replace trigger beforeInsertHortalizaxAbonoAu
before insert on hortaliza_x_abono
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'hortaliza_x_abono',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertHortalizaxAbonoAu;


---Huerta
create or replace trigger beforeInsertHuertaAu
before insert on huerta 
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'huerta',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
  end beforeInsertHuertaAu;

create or replace trigger beforeInsertCalificacionAu
before insert on calificacion
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'calificacion',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertCalificacionAu;

create or replace trigger beforeUpdateHuertaAu
before update on huerta
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'huerta',to_date(sysdate(),'DD-MM-YYYY'),user, 'Update');
end beforeUpdateHuertaAu;

---Locacion 

create or replace trigger beforeInsertDistritoAu
before insert on distrito
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'distrito',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertDistritoAu;

create or replace trigger beforeInsertCantonAu
before insert on canton
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'canton',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertCantonAu;

create or replace trigger beforeInsertProvinciaAu
before insert on provincia 
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'provincia',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertProvinciaAu;
  
create or replace trigger beforeInsertPaisAu
before insert on pais
begin
  insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
  values (s_auditoria.nextval,'pais',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertPaisAu;


---Persona 

create or replace trigger beforeInsertPersonaAu
before insert on persona
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'persona',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertPersonaAu ;

create or replace trigger beforeInsertDedicacionAu
before insert on dedicacion
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'dedicacion',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertDedicacionAu;

create or replace trigger beforeUpdatePersonaAu
before update on persona 
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'persona',to_date(sysdate(),'DD-MM-YYYY'),user, 'Update');
end beforeUpdatePersonaAu;
  


----Usuario 

create or replace trigger beforeInsertUsuarioAu
before insert on usuario 
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'usuario',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertUsuarioAu;

create or replace trigger beforeInsertBitacoraAu
before insert on Administrador.bitacora
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'Bitacora',to_date(sysdate(),'DD-MM-YYYY'),user, 'Insert');
end beforeInsertBitacoraAu;

create or replace trigger beforeUpdateUsuarioAu
before update on usuario
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'Usuario',to_date(sysdate(),'DD-MM-YYYY'),user, 'Update');
end beforeUpdateUsuarioAu;
  
create or replace trigger beforeUpdateBitacoraAu
before update on Administrador.bitacora
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'Bitacora',to_date(sysdate(),'DD-MM-YYYY'),user,'Update');
end beforeUpdateBitacoraAu;





------Plants--------

----Hortaliza 
create or replace trigger beforeInsertHortalizaAu
before insert on hortaliza
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'hortaliza',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertHortalizaAu;
  
create or replace trigger beforeUpdateHortalizaAu
before update on hortaliza
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'hortaliza',to_date(sysdate(),'DD-MM-YYYY'),user,'Update');
end beforeUpdateHortalizaAu;
  

create or replace trigger beforeInsertColorAu
before insert on color
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'color',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertColorAu;

create or replace trigger beforeInsertTipoAu
before insert on tipo
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'tipo',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertTipoAu;


create or replace trigger beforeInsertPropiedadAu
before insert on propiedad 
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'propiedad',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertPropiedadAu;

create or replace trigger beforeInsertCaracteristicaAu
before insert on caracteristica
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'caracteristica',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertCaracteristicaAu;


---Arbol


create or replace trigger beforeInsertArbolAu
before insert on arbol
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'arbol',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertArbolAu;
  
create or replace trigger beforeUpdateArbolAu
before update on arbol
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'arbol',to_date(sysdate(),'DD-MM-YYYY'),user,'Update');
end beforeUpdateArbolAu;
  

create or replace trigger beforeInsertReproduccionAu
before insert on reproduccion
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'reproduccion',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertReproduccionAu;
  
create or replace trigger beforeInsertXilemaAu
before insert on xilema
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'xilema',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertXilemaAu;
  
  
create or replace trigger beforeInsertCambiumAu
before insert on cambium
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'cambium',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertCambiumAu;
  
create or replace trigger beforeInsertCortezaAu
before insert on corteza
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'corteza',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertCortezaAu;


---Trueque Venta 

create or replace trigger beforeInsertVentaAu
before insert on venta
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'venta',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertVentaAu;
  

create or replace trigger beforeInsertTruequeAu
before insert on trueque
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'trueque',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertTruequeAu;
  

create or replace trigger beforeInsertArbolVentaAu
before insert on arbol_x_venta
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'arbol_x_venta',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertArbolVentaAu;

create or replace trigger beforeInsertArbolTruequeAu
before insert on arbol_x_trueque
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'arbol_x_trueque',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertArbolTruequeAu;


create or replace trigger beforeInsertHortalizaVentaAu
before insert on hortaliza_x_venta
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'hortaliza_x_venta',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertHortalizaVentaAu;

create or replace trigger beforeInsertHortalizaTruequeAu
before insert on hortaliza_x_trueque
begin
insert into Administrador.AuditoriaGeneral(id_auditoria,Nombre_Tabla,Fechacreacion,Creadopor,Accion) ----Administrador.AuditoriaGeneral
values (s_auditoria.nextval,'hortaliza_x_trueque',to_date(sysdate(),'DD-MM-YYYY'),user,'Insert');
end beforeInsertHortalizaTruequeAu;