# RaiseTech第9回課題

## 概要
Spring BootでREST APIを作成して、Read機能を実装する。

## やったこと
- Springbootの設定
- Read機能の実装
- フロント画面との接続

### Springbootの設定
| 設定 | 設定値 |
| ---- | ---- |
| Project | Gradle |
| Language | Java | 
| Spring Boot | 2.7.3 |
| Packing | Jav | 
| Java | 17 | 

dependenciesの追加も忘れずに    
![CleanShot 2022-09-13 at 11 22 51](https://user-images.githubusercontent.com/76928095/189793319-1dc07c93-9891-421a-a52f-7623354cfbd4.png)


### Read機能の実装
#### application.ymlの作成
デフォルトだとapplication.propertiesが準備されていると思いますが、ymlファイルに変更して内容を記述する。
```
spring:
  datasource:
    url: jdbc:mysql://localhost:3307/store
    username: root
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
```

#### Dockerの設定をする
DockerでMySQLのデータベースを構築するために、`docker-compose.yml`と`Dockerfile`を作成する。
Dockerfile　　
Docker Imageを簡単に作成することができるようになる。
docker-compose  
複数のコンテナで構成されるアプリケーションについて、Docker Imageのビルドや各コンテナの起動・停止・ネットワーク接続が簡単にできるようになる。

```
java-raisetech-09-backend
├ Dockerfile
└ docker-compose.yml
```
Dockerfileには中身。  
```
FROM mysql:8.0-debian
RUN apt-get update #パッケージリストの更新
RUN apt-get -y install locales-all
ENV LANG ja_JP.UTF-8
ENV LANGUAGE ja_JP:ja
ENV LC_ALL ja_JP.UTF-8
COPY ./conf/mysql/my.cnf /etc/my.conf
```

docker-compose.ymlの中身。
```
version: '3.8'
services:
  db:
    build: .
    container_name: raisetech-9
    platform: linux/x86_64
    command: --default-authentication-plugin=mysql_native_password
    restart: always
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: store
      MYSQL_USER: user
      MYSQL_PASSWORD: password
    ports:
      - 3307:3306
    volumes:
      - $PWD/sql/:/docker-entrypoint-initdb.d
```

#### MySQLでデータを作成する
ルート直下に、sqlフォルダを作成。product.sqlの中身にはSQL文を記述。　　
dockerで立ち上がったときに初期データを作成する。
```
java-raisetech-09-backend
├ mysql
   └ product.sql
```

```sql
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
```

ここまでできたら、ターミナルからプロジェクトフォルダに移動して、MySQLへ接続を確認する。　　
```
//Dockerのコンテナを作成
% docker compose up -d

// MySQLに接続
% docker compose exec db mysql -u root -p

// DBの確認
mysql> show databases;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| mysql              |
| performance_schema |
| store              |
| sys                |
+--------------------+
5 rows in set (0.07 sec)

// 使用するデータベースの指定
mysql> use store;
Reading table information for completion of table and column names
You can turn off this feature to get a quicker startup with -A

// テーブルの確認
mysql> show tables;
+-----------------+
| Tables_in_store |
+-----------------+
| product         |
+-----------------+
1 row in set (0.03 sec)

// テーブルの中身の確認
mysql> select * from product;
+----+--------------------+-------+
| id | name               | price |
+----+--------------------+-------+
|  1 | ポテチ             |   100 |
|  2 | ポッキー           |   120 |
|  3 | ふ菓子             |   200 |
|  4 | 海外のお菓子       |   500 |
+----+--------------------+-------+
4 rows in set (0.01 sec)

```
これでデータベースの準備が完了しました。

#### GETリクエストの実装
次に、Spring Bootからデータベースへのアクセスを確認します。
まずはコントローラーを準備して、GETリクエスト(http://localhost:8080/product)を受けたときのAPIを実装します。  
※@Autowiredを付与することで呼び出したいクラスをnewしなくても呼び出すことができるようになる。

ProductController.java
```ProductController.java
package com.raisetech09.oka.controller;

import com.raisetech09.oka.service.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import com.raisetech09.oka.repository.ProductMapper;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ProductController {
    private ProductMapper productMapper;

    @Autowired
    public ProductController(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @GetMapping("/product")
    public List<Product> get() {
        return productMapper.findAll();
    }
}
```

ProductMapper.java
```ProductMapper.java
package com.raisetech09.oka.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.raisetech09.oka.service.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product")
    List<Product> findAll();
}
```

Product.java
```
package com.raisetech09.oka.service;

public class Product {
    private int id;
    private String name;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //　省略 setter getter constrouconstructor
}

```
#### Postmanで確認する
![CleanShot 2022-09-13 at 11 13 32](https://user-images.githubusercontent.com/76928095/189792290-66fffe2c-d562-4bef-b18f-2cfaec9d5ef8.png)

### フロント画面との接続
reactでフロント部分を作成する。
creat-react-appでとりあえず準備。  
package.jsonに追記。
```json
  "scripts": {
    "start": "react-scripts start",
    "build": "react-scripts build",
    "test": "react-scripts test",
    "eject": "react-scripts eject"
  },
   // 追記
  "proxy":"http://localhost:8080",
  "eslintConfig": {
    "extends": [
      "react-app",
      "react-app/jest"
    ]
  },
```

App.jsx
```javascript
import logo from './logo.svg';
import './App.css';
import { ApiFetch } from './component/ApiFetch';

function App() {
  return (
    <div className="App">
      <ApiFetch />
    </div>
  );
}

export default App;
```

Api.jsx
```javascript
import React, { useState, useEffect } from "react";

export const ApiFetch = () => {
  const [products, setProduct] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/product", { method: "GET" })
      .then((res) => res.json())
      .then((data) => {
        setProduct(data);
      });
  }, []);

  return (
    <div>
      <ul>
        <table>
          <th>ID</th>
          <th>お菓子の名前</th>
          <th>値段</th>
          {products.map((product) => (
            <>
              <tr>
                <td>{product.id}</td>
                <td>{product.name}</td>
                <td>{product.price}</td>
              </tr>
            </>
          ))}
        </table>
      </ul>
    </div>
  );
};
```
http://localhost:3000/にアクセスするとバックエンドから取得したデータを表示することができた。
![CleanShot 2022-09-13 at 11 18 45](https://user-images.githubusercontent.com/76928095/189792919-0a33af2b-5940-49fc-ba78-5e5795495513.png)

## 参考記事
**・Docker**  
https://qiita.com/zongxiaojie/items/6b593ec4ce5e85bb342c

**・MySQL**  


**・Spring Boot**  
@RestContollerと@Controllerの違いについて  
http://memento-mori-blog.com/spring-controller-restcontroller/

**・フロント**  
https://qiita.com/curry__30/items/c91d489551de68adb759
