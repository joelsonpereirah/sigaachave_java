insert into USUARIO values(1,'12147642401','André Filho', 2, '123');
insert into USUARIO values(2,'12147642402','Wisla Argolo', 1, '123');
insert into USUARIO values(3,'12147642403','Daniel Leiros', 0, '123');
insert into USUARIO values(4,'12147642404','Bryan Brito', 0, '123');

insert into SALA values(1, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A101', TRUE);
insert into SALA values(2, 'Sala de atendimento infantil', 'Setor de Psicologia, UFRN', 'A102', FALSE);
insert into SALA values(3, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A103', TRUE);
insert into SALA values(4, 'Sala de atendimento adulto', 'Setor de Psicologia, UFRN', 'A104', FALSE);

insert into RESERVA values(1, 2, 16, 1, FALSE, 'A101', 1, 1);
insert into RESERVA values(2, 2, 17, 2, FALSE, 'A102', 0, 2);
insert into RESERVA values(3, 3, 9, null, FALSE, 'A103', 1, 2);
insert into RESERVA values(4, 3, 10, null, FALSE, 'A103', 2, 3);