-- Table grandeSurface
CREATE TABLE "episaine-schema".grandeSurface(
   id_GS serial,
   intitule VARCHAR(50) NOT NULL,
   ville VARCHAR(50) NOT NULL,
   adresse_GS VARCHAR(50) NOT NULL,
   ville_GS VARCHAR(50),
   code_Postal_GS VARCHAR(50) NOT NULL,
   PRIMARY KEY(ID_GS)
);

ALTER TABLE "episaine-schema".grandeSurface OWNER TO episaine;


-- Table UtilisateurGrandeSurface
CREATE TABLE "episaine-schema".utilisateurGrandeSurface(
   id_UserGS serial,
   nom_User VARCHAR(50) NOT NULL,
   prenom_User VARCHAR(50) NOT NULL,
   poste_User VARCHAR(50),
   id_GS serial NOT NULL,
   dateEmbauche DATE,
   PRIMARY KEY(id_UserGS),
   FOREIGN KEY(id_GS) REFERENCES "episaine-schema".grandeSurface(id_GS)
);

ALTER TABLE "episaine-schema".utilisateurGrandeSurface OWNER TO episaine;

-- Table Produits
CREATE TABLE "episaine-schema".produits(
   iD_Produits serial,
   nom_Produit VARCHAR(50) NOT NULL,
   prix DECIMAL(10,2) NOT NULL,
   regime VARCHAR(50) NOT NULL,
   rayon VARCHAR(50) NOT NULL,
   calorie INT NOT NULL,
   dateExpiration DATE NOT NULL,
   quantite_Produit INT,
   stock INT,
   id_GS serial NOT NULL, 
   PRIMARY KEY(id_Produits),
   FOREIGN KEY(id_GS) REFERENCES "episaine-schema".grandeSurface(id_GS) 
);

ALTER TABLE "episaine-schema".Produits OWNER TO episaine;

-- Relation Vend entre les tables Produits et grandeSurface
-- Le but est de lier les produits et les grandes surfaces
--CREATE INDEX idx_produits ON Produits(ID_Produits, ID_GS);

CREATE TABLE "episaine-schema".vend(
   id_Produits INT,
   id_GS INT,
   quantiteVendue INT,
   PRIMARY KEY(id_Produits, id_GS),  
   FOREIGN KEY(id_Produits) REFERENCES "episaine-schema".Produits(id_Produits),
   FOREIGN KEY(id_GS) REFERENCES "episaine-schema".grandeSurface(id_GS)
);

ALTER TABLE "episaine-schema".vend OWNER TO episaine;

-- Table Nutritionnistes 
CREATE TABLE "episaine-schema".nutritionnistes(
   id_Nutritioniste serial,
   nom_N VARCHAR(50) NOT NULL,
   prenom_N VARCHAR(50),
   numero_de_telephone_N VARCHAR(20), -- Changement de INT à VARCHAR(20)
   mail_N VARCHAR(50),
   PRIMARY KEY(id_Nutritioniste)
);

ALTER TABLE "episaine-schema".nutritionnistes OWNER TO episaine;

-- Table Recettes   
CREATE TABLE "episaine-schema".recettes(
   id_Recette serial,
   nom_Recette VARCHAR(100) NOT NULL, 
   nombre_de_Calories INT NOT NULL,
   ingredients VARCHAR(255), 
   instructions VARCHAR(255),
   regimeAlimentaire VARCHAR(50),
   id_Nutritioniste serial NOT NULL,
   PRIMARY KEY(id_Recette),
   FOREIGN KEY(id_Nutritioniste) REFERENCES "episaine-schema".nutritionnistes(id_Nutritioniste),
   CHECK (regimeAlimentaire IN ('normale', 'cétogène', 'végétarien', 'carnivore', 'pescétarien', 'végétalien', 'sans gluten', 'sans lactose', 'halal', 'cashér', 'paléo', 'sans sucre ajouté', 'régime méditerranéen'))
);

ALTER TABLE "episaine-schema".recettes OWNER TO episaine;

-- Relation EstComposéDe entre les tables Produits et Recettes (un produit peut être dans plusieurs recettes)
CREATE TABLE "episaine-schema".estComposeDe(
   id_Produits serial,
   id_Recettes serial,
   quantite INT,
   PRIMARY KEY(id_Produits, id_Recettes),
   FOREIGN KEY(id_Produits) REFERENCES "episaine-schema".produits(id_Produits),
   FOREIGN KEY(id_Recettes) REFERENCES "episaine-schema".recettes(id_Recettes)
);

ALTER TABLE "episaine-schema".estComposeDe OWNER TO episaine;

-- Table Client 
CREATE TABLE "episaine-schema".clients(
   id_Client serial,
   nom_Client VARCHAR(50) NOT NULL,
   prenom_Client VARCHAR(50) NOT NULL,
   date_de_naissance_Client DATE NOT NULL,
   poids DECIMAL(5,2) NOT NULL, 
   genre VARCHAR(10) NOT NULL,
   taille INT NOT NULL,
   numero_de_telephone_Client VARCHAR(20) NOT NULL,
   mail_Client VARCHAR(50) NOT NULL,
   ville VARCHAR(50) NOT NULL,
   adresse VARCHAR(50) NOT NULL,
   code_Postal VARCHAR(50),
   PRIMARY KEY(id_Client),
   CHECK (genre IN ('Homme', 'Femme')) 
);

ALTER TABLE "episaine-schema".clients OWNER TO episaine;
-- Table Informations 
CREATE TABLE "episaine-schema".informations(
   id_Client serial,
   but VARCHAR(50),
   allergie VARCHAR(50) NOT NULL,
   nbDeRepas INT,
   PRIMARY KEY(id_Client),
   FOREIGN KEY(id_Client) REFERENCES "episaine-schema".clients(id_Client),
   CHECK (but IN ('perte de poids','gain de poids','maintien de poids'))
);

ALTER TABLE "episaine-schema".informations OWNER TO episaine;

-- Relation genere entre les tables Clients et Recettes
CREATE TABLE "episaine-schema".genere(
   id_Client serial,
   id_Recette serial,
   PRIMARY KEY(id_Client, id_Recette),
   FOREIGN KEY(id_Client) REFERENCES "episaine-schema".informations(id_Client),
   FOREIGN KEY(id_Recette) REFERENCES "episaine-schema".recettes(id_Recette)
);

ALTER TABLE "episaine-schema".genere OWNER TO episaine;