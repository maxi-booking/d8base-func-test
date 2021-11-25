## Параметры запуска для командной строки

Требуются JDK Java 11 и Gradle 6.8.3

запуск с параметрами:

`gradle clean test -Durl=https://app.maxibooking.ru/ -Dbrowser=chrome`

после окончания тестов
`gradle allureServe` для отчетов