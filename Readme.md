## Параметры запуска для командной строки

Требуются JDK Java 11 и Gradle 6.8.3

`gradle clean test -Durl=https://app.maxibooking.ru/`

или

`gradle clean test -Durl=https://localhost/`

после окончания тестов
`gradle allureServe` для отчетов