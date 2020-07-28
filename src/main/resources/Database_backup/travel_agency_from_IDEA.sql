create schema if not exists bustravelagency collate utf8_unicode_ci;

create table if not exists defrayal
(
	id_Defrayal int not null,
	Date_of_payment varchar(10) null,
	Id_Tour int not null,
	Count decimal(7,2) null,
	Payment_percentage int default 0 null,
	Id_User int not null,
	id_Discount int default 1 not null,
	Annotation varchar(225) null,
	primary key (id_Defrayal, Id_Tour, Id_User, id_Discount),
	constraint idPayment_UNIQUE
		unique (id_Defrayal)
);

create table if not exists discount
(
	id_Discount int not null,
	Size_of_discount int null,
	constraint Size_of_discount_UNIQUE
		unique (Size_of_discount),
	constraint id_Discount_UNIQUE
		unique (id_Discount)
);

alter table discount
	add primary key (id_Discount);

create table if not exists hotel
(
	id_Hotel int not null,
	Title varchar(45) charset utf8 not null,
	country varchar(255) not null,
	City varchar(255) not null,
	Stars int not null,
	Free_rooms int not null,
	Nutrition int not null,
	Min_price_per_room decimal(7,2) null,
	Url_wallpaper varchar(255) null,
	primary key (id_Hotel, Nutrition)
);

create table if not exists nutrition
(
	id_Nutrition int not null
		primary key,
	Type varchar(45) charset utf8 null
);

create table if not exists tours
(
	id_Tour int not null,
	Title varchar(55) not null,
	Price decimal(7,2) not null,
	Type int not null,
	Hot_tour tinyint(1) default 0 not null,
	Number_of_places int not null,
	Date_start varchar(10) not null,
	Date_end varchar(10) not null,
	id_Discount int not null,
	id_Hotel int not null,
	Description text null,
	Url_wallpaper varchar(225) null,
	primary key (id_Tour, Type, id_Discount, id_Hotel),
	constraint idTours_UNIQUE
		unique (id_Tour)
);

create table if not exists typeoftour
(
	id_TypeOfTour int not null,
	TypeOfTour varchar(45) not null,
	constraint TypeOfTour_UNIQUE
		unique (TypeOfTour),
	constraint idTypeOfTour_UNIQUE
		unique (id_TypeOfTour)
);

alter table typeoftour
	add primary key (id_TypeOfTour);

create table if not exists users
(
	id_User int not null,
	Login varchar(45) charset utf8 not null,
	Password varchar(255) not null,
	Email varchar(50) null,
	Firstname varchar(45) charset utf8 not null,
	Lastname varchar(45) charset utf8 not null,
	Phone varchar(15) null,
	id_Discount int not null,
	Level_access tinyint(1) null,
	primary key (id_User, id_Discount),
	constraint idClients_UNIQUE
		unique (id_User),
	constraint login_UNIQUE
		unique (Login)
);

