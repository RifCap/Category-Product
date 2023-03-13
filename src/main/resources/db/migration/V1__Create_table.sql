/* CREATE TABLE categoryEntity (
    id VARCHAR(36) PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    description VARCHAR(30) NOT NULL
);

CREATE TABLE productEntity (
    id VARCHAR(36) PRIMARY KEY,
    nom VARCHAR(20) not null,
    description VARCHAR(30) not null,
    prix_vente double not null,
    prix_achat double not null,
    stock int not null,
    categories VARCHAR(36),
    Foreign key (categories) References categoryEntity(Id)
);*/