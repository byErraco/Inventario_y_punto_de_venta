--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.11
-- Dumped by pg_dump version 9.5.11

-- Started on 2018-03-21 10:50:35 -04

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

--
-- TOC entry 2370 (class 0 OID 78354)
-- Dependencies: 214
-- Data for Name: ajuste; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY ajuste (id_ajuste, fecha_ajuste, cantidad_ajuste, descripcion_ajuste, id_producto, activo_ajuste) FROM stdin;
\.


--
-- TOC entry 2383 (class 0 OID 0)
-- Dependencies: 213
-- Name: ajuste_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('ajuste_seq', 1, false);


--
-- TOC entry 2338 (class 0 OID 78136)
-- Dependencies: 182
-- Data for Name: caja; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY caja (id_caja, descripcion_caja, activo_caja) FROM stdin;
1	Caja 1	1
\.


--
-- TOC entry 2384 (class 0 OID 0)
-- Dependencies: 181
-- Name: caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('caja_seq', 1, false);


--
-- TOC entry 2340 (class 0 OID 78145)
-- Dependencies: 184
-- Data for Name: cargo; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY cargo (id_cargo, nombre_cargo, activo_cargo) FROM stdin;
1	Administrador	1
2	Gerente	1
3	Cajero	1
\.


--
-- TOC entry 2385 (class 0 OID 0)
-- Dependencies: 183
-- Name: cargo_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('cargo_seq', 1, false);


--
-- TOC entry 2372 (class 0 OID 78368)
-- Dependencies: 216
-- Data for Name: cierre_caja; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY cierre_caja (id_cierre_caja, id_corte_caja, activo_cierre_caja) FROM stdin;
\.


--
-- TOC entry 2386 (class 0 OID 0)
-- Dependencies: 215
-- Name: cierre_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('cierre_caja_seq', 1, false);


--
-- TOC entry 2387 (class 0 OID 0)
-- Dependencies: 219
-- Name: codigo_factura_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('codigo_factura_seq', 1, false);


--
-- TOC entry 2366 (class 0 OID 78326)
-- Dependencies: 210
-- Data for Name: compra; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY compra (id_compra, proveedor_compra, cantidad_compra, fecha_compra, costo_unidad_compra, orden_compra, id_producto, activo_compra) FROM stdin;
\.


--
-- TOC entry 2388 (class 0 OID 0)
-- Dependencies: 209
-- Name: compra_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('compra_seq', 1, false);


--
-- TOC entry 2348 (class 0 OID 78201)
-- Dependencies: 192
-- Data for Name: corte_caja; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY corte_caja (id_corte_caja, fecha_corte, monto_corte, excedente_caja, restante_caja, id_estado_caja, activo_corte_caja) FROM stdin;
\.


--
-- TOC entry 2389 (class 0 OID 0)
-- Dependencies: 191
-- Name: corte_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('corte_caja_seq', 1, false);


--
-- TOC entry 2350 (class 0 OID 78215)
-- Dependencies: 194
-- Data for Name: desglose_caja; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY desglose_caja (id_desglose_caja, monto_desglose_caja, tipo_pago_desglose, id_corte_caja, activo_desglose_caja) FROM stdin;
\.


--
-- TOC entry 2390 (class 0 OID 0)
-- Dependencies: 193
-- Name: desglose_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('desglose_caja_seq', 1, false);


--
-- TOC entry 2344 (class 0 OID 78163)
-- Dependencies: 188
-- Data for Name: empleado; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY empleado (id_empleado, clave, id_cargo, id_persona, activo_empleado) FROM stdin;
1	admin	1	1	1
\.


--
-- TOC entry 2391 (class 0 OID 0)
-- Dependencies: 187
-- Name: empleado_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('empleado_seq', 1, false);


--
-- TOC entry 2346 (class 0 OID 78182)
-- Dependencies: 190
-- Data for Name: estado_caja; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY estado_caja (id_estado_caja, fecha_apertura, monto_apertura, id_empleado, id_caja, activo_estado_caja) FROM stdin;
\.


--
-- TOC entry 2392 (class 0 OID 0)
-- Dependencies: 189
-- Name: estado_caja_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('estado_caja_seq', 1, false);


--
-- TOC entry 2356 (class 0 OID 78252)
-- Dependencies: 200
-- Data for Name: pago; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago) FROM stdin;
2	100	2018-03-19 14:22:26	1	1	1
3	101	2018-03-19 14:30:11	1	1	1
4	100.503998	2018-03-19 14:30:58	1	1	1
5	200	2018-03-19 00:00:00	2	2	1
\.


--
-- TOC entry 2393 (class 0 OID 0)
-- Dependencies: 199
-- Name: pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('pago_seq', 4, true);


--
-- TOC entry 2360 (class 0 OID 78280)
-- Dependencies: 204
-- Data for Name: periodo_venta_producto; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY periodo_venta_producto (id_periodo_venta_producto, nombre_periodo_venta, activo_periodo_venta) FROM stdin;
1	Diario	1
2	Semanal	1
\.


--
-- TOC entry 2394 (class 0 OID 0)
-- Dependencies: 203
-- Name: periodo_venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('periodo_venta_producto_seq', 1, false);


--
-- TOC entry 2342 (class 0 OID 78154)
-- Dependencies: 186
-- Data for Name: persona; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY persona (id_persona, nombre_persona, apellido_persona, numero_identificacion_persona, direccion_persona, tipo_persona, email_persona, telefono_persona, activo_persona) FROM stdin;
1	Administrador	Prueba	0	Direccion	V	Correo	0	1
\.


--
-- TOC entry 2395 (class 0 OID 0)
-- Dependencies: 185
-- Name: persona_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('persona_seq', 1, false);


--
-- TOC entry 2358 (class 0 OID 78271)
-- Dependencies: 202
-- Data for Name: precio_producto; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, porcentaje_impuesto_producto) FROM stdin;
1	2018-03-19	30	12	112	100	0	1	12
2	2018-03-19	30	6	56	50	0	1	12
4	2018-03-20	30	\N	200	0	1	1	0
3	2018-03-19	30	\N	75	0	1	1	0
\.


--
-- TOC entry 2396 (class 0 OID 0)
-- Dependencies: 201
-- Name: precio_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('precio_producto_seq', 1, false);


--
-- TOC entry 2368 (class 0 OID 78340)
-- Dependencies: 212
-- Data for Name: produccion; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY produccion (id_produccion, cantidad_produccion, fecha_produccion, id_producto, activo_produccion) FROM stdin;
\.


--
-- TOC entry 2397 (class 0 OID 0)
-- Dependencies: 211
-- Name: produccion_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('produccion_seq', 1, false);


--
-- TOC entry 2362 (class 0 OID 78288)
-- Dependencies: 206
-- Data for Name: producto; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, id_precio_producto, id_periodo_venta_producto, activo_producto) FROM stdin;
1	Jugo de manzana	1234	1	Los Andes	100	1	0	1	1	1
2	Jugo de naranja	4321	2	Frica	50	1	0	2	2	1
3	Jugo de pera	6789	\N	Del valle	200	1	0	3	\N	1
4	Jugo de mamon	7893	\N	Sur del lago	100	1	0	4	\N	1
\.


--
-- TOC entry 2374 (class 0 OID 78382)
-- Dependencies: 218
-- Data for Name: producto_componente; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY producto_componente (id_producto_componente, id_producto, cantidad_compuesto, activo_producto_componente) FROM stdin;
\.


--
-- TOC entry 2398 (class 0 OID 0)
-- Dependencies: 217
-- Name: producto_componente_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('producto_componente_seq', 1, false);


--
-- TOC entry 2399 (class 0 OID 0)
-- Dependencies: 205
-- Name: producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('producto_seq', 1, false);


--
-- TOC entry 2354 (class 0 OID 78243)
-- Dependencies: 198
-- Data for Name: tipo_pago; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) FROM stdin;
1	Efectivo	1
2	Débito	1
3	Crédito	1
4	CestaTicket	1
\.


--
-- TOC entry 2400 (class 0 OID 0)
-- Dependencies: 197
-- Name: tipo_pago_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('tipo_pago_seq', 1, false);


--
-- TOC entry 2352 (class 0 OID 78229)
-- Dependencies: 196
-- Data for Name: venta; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta) FROM stdin;
1	1234	2018-03-19	1	1	1
2	4321	2018-03-19	1	1	1
3	7890	2018-03-23	1	1	1
4	0	2018-03-12	1	1	1
\.


--
-- TOC entry 2364 (class 0 OID 78307)
-- Dependencies: 208
-- Data for Name: venta_producto; Type: TABLE DATA; Schema: spve; Owner: postgres
--

COPY venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) FROM stdin;
1	14	1	1	1
6	18	1	2	1
8	2	1	3	1
9	2	1	4	1
7	4	1	2	1
\.


--
-- TOC entry 2401 (class 0 OID 0)
-- Dependencies: 207
-- Name: venta_producto_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('venta_producto_seq', 6, true);


--
-- TOC entry 2402 (class 0 OID 0)
-- Dependencies: 195
-- Name: venta_seq; Type: SEQUENCE SET; Schema: spve; Owner: postgres
--

SELECT pg_catalog.setval('venta_seq', 1, false);


--
-- TOC entry 2199 (class 2606 OID 78360)
-- Name: ajuste_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY ajuste
    ADD CONSTRAINT ajuste_pkey PRIMARY KEY (id_ajuste);


--
-- TOC entry 2167 (class 2606 OID 78142)
-- Name: caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id_caja);


--
-- TOC entry 2169 (class 2606 OID 78151)
-- Name: cargo_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo);


--
-- TOC entry 2201 (class 2606 OID 78374)
-- Name: cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY cierre_caja
    ADD CONSTRAINT cierre_caja_pkey PRIMARY KEY (id_cierre_caja);


--
-- TOC entry 2195 (class 2606 OID 78332)
-- Name: compra_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT compra_pkey PRIMARY KEY (id_compra);


--
-- TOC entry 2177 (class 2606 OID 78207)
-- Name: corte_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY corte_caja
    ADD CONSTRAINT corte_caja_pkey PRIMARY KEY (id_corte_caja);


--
-- TOC entry 2179 (class 2606 OID 78221)
-- Name: desglose_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY desglose_caja
    ADD CONSTRAINT desglose_caja_pkey PRIMARY KEY (id_desglose_caja);


--
-- TOC entry 2173 (class 2606 OID 78169)
-- Name: empleado_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


--
-- TOC entry 2175 (class 2606 OID 78188)
-- Name: estado_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY estado_caja
    ADD CONSTRAINT estado_caja_pkey PRIMARY KEY (id_estado_caja);


--
-- TOC entry 2185 (class 2606 OID 78258)
-- Name: pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- TOC entry 2189 (class 2606 OID 78285)
-- Name: periodo_venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY periodo_venta_producto
    ADD CONSTRAINT periodo_venta_producto_pkey PRIMARY KEY (id_periodo_venta_producto);


--
-- TOC entry 2171 (class 2606 OID 78160)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- TOC entry 2187 (class 2606 OID 78277)
-- Name: precio_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY precio_producto
    ADD CONSTRAINT precio_producto_pkey PRIMARY KEY (id_precio_producto);


--
-- TOC entry 2197 (class 2606 OID 78346)
-- Name: produccion_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY produccion
    ADD CONSTRAINT produccion_pkey PRIMARY KEY (id_produccion);


--
-- TOC entry 2203 (class 2606 OID 78388)
-- Name: producto_componente_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto_componente
    ADD CONSTRAINT producto_componente_pkey PRIMARY KEY (id_producto_componente, id_producto);


--
-- TOC entry 2191 (class 2606 OID 78294)
-- Name: producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- TOC entry 2183 (class 2606 OID 78249)
-- Name: tipo_pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY tipo_pago
    ADD CONSTRAINT tipo_pago_pkey PRIMARY KEY (id_tipo_pago);


--
-- TOC entry 2181 (class 2606 OID 78235)
-- Name: venta_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (id_venta);


--
-- TOC entry 2193 (class 2606 OID 78313)
-- Name: venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY venta_producto
    ADD CONSTRAINT venta_producto_pkey PRIMARY KEY (id_venta_producto);


--
-- TOC entry 2219 (class 2606 OID 78361)
-- Name: fk_ajuste_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY ajuste
    ADD CONSTRAINT fk_ajuste_producto1 FOREIGN KEY (id_producto) REFERENCES producto(id_producto);


--
-- TOC entry 2220 (class 2606 OID 78375)
-- Name: fk_cierre_caja_corte_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY cierre_caja
    ADD CONSTRAINT fk_cierre_caja_corte_caja1 FOREIGN KEY (id_corte_caja) REFERENCES corte_caja(id_corte_caja);


--
-- TOC entry 2217 (class 2606 OID 78333)
-- Name: fk_compra_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY compra
    ADD CONSTRAINT fk_compra_producto1 FOREIGN KEY (id_producto) REFERENCES producto(id_producto);


--
-- TOC entry 2208 (class 2606 OID 78208)
-- Name: fk_corte_caja_estado_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY corte_caja
    ADD CONSTRAINT fk_corte_caja_estado_caja1 FOREIGN KEY (id_estado_caja) REFERENCES estado_caja(id_estado_caja);


--
-- TOC entry 2209 (class 2606 OID 78222)
-- Name: fk_desglose_caja_corte_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY desglose_caja
    ADD CONSTRAINT fk_desglose_caja_corte_caja1 FOREIGN KEY (id_corte_caja) REFERENCES corte_caja(id_corte_caja);


--
-- TOC entry 2204 (class 2606 OID 78170)
-- Name: fk_empleado_cargo1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_cargo1 FOREIGN KEY (id_cargo) REFERENCES cargo(id_cargo);


--
-- TOC entry 2205 (class 2606 OID 78175)
-- Name: fk_empleado_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY empleado
    ADD CONSTRAINT fk_empleado_persona1 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 2207 (class 2606 OID 78194)
-- Name: fk_estado_caja_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY estado_caja
    ADD CONSTRAINT fk_estado_caja_caja1 FOREIGN KEY (id_caja) REFERENCES caja(id_caja);


--
-- TOC entry 2206 (class 2606 OID 78189)
-- Name: fk_estado_caja_empleado1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY estado_caja
    ADD CONSTRAINT fk_estado_caja_empleado1 FOREIGN KEY (id_empleado) REFERENCES empleado(id_empleado);


--
-- TOC entry 2211 (class 2606 OID 78259)
-- Name: fk_pago_tipo_pago1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY pago
    ADD CONSTRAINT fk_pago_tipo_pago1 FOREIGN KEY (id_tipo_pago) REFERENCES tipo_pago(id_tipo_pago);


--
-- TOC entry 2212 (class 2606 OID 78264)
-- Name: fk_pago_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY pago
    ADD CONSTRAINT fk_pago_venta1 FOREIGN KEY (id_venta) REFERENCES venta(id_venta);


--
-- TOC entry 2218 (class 2606 OID 78347)
-- Name: fk_produccion_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY produccion
    ADD CONSTRAINT fk_produccion_producto1 FOREIGN KEY (id_producto) REFERENCES producto(id_producto);


--
-- TOC entry 2221 (class 2606 OID 78389)
-- Name: fk_producto_has_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto_componente
    ADD CONSTRAINT fk_producto_has_producto_producto1 FOREIGN KEY (id_producto) REFERENCES producto(id_producto);


--
-- TOC entry 2222 (class 2606 OID 78394)
-- Name: fk_producto_has_producto_producto2; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto_componente
    ADD CONSTRAINT fk_producto_has_producto_producto2 FOREIGN KEY (id_producto_componente) REFERENCES producto(id_producto);


--
-- TOC entry 2214 (class 2606 OID 78300)
-- Name: fk_producto_periodo_venta_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto
    ADD CONSTRAINT fk_producto_periodo_venta_producto1 FOREIGN KEY (id_periodo_venta_producto) REFERENCES periodo_venta_producto(id_periodo_venta_producto);


--
-- TOC entry 2213 (class 2606 OID 78295)
-- Name: fk_producto_precio_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY producto
    ADD CONSTRAINT fk_producto_precio_producto1 FOREIGN KEY (id_precio_producto) REFERENCES precio_producto(id_precio_producto);


--
-- TOC entry 2210 (class 2606 OID 78236)
-- Name: fk_venta_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY venta
    ADD CONSTRAINT fk_venta_persona1 FOREIGN KEY (id_persona) REFERENCES persona(id_persona);


--
-- TOC entry 2216 (class 2606 OID 78319)
-- Name: fk_venta_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY venta_producto
    ADD CONSTRAINT fk_venta_producto_producto1 FOREIGN KEY (id_producto) REFERENCES producto(id_producto);


--
-- TOC entry 2215 (class 2606 OID 78314)
-- Name: fk_venta_producto_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY venta_producto
    ADD CONSTRAINT fk_venta_producto_venta1 FOREIGN KEY (id_venta) REFERENCES venta(id_venta);


-- Completed on 2018-03-21 10:50:36 -04

--
-- PostgreSQL database dump complete
--

