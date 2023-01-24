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
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    transact_id integer NOT NULL,
    customer_id integer NOT NULL,
    order_date timestamp without time zone
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: orders_transact_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.orders_transact_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.orders_transact_id_seq OWNER TO postgres;

--
-- Name: orders_transact_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.orders_transact_id_seq OWNED BY public.orders.transact_id;


--
-- Name: orders transact_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN transact_id SET DEFAULT nextval('public.orders_transact_id_seq'::regclass);


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (1, 1, '2023-01-23 04:34:53.645159');
INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (2, 1, '2023-01-23 04:34:54.834305');
INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (3, 1, '2023-01-23 04:34:55.402134');
INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (4, 1, '2023-01-23 04:34:55.92336');
INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (5, 1, '2023-01-23 04:34:56.409835');
INSERT INTO public.orders (transact_id, customer_id, order_date) VALUES (6, 1, '2023-01-23 04:34:56.864107');


--
-- Name: orders_transact_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orders_transact_id_seq', 6, true);


--
-- Name: orders orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (transact_id);


--
-- Name: orders fk_customer_order; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk_customer_order FOREIGN KEY (customer_id) REFERENCES public.customers(id);


--
-- Name: TABLE orders; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.orders TO "reactive-web";


--
-- PostgreSQL database dump complete
--

