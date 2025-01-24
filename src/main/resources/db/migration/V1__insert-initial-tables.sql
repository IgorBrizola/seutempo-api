CREATE TABLE users (
    id INT PRIMARY KEY NOT NULL,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) UNIQUE,
    phone VARCHAR(20),
    photo_user VARCHAR(255),
    date_anniversary DATE NOT NULL,
    created_at DATETIME2 NOT NULL,
    type_user VARCHAR(50) NOT NULL,
    active BIT NOT NULL);

    CREATE TABLE client (
    id INT PRIMARY KEY NOT NULL,
    surname VARCHAR(255),
    FOREIGN KEY (id) REFERENCES users(id));

    CREATE TABLE professional (
    id INT PRIMARY KEY,
    link_professional VARCHAR(255),
    value_hour DECIMAL(10, 2),
    FOREIGN KEY (id) REFERENCES users(id));

    CREATE TABLE address (
        id INT PRIMARY KEY NOT NULL,
        cep VARCHAR(20),
        state VARCHAR(50),
        city VARCHAR(100),
        neighborhood VARCHAR(100),
        street VARCHAR(255),
        number VARCHAR(20),
        complement VARCHAR(255),
        additional_address VARCHAR(255),
        type_address VARCHAR(50),
        id_users INT NOT NULL,
        FOREIGN KEY (id_users) REFERENCES users(id));


    CREATE TABLE specialty (
       id INT PRIMARY KEY,
       name_specialty VARCHAR(255) NOT NULL);


    CREATE TABLE category (
      id INT PRIMARY KEY,
      name_category VARCHAR(255) NOT NULL);


    CREATE TABLE category_specialty (
       id INT PRIMARY KEY,
       id_category INT,
       id_specialty INT,
       FOREIGN KEY (id_category) REFERENCES category(id),
       FOREIGN KEY (id_specialty) REFERENCES specialty(id));


     CREATE TABLE professional_specialty (
      id INT PRIMARY KEY,
      id_professional INT,
      id_specialty INT,
      FOREIGN KEY (id_professional) REFERENCES professional(id),
      FOREIGN KEY (id_specialty) REFERENCES specialty(id));