
-- Script de création de la base de données ENCHERES
--   type :      SQL Server 2012
--

-- BDD -- 
DROP DATABASE BDD_ENCHERES
GO

CREATE DATABASE BDD_ENCHERES COLLATE French_CI_AS
GO

USE BDD_ENCHERES
GO

-- CATEGORIES -- 
CREATE TABLE CATEGORIES (
    no_categorie   INTEGER IDENTITY(1,1) NOT NULL,
    libelle        VARCHAR(30) NOT NULL
)

ALTER TABLE CATEGORIES ADD constraint categorie_pk PRIMARY KEY (no_categorie)

-- ENCHERES -- 
CREATE TABLE ENCHERES(	
	no_article int NOT NULL,
	date_enchere datetime NOT NULL,
	montant_enchere int NOT NULL,
	no_utilisateur int NOT NULL
 )

 -- RETRAITS -- 
CREATE TABLE RETRAITS (
	no_retrait       INTEGER IDENTITY(1,1) NOT NULL,
    rue              VARCHAR(30) NOT NULL,
	--Modification du type : Un cp est limité à 5 char numériques
    code_postal      NUMERIC(5) NOT NULL,  
    ville            VARCHAR(30) NOT NULL
)

ALTER TABLE RETRAITS ADD constraint retrait_pk PRIMARY KEY  (no_retrait)  

-- boolean pour savoir si retrait a été effectué ou pas
ALTER TABLE RETRAITS ADD statut BIT NOT NULL;

 -- UTILISATEURS -- 
CREATE TABLE UTILISATEURS (
    no_utilisateur   INTEGER IDENTITY(1,1) NOT NULL,
    pseudo           VARCHAR(30) NOT NULL,
    nom              VARCHAR(30) NOT NULL,
    prenom           VARCHAR(30) NOT NULL,
    email            VARCHAR(30) NOT NULL,
    telephone        VARCHAR(15),
    rue              VARCHAR(30) NOT NULL,
	-- modif type code_postal
    code_postal      NUMERIC(5) NOT NULL,
    ville            VARCHAR(30) NOT NULL,
    mot_de_passe     VARCHAR(30) NOT NULL,
    credit           INTEGER NOT NULL,
    administrateur   bit NOT NULL
)

ALTER TABLE UTILISATEURS ADD constraint utilisateur_pk PRIMARY KEY (no_utilisateur)

-- colonne mot_de_passe modifiée car mot de passe hashé sur 32 caractères 
ALTER TABLE UTILISATEURS ALTER COLUMN mot_de_passe VARCHAR(35); 

-- contraites d'unicité pour empêcher l'ajout des doublons
ALTER TABLE UTILISATEURS ADD CONSTRAINT ak_pseudo UNIQUE (pseudo); 
ALTER TABLE UTILISATEURS ADD CONSTRAINT ak_email UNIQUE (email);

-- pour s'assurer que le credit ne soit pas négatif
ALTER TABLE UTILISATEURS ADD CONSTRAINT check_credit CHECK(credit >=0);

-- VENDUS -- 
CREATE TABLE ARTICLES_VENDUS (
    no_article                    INTEGER IDENTITY(1,1) NOT NULL,
    nom_article                   VARCHAR(30) NOT NULL,
    description_article           VARCHAR(300) NOT NULL,
	date_debut_encheres           DATE NOT NULL,
    date_fin_encheres             DATE NOT NULL,
    prix_initial                  INTEGER,
    prix_vente                    INTEGER,
    no_utilisateur                INTEGER NOT NULL,
    no_categorie                  INTEGER NOT NULL,
	no_retrait					  INTEGER NULL
)

ALTER TABLE ARTICLES_VENDUS ADD constraint articles_vendus_pk PRIMARY KEY (no_article)

-- modification des colonnes date pour pouvoir stocker date ET HEURE
ALTER TABLE ARTICLES_VENDUS DROP COLUMN date_debut_encheres;
ALTER TABLE ARTICLES_VENDUS DROP COLUMN date_fin_encheres;
ALTER TABLE ARTICLES_VENDUS ADD debut_encheres DATETIME NOT NULL;
ALTER TABLE ARTICLES_VENDUS ADD fin_encheres DATETIME NOT NULL;

-- ajout de colonne chemin_img pour pouvoir stocker le chemin d'accès à une photo d'article
ALTER TABLE ARTICLES_VENDUS ADD chemin_img VARCHAR(300);


ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_utilisateur_fk FOREIGN KEY ( no_utilisateur ) REFERENCES UTILISATEURS ( no_utilisateur )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_retrait_fk FOREIGN KEY ( no_retrait )
        REFERENCES RETRAITS ( no_retrait )
ON DELETE no action 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT articles_vendus_categories_fk FOREIGN KEY ( no_categorie )
        REFERENCES categories ( no_categorie )
ON DELETE NO ACTION 
    ON UPDATE no action 

ALTER TABLE ARTICLES_VENDUS
    ADD CONSTRAINT ventes_utilisateur_fk FOREIGN KEY ( no_utilisateur )
        REFERENCES utilisateurs ( no_utilisateur ) ON DELETE CASCADE;
-- ON DELETE NO ACTION  ON UPDATE no action 
	-- 
--ALTER TABLE ARTICLES_VENDUS DROP CONSTRAINT ventes_utilisateur_fk;

ALTER TABLE ENCHERES ADD constraint enchere_pk PRIMARY KEY ( no_article)
-- pour pouvoir inserer plusieurs encheres pour meme article et donc pouvoir garder l'histoirique d'encheres
ALTER TABLE ENCHERES DROP CONSTRAINT enchere_pk;

ALTER TABLE ENCHERES
    ADD CONSTRAINT encheres_no_article_fk FOREIGN KEY ( no_article ) REFERENCES ARTICLES_VENDUS ( no_article ) ON DELETE CASCADE;
-- ON DELETE NO ACTION 
--    ON UPDATE no action 
-- ALTER TABLE ENCHERES DROP CONSTRAINT encheres_no_article_fk

INSERT INTO CATEGORIES(libelle) VALUES('Informatique');
INSERT INTO CATEGORIES(libelle) VALUES('Ameublement');
INSERT INTO CATEGORIES(libelle) VALUES('Vêtement');
INSERT INTO CATEGORIES(libelle) VALUES('Sport&Loisirs');

SELECT * FROM ARTICLES_VENDUS
SELECT * FROM ENCHERES
SELECT * FROM UTILISATEURS
SELECT * FROM CATEGORIES