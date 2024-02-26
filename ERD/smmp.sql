DROP TABLE IF EXISTS medicine;


CREATE TABLE medicine
(
    id int NOT NULL AUTO_INCREMENT,
    itemName longtext not null,
    entpName longtext not null,
    itemSeq longtext not null,
    efcyQesitm longtext,
    useMethodQesitm longtext,
    atpnWarnQesitm longtext,
    atpnQesitm longtext,
    depositMethodQesitm longtext,
    intrcQesitm longtext,
    seQesitm longtext,
    itemImage longtext,
PRIMARY KEY (id)
);

DELETE FROM medicine;
INSERT INTO medicine (itemName, entpName, itemSeq, efcyQesitm, useMethodQesitm, atpnWarnQesitm, atpnQesitm, depositMethodQesitm, intrcQesitm, seQesitm, itemImage)
VALUES
    ('활명수', '동화약품(주)', '195700020', '이', '이 약은', '이 약은', '이 약은', '이 약은', '이 약은', '이 약은', 'jj')
;

SELECT * FROM medicine;
