CREATE TABLE ENTITY (
   id int(10) NOT NULL AUTO_INCREMENT,
   attribute varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);
CREATE TABLE CARD (
   id int(10) NOT NULL AUTO_INCREMENT,
   slsp int(45) NOT NULL,
   autokarta int(45) NOT NULL,
   PRIMARY KEY (`id`)
);