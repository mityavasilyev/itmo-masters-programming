## Stack
Backend:
- Java

Tests:
- Bash тестовые сценарии

## Результаты тестов в CI/CD
Исходный код и результаты сборки проекта с тестами можно посмотреть здесь:
https://github.com/mityavasilyev/itmo-masters-programming-lab-1/actions/runs/8606439094/job/23584856216

## Запуск 
Стняуть проект и в корневой директории выполнить команды
```sh
cd client
docker build -t client:latest .

cd ../server
docker build -t server:latest .

docker run -d --network=host --name server server:latest
docker run -d --network=host --name client client:latest
```
