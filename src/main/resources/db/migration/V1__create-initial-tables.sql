CREATE TABLE users (
    id INT IDENTITY PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    phone VARCHAR(20) NOT NULL,
    photo_user VARCHAR(255),
    date_anniversary DATE NOT NULL,
    created_at DATETIME2 NOT NULL,
    type_user VARCHAR(50) NOT NULL,
    active BIT NOT NULL);


  CREATE TABLE client (
      id INT IDENTITY PRIMARY KEY,
      surname VARCHAR(255),
      id_users INT NOT NULL,
      FOREIGN KEY (id_users) REFERENCES users(id)
  );

  CREATE TABLE address (
      id INT IDENTITY PRIMARY KEY,
      cep VARCHAR(20),
      state VARCHAR(50),
      city VARCHAR(100),
      neighborhood VARCHAR(100),
      street VARCHAR(255),
      number INT,
      complement VARCHAR(50),
      additional_address VARCHAR(255),
      type_address VARCHAR(50),
      id_client INT,
      FOREIGN KEY (id_client) REFERENCES client(id)
  );


    CREATE TABLE professional (
    id INT IDENTITY PRIMARY KEY,
    link_professional VARCHAR(255) NOT NULL,
    value_hour DECIMAL(10, 2) NOT NULL,
    id_users INT NOT NULL,
    FOREIGN KEY (id_users) REFERENCES users(id));

    CREATE TABLE specialty (
       id INT IDENTITY PRIMARY KEY,
       name_specialty VARCHAR(255) NOT NULL);


    CREATE TABLE category (
      id INT IDENTITY PRIMARY KEY,
      name_category VARCHAR(255) NOT NULL);


    CREATE TABLE category_specialty (
       id INT IDENTITY PRIMARY KEY,
       id_category INT NOT NULL,
       id_specialty INT NOT NULL,
       FOREIGN KEY (id_category) REFERENCES category(id),
       FOREIGN KEY (id_specialty) REFERENCES specialty(id));


     CREATE TABLE professional_specialty (
      id INT IDENTITY PRIMARY KEY,
      id_professional INT NOT NULL,
      id_specialty INT NOT NULL,
      FOREIGN KEY (id_professional) REFERENCES professional(id),
      FOREIGN KEY (id_specialty) REFERENCES specialty(id));