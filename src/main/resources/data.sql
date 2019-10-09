insert into USUARIO values(1,'12147642401','André Filho', 'ADMIN', '123');
insert into USUARIO values(2,'12147642402','Wisla Argolo', 'COORDENADOR', '123');
insert into USUARIO values(3,'12147642403','Daniel Leiros', 'BOLSISTA', '123');
insert into USUARIO values(4,'12147642404','Bryan Brito', 'BOLSISTA', '123');

insert into SALA values(1, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A101', TRUE);
insert into SALA values(2, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A102', FALSE);
insert into SALA values(3, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A103', TRUE);
insert into SALA values(4, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A104', FALSE);

insert into RESERVA values(1, '2004-01-22', 8, null, false, 'A101', 'PENDENTE', 1);
insert into RESERVA values(2, '2004-01-22', 9, null, false, 'A101', 'CANCELADA', 2);
insert into RESERVA values(3, '2004-01-23', 16, 2, false, 'A101', 'CONFIRMADA', 3);
insert into RESERVA values(4, '2004-01-24', 17, 1, false, 'A101', 'CONFIRMADA', 1);

insert into CHAMADO values(1, 'Lampada queimada', null, 'A101', 'PENDENTE', 3);
insert into CHAMADO values(2, 'Goteira', 1, 'A102', 'EM_EXECUCAO', 2);
insert into CHAMADO values(3, 'Ar com problema', 2, 'A103', 'CONFIRMADO', 4);
insert into CHAMADO values(4, 'Lampada queimada', 1, 'A104', 'CANCELADO', 1);
