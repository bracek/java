CREATE TABLE  contacts (
  id int NOT NULL ,
  name varchar(45) NOT NULL,
  address varchar(45) DEFAULT NULL,
  gender char(1) DEFAULT 'M',
  dob date DEFAULT NULL,
  email varchar(45) DEFAULT NULL,
  mobile varchar(15) DEFAULT NULL,
  phone varchar(15) DEFAULT NULL
)

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE contacts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.contacts_id_seq OWNER TO postgres;


CREATE TABLE ENTITY (
   entity_id int,
   attribute varchar(45) NOT NULL
 );

CREATE SEQUENCE hibernate_sequence START WITH 1 INCREMENT BY 1 NOCYCLE;
CREATE SEQUENCE entity_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE public.entity_id_seq OWNER TO postgres;