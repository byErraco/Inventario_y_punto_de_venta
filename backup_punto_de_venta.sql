--
-- inverdataQL database dump
--

-- Dumped from database version 9.5.15
-- Dumped by pg_dump version 11.1 (Ubuntu 11.1-1.pgdg16.04+1)

-- Started on 2019-01-30 10:40:43 -04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 100720)
-- Name: spve; Type: SCHEMA; Schema: -; Owner: inverdata
--

CREATE SCHEMA spve;


ALTER SCHEMA spve OWNER TO inverdata;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 222 (class 1259 OID 168480)
-- Name: jpais; Type: TABLE; Schema: public; Owner: inverdata
--

CREATE TABLE public.jpais (
    id_pais integer NOT NULL,
    nombre_pais character varying(50),
    id_zona_horaria character varying(50),
    moneda character varying(30),
    nacionalidad character varying(4),
    gmt_zona character varying(30),
    simbolo character varying(10)
);


ALTER TABLE public.jpais OWNER TO inverdata;

--
-- TOC entry 221 (class 1259 OID 168477)
-- Name: pais; Type: TABLE; Schema: public; Owner: inverdata
--

CREATE TABLE public.pais (
    id_pais integer NOT NULL,
    nombre_pais character varying(50),
    id_zona_horaria character varying(50),
    moneda character varying(30),
    nacionalidad character varying(4),
    gmt_zona character varying(30),
    simbolo character varying(10)
);


ALTER TABLE public.pais OWNER TO inverdata;

--
-- TOC entry 213 (class 1259 OID 100931)
-- Name: ajuste_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.ajuste_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.ajuste_seq OWNER TO inverdata;

--
-- TOC entry 214 (class 1259 OID 100933)
-- Name: ajuste; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.ajuste (
    id_ajuste integer DEFAULT nextval('spve.ajuste_seq'::regclass) NOT NULL,
    fecha_ajuste date NOT NULL,
    cantidad_ajuste numeric(10,0) NOT NULL,
    descripcion_ajuste character varying(100) NOT NULL,
    id_producto integer NOT NULL,
    activo_ajuste smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.ajuste OWNER TO inverdata;

--
-- TOC entry 182 (class 1259 OID 100721)
-- Name: caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.caja_seq OWNER TO inverdata;

--
-- TOC entry 183 (class 1259 OID 100723)
-- Name: caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.caja (
    id_caja integer DEFAULT nextval('spve.caja_seq'::regclass) NOT NULL,
    descripcion_caja character varying(45) NOT NULL,
    activo_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.caja OWNER TO inverdata;

--
-- TOC entry 184 (class 1259 OID 100730)
-- Name: cargo_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.cargo_seq OWNER TO inverdata;

--
-- TOC entry 185 (class 1259 OID 100732)
-- Name: cargo; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.cargo (
    id_cargo integer DEFAULT nextval('spve.cargo_seq'::regclass) NOT NULL,
    nombre_cargo character varying(45) NOT NULL,
    activo_cargo smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.cargo OWNER TO inverdata;

--
-- TOC entry 229 (class 1259 OID 218144)
-- Name: cierre_caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.cierre_caja (
    id_cierre_caja integer NOT NULL,
    monto_fisico double precision NOT NULL,
    monto_sistema double precision NOT NULL,
    monto_cortes double precision NOT NULL,
    fecha_cierre timestamp without time zone NOT NULL,
    reporte_cierre bytea,
    id_empleado integer NOT NULL,
    id_estado_caja integer NOT NULL
);


ALTER TABLE spve.cierre_caja OWNER TO inverdata;

--
-- TOC entry 228 (class 1259 OID 218142)
-- Name: cierre_caja_id_cierre_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.cierre_caja_id_cierre_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.cierre_caja_id_cierre_caja_seq OWNER TO inverdata;

--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 228
-- Name: cierre_caja_id_cierre_caja_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.cierre_caja_id_cierre_caja_seq OWNED BY spve.cierre_caja.id_cierre_caja;


--
-- TOC entry 215 (class 1259 OID 100945)
-- Name: cierre_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.cierre_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.cierre_caja_seq OWNER TO inverdata;

--
-- TOC entry 197 (class 1259 OID 100816)
-- Name: codigo_factura_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.codigo_factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.codigo_factura_seq OWNER TO inverdata;

--
-- TOC entry 209 (class 1259 OID 100903)
-- Name: compra_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.compra_seq OWNER TO inverdata;

--
-- TOC entry 210 (class 1259 OID 100905)
-- Name: compra; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.compra (
    id_compra integer DEFAULT nextval('spve.compra_seq'::regclass) NOT NULL,
    proveedor_compra character varying(45) NOT NULL,
    cantidad_compra real NOT NULL,
    fecha_compra date NOT NULL,
    costo_unidad_compra numeric(10,0) NOT NULL,
    orden_compra character varying(45) NOT NULL,
    id_producto integer NOT NULL,
    activo_compra smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.compra OWNER TO inverdata;

--
-- TOC entry 192 (class 1259 OID 100786)
-- Name: corte_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.corte_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.corte_caja_seq OWNER TO inverdata;

--
-- TOC entry 193 (class 1259 OID 100788)
-- Name: corte_caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.corte_caja (
    id_corte_caja integer DEFAULT nextval('spve.corte_caja_seq'::regclass) NOT NULL,
    fecha_corte timestamp(0) without time zone NOT NULL,
    monto_corte real NOT NULL,
    excedente_caja double precision NOT NULL,
    restante_caja double precision NOT NULL,
    id_estado_caja integer NOT NULL,
    activo_corte_caja smallint DEFAULT 1 NOT NULL,
    id_empleado integer NOT NULL,
    reporte_corte bytea
);


ALTER TABLE spve.corte_caja OWNER TO inverdata;

--
-- TOC entry 194 (class 1259 OID 100800)
-- Name: desglose_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.desglose_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.desglose_caja_seq OWNER TO inverdata;

--
-- TOC entry 195 (class 1259 OID 100802)
-- Name: desglose_caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.desglose_caja (
    id_desglose_caja integer DEFAULT nextval('spve.desglose_caja_seq'::regclass) NOT NULL,
    monto_desglose_caja double precision NOT NULL,
    id_corte_caja integer NOT NULL,
    activo_desglose_caja smallint DEFAULT 1 NOT NULL,
    id_tipo_pago integer NOT NULL
);


ALTER TABLE spve.desglose_caja OWNER TO inverdata;

--
-- TOC entry 188 (class 1259 OID 100748)
-- Name: empleado_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.empleado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.empleado_seq OWNER TO inverdata;

--
-- TOC entry 189 (class 1259 OID 100750)
-- Name: empleado; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.empleado (
    id_empleado integer DEFAULT nextval('spve.empleado_seq'::regclass) NOT NULL,
    clave character varying(10) NOT NULL,
    id_cargo integer NOT NULL,
    id_persona integer NOT NULL,
    activo_empleado smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.empleado OWNER TO inverdata;

--
-- TOC entry 190 (class 1259 OID 100767)
-- Name: estado_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.estado_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.estado_caja_seq OWNER TO inverdata;

--
-- TOC entry 191 (class 1259 OID 100769)
-- Name: estado_caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.estado_caja (
    id_estado_caja integer DEFAULT nextval('spve.estado_caja_seq'::regclass) NOT NULL,
    fecha_apertura timestamp(0) without time zone NOT NULL,
    monto_apertura numeric(10,0) NOT NULL,
    id_empleado integer NOT NULL,
    id_caja integer NOT NULL,
    activo_estado_caja smallint DEFAULT 1 NOT NULL,
    caja_abierta smallint NOT NULL
);


ALTER TABLE spve.estado_caja OWNER TO inverdata;

--
-- TOC entry 227 (class 1259 OID 201621)
-- Name: montos_cierre_caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.montos_cierre_caja (
    id_montos_cierre_caja integer NOT NULL,
    monto double precision NOT NULL,
    id_tipo_pago integer NOT NULL,
    id_tipo_monto_cierre integer NOT NULL,
    id_cierre_caja integer NOT NULL,
    activo_monto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.montos_cierre_caja OWNER TO inverdata;

--
-- TOC entry 226 (class 1259 OID 201619)
-- Name: montos_cierre_caja_id_montos_cierre_caja_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.montos_cierre_caja_id_montos_cierre_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.montos_cierre_caja_id_montos_cierre_caja_seq OWNER TO inverdata;

--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 226
-- Name: montos_cierre_caja_id_montos_cierre_caja_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.montos_cierre_caja_id_montos_cierre_caja_seq OWNED BY spve.montos_cierre_caja.id_montos_cierre_caja;


--
-- TOC entry 201 (class 1259 OID 100840)
-- Name: pago_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.pago_seq OWNER TO inverdata;

--
-- TOC entry 202 (class 1259 OID 100842)
-- Name: pago; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.pago (
    id_pago integer DEFAULT nextval('spve.pago_seq'::regclass) NOT NULL,
    monto_pago double precision NOT NULL,
    fecha_pago timestamp(0) without time zone NOT NULL,
    id_tipo_pago integer NOT NULL,
    id_venta integer NOT NULL,
    activo_pago smallint DEFAULT 1 NOT NULL,
    corte_realizado smallint DEFAULT 0 NOT NULL
);


ALTER TABLE spve.pago OWNER TO inverdata;

--
-- TOC entry 223 (class 1259 OID 168483)
-- Name: pais; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.pais (
    id_pais integer NOT NULL,
    nombre_pais character varying(50),
    id_zona_horaria character varying(50),
    moneda character varying(30),
    nacionalidad character varying(4),
    gmt_zona character varying(40),
    simbolo character varying(10),
    formato_id character varying(18),
    activo boolean,
    identificacion character varying(10)
);


ALTER TABLE spve.pais OWNER TO inverdata;

--
-- TOC entry 220 (class 1259 OID 168469)
-- Name: parametros; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.parametros (
    nombre character varying NOT NULL,
    valor character varying NOT NULL
);


ALTER TABLE spve.parametros OWNER TO inverdata;

--
-- TOC entry 203 (class 1259 OID 100859)
-- Name: periodo_venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.periodo_venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.periodo_venta_producto_seq OWNER TO inverdata;

--
-- TOC entry 204 (class 1259 OID 100861)
-- Name: periodo_venta_producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.periodo_venta_producto (
    id_periodo_venta_producto integer DEFAULT nextval('spve.periodo_venta_producto_seq'::regclass) NOT NULL,
    nombre_periodo_venta character varying(45) NOT NULL,
    activo_periodo_venta smallint NOT NULL
);


ALTER TABLE spve.periodo_venta_producto OWNER TO inverdata;

--
-- TOC entry 186 (class 1259 OID 100739)
-- Name: persona_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.persona_seq OWNER TO inverdata;

--
-- TOC entry 187 (class 1259 OID 100741)
-- Name: persona; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.persona (
    id_persona integer DEFAULT nextval('spve.persona_seq'::regclass) NOT NULL,
    nombre_persona character varying(45) NOT NULL,
    apellido_persona character varying(45) NOT NULL,
    tipo_persona character varying(4) NOT NULL,
    numero_identificacion_persona character varying(45) NOT NULL,
    direccion_persona character varying(100) NOT NULL,
    email_persona character varying(45),
    telefono_persona character varying(45),
    activo_persona smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.persona OWNER TO inverdata;

--
-- TOC entry 218 (class 1259 OID 100978)
-- Name: precio_producto_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.precio_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.precio_producto_seq OWNER TO inverdata;

--
-- TOC entry 219 (class 1259 OID 100980)
-- Name: precio_producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.precio_producto (
    id_precio_producto integer DEFAULT nextval('spve.precio_producto_seq'::regclass) NOT NULL,
    fecha_registro_precio date NOT NULL,
    margen_ganancia numeric(10,0),
    porcentaje_impuesto_producto real NOT NULL,
    impuesto_producto numeric(10,0) NOT NULL,
    precio_venta_publico numeric(10,0) NOT NULL,
    base_imponible real,
    producto_exento smallint DEFAULT 0 NOT NULL,
    activo_precio_producto smallint DEFAULT 1 NOT NULL,
    id_producto integer NOT NULL
);


ALTER TABLE spve.precio_producto OWNER TO inverdata;

--
-- TOC entry 211 (class 1259 OID 100917)
-- Name: produccion_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.produccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.produccion_seq OWNER TO inverdata;

--
-- TOC entry 212 (class 1259 OID 100919)
-- Name: produccion; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.produccion (
    id_produccion integer DEFAULT nextval('spve.produccion_seq'::regclass) NOT NULL,
    cantidad_produccion numeric(10,0) NOT NULL,
    fecha_produccion date NOT NULL,
    id_producto integer NOT NULL,
    activo_produccion smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.produccion OWNER TO inverdata;

--
-- TOC entry 205 (class 1259 OID 100867)
-- Name: producto_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.producto_seq OWNER TO inverdata;

--
-- TOC entry 206 (class 1259 OID 100869)
-- Name: producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.producto (
    id_producto integer DEFAULT nextval('spve.producto_seq'::regclass) NOT NULL,
    descripcion_producto character varying(45) NOT NULL,
    codigo_venta_producto character varying(45) NOT NULL,
    limite_venta_persona numeric(10,0),
    descripcion_empaque character varying(45),
    cantidad_disponible numeric(10,0) DEFAULT 0 NOT NULL,
    balanza smallint DEFAULT 0 NOT NULL,
    producto_pre_fabricado smallint DEFAULT 0 NOT NULL,
    activo_producto smallint DEFAULT 1 NOT NULL,
    id_periodo_venta_producto integer
);


ALTER TABLE spve.producto OWNER TO inverdata;

--
-- TOC entry 216 (class 1259 OID 100959)
-- Name: producto_componente_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.producto_componente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.producto_componente_seq OWNER TO inverdata;

--
-- TOC entry 217 (class 1259 OID 100961)
-- Name: producto_componente; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.producto_componente (
    id_producto_componente integer DEFAULT nextval('spve.producto_componente_seq'::regclass) NOT NULL,
    id_producto integer NOT NULL,
    cantidad_compuesto numeric(10,0) NOT NULL,
    activo_producto_componente smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.producto_componente OWNER TO inverdata;

--
-- TOC entry 225 (class 1259 OID 201612)
-- Name: tipo_monto_cierre; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.tipo_monto_cierre (
    id_tipo_monto_cierre integer NOT NULL,
    descripcion_monto character varying(50) NOT NULL,
    activo_tipo_monto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.tipo_monto_cierre OWNER TO inverdata;

--
-- TOC entry 224 (class 1259 OID 201610)
-- Name: tipo_monto_cierre_id_tipo_monto_cierre_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.tipo_monto_cierre_id_tipo_monto_cierre_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.tipo_monto_cierre_id_tipo_monto_cierre_seq OWNER TO inverdata;

--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 224
-- Name: tipo_monto_cierre_id_tipo_monto_cierre_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.tipo_monto_cierre_id_tipo_monto_cierre_seq OWNED BY spve.tipo_monto_cierre.id_tipo_monto_cierre;


--
-- TOC entry 199 (class 1259 OID 100831)
-- Name: tipo_pago_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.tipo_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.tipo_pago_seq OWNER TO inverdata;

--
-- TOC entry 200 (class 1259 OID 100833)
-- Name: tipo_pago; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.tipo_pago (
    id_tipo_pago integer DEFAULT nextval('spve.tipo_pago_seq'::regclass) NOT NULL,
    descripcion_pago character varying(45) NOT NULL,
    activo_tipo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.tipo_pago OWNER TO inverdata;

--
-- TOC entry 196 (class 1259 OID 100814)
-- Name: venta_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.venta_seq OWNER TO inverdata;

--
-- TOC entry 198 (class 1259 OID 100818)
-- Name: venta; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.venta (
    id_venta integer DEFAULT nextval('spve.venta_seq'::regclass) NOT NULL,
    codigo_factura integer DEFAULT nextval('spve.codigo_factura_seq'::regclass) NOT NULL,
    fecha_venta timestamp(0) without time zone NOT NULL,
    estado_venta integer NOT NULL,
    id_persona integer NOT NULL,
    activo_venta smallint DEFAULT 1 NOT NULL,
    id_estado_caja integer,
    reporte_venta bytea
);


ALTER TABLE spve.venta OWNER TO inverdata;

--
-- TOC entry 207 (class 1259 OID 100884)
-- Name: venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: inverdata
--

CREATE SEQUENCE spve.venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.venta_producto_seq OWNER TO inverdata;

--
-- TOC entry 208 (class 1259 OID 100886)
-- Name: venta_producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.venta_producto (
    id_venta_producto integer DEFAULT nextval('spve.venta_producto_seq'::regclass) NOT NULL,
    cantidad_producto real NOT NULL,
    id_venta integer NOT NULL,
    id_producto integer NOT NULL,
    activo_venta_producto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.venta_producto OWNER TO inverdata;

--
-- TOC entry 2249 (class 2604 OID 218147)
-- Name: cierre_caja id_cierre_caja; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja ALTER COLUMN id_cierre_caja SET DEFAULT nextval('spve.cierre_caja_id_cierre_caja_seq'::regclass);


--
-- TOC entry 2247 (class 2604 OID 201624)
-- Name: montos_cierre_caja id_montos_cierre_caja; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja ALTER COLUMN id_montos_cierre_caja SET DEFAULT nextval('spve.montos_cierre_caja_id_montos_cierre_caja_seq'::regclass);


--
-- TOC entry 2245 (class 2604 OID 201615)
-- Name: tipo_monto_cierre id_tipo_monto_cierre; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_monto_cierre ALTER COLUMN id_tipo_monto_cierre SET DEFAULT nextval('spve.tipo_monto_cierre_id_tipo_monto_cierre_seq'::regclass);


--
-- TOC entry 2474 (class 0 OID 168480)
-- Dependencies: 222
-- Data for Name: jpais; Type: TABLE DATA; Schema: public; Owner: inverdata
--



--
-- TOC entry 2473 (class 0 OID 168477)
-- Dependencies: 221
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: inverdata
--



--
-- TOC entry 2466 (class 0 OID 100933)
-- Dependencies: 214
-- Data for Name: ajuste; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- TOC entry 2435 (class 0 OID 100723)
-- Dependencies: 183
-- Data for Name: caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.caja (id_caja, descripcion_caja, activo_caja) VALUES (1, 'Caja 1', 1);


--
-- TOC entry 2437 (class 0 OID 100732)
-- Dependencies: 185
-- Data for Name: cargo; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (1, 'Administrador', 1);
INSERT INTO spve.cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (2, 'Gerente', 1);
INSERT INTO spve.cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (3, 'Cajero', 1);


--
-- TOC entry 2481 (class 0 OID 218144)
-- Dependencies: 229
-- Data for Name: cierre_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- TOC entry 2462 (class 0 OID 100905)
-- Dependencies: 210
-- Data for Name: compra; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- TOC entry 2445 (class 0 OID 100788)
-- Dependencies: 193
-- Data for Name: corte_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.corte_caja (id_corte_caja, fecha_corte, monto_corte, excedente_caja, restante_caja, id_estado_caja, activo_corte_caja, id_empleado, reporte_corte) VALUES (82, '2019-01-24 09:52:08', 201, 1, 0, 71, 1, 98, '\x255044462d312e340a25e2e3cfd30a332030206f626a0a3c3c2f46696c7465722f466c6174654465636f64652f4c656e677468203730323e3e73747265616d0a789cbd98cb72d3301486f77e0a2d5894058e6e762476905e98ce9401ea1dc342d86a7149e2e29af2cc7d0b8e944bcbc5a7668eca6426d124b63e9f5fbfff23e75bf6baca54c974a959d5644755f63e93ec347c2b188757782f3867d52a9b1d0b266074911d3cafaec2b1f7877056af1e9ea46c6e0dd346e656efcf95e15c1e8fe82fb38345d70f9e359e2ddc9563ef5c5fb76e1966e6ecf22fb37ffc049f4dbcba712e5422556ecb1dd3fc8a3c5a5d2fbd6bba97748c90e398856fbe2f5d0208170824a8f6b627d75268d00c29c635ab76cde23b9554e0b271eafc4131fe88628208313ae7504751e6468e70daf5adef1b3738226aae73abe0d64450422ab894b9322f24b52e284a23a4a6ed7d5db71dd50486c76450080a4c224a259521a284944c99f9b821a49e7131935c58ea4ae5720e28338e3af6f597c989f0030e3add9efce164f79b62aa1079c15699e4e576bcccce27de1b4c053b8d5dde9bae77318a432653134528d043e0409b1792bd3aa39a892985885eb5d7e478145ae390b36e3d902956e4dac29a82598b1dc7fed6bbd6b151522d54da7b0bc5f1640b81d81259d1a30b5f0fed6d97426f8cc3736a9f105ce28867745b7224760eef3eb743129d304a229d30045d2769b17edddf356984423190ff49a4422109a49a231bdb85bf195cd5d65ffd90422e0c95c65728822a96862acc8324dd3cb5dccf7fd2bb35abbac12d537407a12d662ed02b85602865aa60635d41ca62df1536e3c95d212e264f706de38b19a71f6b8b7119d9aef9d0d7b3c0aba1bb1f0c63cbc7ea39f4a145d0ab2937b0a7bd975144827b39fc8b80cbb5e87d93402f6095e6515688da21466d92f430c813714c8f04968e653da509148ea09a20ba0c138adc5e75d87f4b85380df6dfe7edcde057b42d38c4193c666fc276be1dff11b663392d4abdcfe9cdf81f1e006521ff4f4f0437c8e209db7b0c690c307d47f713912f3eaf0a656e6473747265616d0a656e646f626a0a312030206f626a0a3c3c2f47726f75703c3c2f532f5472616e73706172656e63792f547970652f47726f75702f43532f4465766963655247423e3e2f436f6e74656e74732033203020522f547970652f506167652f5265736f75726365733c3c2f436f6c6f7253706163653c3c2f43532f4465766963655247423e3e2f50726f63536574205b2f504446202f54657874202f496d61676542202f496d61676543202f496d616765495d2f466f6e743c3c2f46312032203020523e3e3e3e2f506172656e742034203020522f4d65646961426f785b30203020323135203530305d3e3e0a656e646f626a0a352030206f626a0a5b31203020522f58595a20302035313020305d0a656e646f626a0a322030206f626a0a3c3c2f537562747970652f54797065312f547970652f466f6e742f42617365466f6e742f48656c7665746963612f456e636f64696e672f57696e416e7369456e636f64696e673e3e0a656e646f626a0a342030206f626a0a3c3c2f4b6964735b31203020525d2f547970652f50616765732f436f756e7420312f4954585428322e312e37293e3e0a656e646f626a0a362030206f626a0a3c3c2f4e616d65735b284a525f504147455f414e43484f525f305f31292035203020525d3e3e0a656e646f626a0a372030206f626a0a3c3c2f44657374732036203020523e3e0a656e646f626a0a382030206f626a0a3c3c2f4e616d65732037203020522f547970652f436174616c6f672f50616765732034203020522f566965776572507265666572656e6365733c3c2f5072696e745363616c696e672f41707044656661756c743e3e3e3e0a656e646f626a0a392030206f626a0a3c3c2f4d6f644461746528443a32303139303132343039353230392d303427303027292f43726561746f72284a61737065725265706f727473205c287265706f7274206e616d655c29292f4372656174696f6e4461746528443a32303139303132343039353230392d303427303027292f50726f647563657228695465787420322e312e37206279203154335854293e3e0a656e646f626a0a787265660a302031300a303030303030303030302036353533352066200a30303030303030373834203030303030206e200a30303030303031303533203030303030206e200a30303030303030303135203030303030206e200a30303030303031313431203030303030206e200a30303030303031303138203030303030206e200a30303030303031323034203030303030206e200a30303030303031323538203030303030206e200a30303030303031323930203030303030206e200a30303030303031333933203030303030206e200a747261696c65720a3c3c2f496e666f2039203020522f4944205b3c35663562666465323961666238663331303639323436363465336132333536373e3c33366462646236363535363937613635323734663331313131396339666533383e5d2f526f6f742038203020522f53697a652031303e3e0a7374617274787265660a313535340a2525454f460a');


--
-- TOC entry 2447 (class 0 OID 100802)
-- Dependencies: 195
-- Data for Name: desglose_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.desglose_caja (id_desglose_caja, monto_desglose_caja, id_corte_caja, activo_desglose_caja, id_tipo_pago) VALUES (145, 201, 82, 1, 3);


--
-- TOC entry 2441 (class 0 OID 100750)
-- Dependencies: 189
-- Data for Name: empleado; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.empleado (id_empleado, clave, id_cargo, id_persona, activo_empleado) VALUES (98, '0', 1, 100, 1);


--
-- TOC entry 2443 (class 0 OID 100769)
-- Dependencies: 191
-- Data for Name: estado_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.estado_caja (id_estado_caja, fecha_apertura, monto_apertura, id_empleado, id_caja, activo_estado_caja, caja_abierta) VALUES (71, '2019-01-24 09:38:49', 15, 98, 1, 1, 1);


--
-- TOC entry 2479 (class 0 OID 201621)
-- Dependencies: 227
-- Data for Name: montos_cierre_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- TOC entry 2454 (class 0 OID 100842)
-- Dependencies: 202
-- Data for Name: pago; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago, corte_realizado) VALUES (179, 200, '2019-01-24 09:40:00', 2, 224, 1, 1);
INSERT INTO spve.pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago, corte_realizado) VALUES (180, 200, '2019-01-28 14:42:00', 1, 225, 1, 0);


--
-- TOC entry 2475 (class 0 OID 168483)
-- Dependencies: 223
-- Data for Name: pais; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (5, 'Costa Rica', 'America/Costa_Rica', 'Colón', 'CR', '-6:00', '¢', '', NULL, 'NITE');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (1, 'Argentina', 'America/Buenos_Aires', 'Peso Argentino', 'AR', '-3:00', '$', '', NULL, 'CUIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (16, 'República Dominicana', 'America/Santo_Domingo', 'Peso Dominicano', 'DO', '-4:00', '$', '', NULL, 'RNC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (11, 'Mexico', 'America/Mexico_City', 'Peso Mexicano', 'MX', '-6:00', '$', '', NULL, 'RFC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (10, 'Honduras', 'America/Tegucigalpa', 'Lempira', 'HN', '-6:00', 'L', '', NULL, 'RTN');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (2, 'Bolivia', 'America/La_Paz', 'Boliviano', 'BO', '-4:00', 'Bs', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (8, 'El Salvador', 'America/El_Salvador', 'Dólar estadounidense', 'SV', '-6:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (9, 'Guatemala', 'America/Guatemala', 'Quetzal', 'GT', '-6:00', 'Q', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (13, 'Panama', 'America/Panama', 'Dólar estadounidense', 'PA', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (17, 'Uruguay', 'America/Montevideo', 'Peso uruguayo', 'UY', '-3:00', '$', '', NULL, 'RUT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (12, 'Nicaragua', 'America/Managua', 'Córdoba', 'NI', '-6:00', 'C$', '', NULL, 'RUC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (14, 'Paraguay', 'America/Asuncion', 'Guaraní', 'PY', '-3:00', 'G', '', NULL, 'RUC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (15, 'Perú', 'America/Lima', 'Sol Peruano', 'PE', '-5:00', 'S/', '', NULL, 'RUC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (6, 'Cuba', 'America/Havana', 'Peso Cubano', 'CU', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (4, 'Colombia', 'America/Bogota', 'Peso Colombiano', 'CO', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (7, 'Ecuador', 'America/Guayaquil', 'Dólar estadounidense', 'EC', '-5:00', '$', '', NULL, 'RUC');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (18, 'Venezuela', 'America/Caracas', 'Bolivares Soberanos', 'VE', '-4:00', 'Bs. S', '', NULL, 'RIF');
INSERT INTO spve.pais (id_pais, nombre_pais, id_zona_horaria, moneda, nacionalidad, gmt_zona, simbolo, formato_id, activo, identificacion) VALUES (3, 'Chile', 'America/Santiago', 'Peso Chileno', 'CHL', '-3:00', '$', '', true, 'RUT');


--
-- TOC entry 2472 (class 0 OID 168469)
-- Dependencies: 220
-- Data for Name: parametros; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.parametros (nombre, valor) VALUES ('direccion', 'direccion	');
INSERT INTO spve.parametros (nombre, valor) VALUES ('moneda', '$ - Peso Chileno');
INSERT INTO spve.parametros (nombre, valor) VALUES ('logo', 'logo.png');
INSERT INTO spve.parametros (nombre, valor) VALUES ('identificacion', '123456738-2');
INSERT INTO spve.parametros (nombre, valor) VALUES ('nombre', 'inverdata');
INSERT INTO spve.parametros (nombre, valor) VALUES ('pais', 'Chile');
INSERT INTO spve.parametros (nombre, valor) VALUES ('telefono', '412163238');


--
-- TOC entry 2456 (class 0 OID 100861)
-- Dependencies: 204
-- Data for Name: periodo_venta_producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.periodo_venta_producto (id_periodo_venta_producto, nombre_periodo_venta, activo_periodo_venta) VALUES (1, 'Diario', 1);
INSERT INTO spve.periodo_venta_producto (id_periodo_venta_producto, nombre_periodo_venta, activo_periodo_venta) VALUES (2, 'Semanal', 1);


--
-- TOC entry 2439 (class 0 OID 100741)
-- Dependencies: 187
-- Data for Name: persona; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.persona (id_persona, nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, direccion_persona, email_persona, telefono_persona, activo_persona) VALUES (100, 'admin', 'admin', 'CHL', '0', '0', '0@correo.com', '0', 1);
INSERT INTO spve.persona (id_persona, nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, direccion_persona, email_persona, telefono_persona, activo_persona) VALUES (101, 'uno', 'uno', 'CHL', '1', '1', '1', '1', 1);


--
-- TOC entry 2471 (class 0 OID 100980)
-- Dependencies: 219
-- Data for Name: precio_producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, porcentaje_impuesto_producto, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, id_producto) VALUES (1, '2018-03-18', 4, 12, 12, 200, 200, 0, 1, 1);


--
-- TOC entry 2464 (class 0 OID 100919)
-- Dependencies: 212
-- Data for Name: produccion; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- TOC entry 2458 (class 0 OID 100869)
-- Dependencies: 206
-- Data for Name: producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, activo_producto, id_periodo_venta_producto) VALUES (1, 'Jugo de mamon', '4323', 3, 'de la mata', 946, 1, 1, 1, 1);


--
-- TOC entry 2469 (class 0 OID 100961)
-- Dependencies: 217
-- Data for Name: producto_componente; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.producto_componente (id_producto_componente, id_producto, cantidad_compuesto, activo_producto_componente) VALUES (1, 1, 2, 1);


--
-- TOC entry 2477 (class 0 OID 201612)
-- Dependencies: 225
-- Data for Name: tipo_monto_cierre; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.tipo_monto_cierre (id_tipo_monto_cierre, descripcion_monto, activo_tipo_monto) VALUES (1, 'sistema', 1);
INSERT INTO spve.tipo_monto_cierre (id_tipo_monto_cierre, descripcion_monto, activo_tipo_monto) VALUES (2, 'corte', 1);
INSERT INTO spve.tipo_monto_cierre (id_tipo_monto_cierre, descripcion_monto, activo_tipo_monto) VALUES (3, 'fisico', 1);


--
-- TOC entry 2452 (class 0 OID 100833)
-- Dependencies: 200
-- Data for Name: tipo_pago; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (1, 'Efectivo', 1);
INSERT INTO spve.tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (2, 'Débito', 1);
INSERT INTO spve.tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (3, 'Crédito', 1);
INSERT INTO spve.tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (4, 'CestaTicket', 1);


--
-- TOC entry 2450 (class 0 OID 100818)
-- Dependencies: 198
-- Data for Name: venta; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta, id_estado_caja, reporte_venta) VALUES (224, 224, '2019-01-24 09:39:25', 2, 100, 1, 71, '\x255044462d312e340a25e2e3cfd30a332030206f626a0a3c3c2f46696c7465722f466c6174654465636f64652f4c656e677468203635343e3e73747265616d0a789cad984d8fda301086eff9153eb4127b319eb19d38bdb12cb42075d5b2a1aad4ed2190946645c836cbb6fdf975f8d80fa91981262085089c793caf3def58fc0a2e93408702ad1349168c92e0738062da7c0b42f97773c5d888a40cfa6310a044f223e85d2477cdd8e7214a2ccb970f392bb516e840be78169b67d56e44bd0a7a37a3ebc920694229b1fa4fb86fdffd67b69b4e3b2892e0678f28e3f0c871af31d755b9a8f3774c8e1108d00e19e6d9e33aed00a2543be4aaa8f3e5b2a8366c8e0721914d9a95c546ecae5c92a57553ccf81668c9b8f16390caa760ad74d8822836bff33a4bb72917154b74024302359b73cb25f67a1982901db71893e34219fb6434813280106ad48e5d3410133b6098dee575c5ae184b533aac187f3144c5a0e92be8a382980902e324020d8ba59fcee023773368a9630161282dbe6e24cfa4f16098cc67036e4ecac808e99c3e547507460d866839e37cf9b30b86b79d7646badc3ef23301ab9b5521516aff423427c2fef841d34380d9fbe36f7eb4f7032bca00c11eeed7c1cdc96a2031c5abfc615917f71d3857a469d230dd6ca5f824e79b4272b5774a22d2b8a4daa66baeecbed73fc9bebb3f4776aa7d4f1f5795c87251a6255b7888a2a66790bca6d7735b7a147a0661e5c0753bd31c7d49c457b6cdb9bd5404a30ba98c307e4d6c9b679fb337dbfdc749bbe79099f053d1a47d2c8a6dd54d2a14a79354fc825072bde187a752e08637cd71a035fae86fbed9f24f6b2222baf365fa908b4979cfb56f2342c2aa265f06e2b607f8f6f682bf208e48a783f58e883c3a081f1236c50d7f283a4aa04e8a0ebc46ce914af1fd16426c7c9dd20bf0f45cdace03b17e3a0eec6ecf380d507fb40cd37251b06b17c2481a4d82baf1508a70daaefc072ebfdb910a656e6473747265616d0a656e646f626a0a312030206f626a0a3c3c2f47726f75703c3c2f532f5472616e73706172656e63792f547970652f47726f75702f43532f4465766963655247423e3e2f436f6e74656e74732033203020522f547970652f506167652f5265736f75726365733c3c2f436f6c6f7253706163653c3c2f43532f4465766963655247423e3e2f50726f63536574205b2f504446202f54657874202f496d61676542202f496d61676543202f496d616765495d2f466f6e743c3c2f46312032203020523e3e3e3e2f506172656e742034203020522f4d65646961426f785b30203020323135203239345d3e3e0a656e646f626a0a352030206f626a0a5b31203020522f58595a20302033303420305d0a656e646f626a0a322030206f626a0a3c3c2f537562747970652f54797065312f547970652f466f6e742f42617365466f6e742f48656c7665746963612f456e636f64696e672f57696e416e7369456e636f64696e673e3e0a656e646f626a0a342030206f626a0a3c3c2f4b6964735b31203020525d2f547970652f50616765732f436f756e7420312f4954585428322e312e37293e3e0a656e646f626a0a362030206f626a0a3c3c2f4e616d65735b284a525f504147455f414e43484f525f305f31292035203020525d3e3e0a656e646f626a0a372030206f626a0a3c3c2f44657374732036203020523e3e0a656e646f626a0a382030206f626a0a3c3c2f4e616d65732037203020522f547970652f436174616c6f672f50616765732034203020522f566965776572507265666572656e6365733c3c2f5072696e745363616c696e672f41707044656661756c743e3e3e3e0a656e646f626a0a392030206f626a0a3c3c2f4d6f644461746528443a32303139303132343039343030372d303427303027292f43726561746f72284a61737065725265706f727473205c287265706f7274206e616d655c29292f4372656174696f6e4461746528443a32303139303132343039343030372d303427303027292f50726f647563657228695465787420322e312e37206279203154335854293e3e0a656e646f626a0a787265660a302031300a303030303030303030302036353533352066200a30303030303030373336203030303030206e200a30303030303031303035203030303030206e200a30303030303030303135203030303030206e200a30303030303031303933203030303030206e200a30303030303030393730203030303030206e200a30303030303031313536203030303030206e200a30303030303031323130203030303030206e200a30303030303031323432203030303030206e200a30303030303031333435203030303030206e200a747261696c65720a3c3c2f496e666f2039203020522f4944205b3c32313534303637653539363364383635653662656263613737633465666538363e3c30613236633634373033643431626437303538363161663130316238373832383e5d2f526f6f742038203020522f53697a652031303e3e0a7374617274787265660a313530360a2525454f460a');
INSERT INTO spve.venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta, id_estado_caja, reporte_venta) VALUES (225, 225, '2019-01-28 14:42:40', 2, 100, 1, 71, '\x255044462d312e340a25e2e3cfd30a332030206f626a0a3c3c2f46696c7465722f466c6174654465636f64652f4c656e677468203635373e3e73747265616d0a789cad98df4fdb3010c7dff357f86193ca8beb3bdb89b3b752e806d21083749a34f61092c0829a848594edcf9f53ca2f69b9155d5a298d5afb3eb9af7ddfb3fa2bd84f021d0ab44e24797098045f0214c7fdb720947ff7578c8d48aa60ba00014a2457c1642fb9e9c73e0f5122ab5e4e72566a2dd0817c3117fbb96a33a2bd0e26e7872747b3a40fa5c4f53fc27dffe13ff3cde30c832209fee911651c3e72dc6bcc49535db6c50726c708041886cc8b7cbd4a478028350c3928db22cbcaa666733c08896cd2bc2a6bb1b9724996d64d31e35ba025e3c68f412a9f82b5d2e100a2acef8b364fbb948b8a253a8121813a5b72cb25f67a1982903f6e3126c78532f6c96802650021d4a81dbb68202676c03cbd29da865d3196a68c5831fe62888a413755304505311304c64984ffc0a44171fa99bb19b4d4b1803094165f379267d262364f9667336e4ecac808e99c3e35ed08460d8668398b22fb3906c3dbce3023cdba353f13b0ba5f1512a51e5e887647d86f3fe8781be0ece3e36f7eb4f7032baa00c16eef57c1f9ce6a20f18807c55dd696b7233857a469d23cad3b294ee5b22e25577ba724228d4b9a2e5d7165f7bdfe49f6cdfd5b64a7daf7f1faba117921aab4620b0f51d4f70c92d7f77a6e4b8f42cf20ac1cb86e67faa32f89f8c6b639f72015c118432a238c5f133be4d96fd99bc3fee3a47de09099f053d144951d5e155957de37e32443914649c62f0925d83b7e782a056e78d31f088697e24f5177fcf39a8888febc9fde15e2a8bae51ab811216156475f67e26202f8fe628fbf208e486784f58e883c46081f1246c50dbf2d3a4aa0518a0ebc46ce914af11d1742ec9d9dd20b70f75c864e04b17e3a106c6edf701ea0fe6a99a7d565c9ae5d08236934091ac74329c26ebbf22f9a66dc7e0a656e6473747265616d0a656e646f626a0a312030206f626a0a3c3c2f47726f75703c3c2f532f5472616e73706172656e63792f547970652f47726f75702f43532f4465766963655247423e3e2f436f6e74656e74732033203020522f547970652f506167652f5265736f75726365733c3c2f436f6c6f7253706163653c3c2f43532f4465766963655247423e3e2f50726f63536574205b2f504446202f54657874202f496d61676542202f496d61676543202f496d616765495d2f466f6e743c3c2f46312032203020523e3e3e3e2f506172656e742034203020522f4d65646961426f785b30203020323135203239345d3e3e0a656e646f626a0a352030206f626a0a5b31203020522f58595a20302033303420305d0a656e646f626a0a322030206f626a0a3c3c2f537562747970652f54797065312f547970652f466f6e742f42617365466f6e742f48656c7665746963612f456e636f64696e672f57696e416e7369456e636f64696e673e3e0a656e646f626a0a342030206f626a0a3c3c2f4b6964735b31203020525d2f547970652f50616765732f436f756e7420312f4954585428322e312e37293e3e0a656e646f626a0a362030206f626a0a3c3c2f4e616d65735b284a525f504147455f414e43484f525f305f31292035203020525d3e3e0a656e646f626a0a372030206f626a0a3c3c2f44657374732036203020523e3e0a656e646f626a0a382030206f626a0a3c3c2f4e616d65732037203020522f547970652f436174616c6f672f50616765732034203020522f566965776572507265666572656e6365733c3c2f5072696e745363616c696e672f41707044656661756c743e3e3e3e0a656e646f626a0a392030206f626a0a3c3c2f4d6f644461746528443a32303139303132383134343235362d303427303027292f43726561746f72284a61737065725265706f727473205c287265706f7274206e616d655c29292f4372656174696f6e4461746528443a32303139303132383134343235362d303427303027292f50726f647563657228695465787420322e312e37206279203154335854293e3e0a656e646f626a0a787265660a302031300a303030303030303030302036353533352066200a30303030303030373339203030303030206e200a30303030303031303038203030303030206e200a30303030303030303135203030303030206e200a30303030303031303936203030303030206e200a30303030303030393733203030303030206e200a30303030303031313539203030303030206e200a30303030303031323133203030303030206e200a30303030303031323435203030303030206e200a30303030303031333438203030303030206e200a747261696c65720a3c3c2f496e666f2039203020522f4944205b3c39613866336231393765306633326335666436616265626330313330346531663e3c37656461653864653364643062366635353161326130656337356561323931663e5d2f526f6f742038203020522f53697a652031303e3e0a7374617274787265660a313530390a2525454f460a');


--
-- TOC entry 2460 (class 0 OID 100886)
-- Dependencies: 208
-- Data for Name: venta_producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (301, 1, 224, 1, 1);
INSERT INTO spve.venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (302, 1, 225, 1, 1);


--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 213
-- Name: ajuste_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.ajuste_seq', 1, false);


--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 182
-- Name: caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.caja_seq', 1, false);


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 184
-- Name: cargo_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cargo_seq', 1, false);


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 228
-- Name: cierre_caja_id_cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cierre_caja_id_cierre_caja_seq', 5, true);


--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 215
-- Name: cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cierre_caja_seq', 30, true);


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 197
-- Name: codigo_factura_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.codigo_factura_seq', 225, true);


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 209
-- Name: compra_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.compra_seq', 1, false);


--
-- TOC entry 2498 (class 0 OID 0)
-- Dependencies: 192
-- Name: corte_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.corte_caja_seq', 82, true);


--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 194
-- Name: desglose_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.desglose_caja_seq', 145, true);


--
-- TOC entry 2500 (class 0 OID 0)
-- Dependencies: 188
-- Name: empleado_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.empleado_seq', 98, true);


--
-- TOC entry 2501 (class 0 OID 0)
-- Dependencies: 190
-- Name: estado_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.estado_caja_seq', 71, true);


--
-- TOC entry 2502 (class 0 OID 0)
-- Dependencies: 226
-- Name: montos_cierre_caja_id_montos_cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.montos_cierre_caja_id_montos_cierre_caja_seq', 384, true);


--
-- TOC entry 2503 (class 0 OID 0)
-- Dependencies: 201
-- Name: pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.pago_seq', 180, true);


--
-- TOC entry 2504 (class 0 OID 0)
-- Dependencies: 203
-- Name: periodo_venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.periodo_venta_producto_seq', 1, false);


--
-- TOC entry 2505 (class 0 OID 0)
-- Dependencies: 186
-- Name: persona_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.persona_seq', 101, true);


--
-- TOC entry 2506 (class 0 OID 0)
-- Dependencies: 218
-- Name: precio_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.precio_producto_seq', 1, false);


--
-- TOC entry 2507 (class 0 OID 0)
-- Dependencies: 211
-- Name: produccion_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.produccion_seq', 1, false);


--
-- TOC entry 2508 (class 0 OID 0)
-- Dependencies: 216
-- Name: producto_componente_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.producto_componente_seq', 1, false);


--
-- TOC entry 2509 (class 0 OID 0)
-- Dependencies: 205
-- Name: producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.producto_seq', 3, true);


--
-- TOC entry 2510 (class 0 OID 0)
-- Dependencies: 224
-- Name: tipo_monto_cierre_id_tipo_monto_cierre_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.tipo_monto_cierre_id_tipo_monto_cierre_seq', 1, false);


--
-- TOC entry 2511 (class 0 OID 0)
-- Dependencies: 199
-- Name: tipo_pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.tipo_pago_seq', 1, false);


--
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 207
-- Name: venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.venta_producto_seq', 302, true);


--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 196
-- Name: venta_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.venta_seq', 225, true);


--
-- TOC entry 2281 (class 2606 OID 100939)
-- Name: ajuste ajuste_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.ajuste
    ADD CONSTRAINT ajuste_pkey PRIMARY KEY (id_ajuste);


--
-- TOC entry 2251 (class 2606 OID 100729)
-- Name: caja caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id_caja);


--
-- TOC entry 2253 (class 2606 OID 100738)
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo);


--
-- TOC entry 2293 (class 2606 OID 218149)
-- Name: cierre_caja cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT cierre_caja_pkey PRIMARY KEY (id_cierre_caja);


--
-- TOC entry 2277 (class 2606 OID 100911)
-- Name: compra compra_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (id_compra);


--
-- TOC entry 2261 (class 2606 OID 100794)
-- Name: corte_caja corte_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT corte_caja_pkey PRIMARY KEY (id_corte_caja);


--
-- TOC entry 2263 (class 2606 OID 100808)
-- Name: desglose_caja desglose_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT desglose_caja_pkey PRIMARY KEY (id_desglose_caja);


--
-- TOC entry 2257 (class 2606 OID 100756)
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


--
-- TOC entry 2259 (class 2606 OID 100775)
-- Name: estado_caja estado_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT estado_caja_pkey PRIMARY KEY (id_estado_caja);


--
-- TOC entry 2291 (class 2606 OID 201627)
-- Name: montos_cierre_caja montos_cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT montos_cierre_caja_pkey PRIMARY KEY (id_montos_cierre_caja);


--
-- TOC entry 2269 (class 2606 OID 100848)
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 2287 (class 2606 OID 184864)
-- Name: parametros parametros_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.parametros
    ADD CONSTRAINT parametros_pkey PRIMARY KEY (nombre);


--
-- TOC entry 2271 (class 2606 OID 100866)
-- Name: periodo_venta_producto periodo_venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.periodo_venta_producto
    ADD CONSTRAINT periodo_venta_producto_pkey PRIMARY KEY (id_periodo_venta_producto);


--
-- TOC entry 2255 (class 2606 OID 100747)
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2285 (class 2606 OID 100987)
-- Name: precio_producto precio_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.precio_producto
    ADD CONSTRAINT precio_producto_pkey PRIMARY KEY (id_precio_producto);


--
-- TOC entry 2279 (class 2606 OID 100925)
-- Name: produccion produccion_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.produccion
    ADD CONSTRAINT produccion_pkey PRIMARY KEY (id_produccion);


--
-- TOC entry 2283 (class 2606 OID 100967)
-- Name: producto_componente producto_componente_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto_componente
    ADD CONSTRAINT producto_componente_pkey PRIMARY KEY (id_producto_componente, id_producto);


--
-- TOC entry 2273 (class 2606 OID 100878)
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 2289 (class 2606 OID 201618)
-- Name: tipo_monto_cierre tipo_monto_cierre_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_monto_cierre
    ADD CONSTRAINT tipo_monto_cierre_pkey PRIMARY KEY (id_tipo_monto_cierre);


--
-- TOC entry 2267 (class 2606 OID 100839)
-- Name: tipo_pago tipo_pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_pago
    ADD CONSTRAINT tipo_pago_pkey PRIMARY KEY (id_tipo_pago);


--
-- TOC entry 2265 (class 2606 OID 100825)
-- Name: venta venta_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (id_venta);


--
-- TOC entry 2275 (class 2606 OID 100892)
-- Name: venta_producto venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT venta_producto_pkey PRIMARY KEY (id_venta_producto);


--
-- TOC entry 2317 (class 2606 OID 218150)
-- Name: montos_cierre_caja cierre_caja_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT cierre_caja_fkey FOREIGN KEY (id_cierre_caja) REFERENCES spve.cierre_caja(id_cierre_caja);


--
-- TOC entry 2319 (class 2606 OID 218163)
-- Name: cierre_caja cierre_caja_id_estado_caja_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT cierre_caja_id_estado_caja_fkey FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- TOC entry 2318 (class 2606 OID 218158)
-- Name: cierre_caja empleado_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT empleado_fkey FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- TOC entry 2311 (class 2606 OID 100940)
-- Name: ajuste fk_ajuste_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.ajuste
    ADD CONSTRAINT fk_ajuste_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2309 (class 2606 OID 100912)
-- Name: compra fk_compra_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.compra
    ADD CONSTRAINT fk_compra_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2298 (class 2606 OID 100795)
-- Name: corte_caja fk_corte_caja_estado_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT fk_corte_caja_estado_caja1 FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- TOC entry 2300 (class 2606 OID 100809)
-- Name: desglose_caja fk_desglose_caja_corte_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT fk_desglose_caja_corte_caja1 FOREIGN KEY (id_corte_caja) REFERENCES spve.corte_caja(id_corte_caja);


--
-- TOC entry 2294 (class 2606 OID 100757)
-- Name: empleado fk_empleado_cargo1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_cargo1 FOREIGN KEY (id_cargo) REFERENCES spve.cargo(id_cargo);


--
-- TOC entry 2295 (class 2606 OID 100762)
-- Name: empleado fk_empleado_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- TOC entry 2303 (class 2606 OID 184865)
-- Name: venta fk_estado_caja; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT fk_estado_caja FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- TOC entry 2297 (class 2606 OID 100781)
-- Name: estado_caja fk_estado_caja_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_caja1 FOREIGN KEY (id_caja) REFERENCES spve.caja(id_caja);


--
-- TOC entry 2296 (class 2606 OID 100776)
-- Name: estado_caja fk_estado_caja_empleado1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_empleado1 FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- TOC entry 2304 (class 2606 OID 100849)
-- Name: pago fk_pago_tipo_pago1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_tipo_pago1 FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- TOC entry 2305 (class 2606 OID 100854)
-- Name: pago fk_pago_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- TOC entry 2314 (class 2606 OID 100988)
-- Name: precio_producto fk_precio_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.precio_producto
    ADD CONSTRAINT fk_precio_producto_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2310 (class 2606 OID 100926)
-- Name: produccion fk_produccion_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.produccion
    ADD CONSTRAINT fk_produccion_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2312 (class 2606 OID 100968)
-- Name: producto_componente fk_producto_has_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto_componente
    ADD CONSTRAINT fk_producto_has_producto_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2313 (class 2606 OID 100973)
-- Name: producto_componente fk_producto_has_producto_producto2; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto_componente
    ADD CONSTRAINT fk_producto_has_producto_producto2 FOREIGN KEY (id_producto_componente) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2306 (class 2606 OID 100879)
-- Name: producto fk_producto_periodo_venta_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT fk_producto_periodo_venta_producto1 FOREIGN KEY (id_periodo_venta_producto) REFERENCES spve.periodo_venta_producto(id_periodo_venta_producto);


--
-- TOC entry 2302 (class 2606 OID 100826)
-- Name: venta fk_venta_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT fk_venta_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- TOC entry 2308 (class 2606 OID 100898)
-- Name: venta_producto fk_venta_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_venta_producto_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- TOC entry 2307 (class 2606 OID 100893)
-- Name: venta_producto fk_venta_producto_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_venta_producto_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- TOC entry 2299 (class 2606 OID 193348)
-- Name: corte_caja kf_id_empleado; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT kf_id_empleado FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- TOC entry 2316 (class 2606 OID 201633)
-- Name: montos_cierre_caja tipo_monto_cierre_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT tipo_monto_cierre_fkey FOREIGN KEY (id_tipo_monto_cierre) REFERENCES spve.tipo_monto_cierre(id_tipo_monto_cierre);


--
-- TOC entry 2301 (class 2606 OID 193399)
-- Name: desglose_caja tipo_pago_fk; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT tipo_pago_fk FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- TOC entry 2315 (class 2606 OID 201628)
-- Name: montos_cierre_caja tipo_pago_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT tipo_pago_fkey FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: inverdata
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM inverdata;
GRANT ALL ON SCHEMA public TO inverdata;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2019-01-30 10:40:43 -04

--
-- inverdataQL database dump complete
--

