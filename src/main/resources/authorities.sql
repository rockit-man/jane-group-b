CREATE TABLE authorities (
user_auth_id int(11) NOT NULL AUTO_INCREMENT,
username VARCHAR(45) NOT NULL,
authority VARCHAR(45) NOT NULL,
primary key (user_role_id),
foreign key (username) references users (username)
constraint fk_username_auth foreign key (username)  references users (username)
);