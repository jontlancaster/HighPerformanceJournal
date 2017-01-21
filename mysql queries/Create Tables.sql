CREATE DATABASE HPJournal;

USE HPJournal;

CREATE Table User
(
UserId int PRIMARY KEY AUTO_INCREMENT,
FirstName varchar(255) NOT NULL,
LastName varchar(255),
Username varchar(255) NOT NULL UNIQUE, 
Password varchar(255) NOT NULL,
UserType INT NOT NULL,
CreatedDate DATETIME NOT NULL DEFAULT current_timestamp,
ModifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
);

CREATE Table Journal
(
JournalId int PRIMARY KEY AUTO_INCREMENT,
JournalName varchar(255) NOT NULL,
UserId int NOT NULL,
CreatedDate DATETIME NOT NULL DEFAULT current_timestamp,
ModifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (UserId) REFERENCES User(UserId)
);

CREATE Table JournalEntry
(
JournalEntryId int PRIMARY KEY AUTO_INCREMENT,
JournalId int NOT NULL,
MainEntry varchar(1000),
DesiredOutcome varchar(256),
ActionItem1 varchar(256),
ActionItem2 varchar(256),
ActionItem3 varchar(256),
Motiviation int NOT NULL,
Confidence int NOT NULL,
Visualizaing int NOT NULL,
ActionOrientation int NOT NULL,
Decisionmaking int NOT NULL,
CreatedDate DATETIME NOT NULL DEFAULT current_timestamp,
ModifiedDate DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp,
FOREIGN KEY (JournalId) REFERENCES Journal (JournalId)
);

CREATE Table UserType
(
UserTypeId int PRIMARY KEY,
UserType int NOT NULL
);