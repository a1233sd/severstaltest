
## База данных

Приложение использует PostgreSQL. Основные таблицы и их поля:

- **delivery**  
  Хранит информацию о поставках (идентификатор, дату, статус, детали поставки).

- **priceOffer**  
  Содержит данные о ценовых предложениях (идентификатор, цена, валюта, условия, срок действия).

- **product**  
  Описывает товары (идентификатор, название, описание, категория, цена).

- **supplier**  
  Информация о поставщиках (идентификатор, название, контактные данные).
  
- **price_offers**  
  Содержит данные о ценовых предложениях: цена и срок актуальности предложения..

--
## Тестирование

Тесты написаны с использованием JUnit, Mockito и MockMvc, что позволяет проверять корректность работы эндпоинтов.

---

## Запуск через Docker

### Запуск приложения
Перед сборкой проекта в docker нам нужен war файл, поэтому пишем
  ```bash
mvn clean package -DskipTests
  ```

Он собирает проект без запуска тестов (так как у нас бд поднимается в docker, то сначала надо поднять docker, 
а потом запустить тесты)

В корне проекта выполните команду:
   ```bash
   docker-compose up --build
   ```
   Это поднимет:
   - **pgdb** – контейнер с PostgreSQL.
   - **app** – основное приложение.
   - **pgadmin** – pgAdmin для управления базой.
   -  **app-test** – тесты.

Swagger UI будет доступен по адресу:  
`http://localhost:8080/swagger-ui/index.html`

Пароль к БД в docker-compose
---

## Гибкая настройка параметров без пересборки

В проекте реализована возможность изменения параметров приложения без пересборки Docker-образа.  
Для этого используется внешний файл конфигурации, который монтируется в контейнер (`config/application-docker.properties`). 

---

## Postman

Добавлена postman коллекция для тестирования

---


