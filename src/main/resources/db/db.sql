CREATE TABLE products
(

    productID INT
    AUTO_INCREMENT,
    productName VARCHAR
    (30),
    productPrice DECIMAL
    (4,2),
	productDescription VARCHAR
    (1000),
    productImgPath VARCHAR
    (100),
	
    CONSTRAINT Product_PK PRIMARY KEY
    (productID)
    
);

    CREATE TABLE users
    (
        userID INT
        AUTO_INCREMENT,
	username VARCHAR
        (10) UNIQUE NOT NULL ,
    password VARCHAR
        (10) NOT NULL,
    
    CONSTRAINT users_PK PRIMARY KEY
        (userID)
);

        CREATE TABLE customers
        (
            customerID INT
            AUTO_INCREMENT,
    customerFirstName VARCHAR
            (30) NOT NULL,
    customerLastName VARCHAR
            (30) NOT NULL,
	customerEmail VARCHAR
            (30) NOT NULL,
    customerPhone VARCHAR
            (10) NOT NULL,
    customerBirthdate DATE NOT NULL,
    userID INT,
    
    CONSTRAINT Customer_PK PRIMARY KEY
            (customerID),
    CONSTRAINT Customer_User_FK FOREIGN KEY
            (userID) REFERENCES users
            (userID)
    
);



            CREATE TABLE address
            (

                addressID INT
                AUTO_INCREMENT,
    addressStreet VARCHAR
                (30),
    addressZipCode VARCHAR
                (10),
    addressCity VARCHAR
                (30),
    addressCountry VARCHAR
                (50),
    customerID INT,
    addressStatus VARCHAR
                (10),
    
    CONSTRAINT Address_PK PRIMARY KEY
                (addressID),
    CONSTRAINT Address_Customer_FK FOREIGN KEY
                (customerID) REFERENCES customers
                (customerID)
);

                CREATE TABLE payments
                (
                    paymentID INT
                    AUTO_INCREMENT,
    paymentCardNumber VARCHAR
                    (50),
    paymentCardCV VARCHAR
                    (5),
    paymentCardExpired VARCHAR
                    (10),
    
    CONSTRAINT Payment_PK PRIMARY KEY
                    (paymentID)
    
);

                    CREATE TABLE orders
                    (
                        orderID INT
                        AUTO_INCREMENT,
    addressID INT,
    paymentID INT,
    orderDate DATE NOT NULL,
    
    CONSTRAINT Order_PK PRIMARY KEY
                        (orderID),
    CONSTRAINT Order_Address_FK FOREIGN KEY
                        (addressID) REFERENCES address
                        (addressID),
    CONSTRAINT Order_Payment_FK FOREIGN KEY
                        (paymentID) REFERENCES payments
                        (paymentID)
);

                        CREATE TABLE orderItems
                        (
                            orderItemID INT
                            AUTO_INCREMENT,
    orderID INT,
    productID INT,
    orderItemQuantity INT NOT NULL,
    
    CONSTRAINT OrderItem_PK PRIMARY KEY
                            (orderItemID),
    CONSTRAINT OrderItem_Order_FK FOREIGN KEY
                            (orderID) REFERENCES orders
                            (orderID),
	CONSTRAINT OrderItem_Product_FK FOREIGN KEY
                            (productID) REFERENCES products
                            (productID)
    
);

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('BioHack:Serum Code:PR3', 49.99, 'Наш флагманский стимулятор предназначен для того, чтобы привести вас к вашим целям и даже за их пределы. Таким образом, мы увидим значительно повышенный уровень холина в нашем организме.',
                                    'biohack-serum-code-pr3.png');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('BioHack:Serum Code:Pump', 39.99, 'Что такое BioHack Pharmaceuticals Serum Code насос? Основная цель добавки - улучшить мышечную силу во время тренировки и помочь доставке питательных веществ в ваши мышцы, чтобы дать вам этот дополнительный толчок энергии, который вам действительно нужен во время тренировок.'
    'biohack-serum-code-pumk.png');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('Premium Raws: Tudca', 34.99, 'Что такое Tudca?  Таурурзодезоксиловая кислота, чаще называемая «тудкой», представляет собой желчную соль встречающейся в организме. Когда регулярные соли желчи достигают кишечника, они могут метаболизировать бактерии в UDCA, а затем связывать их с молекулой таурина, чтобы стать таурурзодезоксиловой кислотой.''premium-raws.jpg');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('TWP: Salvage', 44.99, 'Salvage - это белок, полностью раскрывающий весь состав содержания белков, предлагая вам полную уверенность и силу во время тренировок.''twp-salvage.jpg');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('TWP: Ashwagandha', 15.99, 'TWP Ashwagandha. Включает в себя биоперирин для увеличения биопродуктивности. Также включает в себя астрагин.',
                                    'twp-ashwagandha.jpg');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('TWP: Ashwagandha', 18.99, 'TWP Ashwagandha. Включает в себя биоперирин для увеличения биопродуктивности. Также включает в себя астрагин.',
                                    'twp-ashwagandha.jpg');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('TWP: Greed', 15.99, 'Greed является наиболее инклюзивной и актуальной пищеварительной помощью и усилением аппетита на рынке. Независимо от того, находитесь ли вы в «межсезонье», стремясь использовать данную добавку для притока калорий, или вы используете её для повседневной жизни, когда вы страдаете от проблем с пищеварением.',
                                    'twp-greed.jpg');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('MaxxMuscle: Atomic Bomb', 49.99, 'Atomic Bomb содержит в себе взрывные ингредиенты, которые необходимы для тренировок. Данная пищевая добавка представляет собой яростную смесь ингредиентов, повышающих вашу производительность: силу, выносливость. ',
                                    'maxxmuscle-atomic-bomb.png');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('MaxxMuscle: EAAmmo', 39.99, 'Мощное сочетание незаменимых аминокислот и BCAA. Данная добавка предназначена для поддержания интенсивности тренировок. А также EAAmmo укрепляет ваш организм мощным адаптогеном, чтобы привести в норму уровень кортизола в организме. ',
                                    'maxxmuscle-eaammo.png');

                            INSERT INTO products
                                (productName, productPrice, productDescription, productImgPath)
                            VALUES('OHS: Unleash Hell', 29.99, 'Unleash Hell является обязательной добавкой, которую использую предварительно перед тренировкой. В данную добавку входят самые лучшие ингредиенты в необходимых дозах, чтобы во время тренировки вы могли выложиться на полную. ',
                                    'ohs-unleash-hell.png');