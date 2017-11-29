create table access (
	id int not null,
	apikey varchar(500) not null, 
	apikeysecret varchar(500) not null, 
	accesstoken varchar(500) not null, 
	accesstokensecret varchar(500) not null,
	PRIMARY KEY (id,apikey, apikeysecret, accesstoken)
);
