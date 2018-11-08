/*
 *   MODELE DES DONNEES
 *   ------------------
 *
 *
 *   +-------------------+                 +-----------------------+
 *   | names             |                 | history               |
 *   +-------------------+                 +-----------------------+
 *   |                   |                 |                       |
 *   | * id: int         <-----------------+ * firstname_id: int   |
 *   |   name: string    <-----------------+ * secondname_id: int  |
 *   |                   |                 |   result: int         |
 *   +-------------------+                 |                       |
 *                                         +-----------------------+
 */

-- Création du schéma
CREATE TABLE names (
    id SERIAL PRIMARY KEY,
    name character varying(255) UNIQUE NOT NULL
);
CREATE TABLE history (
    firstname_id INTEGER REFERENCES names(id),
    secondname_id INTEGER REFERENCES names(id),
    result INTEGER NOT NULL,
    PRIMARY KEY (firstname_id, secondname_id)
);

-- Insertion des noms
INSERT INTO names (name) VALUES ('links');
INSERT INTO names (name) VALUES ('zelda');
INSERT INTO names (name) VALUES ('batman');
INSERT INTO names (name) VALUES ('robin');

-- Insertion de l'historique
INSERT INTO history VALUES (
    (SELECT id FROM names ORDER BY id ASC LIMIT 1),
    (SELECT id FROM names ORDER BY id ASC LIMIT 1 OFFSET 1),
    80
);
INSERT INTO history VALUES (
    (SELECT id FROM names ORDER BY id ASC LIMIT 1 OFFSET 2),
    (SELECT id FROM names ORDER BY id ASC LIMIT 1 OFFSET 3),
    100
);
