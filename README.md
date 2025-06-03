[![Actions Status](https://github.com/ganiev-dev/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/ganiev-dev/java-project-71/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ganiev-dev_java-project-71&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ganiev-dev_java-project-71)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=ganiev-dev_java-project-71&metric=coverage)](https://sonarcloud.io/summary/new_code?id=ganiev-dev_java-project-71)
 # Вычислитель отличий / Generate Difference

## Описание 

Вычислитель отличий – консольная утилита, определяющая разницу между двумя структурами данных. Это популярная задача, для решения которой существует множество онлайн сервисов, например [http://www.jsondiff.com/](http://www.jsondiff.com/). Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменений в конфигурационных файлах.

## Возможности
-   Поддержка разных входных форматов: yaml, json
-   Генерация отчета в виде plain text, stylish и json

## Установка

	$ make install              

## Использование
	$ gendiff [-hV] [-f=format] filepath1 filepath2
	filepath1	Путь к 1 файлу
	filepath2	Путь ко 2 файлу
	-f, --format	Выбор форматтера. По умолчанию *stylish.*
	-h, --help	Справка.
	-V, --version	Номер версии.


Указываются пути к двум json или yaml файлам. Утилита их сранивает и выдает отформатированный результат. Если необходим форматтер *plain*, он указывается через ключ *-f "plain"*.

	$app -f plain path1.json path2.json



# Примеры работы
Нахождение различий между двумя json файлами, формат по умолчанию.

[![asciicast](https://asciinema.org/a/yz0WFg6GGXzF8pb0ty2rCc7kx.svg)](https://asciinema.org/a/yz0WFg6GGXzF8pb0ty2rCc7kx)

Вывод различий двух древовидных json файлов

[![asciicast](https://asciinema.org/a/4KhZfanaDw2EAMKz52XAyfMFH.svg)](https://asciinema.org/a/4KhZfanaDw2EAMKz52XAyfMFH)

Сравнение и вывод двух древовидных json файлов в формате json

[![asciicast](https://asciinema.org/a/oXwgY4cMNz06F3CkcitaBnaqE.svg)](https://asciinema.org/a/oXwgY4cMNz06F3CkcitaBnaqE)

Сделано в рамках обучения в школе программирования ["Hexlet"](https://ru.hexlet.io/).
