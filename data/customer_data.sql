--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1 (Debian 15.1-1.pgdg110+1)
-- Dumped by pg_dump version 15.1

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
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
    id integer NOT NULL,
    first_name character varying(100) NOT NULL,
    last_name character varying(100) NOT NULL,
    postal_address text,
    email_address character varying(100),
    phone_number character varying(30)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.customers_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.customers_id_seq OWNER TO postgres;

--
-- Name: customers_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.customers_id_seq OWNED BY public.customers.id;


--
-- Name: customers id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers ALTER COLUMN id SET DEFAULT nextval('public.customers_id_seq'::regclass);


--
-- Data for Name: customers; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.customers (id, first_name, last_name, postal_address, email_address, phone_number) VALUES (1, 'Bella', 'Rusden', '50 Saggers Road NAIRIBIN WA 6350', 'BellaRusden@mailer.com.au', '+61890458058');
INSERT INTO public.customers (id, first_name, last_name, postal_address, email_address, phone_number) VALUES (2, 'Madeline', 'Namatjira', '81 Thule Drive FORDS SA 5373', 'MadelineNamatjira@mailer.com.au', '+61883101682');
INSERT INTO public.customers (id, first_name, last_name, postal_address, email_address, phone_number) VALUES (3, 'Gabrielle', 'Cussen', '24 Ghost Hill Road MOUNTAIN LAGOON NSW 2758', 'GabrielleCussen@mailer.com.au', '+61247075048');
INSERT INTO public.customers (id, first_name, last_name, postal_address, email_address, phone_number) VALUES (4, 'Zachary', 'Goold', '37 Tooraweenah Road FORBES NSW 2871', 'ZacharyGoold@mailer.com.au', '+612940536700');


--
-- Name: customers_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.customers_id_seq', 4, true);


--
-- Name: customers customers_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.customers
    ADD CONSTRAINT customers_pkey PRIMARY KEY (id);


--
-- Name: TABLE customers; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.customers TO "reactive-web";


--
-- PostgreSQL database dump complete
--

