CREATE TABLE ENTITY (
   id int(10) NOT NULL AUTO_INCREMENT,
   attribute varchar(45) NOT NULL,
   PRIMARY KEY (`id`)
);
CREATE TABLE CARD (
   id int(10) NOT NULL AUTO_INCREMENT,
   date date NOT NULL,
   slsp float(45) NOT NULL,
   autokarta float(45) NOT NULL,
   PRIMARY KEY (`id`)
);