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
-- Name: order_lines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_lines (
    transact_id integer NOT NULL,
    product_id character varying(100) NOT NULL,
    quantity integer NOT NULL,
    price_at_ordertime numeric
);


ALTER TABLE public.order_lines OWNER TO postgres;

--
-- Data for Name: order_lines; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (1, '63c7484e321fbab0844611b3', 2, 3.95);
INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (2, '63c7484e321fbab0844611b3', 7, 3.82);
INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (3, '63c7484e321fbab0844611b3', 1, 4.20);
INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (5, '63c7484e321fbab0844611bb', 42, 3.86);
INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (6, '63c7484e321fbab0844611b7', 1, 20.41);
INSERT INTO public.order_lines (transact_id, product_id, quantity, price_at_ordertime) VALUES (4, '63c7484e321fbab0844611bc', 4, 8.77);


--
-- Name: order_lines order_lines_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_lines
    ADD CONSTRAINT order_lines_pkey PRIMARY KEY (transact_id, product_id);


--
-- Name: order_lines fk_order_lines; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.order_lines
    ADD CONSTRAINT fk_order_lines FOREIGN KEY (transact_id) REFERENCES public.orders(transact_id);


--
-- Name: TABLE order_lines; Type: ACL; Schema: public; Owner: postgres
--

GRANT SELECT,INSERT,DELETE,UPDATE ON TABLE public.order_lines TO "reactive-web";


--
-- PostgreSQL database dump complete
--

