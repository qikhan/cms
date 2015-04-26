-- -----------
create database cms;
use cms;
create user 'khan'@'localhost' identified by password 'zaina';
grant all privileges on cms.* to 'khan'@'localhost' with grant option;
-- drop user 'khan'

create table user (

	user_name varchar(30) not null,
    password varchar(10) not null,
    email varchar(50) not null,
	
	CONSTRAINT pk_username PRIMARY KEY (user_name)
);

CREATE INDEX idx_user_email ON user (email);

insert into user values('qikhan', 'zaina', 'qikhan@gmail.com');
insert into user values('zaikhan', 'zaina', 'zaikhan@gmail.com');
insert into user values('zunkhan', 'zaina', 'zunkhan@gmail.com');

-- drop table user;

select * from user;



