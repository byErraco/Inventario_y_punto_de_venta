--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.11
-- Dumped by pg_dump version 9.5.11

-- Started on 2018-03-21 10:48:59 -04

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = spve, pg_catalog;

--
-- TOC entry 2370 (class 0 OID 78354)
-- Dependencies: 214
-- Data for Name: ajuste; Type: TABLE DATA; Schema: spve; Owner: postgres
--



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

INSERT INTO caja (id_caja, descripcion_caja, activo_caja) VALUES (1, 'Caja 1', 1);


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

INSERT INTO cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (1, 'Administrador', 1);
INSERT INTO cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (2, 'Gerente', 1);
INSERT INTO cargo (id_cargo, nombre_cargo, activo_cargo) VALUES (3, 'Cajero', 1);
INSERT INTO persona (id_persona, nombre_persona, apellido_persona, numero_identificacion_persona, direccion_persona, tipo_persona, email_persona, telefono_persona, activo_persona) VALUES (1, 'Administrador', 'Prueba', '0', 'Direccion', 'V', 'Correo', '0', 1);
INSERT INTO empleado (id_empleado, clave, id_cargo, id_persona, activo_empleado) VALUES (1, 'admin', 1, 1, 1);

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
INSERT INTO venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta) VALUES (1, 1234, '2018-03-19', 1, 1, 1);
INSERT INTO venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta) VALUES (2, 4321, '2018-03-19', 1, 1, 1);
INSERT INTO venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta) VALUES (3, 7890, '2018-03-23', 1, 1, 1);
INSERT INTO venta (id_venta, codigo_factura, fecha_venta, estado_venta, id_persona, activo_venta) VALUES (4, 0, '2018-03-12', 1, 1, 1);
INSERT INTO tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (1, 'Efectivo', 1);
INSERT INTO tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (2, 'Débito', 1);
INSERT INTO tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (3, 'Crédito', 1);
INSERT INTO tipo_pago (id_tipo_pago, descripcion_pago, activo_tipo_pago) VALUES (4, 'CestaTicket', 1);
INSERT INTO pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago) VALUES (2, 100, '2018-03-19 14:22:26', 1, 1, 1);
INSERT INTO pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago) VALUES (3, 101, '2018-03-19 14:30:11', 1, 1, 1);
INSERT INTO pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago) VALUES (4, 100.503998, '2018-03-19 14:30:58', 1, 1, 1);
INSERT INTO pago (id_pago, monto_pago, fecha_pago, id_tipo_pago, id_venta, activo_pago) VALUES (5, 200, '2018-03-19 00:00:00', 2, 2, 1);


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

INSERT INTO periodo_venta_producto (id_periodo_venta_producto, nombre_periodo_venta, activo_periodo_venta) VALUES (1, 'Diario', 1);
INSERT INTO periodo_venta_producto (id_periodo_venta_producto, nombre_periodo_venta, activo_periodo_venta) VALUES (2, 'Semanal', 1);


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
INSERT INTO producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, id_periodo_venta_producto, activo_producto) VALUES (1, 'Jugo de manzana', '1234', 1, 'Los Andes', 100, 1, 0, 1, 1);
INSERT INTO producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, id_periodo_venta_producto, activo_producto) VALUES (2, 'Jugo de naranja', '4321', 2, 'Frica', 50, 1, 0, 2, 1);
INSERT INTO producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, id_periodo_venta_producto, activo_producto) VALUES (3, 'Jugo de pera', '6789', NULL, 'Del valle', 200, 1, 0, NULL, 1);
INSERT INTO producto (id_producto, descripcion_producto, codigo_venta_producto, limite_venta_persona, descripcion_empaque, cantidad_disponible, balanza, producto_pre_fabricado, id_periodo_venta_producto, activo_producto) VALUES (4, 'Jugo de mamon', '7893', NULL, 'Sur del lago', 100, 1, 0, NULL, 1);
INSERT INTO precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, porcentaje_impuesto_producto, id_producto) VALUES (1, '2018-03-19', 30, 12, 112, 100, 0, 1, 12, 1);
INSERT INTO precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, porcentaje_impuesto_producto, id_producto) VALUES (2, '2018-03-19', 30, 6, 56, 50, 0, 1, 12, 2);
INSERT INTO precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, porcentaje_impuesto_producto, id_producto) VALUES (4, '2018-03-20', 30, NULL, 200, 0, 1, 1, 0, 3);
INSERT INTO precio_producto (id_precio_producto, fecha_registro_precio, margen_ganancia, impuesto_producto, precio_venta_publico, base_imponible, producto_exento, activo_precio_producto, porcentaje_impuesto_producto, id_producto) VALUES (3, '2018-03-19', 30, NULL, 75, 0, 1, 1, 0, 4);



--
-- TOC entry 2374 (class 0 OID 78382)
-- Dependencies: 218
-- Data for Name: producto_componente; Type: TABLE DATA; Schema: spve; Owner: postgres
--



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




--
-- TOC entry 2364 (class 0 OID 78307)
-- Dependencies: 208
-- Data for Name: venta_producto; Type: TABLE DATA; Schema: spve; Owner: postgres
--

INSERT INTO venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (1, 14, 1, 1, 1);
INSERT INTO venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (6, 18, 1, 2, 1);
INSERT INTO venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (8, 2, 1, 3, 1);
INSERT INTO venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (9, 2, 1, 4, 1);
INSERT INTO venta_producto (id_venta_producto, cantidad_producto, id_venta, id_producto, activo_venta_producto) VALUES (7, 4, 1, 2, 1);


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


-- Completed on 2018-03-21 10:48:59 -04

--
-- PostgreSQL database dump complete
--

