--drop sequence seq_authority;
--drop sequence seq_code_table;
--drop sequence seq_group_authority;
--drop sequence seq_group_member;
--drop sequence seq_groups;
--drop sequence seq_users;

--SET CLIENT_ENCODING TO 'UTF8';

truncate table code_table cascade;
truncate table groups cascade;
truncate table projects_users cascade;
truncate table projects cascade;
truncate table users cascade;

INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (1,'LEVELS','BEGINNER','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (2,'LEVELS','ELEMENTARY','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (3,'LEVELS','LOWER_INTERMEDIATE','',2);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (4,'LEVELS','INTERMEDIATE','',3);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (5,'LEVELS','UPPER_INTERMEDIATE','',4);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (6,'LEVELS','ADVANCED','',5);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (7,'AUTHORITIES','ROLE_USER','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (8,'AUTHORITIES','ROLE_GROUP_MANAGER','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (9,'AUTHORITIES','ROLE_ADMIN','',2);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (10,'ANSWER_TYPES','LEVELS','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (11,'ANSWER_TYPES','BOOLEAN','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (12,'ANSWER_TYPES','TIME_INTERVAL','',2);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (13,'BOOLEAN','NO','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (14,'BOOLEAN','YES','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (15,'TIME_INTERVAL','LESS_THAN_ONE_MONTH','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (16,'TIME_INTERVAL','MAX_2_MONTHs','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (17,'TIME_INTERVAL','MAX_3_MONTHs','',2);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (18,'TIME_INTERVAL','MAX_6_MONTHs','',3);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (19,'TIME_INTERVAL','MAX_9_MONTHs','',4);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (20,'TIME_INTERVAL','MAX_12_MONTHs','',5);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (21,'TIME_INTERVAL','MORE_THAN_12_MONTHs','',6);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (22,'PROJECT_STATE','ONGOING','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (23,'PROJECT_STATE','PLANNED','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (24,'PROJECT_STATE','CONFIRMED','',2);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (25,'PROJECT_STATE','OBSOLETE','',3);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (26,'ASSIGNMENT_STATE','PROPOSED','',0);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (27,'ASSIGNMENT_STATE','CONFIRMED','',1);
INSERT INTO code_table (code_table_id,group_code,code,description,index) VALUES (28,'ASSIGNMENT_STATE','ASSIGNED','',2);

--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (4,'happoka','Kari', 'Happonen', 'kari@ixonos.com', '0907111233', 'Helsinki', 'CHIEF_EXECUTIVE_OFFICER', 'a70446a88bccf2c9afe98900e89167abb918065f1fe3c1a14fec652e3381f208',true,null,null);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (3,'klimcro','Roman', 'Klimcik', 'roman@ixonos.com', '0907111233', 'Košice', 'JAVA SITE_MANAGERS','c746059ec3d56536ff9216711d5abd4c5023856bb4c1c74c6fcf5cf68e110926',true,null,4);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (1,'hustasl','Slavomir', 'Hustaty', 'hustaty@ixonos.com', '0907111233', 'Košice', 'GROUP_MANAGERS','0d7da7d8eef7a89777ed2cfc8f5dd546c25b9075141ba6bc1fed654ee8172624',true,null,3);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (6,'gregama','Marian', 'Grega', 'grega@ixonos.com', '0907111233', 'Košice', 'GROUP_MANAGERS','ca630db59da45126beb4faf08af909d8c8819ae5a0ffbed645e911bce54482cb',true,null,3);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (2,'katrami','Miroslav', 'Katrak', 'katrak@ixonos.com', '0907111233', 'Košice', 'JAVA DEVELOPER','63c77aa9ffeef60c7b73b208614434239194acc6dd9dbb963753973c15b0f0ba',true,null,1);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (5,'magurja','Jan', 'Magur', 'magur@ixonos.com', '0907111233', 'Košice', 'JAVA DEVELOPER','abba688a433d56f5eacb3785771452f4f4e10cede42cc9ae0ce4f10133e345e9',true,null,1);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (8,'polakja','Jan', 'Polak', 'polak@ixonos.com', '0907111233', 'Košice', 'JAVA DEVELOPER','abba688a433d56f5eacb3785771452f4f4e10cede42cc9ae0ce4f10133e345e9',true,null,1);
--INSERT INTO users (user_id,username,name,surname,email,telephonenumber,location,position,password,enabled,curriculum,manager_id) VALUES (7,'ciuliot','Oto', 'Ciulis', 'oto.ciulis@ixonos.com', '0907111233', 'Kosice', 'PROJECT_MANAGERS','a70446a88bccf2c9afe98900e89167abb918065f1fe3c1a14fec652e3381f208',true,null,1);
--
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (1,'android', 'android description', '2010-01-01', '2010-06-30');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (2,'intel', 'intel description', '2010-07-01', '2010-12-30');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (3,'visual studio', 'visual studio description', '2010-10-19', '2010-12-30');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (4,'j2me', 'situated in Oulu', '2011-01-01', '2011-12-31');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (5,'ovi', 'forsquare, situare for OVI', '2010-01-01', '2010-12-31');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (6,'meniny', 'meniny, android', '2010-10-30', '2010-12-31');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (7,'sms messaging ', 'java, spring, jdbc, mysql', '2009-01-01', '2009-12-31');
--INSERT INTO projects (projects_id,name,description, date_from, date_to) VALUES (8,'hudson ', 'hudson, maven plugin', '2010-06-30', '2010-12-31');
--
--INSERT INTO groups (group_id,group_name) VALUES (1,'SITE_MANAGERS');
--INSERT INTO groups (group_id,group_name) VALUES (2,'GROUP_MANAGERS');
--INSERT INTO groups (group_id,group_name) VALUES (3,'PROJECT_MANAGERS');
--INSERT INTO groups (group_id,group_name) VALUES (4,'CHIEF_EXECUTIVE_OFFICER');
--INSERT INTO groups (group_id,group_name) VALUES (5,'ADMINISTRATORS');
--
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (1,1,2);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (2,4,4);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (3,3,1);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (4,2,5);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (5,5,5);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (6,6,2);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (7,4,5);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (8,3,5);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (9,1,5);
--INSERT INTO group_member (group_member_id,user_id,group_id) VALUES (10,7,3);
--
--INSERT INTO authority (authority_id,user_id,authority) VALUES (1,5,7);
--INSERT INTO authority (authority_id,user_id,authority) VALUES (2,4,7);
--INSERT INTO authority (authority_id,user_id,authority) VALUES (3,2,7);
--INSERT INTO authority (authority_id,user_id,authority) VALUES (4,3,7);
--
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (1,1,7);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (2,1,8);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (3,1,9);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (4,2,8);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (5,2,7);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (6,3,7);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (7,4,9);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (8,4,8);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (9,4,7);
--INSERT INTO group_authority (group_authority_id,group_id,authority) VALUES (10,5,9);
--
--INSERT INTO projects_users (project_id,user_id) VALUES (1,1);
--INSERT INTO projects_users (project_id,user_id) VALUES (1,2);
--INSERT INTO projects_users (project_id,user_id) VALUES (1,3);
--INSERT INTO projects_users (project_id,user_id) VALUES (2,4);
--INSERT INTO projects_users (project_id,user_id) VALUES (2,5);
--
--INSERT INTO projects_users (project_id,user_id) VALUES (3,1);
--INSERT INTO projects_users (project_id,user_id) VALUES (3,2);
--INSERT INTO projects_users (project_id,user_id) VALUES (3,4);
--
--INSERT INTO projects_users (project_id,user_id) VALUES (4,1);
--INSERT INTO projects_users (project_id,user_id) VALUES (4,2);
--INSERT INTO projects_users (project_id,user_id) VALUES (4,3);
--INSERT INTO projects_users (project_id,user_id) VALUES (4,4);
--INSERT INTO projects_users (project_id,user_id) VALUES (4,5);