CREATE TABLE news(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
    title VARCHAR(255) NOT NULL,
    text VARCHAR(255) NOT NULL
);

CREATE TABLE comments(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date DATE NOT NULL,
	text VARCHAR(255) NOT NULL,
    username VARCHAR(32) NOT NULL,
	id_news BIGINT,
	CONSTRAINT fk_news FOREIGN KEY (id_news) REFERENCES news(id)
);

INSERT INTO news(date,title,text)
VALUES
    ('2022-09-04','News 1','Text 1'),
    ('2022-09-04','News 2','Text 2'),
    ('2022-09-04','News 3','Text 3'),
    ('2022-09-04','News 4','Text 4'),
    ('2022-09-04','News 5','Text 5'),
    ('2022-09-04','News 6','Text 6'),
    ('2022-09-04','News 7','Text 7'),
    ('2022-09-04','News 8','Text 8'),
    ('2022-09-04','News 9','Text 9'),
    ('2022-09-04','News 10','Text 10'),
    ('2022-09-04','News 11','Text 11'),
    ('2022-09-04','News 12','Text 12'),
    ('2022-09-04','News 13','Text 13'),
    ('2022-09-04','News 14','Text 14'),
    ('2022-09-04','News 15','Text 15'),
    ('2022-09-04','News 16','Text 16'),
    ('2022-09-04','News 17','Text 17'),
    ('2022-09-04','News 18','Text 18'),
    ('2022-09-04','News 19','Text 19'),
    ('2022-09-04','News 20','Text 20');

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',1),
    ('2022-09-04','Comment 2','user',1),
    ('2022-09-04','Comment 3','user',1),
    ('2022-09-04','Comment 4','user',1),
    ('2022-09-04','Comment 5','user',1),
    ('2022-09-04','Comment 6','user',1),
    ('2022-09-04','Comment 7','user',1),
    ('2022-09-04','Comment 8','user',1),
    ('2022-09-04','Comment 9','user',1),
    ('2022-09-04','Comment 10','user',1);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',2),
    ('2022-09-04','Comment 2','user',2),
    ('2022-09-04','Comment 3','user',2),
    ('2022-09-04','Comment 4','user',2),
    ('2022-09-04','Comment 5','user',2),
    ('2022-09-04','Comment 6','user',2),
    ('2022-09-04','Comment 7','user',2),
    ('2022-09-04','Comment 8','user',2),
    ('2022-09-04','Comment 9','user',2),
    ('2022-09-04','Comment 10','user',2);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',3),
    ('2022-09-04','Comment 2','user',3),
    ('2022-09-04','Comment 3','user',3),
    ('2022-09-04','Comment 4','user',3),
    ('2022-09-04','Comment 5','user',3),
    ('2022-09-04','Comment 6','user',3),
    ('2022-09-04','Comment 7','user',3),
    ('2022-09-04','Comment 8','user',3),
    ('2022-09-04','Comment 9','user',3),
    ('2022-09-04','Comment 10','user',3);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',4),
    ('2022-09-04','Comment 2','user',4),
    ('2022-09-04','Comment 3','user',4),
    ('2022-09-04','Comment 4','user',4),
    ('2022-09-04','Comment 5','user',4),
    ('2022-09-04','Comment 6','user',4),
    ('2022-09-04','Comment 7','user',4),
    ('2022-09-04','Comment 8','user',4),
    ('2022-09-04','Comment 9','user',4),
    ('2022-09-04','Comment 10','user',4);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',5),
    ('2022-09-04','Comment 2','user',5),
    ('2022-09-04','Comment 3','user',5),
    ('2022-09-04','Comment 4','user',5),
    ('2022-09-04','Comment 5','user',5),
    ('2022-09-04','Comment 6','user',5),
    ('2022-09-04','Comment 7','user',5),
    ('2022-09-04','Comment 8','user',5),
    ('2022-09-04','Comment 9','user',5),
    ('2022-09-04','Comment 10','user',5);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',6),
    ('2022-09-04','Comment 2','user',6),
    ('2022-09-04','Comment 3','user',6),
    ('2022-09-04','Comment 4','user',6),
    ('2022-09-04','Comment 5','user',6),
    ('2022-09-04','Comment 6','user',6),
    ('2022-09-04','Comment 7','user',6),
    ('2022-09-04','Comment 8','user',6),
    ('2022-09-04','Comment 9','user',6),
    ('2022-09-04','Comment 10','user',6);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',7),
    ('2022-09-04','Comment 2','user',7),
    ('2022-09-04','Comment 3','user',7),
    ('2022-09-04','Comment 4','user',7),
    ('2022-09-04','Comment 5','user',7),
    ('2022-09-04','Comment 6','user',7),
    ('2022-09-04','Comment 7','user',7),
    ('2022-09-04','Comment 8','user',7),
    ('2022-09-04','Comment 9','user',7),
    ('2022-09-04','Comment 10','user',7);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',8),
    ('2022-09-04','Comment 2','user',8),
    ('2022-09-04','Comment 3','user',8),
    ('2022-09-04','Comment 4','user',8),
    ('2022-09-04','Comment 5','user',8),
    ('2022-09-04','Comment 6','user',8),
    ('2022-09-04','Comment 7','user',8),
    ('2022-09-04','Comment 8','user',8),
    ('2022-09-04','Comment 9','user',8),
    ('2022-09-04','Comment 10','user',8);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',9),
    ('2022-09-04','Comment 2','user',9),
    ('2022-09-04','Comment 3','user',9),
    ('2022-09-04','Comment 4','user',9),
    ('2022-09-04','Comment 5','user',9),
    ('2022-09-04','Comment 6','user',9),
    ('2022-09-04','Comment 7','user',9),
    ('2022-09-04','Comment 8','user',9),
    ('2022-09-04','Comment 9','user',9),
    ('2022-09-04','Comment 10','user',9);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',10),
    ('2022-09-04','Comment 2','user',10),
    ('2022-09-04','Comment 3','user',10),
    ('2022-09-04','Comment 4','user',10),
    ('2022-09-04','Comment 5','user',10),
    ('2022-09-04','Comment 6','user',10),
    ('2022-09-04','Comment 7','user',10),
    ('2022-09-04','Comment 8','user',10),
    ('2022-09-04','Comment 9','user',10),
    ('2022-09-04','Comment 10','user',10);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',11),
    ('2022-09-04','Comment 2','user',11),
    ('2022-09-04','Comment 3','user',11),
    ('2022-09-04','Comment 4','user',11),
    ('2022-09-04','Comment 5','user',11),
    ('2022-09-04','Comment 6','user',11),
    ('2022-09-04','Comment 7','user',11),
    ('2022-09-04','Comment 8','user',11),
    ('2022-09-04','Comment 9','user',11),
    ('2022-09-04','Comment 10','user',11);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',12),
    ('2022-09-04','Comment 2','user',12),
    ('2022-09-04','Comment 3','user',12),
    ('2022-09-04','Comment 4','user',12),
    ('2022-09-04','Comment 5','user',12),
    ('2022-09-04','Comment 6','user',12),
    ('2022-09-04','Comment 7','user',12),
    ('2022-09-04','Comment 8','user',12),
    ('2022-09-04','Comment 9','user',12),
    ('2022-09-04','Comment 10','user',12);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',13),
    ('2022-09-04','Comment 2','user',13),
    ('2022-09-04','Comment 3','user',13),
    ('2022-09-04','Comment 4','user',13),
    ('2022-09-04','Comment 5','user',13),
    ('2022-09-04','Comment 6','user',13),
    ('2022-09-04','Comment 7','user',13),
    ('2022-09-04','Comment 8','user',13),
    ('2022-09-04','Comment 9','user',13),
    ('2022-09-04','Comment 10','user',13);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',14),
    ('2022-09-04','Comment 2','user',14),
    ('2022-09-04','Comment 3','user',14),
    ('2022-09-04','Comment 4','user',14),
    ('2022-09-04','Comment 5','user',14),
    ('2022-09-04','Comment 6','user',14),
    ('2022-09-04','Comment 7','user',14),
    ('2022-09-04','Comment 8','user',14),
    ('2022-09-04','Comment 9','user',14),
    ('2022-09-04','Comment 10','user',14);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',15),
    ('2022-09-04','Comment 2','user',15),
    ('2022-09-04','Comment 3','user',15),
    ('2022-09-04','Comment 4','user',15),
    ('2022-09-04','Comment 5','user',15),
    ('2022-09-04','Comment 6','user',15),
    ('2022-09-04','Comment 7','user',15),
    ('2022-09-04','Comment 8','user',15),
    ('2022-09-04','Comment 9','user',15),
    ('2022-09-04','Comment 10','user',15);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',16),
    ('2022-09-04','Comment 2','user',16),
    ('2022-09-04','Comment 3','user',16),
    ('2022-09-04','Comment 4','user',16),
    ('2022-09-04','Comment 5','user',16),
    ('2022-09-04','Comment 6','user',16),
    ('2022-09-04','Comment 7','user',16),
    ('2022-09-04','Comment 8','user',16),
    ('2022-09-04','Comment 9','user',16),
    ('2022-09-04','Comment 10','user',16);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',17),
    ('2022-09-04','Comment 2','user',17),
    ('2022-09-04','Comment 3','user',17),
    ('2022-09-04','Comment 4','user',17),
    ('2022-09-04','Comment 5','user',17),
    ('2022-09-04','Comment 6','user',17),
    ('2022-09-04','Comment 7','user',17),
    ('2022-09-04','Comment 8','user',17),
    ('2022-09-04','Comment 9','user',17),
    ('2022-09-04','Comment 10','user',17);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',18),
    ('2022-09-04','Comment 2','user',18),
    ('2022-09-04','Comment 3','user',18),
    ('2022-09-04','Comment 4','user',18),
    ('2022-09-04','Comment 5','user',18),
    ('2022-09-04','Comment 6','user',18),
    ('2022-09-04','Comment 7','user',18),
    ('2022-09-04','Comment 8','user',18),
    ('2022-09-04','Comment 9','user',18),
    ('2022-09-04','Comment 10','user',18);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',19),
    ('2022-09-04','Comment 2','user',19),
    ('2022-09-04','Comment 3','user',19),
    ('2022-09-04','Comment 4','user',19),
    ('2022-09-04','Comment 5','user',19),
    ('2022-09-04','Comment 6','user',19),
    ('2022-09-04','Comment 7','user',19),
    ('2022-09-04','Comment 8','user',19),
    ('2022-09-04','Comment 9','user',19),
    ('2022-09-04','Comment 10','user',19);

INSERT INTO comments(date,text,username,id_news)
VALUES
    ('2022-09-04','Comment 1','user',20),
    ('2022-09-04','Comment 2','user',20),
    ('2022-09-04','Comment 3','user',20),
    ('2022-09-04','Comment 4','user',20),
    ('2022-09-04','Comment 5','user',20),
    ('2022-09-04','Comment 6','user',20),
    ('2022-09-04','Comment 7','user',20),
    ('2022-09-04','Comment 8','user',20),
    ('2022-09-04','Comment 9','user',20),
    ('2022-09-04','Comment 10','user',20);