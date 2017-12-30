CREATE DATABASE IF NOT EXISTS douban charset utf8mb4;

use douban;
create table if not exists event (
    dbid INTEGER NOT NULL PRIMARY KEY, -- the id from douban itself
    name VARCHAR(200) NOT NULL,
    time VARCHAR(200),
    price VARCHAR(100),
    city VARCHAR(20),
    place VARCHAR(200),
    type VARCHAR(100),
    provider VARCHAR(200), -- provider name with id: piaoniu (http://xxxx)
    people_interested MEDIUMINT(9) NOT NULL DEFAULT 0,
    people_going MEDIUMINT(9) NOT NULL DEFAULT 0,
    info TEXT
);
