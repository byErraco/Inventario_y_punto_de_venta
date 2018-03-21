--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.11
-- Dumped by pg_dump version 9.5.11

-- Started on 2018-03-21 10:53:35 -04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 2381 (class 0 OID 0)
-- Dependencies: 2380
-- Name: DATABASE stpv; Type: COMMENT; Schema: -; Owner: inverdata
--

COMMENT ON DATABASE stpv IS 'Ambiente de pruebas para las actualizaciones de la base de datos de punto de venta';


--
-- TOC entry 7 (class 2615 OID 78133)
-- Name: spve; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA spve;


ALTER SCHEMA spve OWNER TO postgres;

--
-- TOC entry 1 (class 3079 OID 12395)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2382 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = spve, pg_catalog;

--
-- TOC entry 213 (class 1259 OID 78352)
-- Name: ajuste_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE ajuste_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE ajuste_seq OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 214 (class 1259 OID 78354)
-- Name: ajuste; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE ajuste (
    id_ajuste integer DEFAULT nextval('ajuste_seq'::regclass) NOT NULL,
    fecha_ajuste date NOT NULL,
    cantidad_ajuste numeric(10,0) NOT NULL,
    descripcion_ajuste character varying(100) NOT NULL,
    id_producto integer NOT NULL,
    activo_ajuste smallint DEFAULT 1 NOT NULL
);


ALTER TABLE ajuste OWNER TO postgres;

--
-- TOC entry 181 (class 1259 OID 78134)
-- Name: caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE caja_seq OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 78136)
-- Name: caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE caja (
    id_caja integer DEFAULT nextval('caja_seq'::regclass) NOT NULL,
    descripcion_caja character varying(45) NOT NULL,
    activo_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE caja OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 78143)
-- Name: cargo_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cargo_seq OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 78145)
-- Name: cargo; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE cargo (
    id_cargo integer DEFAULT nextval('cargo_seq'::regclass) NOT NULL,
    nombre_cargo character varying(45) NOT NULL,
    activo_cargo smallint DEFAULT 1 NOT NULL
);


ALTER TABLE cargo OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 78366)
-- Name: cierre_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE cierre_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE cierre_caja_seq OWNER TO postgres;

--
-- TOC entry 216 (class 1259 OID 78368)
-- Name: cierre_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE cierre_caja (
    id_cierre_caja integer DEFAULT nextval('cierre_caja_seq'::regclass) NOT NULL,
    id_corte_caja integer NOT NULL,
    activo_cierre_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE cierre_caja OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 78416)
-- Name: codigo_factura_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE codigo_factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE codigo_factura_seq OWNER TO postgres;

--
-- TOC entry 209 (class 1259 OID 78324)
-- Name: compra_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE compra_seq OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 78326)
-- Name: compra; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE compra (
    id_compra integer DEFAULT nextval('compra_seq'::regclass) NOT NULL,
    proveedor_compra character varying(45) NOT NULL,
    cantidad_compra real NOT NULL,
    fecha_compra date NOT NULL,
    costo_unidad_compra numeric(10,0) NOT NULL,
    orden_compra character varying(45) NOT NULL,
    id_producto integer NOT NULL,
    activo_compra smallint DEFAULT 1 NOT NULL
);


ALTER TABLE compra OWNER TO postgres;

--
-- TOC entry 191 (class 1259 OID 78199)
-- Name: corte_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE corte_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE corte_caja_seq OWNER TO postgres;

--
-- TOC entry 192 (class 1259 OID 78201)
-- Name: corte_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE corte_caja (
    id_corte_caja integer DEFAULT nextval('corte_caja_seq'::regclass) NOT NULL,
    fecha_corte timestamp(0) without time zone NOT NULL,
    monto_corte real NOT NULL,
    excedente_caja real NOT NULL,
    restante_caja numeric(10,0) NOT NULL,
    id_estado_caja integer NOT NULL,
    activo_corte_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE corte_caja OWNER TO postgres;

--
-- TOC entry 193 (class 1259 OID 78213)
-- Name: desglose_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE desglose_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE desglose_caja_seq OWNER TO postgres;

--
-- TOC entry 194 (class 1259 OID 78215)
-- Name: desglose_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE desglose_caja (
    id_desglose_caja integer DEFAULT nextval('desglose_caja_seq'::regclass) NOT NULL,
    monto_desglose_caja numeric(10,0) NOT NULL,
    tipo_pago_desglose character varying(45) NOT NULL,
    id_corte_caja integer NOT NULL,
    activo_desglose_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE desglose_caja OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 78161)
-- Name: empleado_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE empleado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE empleado_seq OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 78163)
-- Name: empleado; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE empleado (
    id_empleado integer DEFAULT nextval('empleado_seq'::regclass) NOT NULL,
    clave character varying(10) NOT NULL,
    id_cargo integer NOT NULL,
    id_persona integer NOT NULL,
    activo_empleado smallint DEFAULT 1 NOT NULL
);


ALTER TABLE empleado OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 78180)
-- Name: estado_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE estado_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE estado_caja_seq OWNER TO postgres;

--
-- TOC entry 190 (class 1259 OID 78182)
-- Name: estado_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE estado_caja (
    id_estado_caja integer DEFAULT nextval('estado_caja_seq'::regclass) NOT NULL,
    fecha_apertura timestamp(0) without time zone NOT NULL,
    monto_apertura numeric(10,0) NOT NULL,
    id_empleado integer NOT NULL,
    id_caja integer NOT NULL,
    activo_estado_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE estado_caja OWNER TO postgres;

--
-- TOC entry 199 (class 1259 OID 78250)
-- Name: pago_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE pago_seq OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 78252)
-- Name: pago; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE pago (
    id_pago integer DEFAULT nextval('pago_seq'::regclass) NOT NULL,
    monto_pago real NOT NULL,
    fecha_pago timestamp(0) without time zone NOT NULL,
    id_tipo_pago integer NOT NULL,
    id_venta integer NOT NULL,
    activo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE pago OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 78278)
-- Name: periodo_venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE periodo_venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE periodo_venta_producto_seq OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 78280)
-- Name: periodo_venta_producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE periodo_venta_producto (
    id_periodo_venta_producto integer DEFAULT nextval('periodo_venta_producto_seq'::regclass) NOT NULL,
    nombre_periodo_venta character varying(45) NOT NULL,
    activo_periodo_venta smallint NOT NULL
);


ALTER TABLE periodo_venta_producto OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 78152)
-- Name: persona_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE persona_seq OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 78154)
-- Name: persona; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE persona (
    id_persona integer DEFAULT nextval('persona_seq'::regclass) NOT NULL,
    nombre_persona character varying(45) NOT NULL,
    apellido_persona character varying(45) NOT NULL,
    numero_identificacion_persona character varying(45) NOT NULL,
    direccion_persona character varying(100),
    tipo_persona character varying(1) NOT NULL,
    email_persona character varying(45),
    telefono_persona character varying(45),
    activo_persona smallint DEFAULT 1 NOT NULL
);


ALTER TABLE persona OWNER TO postgres;

--
-- TOC entry 201 (class 1259 OID 78269)
-- Name: precio_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE precio_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE precio_producto_seq OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 78271)
-- Name: precio_producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE precio_producto (
    id_precio_producto integer DEFAULT nextval('precio_producto_seq'::regclass) NOT NULL,
    fecha_registro_precio date NOT NULL,
    margen_ganancia numeric(10,0) NOT NULL,
    impuesto_producto numeric(10,0),
    precio_venta_publico numeric(10,0) NOT NULL,
    base_imponible real NOT NULL,
    producto_exento smallint NOT NULL,
    activo_precio_producto smallint DEFAULT 1 NOT NULL,
    porcentaje_impuesto_producto real
);


ALTER TABLE precio_producto OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 78338)
-- Name: produccion_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE produccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE produccion_seq OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 78340)
-- Name: produccion; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE produccion (
    id_produccion integer DEFAULT nextval('produccion_seq'::regclass) NOT NULL,
    cantidad_produccion numeric(10,0) NOT NULL,
    fecha_produccion date NOT NULL,
    id_producto integer NOT NULL,
    activo_produccion smallint DEFAULT 1 NOT NULL
);


ALTER TABLE produccion OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 78286)
-- Name: producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE producto_seq OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 78288)
-- Name: producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE producto (
    id_producto integer DEFAULT nextval('producto_seq'::regclass) NOT NULL,
    descripcion_producto character varying(45) NOT NULL,
    codigo_venta_producto character varying(45) NOT NULL,
    limite_venta_persona numeric(10,0),
    descripcion_empaque character varying(45) NOT NULL,
    cantidad_disponible real NOT NULL,
    balanza smallint NOT NULL,
    producto_pre_fabricado smallint NOT NULL,
    id_precio_producto integer NOT NULL,
    id_periodo_venta_producto integer,
    activo_producto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE producto OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 78380)
-- Name: producto_componente_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE producto_componente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE producto_componente_seq OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 78382)
-- Name: producto_componente; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE producto_componente (
    id_producto_componente integer DEFAULT nextval('producto_componente_seq'::regclass) NOT NULL,
    id_producto integer NOT NULL,
    cantidad_compuesto numeric(10,0) NOT NULL,
    activo_producto_componente smallint DEFAULT 1 NOT NULL
);


ALTER TABLE producto_componente OWNER TO postgres;

--
-- TOC entry 197 (class 1259 OID 78241)
-- Name: tipo_pago_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE tipo_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE tipo_pago_seq OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 78243)
-- Name: tipo_pago; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE tipo_pago (
    id_tipo_pago integer DEFAULT nextval('tipo_pago_seq'::regclass) NOT NULL,
    descripcion_pago character varying(45) NOT NULL,
    activo_tipo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE tipo_pago OWNER TO postgres;

--
-- TOC entry 195 (class 1259 OID 78227)
-- Name: venta_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venta_seq OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 78229)
-- Name: venta; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE venta (
    id_venta integer DEFAULT nextval('venta_seq'::regclass) NOT NULL,
    codigo_factura integer NOT NULL,
    fecha_venta date NOT NULL,
    estado_venta integer NOT NULL,
    id_persona integer NOT NULL,
    activo_venta smallint DEFAULT 1 NOT NULL
);


ALTER TABLE venta OWNER TO postgres;

--
-- TOC entry 207 (class 1259 OID 78305)
-- Name: venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE venta_producto_seq OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 78307)
-- Name: venta_producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE venta_producto (
    id_venta_producto integer DEFAULT nextval('venta_producto_seq'::regclass) NOT NULL,
    cantidad_producto real NOT NULL,
    id_venta integer NOT NULL,
    id_producto integer NOT NULL,
    activo_venta_producto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE venta_producto OWNER TO postgres;

-- Completed on 2018-03-21 10:53:35 -04

--
-- PostgreSQL database dump complete
--

