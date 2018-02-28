--QUERY PARA AGREGAR UNA PERSONA
/*INSERT INTO spve.persona (id_persona, nombre_persona, apellido_persona, tipo_persona, numero_identificacion_persona, 
telefono_persona, email_persona, direccion_persona, activo_persona) 
VALUES ('2' , 'ernesto' , 'rincon' , 'V' , '20944806' , '04266241381' , 'erincongil@gmail.com' , 'mcbo' , '1');
INSERT INTO spve.empleado (id_empleado, id_cargo, id_persona, activo_empleado) VALUES ('2' , '1' ,'1' , '1');*/

--QUERY PARA CONSULTAR UN EMPLEADO
/*SELECT persona.nombre_persona, persona.apellido_persona, persona.tipo_persona, persona.numero_identificacion_persona, 
persona.telefono_persona, persona.email_persona, 
persona.direccion_persona, empleado.id_empleado, cargo.nombre_cargo 
FROM spve.persona INNER JOIN spve.empleado ON persona.id_persona = empleado.id_persona 
INNER JOIN spve.cargo ON empleado.id_cargo = cargo.id_cargo WHERE id_empleado = */

--QUERY PARA MODIFICAR UN EMPLEADO
/*UPDATE spve.persona
SET nombre_persona = 'luis', apellido_persona = 'rincon', tipo_persona = 'V', 
numero_identificacion_persona = '27491458', telefono_persona = '04162267935', 
email_persona = 'lrincongil31@gmail.com', direccion_persona = 'maracaibo'
WHERE id_persona = 1;
UPDATE spve.empleado SET contraseña = '12345', id_cargo = 3
WHERE id_persona = 1;*/


