CREATE TABLE names (
    id SERIAL PRIMARY KEY,
    name character varying(255) NOT NULL
);

CREATE TABLE history (
    firstname_id INTEGER REFERENCES names(id),
    secondname_id INTEGER REFERENCES names(id),
    result INTEGER NOT NULL,
    PRIMARY KEY (firstname_id, secondname_id)
);


