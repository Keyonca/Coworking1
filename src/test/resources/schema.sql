DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_type;
DROP TABLE IF EXISTS booking_history;
DROP TABLE IF EXISTS job_title;
DROP TABLE IF EXISTS workplace;



CREATE TABLE user
(
    id integer NOT NULL,
    surname character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(100) NOT NULL,
    phone_number character varying(45) NOT NULL,
    photo bytea,
    form_of_work character varying(100) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY (id)

);

CREATE TABLE user_type
(
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT user_type_pkey PRIMARY KEY (id)
);

CREATE TABLE booking_history
(
    ID INTEGER NOT NULL,
    START_DATE_AND_TIME TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    END_DATE_AND_TIME TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    CONSTRAINT booking_history_pkey PRIMARY KEY (ID)
);

CREATE TABLE job_title
(
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    CONSTRAINT job_title_pkey PRIMARY KEY (id)
);

CREATE TABLE workplace
(
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    is_anyone_sitting_here BOOLEAN NOT NULL,
    CONSTRAINT workplace_pkey PRIMARY KEY (id)
);

