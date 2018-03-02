SELECT caja.id_caja, MAX(estado_caja.id_estado_caja) FROM spve.caja 
INNER JOIN spve.estado_caja ON estado_caja.id_caja = caja.id_caja
GROUP BY caja.id_caja;
SELECT estado_caja.id_estado_caja, MAX(corte_caja.id_corte_caja) FROM spve.estado_caja
INNER JOIN spve.corte_caja ON corte_caja.id_estado_caja = estado_caja.id_estado_caja
GROUP BY estado_caja.id_estado_caja;
SELECT EXISTS (SELECT cierre_caja.id_corte_caja, corte_caja.id_corte_caja FROM spve.cierre_caja 
INNER JOIN spve.corte_caja ON cierre_caja.id_corte_caja = corte_caja.id_corte_caja
GROUP BY cierre_caja.id_cierre_caja, corte_caja.id_corte_caja)