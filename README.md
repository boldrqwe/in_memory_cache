in_memory_cache

Для запуска программы необходимо, скачать файл "in_memory_cache-0.0.1-SNAPSHOT.jar" из папки /target, находясь в директории с программой, в командной сроке написать команду java -jar in_memory_cache-0.0.1-SNAPSHOT.jar, приложение запуституся на порту 8080. К приложению можно обращаться по адресу: http://localhost:8080/.

Работоспособность программы можно протестировать с помощью запросов через программу Postman.

Список команд примеров:

1) curl --location --request POST 'http://localhost:8080/set?key=sss&ttl=20000' \
--header 'asd: qweqwe' \
--header 'Content-Type: application/json' \
--data-raw '["aaa","bbb","ccc"]'

2) curl --location --request GET 'http://localhost:8080/get?key=sss'

3) curl --location --request GET 'http://localhost:8080/keys'

4) curl --location --request POST 'http://localhost:8080/del' \
--header 'Content-Type: application/json' \
--data-raw '["aaa","bbb","ccc"]'
