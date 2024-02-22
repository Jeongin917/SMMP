DROP TABLE IF EXISTS medicine;


CREATE TABLE medicine
(
    id int NOT NULL AUTO_INCREMENT,
    itemName varchar(1000) not null,
    entpName varchar(1000) not null,
    itemSeq int not null,
    efcyQesitm varchar(1000),
    useMethodQesitm varchar(1000),
    atpnWarnQesitm varchar(1000),
    atpnQesitm varchar(1000),
    depositMethodQesitm varchar(1000),
    intrcQesitm varchar(1000),
    seQesitm varchar(1000),
    itemImage varchar(1000),
PRIMARY KEY (id)
);

DELETE FROM medicine;
INSERT INTO medicine (itemName, entpName, itemSeq, efcyQesitm, useMethodQesitm, atpnWarnQesitm, atpnQesitm, depositMethodQesitm, intrcQesitm, seQesitm, itemImage)
VALUES
    ('활명수', '동화약품(주)', 195700020, '이', '이 약은', '이 약은', '이 약은', '이 약은', '이 약은', '이 약은', 'jj')
;

SELECT * FROM medicine;
