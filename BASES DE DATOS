CREATE DATABASE IF NOT EXISTS centro_datos;
USE centro_datos;

CREATE TABLE IF NOT EXISTS servidores (
    id_servidores     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    energia_consumida DECIMAL(10,2) NOT NULL,
    potencia_maxima   DECIMAL(10,2) NOT NULL,
    temperatura       DECIMAL(5,2) NOT NULL,
    memoria_ram       INT NOT NULL,
    fecha             DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS calefaccion (
    id_calefaccion    INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    energia_consumida DECIMAL(10,2) NOT NULL,
    estado            VARCHAR(50) NOT NULL,
    temperatura       DECIMAL(5,2) NOT NULL,
    num_calefaccion   INT NOT NULL,
    fecha             DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS consumo_humano (
    id_consumo_humano        INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    litros_de_agua_consumida DECIMAL(10,2) NOT NULL,
    num_de_personas          INT NOT NULL,
    tipo_de_uso              VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS aire_acondicionado (
    id_aire_acondicionado INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    energia_consumida     DECIMAL(10,2) NOT NULL,
    estado                VARCHAR(50) NOT NULL,
    modo_funcionamiento   VARCHAR(50) NOT NULL,
    potencia              DECIMAL(10,2) NOT NULL,
    fecha                 DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS tratamiento_de_agua (
    id_tratamiento_de_agua   INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    litros_de_agua_consumida DECIMAL(10,2) NOT NULL,
    coste                    DECIMAL(10,2) NOT NULL,
    capacidad_deposito       DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS refrigeracion (
    id_refrigeracion         INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    litros_de_agua_consumida DECIMAL(10,2) NOT NULL,
    presion                  DECIMAL(5,2) NOT NULL,
    temperatura_refrigerante DECIMAL(5,2) NOT NULL,
    fecha                    DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS sensores (
    id_sensor     INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fecha         DATE NOT NULL,
    lugar         VARCHAR(100) NOT NULL,
    valor_medido  DECIMAL(10,2) NOT NULL,
    unidad_medida VARCHAR(20) NOT NULL,
    tipo_sensor   VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS emisiones (
    id_emisiones      INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    fecha             DATE NOT NULL,
    tipo_de_emision   VARCHAR(50) NOT NULL,
    huella_de_carbono DECIMAL(10,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS centro_de_datos (
    id_centro_de_datos INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_sensores        INT NOT NULL,
    id_emisiones       INT NOT NULL,
    localizacion       VARCHAR(100) NOT NULL,
    nombre             VARCHAR(100) NOT NULL,
    capacidad_maxima   DECIMAL(10,2) NOT NULL,
    superficie         DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_sensores)  REFERENCES sensores(id_sensor),
    FOREIGN KEY (id_emisiones) REFERENCES emisiones(id_emisiones)
);

CREATE TABLE IF NOT EXISTS agua (
    id_agua                  INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_tratamiento_de_agua   INT NOT NULL,
    id_consumo_humano        INT NOT NULL,
    id_centro_de_datos       INT NOT NULL,
    id_refrigeracion         INT NOT NULL,
    litros_de_agua           DECIMAL(10,2) NOT NULL,
    coste_total              DECIMAL(10,2) NOT NULL,
    calidad_agua             VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_tratamiento_de_agua) REFERENCES tratamiento_de_agua(id_tratamiento_de_agua),
    FOREIGN KEY (id_consumo_humano)      REFERENCES consumo_humano(id_consumo_humano),
    FOREIGN KEY (id_centro_de_datos)     REFERENCES centro_de_datos(id_centro_de_datos),
    FOREIGN KEY (id_refrigeracion)       REFERENCES refrigeracion(id_refrigeracion)
);

CREATE TABLE IF NOT EXISTS energia (
    id_energia            INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    id_calefaccion        INT NOT NULL,
    id_servidores         INT NOT NULL,
    id_aire_acondicionado INT NOT NULL,
    id_centro_de_datos    INT NOT NULL,
    consumo_total         DECIMAL(10,2) NOT NULL,
    coste_total           DECIMAL(10,2) NOT NULL,
    voltaje               DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_calefaccion)        REFERENCES calefaccion(id_calefaccion),
    FOREIGN KEY (id_servidores)         REFERENCES servidores(id_servidores),
    FOREIGN KEY (id_aire_acondicionado) REFERENCES aire_acondicionado(id_aire_acondicionado),
    FOREIGN KEY (id_centro_de_datos)    REFERENCES centro_de_datos(id_centro_de_datos)
);


-- =============================================
-- 1. CONSULTAS Y AGRUPAMIENTOS
-- ====================
-- Centros con coste de agua superior a la media (HAVING + GROUP BY)
SELECT cd.nombre,
       ROUND(SUM(ag.coste_total), 2) AS coste_agua
FROM agua ag
JOIN centro_de_datos cd ON ag.id_centro_de_datos = cd.id_centro_de_datos
GROUP BY cd.nombre
HAVING SUM(ag.coste_total) > (SELECT AVG(coste_total) FROM agua)
ORDER BY coste_agua DESC;

-- Sensores con valor alto UNION servidores con temperatura alta
SELECT 'Sensor' AS origen, lugar AS descripcion, valor_medido AS valor, fecha
FROM sensores
WHERE valor_medido > 80
UNION
SELECT 'Servidor', CAST(id_servidores AS CHAR), temperatura, fecha
FROM servidores
WHERE temperatura > 40
ORDER BY valor DESC;

-- Fechas con emisiones altas Y servidores calientes (INTERSECT equivalente)
SELECT fecha FROM emisiones
WHERE huella_de_carbono > 50
AND fecha IN (
    SELECT fecha FROM servidores WHERE temperatura > 40
);

-- Emisiones totales por tipo con HAVING
SELECT tipo_de_emision,
       ROUND(SUM(huella_de_carbono), 2) AS total_huella
FROM emisiones
GROUP BY tipo_de_emision
HAVING SUM(huella_de_carbono) > 100
ORDER BY total_huella DESC;

-- Días donde la huella de carbono total supera 130
SELECT fecha,
       ROUND(SUM(huella_de_carbono), 2) AS total_dia
FROM emisiones
GROUP BY fecha
HAVING SUM(huella_de_carbono) > 130
ORDER BY total_dia DESC;

-- Centros con consumo de energía superior a la media (HAVING + GROUP BY)
SELECT cd.nombre,
       ROUND(SUM(e.consumo_total), 2) AS consumo_energia
FROM energia e
JOIN centro_de_datos cd ON e.id_centro_de_datos = cd.id_centro_de_datos
GROUP BY cd.nombre
HAVING SUM(e.consumo_total) > (SELECT AVG(consumo_total) FROM energia)
ORDER BY consumo_energia DESC;

-- Aires acondicionados activos UNION calefacciones activas
SELECT 'Aire Acondicionado' AS tipo, estado, energia_consumida, fecha
FROM aire_acondicionado
WHERE estado = 'activo'
UNION
SELECT 'Calefaccion', estado, energia_consumida, fecha
FROM calefaccion
WHERE estado = 'activo'
ORDER BY energia_consumida DESC;

-- =============================================
-- 2. RELACIONES Y SUBCONSULTAS
-- ==============================================

-- LEFT JOIN: todos los centros aunque no tengan energía registrada
SELECT cd.nombre, cd.localizacion,
       ROUND(SUM(e.consumo_total), 2) AS energia_total,
       ROUND(SUM(e.coste_total), 2)   AS coste_total
FROM centro_de_datos cd
LEFT JOIN energia e ON e.id_centro_de_datos = cd.id_centro_de_datos
GROUP BY cd.nombre, cd.localizacion
ORDER BY energia_total DESC;

-- RIGHT JOIN: todas las emisiones aunque no tengan centro asociado
SELECT cd.nombre, cd.localizacion,
       em.tipo_de_emision, em.huella_de_carbono, em.fecha
FROM centro_de_datos cd
RIGHT JOIN emisiones em ON cd.id_emisiones = em.id_emisiones
ORDER BY em.huella_de_carbono DESC;

-- JOIN triple + subconsulta: agua por encima de la media con detalle completo
SELECT cd.nombre AS centro,
       ag.calidad_agua,
       ag.coste_total,
       r.presion,
       ch.num_de_personas
FROM agua ag
JOIN centro_de_datos cd ON ag.id_centro_de_datos = cd.id_centro_de_datos
JOIN refrigeracion r    ON ag.id_refrigeracion   = r.id_refrigeracion
JOIN consumo_humano ch  ON ag.id_consumo_humano  = ch.id_consumo_humano
WHERE ag.coste_total > (
    SELECT AVG(coste_total) FROM agua
)
ORDER BY ag.coste_total DESC;

-- Subconsulta correlacionada: sensores por encima de la media de su tipo
SELECT s.tipo_sensor, s.lugar, s.valor_medido, s.fecha
FROM sensores s
WHERE s.valor_medido > (
    SELECT AVG(s2.valor_medido)
    FROM sensores s2
    WHERE s2.tipo_sensor = s.tipo_sensor
)
ORDER BY s.tipo_sensor, s.valor_medido DESC;

-- Subconsulta en FROM: centros con energía por encima de la media global
SELECT nombre, energia_total
FROM (
    SELECT cd.nombre,
           ROUND(SUM(e.consumo_total), 2) AS energia_total
    FROM energia e
    JOIN centro_de_datos cd ON e.id_centro_de_datos = cd.id_centro_de_datos
    GROUP BY cd.nombre
) sub
WHERE sub.energia_total > (SELECT AVG(consumo_total) FROM energia)
ORDER BY energia_total DESC;

-- Servidores que superan la temperatura media
SELECT id_servidores, temperatura, fecha
FROM servidores
WHERE temperatura > (SELECT AVG(temperatura) FROM servidores)
ORDER BY temperatura DESC;

-- LEFT JOIN: todos los centros aunque no tengan agua registrada
SELECT cd.nombre, cd.localizacion,
       ROUND(SUM(ag.litros_de_agua), 2) AS litros_totales,
       ROUND(SUM(ag.coste_total), 2)    AS coste_total
FROM centro_de_datos cd
LEFT JOIN agua ag ON ag.id_centro_de_datos = cd.id_centro_de_datos
GROUP BY cd.nombre, cd.localizacion
ORDER BY litros_totales DESC;

-- Subconsulta correlacionada: servidores con memoria RAM por encima de la media de su fecha
SELECT id_servidores, fecha, memoria_ram, energia_consumida
FROM servidores s
WHERE memoria_ram > (
    SELECT AVG(s2.memoria_ram)
    FROM servidores s2
    WHERE s2.fecha = s.fecha
)
ORDER BY fecha, memoria_ram DESC;
-- =============
-- 3. MANIPULACIÓN DE DATOS E INTEGRIDAD
-- =======================================

-- INSERT masivo desde consulta
INSERT INTO emisiones (fecha, tipo_de_emision, huella_de_carbono)
SELECT fecha, 'CO2_estimado',
       ROUND(energia_consumida * 0.233, 2)
FROM servidores
WHERE energia_consumida > 400;

-- UPDATE con subconsulta: reducir coste en agua con litros por encima de la media
UPDATE agua
SET coste_total = ROUND(coste_total * 0.95, 2)
WHERE litros_de_agua > (
    SELECT AVG(litros_de_agua) FROM (SELECT litros_de_agua FROM agua) tmp
);

-- DELETE con subconsulta: eliminar emisiones de fechas sin servidores activos
DELETE FROM emisiones
WHERE fecha NOT IN (
    SELECT DISTINCT fecha FROM servidores
);

-- Clasificar servidores por nivel de consumo (CASE)
SELECT id_servidores, energia_consumida,
    CASE
        WHEN energia_consumida >= 450 THEN 'Alto'
        WHEN energia_consumida >= 300 THEN 'Medio'
        ELSE                               'Bajo'
    END AS nivel_consumo
FROM servidores
ORDER BY energia_consumida DESC;

-- INSERT masivo: registrar emisiones estimadas desde aires acondicionados con alto consumo
INSERT INTO emisiones (fecha, tipo_de_emision, huella_de_carbono)
SELECT fecha, 'CO2_aire_acondicionado',
       ROUND(energia_consumida * 0.185, 2)
FROM aire_acondicionado
WHERE energia_consumida > 150;

-- Clasificar refrigeraciones por nivel de presión (CASE)
SELECT id_refrigeracion, presion, temperatura_refrigerante,
    CASE
        WHEN presion >= 8  THEN 'Alta'
        WHEN presion >= 4  THEN 'Media'
        ELSE                    'Baja'
    END AS nivel_presion
FROM refrigeracion
ORDER BY presion DESC;

-- Script de edición masiva: actualizar temperatura de servidores con validación
UPDATE servidores
SET temperatura = ROUND(temperatura * 0.95, 2)
WHERE energia_consumida > (
    SELECT AVG(energia_consumida) FROM (SELECT energia_consumida FROM servidores) tmp
)
AND temperatura > (
    SELECT AVG(temperatura) FROM (SELECT temperatura FROM servidores) tmp2
);

-- Script de carga masiva con validación de duplicados
INSERT INTO emisiones (fecha, tipo_de_emision, huella_de_carbono)
SELECT * FROM (
    SELECT '2024-02-01', 'CO2', 130.5 UNION ALL
    SELECT '2024-02-01', 'NOx',   8.7 UNION ALL
    SELECT '2024-02-02', 'CO2', 145.2 UNION ALL
    SELECT '2024-02-02', 'CH4',   4.3
) AS nuevas_emisiones (fecha, tipo_de_emision, huella_de_carbono)
WHERE NOT EXISTS (
    SELECT 1 FROM emisiones e
    WHERE e.fecha = nuevas_emisiones.fecha
      AND e.tipo_de_emision = nuevas_emisiones.tipo_de_emision
);
-- ================================================
-- 4. Transacciones, Bloqueos y Optimización 
-- ====================================

--  COMMIT
UPDATE servidores
SET temperatura = temperatura - 2
WHERE temperatura > 45;
COMMIT;


--  ROLLBACK
DELETE FROM emisiones
WHERE huella_de_carbono < 5;
ROLLBACK;

-- Transacción completa con savepoints
START TRANSACTION;

    INSERT INTO sensores (fecha, lugar, valor_medido, unidad_medida, tipo_sensor)
    VALUES (CURDATE(), 'Sala A', 75.50, 'kW', 'energia');

    SAVEPOINT sp1;

    UPDATE servidores
    SET energia_consumida = energia_consumida * 1.05
    WHERE temperatura > 45;

    SAVEPOINT sp2;

    DELETE FROM emisiones
    WHERE huella_de_carbono < 5;

    -- Si falla el DELETE:  ROLLBACK TO sp2;
    -- Si falla el UPDATE:  ROLLBACK TO sp1;
    -- Si falla todo:       ROLLBACK;

COMMIT;

-- Consulta optimizada con CASE y filtro por rango de fechas
SELECT id_servidores, fecha, energia_consumida,
    CASE
        WHEN energia_consumida >= 450 THEN 'Alto'
        WHEN energia_consumida >= 300 THEN 'Medio'
        ELSE                               'Bajo'
    END AS nivel_consumo
FROM servidores
WHERE fecha BETWEEN '2024-01-01' AND '2024-12-31'
ORDER BY energia_consumida DESC;

-- Servidores cuya energía supera la media de su propio mes
SELECT id_servidores, fecha, energia_consumida
FROM servidores s
WHERE energia_consumida > (
    SELECT AVG(s2.energia_consumida)
    FROM servidores s2
    WHERE MONTH(s2.fecha) = MONTH(s.fecha)
      AND YEAR(s2.fecha)  = YEAR(s.fecha)
)
ORDER BY fecha, energia_consumida DESC;

-- ============================================================
-- 5. ÍNDICES Y OPTIMIZACIÓN
-- ==============================================

-- Índice para acelerar búsquedas de servidores por fecha y temperatura
CREATE INDEX idx_servidores_fecha_temp 
ON servidores(fecha, temperatura);

-- Índice para acelerar consultas de emisiones por tipo y fecha
CREATE INDEX idx_emisiones_tipo_fecha 
ON emisiones(tipo_de_emision, fecha);

-- Índice para mejorar JOINs entre agua y centro_de_datos
CREATE INDEX idx_agua_centro 
ON agua(id_centro_de_datos);
