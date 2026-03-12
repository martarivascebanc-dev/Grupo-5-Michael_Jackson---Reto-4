USE centro_datos;
set foreign_key_checks = 0;
-- Tablas independientes primero
INSERT INTO servidores (energia_consumida, potencia_maxima, temperatura, memoria_ram, fecha) VALUES
(320.5, 500.0, 42.3, 128, '2024-01-15'),
(289.0, 500.0, 39.8,  64, '2024-01-15'),
(415.2, 600.0, 45.1, 256, '2024-01-16'),
(198.7, 400.0, 37.5,  64, '2024-01-16'),
(502.3, 600.0, 48.9, 512, '2024-01-17'),
(310.0, 500.0, 41.0, 128, '2024-01-17'),
(275.4, 400.0, 38.2,  64, '2024-01-18'),
(489.1, 600.0, 47.6, 256, '2024-01-18');

INSERT INTO calefaccion (energia_consumida, estado, temperatura, num_calefaccion, fecha) VALUES
(45.2, 'activo',   21.5, 1, '2024-01-15'),
(38.7, 'activo',   20.8, 2, '2024-01-15'),
(0.0,  'inactivo', 18.0, 3, '2024-01-16'),
(52.1, 'activo',   22.3, 1, '2024-01-16'),
(41.0, 'activo',   21.0, 2, '2024-01-17'),
(0.0,  'inactivo', 17.5, 3, '2024-01-17'),
(49.8, 'activo',   22.0, 1, '2024-01-18'),
(36.3, 'activo',   20.5, 2, '2024-01-18');

INSERT INTO consumo_humano (litros_de_agua_consumida, num_de_personas, tipo_de_uso) VALUES
(120.0, 15, 'sanitario'),
(45.5,  15, 'limpieza'),
(85.0,  20, 'sanitario'),
(30.0,  20, 'limpieza'),
(95.5,  18, 'sanitario'),
(40.0,  18, 'limpieza'),
(110.0, 22, 'sanitario'),
(55.0,  22, 'cocina');

INSERT INTO aire_acondicionado (energia_consumida, estado, modo_funcionamiento, potencia, fecha) VALUES
(180.5, 'activo',   'enfriamiento', 250.0, '2024-01-15'),
(165.3, 'activo',   'enfriamiento', 250.0, '2024-01-15'),
(0.0,   'inactivo', 'standby',       50.0, '2024-01-16'),
(210.7, 'activo',   'enfriamiento', 300.0, '2024-01-16'),
(195.2, 'activo',   'ventilacion',  200.0, '2024-01-17'),
(175.8, 'activo',   'enfriamiento', 250.0, '2024-01-17'),
(0.0,   'inactivo', 'standby',       50.0, '2024-01-18'),
(220.4, 'activo',   'enfriamiento', 300.0, '2024-01-18');

INSERT INTO tratamiento_de_agua (litros_de_agua_consumida, coste, capacidad_deposito) VALUES
(500.0,  85.50, 2000.0),
(650.0, 110.25, 2000.0),
(420.0,  71.40, 1500.0),
(780.0, 132.60, 2000.0),
(530.0,  90.10, 2000.0),
(470.0,  79.90, 1500.0),
(610.0, 103.70, 2000.0),
(560.0,  95.20, 2000.0);

INSERT INTO refrigeracion (litros_de_agua_consumida, presion, temperatura_refrigerante, fecha) VALUES
(350.0, 2.5, 12.0, '2024-01-15'),
(420.0, 2.8, 10.5, '2024-01-15'),
(280.0, 2.2,  8.0, '2024-01-16'),
(510.0, 3.1, 14.2, '2024-01-16'),
(390.0, 2.6, 11.8, '2024-01-17'),
(330.0, 2.4,  9.5, '2024-01-17'),
(460.0, 2.9, 13.0, '2024-01-18'),
(400.0, 2.7, 11.0, '2024-01-18');

-- Sensores y emisiones ANTES que centro_de_datos (sin id_centro_de_datos)
INSERT INTO sensores (fecha, lugar, valor_medido, unidad_medida, tipo_sensor) VALUES
('2024-01-15', 'Sala Servidores A',  42.3, '°C',  'temperatura'),
('2024-01-15', 'Sala Servidores B',  39.8, '°C',  'temperatura'),
('2024-01-15', 'Pasillo Principal',   2.5, 'bar', 'presion'),
('2024-01-16', 'Sala Servidores A',  45.1, '°C',  'temperatura'),
('2024-01-16', 'CPD Exterior',       65.0, '%',   'humedad'),
('2024-01-16', 'Sala Refrigeracion',  3.1, 'bar', 'presion'),
('2024-01-17', 'Sala Servidores B',  38.2, '°C',  'temperatura'),
('2024-01-17', 'CPD Exterior',       70.0, '%',   'humedad'),
('2024-01-17', 'Acceso Principal',  500.0, 'lux', 'luminosidad'),
('2024-01-18', 'Sala Servidores A',  47.6, '°C',  'temperatura');

INSERT INTO emisiones (fecha, tipo_de_emision, huella_de_carbono) VALUES
('2024-01-15', 'CO2', 125.4),
('2024-01-15', 'NOx',   8.2),
('2024-01-15', 'CH4',   3.5),
('2024-01-16', 'CO2', 142.7),
('2024-01-16', 'NOx',   9.1),
('2024-01-17', 'CO2', 118.3),
('2024-01-17', 'CH4',   4.0),
('2024-01-17', 'NOx',   7.8),
('2024-01-18', 'CO2', 155.9),
('2024-01-18', 'NOx',  10.3);

-- Centro de datos apunta a sensores y emisiones
INSERT INTO centro_de_datos (id_sensores, id_emisiones, localizacion, nombre, capacidad_maxima, superficie) VALUES
(1, 1, 'Madrid, España',    'CDM-Madrid-01',    5000.0, 2500.0),
(4, 4, 'Barcelona, España', 'CDM-Barcelona-01', 3500.0, 1800.0),
(8, 6, 'Valencia, España',  'CDM-Valencia-01',  2000.0, 1200.0);

-- Tablas con claves foráneas
INSERT INTO agua (id_tratamiento_de_agua, id_consumo_humano, id_centro_de_datos, id_refrigeracion, litros_de_agua, coste_total, calidad_agua) VALUES
(1, 1, 1, 1,  970.0, 145.20, 'potable'),
(2, 2, 1, 2, 1115.5, 188.75, 'potable'),
(3, 3, 2, 3,  780.0, 121.40, 'reciclada'),
(4, 4, 2, 4, 1320.0, 224.60, 'potable'),
(5, 5, 3, 5, 1014.5, 172.30, 'potable'),
(6, 6, 3, 6,  839.0, 132.90, 'reciclada'),
(7, 7, 1, 7, 1175.0, 199.50, 'potable'),
(8, 8, 2, 8, 1015.0, 172.40, 'potable');

INSERT INTO energia (id_calefaccion, id_servidores, id_aire_acondicionado, id_centro_de_datos, consumo_total, coste_total, voltaje) VALUES
(1, 1, 1, 1,  546.2,  87.39, 220.0),
(2, 2, 2, 1,  493.0,  78.88, 220.0),
(3, 3, 3, 2,  415.2,  66.43, 230.0),
(4, 4, 4, 2,  764.1, 122.26, 230.0),
(5, 5, 5, 3,  737.5, 118.00, 220.0),
(6, 6, 6, 3,  521.2,  83.39, 220.0),
(7, 7, 7, 1,  769.3, 123.09, 230.0),
(8, 8, 8, 2,  745.8, 119.33, 230.0);
set foreign_key_checks = 1;
