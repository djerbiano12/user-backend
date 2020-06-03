-- CREATE TABLE IF NOT EXISTS user_roles (
--  user_id bigint(20) NOT NULL,
--  role_id bigint(20) NOT NULL,
--  PRIMARY KEY (user_id,role_id),
--  KEY role_id (role_id)
-- ) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

-- CREATE TABLE IF NOT EXISTS roles (
--  id bigint(20) NOT NULL AUTO_INCREMENT,
--  created_on bigint(20) DEFAULT NULL,
--  description varchar(255) DEFAULT NULL,
--  modified_on bigint(20) DEFAULT NULL,
--  name varchar(255) DEFAULT NULL,
--  PRIMARY KEY (id)
-- ) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- CREATE TABLE utilisateur (
-- id int(11) NOT NULL AUTO_INCREMENT,
-- email varchar(255) DEFAULT NULL,
-- first_name varchar(255) DEFAULT NULL,
-- last_name varchar(255) DEFAULT NULL,
-- password varchar(255) CHARACTER SET utf8 NOT NULL,
-- phone_number varchar(50) NOT NULL,
-- locked tinyint(1) NOT NULL,
-- PRIMARY KEY (id)
--) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4


-- delete from user_roles;
-- delete from user_roles;
-- delete from utilisateur;
-- commit;

-- INSERT INTO utilisateur (id, email, first_name, last_name, password, phone_number) VALUES
-- (20, 'aminebms4@gmail.com', 'BEN MOUSSA', 'Amine', '$2a$10$OdrPh/jovppNokZzJNKnnOTyzwjuRxpdSrfJ7.4ttZwdQ4WEe7R6e', '0623861650'),
-- (25, 'user@user.fr', 'user', 'user', '$2a$10$t5OOMz6J4ItJzzVBP9OUpOrMDMd1PaokuP73nn8Zvw2T00KWr9C3m', '1234567890'),
-- (29, 'aaaaa@aaa.fr', 'aaaaa', 'aaaaaaa', '$2a$10$OiV2XXGVfZCufnNpuYvfk.GOMVQfiAcDRHU0XsBFu45rZv2Z8n3FK', '1212121212'),
-- (30, 'bbbbb@bbb.fr', 'bbbb', 'bbbbbb', '$2a$10$R/L6kMLUyXbpnUGpJjYWP.1BZ8tMv.zw4C9Bw8Vc9LN2juioWdxlW', '1234567890'),
--( 31, 'ccc@cc.fr', 'cccccc', 'ccccccc', '$2a$10$y3AeLQ3m3E2Tiu5ATZpvo.omSPhnzqpCMkWrmOzqtN9.xYiLJu7HS', '0122122132'),
-- (32, 'ddd@dd.fr', 'dddd', 'ddd', '$2a$10$HudB.I.J2Ft1F7Ig2SoXP.WCcXbw0C9y4QDJUduYafcVjM6Nzl.XK', '0202020202');

-- INSERT INTO roles (id, created_on, description, modified_on, name) VALUES
-- (1, NULL, 'Admin', NULL, 'ADMIN'),
-- (2, NULL, 'User', NULL, 'USER');

-- INSERT INTO user_roles (user_id, role_id) VALUES
-- (20, 1);
-- commit;