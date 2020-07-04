DROP DATABASE IF EXISTS employees;
CREATE DATABASE IF NOT EXISTS employees;
USE employees;

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
    dep_dept_no     	int         	NOT NULL,
    dep_dept_name   	VARCHAR(40)     NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (dep_dept_no),
    UNIQUE  KEY (dep_dept_name)
);


CREATE TABLE emp_pos_position (
    pos_position_no     INT             NOT NULL,
    pos_position       	VARCHAR(50)     NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (pos_position_no)
); 
CREATE TABLE emp_emp_employee (
    emp_emp_no      	INT             NOT NULL,
    emp_birth_date  	DATE            NOT NULL,
    emp_first_name  	VARCHAR(14)     NOT NULL,
    emp_last_name   	VARCHAR(16)     NOT NULL,
    emp_gender      	ENUM ('M','F')  NOT NULL,    
    emp_hire_date   	DATE            NOT NULL,
    emp_position_no     INT             NOT NULL,
    emp_dept_no     	CHAR(4)         NOT NULL,
	A_user_create 	    VARCHAR(10)     NOT NULL,
    A_date_create       DATE     		NOT NULL,
    A_user_change 	    VARCHAR(10)  	NOT NULL,
    A_date_change       DATE     		NOT NULL,
    PRIMARY KEY (emp_emp_no),
    FOREIGN KEY (emp_position_no) REFERENCEs emp_pos_position(pos_position_no) ON update cascade on delete cascade,
    FOREIGN KEY (emp_dept_no) REFERENCES emp_dep_department(dep_dept_no) ON update cascade on delete cascade
);

CREATE TABLE emp_pay_payroll (
	pay_payroll_no		INT				NOT NULL,
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
    bil_payroll_no     INT             	NOT NULL,
    bil_value 		   FLOAT(7,2)		NOT NULL,
    bil_description	   VARCHAR(50) 		NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    FOREIGN KEY (bil_payroll_no) REFERENCES emp_pay_payroll (pay_payroll_no) ON DELETE CASCADE

) 
; 


CREATE TABLE emp_rol_rol (
    rol_rol_no         INT             	NOT NULL,
    rol_rol	   		   VARCHAR(10) 		NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    PRIMARY KEY(rol_rol_no)
) ;

CREATE TABLE emp_usr_user (
    usr_user_no       INT             	NOT NULL,
    usr_user 		   FLOAT(7,2)		NOT NULL,
    user_password	   VARCHAR(130) 	NOT NULL,
    usr_rol_no 		   INT 				NOT NULL,
	A_user_create 	   VARCHAR(10)      NOT NULL,
    A_date_create      DATE     		NOT NULL,
    A_user_change 	   VARCHAR(10)  	NOT NULL,
    A_date_change      DATE     		NOT NULL,
    PRIMARY KEY(usr_user_no),
    FOREIGN KEY (usr_rol_no) REFERENCES emp_rol_rol (rol_rol_no) ON DELETE CASCADE
) 
; 