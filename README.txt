Тестовое задание
Задача: Реализовать Rest API
без использования бд.
вместо таблиц классы Manufacturer и Products, вместо связей между таблицами списки manufacturerList и products

--Cоздание производителя
GET http://localhost:8080/addManufacturer
body {
         "manufacturer_id" : "m_id",
         "manufacturer_name" : "m_name"
     }

--Добавить продукт к производителю. Если продукт уже есть, предложит аналогичный метод PUT
POST http://localhost:8080/addProduct
body {
         "manufacturer_id": "m_id",
         "product_id": "p_id",
         "product_name": "p_name"
     }

--Проапдейтить продукт у производителя. Если продукта нет, предложит аналогичный метод POST
PUT http://localhost:8080/addProduct
body {
         "manufacturer_id": "m_id",
         "product_id": "p_id",
         "product_name": "p_name"
     }

--Вывести все продукты производителя
GET http://localhost:8080/getAllProducts?manufacturer_id=m_id

--Вывести названия всех производителей, у которых больше counter продуктов
GET http://localhost:8080/getBigManufactures?counter=2