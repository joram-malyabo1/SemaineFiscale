CREATE DATABASE semainefiscalebd;

CREATE TABLE acte(
id int,
libelle VARCHAR(50),
CONSTRAINT pkActe Primary key(id)
);

CREATE TABLE service(
id int,
designation varchar(100),
sigle varchar(25),
CONSTRAINT pkservice primary key(id)
);

CREATE TABLE entite(
id int,	
designation varchar(100),
sigle varchar(50),
id_centre int,
constraint pkentite primary key(id)
);

CREATE TABLE assujetti(
id SERIAL,
nom varchar(200),
sigle varchar(25),
telephone varchar(20),
adresse varchar(100),
CONSTRAINT pk_assujetti primary key(id),
CONSTRAINT ukassujetti UNIQUE(nom)
);

CREATE TABLE agent(
	id int,
	id_service int,
	CONSTRAINT pk_agent PRIMARY KEY(id),
	CONSTRAINT fk_agent_assujetti FOREIGN KEY(id)REFERENCES assujetti(id) ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT fk_agent_service FOREIGN KEY(id_service) REFERENCES Service(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE agentdgrnk(
	id int,
	id_entite int,
	CONSTRAINT pk_agentdgrnk PRIMARY KEY(id),
	CONSTRAINT fk_agent_assujetti FOREIGN KEY(id)REFERENCES agent(id) ON UPDATE CASCADE ON DELETE CASCADE,	
	CONSTRAINT fk_agent_entite FOREIGN KEY(id_entite) REFERENCES entite(id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE declaration(
date_decla date,
montant float,
monnaie varchar(10),
id_assujetti int,
id_acte int,
id_entite int,
note_perception varchar(20),
CONSTRAINT pk_declaration PRIMARY KEY(id_assujetti,id_acte,id_entite),
CONSTRAINT fk_Decla_assujetti FOREIGN KEY(id_assujetti) REFERENCES assujetti(id)ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_Decla_acte FOREIGN KEY(id_acte) REFERENCES acte (id) ON UPDATE CASCADE ON DELETE CASCADE,
CONSTRAINT fk_Decla_entite FOREIGN KEY(id_entite) REFERENCES entite (id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE paiement(
datepaie date,
montant float,
monnaie varchar(200),
id_assujetti int,
note_perception varchar(20),
banque varchar(50),
bordereau varchar(50),
CONSTRAINT pk_paiement PRIMARY KEY(id_assujetti,note_perception),
CONSTRAINT fk_paie_assujetti FOREIGN KEY(id_assujetti) REFERENCES assujetti(id)ON UPDATE CASCADE ON DELETE CASCADE
);