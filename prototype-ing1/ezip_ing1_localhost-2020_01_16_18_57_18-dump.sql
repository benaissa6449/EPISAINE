--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1 (Ubuntu 12.1-1.pgdg19.10+1)
-- Dumped by pg_dump version 12.1 (Ubuntu 12.1-1.pgdg19.10+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: students; Type: TABLE; Schema: ezip-ing1; Owner: pgil
--

CREATE TABLE "ezip-ing1".students (
    name character varying(64) NOT NULL,
    firstname character varying(64) NOT NULL,
    id integer NOT NULL,
    "group" character varying(8) NOT NULL
);


ALTER TABLE "ezip-ing1".students OWNER TO pgil;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: ezip-ing1; Owner: pgil
--

CREATE SEQUENCE "ezip-ing1".students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE "ezip-ing1".students_id_seq OWNER TO pgil;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: ezip-ing1; Owner: pgil
--

ALTER SEQUENCE "ezip-ing1".students_id_seq OWNED BY "ezip-ing1".students.id;


--
-- Name: students id; Type: DEFAULT; Schema: ezip-ing1; Owner: pgil
--

ALTER TABLE ONLY "ezip-ing1".students ALTER COLUMN id SET DEFAULT nextval('"ezip-ing1".students_id_seq'::regclass);


--
-- Data for Name: students; Type: TABLE DATA; Schema: ezip-ing1; Owner: pgil
--

COPY "ezip-ing1".students (name, firstname, id, "group") FROM stdin;
MYNAME	Myfirstname	401	FISA-B
\.


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: ezip-ing1; Owner: pgil
--

SELECT pg_catalog.setval('"ezip-ing1".students_id_seq', 443, true);


--
-- Name: students students_pk; Type: CONSTRAINT; Schema: ezip-ing1; Owner: pgil
--

ALTER TABLE ONLY "ezip-ing1".students
    ADD CONSTRAINT students_pk PRIMARY KEY (id);


--
-- Name: students_id_uindex; Type: INDEX; Schema: ezip-ing1; Owner: pgil
--

CREATE UNIQUE INDEX students_id_uindex ON "ezip-ing1".students USING btree (id);


--
-- PostgreSQL database dump complete
--

