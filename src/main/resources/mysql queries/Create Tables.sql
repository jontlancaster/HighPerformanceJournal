CREATE DATABASE HPJournal;

USE HPJournal;

CREATE Table User
(
UserId int PRIMARY KEY AUTO_INCREMENT,
firstName varchar(255) NOT NULL,
lastName varchar(255),
username varchar(255) NOT NULL UNIQUE,
password varchar(255) NOT NULL,
userType INT NOT NULL,
createdDate DATETIME DEFAULT current_timestamp,
modifiedDate DATETIME DEFAULT current_timestamp ON UPDATE current_timestamp
);

CREATE Table Journal
(
journalId int PRIMARY KEY AUTO_INCREMENT,
journalName varchar(255) NOT NULL,
userId int NOT NULL,
createdDate DATETIME NOT NULL DEFAULT current_timestamp,
modifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (UserId) REFERENCES User(UserId)
);

CREATE Table JournalEntry
(
journalEntryId int PRIMARY KEY AUTO_INCREMENT,
journalId int NOT NULL,
mainEntry varchar(1000),
desiredOutcome varchar(256),
actionItem1 varchar(256),
actionItem2 varchar(256),
actionItem3 varchar(256),
motiviation int NOT NULL,
confidence int NOT NULL,
visualizing int NOT NULL,
actionOrientation int NOT NULL,
decisionmaking int NOT NULL,
createdDate DATETIME DEFAULT current_timestamp,
modifiedDate DATETIME DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (JournalId) REFERENCES Journal (JournalId)
);

CREATE Table UserType
(
userTypeId int PRIMARY KEY,
userType int NOT NULL
);

