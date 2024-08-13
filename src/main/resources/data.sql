INSERT INTO `dmd_address` (street, number, letter, town, city, country, postal_code, longitude, latitude, additional_info, created_at, created_by, updated_at, updated_by) VALUES
	('Avda Piedra el Gallo',22,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Pabellón de deportes Santa Lucía, Planta 1','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Corredera',155,'A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Vivo actualmente en el Piso','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Velázquez',17,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Bajo','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Avda Republica Nicaragua',1,'N/A','El Viso del Alcor','Sevilla','España',41520,'-5.7199300','37.391060','Piso','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Rosario',25,'B','Mairena del Alcor','Sevilla','España',41510,'-5.7199300','37.891060','Centro Deportivo','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN'),
	('Ancha',01,'N/A','Mairena del Alcor','Sevilla','España',41510,'-5.7199300','37.891060','Iglesia de la Merced','2024-06-17 13:00','ROLE_ADMIN','2024-06-17 13:00','ROLE_ADMIN');

INSERT INTO `dmd_contact` (email, phone_mobile, phone_home, created_at, created_by, updated_at, updated_by) VALUES
    ('admin@gmail.com','666111444','955945165','2024-08-12 18:32','ROLE_ADMIN','2024-08-12 18:32','ROLE_ADMIN'),
	('sdjr2_001@gmail.com','666333999','955270000','2024-08-12 18:32','ROLE_ADMIN','2024-08-12 18:32','ROLE_ADMIN'),
	('rbg_001@gmail.com','666944116','955270001','2024-08-12 18:32','ROLE_ADMIN','2024-08-12 18:32','ROLE_ADMIN'),
	('test_00@gmail.com','666555444','955270011','2024-08-12 18:32','ROLE_ADMIN','2024-08-12 18:32','ROLE_ADMIN'),
	('test_01@gmail.com','666555440','955270021','2024-08-12 18:32','ROLE_ADMIN','2024-08-12 18:32','ROLE_ADMIN');

INSERT INTO `dmd_user` (username, pwd, nickname, email, enabled, last_access, created_at, created_by, updated_at, updated_by) VALUES
    ('admin','$2a$10$Xg6h/V/CiwE3EowwgDpeQelxl37x9gLSvL6XPfO4cEuuaRqEGWHKK','admin000','admin@gmail.com',true,'2024-07-31 19:30','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('sdjr2_001','$2a$10$Xg6h/V/CiwE3EowwgDpeQelxl37x9gLSvL6XPfO4cEuuaRqEGWHKK','erCalvo_-_21','sdjr2_001@gmail.com',true,'2024-07-31 19:30','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('rbg_001','$2a$10$Xg6h/V/CiwE3EowwgDpeQelxl37x9gLSvL6XPfO4cEuuaRqEGWHKK','lady_-_29','rbg_001@gmail.com',true,'2024-08-02 20:30','2024-08-02 20:30','ROLE_ADMIN','2024-08-02 20:30','ROLE_ADMIN'),
    ('test_00','$2a$10$Xg6h/V/CiwE3EowwgDpeQelxl37x9gLSvL6XPfO4cEuuaRqEGWHKK','machine00','test_00@gmail.com',true,'2024-08-02 20:30','2024-08-02 20:30','ROLE_ADMIN','2024-08-02 20:30','ROLE_ADMIN'),
    ('test_01','$2a$10$Xg6h/V/CiwE3EowwgDpeQelxl37x9gLSvL6XPfO4cEuuaRqEGWHKK','machine01','test_01@gmail.com',false,'2024-08-02 20:30','2024-08-02 20:30','ROLE_ADMIN','2024-08-02 20:30','ROLE_ADMIN');

INSERT INTO `dmp_role` (name, description, created_at, created_by, updated_at, updated_by) VALUES
    ('ROLE_ADMIN','Monitors and manages both the configuration and functionalities of an application','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('ROLE_MEMBER','Registered user who can perform all the functionalities of a member','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN'),
    ('ROLE_USER','Guest user who can perform certain functionalities of a member','2024-07-31 19:30','ROLE_ADMIN','2024-07-31 19:30','ROLE_ADMIN');

INSERT INTO `dmr_user_role` (user_id, role_id) VALUES
    (1,1),(2,1),(2,2),(3,2),(4,3),(5,3);