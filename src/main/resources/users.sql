CREATE TABLE users (
   userID int(11) NOT NULL,
   username VARCHAR(45) NOT NULL ,
   password VARCHAR(45) NOT NULL ,
   account_non_locked TINYINT NOT NULL DEFAULT 1 ,
   PRIMARY KEY (username)
);

