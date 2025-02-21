-- Inserindo dados na tabela category
INSERT INTO category (name_category)
VALUES ('Saúde'), ('Educação'), ('Tecnologia'), ('Engenharia'), ('Arte'), ('Esporte'), ('Culinária'), ('Música'), ('Negócios'), ('Moda'),
('Direito'), ('Agronegócio'), ('Medicina Veterinária'), ('Ciências Sociais'), ('Meio Ambiente'), ('Marketing Digital'), ('Design Gráfico'), ('Turismo'), ('Ciência de Dados'), ('Segurança do Trabalho'),
('Administração'), ('Finanças'), ('Logística'), ('Psicopedagogia'), ('Química'), ('Astronomia'), ('Fotografia'), ('Biotecnologia'), ('Gestão de Projetos'), ('Fonoaudiologia');

-- Inserindo dados na tabela specialty
INSERT INTO specialty (name_specialty, id_category)
VALUES ('Fisioterapia', 1), ('Psicologia', 1), ('Matemática', 2), ('Desenvolvimento Web', 3), ('Arquitetura', 4),
('Pintura', 5), ('Futebol', 6), ('Gastronomia', 7), ('Violão', 8), ('Marketing', 9),
('Advocacia Criminal', 11), ('Advocacia Trabalhista', 11), ('Zootecnia', 13), ('Sociologia', 14), ('Ecologia', 15),
('SEO', 16), ('Ilustração Digital', 17), ('Hotelaria', 18), ('Machine Learning', 19), ('Normas Regulamentadoras', 20),
('Gestão Empresarial', 21), ('Investimentos', 22), ('Transporte e Distribuição', 23), ('Educação Inclusiva', 24), ('Bioquímica', 25),
('Astrofísica', 26), ('Fotojornalismo', 27), ('Genética', 28), ('Metodologias Ágeis', 29), ('Terapia da Fala', 30);
