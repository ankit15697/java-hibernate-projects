CREATE TABLE `training`.`branch` (
branchCode varchar(4) primary key,
branchName varchar(20)
);

CREATE TABLE customer(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(20),
    balance double(10,2),
    accountId varchar(8),
    branchCode varchar(4),
    PRIMARY KEY (id),
    FOREIGN KEY (branchcode) REFERENCES branch(branchCode)
);