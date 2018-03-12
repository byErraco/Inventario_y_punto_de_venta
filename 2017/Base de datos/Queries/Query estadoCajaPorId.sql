SELECT p.tipo_persona||'-'||p.numero_identificacion_persona as empleado_apertura, fecha_apertura, fecha_corte
FROM spve.estado_caja as ec INNER JOIN spve.caja as c ON ec.id_caja = c.id_caja
LEFT JOIN spve.corte_caja as cc ON ec.id_estado_caja = cc.id_estado_caja
INNER JOIN spve.empleado as em ON ec.id_empleado = em.id_empleado
INNER JOIN spve.persona as p ON em.id_persona = p.id_persona
WHERE ec.id_caja = 1
ORDER BY fecha_apertura DESC;