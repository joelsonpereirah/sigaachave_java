insert into USUARIO values(1,'12147642401','André Filho', 'ADMIN', '123');
insert into USUARIO values(2,'12147642402','Wisla Argolo', 'COORDENADOR', '123');
insert into USUARIO values(3,'12147642403','Daniel Leiros', 'BOLSISTA', '123');
insert into USUARIO values(4,'12147642404','Bryan Brito', 'BOLSISTA', '123');

insert into SALA values(1, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A101', TRUE);
insert into SALA values(2, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A102', FALSE);
insert into SALA values(3, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A103', TRUE);
insert into SALA values(4, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A104', FALSE);

insert into RESERVA values(1, '2004-01-22', 8, null, false, 'A101', 'PENDENTE', 1);
insert into RESERVA values(2, '2004-01-22', 9, null, false, 'A101', 'CANCELADA', 1);
insert into RESERVA values(3, '2004-01-23', 16, null, false, 'A101', 'CONFIRMADA', 1);
insert into RESERVA values(4, '2004-01-24', 17, null, false, 'A101', 'CONFIRMADA', 1);