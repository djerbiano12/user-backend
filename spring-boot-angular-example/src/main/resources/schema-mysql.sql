CREATE TABLE IF NOT EXISTS user_roles (
 user_id bigint(20) NOT NULL,
 role_id bigint(20) NOT NULL,
 PRIMARY KEY (user_id,role_id),
 KEY role_id (role_id)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS roles (
 id bigint(20) NOT NULL AUTO_INCREMENT,
 created_on bigint(20) DEFAULT NULL,
 description varchar(255) DEFAULT NULL,
 modified_on bigint(20) DEFAULT NULL,
 name varchar(255) DEFAULT NULL,
 PRIMARY KEY (id)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS utilisateur (
 id int(11) NOT NULL AUTO_INCREMENT,
 email varchar(255) DEFAULT NULL,
 first_name varchar(255) DEFAULT NULL,
 last_name varchar(255) DEFAULT NULL,
 password varchar(255) CHARACTER SET utf8 NOT NULL,
 PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

delete from user_roles;
delete from user_roles;
delete from utilisateur;
commit;

INSERT INTO utilisateur (id, email, first_name, last_name, password) VALUES
(20, 'aminebms4@gmail.com', 'BEN MOUSSA', 'Amine', '$2a$10$wYrEMoGV.UZBDQYa57ucGuDR64IpBPMSmi1NTk79QPSfSFSmhB5aa'),
(21, 'system@system.fr', 'system', 'system', '$2a$10$SZ4GCJ8XApw/BgT5E6CG1egu8z3uNwQYht.207RUna7WDRgT4IYCm'),
(25, 'user@user.fr', 'user', 'user', '$2a$10$9CL7G3oXolIddA9e5lmSgOK6yL5lfxCAw3ujiFfIL.HN6t1gfvszy'),
(27, 'benmoussaamine@hotmail.fr', 'ben moussa', 'ben moussa', '$2a$10$FGMC5.iTuOe5KvzIF7JFVedKx6u/LCwX/q4hm.b9wqkPcZxWPxplK'),
(28, 'amine@amine.amine', 'amine', 'amine', '$2a$10$oLfu0MhBYkY.W37dge/63eEU9KGYI7ZiJLcSp2nN97FsrDRCchCMG');

INSERT INTO roles (id, created_on, description, modified_on, name) VALUES
(1, NULL, 'Admin', NULL, 'ADMIN'),
(2, NULL, 'User', NULL, 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES
(20, 1);
commit;