--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.7
-- Dumped by pg_dump version 10.3

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

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


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
-- Name: ajuste_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.ajuste_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.ajuste_seq OWNER TO postgres;

--
-- Name: caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.caja_seq OWNER TO postgres;

--
-- Name: caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.caja (
    id_caja integer DEFAULT nextval('spve.caja_seq'::regclass) NOT NULL,
    descripcion_caja character varying(45) NOT NULL,
    activo_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.caja OWNER TO postgres;

--
-- Name: cargo_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.cargo_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.cargo_seq OWNER TO postgres;

--
-- Name: cargo; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.cargo (
    id_cargo integer DEFAULT nextval('spve.cargo_seq'::regclass) NOT NULL,
    nombre_cargo character varying(45) NOT NULL,
    activo_cargo smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.cargo OWNER TO postgres;

--
-- Name: cierre_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.cierre_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.cierre_caja_seq OWNER TO postgres;

--
-- Name: cierre_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.cierre_caja (
    id_cierre_caja integer DEFAULT nextval('spve.cierre_caja_seq'::regclass) NOT NULL,
    id_corte_caja integer NOT NULL,
    activo_cierre_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.cierre_caja OWNER TO postgres;

--
-- Name: codigo_factura_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.codigo_factura_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.codigo_factura_seq OWNER TO postgres;

--
-- Name: compra_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.compra_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.compra_seq OWNER TO postgres;

--
-- Name: corte_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.corte_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.corte_caja_seq OWNER TO postgres;

--
-- Name: corte_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.corte_caja (
    id_corte_caja integer DEFAULT nextval('spve.corte_caja_seq'::regclass) NOT NULL,
    fecha_corte timestamp(0) without time zone NOT NULL,
    monto_corte real NOT NULL,
    excedente_caja real NOT NULL,
    restante_caja numeric(10,0) NOT NULL,
    id_estado_caja integer NOT NULL,
    activo_corte_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.corte_caja OWNER TO postgres;

--
-- Name: desglose_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.desglose_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.desglose_caja_seq OWNER TO postgres;

--
-- Name: desglose_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.desglose_caja (
    id_desglose_caja integer DEFAULT nextval('spve.desglose_caja_seq'::regclass) NOT NULL,
    monto_desglose_caja numeric(10,0) NOT NULL,
    tipo_pago_desglose character varying(45) NOT NULL,
    id_corte_caja integer NOT NULL,
    activo_desglose_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.desglose_caja OWNER TO postgres;

--
-- Name: empleado_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.empleado_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.empleado_seq OWNER TO postgres;

--
-- Name: empleado; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.empleado (
    id_empleado integer DEFAULT nextval('spve.empleado_seq'::regclass) NOT NULL,
    clave character varying(10) NOT NULL,
    id_cargo integer NOT NULL,
    id_persona integer NOT NULL,
    activo_empleado smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.empleado OWNER TO postgres;

--
-- Name: estado_caja_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.estado_caja_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.estado_caja_seq OWNER TO postgres;

--
-- Name: estado_caja; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.estado_caja (
    id_estado_caja integer DEFAULT nextval('spve.estado_caja_seq'::regclass) NOT NULL,
    fecha_apertura timestamp(0) without time zone NOT NULL,
    monto_apertura numeric(10,0) NOT NULL,
    id_empleado integer NOT NULL,
    id_caja integer NOT NULL,
    activo_estado_caja smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.estado_caja OWNER TO postgres;

--
-- Name: pago_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.pago_seq OWNER TO postgres;

--
-- Name: pago; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.pago (
    id_pago integer DEFAULT nextval('spve.pago_seq'::regclass) NOT NULL,
    monto_pago numeric(10,0) NOT NULL,
    fecha_pago timestamp(0) without time zone NOT NULL,
    id_tipo_pago integer NOT NULL,
    id_venta integer NOT NULL,
    activo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.pago OWNER TO postgres;

--
-- Name: periodo_venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.periodo_venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.periodo_venta_producto_seq OWNER TO postgres;

--
-- Name: periodo_venta_producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.periodo_venta_producto (
    id_periodo_venta_producto integer DEFAULT nextval('spve.periodo_venta_producto_seq'::regclass) NOT NULL,
    nombre_periodo_venta character varying(45) NOT NULL,
    activo_periodo_venta smallint NOT NULL
);


ALTER TABLE spve.periodo_venta_producto OWNER TO postgres;

--
-- Name: persona_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.persona_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.persona_seq OWNER TO postgres;

--
-- Name: persona; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.persona (
    id_persona integer DEFAULT nextval('spve.persona_seq'::regclass) NOT NULL,
    nombre_persona character varying(45) NOT NULL,
    apellido_persona character varying(45) NOT NULL,
    tipo_persona character varying(1) NOT NULL,
    numero_identificacion_persona character varying(45) NOT NULL,
    direccion_persona character varying(100) NOT NULL,
    email_persona character varying(45),
    telefono_persona character varying(45),
    activo_persona smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.persona OWNER TO postgres;

--
-- Name: precio_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.precio_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.precio_producto_seq OWNER TO postgres;

--
-- Name: precio_producto; Type: TABLE; Schema: spve; Owner: postgres
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


ALTER TABLE spve.precio_producto OWNER TO postgres;

--
-- Name: produccion_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.produccion_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.produccion_seq OWNER TO postgres;

--
-- Name: producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.producto_seq OWNER TO postgres;

--
-- Name: producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.producto (
    id_producto integer DEFAULT nextval('spve.producto_seq'::regclass) NOT NULL,
    descripcion_producto character varying(45) NOT NULL,
    codigo_venta_producto character varying(45) NOT NULL,
    limite_venta_persona numeric(10,4),
    descripcion_empaque character varying(45),
    cantidad_disponible numeric(10,0) DEFAULT 0 NOT NULL,
    balanza smallint DEFAULT 0 NOT NULL,
    producto_pre_fabricado smallint DEFAULT 0 NOT NULL,
    activo_producto smallint DEFAULT 1 NOT NULL,
    id_periodo_venta_producto integer,
    seguimiento smallint,
    id_unidad integer
);


ALTER TABLE spve.producto OWNER TO postgres;

--
-- Name: producto_componente_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.producto_componente_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.producto_componente_seq OWNER TO postgres;

--
-- Name: tipo_pago_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.tipo_pago_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.tipo_pago_seq OWNER TO postgres;

--
-- Name: tipo_pago; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.tipo_pago (
    id_tipo_pago integer DEFAULT nextval('spve.tipo_pago_seq'::regclass) NOT NULL,
    descripcion_pago character varying(45) NOT NULL,
    activo_tipo_pago smallint DEFAULT 1 NOT NULL
);


ALTER TABLE spve.tipo_pago OWNER TO postgres;

--
-- Name: venta_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.venta_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.venta_seq OWNER TO postgres;

--
-- Name: venta; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.venta (
    id_venta integer DEFAULT nextval('spve.venta_seq'::regclass) NOT NULL,
    codigo_factura integer DEFAULT nextval('spve.codigo_factura_seq'::regclass) NOT NULL,
    fecha_venta timestamp(0) without time zone NOT NULL,
    estado_venta integer NOT NULL,
    id_persona integer NOT NULL,
    activo_venta smallint DEFAULT 1 NOT NULL,
    fecha_ultima_sincronizacion timestamp without time zone,
    fecha_ultima_modificacion timestamp without time zone NOT NULL
);


ALTER TABLE spve.venta OWNER TO postgres;

--
-- Name: venta_producto_seq; Type: SEQUENCE; Schema: spve; Owner: postgres
--

CREATE SEQUENCE spve.venta_producto_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE spve.venta_producto_seq OWNER TO postgres;

--
-- Name: venta_producto; Type: TABLE; Schema: spve; Owner: postgres
--

CREATE TABLE spve.venta_producto (
    id_venta_producto integer DEFAULT nextval('spve.venta_producto_seq'::regclass) NOT NULL,
    cantidad_producto real NOT NULL,
    id_venta integer NOT NULL,
    activo_venta_producto smallint DEFAULT 1 NOT NULL,
    id_unidad_inventario integer NOT NULL,
    fecha_ultima_modificacion timestamp without time zone NOT NULL
);


ALTER TABLE spve.venta_producto OWNER TO postgres;

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
-- Name: caja caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.caja
    ADD CONSTRAINT caja_pkey PRIMARY KEY (id_caja);


--
-- Name: cargo cargo_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.cargo
    ADD CONSTRAINT cargo_pkey PRIMARY KEY (id_cargo);


--
-- Name: cierre_caja cierre_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT cierre_caja_pkey PRIMARY KEY (id_cierre_caja);


--
-- Name: corte_caja corte_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT corte_caja_pkey PRIMARY KEY (id_corte_caja);


--
-- Name: desglose_caja desglose_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT desglose_caja_pkey PRIMARY KEY (id_desglose_caja);


--
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id_empleado);


--
-- Name: estado_caja estado_caja_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT estado_caja_pkey PRIMARY KEY (id_estado_caja);


--
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- Name: periodo_venta_producto periodo_venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.periodo_venta_producto
    ADD CONSTRAINT periodo_venta_producto_pkey PRIMARY KEY (id_periodo_venta_producto);


--
-- Name: persona persona_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (id_persona);


--
-- Name: precio_producto precio_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.precio_producto
    ADD CONSTRAINT precio_producto_pkey PRIMARY KEY (id_precio_producto);


--
-- Name: producto producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT producto_pkey PRIMARY KEY (id_producto);


--
-- Name: tipo_pago tipo_pago_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.tipo_pago
    ADD CONSTRAINT tipo_pago_pkey PRIMARY KEY (id_tipo_pago);


--
-- Name: venta venta_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT venta_pkey PRIMARY KEY (id_venta);


--
-- Name: venta_producto venta_producto_pkey; Type: CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT venta_producto_pkey PRIMARY KEY (id_venta_producto);


--
-- Name: venta_producto venta_unidad_inventario; Type: CONSTRAINT; Schema: spve; Owner: postgres
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
-- Name: fki_unidad_id; Type: INDEX; Schema: spve; Owner: postgres
--

CREATE INDEX fki_unidad_id ON spve.producto USING btree (id_unidad);


--
-- Name: fki_unidad_inventario; Type: INDEX; Schema: spve; Owner: postgres
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
-- Name: cierre_caja fk_cierre_caja_corte_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.cierre_caja
    ADD CONSTRAINT fk_cierre_caja_corte_caja1 FOREIGN KEY (id_corte_caja) REFERENCES spve.corte_caja(id_corte_caja);


--
-- Name: corte_caja fk_corte_caja_estado_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.corte_caja
    ADD CONSTRAINT fk_corte_caja_estado_caja1 FOREIGN KEY (id_estado_caja) REFERENCES spve.estado_caja(id_estado_caja);


--
-- Name: desglose_caja fk_desglose_caja_corte_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.desglose_caja
    ADD CONSTRAINT fk_desglose_caja_corte_caja1 FOREIGN KEY (id_corte_caja) REFERENCES spve.corte_caja(id_corte_caja);


--
-- Name: empleado fk_empleado_cargo1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_cargo1 FOREIGN KEY (id_cargo) REFERENCES spve.cargo(id_cargo);


--
-- Name: empleado fk_empleado_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.empleado
    ADD CONSTRAINT fk_empleado_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- Name: estado_caja fk_estado_caja_caja1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_caja1 FOREIGN KEY (id_caja) REFERENCES spve.caja(id_caja);


--
-- Name: estado_caja fk_estado_caja_empleado1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.estado_caja
    ADD CONSTRAINT fk_estado_caja_empleado1 FOREIGN KEY (id_empleado) REFERENCES spve.empleado(id_empleado);


--
-- Name: pago fk_pago_tipo_pago1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_tipo_pago1 FOREIGN KEY (id_tipo_pago) REFERENCES spve.tipo_pago(id_tipo_pago);


--
-- Name: pago fk_pago_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.pago
    ADD CONSTRAINT fk_pago_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- Name: precio_producto fk_precio_producto_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.precio_producto
    ADD CONSTRAINT fk_precio_producto_producto1 FOREIGN KEY (id_producto) REFERENCES spve.producto(id_producto);


--
-- Name: producto fk_producto_periodo_venta_producto1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT fk_producto_periodo_venta_producto1 FOREIGN KEY (id_periodo_venta_producto) REFERENCES spve.periodo_venta_producto(id_periodo_venta_producto);


--
-- Name: producto fk_unidad_id; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.producto
    ADD CONSTRAINT fk_unidad_id FOREIGN KEY (id_unidad) REFERENCES inventario.unidad(id);


--
-- Name: venta_producto fk_unidad_inventario; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_unidad_inventario FOREIGN KEY (id_unidad_inventario) REFERENCES inventario.unidad_inventario(id);


--
-- Name: venta fk_venta_persona1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.venta
    ADD CONSTRAINT fk_venta_persona1 FOREIGN KEY (id_persona) REFERENCES spve.persona(id_persona);


--
-- Name: venta_producto fk_venta_producto_venta1; Type: FK CONSTRAINT; Schema: spve; Owner: postgres
--

ALTER TABLE ONLY spve.venta_producto
    ADD CONSTRAINT fk_venta_producto_venta1 FOREIGN KEY (id_venta) REFERENCES spve.venta(id_venta);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
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
-- Name: SEQUENCE ajuste_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.ajuste_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.ajuste_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.ajuste_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.ajuste_seq TO inverdata;


--
-- Name: SEQUENCE caja_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.caja_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.caja_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.caja_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.caja_seq TO inverdata;


--
-- Name: TABLE caja; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.caja FROM PUBLIC;
REVOKE ALL ON TABLE spve.caja FROM postgres;
GRANT ALL ON TABLE spve.caja TO postgres;
GRANT ALL ON TABLE spve.caja TO inverdata;


--
-- Name: SEQUENCE cargo_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.cargo_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.cargo_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.cargo_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.cargo_seq TO inverdata;


--
-- Name: TABLE cargo; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.cargo FROM PUBLIC;
REVOKE ALL ON TABLE spve.cargo FROM postgres;
GRANT ALL ON TABLE spve.cargo TO postgres;
GRANT ALL ON TABLE spve.cargo TO inverdata;


--
-- Name: SEQUENCE cierre_caja_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.cierre_caja_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.cierre_caja_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.cierre_caja_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.cierre_caja_seq TO inverdata;


--
-- Name: TABLE cierre_caja; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.cierre_caja FROM PUBLIC;
REVOKE ALL ON TABLE spve.cierre_caja FROM postgres;
GRANT ALL ON TABLE spve.cierre_caja TO postgres;
GRANT ALL ON TABLE spve.cierre_caja TO inverdata;


--
-- Name: SEQUENCE codigo_factura_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.codigo_factura_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.codigo_factura_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.codigo_factura_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.codigo_factura_seq TO inverdata;


--
-- Name: SEQUENCE compra_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.compra_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.compra_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.compra_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.compra_seq TO inverdata;


--
-- Name: SEQUENCE corte_caja_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.corte_caja_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.corte_caja_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.corte_caja_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.corte_caja_seq TO inverdata;


--
-- Name: TABLE corte_caja; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.corte_caja FROM PUBLIC;
REVOKE ALL ON TABLE spve.corte_caja FROM postgres;
GRANT ALL ON TABLE spve.corte_caja TO postgres;
GRANT ALL ON TABLE spve.corte_caja TO inverdata;


--
-- Name: SEQUENCE desglose_caja_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.desglose_caja_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.desglose_caja_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.desglose_caja_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.desglose_caja_seq TO inverdata;


--
-- Name: TABLE desglose_caja; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.desglose_caja FROM PUBLIC;
REVOKE ALL ON TABLE spve.desglose_caja FROM postgres;
GRANT ALL ON TABLE spve.desglose_caja TO postgres;
GRANT ALL ON TABLE spve.desglose_caja TO inverdata;


--
-- Name: SEQUENCE empleado_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.empleado_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.empleado_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.empleado_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.empleado_seq TO inverdata;


--
-- Name: TABLE empleado; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.empleado FROM PUBLIC;
REVOKE ALL ON TABLE spve.empleado FROM postgres;
GRANT ALL ON TABLE spve.empleado TO postgres;
GRANT ALL ON TABLE spve.empleado TO inverdata;


--
-- Name: SEQUENCE estado_caja_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.estado_caja_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.estado_caja_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.estado_caja_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.estado_caja_seq TO inverdata;


--
-- Name: TABLE estado_caja; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.estado_caja FROM PUBLIC;
REVOKE ALL ON TABLE spve.estado_caja FROM postgres;
GRANT ALL ON TABLE spve.estado_caja TO postgres;
GRANT ALL ON TABLE spve.estado_caja TO inverdata;


--
-- Name: SEQUENCE pago_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.pago_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.pago_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.pago_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.pago_seq TO inverdata;


--
-- Name: TABLE pago; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.pago FROM PUBLIC;
REVOKE ALL ON TABLE spve.pago FROM postgres;
GRANT ALL ON TABLE spve.pago TO postgres;
GRANT ALL ON TABLE spve.pago TO inverdata;


--
-- Name: SEQUENCE periodo_venta_producto_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.periodo_venta_producto_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.periodo_venta_producto_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.periodo_venta_producto_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.periodo_venta_producto_seq TO inverdata;


--
-- Name: TABLE periodo_venta_producto; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.periodo_venta_producto FROM PUBLIC;
REVOKE ALL ON TABLE spve.periodo_venta_producto FROM postgres;
GRANT ALL ON TABLE spve.periodo_venta_producto TO postgres;
GRANT ALL ON TABLE spve.periodo_venta_producto TO inverdata;


--
-- Name: SEQUENCE persona_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.persona_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.persona_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.persona_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.persona_seq TO inverdata;


--
-- Name: TABLE persona; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.persona FROM PUBLIC;
REVOKE ALL ON TABLE spve.persona FROM postgres;
GRANT ALL ON TABLE spve.persona TO postgres;
GRANT ALL ON TABLE spve.persona TO inverdata;


--
-- Name: SEQUENCE precio_producto_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.precio_producto_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.precio_producto_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.precio_producto_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.precio_producto_seq TO inverdata;


--
-- Name: TABLE precio_producto; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.precio_producto FROM PUBLIC;
REVOKE ALL ON TABLE spve.precio_producto FROM postgres;
GRANT ALL ON TABLE spve.precio_producto TO postgres;
GRANT ALL ON TABLE spve.precio_producto TO inverdata;


--
-- Name: SEQUENCE produccion_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.produccion_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.produccion_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.produccion_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.produccion_seq TO inverdata;


--
-- Name: SEQUENCE producto_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.producto_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.producto_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.producto_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.producto_seq TO inverdata;


--
-- Name: TABLE producto; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.producto FROM PUBLIC;
REVOKE ALL ON TABLE spve.producto FROM postgres;
GRANT ALL ON TABLE spve.producto TO postgres;
GRANT ALL ON TABLE spve.producto TO inverdata;


--
-- Name: SEQUENCE producto_componente_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.producto_componente_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.producto_componente_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.producto_componente_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.producto_componente_seq TO inverdata;


--
-- Name: SEQUENCE tipo_pago_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.tipo_pago_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.tipo_pago_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.tipo_pago_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.tipo_pago_seq TO inverdata;


--
-- Name: TABLE tipo_pago; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.tipo_pago FROM PUBLIC;
REVOKE ALL ON TABLE spve.tipo_pago FROM postgres;
GRANT ALL ON TABLE spve.tipo_pago TO postgres;
GRANT ALL ON TABLE spve.tipo_pago TO inverdata;


--
-- Name: SEQUENCE venta_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.venta_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.venta_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.venta_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.venta_seq TO inverdata;


--
-- Name: TABLE venta; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.venta FROM PUBLIC;
REVOKE ALL ON TABLE spve.venta FROM postgres;
GRANT ALL ON TABLE spve.venta TO postgres;
GRANT ALL ON TABLE spve.venta TO inverdata;


--
-- Name: SEQUENCE venta_producto_seq; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON SEQUENCE spve.venta_producto_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE spve.venta_producto_seq FROM postgres;
GRANT ALL ON SEQUENCE spve.venta_producto_seq TO postgres;
GRANT SELECT,USAGE ON SEQUENCE spve.venta_producto_seq TO inverdata;


--
-- Name: TABLE venta_producto; Type: ACL; Schema: spve; Owner: postgres
--

REVOKE ALL ON TABLE spve.venta_producto FROM PUBLIC;
REVOKE ALL ON TABLE spve.venta_producto FROM postgres;
GRANT ALL ON TABLE spve.venta_producto TO postgres;
GRANT ALL ON TABLE spve.venta_producto TO inverdata;


--
-- PostgreSQL database dump complete
--

