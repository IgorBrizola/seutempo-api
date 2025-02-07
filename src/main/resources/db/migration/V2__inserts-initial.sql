-- Inserindo dados na tabela users
INSERT INTO users (name, last_name, email, password, cpf, phone, photo_user, date_anniversary, created_at, type_user, active)
VALUES
('João', 'Silva', 'joao.silva@seutempo.com', 'senha123', '123.456.789-00', '11 99999-9999', 'joao.jpg', '1990-05-15', GETDATE(), 'CLIENT', 1),
('Maria', 'Oliveira', 'maria.oliveira@seutempo.com', 'senha123', '987.654.321-00', '11 98888-8888', 'maria.jpg', '1985-08-22', GETDATE(), 'PROFESSIONAL', 1),
('Carlos', 'Pereira', 'carlos.pereira@seutempo.com', 'senha123', '321.654.987-00', '11 97777-7777', 'carlos.jpg', '1982-11-30', GETDATE(), 'CLIENT', 1),
('Ana', 'Souza', 'ana.souza@seutempo.com', 'senha123', '456.123.789-00', '11 96666-6666', 'ana.jpg', '1995-04-10', GETDATE(), 'PROFESSIONAL', 1),
('Fernanda', 'Lima', 'fernanda.lima@seutempo.com', 'senha123', '159.753.486-00', '11 95555-5555', 'fernanda.jpg', '1993-07-20', GETDATE(), 'CLIENT', 1),
('Ricardo', 'Melo', 'ricardo.melo@seutempo.com', 'senha123', '357.951.654-00', '11 94444-4444', 'ricardo.jpg', '1988-02-25', GETDATE(), 'PROFESSIONAL', 1),
('Juliana', 'Almeida', 'juliana.almeida@seutempo.com', 'senha123', '258.147.369-00', '11 93333-3333', 'juliana.jpg', '1997-09-05', GETDATE(), 'CLIENT', 1),
('Gabriel', 'Ferreira', 'gabriel.ferreira@seutempo.com', 'senha123', '369.852.147-00', '11 92222-2222', 'gabriel.jpg', '1980-06-18', GETDATE(), 'PROFESSIONAL', 1),
('Paula', 'Rodrigues', 'paula.rodrigues@seutempo.com', 'senha123', '741.852.963-00', '11 91111-1111', 'paula.jpg', '1983-03-12', GETDATE(), 'CLIENT', 1),
('Marcelo', 'Cunha', 'marcelo.cunha@seutempo.com', 'senha123', '852.963.741-00', '11 90000-0000', 'marcelo.jpg', '1991-12-08', GETDATE(), 'PROFESSIONAL', 1);

-- Inserindo dados na tabela client
INSERT INTO client (surname, id_users)
VALUES
('Silva', 1), ('Pereira', 3), ('Lima', 5), ('Almeida', 7), ('Rodrigues', 9);

-- Inserindo dados na tabela professional
INSERT INTO professional (link_professional, value_hour, cep, latitude, longitude, service_radius_km, location, id_users)
VALUES
('https://seutempo.com.br/st/maria-oliveira', 100.00, '01001-000',
 -23.550520, -46.633308, 10, geography::Point(-23.550520, -46.633308, 4326), 2),
('https://seutempo.com.br/st/ana-souza', 120.00, '01001-000',
 -23.550520, -46.633308, 10, geography::Point(-23.550520, -46.633308, 4326), 4),
('https://seutempo.com.br/st/ricardo-melo', 150.00, '01001-000',
 -23.550520, -46.633308, 10, geography::Point(-23.550520, -46.633308, 4326), 6),
('https://seutempo.com.br/st/gabriel-ferreira', 110.00, '01001-000',
 -23.550520, -46.633308, 10, geography::Point(-23.550520, -46.633308, 4326), 8),
('https://seutempo.com.br/st/marcelo-cunha', 130.00, '01001-000',
 -23.550520, -46.633308, 10, geography::Point(-23.550520, -46.633308, 4326), 10);

-- Inserindo dados na tabela address
INSERT INTO address (cep, state, city, neighborhood, street, number, complement, additional_address, type_address, latitude, longitude, location, id_client)
VALUES
('01000-000', 'SP', 'São Paulo', 'Centro', 'Rua A', 123, 'Apto 101', 'Próximo ao mercado', 'Residencial', -23.600000, -46.650000,
 geography::Point(-23.600000, -46.650000, 4326), 1),
('02000-000', 'RJ', 'Rio de Janeiro', 'Copacabana', 'Rua B', 456, 'Bloco 2', 'Ao lado do parque', 'Residencial', -23.600000, -46.650000,
 geography::Point(-23.600000, -46.650000, 4326), 2),
('03000-000', 'MG', 'Belo Horizonte', 'Savassi', 'Rua C', 789, 'Casa', 'Em frente ao shopping', 'Residencial', -23.600000, -46.650000,
 geography::Point(-23.600000, -46.650000, 4326), 3),
('04000-000', 'RS', 'Porto Alegre', 'Moinhos de Vento', 'Rua D', 101, 'Apto 202', 'Perto da praça', 'Residencial', -23.600000, -46.650000,
 geography::Point(-23.600000, -46.650000, 4326), 4),
('05000-000', 'PR', 'Curitiba', 'Batel', 'Rua E', 202, 'Cobertura', 'Vista para o mar', 'Residencial', -23.600000, -46.650000,
 geography::Point(-23.600000, -46.650000, 4326), 5);

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
