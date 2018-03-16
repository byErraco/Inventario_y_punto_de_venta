﻿-- MySQL Script generated by MySQL Workbench
-- mar 13 mar 2018 10:11:08 -04
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

/* SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0; */
/* SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0; */
/* SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES'; */

-- -----------------------------------------------------
-- Schema spve
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema spve
-- -----------------------------------------------------
CREATE SCHEMA spve --DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
--USE spve ;

-- -----------------------------------------------------
-- Table `spve`.`caja`
-- -----------------------------------------------------
--DROP TABLE IF EXISTS spve.caja ;

CREATE SEQUENCE spve.caja_seq;

CREATE TABLE IF NOT EXISTS spve.caja (
  id_caja INT NOT NULL DEFAULT NEXTVAL ('spve.caja_seq'),
  descripcion_caja VARCHAR(45) NOT NULL,
  activo_caja SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_caja))
;


-- -----------------------------------------------------
-- Table `spve`.`cargo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.cargo ;

CREATE SEQUENCE spve.cargo_seq;

CREATE TABLE IF NOT EXISTS spve.cargo (
  id_cargo INT NOT NULL DEFAULT NEXTVAL ('spve.cargo_seq'),
  nombre_cargo VARCHAR(45) NOT NULL,
  activo_cargo SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_cargo))
;


-- -----------------------------------------------------
-- Table `spve`.`persona`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.persona ;

CREATE SEQUENCE spve.persona_seq;

CREATE TABLE IF NOT EXISTS spve.persona (
  id_persona INT NOT NULL DEFAULT NEXTVAL ('spve.persona_seq'),
  nombre_persona VARCHAR(45) NOT NULL,
  apellido_persona VARCHAR(45) NOT NULL,
  tipo_persona VARCHAR(1) NOT NULL,
  numero_identificacion_persona VARCHAR(45) NOT NULL,
  direccion_persona VARCHAR(100),
  email_persona VARCHAR(45),
  telefono_persona VARCHAR(45),
  activo_persona SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_persona))
;


-- -----------------------------------------------------
-- Table `spve`.`empleado`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.empleado ;

CREATE SEQUENCE spve.empleado_seq;

CREATE TABLE IF NOT EXISTS spve.empleado (
  id_empleado INT NOT NULL DEFAULT NEXTVAL ('spve.empleado_seq'),
  clave VARCHAR(10) NOT NULL,
  id_cargo_empleado INT NOT NULL,
  id_persona INT NOT NULL,
  activo_empleado SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_empleado),
  CONSTRAINT fk_empleado_cargo1
    FOREIGN KEY (id_cargo_empleado)
    REFERENCES spve.cargo (id_cargo)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_empleado_persona1
    FOREIGN KEY (id_persona)
    REFERENCES spve.persona (id_persona)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`estado_caja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.estado_caja ;

CREATE SEQUENCE spve.estado_caja_seq;

CREATE TABLE IF NOT EXISTS spve.estado_caja (
  id_estado_caja INT NOT NULL DEFAULT NEXTVAL ('spve.estado_caja_seq'),
  fecha_apertura TIMESTAMP(0) NOT NULL,
  monto_apertura DECIMAL(10,0) NOT NULL,
  id_empleado INT NOT NULL,
  id_caja INT NOT NULL,
  activo_estado_caja SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_estado_caja),
  CONSTRAINT fk_estado_caja_empleado1
    FOREIGN KEY (id_empleado)
    REFERENCES spve.empleado (id_empleado)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_estado_caja_caja1
    FOREIGN KEY (id_caja)
    REFERENCES spve.caja (id_caja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`corte_caja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.corte_caja ;

CREATE SEQUENCE spve.corte_caja_seq;

CREATE TABLE IF NOT EXISTS spve.corte_caja (
  id_corte_caja INT NOT NULL DEFAULT NEXTVAL ('spve.corte_caja_seq'),
  fecha_corte TIMESTAMP(0) NOT NULL,
  monto_corte REAL NOT NULL,
  excedente_caja REAL NOT NULL,
  restante_caja DECIMAL(10,0) NOT NULL,
  id_estado_caja INT NOT NULL,
  activo_corte_caja SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_corte_caja),
  CONSTRAINT fk_corte_caja_estado_caja1
    FOREIGN KEY (id_estado_caja)
    REFERENCES spve.estado_caja (id_estado_caja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`desglose_caja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.desglose_caja ;

CREATE SEQUENCE spve.desglose_caja_seq;

CREATE TABLE IF NOT EXISTS spve.desglose_caja (
  id_desglose_caja INT NOT NULL DEFAULT NEXTVAL ('spve.desglose_caja_seq'),
  monto_desglose_caja DECIMAL(10,0) NOT NULL,
  tipo_pago_desglose VARCHAR(45) NOT NULL,
  id_corte_caja INT NOT NULL,
  activo_desglose_caja SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_desglose_caja),
  CONSTRAINT fk_desglose_caja_corte_caja1
    FOREIGN KEY (id_corte_caja)
    REFERENCES spve.corte_caja (id_corte_caja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`venta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.venta ;

CREATE SEQUENCE spve.venta_seq;

CREATE TABLE IF NOT EXISTS spve.venta (
  id_venta INT NOT NULL DEFAULT NEXTVAL ('spve.venta_seq'),
  total_previo_venta REAL NOT NULL,
  impuesto_venta REAL NOT NULL,
  total_exento REAL NOT NULL,
  abono_venta REAL NOT NULL,
  total_venta REAL NOT NULL,
  cambio_venta DECIMAL(10,0) NOT NULL,
  codigo_factura VARCHAR(45) NOT NULL,
  fecha_venta DATE NOT NULL,
  estado_venta INT NOT NULL,
  id_persona INT NOT NULL,
  activo_venta SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_venta),
  CONSTRAINT fk_venta_persona1
    FOREIGN KEY (id_persona)
    REFERENCES spve.persona (id_persona)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`tipo_pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.tipo_pago ;

CREATE SEQUENCE spve.tipo_pago_seq;

CREATE TABLE IF NOT EXISTS spve.tipo_pago (
  id_tipo_pago INT NOT NULL DEFAULT NEXTVAL ('spve.tipo_pago_seq'),
  descripcion_pago VARCHAR(45) NOT NULL,
  activo_tipo_pago SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_tipo_pago))
;


-- -----------------------------------------------------
-- Table `spve`.`pago`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.pago ;

CREATE SEQUENCE spve.pago_seq;

CREATE TABLE IF NOT EXISTS spve.pago (
  id_pago INT NOT NULL DEFAULT NEXTVAL ('spve.pago_seq'),
  monto_pago DECIMAL(10,0) NOT NULL,
  fecha_pago TIMESTAMP(0) NOT NULL,
  id_tipo_pago INT NOT NULL,
  id_venta INT NOT NULL,
  activo_pago SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_pago),
  CONSTRAINT fk_pago_tipo_pago1
    FOREIGN KEY (id_tipo_pago)
    REFERENCES spve.tipo_pago (id_tipo_pago)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_pago_venta1
    FOREIGN KEY (id_venta)
    REFERENCES spve.venta (id_venta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.producto ;

CREATE SEQUENCE spve.producto_seq;

CREATE TABLE IF NOT EXISTS spve.producto (
  id_producto INT NOT NULL DEFAULT NEXTVAL ('spve.producto_seq'),
  nombre_producto VARCHAR(45) NOT NULL,
  descripcion_producto VARCHAR(45) NOT NULL,
  codigo_venta_producto VARCHAR(45) NOT NULL,
  limite_venta_persona DECIMAL(10,0) NOT NULL,
  margen_ganancia DECIMAL(10,0) NOT NULL,
  impuesto_producto DECIMAL(10,0),
  precio_venta_producto VARCHAR(45) NOT NULL,
  periodo_venta_persona DECIMAL(10,0) NOT NULL,
  descripcion_empaque VARCHAR(45) NOT NULL,
  base_imponible REAL NOT NULL,
  cantidad_disponible DECIMAL(10,0) NOT NULL,
  balanza SMALLINT NOT NULL,
  producto_pre_fabricado SMALLINT NOT NULL,
  activo_producto SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_producto))
;


-- -----------------------------------------------------
-- Table `spve`.`venta_producto`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.venta_producto ;

CREATE SEQUENCE spve.venta_producto_seq;

CREATE TABLE IF NOT EXISTS spve.venta_producto (
  id_venta_producto INT NOT NULL DEFAULT NEXTVAL ('spve.venta_producto_seq'),
  cantidad_producto VARCHAR(45) NOT NULL,
  id_venta INT NOT NULL,
  id_producto INT NOT NULL,
  activo_venta_producto SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_venta_producto),
  CONSTRAINT fk_venta_producto_venta1
    FOREIGN KEY (id_venta)
    REFERENCES spve.venta (id_venta)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_venta_producto_producto1
    FOREIGN KEY (id_producto)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`compra`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.compra ;

CREATE SEQUENCE spve.compra_seq;

CREATE TABLE IF NOT EXISTS spve.compra (
  id_compra INT NOT NULL DEFAULT NEXTVAL ('spve.compra_seq'),
  proveedor_compra VARCHAR(45) NOT NULL,
  cantidad_compra REAL NOT NULL,
  fecha_compra DATE NOT NULL,
  costo_unidad_compra DECIMAL(10,0) NOT NULL,
  orden_compra VARCHAR(45) NOT NULL,
  id_producto INT NOT NULL,
  activo_compra SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_compra),
  CONSTRAINT fk_compra_producto1
    FOREIGN KEY (id_producto)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`produccion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.produccion ;

CREATE SEQUENCE spve.produccion_seq;

CREATE TABLE IF NOT EXISTS spve.produccion (
  id_produccion INT NOT NULL DEFAULT NEXTVAL ('spve.produccion_seq'),
  cantidad_produccion DECIMAL(10,0) NOT NULL,
  fecha_produccion DATE NOT NULL,
  id_producto INT NOT NULL,
  activo_produccion SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_produccion),
  CONSTRAINT fk_produccion_producto1
    FOREIGN KEY (id_producto)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`ajuste`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.ajuste ;

CREATE SEQUENCE spve.ajuste_seq;

CREATE TABLE IF NOT EXISTS spve.ajuste (
  id_ajuste INT NOT NULL DEFAULT NEXTVAL ('spve.ajuste_seq'),
  fecha_ajuste DATE NOT NULL,
  cantidad_ajuste DECIMAL(10,0) NOT NULL,
  descripcion_ajuste VARCHAR(100) NOT NULL,
  id_producto INT NOT NULL,
  activo_ajuste SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_ajuste),
  CONSTRAINT fk_ajuste_producto1
    FOREIGN KEY (id_producto)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`cierre_caja`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.cierre_caja ;

CREATE SEQUENCE spve.cierre_caja_seq;

CREATE TABLE IF NOT EXISTS spve.cierre_caja (
  id_cierre_caja INT NOT NULL DEFAULT NEXTVAL ('spve.cierre_caja_seq'),
  id_corte_caja INT NOT NULL,
  activo_cierre_caja SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_cierre_caja),
  CONSTRAINT fk_cierre_caja_corte_caja1
    FOREIGN KEY (id_corte_caja)
    REFERENCES spve.corte_caja (id_corte_caja)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table `spve`.`producto_componente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS spve.producto_componente ;

CREATE SEQUENCE spve.producto_componente_seq;

CREATE TABLE IF NOT EXISTS spve.producto_componente (
  id_producto_componente INT NOT NULL DEFAULT NEXTVAL ('spve.producto_componente_seq'),
  id_producto INT NOT NULL,
  cantidad_compuesto DECIMAL(10,0) NOT NULL,
  activo_producto_componente SMALLINT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_producto_componente, id_producto),
  CONSTRAINT fk_producto_has_producto_producto1
    FOREIGN KEY (id_producto)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_producto_has_producto_producto2
    FOREIGN KEY (id_producto_componente)
    REFERENCES spve.producto (id_producto)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


/* SET SQL_MODE=@OLD_SQL_MODE; */
/* SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS; */
/* SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS; */
