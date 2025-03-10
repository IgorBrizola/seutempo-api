CREATE TABLE posts (
    id INT PRIMARY KEY IDENTITY,
    title INT NOT NULL,
    img_url VARCHAR(MAX),
    createdAt DATETIME2,
    id_professional INT NOT NULL,
    FOREIGN KEY (id_professional) REFERENCES professional(id)
)