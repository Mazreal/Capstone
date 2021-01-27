-------STATION-------

INSERT INTO fit.Station(stationNo,stationName)
VALUES( 1,'Garde Manger');

INSERT INTO fit.Station(stationNo,stationName)
VALUES(2,'Entremetier');

INSERT INTO fit.Station(stationNo,stationName)
VALUES(3,'Bakery');

INSERT INTO fit.Station(stationNo,stationName)
VALUES(4,'Protein/Sauce');

INSERT INTO fit.Station(stationNo,stationName)
VALUES(5,'Stewart');

-------INGREDIENT-----

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (0, 'Lettuce', 3, '7,11', 1);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (1, 'Chicken', 5, '7,11', 4);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
VALUES (2, 'Ranch', 10, '10/20', 5);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (3, 'Sliced Ham', 7, '8,13', 4);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
 VALUES (4, 'Sliced White Bread', 4, '7,11', 3);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (5, 'Pasta', 8, '10,15', 2);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
 VALUES (6, 'Marinara Sauce', 3, '4,8', 4);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
 VALUES (7, 'Meat Balls', 3, '6,9', 4);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (8, 'Sushi Rice', 1, '3,6', 2);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
 VALUES (9, 'Seaweed', NULL , '3', 1);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo)
 VALUES (10, 'Avocado', 2, '3,6', 5);

INSERT INTO fit.Ingredient(ingredientNo, ingredientName, ingredientShelfLife, par, stationNo) 
VALUES (11, 'Raw Salmon', 2, '4,7', 4);


------------INGREDIENT_ITEM--------------------

INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (0, CURRENT_DATE, 0, 6);
 
INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (1, CURRENT_DATE, 0, 6);
 
INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (2, CURRENT_DATE, 0, 20);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity) 
 VALUES (3, CURRENT_DATE, 0, 9);
 
INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity) 
VALUES (4, CURRENT_DATE, 0, 4);

INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (5, CURRENT_DATE, 0, 12);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity) 
 VALUES (6, CURRENT_DATE, 0, 6);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (7, CURRENT_DATE, 0, 3);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity) 
 VALUES (8, CURRENT_DATE, 0, 6);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (9, CURRENT_DATE, 0, 98);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity) 
 VALUES (10, CURRENT_DATE, 0, 4);
 
 INSERT INTO fit.Ingredient_Item(ingredientNo, ingredientDateProduced, expired, quantity)
 VALUES (11, CURRENT_DATE, 0, 2);
------MEAL-------------

INSERT INTO fit.Meal(mealItemNo,mealName,mealShelfLife)
VALUES(0,'Ceasar salad',5 );

INSERT INTO fit.Meal(mealItemNo,mealName,mealShelfLife)
VALUES(1, 'Ham Sandwich', 6);

INSERT INTO fit.Meal(mealItemNo,mealName,mealShelfLife)
VALUES(3,'Sushi',5);

INSERT INTO fit.Meal(mealItemNo,mealName,mealShelfLife)
VALUES(2,'Spaghetti n Balls',1);

---------MEAL ITEM-----------------------

INSERT INTO fit.Meal_Item(mealItemNo,mealDateProduced,mealExpired,quantity)
VALUES(0,CURRENT_DATE,0,6);

INSERT INTO fit.Meal_Item(mealItemNo,mealDateProduced,mealExpired,quantity)
VALUES(1,CURRENT_DATE,0,4);

INSERT INTO fit.Meal_Item(mealItemNo,mealDateProduced,mealExpired,quantity)
VALUES(3,CURRENT_DATE,0,5);

INSERT INTO fit.Meal_Item(mealItemNo,mealDateProduced,mealExpired,quantity)
VALUES(2,CURRENT_DATE,0,1);


-----------------RECIPE------------------------
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (0, 0, 5);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (0, 1, 3);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (0, 2, 2);

INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (1, 3, 3);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (1, 4, 2);

INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (2, 5, 5);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (2, 6, 3);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (2, 7, 3);

INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (3, 8, 2);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (3, 9, 0.3);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (3, 10, 1);
INSERT INTO fit.Meal_Recipe(mealItemNo, ingredientNo, quantity) VALUES (3, 11, 1);

--------------CLIENT--------------------------------
INSERT into fit.Client
VALUES (1, 'Jason Lang', 'Yardworkers LTD', '1010 4 Street NW', 'jasonlang@yardworkers.com', '4033334444');

INSERT into fit.Client
VALUES (2, 'Mike Fitzgerald', 'Safeway', '23 Center Street NE', 'mfitzgerald@safeway.com', '4032226969');

INSERT into fit.Client
VALUES (3, 'Drake', 'OVO', '16 Center Street NE', 'drake@ovo.com', '4039744000');
  

----------------PO--------------------------------
INSERT into fit.Purchase_Order (poNo, poDatePlaced, poDateRequired, empName, empEmail, delivery, completed, clientNo)
VALUES (1, CURRENT_DATE, '2020-4-20', 'Yardworkers LTD.', 'jasonlang@yardworkers.com', 0, 0, 1);

INSERT into fit.Purchase_Order (poNo, poDatePlaced, poDateRequired, empName, empEmail, delivery, completed, clientNo)
VALUES (2, CURRENT_DATE, '2020-4-30', 'Safeway', 'mfitzgerald@safeway.com', 0, 0, 2);

INSERT into fit.Purchase_Order (poNo, poDatePlaced, poDateRequired, empName, empEmail, delivery, completed, clientNo)
VALUES (3, CURRENT_DATE, '2020-4-25', 'OVO', 'drake@ovo.com', 0, 0, 3);

---------------PO ITEMS-----------------------------
INSERT into fit.PO_Items
VALUES (1,1, 'Yardworkers LTD.', 10.0, 'Sushi');

INSERT into fit.PO_Items
VALUES (2,1, 'Yardworkers LTD.', 5.0, 'Ham Sandwich');

INSERT into fit.PO_Items
VALUES (3,1, 'Yardworkers LTD.', 9.0, 'Caesar Salad');

INSERT into fit.PO_Items
VALUES (4,1, 'Yardworkers LTD.', 15.0, 'Spaghetti and Meatballs');

INSERT into fit.PO_Items
VALUES (5,1, 'Yardworkers LTD.', 4.0, 'Apple Pie');

INSERT into fit.PO_Items
VALUES (6,2, 'Safeway', 10.0, 'Sushi');

INSERT into fit.PO_Items
VALUES (7,2, 'Safeway', 5.0, 'Ham Sandwich');

INSERT into fit.PO_Items
VALUES (8,2, 'Safeway', 9.0, 'Caesar Salad');

INSERT into fit.PO_Items
VALUES (9,2, 'Safeway', 15.0, 'Spaghetti and Meatballs');

INSERT into fit.PO_Items
VALUES (10,2, 'Safeway', 4.0, 'Apple Pie');

INSERT into fit.PO_Items
VALUES (11,3, 'OVO', 4.0, 'Apple Pie');

INSERT into fit.PO_Items
VALUES (11,3, 'OVO', 4.0, 'Sushi');

INSERT into fit.PO_Items
VALUES (11,3, 'OVO', 4.0, ' Caesar Salad');

INSERT into fit.PO_Items
VALUES (11,3, 'OVO', 4.0, 'Spaghetti n Meatballs');
---------------USERS---------------------

INSERT into fit.Users
VALUES (1, 'John', 'password', 'A');

INSERT into fit.Users
VALUES (2, 'Mike', 'password', 'W');

INSERT into fit.Users
VALUES (3, 'Kevin', 'password', 'K');

-------------EXPIRED LOG-----------------------
INSERT into fit.Expired_Log
VALUES (1,1,'Cesar Salad', 10.0,CURRENT_DATE);

INSERT into fit.Expired_Log
VALUES (2,1,'Cesar Salad', 3.0,'2020-03-03');

INSERT into fit.Expired_Log
VALUES (3,1,'Cesar Salad', 2.0,'2020-03-02');

INSERT into fit.Expired_Log
VALUES (4,1,'Cesar Salad', 6.0,'2020-03-01');

INSERT into fit.Expired_Log
VALUES (5,2, 'Sushi', 7, CURRENT_DATE);

INSERT into fit.Expired_Log
VALUES (6,2, 'Sushi', 7, '2020-03-02');

INSERT into fit.Expired_Log
VALUES (7,3,'Apple pie', 3.0, CURRENT_DATE);

INSERT into fit.Expired_Log
VALUES (8,3,'Apple pie', 8.0, '2020-03-01');

INSERT into fit.Expired_Log
VALUES (9,3,'Apple pie', 5.0, '2020-03-02');

INSERT into fit.Expired_Log
VALUES (10,3,'Apple pie', 3.0, '2020-03-03');

INSERT into fit.Expired_Log
VALUES (11,4,'Spaghetti and Meatballs', 5.0, CURRENT_DATE);

INSERT into fit.Expired_Log
VALUES (12,4,'Spaghetti and Meatballs', 3.0, '2020-03-01');

INSERT into fit.Expired_Log
VALUES (13,4,'Spaghetti and Meatballs', 8.0, '2020-03-02');

INSERT into fit.Expired_Log
VALUES (14,4,'Spaghetti and Meatballs', 5.0, '2020-03-03');

INSERT into fit.Expired_Log
VALUES (15,5,'Ham Sandwich', 12.0, CURRENT_DATE);

INSERT into fit.Expired_Log
VALUES (16,5,'Ham Sandwich', 6.0, '2020-03-01');

INSERT into fit.Expired_Log
VALUES (17,6,'Ramen', 9.0, '2020-03-01');

INSERT into fit.Expired_Log
VALUES (18,6,'Ramen', 6.0, '2020-03-02');

INSERT into fit.Expired_Log
VALUES (19,7,'Pizza', 2.0, '2020-03-02');

INSERT into fit.Expired_Log
VALUES (20,8,'Mac n Cheese', 7.0, '2020-03-03');

-------------REPORTS-----------------------
INSERT into fit.Reports
VALUES(1,'Expired meal 01', CURRENT_DATE);

INSERT into fit.Reports
VALUES(2, 'Purchase Order 01', CURRENT_DATE);

INSERT into fit.Reports
VALUES (3, 'Purchase Order 02', CURRENT_DATE);