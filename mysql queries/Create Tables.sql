CREATE DATABASE clearlinkjournal;

USE clearlinkjournal;

CREATE Table usertype
(
user_type_id int PRIMARY KEY,
user_type varchar(24) NOT NULL
);

CREATE table user
(
user_id int PRIMARY KEY AUTO_INCREMENT,
first_name varchar(255) NOT NULL,
last_name varchar(255),
username varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
user_type INT NOT NULL,
enabled BOOL NOT NULL DEFAULT 1,
created_date DATETIME NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (user_type) REFERENCES usertype (user_type_id)
);

CREATE table journal
(
journal_id int PRIMARY KEY AUTO_INCREMENT,
journal_name varchar(255) NOT NULL,
user_id int NOT NULL,
created_date DATETIME NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (user_id) REFERENCES user(user_id)
);

CREATE Table journalentry
(
journal_entry_id int PRIMARY KEY AUTO_INCREMENT,
journal_id int NOT NULL,
positive_review varchar(1000),
goal varchar(1000),
momentum varchar(1000),
mental_toughness int NOT NULL,
willingness int NOT NULL,
determination int NOT NULL,
motivation int NOT NULL,
attitude int NOT NULL,
personal_impact int NOT NULL,
created_date DATETIME NOT NULL DEFAULT current_timestamp,
modified_date DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (journal_id) REFERENCES journal (journal_id) ON DELETE CASCADE
);


