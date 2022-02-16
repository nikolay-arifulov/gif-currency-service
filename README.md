# Gif currency service

Данный сервис сравнивает курс указанной валюты за сегодня и вчера, и возвращает ответ в виде GIF в зависимости
от результата сравнения — если курс по отношению к рублю за сегодня стал выше вчерашнего, то выдаётся случайная
GIF по тегу "rich", в противном случае — случайная GIF по тегу "broke".

## Инструкция по запуску

Для запуска вам понадобится установленный Docker: https://docs.docker.com/desktop/

Шаги для создания Docker-образа:

1. Скопировать этот репозиторий:
```bash
git clone https://github.com/Kooliz/gif-currency-service
```

2. Перейти в рабочую директорию:
```bash
cd gif-currency-service 
```

3. Запуск:
```bash
docker-compose up 
```

## Использование

Endpoint: http://localhost:8080/api/currency/check?currency=CODE, где CODE — код валюты, HttpMethod — GET