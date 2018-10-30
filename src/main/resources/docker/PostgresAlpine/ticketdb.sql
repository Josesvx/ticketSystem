-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.1
-- PostgreSQL version: 9.4
-- Project Site: pgmodeler.com.br
-- Model Author: ---

-- object: ticketuser | type: ROLE --
-- DROP ROLE IF EXISTS ticketuser;
--CREATE ROLE ticketuser WITH 
--	SUPERUSER
--	UNENCRYPTED PASSWORD '12345678';
-- ddl-end --
--COMMENT ON ROLE ticketuser IS 'usuario de la base de datos';
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: ticketdb | type: DATABASE --
-- -- DROP DATABASE IF EXISTS ticketdb;
-- CREATE DATABASE ticketdb
-- 	OWNER = ticketuser
-- ;
-- -- ddl-end --
-- 

-- object: public.categoria | type: TABLE --
-- DROP TABLE IF EXISTS public.categoria CASCADE;
CREATE TABLE public.categoria(
	id_categoria serial NOT NULL,
	nombre varchar(25) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_categoria PRIMARY KEY (id_categoria)

);
-- ddl-end --
ALTER TABLE public.categoria OWNER TO ticketuser;
-- ddl-end --

-- object: public.rol | type: TABLE --
-- DROP TABLE IF EXISTS public.rol CASCADE;
CREATE TABLE public.rol(
	id_rol serial NOT NULL,
	nombre varchar(50) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_rol PRIMARY KEY (id_rol)

);
-- ddl-end --
ALTER TABLE public.rol OWNER TO ticketuser;
-- ddl-end --

-- object: public.departamento | type: TABLE --
-- DROP TABLE IF EXISTS public.departamento CASCADE;
CREATE TABLE public.departamento(
	id_departamento serial NOT NULL,
	nombre varchar(50) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_departamento PRIMARY KEY (id_departamento)

);
-- ddl-end --
ALTER TABLE public.departamento OWNER TO ticketuser;
-- ddl-end --

-- object: public.estado | type: TABLE --
-- DROP TABLE IF EXISTS public.estado CASCADE;
CREATE TABLE public.estado(
	id_estado serial NOT NULL,
	nombre varchar(25) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_estado PRIMARY KEY (id_estado)

);
-- ddl-end --
ALTER TABLE public.estado OWNER TO ticketuser;
-- ddl-end --

-- object: public.prioridad | type: TABLE --
-- DROP TABLE IF EXISTS public.prioridad CASCADE;
CREATE TABLE public.prioridad(
	id_prioridad serial NOT NULL,
	nombre varchar(25) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_prioridad PRIMARY KEY (id_prioridad)

);
-- ddl-end --
ALTER TABLE public.prioridad OWNER TO ticketuser;
-- ddl-end --

-- object: public.directorio | type: TABLE --
-- DROP TABLE IF EXISTS public.directorio CASCADE;
CREATE TABLE public.directorio(
	id_directorio serial NOT NULL,
	id_rol integer NOT NULL,
	id_departamento integer NOT NULL,
	nombre1 varchar(25) NOT NULL,
	nombre2 varchar(25) NOT NULL,
	apellido1 varchar(25) NOT NULL,
	apellido2 varchar(25) NOT NULL,
	correo varchar(50) NOT NULL,
	contrasenia varchar(8) NOT NULL,
	usuario varchar(50) NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_directorio PRIMARY KEY (id_directorio)

);
-- ddl-end --
ALTER TABLE public.directorio OWNER TO ticketuser;
-- ddl-end --

-- object: public.encargado | type: TABLE --
-- DROP TABLE IF EXISTS public.encargado CASCADE;
CREATE TABLE public.encargado(
	id_encargado serial NOT NULL,
	id_directorio integer NOT NULL,
	id_mantenimiento_encargado integer NOT NULL,
	estado boolean NOT NULL,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_encargado PRIMARY KEY (id_encargado)

);
-- ddl-end --
ALTER TABLE public.encargado OWNER TO ticketuser;
-- ddl-end --

-- object: public.solicitud | type: TABLE --
-- DROP TABLE IF EXISTS public.solicitud CASCADE;
CREATE TABLE public.solicitud(
	id_solicitud serial NOT NULL,
	titulo varchar(50) NOT NULL,
	id_categoria integer NOT NULL,
	id_prioridad integer,
	id_directorio integer NOT NULL,
	descripcion varchar(250) NOT NULL,
	adjunto varchar(250),
	n_seguimiento varchar(25) NOT NULL,
	feedback varchar(500),
	correlativo varchar(10),
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_solicitud PRIMARY KEY (id_solicitud)

);
-- ddl-end --
ALTER TABLE public.solicitud OWNER TO ticketuser;
-- ddl-end --

-- object: public.mantenimiento_encargado | type: TABLE --
-- DROP TABLE IF EXISTS public.mantenimiento_encargado CASCADE;
CREATE TABLE public.mantenimiento_encargado(
	id_mantenimiento_encargado serial NOT NULL,
	id_solicitud integer NOT NULL,
	id_descripcion_mantenimiento integer,
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_mantenimietno_encargado PRIMARY KEY (id_mantenimiento_encargado)

);
-- ddl-end --
ALTER TABLE public.mantenimiento_encargado OWNER TO ticketuser;
-- ddl-end --

-- object: public.descripcion_mantenimiento | type: TABLE --
-- DROP TABLE IF EXISTS public.descripcion_mantenimiento CASCADE;
CREATE TABLE public.descripcion_mantenimiento(
	id_descripcion_mantenimiento serial NOT NULL,
	descripcion_problema varchar(500),
	descripcion_solucion varchar(500),
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_descripcion_mantenimiento PRIMARY KEY (id_descripcion_mantenimiento)

);
-- ddl-end --
ALTER TABLE public.descripcion_mantenimiento OWNER TO ticketuser;
-- ddl-end --

-- object: public.estado_solicitud | type: TABLE --
-- DROP TABLE IF EXISTS public.estado_solicitud CASCADE;
CREATE TABLE public.estado_solicitud(
	id_estado_solicitud serial NOT NULL,
	fecha date NOT NULL,
	id_estado integer NOT NULL,
	id_solicitud integer NOT NULL,
	justificacion varchar(500),
	aud_nombre_creacion varchar(250) NOT NULL,
	aud_fecha_creacion date NOT NULL,
	aud_nombre_modificacion varchar(250),
	aud_fecha_modificacion date,
	aud_status boolean NOT NULL,
	CONSTRAINT id_estado_solicitud PRIMARY KEY (id_estado_solicitud)

);
-- ddl-end --
ALTER TABLE public.estado_solicitud OWNER TO ticketuser;
-- ddl-end --

-- object: id_rol | type: CONSTRAINT --
-- ALTER TABLE public.directorio DROP CONSTRAINT IF EXISTS id_rol CASCADE;
ALTER TABLE public.directorio ADD CONSTRAINT id_rol FOREIGN KEY (id_rol)
REFERENCES public.rol (id_rol) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_departemento | type: CONSTRAINT --
-- ALTER TABLE public.directorio DROP CONSTRAINT IF EXISTS id_departemento CASCADE;
ALTER TABLE public.directorio ADD CONSTRAINT id_departemento FOREIGN KEY (id_departamento)
REFERENCES public.departamento (id_departamento) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_directorio | type: CONSTRAINT --
-- ALTER TABLE public.encargado DROP CONSTRAINT IF EXISTS id_directorio CASCADE;
ALTER TABLE public.encargado ADD CONSTRAINT id_directorio FOREIGN KEY (id_directorio)
REFERENCES public.directorio (id_directorio) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_mantenimiento_encargado | type: CONSTRAINT --
-- ALTER TABLE public.encargado DROP CONSTRAINT IF EXISTS id_mantenimiento_encargado CASCADE;
ALTER TABLE public.encargado ADD CONSTRAINT id_mantenimiento_encargado FOREIGN KEY (id_mantenimiento_encargado)
REFERENCES public.mantenimiento_encargado (id_mantenimiento_encargado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_categoria | type: CONSTRAINT --
-- ALTER TABLE public.solicitud DROP CONSTRAINT IF EXISTS id_categoria CASCADE;
ALTER TABLE public.solicitud ADD CONSTRAINT id_categoria FOREIGN KEY (id_categoria)
REFERENCES public.categoria (id_categoria) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_prioridad | type: CONSTRAINT --
-- ALTER TABLE public.solicitud DROP CONSTRAINT IF EXISTS id_prioridad CASCADE;
ALTER TABLE public.solicitud ADD CONSTRAINT id_prioridad FOREIGN KEY (id_prioridad)
REFERENCES public.prioridad (id_prioridad) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_directorio | type: CONSTRAINT --
-- ALTER TABLE public.solicitud DROP CONSTRAINT IF EXISTS id_directorio CASCADE;
ALTER TABLE public.solicitud ADD CONSTRAINT id_directorio FOREIGN KEY (id_directorio)
REFERENCES public.directorio (id_directorio) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_descripcion_mantenimiento | type: CONSTRAINT --
-- ALTER TABLE public.mantenimiento_encargado DROP CONSTRAINT IF EXISTS id_descripcion_mantenimiento CASCADE;
ALTER TABLE public.mantenimiento_encargado ADD CONSTRAINT id_descripcion_mantenimiento FOREIGN KEY (id_descripcion_mantenimiento)
REFERENCES public.descripcion_mantenimiento (id_descripcion_mantenimiento) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_solicitud | type: CONSTRAINT --
-- ALTER TABLE public.mantenimiento_encargado DROP CONSTRAINT IF EXISTS id_solicitud CASCADE;
ALTER TABLE public.mantenimiento_encargado ADD CONSTRAINT id_solicitud FOREIGN KEY (id_solicitud)
REFERENCES public.solicitud (id_solicitud) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_solicitud | type: CONSTRAINT --
-- ALTER TABLE public.estado_solicitud DROP CONSTRAINT IF EXISTS id_solicitud CASCADE;
ALTER TABLE public.estado_solicitud ADD CONSTRAINT id_solicitud FOREIGN KEY (id_solicitud)
REFERENCES public.solicitud (id_solicitud) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- ddl-end --

-- object: id_estado | type: CONSTRAINT --
-- ALTER TABLE public.estado_solicitud DROP CONSTRAINT IF EXISTS id_estado CASCADE;
ALTER TABLE public.estado_solicitud ADD CONSTRAINT id_estado FOREIGN KEY (id_estado)
REFERENCES public.estado (id_estado) MATCH FULL
ON DELETE CASCADE ON UPDATE CASCADE;
-- Insertando datos predeterminados --

INSERT INTO prioridad (id_prioridad, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 'Baja', 'diseñoII', '2018-10-24', true);
INSERT INTO prioridad (id_prioridad, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 'Media', 'diseñoII', '2018-10-24', true);
INSERT INTO prioridad (id_prioridad, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 'Alta', 'diseñoII', '2018-10-24', true);

INSERT INTO rol (id_rol, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 'Empleado', 'diseñoII', '2018-10-24', true);
INSERT INTO rol (id_rol, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 'Tecnico', 'diseñoII', '2018-10-24', true);
INSERT INTO rol (id_rol, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 'Jefe de Departamento', 'diseñoII', '2018-10-24', true);
INSERT INTO rol (id_rol, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (4, 'Gerente General', 'diseñoII', '2018-10-24', true);

INSERT INTO estado (id_estado, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 'Creado', 'diseñoII', '2018-10-24', true);
INSERT INTO estado (id_estado, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 'Asignado', 'diseñoII', '2018-10-24', true);
INSERT INTO estado (id_estado, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 'Pausado', 'diseñoII', '2018-10-24', true);
INSERT INTO estado (id_estado, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (4, 'Cerrado', 'diseñoII', '2018-10-24', true);
INSERT INTO estado (id_estado, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (5, 'Reabierto', 'diseñoII', '2018-10-24', true);

INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 'Reclutamiento', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 'Contabilidad', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 'RRHH', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (4, 'Mantenimiento', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (5, 'Seguridad', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (6, 'Teleoperadores', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (7, 'IT', 'diseñoII', '2018-10-24', true);
INSERT INTO departamento (id_departamento, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (8, 'Gerencia General', 'diseñoII', '2018-10-24', true);

INSERT INTO categoria (id_categoria, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 'Infraestructura', 'diseñoII', '2018-10-24', true);
INSERT INTO categoria (id_categoria, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 'Hardware', 'diseñoII', '2018-10-24', true);
INSERT INTO categoria (id_categoria, nombre, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 'Software', 'diseñoII', '2018-10-24', true);

INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (1, 1, 1, 'Rigoberto', 'Alexander', 'Monzon', 'Mazariego', 'monzon@gmail.com', '123', 'monzon1', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (2, 1, 2, 'Erick', 'Antonio', 'Flores', 'Lopez', 'katiro@gmail.com', '1234', 'katirox9', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (3, 1, 3, 'Nelson', 'Humberto', 'Gochez', 'Landaverde', 'gochez@gmail.com', '12345', 'ngochez', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (4, 2, 4, 'Jose', 'Enrique', 'Vides', 'Aviles', 'chepe@gmail.com', '123456', 'chepesvx', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (5, 2, 7, 'Kevin', 'Joel', 'Rojas', 'Monterroza', 'kevin@gmail.com', '1234567', 'joker', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (6, 3, 4, 'Juan', 'Antonio', 'Rivas', 'Martinez', 'juan@gmail.com', '123', 'juan1', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (7, 3, 7, 'Roberto', 'Carlos', 'Peña', 'Magaña', 'rcpenya@gmail.com', '123', 'rcpenya', 'diseñoII', '2018-10-24', true);
INSERT INTO directorio (id_directorio, id_rol, id_departamento, nombre1, nombre2, apellido1, apellido2, correo, contrasenia, usuario, aud_nombre_creacion, aud_fecha_creacion, aud_status) VALUES (8, 4, 8, 'Josseline', 'Elizabeth', 'Madrid', 'Colon', 'jossy@gmail.com', '123', 'jossy', 'diseñoII', '2018-10-24', true);


-- ddl-end --


