-- Table GrandeSurface
CREATE TABLE "episaine-schema".GrandeSurface(
   ID_GS INT,
   Intitule VARCHAR(50) NOT NULL,
   Ville VARCHAR(50) NOT NULL,
   Adresse_GS VARCHAR(50) NOT NULL,
   Ville_GS VARCHAR(50),
   Code_Postal_GS VARCHAR(50) NOT NULL,
   PRIMARY KEY(ID_GS)
);

ALTER TABLE "episaine-schema".GrandeSurface OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".gs_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "episaine-schema".gs_id OWNER TO episaine;
   
ALTER SEQUENCE "episaine-schema".gs_id OWNED BY "episaine-schema".GrandeSurface.ID_GS;

-- Table UtilisateurGrandeSurface
CREATE TABLE "episaine-schema".UtilisateurGrandeSurface(
   ID_UserGS INT,
   Nom_User VARCHAR(50) NOT NULL,
   Prenom_User VARCHAR(50) NOT NULL,
   Poste_User VARCHAR(50),
   ID_GS INT NOT NULL,
   DateEmbauche DATE,
   PRIMARY KEY(ID_UserGS),
   FOREIGN KEY(ID_GS) REFERENCES "episaine-schema".GrandeSurface(ID_GS)
);

ALTER TABLE "episaine-schema".UtilisateurGrandeSurface OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".u_gs_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
   
ALTER TABLE "episaine-schema".u_gs_id OWNER TO episaine;

ALTER SEQUENCE "episaine-schema".u_gs_id OWNED BY "episaine-schema".UtilisateurGrandeSurface.ID_UserGS;

-- Table Produits
CREATE TABLE "episaine-schema".Produits(
   ID_Produits INT,
   Nom_Produit VARCHAR(50) NOT NULL,
   Prix DECIMAL(10,2) NOT NULL,
   Regime VARCHAR(50) NOT NULL,
   Rayon VARCHAR(50) NOT NULL,
   Calorie INT NOT NULL,
   DateExpiration DATE NOT NULL,
   Quantite_Produit INT,
   Stock INT,
   ID_GS INT NOT NULL, 
   PRIMARY KEY(ID_Produits),
   FOREIGN KEY(ID_GS) REFERENCES "episaine-schema".GrandeSurface(ID_GS) 
);

ALTER TABLE "episaine-schema".Produits OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".p_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
   
ALTER TABLE "episaine-schema".p_id OWNER TO episaine;

ALTER SEQUENCE "episaine-schema".p_id OWNED BY "episaine-schema".Produits.ID_Produits;

-- Relation Vend entre les tables Produits et GrandeSurface
-- Le but est de lier les produits et les grandes surfaces
--CREATE INDEX idx_produits ON Produits(ID_Produits, ID_GS);

CREATE TABLE "episaine-schema".Vend(
   ID_Produits INT,
   ID_GS INT,
   QuantiteVendue INT,
   PRIMARY KEY(ID_Produits, ID_GS),  
   FOREIGN KEY(ID_Produits) REFERENCES "episaine-schema".Produits(ID_Produits),
   FOREIGN KEY(ID_GS) REFERENCES "episaine-schema".GrandeSurface(ID_GS)
);

ALTER TABLE "episaine-schema".Vend OWNER TO episaine;

-- Table Nutritionnistes 
CREATE TABLE "episaine-schema".Nutritionnistes(
   ID_Nutritioniste INT,
   Nom_N VARCHAR(50) NOT NULL,
   Prenom_N VARCHAR(50),
   Numero_de_telephone_N VARCHAR(20), -- Changement de INT à VARCHAR(20)
   Mail_N VARCHAR(50),
   PRIMARY KEY(ID_Nutritioniste)
);

ALTER TABLE "episaine-schema".Nutritionnistes OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".n_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
ALTER TABLE "episaine-schema".n_id OWNER TO episaine;
   
ALTER SEQUENCE "episaine-schema".n_id OWNED BY "episaine-schema".Nutritionnistes.ID_Nutritioniste;

-- Table Recettes   
CREATE TABLE "episaine-schema".Recettes(
   ID_Recettes INT,
   Nom_Recette VARCHAR(100) NOT NULL, 
   Nombre_de_Calories INT NOT NULL,
   Ingredients VARCHAR(255), 
   Instructions VARCHAR(255),
   RegimeAlimentaire VARCHAR(50),
   ID_Nutritioniste INT NOT NULL,
   PRIMARY KEY(ID_Recettes),
   FOREIGN KEY(ID_Nutritioniste) REFERENCES "episaine-schema".Nutritionnistes(ID_Nutritioniste),
   CHECK (RegimeAlimentaire IN ('normale', 'cétogène', 'végétarien', 'carnivore', 'pescétarien', 'végétalien', 'sans gluten', 'sans lactose', 'halal', 'cashér', 'paléo', 'sans sucre ajouté', 'régime méditerranéen'))
);

ALTER TABLE "episaine-schema".Recettes OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".r_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE "episaine-schema".r_id OWNER TO episaine;
   
ALTER SEQUENCE "episaine-schema".r_id OWNED BY "episaine-schema".Recettes.ID_Recettes;

-- Relation EstComposéDe entre les tables Produits et Recettes (un produit peut être dans plusieurs recettes)
CREATE TABLE "episaine-schema".EstComposeDe(
   ID_Produits INT,
   ID_Recettes INT,
   Quantite INT,
   PRIMARY KEY(ID_Produits, ID_Recettes),
   FOREIGN KEY(ID_Produits) REFERENCES "episaine-schema".Produits(ID_Produits),
   FOREIGN KEY(ID_Recettes) REFERENCES "episaine-schema".Recettes(ID_Recettes)
);

ALTER TABLE "episaine-schema".EstComposeDe OWNER TO episaine;

-- Table Client 
CREATE TABLE "episaine-schema".Clients(
   ID_Clients INT,
   Nom_Client VARCHAR(50) NOT NULL,
   Prenom_Client VARCHAR(50) NOT NULL,
   Date_de_naissance_Client DATE NOT NULL,
   Poids DECIMAL(5,2) NOT NULL, 
   Genre VARCHAR(10) NOT NULL,
   Taille INT NOT NULL,
   Numero_de_telephone_Client VARCHAR(20) NOT NULL,
   Mail_Client VARCHAR(50) NOT NULL,
   Ville VARCHAR(50) NOT NULL,
   Adresse VARCHAR(50) NOT NULL,
   Code_Postal_ VARCHAR(50),
   PRIMARY KEY(ID_Clients),
   CHECK (Genre IN ('Homme', 'Femme')) 
);

ALTER TABLE "episaine-schema".Clients OWNER TO episaine;

CREATE SEQUENCE "episaine-schema".c_id
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
    
ALTER TABLE "episaine-schema".c_id OWNER TO episaine;
   
ALTER SEQUENCE "episaine-schema".c_id OWNED BY "episaine-schema".Clients.ID_Clients;

-- Table Informations 
CREATE TABLE "episaine-schema".Informations(
   ID_Clients INT,
   But VARCHAR(50),
   Allergie VARCHAR(50) NOT NULL,
   NbDeRepas INT,
   PRIMARY KEY(ID_Clients),
   FOREIGN KEY(ID_Clients) REFERENCES "episaine-schema".Clients(ID_Clients),
   CHECK (But IN ('perte de poids','gain de poids','maintien de poids'))
);

ALTER TABLE "episaine-schema".Informations OWNER TO episaine;

-- Relation genere entre les tables Clients et Recettes
CREATE TABLE "episaine-schema".genere(
   ID_Clients INT,
   ID_Recettes INT,
   PRIMARY KEY(ID_Clients, ID_Recettes),
   FOREIGN KEY(ID_Clients) REFERENCES "episaine-schema".Informations(ID_Clients),
   FOREIGN KEY(ID_Recettes) REFERENCES "episaine-schema".Recettes(ID_Recettes)
);

ALTER TABLE "episaine-schema".genere OWNER TO episaine;