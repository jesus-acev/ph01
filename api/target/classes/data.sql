-- Inicializa los enums

INSERT INTO tipo_calificacion(tca_id, tca_nombre) VALUES (1, 'ALOJAMIENTO');

INSERT INTO estado_reserva(ers_id, ers_nombre) VALUES (1, 'PAGADA');
INSERT INTO estado_reserva(ers_id, ers_nombre) VALUES (2, 'CANCELADA');
INSERT INTO estado_reserva(ers_id, ers_nombre) VALUES (3, 'INICIADA');
INSERT INTO estado_reserva(ers_id, ers_nombre) VALUES (4, 'FINALIZADA');

INSERT INTO tipo_comision(tco_id, tco_nombre) VALUES (1, 'PUBLICACION');
INSERT INTO tipo_comision(tco_id, tco_nombre) VALUES (2, 'ACTUALIZACION');
INSERT INTO tipo_comision(tco_id, tco_nombre) VALUES (3, 'RESERVA');
INSERT INTO tipo_comision(tco_id, tco_nombre) VALUES (4, 'CANCELACION');
INSERT INTO tipo_comision(tco_id, tco_nombre) VALUES (5, 'PAGO');

INSERT INTO role(rol_id, rol_nombre) VALUES (1, 'ROLE_ANFITRION');
INSERT INTO role(rol_id, rol_nombre) VALUES (2, 'ROLE_HUESPED');

