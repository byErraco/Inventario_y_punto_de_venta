SELECT p.tipo_persona||'-'||p.numero_identificacion_persona as empleado_apertura, fecha_apertura, p1.tipo_persona||'-'||p1.numero_identificacion_persona as empleado_cierre, fecha_corte as fecha_cierre
FROM spve.estado_caja as ec INNER JOIN spve.caja as c ON ec.id_caja = c.id_caja
LEFT JOIN spve.corte_caja as cc ON ec.id_estado_caja = cc.id_estado_caja
LEFT JOIN spve.cierre_caja as cic ON cc.id_corte_caja = cic.id_corte_caja
INNER JOIN spve.empleado as em ON ec.id_empleado = em.id_empleado
INNER JOIN spve.persona as p ON em.id_persona = p.id_persona
LEFT JOIN spve.empleado as em1 ON cc.id_empleado = em1.id_empleado
LEFT JOIN spve.persona as p1 ON em1.id_persona = p1.id_persona
WHERE ec.id_caja = 1
GROUP BY ec.id_estado_caja, descripcion_caja, empleado_apertura, fecha_apertura, empleado_cierre, fecha_cierre
ORDER BY fecha_apertura DESC;