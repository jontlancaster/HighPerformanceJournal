CREATE DATABASE ClearlinkJournal;

USE ClearlinkJournal;

CREATE Table User
(
UserId int PRIMARY KEY AUTO_INCREMENT,
firstName varchar(255) NOT NULL,
lastName varchar(255),
username varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
userType INT NOT NULL,
createdDate DATETIME NOT NULL DEFAULT current_timestamp,
modifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
);

CREATE Table Journal
(
journalId int PRIMARY KEY AUTO_INCREMENT,
journalName varchar(255) NOT NULL,
userId int NOT NULL,
createdDate DATETIME NOT NULL DEFAULT current_timestamp,
modifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (userId) REFERENCES User(userId)
);

CREATE Table JournalEntry
(
journalEntryId int PRIMARY KEY AUTO_INCREMENT,
journalId int NOT NULL,
positiveReview varchar(1000),
goal varchar(1000),
momentum varchar(1000),
mentalToughness int NOT NULL,
willingness int NOT NULL,
determination int NOT NULL,
motivation int NOT NULL,
attitude int NOT NULL,
personalImpact int NOT NULL,
createdDate DATETIME NOT NULL DEFAULT current_timestamp,
modifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (journalId) REFERENCES Journal (journalId)
);

CREATE Table UserType
(
userTypeId int PRIMARY KEY,
userType int NOT NULL
);

