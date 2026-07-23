CREATE DATABASE IF NOT EXISTS gestionParc;
USE gestionParc;

CREATE TABLE marque (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(200)
);

CREATE TABLE modele (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(200),
    idMarque INT,
    reference VARCHAR(200),
    FOREIGN KEY (idMarque) REFERENCES marque(id)
);

CREATE TABLE ordinateur(
    id INT AUTO_INCREMENT PRIMARY KEY,
    idModele INT,
    ram INT,
    processeur VARCHAR(200),
    disqueDur INT,
    FOREIGN KEY (idModele) REFERENCES modele(id)
);


CREATE TABLE utilisateur(
    id INT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(200),
    mdp VARCHAR(200),
    role enum('admin', 'user') DEFAULT NULL
);

CREATE TABLE etat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(200)
);

CREATE TABLE type_panne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(200)
);

CREATE TABLE ordinateur_etat (
    id INT AUTO_INCREMENT PRIMARY KEY,
    idOrdi INT,
    idEtat INT,
    date DATETIME,
    idTypePanne INT NULL,
    observation TEXT,
    FOREIGN KEY (idOrdi) REFERENCES ordinateur(id),
    FOREIGN KEY (idEtat) REFERENCES etat(id)
);



INSERT INTO utilisateur (login, mdp, role) VALUES 
('admin', 'admin!', 'admin'),
('hotiana', 'hoty123', 'user'),
('haivo', 'haivo456', NULL),
('mihaja', 'mihaja789', 'user');

INSERT INTO marque (libelle) VALUES 
('Dell'),
('HP'),
('Lenovo'),
('Apple'),
('Acer');

INSERT INTO modele (libelle, idMarque, reference) VALUES 
('Optiplex', 1, 'TP-X1-2026'),  
('Prodesk', 2, 'DELL-LAT-5420'), 
('ThinkCenter', 3, 'HP-PB-450G8'), 
('ProBook', 2, 'AAPL-MBP14-M3'),  
('Precision', 1, 'AAPL-MBP14-M3'),  
('Veriton', 5, 'AAPL-MBP14-M3'),  
('Latitude', 1, 'ASUS-ZB-14X');         

INSERT INTO type_panne(libelle) VALUES
('Alimentation'),
('Disque Dur'),
('Carte Mere');


INSERT INTO etat (libelle) VALUES 
('fonctionnel'),
('en panne');

INSERT INTO ordinateur_etat (idOrdi,idEtat,date,idTypePanne,observation) values
(1,1,"2026-07-21",null,"nety tsara"),
(1,2,"2026-07-22",3,"tsy nandeha tampoka"),
(1,1,"2026-07-23",null,"zay vao mety"),
(2,1,"2026-07-22",null,"milamina"),
(2,2,"2026-07-23",1,"tsy milamina"),
(3,2,"2026-07-21",1,"tsy nandeha"),
(3,1,"2026-07-22",null,"nandeha"),
(3,2,"2026-07-23",2,"tsy nandeha"),
(4,1,"2026-07-23",null,"tsy misy kianina"),
(5,1,"2026-07-21",null,"mandeha tsara"),
(5,2,"2026-07-23",3,"tsy hay hoe ahoana"),
(6,2,"2026-07-23",1,"tsy mety velona mitsy"),
(7,1,"2026-07-21",null,"nandeha"),
(7,2,"2026-07-23",2,"maty tampoka"),
(8,1,"2026-07-23",null,"milamina tsara");




select 
    mo.*,
    ma.*
from modele mo
join marque ma
on mo.idMarque = ma.id;


select count(*) as total from ordinateur_etat where idEtat = 2 AND date = '2026-07-23';
select count(*) as total from ordinateur_etat where idEtat = 1 AND date = '2026-07-23';
select count(*) as total_type from ordinateur_etat where idEtat = 2 AND idTypePanne = 1 AND date = '2026-07-23';
