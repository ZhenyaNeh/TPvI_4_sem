use LIBRARY

drop table USERS
go
create table USERS
(
	ID int identity(1, 1) Primary key,
	USER_LOG nvarchar(50) not null,
	USER_PAS nvarchar(50) not null,
	USER_ROL nvarchar(50) not null,
)

create table PEOPLE
(
	ID int identity(1, 1) Primary key,
	PEOPLE nvarchar(50) not null,
	PEOPLE_COUNTRY nvarchar(50) not null,
	PEOPLE_COUNT nvarchar(50) not null,
)
go
insert into USERS (USER_LOG, USER_PAS, USER_ROL)
	values
		(N'admin', N'1234', N'admin'),
		(N'user1', N'1234', N'user'),
		(N'user2', N'1234', N'user')

insert into PEOPLE (PEOPLE, PEOPLE_COUNTRY, PEOPLE_COUNT)
	values
		(N'Russuans', N'Russia', N'80%'),
		(N'Ukrains', N'Russia', N'3%'),
		(N'Tatars', N'Russia', N'3.8%'),
		(N'Chuvash', N'Russia', N'1.2%'),
		(N'Bellarusians', N'Russia', N'0.8%')