insert into albums(naam, artiestid)
values ('test', (select id from artiesten where naam = 'test'));
insert into tracks(naam, tijd, albumid)
values ('test', '01:10:0',(select id from albums where naam = 'test'));