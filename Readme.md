## Параметры запуска для командной строки


### Пресеты через system.properties:

* `./gradlew clean test -Denvironment=local_chrome`

* `./gradlew clean test -Denvironment=local_safari`

* `./gradlew clean test -Denvironment=remote_chrome`

* `./gradlew clean test -Denvironment=remote_safari`


### Запуск с параметрами, пример:

`./gradlew clean test -Dlanguage=russian -DfrontendUrl=http://localhost:4200 -DbackendUrl=http://localhost -DbrowserName=chrome`


### Доступные параметры: 

* `threads` - количество параллельных потоков, default: `3`

* `browserName` - название браузера, default: `chrome` 

* `browserVersion` - версия браузера; игнорируется, если не указана

* `browserSize` - размер окна браузера, default: `1920x1080`

* `headless` - headless режим браузера, default: `false`

* `timeout` - максимальная задержка до падения теста, default: `10000` ms

* `language` - язык по умолчанию для тестов `english`

* `frontendUrl` - основной url тестируемого сайта (клиентская часть), default: `https://app.maxibooking.ru`

* `backendUrl` - основной url тестируемого сайта (серверная часть), default: `https://app.maxibooking.ru`

* `remote` - адрес удаленного сервера Selenide; игнорируется, если оставить пустым

* `enableVNC` - нужно для записи видео тестов //todo, default: `true`

* `enableVideo` - нужно для записи видео тестов //todo, default: `true`

браузеры: `chrome`, `safari`, `firefox`, `legacy_firefox`, `ie`, `opera`, `edge`.


### После окончания тестов:

`./gradlew allureServe` для отчетов.