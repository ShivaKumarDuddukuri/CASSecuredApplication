CREATE TABLE employees (

  id_employee bigserial NOT NULL,
  username character varying(275) NOT NULL,
  password character varying(275) NOT NULL,
  first_name character varying(275) NOT NULL,
  last_name character varying(275) NOT NULL,
  email character varying(275),
  address character varying(275) ,
  designation  character varying(275) NOT NULL,
  department  character varying(275) NOT NULL,
  phoneNumber  character varying(275) NOT NULL,
  bloodGroup  character varying(275) NOT NULL,
  
  CONSTRAINT employees_pk PRIMARY KEY (id_employee),
  CONSTRAINT unique_username UNIQUE (username)
);


CREATE TABLE leaves (
 
   leave_id  bigserial NOT NULL,
   description    VARCHAR(1000) NOT NULL,
   numberOfDays    bigserial NOT NULL,
   fromDate  bigserial NOT NULL,          
   toDate  bigserial NOT NULL, 
   leaveType character varying(275) ,
   id_employee bigint,
   
   CONSTRAINT leaves_pk PRIMARY KEY (leave_id),
   
   CONSTRAINT fk_id_employee FOREIGN KEY (id_employee)
      REFERENCES employees (id_employee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);



CREATE TABLE events (
 
   event_id  bigserial NOT NULL,
   event_name    VARCHAR(1000) NOT NULL,
   description    VARCHAR(1000) NOT NULL,
   summary    VARCHAR(1000) NOT NULL,         
   eventDate  bigserial NOT NULL,   
   id_employee bigint,
   
   CONSTRAINT event_pk PRIMARY KEY (event_id),
   
   CONSTRAINT unique_name UNIQUE (event_name),
   CONSTRAINT fk_id_employee FOREIGN KEY (id_employee)
      REFERENCES employees (id_employee) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);