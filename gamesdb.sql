drop table gameupc CASCADE CONSTRAINTS;
drop table gamedevs CASCADE CONSTRAINTS;
drop table employee CASCADE CONSTRAINTS;
drop table store CASCADE CONSTRAINTS;
drop table isininventory CASCADE CONSTRAINTS;
drop table orders CASCADE CONSTRAINTS;
drop table ordercontains CASCADE CONSTRAINTS;
drop table customer CASCADE CONSTRAINTS;
drop table favoritegame CASCADE CONSTRAINTS;


CREATE TABLE gamedevs(
    title CHAR(140),
    launchyear SMALLINT,
    devName CHAR(32),
    pubName CHAR(32),
    PRIMARY KEY(title, launchyear)
);

CREATE TABLE gameupc(
    upc CHAR(12),
    title CHAR(140),
    launchyear SMALLINT,
    platform CHAR(16),
    genre CHAR(16),
    PRIMARY KEY(UPC, platform),
    UNIQUE(title, launchyear, platform),
    FOREIGN KEY(title, launchyear) references gamedevs
        ON DELETE CASCADE
);

CREATE TABLE store (
    storeNum SMALLINT,
    city CHAR(16),
    streetAddress CHAR(64),
    PRIMARY KEY(storeNum)
);

CREATE TABLE employee (
    eid CHAR(8),
    ssin CHAR(9),
    name CHAR(32),
    phone CHAR(10),
    wage SMALLINT,
    hours INTEGER,
    storeNum SMALLINT,
    CHECK (wage >= 10),
    isManager CHAR(1),  /*no boolean type replaced with char*/
    PRIMARY KEY(eid),
    UNIQUE(ssin),
    UNIQUE(name, phone),
    FOREIGN KEY(storeNum) references store
        ON DELETE SET NULL         /*or cascade*/
);




CREATE TABLE isininventory (
    upc CHAR(12),
    platform CHAR(16),
    storeNum SMALLINT,
    stock SMALLINT,
    price SMALLINT,
    PRIMARY KEY(upc, platform, storeNum),
    FOREIGN KEY(upc, platform) references gameupc
        ON DELETE CASCADE,
    FOREIGN KEY(storeNum) references store
        ON DELETE CASCADE
 );
 
CREATE TABLE customer(
    email CHAR(60),
    name CHAR(70),
    phone CHAR(10),
    PRIMARY KEY(email) /*or set null?*/
);

CREATE TABLE orders (
    orderID INT,
    odate    DATE,
    email     CHAR(60),
    PRIMARY KEY(orderID),
    FOREIGN KEY(email) references customer
);


CREATE TABLE ordercontains (
    upc CHAR(12),
    platform CHAR(16),
    storeNum SMALLINT,
    orderID INT,
    quantity INT,
    PRIMARY KEY(orderID, upc, platform, storeNum),
    FOREIGN KEY(upc, platform) references gameupc
        ON DELETE CASCADE,
    FOREIGN KEY(storeNum) references store
        ON DELETE CASCADE,
    FOREIGN KEY(orderID) references orders
        ON DELETE CASCADE
);



CREATE TABLE favoritegame(
    email CHAR(60),
    upc CHAR(12),
    platform CHAR(16),
    PRIMARY KEY(email, upc, platform),
    FOREIGN KEY(email) references customer
        ON DELETE CASCADE,
    FOREIGN KEY(upc, platform) references gameupc
        ON DELETE CASCADE
);


INSERT INTO gamedevs VALUES ('The legend of Zelda', 1990, 'Nintendo', 'Nintendo');
INSERT INTO gamedevs VALUES ('Tony Hawks Pro Skater 2', 2000, 'Neversoft', 'Activision');
INSERT INTO gamedevs VALUES ('Grand Theft Auto IV', 2008, 'Rockstar North', 'Rockstar Games');
INSERT INTO gamedevs VALUES ('SoulCalibur', 1999, 'Project Soul', 'Namco');
INSERT INTO gamedevs VALUES ('Super Mario Galaxy', 2007, 'Nintendo', 'Nintendo');
INSERT INTO gamedevs VALUES ('Halo: Combat Evolved', 2001, 'Bungie', 'Microsoft Game Studios');
INSERT INTO gamedevs VALUES ('Half-Life 2', 2004, 'Valve', 'Valve');
INSERT INTO gamedevs VALUES ('Tekken 3', 1998, 'Namco', 'Namco');
INSERT INTO gamedevs VALUES ('Gran Turismo', 1998, 'Polyphony Digital', 'Sony');
INSERT INTO gamedevs VALUES ('Grand Theft Auto:San Andreas', 2004, 'Rockstar North', 'Rockstar Games');
INSERT INTO gamedevs VALUES ('Rayman 10: Snakes', 2020, 'Ubisoft', 'Ubisoft');
INSERT INTO gamedevs VALUES ('Rocket League 2', 2021, 'Psyonix', 'Psyonix');
INSERT INTO gamedevs VALUES ('Tekken 3', 1999, 'Namco', 'Namco');
INSERT INTO gamedevs VALUES ('Gran Turismo', 1999, 'Polyphony Digital', 'Sony');
INSERT INTO gamedevs VALUES ('The legend of Zelda', 1991, 'Nintendo', 'Nintendo');
INSERT INTO gamedevs VALUES ('Batman: Retirement Sucks', 2020, 'Rocksteady', 'Warner Bros');

INSERT INTO gameupc VALUES ('222233334444', 'The legend of Zelda', 1990, 'PC', 'Fantasy'); 
INSERT INTO gameupc VALUES ('000000000001', 'Tony Hawks Pro Skater 2', 2000, 'PS', 'Sports');
INSERT INTO gameupc VALUES ('000000000002', 'Grand Theft Auto IV', 2008, 'PS3', 'Action');
INSERT INTO gameupc VALUES ('000000000003', 'SoulCalibur', 1999, 'DC', 'Action');
INSERT INTO gameupc VALUES ('000000000004', 'Super Mario Galaxy', 2007, 'WII', 'Action');   
INSERT INTO gameupc VALUES ('000000000005', 'Halo: Combat Evolved', 2001, 'XBOX', 'Action');    
INSERT INTO gameupc VALUES ('000000000006', 'Half-Life 2', 2004, 'PC', 'Action');
INSERT INTO gameupc VALUES ('000000000007', 'Tekken 3', 1998, 'PS', 'Action');
INSERT INTO gameupc VALUES ('000000000008', 'Gran Turismo', 1998, 'PS', 'Racing');
INSERT INTO gameupc VALUES ('000000000009', 'Grand Theft Auto:San Andreas', 2004, 'PS2', 'Action');
INSERT INTO gameupc VALUES ('000000000010', 'Rayman 10: Snakes', 2020, 'PS5', 'Adventure');
INSERT INTO gameupc VALUES ('000000000011', 'Rocket League 2', 2021, 'PS5', 'Sports');
INSERT INTO gameupc VALUES ('000000000012', 'Tekken 3', 1999, 'PC', 'Action');
INSERT INTO gameupc VALUES ('000000000013', 'Gran Turismo', 1999, 'PC', 'Racing');
INSERT INTO gameupc VALUES ('000000000014', 'The legend of Zelda', 1991, 'PS', 'Fantasy');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'PS5', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'PS', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'PC', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'PS3', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'DC', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'WII', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'XBOX', 'Action');
INSERT INTO gameupc VALUES ('000000000015', 'Batman: Retirement Sucks', 2020, 'PS2', 'Action');

INSERT INTO store VALUES (0, 'Vancouver', '2205 Lower Mall'); 
INSERT INTO store VALUES (1, 'Burnaby', 'Burnaby lane');
INSERT INTO store VALUES (2, 'Vancouver', 'Pacific Centre');

INSERT INTO employee VALUES ('00000000', '000000000', 'John Applewood', '7788881294', 100000, 6, 0, '1'); 
INSERT INTO employee VALUES ('00000001', '111111111', 'Mark Johnson', '7788881295', 40000, 8, 0, '0');
INSERT INTO employee VALUES ('00000002', '222222222', 'John Bigmac', '7788881296', 40000, 8, 0, '0');
INSERT INTO employee VALUES ('00000003', '333333333', 'Cake Johnson', '7788881297', 40000, 8, 0, '0');
INSERT INTO employee VALUES ('00000004', '444444444', 'Mark Twain', '7788881298', 40000, 8, 0, '0');
INSERT INTO employee VALUES ('00000005', '555555555', 'Elon Musk', '7788881299', 40000, 8, 0, '0');
INSERT INTO employee VALUES ('00000006', '666666666', 'Tim Cook', '7788881300', 100000, 6, 1, '1');
INSERT INTO employee VALUES ('00000007', '777777777', 'Shinji Kagawa', '7788881301', 40000, 8, 1, '0');
INSERT INTO employee VALUES ('00000008', '888888888', 'Bill Gates', '7788881302', 40000, 8, 1, '0');
INSERT INTO employee VALUES ('00000009', '999999999', 'Hiro Nakamura', '7788881303', 40000, 8, 1, '0');
INSERT INTO employee VALUES ('00000010', '111111110', 'James Harden', '7799999999', 40000, 8, 1, '0');
INSERT INTO employee VALUES ('00000011', '111122220', 'James Cook', '7786666666', 100000, 6, 2, '1');
INSERT INTO employee VALUES ('00000012', '121212121', 'John Snow', '7787777777', 40000, 8, 2, '0');
INSERT INTO employee VALUES ('00000013', '131313131', 'John Snowed', '7787777776', 40000, 8, 2, '0');
INSERT INTO employee VALUES ('00000014', '141414141', 'John Rain', '7787777779', 40000, 8, 2, '0');

INSERT INTO isininventory VALUES('222233334444', 'PC', 0, 0, 50); 
INSERT INTO isininventory VALUES('000000000001', 'PS', 0, 100, 50);
INSERT INTO isininventory VALUES('000000000002', 'PS3', 0, 1, 50);
INSERT INTO isininventory VALUES('000000000003', 'DC', 0, 2, 50);
INSERT INTO isininventory VALUES('000000000004', 'WII', 0, 200, 50);
INSERT INTO isininventory VALUES('000000000005', 'XBOX', 0, 0, 50);
INSERT INTO isininventory VALUES('000000000006', 'PC', 0, 0, 50);
INSERT INTO isininventory VALUES('000000000007', 'PS', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000008', 'PS', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000009', 'PS2', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000010', 'PS5', 0, 10, 100);
INSERT INTO isininventory VALUES('000000000011', 'PS5', 0, 10, 100);
INSERT INTO isininventory VALUES('000000000012', 'PC', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000013', 'PC', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000014', 'PS', 0, 10, 50);
INSERT INTO isininventory VALUES('000000000015', 'PS5', 0, 10, 50);

INSERT INTO isininventory VALUES('222233334444', 'PC', 1, 1, 50); 
INSERT INTO isininventory VALUES('000000000001', 'PS', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000002', 'PS3', 1, 3, 50);
INSERT INTO isininventory VALUES('000000000003', 'DC', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000004', 'WII', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000005', 'XBOX', 1, 4, 50);
INSERT INTO isininventory VALUES('000000000006', 'PC', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000007', 'PS', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000008', 'PS', 1, 0, 50);
INSERT INTO isininventory VALUES('000000000009', 'PS2', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000010', 'PS5', 1, 0, 100);
INSERT INTO isininventory VALUES('000000000011', 'PS5', 1, 10, 100);
INSERT INTO isininventory VALUES('000000000012', 'PC', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000013', 'PC', 1, 6, 50);
INSERT INTO isininventory VALUES('000000000014', 'PS', 1, 10, 50);
INSERT INTO isininventory VALUES('000000000015', 'PS5', 1, 10, 50);

INSERT INTO isininventory VALUES('222233334444', 'PC', 2, 0, 50); 
INSERT INTO isininventory VALUES('000000000001', 'PS', 2, 1, 50);
INSERT INTO isininventory VALUES('000000000002', 'PS3', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000003', 'DC', 2, 2, 50);
INSERT INTO isininventory VALUES('000000000004', 'WII', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000005', 'XBOX', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000006', 'PC', 2, 4, 50);
INSERT INTO isininventory VALUES('000000000007', 'PS', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000008', 'PS', 2, 6, 50);
INSERT INTO isininventory VALUES('000000000009', 'PS2', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000010', 'PS5', 2, 5, 100);
INSERT INTO isininventory VALUES('000000000011', 'PS5', 2, 10, 100);
INSERT INTO isininventory VALUES('000000000012', 'PC', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000013', 'PC', 2, 3, 50);
INSERT INTO isininventory VALUES('000000000014', 'PS', 2, 10, 50);
INSERT INTO isininventory VALUES('000000000015', 'PS5', 2, 10, 50);

INSERT INTO customer VALUES ('ron@gmail.com', 'Ron Burgundy', '7761111111'); 
INSERT INTO customer VALUES ('seed@gmail.com', 'Seed Rose','7761111112'); 
INSERT INTO customer VALUES ('rahul@gmail.com', 'Rahul Naik', '7771111111'); 
INSERT INTO customer VALUES ('john@gmail.com', 'John Olive', '7772222222'); 
INSERT INTO customer VALUES ('radu@gmail.com', 'Radu Sadu', '6041111111'); 
INSERT INTO customer VALUES ('radu@hotmail.com', 'Radu Jadu', '6041234333'); 
INSERT INTO customer VALUES ('mark@gmail.com', 'Mark Ryerson', '6041234568'); 
INSERT INTO customer VALUES ('rohin@gmail.com', 'Rohin Patel', '7711234111'); 
INSERT INTO customer VALUES ('spencer@gmail.com', 'Spencer Lee', '6045555555'); 
INSERT INTO customer VALUES ('laks@gmail.com', 'Laks Laksman', '7789999999');
INSERT INTO customer VALUES ('kevin@gmail.com', 'Kevin Chiang', '7781234123'); 
INSERT INTO customer VALUES ('fekre@gmail.com', 'Fekre Mulugeta', '7783212778'); 
INSERT INTO customer VALUES ('vishnu@gmail.com', 'Vishnu Bijju', '7783212776'); 
INSERT INTO customer VALUES ('amaury@gmail.com', 'Amaury Brown', '7783212774'); 
INSERT INTO customer VALUES ('andrew@gmail.com', 'Andrew Fisher', '7786861294'); 
INSERT INTO customer VALUES ('asisha@gmail.com', 'Asisha Sachdev', '7783212779'); 

INSERT INTO orders VALUES(0,'01-01-16','ron@gmail.com'); 
INSERT INTO orders VALUES(1, '01-01-16', 'seed@gmail.com');
INSERT INTO orders VALUES(2, '01-01-16', 'rahul@gmail.com');
INSERT INTO orders VALUES(3, '01-01-16', 'john@gmail.com');
INSERT INTO orders VALUES(4, '01-01-16', 'radu@gmail.com');
INSERT INTO orders VALUES(5, '01-01-16', 'radu@hotmail.com');
INSERT INTO orders VALUES(6, '01-01-16', 'mark@gmail.com');
INSERT INTO orders VALUES(7, '01-01-16', 'rohin@gmail.com');
INSERT INTO orders VALUES(8, '01-01-16', 'spencer@gmail.com');
INSERT INTO orders VALUES(9, '01-01-16', 'laks@gmail.com');
INSERT INTO orders VALUES(10, '01-01-16', 'kevin@gmail.com');
INSERT INTO orders VALUES(11, '01-01-16', 'fekre@gmail.com');
INSERT INTO orders VALUES(12, '01-01-16', 'vishnu@gmail.com');
INSERT INTO orders VALUES(13, '01-01-16', 'amaury@gmail.com');
INSERT INTO orders VALUES(14, '01-01-16', 'andrew@gmail.com');
INSERT INTO orders VALUES(15, '01-01-16', 'asisha@gmail.com');

INSERT INTO orders VALUES(16,'01-02-16','ron@gmail.com'); 
INSERT INTO orders VALUES(17, '01-02-16', 'seed@gmail.com');
INSERT INTO orders VALUES(18, '01-02-16', 'rahul@gmail.com');
INSERT INTO orders VALUES(19, '01-02-16', 'john@gmail.com');
INSERT INTO orders VALUES(20, '01-02-16', 'radu@gmail.com');
INSERT INTO orders VALUES(21, '01-03-16', 'radu@hotmail.com');
INSERT INTO orders VALUES(22, '01-03-16', 'mark@gmail.com');
INSERT INTO orders VALUES(23, '01-03-16', 'rohin@gmail.com');
INSERT INTO orders VALUES(24, '01-03-16', 'spencer@gmail.com');
INSERT INTO orders VALUES(25, '01-03-16', 'laks@gmail.com');
INSERT INTO orders VALUES(26, '01-04-16', 'kevin@gmail.com');
INSERT INTO orders VALUES(27, '01-04-16', 'fekre@gmail.com');
INSERT INTO orders VALUES(28, '01-04-16', 'vishnu@gmail.com');
INSERT INTO orders VALUES(29, '01-04-16', 'amaury@gmail.com');
INSERT INTO orders VALUES(30, '01-04-16', 'andrew@gmail.com');
INSERT INTO orders VALUES(31, '01-04-16', 'asisha@gmail.com');

INSERT INTO ordercontains VALUES ('222233334444', 'PC', 0, 0, 1); 
INSERT INTO ordercontains VALUES ('000000000001', 'PS', 0, 1, 1);
INSERT INTO ordercontains VALUES ('000000000002', 'PS3', 0, 2, 1);
INSERT INTO ordercontains VALUES ('000000000003', 'DC', 0, 3, 1);
INSERT INTO ordercontains VALUES ('000000000004', 'WII', 0, 4, 1);
INSERT INTO ordercontains VALUES ('000000000005', 'XBOX', 0, 5, 1);
INSERT INTO ordercontains VALUES ('000000000006', 'PC', 0, 6, 1);
INSERT INTO ordercontains VALUES ('000000000007', 'PS', 0, 7, 1);
INSERT INTO ordercontains VALUES ('000000000008', 'PS', 0, 8, 1);
INSERT INTO ordercontains VALUES ('000000000009', 'PS2', 0, 9, 1);
INSERT INTO ordercontains VALUES ('000000000010', 'PS5', 1, 10, 3);
INSERT INTO ordercontains VALUES ('000000000011', 'PS5', 1, 11, 2);
INSERT INTO ordercontains VALUES ('000000000012', 'PC', 1, 12, 2);
INSERT INTO ordercontains VALUES ('000000000013', 'PC', 1, 13, 1);
INSERT INTO ordercontains VALUES ('000000000014', 'PS', 1, 14, 1);

INSERT INTO favoritegame VALUES('ron@gmail.com','222233334444', 'PC');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000001', 'PS');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000002', 'PS3');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000003', 'DC');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000004', 'WII');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000005', 'XBOX');
INSERT INTO favoritegame VALUES('ron@gmail.com','000000000006', 'PC');

