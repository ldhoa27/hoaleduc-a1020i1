drop database if exists case_module4;
create database case_module4;
use case_module4;

CREATE TABLE `app_role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `app_user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `department` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `education_degree` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `attach_service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `unit` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `service_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `rent_type` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cost` double NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `resort_service` (
  `id` varchar(255) NOT NULL,
  `area` int NOT NULL,
  `cost` double NOT NULL,
  `max_people` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rent_type_id` int NOT NULL,
  `service_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `resort_service_rent_type_fk` FOREIGN KEY (`rent_type_id`) REFERENCES `rent_type` (`id`),
  CONSTRAINT `resort_service_service_type_fk` FOREIGN KEY (`service_type_id`) REFERENCES `service_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `customer` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) NOT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `customer_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `customer_customer_type_fk` FOREIGN KEY (`customer_type_id`) REFERENCES `customer_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_card` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `salary` double NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `department_id` int NOT NULL,
  `education_degree_id` int NOT NULL,
  `position_id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `employee_position_fk` FOREIGN KEY (`position_id`) REFERENCES `position` (`id`),
  CONSTRAINT `employee_department_fk` FOREIGN KEY (`department_id`) REFERENCES `department` (`id`),
  CONSTRAINT `employee_app_user_fk` FOREIGN KEY (`username`) REFERENCES `app_user` (`username`),
  CONSTRAINT `employee_education_degree_fk` FOREIGN KEY (`education_degree_id`) REFERENCES `education_degree` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int NOT NULL,
  `amount` double NOT NULL,
  `deposit` double NOT NULL,
  `end_date` date NOT NULL,
  `start_date` date NOT NULL,
  `customer_id` varchar(255) NOT NULL,
  `employee_id` int DEFAULT NULL,
  `service_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `contract_resort_service_fk` FOREIGN KEY (`service_id`) REFERENCES `resort_service` (`id`),
  CONSTRAINT `contract_customer_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `contract_employee_fk` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `contract_detail` (
  `attach_service_id` int NOT NULL,
  `contract_id` int NOT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`attach_service_id`,`contract_id`),
  CONSTRAINT `contract_detail_attach_service_fk` FOREIGN KEY (`attach_service_id`) REFERENCES `attach_service` (`id`),
  CONSTRAINT `contract_detail_contract_fk` FOREIGN KEY (`contract_id`) REFERENCES `contract` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `user_role` (
  `role_id` int NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`,`username`),
  CONSTRAINT `user_role_app_role_fk` FOREIGN KEY (`role_id`) REFERENCES `app_role` (`id`),
  CONSTRAINT `user_role_app_user_fk` FOREIGN KEY (`username`) REFERENCES `app_user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `app_role` VALUES (1,'ROLE_GIAMDOC'),(2,'ROLE_QUANLY'),(3,'ROLE_NHANVIEN');

INSERT INTO `app_user` VALUES ('admin','$2a$10$52nJhv6TJlbwbzhl.ip0v.QRoafPZPVv97cdhkyHemq7WpWgBXXVS'),('admin1','$2a$10$IymI2rka/9Fq3PUa/xASgOcVdGswS349U2aDZ4c.ovQacjoiQCnYm'),('duchoa','$2a$10$b/frA4cY0yuz4G5oJjrbMO7HJegUUbg9Z0C4Qfb8K1oQPO3ZzmviC');
INSERT INTO `customer_type` VALUES (1,'Diamond'),(2,'Platinum'),(3,'Gold'),(4,'Silver'),(5,'Member');
INSERT INTO `position` VALUES (1,'L??? t??n'),(2,'Chuy??n vi??n'),(3,'Ph???c v???'),(4,'Gi??m s??t'),(5,'Qu???n l??'),(6,'Gi??m ?????c');
INSERT INTO `department` VALUES (1,'Sales-Marketing'),(2,'H??nh ch??nh'),(3,'Qu???n l??'),(4,'Ph???c v???');
INSERT INTO `education_degree` VALUES (1,'Trung c???p'),(2,'Cao ?????ng'),(3,'?????i h???c'),(4,'Sau ?????i h???c');
INSERT INTO `attach_service` VALUES (1,1000000,'Massage','available','L???n'),(2,300000,'Karaoke','Kh??? d???ng','Gi???'),(3,300000,'Th???c ??n','Kh??? d???ng','Kh??ch'),(4,20000,'N?????c u???ng','Kh??? d???ng','Kh??ch'),(5,150000,'Thu?? xe','Kh??? d???ng','Kh??ch');
INSERT INTO `service_type` VALUES (1,'Villa'),(2,'House'),(3,'Room');
INSERT INTO `rent_type` VALUES (1,100,'N??m'),(2,100,'Th??ng'),(3,100,'Ng??y'),(4,100,'Gi???');
INSERT INTO `resort_service` VALUES ('DV-0001',150,2500000,4,'Swiming',3,2),('DV-0002',200,15000000,8,'Art',2,1),('DV-0003',45,3000000,3,'Picnic',1,2),('DV-0004',120,9000000,6,'Car free',1,1),('DV-0005',63,1200000,2,'Game',1,3);
INSERT INTO `customer` VALUES ('KH-0001','Ngh??? An','1999-09-10','duchoa@gmail.com','Nam','169636325312','?????c H??a','0915255255',3),('KH-0002','H?? T??nh','2002-05-05','maithuong@gmail.com','N???','193366655','Nguy???n Mai Th????ng','0905444555',2),('KH-0003','Hu???','1991-05-02','quynhtrang@gmail.com','Nam','198855633255','Nguy???n Qu???nh Trang','0912333655',5),('KH-0004','H?? N???i','1999-01-01','hoangtrang@gmaicx.com','N???','125363666','Nguy???n Ho??ng Trang','0903255255',4),('KH-0005','H??? Ch?? Minh','2000-01-01','huonglan@gmail.com','N???','447858896696','H????ng Lan','0914448585',3);
INSERT INTO `employee` VALUES (1,'Ngh??? An','1995-09-02','duchoale@gmail.com','197255499','L?? ?????c H??a','0915225252',15000000,'duchoa',3,3,1),(2,'Hu???','1993-12-15','mailan@gmail.com','444555666','Mai Lan','0913666555',6000000,'admin',3,2,1),(3,'H?? N???i','2000-01-01','thaoson@gmail.com','195625536','Nguy???n T???t Th???o','0905444555',7000000,'admin1',1,3,3),(4,'Ngh??? An','1999-09-05','loctoan@gmail.com','195625536','Nguy???n T???t L???c','0918556336',7000000,'admin1',3,3,2);
INSERT INTO `contract` VALUES (2,0,0,'2021-09-04','2021-09-02','KH-0003',1,'DV-0001'),(3,0,0,'2021-05-09','2021-05-05','KH-0002',1,'DV-0001'),(4,0,0,'2022-01-09','2022-01-01','KH-0002',1,'DV-0005'),(5,0,0,'2021-04-26','2021-04-25','KH-0001',1,'DV-0003'),(6,0,0,'2021-08-05','2021-08-02','KH-0001',1,'DV-0003'),(8,0,0,'2021-09-04','2021-09-02','KH-0003',1,'DV-0001'),(9,0,0,'2021-09-05','2021-09-02','KH-0003',1,'DV-0001'),(11,0,0,'2021-09-05','2021-09-02','KH-0003',1,'DV-0001'),(13,0,0,'2021-09-09','2021-09-02','KH-0002',1,'DV-0001');
INSERT INTO `contract_detail` VALUES (1,2,3),(2,3,2),(3,2,1),(4,3,1),(5,4,3);
INSERT INTO `user_role` VALUES (1,'admin'),(2,'admin1'),(3,'duchoa'),(1,'duchoa'),(3,'admin1'),(3,'admin');

  