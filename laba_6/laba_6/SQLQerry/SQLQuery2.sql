--CREATE DATABASE LIBRARY

USE LIBRARY

drop table AUTHOR
drop table BOOK

CREATE TABLE AUTHOR (
NAME nvarchar(50) not null  PRIMARY KEY,
COUNTRY nvarchar(50) not null
)

CREATE TABLE BOOK (
NAME_BOOK nvarchar(50) not null,
NAME_AUTHOR nvarchar(50) not null FOREIGN KEY REFERENCES AUTHOR(NAME),
YEAR_OF_RELEASE int not null,
PUBLISHING_HOUSE nvarchar(50) not null
)

INSERT INTO AUTHOR(NAME, COUNTRY)
	VALUES
		('author_1', 'country_1'),
		('author_2', 'country_2'),
		('author_3', 'country_3'),
		('author_4', 'country_4'),
		('author_5', 'country_5');


INSERT INTO BOOK(NAME_BOOK, NAME_AUTHOR, YEAR_OF_RELEASE, PUBLISHING_HOUSE)
	VALUES
		('book_1','author_1', 2001, 'publish_1'),
		('book_2','author_2', 2002, 'publish_2'),
		('book_3','author_3', 2003, 'publish_3'),
		('book_4','author_4', 2004, 'publish_4'),
		('book_5','author_4', 2005, 'publish_5'),
		('book_6','author_4', 2006, 'publish_5'),
		('book_7','author_5', 2005, 'publish_5'),
		('book_8','author_3', 2007, 'publish_5'),
		('book_9','author_3', 2009, 'publish_5'),
		('book_10','author_5', 2006, 'publish_5');
