DELETE FROM article;
ALTER SEQUENCE global_sequence RESTART WITH 100000;

INSERT INTO article (id, created, title, content, preview)
VALUES (1,
        '2024-08-13 15:00:00',
        'Первый пост',
        'Не так часто производители смартфонов делают отсылки к знаменитым машинам...',
        'Неделя с Infinix NOTE 40 Pro Racing Edition'),
       (2,
        '2024-08-13 16:30:00',
        'Второй пост',
        'Тонкие офисные ноутбуки бывают разными. Есть дорогие модели из алюминия и магния...',
        'Lenovo Xiaoxin Pro 16: ничего лишнего, максимум полезного'),
       (3,
        '2024-09-13 17:00:00',
        'Третий пост',
        'Компания Edifier представила беспроводные наушники Stax Spirit S10...',
        'Edifier выпустила планарные TWS-наушники');
