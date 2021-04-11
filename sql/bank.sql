create table user (
 id  int(3) NOT NULL AUTO_INCREMENT,
 name varchar(120) NOT NULL,
 username varchar(220) NOT NULL,
 password varchar(120),
 PRIMARY KEY (id)
);

insert into user (name, username, password, created_date) values ("samarth", "ha07", "ha07",sysdate());
