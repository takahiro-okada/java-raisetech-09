DROP TABLE IF EXISTS product;

  CREATE TABLE product (
    id int unsigned AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price int unsigned NOT NULL,
    PRIMARY KEY(id)
  );

  INSERT INTO product (name, price) VALUES ("ポテチ","100");
  INSERT INTO product (name, price) VALUES ("ポッキー","120");
  INSERT INTO product (name, price) VALUES ("ふ菓子","200");
  INSERT INTO product (name, price) VALUES ("海外のお菓子","500");

