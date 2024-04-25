UPDATE "episaine-schema".clients SET nom_client = TRIM(nom_client);
UPDATE "episaine-schema".clients SET prenom_client = TRIM(prenom_client);
UPDATE "episaine-schema".clients SET genre = TRIM(genre);
UPDATE "episaine-schema".clients SET numero_de_telephone_client = TRIM(numero_de_telephone_client);
UPDATE "episaine-schema".clients SET mail_client = TRIM(mail_client);
UPDATE "episaine-schema".clients SET ville = TRIM(ville);
UPDATE "episaine-schema".clients SET adresse = TRIM(adresse);
UPDATE "episaine-schema".clients SET code_postal = TRIM(code_postal);


UPDATE "episaine-schema".grandesurface SET intitule = TRIM(intitule);
UPDATE "episaine-schema".grandesurface SET adresse_gs = TRIM(adresse_gs);
UPDATE "episaine-schema".grandesurface SET ville_gs = TRIM(ville_gs);
UPDATE "episaine-schema".grandesurface SET code_postal_gs = TRIM(code_postal_gs);


UPDATE "episaine-schema".informations SET but = TRIM(but);
UPDATE "episaine-schema".informations SET allergie = TRIM(allergie);
UPDATE "episaine-schema".informations SET nbderepas = TRIM(nbderepas);


UPDATE "episaine-schema".nutritionnistes SET nom_n = TRIM(nom_n);
UPDATE "episaine-schema".nutritionnistes SET prenom_n = TRIM(prenom_n);
UPDATE "episaine-schema".nutritionnistes SET numero_de_telephone_n = TRIM(numero_de_telephone_n);
UPDATE "episaine-schema".nutritionnistes SET mail_n = TRIM(mail_n);


UPDATE "episaine-schema".produits SET nom_produit = TRIM(nom_produit);
UPDATE "episaine-schema".produits SET regime = TRIM(regime);
UPDATE "episaine-schema".produits SET rayon = TRIM(rayon);


UPDATE "episaine-schema".recettes SET nom_recette = TRIM(nom_recette);
UPDATE "episaine-schema".recettes SET ingredients = TRIM(ingredients);
UPDATE "episaine-schema".recettes SET instructions = TRIM(instructions);
UPDATE "episaine-schema".recettes SET regimealimentaire = TRIM(regimealimentaire);


UPDATE "episaine-schema".utilisateurgrandesurface SET nom_user = TRIM(nom_user);
UPDATE "episaine-schema".utilisateurgrandesurface SET prenom_user = TRIM(prenom_user);
UPDATE "episaine-schema".utilisateurgrandesurface SET poste_user = TRIM(poste_user);


