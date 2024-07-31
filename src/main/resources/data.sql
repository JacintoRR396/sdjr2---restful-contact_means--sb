INSERT INTO `dmd_address` (street, number, letter, town, city, country, postal_code, longitude, latitude, additional_info, created_at, created_by, updated_at, updated_by) VALUES
	('Avda Piedra el Gallo',22,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Pabellón de deportes Santa Lucía, Planta 1','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Corredera',155,'A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Vivo actualmente en el Piso','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Velázquez',17,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Bajo','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Avda Republica Nicaragua',1,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Piso','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Rosario',25,'B','Mairena del Alcor','Sevilla','España',41510,'-5.7199300','37.891060','Centro Deportivo','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Ancha',01,'N/A','Mairena del Alcor','Sevilla','España',41510,'-5.7199300','37.891060','Iglesia de la Merced','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN');

INSERT INTO `dmd_user` (username, pwd, nickname, email, last_access, is_active, created_at, created_by, updated_at, updated_by) VALUES
    ('sdjr2_001','pwd.Test_1234','erCalvo_-_21','sdjr2_001@gmail.com','2024-07-31 19:30',true,'2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN');

INSERT INTO `dmp_role` (name, description, created_at, created_by, updated_at, updated_by) VALUES
    ('ROLE_ADMIN','Monitors and manages both the configuration and functionalities of an application','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('ROLE_MEMBER','Registered user who can perform all the functionalities of a member','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('ROLE_USER','Guest user who can perform certain functionalities of a member','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN');

INSERT INTO `dmr_user_role` (user_id, role_id) VALUES
    (1,1),(1,2);