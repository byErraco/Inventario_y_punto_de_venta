SELECT EXISTS (SELECT id_cierre_caja, fecha_corte FROM spve.cierre_caja AS cic LEFT JOIN spve.corte_caja as c
ON cic.id_corte_caja = c.id_corte_caja 
WHERE cic.id_corte_caja = 
(SELECT max(id_corte_caja) FROM spve.corte_caja as cc 
WHERE cc.id_estado_caja = 
(SELECT max(id_estado_caja) FROM spve.estado_caja as ec WHERE ec.id_caja = 1)) AND fecha_corte >= current_date);
