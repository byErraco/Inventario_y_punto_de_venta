﻿SELECT EXISTS (SELECT id_cierre_caja from spve.cierre_caja as cic WHERE cic.id_corte_caja = (SELECT max(id_corte_caja) FROM spve.corte_caja as cc WHERE cc.id_estado_caja = (SELECT max(id_estado_caja) FROM spve.estado_caja as ec WHERE ec.id_caja = 2)));