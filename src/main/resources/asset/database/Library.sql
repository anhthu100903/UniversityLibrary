CREATE TABLE `Author` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(30) NOT NULL,
  `year` int NOT NULL,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Category` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(30) NOT NULL,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Publisher` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(30) NOT NULL,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Book` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(100) NOT NULL,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `BookAuthor` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `authorID` int NOT NULL,
  `bookID` int NOT NULL
);

CREATE TABLE `BookCategory` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `categoryID` int NOT NULL,
  `bookID` int NOT NULL
);

CREATE TABLE `VersionOfBook` (
  `ISBN` varchar(17) UNIQUE,
  `bookID` int,
  `img` varchar(100) UNIQUE,
  `publisherID` int,
  `edition` nvarchar(50),
  `location` varchar(20),
  `price` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT 0,
  `available` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`ISBN`, `bookID`)
);

CREATE TABLE `Person` (
  `id` varchar(10) UNIQUE PRIMARY KEY,
  `name` nvarchar(30) NOT NULL,
  `tel` varchar(10) UNIQUE,
  `address` nvarchar(50),
  `schoolYear` varchar(9),
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Role` (
  `id` varchar(2) PRIMARY KEY,
  `name` nvarchar(20) UNIQUE NOT NULL
);

CREATE TABLE `Account` (
  `id` varchar(10) PRIMARY KEY,
  `password` varchar(200) NOT NULL,
  `positionID` varchar(2) NOT NULL,
  `dayCreated` date DEFAULT (CURRENT_DATE),
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Borrowing` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `readerID` varchar(10) NOT NULL,
  `borrowStaffID` varchar(10) NOT NULL,
  `returnStaffID` varchar(10),
  `borrowDate` date DEFAULT (CURRENT_DATE),
  `dueDate` date NOT NULL,
  `returnDate` date,
  `delay` bool DEFAULT 0,
  `fine` bigint DEFAULT 0,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `BorrowDetail` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `borrowID` int NOT NULL,
  `ISBN` varchar(17) NOT NULL,
  `quantity` smallint NOT NULL,
  `description` nvarchar(200),
  `lost` smallint DEFAULT 0,
  `broke` smallint DEFAULT 0
);

CREATE TABLE `Supplier` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` nvarchar(30) NOT NULL,
  `address` nvarchar(100),
  `tel` varchar(10),
  `isActive` bool DEFAULT 1
);

CREATE TABLE `Importing` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `supplierID` int,
  `staffID` varchar(10) NOT NULL,
  `importDate` date DEFAULT (CURRENT_DATE),
  `fee` bigint DEFAULT 0,
  `isActive` bool DEFAULT 1
);

CREATE TABLE `ImportDetail` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `importID` int,
  `ISBN` varchar(17),
  `quantity` int NOT NULL
);

CREATE UNIQUE INDEX `BookAuthor_index_0` ON `BookAuthor` (`authorID`, `bookID`);

CREATE UNIQUE INDEX `BookCategory_index_1` ON `BookCategory` (`categoryID`, `bookID`);

CREATE UNIQUE INDEX `BorrowDetail_index_2` ON `BorrowDetail` (`borrowID`, `ISBN`);

CREATE UNIQUE INDEX `ImportDetail_index_3` ON `ImportDetail` (`importID`, `ISBN`);

ALTER TABLE `BookAuthor` ADD FOREIGN KEY (`authorID`) REFERENCES `Author` (`id`);

ALTER TABLE `BookAuthor` ADD FOREIGN KEY (`bookID`) REFERENCES `Book` (`id`);

ALTER TABLE `BookCategory` ADD FOREIGN KEY (`categoryID`) REFERENCES `Category` (`id`);

ALTER TABLE `BookCategory` ADD FOREIGN KEY (`bookID`) REFERENCES `Book` (`id`);

ALTER TABLE `VersionOfBook` ADD FOREIGN KEY (`bookID`) REFERENCES `Book` (`id`);

ALTER TABLE `VersionOfBook` ADD FOREIGN KEY (`publisherID`) REFERENCES `Publisher` (`id`);

ALTER TABLE `Account` ADD FOREIGN KEY (`id`) REFERENCES `Person` (`id`);

ALTER TABLE `Account` ADD FOREIGN KEY (`positionID`) REFERENCES `Role` (`id`);

ALTER TABLE `Borrowing` ADD FOREIGN KEY (`readerID`) REFERENCES `Person` (`id`);

ALTER TABLE `Borrowing` ADD FOREIGN KEY (`borrowStaffID`) REFERENCES `Person` (`id`);

ALTER TABLE `Borrowing` ADD FOREIGN KEY (`returnStaffID`) REFERENCES `Person` (`id`);

ALTER TABLE `BorrowDetail` ADD FOREIGN KEY (`borrowID`) REFERENCES `Borrowing` (`id`);

ALTER TABLE `BorrowDetail` ADD FOREIGN KEY (`ISBN`) REFERENCES `VersionOfBook` (`ISBN`);

ALTER TABLE `Importing` ADD FOREIGN KEY (`supplierID`) REFERENCES `Supplier` (`id`);

ALTER TABLE `Importing` ADD FOREIGN KEY (`staffID`) REFERENCES `Person` (`id`);

ALTER TABLE `ImportDetail` ADD FOREIGN KEY (`importID`) REFERENCES `Importing` (`id`);

ALTER TABLE `ImportDetail` ADD FOREIGN KEY (`ISBN`) REFERENCES `VersionOfBook` (`ISBN`);
