insert into USUARIO values(1, 'Andre', 'ADMIN', '123');
insert into USUARIO values(2, 'Wisla', 'COORDENADOR', '123');
insert into USUARIO values(3, 'Daniel', 'USUARIO', '123');
insert into USUARIO values(4, 'Lucas', 'USUARIO', '123');

insert into RESERVA values(1, '01-01-2019', true,'201', 'PENDENTE', 1);
insert into RESERVA values(2, '02-01-2019', false,'204', 'PENDENTE', 1);
insert into RESERVA values(3, '02-03-2019', false, '201','CONFIRMADA', 2);
insert into RESERVA values(4, '05-03-2019', false, '203','PENDENTE', 3);
insert into RESERVA values(5, '06-07-2019', true, '206','PENDENTE', 2);
insert into RESERVA values(6, '07-01-2019', true,'201', 'CONFIRMADA', 1);
insert into RESERVA values(7, '08-01-2019', false,'204', 'PENDENTE', 1);
insert into RESERVA values(8, '09-03-2019', false, '201','PENDENTE', 2);
insert into RESERVA values(9, '10-03-2019', false, '203','CONFIRMADA', 3);
insert into RESERVA values(10, '11-07-2019', true, '206','CANCELADA', 2);

insert into SALA values(1, 'Sala de atendimento infantil', 'Setor de psicologia', 'A101', false);
insert into SALA values(2, 'Sala de atendimento infantil', 'Setor de psicologia', 'A102', false);
insert into SALA values(3, 'Sala de atendimento infantil', 'Setor de psicologia', 'A103', false);
