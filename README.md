# currency_converter
Конвертер валют 
На экране пользователю доступы следующие поля:

Ввод суммы в исходной валюте;
Выбор исходной валюты (например: Доллар США);
Выбор конечной валюты.

По нажатию на кнопку выводится сумма в конечной валюте.

Курсы валют брать из любого открытого источника данных: https://exchangeratesapi.io/, https://www.iban.com/exchange-rates-api, http://www.cbr.ru/scripts/XML_daily.asp итд

При каждом входе в приложение следует пытаться загрузить по сети курсы валют и (в случае их доступности) сохранять их локально (надо кэшировать, не нужно проверять, отличаются ли загруженные курсы от закэшированных). В случае, если загрузить не удалось / не успели, следует использовать закэшированные курсы.

Обязательные требования:

Код пишется только на Kotlin
Код должен быть максимально приближен к production-ready, то есть разрабатывать его вы должны ровно так же, как разрабатывали бы фичу. 
Библиотеки должны быть выбраны те, которые вы бы использовали при разработке реальной фичи. 
Архитектура и решение писать ли тесты тоже принимается так, как вы бы делали при разрабооке реальной фичи. 
