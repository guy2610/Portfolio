--a
drop table running;
drop table votes;
drop table  election;
drop table party;
drop table city;

create table if not exists election(
	edate date primary key,
	kno int
);
create table if not exists party(
	pname char(20) primary key,
	symbol char(5)
);
create table if not exists running(
	edate	date	,
	pname	char(20),
	child	numeric(5,0),
	totalvotes	int  default 0 not null,
	primary key(edate,pname),
	foreign key(edate) references election,
	foreign key(pname) references party
);
create table if not exists city(
	cid	numeric(5,0) primary key,
	cname varchar(20),
	region varchar(20)
);
create table if not exists votes(
	cid	numeric(5,0),
	edate	date,
	pname	char(20),
	nofvotes	int not null ,
	primary key(cid,pname,edate),
	foreign key(cid) references city,
	foreign key(edate) references election,
	foreign key(pname) references party
	
);


--b
create or replace function trigf1() returns trigger as $$
declare
totalvotesPrev int;
begin
	select sum(nofvotes) into totalvotesPrev
	from votes
	where edate=new.edate and pname=new.pname;
	if(TG_OP='INSERT' or TG_OP='UPDATE')then
		update running 
		set totalvotes=totalvotesPrev
		where edate=new.edate and pname=new.pname;
	end if;
	
	return null;
end;
$$language plpgsql;


create trigger T1
after insert or update on votes
for each row 
execute procedure trigf1();




--c
insert into election 
	values('9.4.2019', 1),
	('17.9.2019' ,2),
	('2.3.2020' ,3),
	('23.3.2021' ,4),
	('1.11.2022' ,5);

insert into party
	values('nature party' ,'np'),
	('science group' ,'sg'),
	('life party' ,'lp'),
	('art group' ,'ag'),
	('lost group' ,'lg');

insert into running
	values('9.4.2019', 'nature party', 12345),
	('9.4.2019','life party' ,54321),
	('9.4.2019', 'lost group', 34567),
	('17.9.2019', 'lost group', 76543),
	('17.9.2019', 'art group', 67890),
	('2.3.2020', 'science group', 90876),
	('2.3.2020','nature party' ,55555),
	('2.3.2020' ,'life party' ,54321);



insert into city
	values(22 ,'ryde end' ,'north'),
	(77, 'east strat' ,'south'),
	(33, 'grandetu' ,'center'),
	(88, 'royalpre', 'hills'),
	(11, 'carlpa' ,'hills'),
	(44, 'lommont', 'north'),
	(66, 'grand sen', 'south'),
	(99, 'kingo haven', 'hills'),
	(55, 'el munds' ,'south');
	

insert into votes
	values(22, '2.3.2020', 'nature party', 100),
	(22, '2.3.2020', 'science group', 30),
	(22 ,'2.3.2020', 'life party', 500),
	(77 ,'2.3.2020', 'nature party', 300),
	(77 ,'2.3.2020', 'science group', 150),
	(77 ,'2.3.2020', 'life party', 25),
	(33 ,'2.3.2020', 'nature party', 13),
	(33 ,'2.3.2020', 'science group', 740),
	(33 ,'2.3.2020', 'life party', 670);
	
	
--d1
--select pname,nofvotes
--from city natural join votes
--where cname='ryde end' and edate='2.3.2020';

--d2
--select pname,region, sum(nofvotes)
--from city natural join votes natural join election
--where kno=3 
--group by pname,region;

--d3
--select cname,region
--from city
--except
--select cname,region
--from city natural join votes
--where pname='life party';

--d4
--select edate,kno
--from election natural join running
--group by edate,kno
--having(count (pname)>=all(
--					select count (pname)
--					from election natural join running
--					group by edate,kno));

--d5
--select pname,sum(totalvotes)
--from election natural join running
--where  pname in(select pname
--						from election natural join running
--						where kno=3
--						except
--						select pname
--						from election natural join votes natural join city
--						where kno=3 and region='hills'
--						)
--group by pname
--having(sum(totalvotes)<=all(select sum(totalvotes)
--from election natural join running
--where pname in(select pname
--						from election natural join running
--						where kno=3
--						except
--						select pname
--						from election natural join votes natural join city
--						where kno=3 and region='hills'
--						)
--group by pname));


--d6
--select pname
--from running natural join election
--where kno=3 and totalvotes in(select max(totalvotes)
--				 			from running natural join election
--							where kno=3 and totalvotes not in(select max(totalvotes)
--													from running natural join election
--												   	where kno=3));


--d7
--select distinct r1.pname,r2.pname 
--from running as r1 , running as r2 
--where r1.pname>r2.pname and r1.pname not in(select pname from running where edate not in(select edate from running where pname=r2.pname))
--										and r2.pname not in(select pname from running where edate not in(select edate from running where pname=r1.pname));






