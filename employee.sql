DROP DATABASE IF EXISTS employees;
CREATE DATABASE IF NOT EXISTS employees;
USE employees
;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS emp_emp_employee,
                     emp_dep_department,
                     emp_dmr_dept_manager,
                     emp_pay_payroll,
                     emp_bil_bill,
                     emp_usr_user,
                     emp_rol_rol,
                     emp_pos_position;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;



CREATE TABLE emp_dep_department (
    dep_dept_no     	int         	NOT NULL AUTO_INCREMENT,
    dep_dept_name   	VARCHAR(40)     NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (dep_dept_no),
    UNIQUE  KEY (dep_dept_name)
);


CREATE TABLE emp_pos_position (
    pos_position_no     INT             NOT NULL AUTO_INCREMENT,
    pos_position       	VARCHAR(50)     NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (pos_position_no)
); 
CREATE TABLE emp_emp_employee (
    emp_emp_no      	INT             NOT NULL AUTO_INCREMENT,
    emp_birth_date  	DATE            NOT NULL,
    emp_first_name  	VARCHAR(14)     NOT NULL,
    emp_last_name   	VARCHAR(16)     NOT NULL,
    emp_gender      	ENUM ('M','F')  NOT NULL,    
    emp_hire_date   	DATE            NOT NULL,
    emp_email           VARCHAR(150)    NOT NULL,
    emp_position_no     INT             NOT NULL,
    emp_dept_no     	INT         	NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (emp_emp_no)
    
);

ALTER TABLE emp_emp_employee add constraint FOREIGN KEY (emp_position_no) REFERENCES emp_pos_position(pos_position_no) ON update cascade on delete cascade;
  ALTER TABLE emp_emp_employee add   constraint FOREIGN KEY (emp_dept_no) REFERENCES emp_dep_department(dep_dept_no) ON update cascade on delete cascade;

CREATE TABLE emp_pay_payroll (
	pay_payroll_no		INT				NOT NULL AUTO_INCREMENT,
    pay_emp_no      	INT             NOT NULL,
    pay_from_date   	DATE            NOT NULL,
    pay_to_date     	DATE            NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    FOREIGN KEY (pay_emp_no) REFERENCES emp_emp_employee (emp_emp_no) ON DELETE CASCADE,
    PRIMARY KEY (pay_payroll_no)
) 
; 

CREATE TABLE emp_bil_bill (
    bill_bill_no       INT              NOT NULL,
    bil_payroll_no     INT             	NOT NULL,
    bil_value 		   FLOAT(7,2)		NOT NULL,
    bil_description	   VARCHAR(50) 		NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    constraint FOREIGN KEY (bil_payroll_no) REFERENCES emp_pay_payroll (pay_payroll_no) ON DELETE CASCADE,
PRIMARY KEY(bill_bill_no)
) 
; 


CREATE TABLE emp_rol_rol (
    rol_rol_no         INT             	NOT NULL AUTO_INCREMENT,
    rol_rol	   		   VARCHAR(10) 		NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    PRIMARY KEY(rol_rol_no)
    
) ;

CREATE TABLE emp_usr_user (
    usr_user_no       INT             	NOT NULL AUTO_INCREMENT,
    usr_user 		   VARCHAR(10)		NOT NULL,
    user_password	   VARCHAR(130) 	NOT NULL,
    usr_rol_no 		   INT 				NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    PRIMARY KEY(usr_user_no),
    constraint FOREIGN KEY (usr_rol_no) REFERENCES emp_rol_rol (rol_rol_no) ON DELETE CASCADE
) 
; 
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'admin', 'roberto', '2020-07-03', 'roberto', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'rrhh', 'roberto', '2020-07-03', 'roberto', '2020-07-03');

insert into emp_usr_user value(0, 'roberto', '3c9909afec25354d551dae21590bb26e38d53f2173b8d3dc3eee4c047e7ab1c1eb8b85103e3be7ba613b31bb5c9c36214dc9f14a42fd7a2fdb84856bca5c44c2', 1, 'roberto', '2020-07-03', 'roberto', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'rrhh', 'roberto', '2020-07-03', 'roberto', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'rrhh', 'Andrea', '2020-07-03', 'Andrea', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'rrhh', 'Juan', '2020-07-03', 'Juan', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'rrhh', 'Carlos', '2020-07-03', 'Juan', '2020-07-03');
INSERT INTO `employees`.`emp_rol_rol` ( `rol_rol`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ( 'admin', 'Carlos', '2020-07-03', 'Juan', '2020-07-03');

-- INSERT DE DEPARTAMENTOS -----------------------------------------------------------------------------------------------

INSERT INTO `employees`.`emp_dep_department` (`dep_dept_name`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Marketing', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_dep_department` (`dep_dept_name`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Ventas', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_dep_department` (`dep_dept_name`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('RRHH', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_dep_department` (`dep_dept_name`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Informatica', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');

-- INSERT DE JEFATURAS -----------------------------------------------------------------------------------------------

INSERT INTO `employees`.`emp_pos_position` (`pos_position`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Gerente Marketing', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_pos_position` (`pos_position`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Gerente de ventas', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_pos_position` (`pos_position`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Gerente de RRHH', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
INSERT INTO `employees`.`emp_pos_position` (`pos_position`, `A_user_create`, `A_date_create`, `A_user_change`, `A_date_change`) VALUES ('Gerente de informatica', 'Andrea', '2020-07-05', 'Andrea', '2020-07-05');
