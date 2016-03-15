drop table gameupc;
drop table gamedevs;
drop table employee;
drop table store;
drop table isininventory;
drop table orders;
drop table ordercontains;
drop table customer;
drop table favoritegame;

CREATE TABLE gameupc(
    upc CHAR(12),
    title CHAR(140),
    year SMALLINT,
    platform CHAR(16),
    genre CHAR(16)
    PRIMARY KEY(UPC, platform),
    UNIQUE(title, year, platform)
);

CREATE TABLE gamedevs(
    title CHAR(140),
    year SMALLINT,
    devName CHAR(32),
    pubName CHAR(32),
    PRIMARY KEY(title, year)
);

CREATE TABLE employee (
    eid CHAR(8),
    sin CHAR(9),
    name CHAR(32),
    phone CHAR(10),
    wage SMALLINT,
    hours INTEGER,
    storeNum SMALLINT,
    isManager BOOLEAN,
    PRIMARY KEY(eid),
    UNIQUE(sin),
    UNIQUE(name, phone),
    Foreign Key(storeNum) references store
        ON DELETE SET DEFAULT
        ON UPDATE CASCADE
);


CREATE TABLE store (
    storeNum SMALLINT,
    city CHAR(16),
    streetAddress CHAR(64),
    PRIMARY KEY(storeNum)
);

CREATE TABLE IsInInventory (
    upc CHAR(12),
    platform CHAR(16),
    storeNum SMALLINT,
    stock SMALLINT,
    price FLOAT,
    PRIMARY KEY(upc, platform, storeNum)
    FOREIGN KEY(upc, platform) references gameupc
        ON DELETE CASCADE
        ON UPDATE CASCADE
    FOREIGN KEY(storeNum) references store
        ON DELETE CASCADE
        ON UPDATE CASCADE
 );

CREATE TABLE orders (
orderID INT,
date	DATE
email 	STRING,
PRIMARY KEY(orderID)
FOREIGN KEY(email) references customer
);


CREATE TABLE ordercontains (
    upc CHAR(12),
    platform CHAR(16),
    storeNum SMALLINT,
    orderID INT,
   quantity INT,
    PRIMARY KEY(orderID, upc, platform, storeNum)
    FOREIGN KEY(upc, platform) references game
        ON DELETE CASCADE
        ON UPDATE CASCADE
    FOREIGN KEY(storeNum) references store
        ON DELETE CASCADE
        ON UPDATE CASCADE
    FOREIGN KEY(orderID) references orders
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE customer(
	email CHAR(40),
	name CHAR(70),
	phone CHAR(10),
	PRIMARY KEY(email)
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE
);

CREATE TABLE favoritegame(
	email CHAR(40),
	upc CHAR(12),
	platform CHAR(16),
	PRIMARY KEY(email, upc, platform),
	FOREIGN KEY(email) references customer
		ON DELETE CASCADE
		ON UPDATE CASCADE
	FOREIGN KEY(upc, platform) references favoritegame
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


