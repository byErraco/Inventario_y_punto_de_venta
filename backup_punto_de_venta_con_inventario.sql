--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.7
-- Dumped by pg_dump version 11.0

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
-- Name: inventario; Type: SCHEMA; Schema: -; Owner: inverdata
--

CREATE SCHEMA inventario;


ALTER SCHEMA inventario OWNER TO inverdata;

--
-- Name: spve; Type: SCHEMA; Schema: -; Owner: inverdata
--

CREATE SCHEMA spve;


ALTER SCHEMA spve OWNER TO inverdata;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: almacen; Type: TABLE; Schema: inventario; Owner: inverdata
--

CREATE TABLE inventario.almacen (
    id integer NOT NULL,
    nombre character varying(255) NOT NULL,
    direccion character varying(255)
);


ALTER TABLE inventario.almacen OWNER TO inverdata;

--
-- Name: almacen_id_seq; Type: SEQUENCE; Schema: inventario; Owner: inverdata
--

CREATE SEQUENCE inventario.almacen_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventario.almacen_id_seq OWNER TO inverdata;

--
-- Name: almacen_id_seq; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: inverdata
--

ALTER SEQUENCE inventario.almacen_id_seq OWNED BY inventario.almacen.id;


--
-- Name: lote_produccion; Type: TABLE; Schema: inventario; Owner: inverdata
--

CREATE TABLE inventario.lote_produccion (
    id integer NOT NULL,
    codigo character varying(255) NOT NULL,
    producto_id integer NOT NULL
);


ALTER TABLE inventario.lote_produccion OWNER TO inverdata;

--
-- Name: lote_produccion_id_seq; Type: SEQUENCE; Schema: inventario; Owner: inverdata
--

CREATE SEQUENCE inventario.lote_produccion_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventario.lote_produccion_id_seq OWNER TO inverdata;

--
-- Name: lote_produccion_id_seq; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: inverdata
--

ALTER SEQUENCE inventario.lote_produccion_id_seq OWNED BY inventario.lote_produccion.id;


--
-- Name: precio_unidad_inventario; Type: TABLE; Schema: inventario; Owner: inverdata
--

CREATE TABLE inventario.precio_unidad_inventario (
    id integer NOT NULL,
    base_imponible numeric(10,4),
    margen_ganancia numeric(10,4),
    porcentaje_impuesto numeric(10,4),
    impuesto numeric(10,4),
    precio_venta_publico numeric(10,4) NOT NULL,
    exento boolean NOT NULL,
    unidad_inventario_id integer NOT NULL,
    activo smallint DEFAULT 1 NOT NULL
);


ALTER TABLE inventario.precio_unidad_inventario OWNER TO inverdata;

--
-- Name: precio_unidad_inventario_id_seq; Type: SEQUENCE; Schema: inventario; Owner: inverdata
--

CREATE SEQUENCE inventario.precio_unidad_inventario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventario.precio_unidad_inventario_id_seq OWNER TO inverdata;

--
-- Name: precio_unidad_inventario_id_seq; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: inverdata
--

ALTER SEQUENCE inventario.precio_unidad_inventario_id_seq OWNED BY inventario.precio_unidad_inventario.id;


--
-- Name: unidad; Type: TABLE; Schema: inventario; Owner: inverdata
--

CREATE TABLE inventario.unidad (
    id integer NOT NULL,
    nombre character varying(255) NOT NULL,
    descripcion character varying(255) NOT NULL
);


ALTER TABLE inventario.unidad OWNER TO inverdata;

--
-- Name: unidad_id_seq; Type: SEQUENCE; Schema: inventario; Owner: inverdata
--

CREATE SEQUENCE inventario.unidad_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventario.unidad_id_seq OWNER TO inverdata;

--
-- Name: unidad_id_seq; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: inverdata
--

ALTER SEQUENCE inventario.unidad_id_seq OWNED BY inventario.unidad.id;


--
-- Name: unidad_inventario; Type: TABLE; Schema: inventario; Owner: inverdata
--

CREATE TABLE inventario.unidad_inventario (
    id integer NOT NULL,
    cantidad_producto numeric(10,4) NOT NULL,
    almacen_id integer NOT NULL,
    lote_produccion_id integer,
    producto_id integer NOT NULL,
    activo smallint
);


ALTER TABLE inventario.unidad_inventario OWNER TO inverdata;

--
-- Name: unidad_inventario_id_seq; Type: SEQUENCE; Schema: inventario; Owner: inverdata
--

CREATE SEQUENCE inventario.unidad_inventario_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE inventario.unidad_inventario_id_seq OWNER TO inverdata;

--
-- Name: unidad_inventario_id_seq; Type: SEQUENCE OWNED BY; Schema: inventario; Owner: inverdata
--

ALTER SEQUENCE inventario.unidad_inventario_id_seq OWNED BY inventario.unidad_inventario.id;


--
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
-- Name: caja; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.caja (
    id_caja integer DEFAULT nextval('spve.caja_seq'::regclass) NOT NULL,
    descripcion_caja character varying(45) NOT NULL,
    activo_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.caja OWNER TO inverdata;

--
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
-- Name: cargo; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.cargo (
    id_cargo integer DEFAULT nextval('spve.cargo_seq'::regclass) NOT NULL,
    nombre_cargo character varying(45) NOT NULL,
    activo_cargo smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.cargo OWNER TO inverdata;

--
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
-- Name: cierre_caja_id_cierre_caja_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.cierre_caja_id_cierre_caja_seq OWNED BY spve.cierre_caja.id_cierre_caja;


--
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
-- Name: montos_cierre_caja_id_montos_cierre_caja_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.montos_cierre_caja_id_montos_cierre_caja_seq OWNED BY spve.montos_cierre_caja.id_montos_cierre_caja;


--
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
-- Name: parametros; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.parametros (
    nombre character varying NOT NULL,
    valor character varying NOT NULL
);


ALTER TABLE spve.parametros OWNER TO inverdata;

--
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
-- Name: periodo_venta_producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.periodo_venta_producto (
    id_periodo_venta_producto integer DEFAULT nextval('spve.periodo_venta_producto_seq'::regclass) NOT NULL,
    nombre_periodo_venta character varying(45) NOT NULL,
    activo_periodo_venta smallint NOT NULL
);


ALTER TABLE spve.periodo_venta_producto OWNER TO inverdata;

--
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
-- Name: producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.producto (
    id_producto integer DEFAULT nextval('spve.producto_seq'::regclass) NOT NULL,
    descripcion_producto character varying(45) NOT NULL,
    codigo_venta_producto character varying(45) NOT NULL,
    limite_venta_persona numeric(10,0),
    balanza smallint DEFAULT 0 NOT NULL,
    producto_pre_fabricado smallint DEFAULT 0 NOT NULL,
    activo_producto smallint DEFAULT 1 NOT NULL,
    id_periodo_venta_producto integer,
    seguimiento smallint,
    id_unidad integer
);


ALTER TABLE spve.producto OWNER TO inverdata;

--
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
-- Name: tipo_monto_cierre; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.tipo_monto_cierre (
    id_tipo_monto_cierre integer NOT NULL,
    descripcion_monto character varying(50) NOT NULL,
    activo_tipo_monto smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.tipo_monto_cierre OWNER TO inverdata;

--
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
-- Name: tipo_monto_cierre_id_tipo_monto_cierre_seq; Type: SEQUENCE OWNED BY; Schema: spve; Owner: inverdata
--

ALTER SEQUENCE spve.tipo_monto_cierre_id_tipo_monto_cierre_seq OWNED BY spve.tipo_monto_cierre.id_tipo_monto_cierre;


--
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
-- Name: tipo_pago; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.tipo_pago (
    id_tipo_pago integer DEFAULT nextval('spve.tipo_pago_seq'::regclass) NOT NULL,
    descripcion_pago character varying(45) NOT NULL,
    activo_tipo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.tipo_pago OWNER TO inverdata;

--
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
    reporte_venta bytea,
    fecha_ultima_sincronizacion timestamp without time zone,
    fecha_ultima_modificacion timestamp without time zone NOT NULL
);


ALTER TABLE spve.venta OWNER TO inverdata;

--
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
-- Name: venta_producto; Type: TABLE; Schema: spve; Owner: inverdata
--

CREATE TABLE spve.venta_producto (
    id_venta_producto integer DEFAULT nextval('spve.venta_producto_seq'::regclass) NOT NULL,
    cantidad_producto real NOT NULL,
    id_venta integer NOT NULL,
    activo_venta_producto smallint DEFAULT 1 NOT NULL,
    id_unidad_inventario integer NOT NULL,
    fecha_ultima_modificacion timestamp without time zone NOT NULL
);


ALTER TABLE spve.venta_producto OWNER TO inverdata;

--
-- Name: almacen id; Type: DEFAULT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.almacen ALTER COLUMN id SET DEFAULT nextval('inventario.almacen_id_seq'::regclass);


--
-- Name: lote_produccion id; Type: DEFAULT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.lote_produccion ALTER COLUMN id SET DEFAULT nextval('inventario.lote_produccion_id_seq'::regclass);


--
-- Name: precio_unidad_inventario id; Type: DEFAULT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.precio_unidad_inventario ALTER COLUMN id SET DEFAULT nextval('inventario.precio_unidad_inventario_id_seq'::regclass);


--
-- Name: unidad id; Type: DEFAULT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad ALTER COLUMN id SET DEFAULT nextval('inventario.unidad_id_seq'::regclass);


--
-- Name: unidad_inventario id; Type: DEFAULT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad_inventario ALTER COLUMN id SET DEFAULT nextval('inventario.unidad_inventario_id_seq'::regclass);


--
-- Name: cierre_caja id_cierre_caja; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja ALTER COLUMN id_cierre_caja SET DEFAULT nextval('spve.cierre_caja_id_cierre_caja_seq'::regclass);


--
-- Name: montos_cierre_caja id_montos_cierre_caja; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja ALTER COLUMN id_montos_cierre_caja SET DEFAULT nextval('spve.montos_cierre_caja_id_montos_cierre_caja_seq'::regclass);


--
-- Name: tipo_monto_cierre id_tipo_monto_cierre; Type: DEFAULT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_monto_cierre ALTER COLUMN id_tipo_monto_cierre SET DEFAULT nextval('spve.tipo_monto_cierre_id_tipo_monto_cierre_seq'::regclass);


--
-- Data for Name: almacen; Type: TABLE DATA; Schema: inventario; Owner: inverdata
--



--
-- Data for Name: lote_produccion; Type: TABLE DATA; Schema: inventario; Owner: inverdata
--



--
-- Data for Name: precio_unidad_inventario; Type: TABLE DATA; Schema: inventario; Owner: inverdata
--



--
-- Data for Name: unidad; Type: TABLE DATA; Schema: inventario; Owner: inverdata
--



--
-- Data for Name: unidad_inventario; Type: TABLE DATA; Schema: inventario; Owner: inverdata
--



--
-- Data for Name: jpais; Type: TABLE DATA; Schema: public; Owner: inverdata
--



--
-- Data for Name: pais; Type: TABLE DATA; Schema: public; Owner: inverdata
--



--
-- Data for Name: caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.caja VALUES (1, 'Caja 1', 1);


--
-- Data for Name: cargo; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.cargo VALUES (1, 'Administrador', 1);
INSERT INTO spve.cargo VALUES (2, 'Gerente', 1);
INSERT INTO spve.cargo VALUES (3, 'Cajero', 1);


--
-- Data for Name: cierre_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: corte_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: desglose_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: empleado; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.empleado VALUES (98, '0', 1, 100, 1);


--
-- Data for Name: estado_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: montos_cierre_caja; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: pago; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: pais; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.pais VALUES (5, 'Costa Rica', 'America/Costa_Rica', 'Colón', 'CR', '-6:00', '¢', '', NULL, 'NITE');
INSERT INTO spve.pais VALUES (1, 'Argentina', 'America/Buenos_Aires', 'Peso Argentino', 'AR', '-3:00', '$', '', NULL, 'CUIT');
INSERT INTO spve.pais VALUES (16, 'República Dominicana', 'America/Santo_Domingo', 'Peso Dominicano', 'DO', '-4:00', '$', '', NULL, 'RNC');
INSERT INTO spve.pais VALUES (11, 'Mexico', 'America/Mexico_City', 'Peso Mexicano', 'MX', '-6:00', '$', '', NULL, 'RFC');
INSERT INTO spve.pais VALUES (10, 'Honduras', 'America/Tegucigalpa', 'Lempira', 'HN', '-6:00', 'L', '', NULL, 'RTN');
INSERT INTO spve.pais VALUES (2, 'Bolivia', 'America/La_Paz', 'Boliviano', 'BO', '-4:00', 'Bs', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (8, 'El Salvador', 'America/El_Salvador', 'Dólar estadounidense', 'SV', '-6:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (9, 'Guatemala', 'America/Guatemala', 'Quetzal', 'GT', '-6:00', 'Q', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (13, 'Panama', 'America/Panama', 'Dólar estadounidense', 'PA', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (17, 'Uruguay', 'America/Montevideo', 'Peso uruguayo', 'UY', '-3:00', '$', '', NULL, 'RUT');
INSERT INTO spve.pais VALUES (12, 'Nicaragua', 'America/Managua', 'Córdoba', 'NI', '-6:00', 'C$', '', NULL, 'RUC');
INSERT INTO spve.pais VALUES (14, 'Paraguay', 'America/Asuncion', 'Guaraní', 'PY', '-3:00', 'G', '', NULL, 'RUC');
INSERT INTO spve.pais VALUES (15, 'Perú', 'America/Lima', 'Sol Peruano', 'PE', '-5:00', 'S/', '', NULL, 'RUC');
INSERT INTO spve.pais VALUES (6, 'Cuba', 'America/Havana', 'Peso Cubano', 'CU', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (4, 'Colombia', 'America/Bogota', 'Peso Colombiano', 'CO', '-5:00', '$', '', NULL, 'NIT');
INSERT INTO spve.pais VALUES (7, 'Ecuador', 'America/Guayaquil', 'Dólar estadounidense', 'EC', '-5:00', '$', '', NULL, 'RUC');
INSERT INTO spve.pais VALUES (18, 'Venezuela', 'America/Caracas', 'Bolivares Soberanos', 'VE', '-4:00', 'Bs. S', '', NULL, 'RIF');
INSERT INTO spve.pais VALUES (3, 'Chile', 'America/Santiago', 'Peso Chileno', 'CHL', '-3:00', '$', '', true, 'RUT');


--
-- Data for Name: parametros; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.parametros VALUES ('direccion', 'direccion	');
INSERT INTO spve.parametros VALUES ('moneda', '$ - Peso Chileno');
INSERT INTO spve.parametros VALUES ('logo', 'logo.png');
INSERT INTO spve.parametros VALUES ('identificacion', '123456738-2');
INSERT INTO spve.parametros VALUES ('nombre', 'inverdata');
INSERT INTO spve.parametros VALUES ('pais', 'Chile');
INSERT INTO spve.parametros VALUES ('telefono', '412163238');


--
-- Data for Name: periodo_venta_producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.periodo_venta_producto VALUES (1, 'Diario', 1);
INSERT INTO spve.periodo_venta_producto VALUES (2, 'Semanal', 1);


--
-- Data for Name: persona; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.persona VALUES (100, 'admin', 'admin', 'CHL', '0', '0', '0@correo.com', '0', 1);
INSERT INTO spve.persona VALUES (101, 'uno', 'uno', 'CHL', '1', '1', '1', '1', 1);


--
-- Data for Name: producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: tipo_monto_cierre; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.tipo_monto_cierre VALUES (1, 'sistema', 1);
INSERT INTO spve.tipo_monto_cierre VALUES (2, 'corte', 1);
INSERT INTO spve.tipo_monto_cierre VALUES (3, 'fisico', 1);


--
-- Data for Name: tipo_pago; Type: TABLE DATA; Schema: spve; Owner: inverdata
--

INSERT INTO spve.tipo_pago VALUES (1, 'Efectivo', 1);
INSERT INTO spve.tipo_pago VALUES (2, 'Débito', 1);
INSERT INTO spve.tipo_pago VALUES (3, 'Crédito', 1);
INSERT INTO spve.tipo_pago VALUES (4, 'CestaTicket', 1);


--
-- Data for Name: venta; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Data for Name: venta_producto; Type: TABLE DATA; Schema: spve; Owner: inverdata
--



--
-- Name: almacen_id_seq; Type: SEQUENCE SET; Schema: inventario; Owner: inverdata
--

SELECT pg_catalog.setval('inventario.almacen_id_seq', 1, false);


--
-- Name: lote_produccion_id_seq; Type: SEQUENCE SET; Schema: inventario; Owner: inverdata
--

SELECT pg_catalog.setval('inventario.lote_produccion_id_seq', 1, false);


--
-- Name: precio_unidad_inventario_id_seq; Type: SEQUENCE SET; Schema: inventario; Owner: inverdata
--

SELECT pg_catalog.setval('inventario.precio_unidad_inventario_id_seq', 1, false);


--
-- Name: unidad_id_seq; Type: SEQUENCE SET; Schema: inventario; Owner: inverdata
--

SELECT pg_catalog.setval('inventario.unidad_id_seq', 1, false);


--
-- Name: unidad_inventario_id_seq; Type: SEQUENCE SET; Schema: inventario; Owner: inverdata
--

SELECT pg_catalog.setval('inventario.unidad_inventario_id_seq', 1, false);


--
-- Name: ajuste_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.ajuste_seq', 1, false);


--
-- Name: caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.caja_seq', 1, false);


--
-- Name: cargo_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cargo_seq', 1, false);


--
-- Name: cierre_caja_id_cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cierre_caja_id_cierre_caja_seq', 5, true);


--
-- Name: cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.cierre_caja_seq', 30, true);


--
-- Name: codigo_factura_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.codigo_factura_seq', 226, true);


--
-- Name: compra_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.compra_seq', 1, false);


--
-- Name: corte_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.corte_caja_seq', 82, true);


--
-- Name: desglose_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.desglose_caja_seq', 145, true);


--
-- Name: empleado_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.empleado_seq', 98, true);


--
-- Name: estado_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.estado_caja_seq', 71, true);


--
-- Name: montos_cierre_caja_id_montos_cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.montos_cierre_caja_id_montos_cierre_caja_seq', 384, true);


--
-- Name: pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.pago_seq', 180, true);


--
-- Name: periodo_venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.periodo_venta_producto_seq', 1, false);


--
-- Name: persona_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.persona_seq', 101, true);


--
-- Name: precio_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.precio_producto_seq', 1, false);


--
-- Name: produccion_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.produccion_seq', 1, false);


--
-- Name: producto_componente_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.producto_componente_seq', 1, false);


--
-- Name: producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.producto_seq', 3, true);


--
-- Name: tipo_monto_cierre_id_tipo_monto_cierre_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.tipo_monto_cierre_id_tipo_monto_cierre_seq', 1, false);


--
-- Name: tipo_pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.tipo_pago_seq', 1, false);


--
-- Name: venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.venta_producto_seq', 302, true);


--
-- Name: venta_seq; Type: SEQUENCE SET; Schema: spve; Owner: inverdata
--

SELECT pg_catalog.setval('spve.venta_seq', 226, true);


--
-- Name: almacen almacen_pkey; Type: CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.almacen
    ADD CONSTRAINT almacen_pkey PRIMARY KEY (id);


--
-- Name: lote_produccion lote_produccion_pkey; Type: CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.lote_produccion
    ADD CONSTRAINT lote_produccion_pkey PRIMARY KEY (id);


--
-- Name: precio_unidad_inventario precio_unidad_inventario_pkey; Type: CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.precio_unidad_inventario
    ADD CONSTRAINT precio_unidad_inventario_pkey PRIMARY KEY (id);


--
-- Name: unidad_inventario unidad_inventario_pkey; Type: CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad_inventario
    ADD CONSTRAINT unidad_inventario_pkey PRIMARY KEY (id);


--
-- Name: unidad unidad_pkey; Type: CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad
    ADD CONSTRAINT unidad_pkey PRIMARY KEY (id);


--
-- Name: caja caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id_caja);


--
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo);


--
-- Name: cierre_caja cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT cierre_caja_pkey PRIMARY KEY (id_cierre_caja);


--
-- Name: corte_caja corte_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT corte_caja_pkey PRIMARY KEY (id_corte_caja);


--
-- Name: desglose_caja desglose_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT desglose_caja_pkey PRIMARY KEY (id_desglose_caja);


--
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


--
-- Name: estado_caja estado_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT estado_caja_pkey PRIMARY KEY (id_estado_caja);


--
-- Name: montos_cierre_caja montos_cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT montos_cierre_caja_pkey PRIMARY KEY (id_montos_cierre_caja);


--
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- Name: parametros parametros_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.parametros
    ADD CONSTRAINT parametros_pkey PRIMARY KEY (nombre);


--
-- Name: periodo_venta_producto periodo_venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.periodo_venta_producto
    ADD CONSTRAINT periodo_venta_producto_pkey PRIMARY KEY (id_periodo_venta_producto);


--
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- Name: tipo_monto_cierre tipo_monto_cierre_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_monto_cierre
    ADD CONSTRAINT tipo_monto_cierre_pkey PRIMARY KEY (id_tipo_monto_cierre);


--
-- Name: tipo_pago tipo_pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.tipo_pago
    ADD CONSTRAINT tipo_pago_pkey PRIMARY KEY (id_tipo_pago);


--
-- Name: venta venta_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (id_venta);


--
-- Name: venta_producto venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT venta_producto_pkey PRIMARY KEY (id_venta_producto);


--
-- Name: venta_producto venta_unidad_inventario; Type: CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT venta_unidad_inventario UNIQUE (id_venta, id_unidad_inventario, activo_venta_producto);


--
-- Name: lote_produccion_producto_id_f9c121d9; Type: INDEX; Schema: inventario; Owner: inverdata
--

CREATE INDEX lote_produccion_producto_id_f9c121d9 ON inventario.lote_produccion USING btree (producto_id);


--
-- Name: precio_unidad_inventario_unidad_inventario_id_71495687; Type: INDEX; Schema: inventario; Owner: inverdata
--

CREATE INDEX precio_unidad_inventario_unidad_inventario_id_71495687 ON inventario.precio_unidad_inventario USING btree (unidad_inventario_id);


--
-- Name: unidad_inventario_almacen_id_45607727; Type: INDEX; Schema: inventario; Owner: inverdata
--

CREATE INDEX unidad_inventario_almacen_id_45607727 ON inventario.unidad_inventario USING btree (almacen_id);


--
-- Name: unidad_inventario_lote_produccion_id_cfa9708c; Type: INDEX; Schema: inventario; Owner: inverdata
--

CREATE INDEX unidad_inventario_lote_produccion_id_cfa9708c ON inventario.unidad_inventario USING btree (lote_produccion_id);


--
-- Name: unidad_inventario_producto_id_3126d5dc; Type: INDEX; Schema: inventario; Owner: inverdata
--

CREATE INDEX unidad_inventario_producto_id_3126d5dc ON inventario.unidad_inventario USING btree (producto_id);


--
-- Name: fki_unidad_id; Type: INDEX; Schema: spve; Owner: inverdata
--

CREATE INDEX fki_unidad_id ON spve.producto USING btree (id_unidad);


--
-- Name: fki_unidad_inventario; Type: INDEX; Schema: spve; Owner: inverdata
--

CREATE INDEX fki_unidad_inventario ON spve.venta_producto USING btree (id_unidad_inventario);


--
-- Name: lote_produccion lote_produccion_producto_id_f9c121d9_fk_producto_id; Type: FK CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.lote_produccion
    ADD CONSTRAINT lote_produccion_producto_id_f9c121d9_fk_producto_id FOREIGN KEY (producto_id) REFERENCES spve.producto(id_producto) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: precio_unidad_inventario precio_unidad_invent_unidad_inventario_id_71495687_fk_unidad_in; Type: FK CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.precio_unidad_inventario
    ADD CONSTRAINT precio_unidad_invent_unidad_inventario_id_71495687_fk_unidad_in FOREIGN KEY (unidad_inventario_id) REFERENCES inventario.unidad_inventario(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: unidad_inventario unidad_inventario_almacen_id_45607727_fk_almacen_id; Type: FK CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad_inventario
    ADD CONSTRAINT unidad_inventario_almacen_id_45607727_fk_almacen_id FOREIGN KEY (almacen_id) REFERENCES inventario.almacen(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: unidad_inventario unidad_inventario_lote_produccion_id_cfa9708c_fk_lote_prod; Type: FK CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad_inventario
    ADD CONSTRAINT unidad_inventario_lote_produccion_id_cfa9708c_fk_lote_prod FOREIGN KEY (lote_produccion_id) REFERENCES inventario.lote_produccion(id) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: unidad_inventario unidad_inventario_producto_id_3126d5dc_fk_producto_id; Type: FK CONSTRAINT; Schema: inventario; Owner: inverdata
--

ALTER TABLE ONLY inventario.unidad_inventario
    ADD CONSTRAINT unidad_inventario_producto_id_3126d5dc_fk_producto_id FOREIGN KEY (producto_id) REFERENCES spve.producto(id_producto) DEFERRABLE INITIALLY DEFERRED;


--
-- Name: montos_cierre_caja cierre_caja_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT cierre_caja_fkey FOREIGN KEY (id_cierre_caja) REFERENCES spve.cierre_caja(id_cierre_caja);


--
-- Name: cierre_caja cierre_caja_id_estado_caja_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT cierre_caja_id_estado_caja_fkey FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- Name: cierre_caja empleado_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT empleado_fkey FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- Name: corte_caja fk_corte_caja_estado_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT fk_corte_caja_estado_caja1 FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- Name: empleado fk_empleado_cargo1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_cargo1 FOREIGN KEY (id_cargo) REFERENCES spve.cargo(id_cargo);


--
-- Name: empleado fk_empleado_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- Name: venta fk_estado_caja; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT fk_estado_caja FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- Name: estado_caja fk_estado_caja_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_caja1 FOREIGN KEY (id_caja) REFERENCES spve.caja(id_caja);


--
-- Name: estado_caja fk_estado_caja_empleado1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_empleado1 FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- Name: pago fk_pago_tipo_pago1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_tipo_pago1 FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- Name: pago fk_pago_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- Name: producto fk_producto_periodo_venta_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT fk_producto_periodo_venta_producto1 FOREIGN KEY (id_periodo_venta_producto) REFERENCES spve.periodo_venta_producto(id_periodo_venta_producto);


--
-- Name: producto fk_unidad_id; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT fk_unidad_id FOREIGN KEY (id_unidad) REFERENCES inventario.unidad(id);


--
-- Name: venta_producto fk_unidad_inventario; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_unidad_inventario FOREIGN KEY (id_unidad_inventario) REFERENCES inventario.unidad_inventario(id);


--
-- Name: venta fk_venta_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT fk_venta_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- Name: venta_producto fk_venta_producto_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_venta_producto_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- Name: corte_caja kf_id_empleado; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT kf_id_empleado FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- Name: montos_cierre_caja tipo_monto_cierre_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT tipo_monto_cierre_fkey FOREIGN KEY (id_tipo_monto_cierre) REFERENCES spve.tipo_monto_cierre(id_tipo_monto_cierre);


--
-- Name: desglose_caja tipo_pago_fk; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT tipo_pago_fk FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- Name: montos_cierre_caja tipo_pago_fkey; Type: FK CONSTRAINT; Schema: spve; Owner: inverdata
--

ALTER TABLE ONLY spve.montos_cierre_caja
    ADD CONSTRAINT tipo_pago_fkey FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO inverdata;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- Name: TABLE almacen; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON TABLE inventario.almacen FROM PUBLIC;
REVOKE ALL ON TABLE inventario.almacen FROM inverdata;
GRANT ALL ON TABLE inventario.almacen TO inverdata;


--
-- Name: SEQUENCE almacen_id_seq; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON SEQUENCE inventario.almacen_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE inventario.almacen_id_seq FROM inverdata;
GRANT ALL ON SEQUENCE inventario.almacen_id_seq TO inverdata;


--
-- Name: TABLE lote_produccion; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON TABLE inventario.lote_produccion FROM PUBLIC;
REVOKE ALL ON TABLE inventario.lote_produccion FROM inverdata;
GRANT ALL ON TABLE inventario.lote_produccion TO inverdata;


--
-- Name: SEQUENCE lote_produccion_id_seq; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON SEQUENCE inventario.lote_produccion_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE inventario.lote_produccion_id_seq FROM inverdata;
GRANT ALL ON SEQUENCE inventario.lote_produccion_id_seq TO inverdata;


--
-- Name: TABLE precio_unidad_inventario; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON TABLE inventario.precio_unidad_inventario FROM PUBLIC;
REVOKE ALL ON TABLE inventario.precio_unidad_inventario FROM inverdata;
GRANT ALL ON TABLE inventario.precio_unidad_inventario TO inverdata;


--
-- Name: SEQUENCE precio_unidad_inventario_id_seq; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON SEQUENCE inventario.precio_unidad_inventario_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE inventario.precio_unidad_inventario_id_seq FROM inverdata;
GRANT ALL ON SEQUENCE inventario.precio_unidad_inventario_id_seq TO inverdata;


--
-- Name: TABLE unidad; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON TABLE inventario.unidad FROM PUBLIC;
REVOKE ALL ON TABLE inventario.unidad FROM inverdata;
GRANT ALL ON TABLE inventario.unidad TO inverdata;


--
-- Name: SEQUENCE unidad_id_seq; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON SEQUENCE inventario.unidad_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE inventario.unidad_id_seq FROM inverdata;
GRANT ALL ON SEQUENCE inventario.unidad_id_seq TO inverdata;


--
-- Name: TABLE unidad_inventario; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON TABLE inventario.unidad_inventario FROM PUBLIC;
REVOKE ALL ON TABLE inventario.unidad_inventario FROM inverdata;
GRANT ALL ON TABLE inventario.unidad_inventario TO inverdata;


--
-- Name: SEQUENCE unidad_inventario_id_seq; Type: ACL; Schema: inventario; Owner: inverdata
--

REVOKE ALL ON SEQUENCE inventario.unidad_inventario_id_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE inventario.unidad_inventario_id_seq FROM inverdata;
GRANT ALL ON SEQUENCE inventario.unidad_inventario_id_seq TO inverdata;


--
-- PostgreSQL database dump complete
--

