--
-- PostgreSQL database dump
--

-- Dumped from database version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)
-- Dumped by pg_dump version 16.1 (Ubuntu 16.1-1.pgdg22.04+1)

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

--
-- Name: episaine-schema; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA "episaine-schema";


ALTER SCHEMA "episaine-schema" OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clients; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".clients (
    id_client integer NOT NULL,
    nom_client character varying(50) NOT NULL,
    prenom_client character varying(50) NOT NULL,
    date_de_naissance_client date NOT NULL,
    poids numeric(5,2) NOT NULL,
    genre character varying(10) NOT NULL,
    taille integer NOT NULL,
    numero_de_telephone_client character varying(20) NOT NULL,
    mail_client character varying(50) NOT NULL,
    ville character varying(50) NOT NULL,
    adresse character varying(50) NOT NULL,
    code_postal character varying(50),
    CONSTRAINT clients_genre_check CHECK (((genre)::text = ANY ((ARRAY['Homme'::character varying, 'Femme'::character varying])::text[])))
);


ALTER TABLE "episaine-schema".clients OWNER TO episaine;

--
-- Name: clients_id_client_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".clients_id_client_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".clients_id_client_seq OWNER TO episaine;

--
-- Name: clients_id_client_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".clients_id_client_seq OWNED BY "episaine-schema".clients.id_client;


--
-- Name: estcomposede; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".estcomposede (
    id_produit integer NOT NULL,
    id_recette integer NOT NULL,
    quantite integer NOT NULL
);


ALTER TABLE "episaine-schema".estcomposede OWNER TO episaine;

--
-- Name: genere; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".genere (
    id_gen integer NOT NULL,
    id_client integer NOT NULL,
    id_recette integer NOT NULL
);


ALTER TABLE "episaine-schema".genere OWNER TO episaine;

--
-- Name: genere_id_gen_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".genere_id_gen_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".genere_id_gen_seq OWNER TO episaine;

--
-- Name: genere_id_gen_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".genere_id_gen_seq OWNED BY "episaine-schema".genere.id_gen;


--
-- Name: grandesurface; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".grandesurface (
    id_gs integer NOT NULL,
    intitule character varying(50) NOT NULL,
    ville character varying(50) NOT NULL,
    adresse_gs character varying(50) NOT NULL,
    ville_gs character varying(50) NOT NULL,
    code_postal_gs character varying(50) NOT NULL
);


ALTER TABLE "episaine-schema".grandesurface OWNER TO episaine;

--
-- Name: grandesurface_id_gs_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".grandesurface_id_gs_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".grandesurface_id_gs_seq OWNER TO episaine;

--
-- Name: grandesurface_id_gs_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".grandesurface_id_gs_seq OWNED BY "episaine-schema".grandesurface.id_gs;


--
-- Name: informations; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".informations (
    id_info integer NOT NULL,
    id_client integer NOT NULL,
    but character varying(50) NOT NULL,
    allergie character varying(50) NOT NULL,
    nbderepas integer NOT NULL,
    CONSTRAINT informations_allergie_check CHECK (((allergie)::text = ANY ((ARRAY['oeuf'::character varying, 'lait'::character varying, 'moutard'::character varying, 'arachide'::character varying, 'mollusque'::character varying, 'crustacé'::character varying, 'poisson'::character varying, 'graine de sesame'::character varying, 'soja'::character varying, 'sulfite'::character varying, 'noix'::character varying, 'blé'::character varying, 'tricicale'::character varying])::text[]))),
    CONSTRAINT informations_but_check CHECK (((but)::text = ANY ((ARRAY['perte de poids'::character varying, 'gain de poids'::character varying, 'maintien de poids'::character varying])::text[])))
);


ALTER TABLE "episaine-schema".informations OWNER TO episaine;

--
-- Name: informations_id_info_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".informations_id_info_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".informations_id_info_seq OWNER TO episaine;

--
-- Name: informations_id_info_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".informations_id_info_seq OWNED BY "episaine-schema".informations.id_info;


--
-- Name: nutritionnistes; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".nutritionnistes (
    id_nutritionniste integer NOT NULL,
    nom_n character varying(50) NOT NULL,
    prenom_n character varying(50) NOT NULL,
    numero_de_telephone_n character varying(20) NOT NULL,
    mail_n character varying(50) NOT NULL
);


ALTER TABLE "episaine-schema".nutritionnistes OWNER TO episaine;

--
-- Name: nutritionnistes_id_nutritionniste_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".nutritionnistes_id_nutritionniste_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".nutritionnistes_id_nutritionniste_seq OWNER TO episaine;

--
-- Name: nutritionnistes_id_nutritionniste_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".nutritionnistes_id_nutritionniste_seq OWNED BY "episaine-schema".nutritionnistes.id_nutritionniste;


--
-- Name: produits; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".produits (
    id_produit integer NOT NULL,
    nom_produit character varying(50) NOT NULL,
    prix numeric(10,2) NOT NULL,
    regime character varying(50) NOT NULL,
    rayon character varying(50) NOT NULL,
    calorie integer NOT NULL,
    dateexpiration date NOT NULL,
    quantite_produit integer NOT NULL,
    stock integer NOT NULL,
    id_gs integer NOT NULL
);


ALTER TABLE "episaine-schema".produits OWNER TO episaine;

--
-- Name: produits_id_produit_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".produits_id_produit_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".produits_id_produit_seq OWNER TO episaine;

--
-- Name: produits_id_produit_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".produits_id_produit_seq OWNED BY "episaine-schema".produits.id_produit;


--
-- Name: recettes; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".recettes (
    id_recette integer NOT NULL,
    nom_recette character varying(100) NOT NULL,
    nombre_de_calories integer NOT NULL,
    ingredients character varying(255) NOT NULL,
    instructions character varying(255) NOT NULL,
    regimealimentaire character varying(50) NOT NULL,
    id_nutritionniste integer NOT NULL,
    CONSTRAINT recettes_regimealimentaire_check CHECK (((regimealimentaire)::text = ANY ((ARRAY['normale'::character varying, 'cétogène'::character varying, 'végétarien'::character varying, 'carnivore'::character varying, 'pescétarien'::character varying, 'végétalien'::character varying, 'sans gluten'::character varying, 'sans lactose'::character varying, 'halal'::character varying, 'cashér'::character varying, 'paléo'::character varying, 'sans sucre ajouté'::character varying, 'régime méditerranéen'::character varying])::text[])))
);


ALTER TABLE "episaine-schema".recettes OWNER TO episaine;

--
-- Name: recettes_id_recette_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".recettes_id_recette_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".recettes_id_recette_seq OWNER TO episaine;

--
-- Name: recettes_id_recette_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".recettes_id_recette_seq OWNED BY "episaine-schema".recettes.id_recette;


--
-- Name: utilisateurgrandesurface; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".utilisateurgrandesurface (
    id_usergs integer NOT NULL,
    nom_user character varying(50) NOT NULL,
    prenom_user character varying(50) NOT NULL,
    poste_user character varying(50) NOT NULL,
    id_gs integer NOT NULL,
    dateembauche date NOT NULL
);


ALTER TABLE "episaine-schema".utilisateurgrandesurface OWNER TO episaine;

--
-- Name: utilisateurgrandesurface_id_usergs_seq; Type: SEQUENCE; Schema: episaine-schema; Owner: episaine
--

CREATE SEQUENCE "episaine-schema".utilisateurgrandesurface_id_usergs_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE "episaine-schema".utilisateurgrandesurface_id_usergs_seq OWNER TO episaine;

--
-- Name: utilisateurgrandesurface_id_usergs_seq; Type: SEQUENCE OWNED BY; Schema: episaine-schema; Owner: episaine
--

ALTER SEQUENCE "episaine-schema".utilisateurgrandesurface_id_usergs_seq OWNED BY "episaine-schema".utilisateurgrandesurface.id_usergs;


--
-- Name: vend; Type: TABLE; Schema: episaine-schema; Owner: episaine
--

CREATE TABLE "episaine-schema".vend (
    id_produit integer NOT NULL,
    id_gs integer NOT NULL,
    quantitevendue integer NOT NULL
);


ALTER TABLE "episaine-schema".vend OWNER TO episaine;

--
-- Name: clients id_client; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".clients ALTER COLUMN id_client SET DEFAULT nextval('"episaine-schema".clients_id_client_seq'::regclass);


--
-- Name: genere id_gen; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".genere ALTER COLUMN id_gen SET DEFAULT nextval('"episaine-schema".genere_id_gen_seq'::regclass);


--
-- Name: grandesurface id_gs; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".grandesurface ALTER COLUMN id_gs SET DEFAULT nextval('"episaine-schema".grandesurface_id_gs_seq'::regclass);


--
-- Name: informations id_info; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".informations ALTER COLUMN id_info SET DEFAULT nextval('"episaine-schema".informations_id_info_seq'::regclass);


--
-- Name: nutritionnistes id_nutritionniste; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".nutritionnistes ALTER COLUMN id_nutritionniste SET DEFAULT nextval('"episaine-schema".nutritionnistes_id_nutritionniste_seq'::regclass);


--
-- Name: produits id_produit; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".produits ALTER COLUMN id_produit SET DEFAULT nextval('"episaine-schema".produits_id_produit_seq'::regclass);


--
-- Name: recettes id_recette; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".recettes ALTER COLUMN id_recette SET DEFAULT nextval('"episaine-schema".recettes_id_recette_seq'::regclass);


--
-- Name: utilisateurgrandesurface id_usergs; Type: DEFAULT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".utilisateurgrandesurface ALTER COLUMN id_usergs SET DEFAULT nextval('"episaine-schema".utilisateurgrandesurface_id_usergs_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".clients (id_client, nom_client, prenom_client, date_de_naissance_client, poids, genre, taille, numero_de_telephone_client, mail_client, ville, adresse, code_postal) FROM stdin;
2	Smith	Emma	1985-08-25	65.20	Femme	165	0987654321	emma.smith@example.com	London	456 Elm Street	WC1X 0AA
3	Garcia	Juan	1978-12-10	80.30	Homme	175	0666666666	juan.garcia@example.com	Madrid	789 Calle Mayor	28001
4	Choi	Seo-Yeon	1995-03-30	55.80	Femme	160	0777777777	seo.yeon@example.com	Seoul	987 Gangnam-gu	06000
5	Rossi	Giuseppe	1982-06-20	70.00	Homme	170	0333333333	giuseppe.rossi@example.com	Rome	321 Via Veneto	00100
6	Wong	Ling	1993-11-05	45.50	Femme	155	0555555555	ling.wong@example.com	Hong Kong	654 Nathan Road	\N
7	Kowalski	Piotr	1970-04-12	90.20	Homme	185	0444444444	piotr.kowalski@example.com	Warsaw	234 Krakowskie Przedmieście	00-001
8	Müller	Hans	1988-09-08	85.00	Homme	178	0888888888	hans.muller@example.com	Berlin	987 Unter den Linden	10117
9	Lopez	Maria	1991-02-18	60.70	Femme	163	0999999999	maria.lopez@example.com	Barcelona	123 Rambla de Catalunya	08001
10	Kim	Min-Ji	1980-07-22	52.30	Femme	158	0111111111	min.ji@example.com	Seoul	456 Myeongdong	06000
11	Almeida	Carlos	1975-10-14	78.40	Homme	175	0222222222	carlos.almeida@example.com	Lisbon	789 Avenida da Liberdade	1000-001
12	Nguyen	Thi	1994-01-04	48.90	Femme	150	0888877777	thi.nguyen@example.com	Hanoi	987 Le Loi Street	\N
13	Sato	Takeshi	1986-08-30	73.80	Homme	172	0999999888	takeshi.sato@example.com	Tokyo	654 Shibuya	100-0001
14	Fernández	Ana	1987-05-28	62.50	Femme	167	0666666555	ana.fernandez@example.com	Madrid	321 Gran Vía	28001
15	Mueller	Sophie	1990-03-17	57.20	Femme	162	0333666666	sophie.mueller@example.com	Berlin	654 Friedrichstraße	10117
16	Silva	João	1973-11-09	82.10	Homme	180	0555777777	joao.silva@example.com	Lisbon	987 Rua Augusta	1000-002
17	Nakamura	Yuki	1984-09-02	50.60	Femme	155	0999333333	yuki.nakamura@example.com	Tokyo	123 Asakusa	111-0032
18	Martinez	Manuel	1977-06-25	88.00	Homme	190	0666888888	manuel.martinez@example.com	Barcelona	456 Passeig de Gràcia	08001
19	Liu	Wei	1996-02-12	59.30	Femme	160	0888555555	wei.liu@example.com	Beijing	789 Wangfujing	\N
20	Kovács	Gábor	1983-07-18	77.50	Homme	178	0555888888	gabor.kovacs@example.com	Budapest	987 Andrassy út	1061
21	nom	prenom	2022-03-02	321.00	Homme	32	num	mail	ville	adresss	codepos
22	nom	prenom	2022-03-02	321.00	Homme	32	num	mail	ville	adresss	codepos
\.


--
-- Data for Name: estcomposede; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".estcomposede (id_produit, id_recette, quantite) FROM stdin;
\.


--
-- Data for Name: genere; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".genere (id_gen, id_client, id_recette) FROM stdin;
\.


--
-- Data for Name: grandesurface; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".grandesurface (id_gs, intitule, ville, adresse_gs, ville_gs, code_postal_gs) FROM stdin;
\.


--
-- Data for Name: informations; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".informations (id_info, id_client, but, allergie, nbderepas) FROM stdin;
1	3	perte de poids	oeuf	2
\.


--
-- Data for Name: nutritionnistes; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".nutritionnistes (id_nutritionniste, nom_n, prenom_n, numero_de_telephone_n, mail_n) FROM stdin;
1	nom	prenom	num	mail
\.


--
-- Data for Name: produits; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".produits (id_produit, nom_produit, prix, regime, rayon, calorie, dateexpiration, quantite_produit, stock, id_gs) FROM stdin;
\.


--
-- Data for Name: recettes; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".recettes (id_recette, nom_recette, nombre_de_calories, ingredients, instructions, regimealimentaire, id_nutritionniste) FROM stdin;
1	nom	312	ing	ins	normale	1
\.


--
-- Data for Name: utilisateurgrandesurface; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".utilisateurgrandesurface (id_usergs, nom_user, prenom_user, poste_user, id_gs, dateembauche) FROM stdin;
\.


--
-- Data for Name: vend; Type: TABLE DATA; Schema: episaine-schema; Owner: episaine
--

COPY "episaine-schema".vend (id_produit, id_gs, quantitevendue) FROM stdin;
\.


--
-- Name: clients_id_client_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".clients_id_client_seq', 22, true);


--
-- Name: genere_id_gen_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".genere_id_gen_seq', 1, false);


--
-- Name: grandesurface_id_gs_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".grandesurface_id_gs_seq', 1, false);


--
-- Name: informations_id_info_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".informations_id_info_seq', 1, true);


--
-- Name: nutritionnistes_id_nutritionniste_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".nutritionnistes_id_nutritionniste_seq', 1, false);


--
-- Name: produits_id_produit_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".produits_id_produit_seq', 1, false);


--
-- Name: recettes_id_recette_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".recettes_id_recette_seq', 1, true);


--
-- Name: utilisateurgrandesurface_id_usergs_seq; Type: SEQUENCE SET; Schema: episaine-schema; Owner: episaine
--

SELECT pg_catalog.setval('"episaine-schema".utilisateurgrandesurface_id_usergs_seq', 1, false);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id_client);


--
-- Name: estcomposede estcomposede_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".estcomposede
    ADD CONSTRAINT estcomposede_pkey PRIMARY KEY (id_produit, id_recette);


--
-- Name: genere genere_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".genere
    ADD CONSTRAINT genere_pkey PRIMARY KEY (id_gen);


--
-- Name: grandesurface grandesurface_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".grandesurface
    ADD CONSTRAINT grandesurface_pkey PRIMARY KEY (id_gs);


--
-- Name: informations informations_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".informations
    ADD CONSTRAINT informations_pkey PRIMARY KEY (id_info);


--
-- Name: nutritionnistes nutritionnistes_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".nutritionnistes
    ADD CONSTRAINT nutritionnistes_pkey PRIMARY KEY (id_nutritionniste);


--
-- Name: produits produits_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".produits
    ADD CONSTRAINT produits_pkey PRIMARY KEY (id_produit);


--
-- Name: recettes recettes_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".recettes
    ADD CONSTRAINT recettes_pkey PRIMARY KEY (id_recette);


--
-- Name: utilisateurgrandesurface utilisateurgrandesurface_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".utilisateurgrandesurface
    ADD CONSTRAINT utilisateurgrandesurface_pkey PRIMARY KEY (id_usergs);


--
-- Name: vend vend_pkey; Type: CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".vend
    ADD CONSTRAINT vend_pkey PRIMARY KEY (id_produit, id_gs);


--
-- Name: ind_all_i; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_all_i ON "episaine-schema".informations USING btree (allergie);


--
-- Name: ind_but_i; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_but_i ON "episaine-schema".informations USING btree (but);


--
-- Name: ind_cal_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_cal_p ON "episaine-schema".produits USING btree (calorie);


--
-- Name: ind_cod_gs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_cod_gs ON "episaine-schema".grandesurface USING btree (code_postal_gs);


--
-- Name: ind_code_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_code_c ON "episaine-schema".clients USING btree (code_postal);


--
-- Name: ind_dat_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_dat_p ON "episaine-schema".produits USING btree (dateexpiration);


--
-- Name: ind_dat_ugs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_dat_ugs ON "episaine-schema".utilisateurgrandesurface USING btree (dateembauche);


--
-- Name: ind_date_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_date_c ON "episaine-schema".clients USING btree (date_de_naissance_client);


--
-- Name: ind_idc_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idc_c ON "episaine-schema".clients USING btree (id_client);


--
-- Name: ind_idc_g; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idc_g ON "episaine-schema".genere USING btree (id_client);


--
-- Name: ind_idc_i; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idc_i ON "episaine-schema".informations USING btree (id_client);


--
-- Name: ind_idg_g; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idg_g ON "episaine-schema".genere USING btree (id_gen);


--
-- Name: ind_idg_gs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idg_gs ON "episaine-schema".grandesurface USING btree (id_gs);


--
-- Name: ind_idg_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idg_p ON "episaine-schema".produits USING btree (id_gs);


--
-- Name: ind_idg_v; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idg_v ON "episaine-schema".vend USING btree (id_gs);


--
-- Name: ind_idgs_ugs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idgs_ugs ON "episaine-schema".utilisateurgrandesurface USING btree (id_gs);


--
-- Name: ind_idi_i; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idi_i ON "episaine-schema".informations USING btree (id_info);


--
-- Name: ind_idn_n; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idn_n ON "episaine-schema".nutritionnistes USING btree (id_nutritionniste);


--
-- Name: ind_idn_r; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idn_r ON "episaine-schema".recettes USING btree (id_nutritionniste);


--
-- Name: ind_idp_ecd; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idp_ecd ON "episaine-schema".estcomposede USING btree (id_produit);


--
-- Name: ind_idp_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idp_p ON "episaine-schema".produits USING btree (id_produit);


--
-- Name: ind_idp_v; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idp_v ON "episaine-schema".vend USING btree (id_produit);


--
-- Name: ind_idr_ecd; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idr_ecd ON "episaine-schema".estcomposede USING btree (id_recette);


--
-- Name: ind_idr_g; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idr_g ON "episaine-schema".genere USING btree (id_recette);


--
-- Name: ind_idr_r; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idr_r ON "episaine-schema".recettes USING btree (id_recette);


--
-- Name: ind_idu_ugs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_idu_ugs ON "episaine-schema".utilisateurgrandesurface USING btree (id_usergs);


--
-- Name: ind_int_gs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_int_gs ON "episaine-schema".grandesurface USING btree (intitule);


--
-- Name: ind_nbr_i; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_nbr_i ON "episaine-schema".informations USING btree (nbderepas);


--
-- Name: ind_nombre_r; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_nombre_r ON "episaine-schema".recettes USING btree (nombre_de_calories);


--
-- Name: ind_poids_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_poids_c ON "episaine-schema".clients USING btree (poids);


--
-- Name: ind_pos_ugs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_pos_ugs ON "episaine-schema".utilisateurgrandesurface USING btree (poste_user);


--
-- Name: ind_pri_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_pri_p ON "episaine-schema".produits USING btree (prix);


--
-- Name: ind_qua_ecd; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_qua_ecd ON "episaine-schema".estcomposede USING btree (quantite);


--
-- Name: ind_qua_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_qua_p ON "episaine-schema".produits USING btree (quantite_produit);


--
-- Name: ind_ray_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_ray_p ON "episaine-schema".produits USING btree (rayon);


--
-- Name: ind_reg_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_reg_p ON "episaine-schema".produits USING btree (regime);


--
-- Name: ind_reg_r; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_reg_r ON "episaine-schema".recettes USING btree (regimealimentaire);


--
-- Name: ind_sto_p; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_sto_p ON "episaine-schema".produits USING btree (stock);


--
-- Name: ind_taille_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_taille_c ON "episaine-schema".clients USING btree (taille);


--
-- Name: ind_vil_c; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_vil_c ON "episaine-schema".clients USING btree (ville);


--
-- Name: ind_vil_gs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_vil_gs ON "episaine-schema".grandesurface USING btree (ville_gs);


--
-- Name: ind_ville_gs; Type: INDEX; Schema: episaine-schema; Owner: episaine
--

CREATE INDEX ind_ville_gs ON "episaine-schema".grandesurface USING btree (ville);


--
-- Name: estcomposede estcomposede_id_produit_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".estcomposede
    ADD CONSTRAINT estcomposede_id_produit_fkey FOREIGN KEY (id_produit) REFERENCES "episaine-schema".produits(id_produit);


--
-- Name: estcomposede estcomposede_id_recette_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".estcomposede
    ADD CONSTRAINT estcomposede_id_recette_fkey FOREIGN KEY (id_recette) REFERENCES "episaine-schema".recettes(id_recette);


--
-- Name: genere genere_id_client_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".genere
    ADD CONSTRAINT genere_id_client_fkey FOREIGN KEY (id_client) REFERENCES "episaine-schema".clients(id_client);


--
-- Name: genere genere_id_recette_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".genere
    ADD CONSTRAINT genere_id_recette_fkey FOREIGN KEY (id_recette) REFERENCES "episaine-schema".recettes(id_recette);


--
-- Name: informations informations_id_client_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".informations
    ADD CONSTRAINT informations_id_client_fkey FOREIGN KEY (id_client) REFERENCES "episaine-schema".clients(id_client);


--
-- Name: produits produits_id_gs_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".produits
    ADD CONSTRAINT produits_id_gs_fkey FOREIGN KEY (id_gs) REFERENCES "episaine-schema".grandesurface(id_gs);


--
-- Name: recettes recettes_id_nutritionniste_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".recettes
    ADD CONSTRAINT recettes_id_nutritionniste_fkey FOREIGN KEY (id_nutritionniste) REFERENCES "episaine-schema".nutritionnistes(id_nutritionniste);


--
-- Name: utilisateurgrandesurface utilisateurgrandesurface_id_gs_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".utilisateurgrandesurface
    ADD CONSTRAINT utilisateurgrandesurface_id_gs_fkey FOREIGN KEY (id_gs) REFERENCES "episaine-schema".grandesurface(id_gs);


--
-- Name: vend vend_id_gs_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".vend
    ADD CONSTRAINT vend_id_gs_fkey FOREIGN KEY (id_gs) REFERENCES "episaine-schema".grandesurface(id_gs);


--
-- Name: vend vend_id_produit_fkey; Type: FK CONSTRAINT; Schema: episaine-schema; Owner: episaine
--

ALTER TABLE ONLY "episaine-schema".vend
    ADD CONSTRAINT vend_id_produit_fkey FOREIGN KEY (id_produit) REFERENCES "episaine-schema".produits(id_produit);


--
-- Name: SCHEMA "episaine-schema"; Type: ACL; Schema: -; Owner: postgres
--

GRANT ALL ON SCHEMA "episaine-schema" TO episaine;


--
-- PostgreSQL database dump complete
--

