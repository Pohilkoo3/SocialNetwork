
## Запуск  docker compose 

1. перейти в корень проекта
   (выйти из директории `cd ..`)
2. `docker-compose up -d`

...

Файл расположен в корне проекта, в docker-compose.yaml


`version: '3'` - версия docker-compose

`restart: unless-stopped` - контейнер перезапускется, если он останавливается. Если контейнер останавливается (вручную или иным образом), 
он не перезапускается даже после перезапуска демона Docker.

