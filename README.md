# hh-school-search
Для сборки в jar файл:

mvn clean package

Запуск программы:

java -jar target/hh-school-search-1.0-SNAPSHOT.jar

Программа работает в двух режимах. 
После запуска программы в консольном окне введите:
 - index (или 1) для перехода в режим записи индекса;
 - search (или 2) для перехода в режим поиска по индексу.
 - Q (или 3) выход из программы.
В любом другом варианте программа выдаст ошибку ввода и завершит работу. 


Режим 1. Запись в индекс:

На вход подается директория с название файла для индекса.
Если указать уже существующий файл, то индекс перезаписывается.

Далее на вход запрашивается исходный текстовый файл с указанием пути к нему. Исходный файл содержит текст построчно. Каждая строка считается, как отдельный документ.
Исходный документ проверяется на стоп-слова (перечислены в stopWords.inp, используется как встроенный ресурс), они не хранятся в индексе.
Нормализация слов не проводится.

Режим 2. Поиск по индексу:

На вход подается созданный файл индекса из режима 1, с указанием пути к нему.
Далее предлагается ввести запрос или символ "Q" для завершения работы программы.
В качестве результата выводится список документов, подходящих под этот запрос.

Запрос поддерживает:
 - поиск по одному слову;
 - поиск по нескольким словам (список документов, в которых содержится хотя бы одно слово);
 - Поддерживаются булевые запросы AND и NOT; 
 - Фразовый запрос (запрос в кавычках " ") - выдает список документов, где есть все слова. Учитывается порядок слов

Во всех запросах игнорируются знаки препинания и стоп слова (для фразового запроса в том числе).

## Дз по поиску 

Реализовать свой простой поиск на java, который может работать в 2 режимах: 

1. Индексация: на вход подаётся название файла(директории) индекса и исходный файл, где каждая строка является отдельным документом в индексе, и происходит построение индекса, который записывается в файл(директорию). 
2. Поиск: на вход подаётся название файла индекса и запрос, на выходе получаем список документов подходящих под этот запрос.

Начать можно с написания наивной реализации инвертированного индекса и запросов по одному слову. В итоге должна получиться jar-ка, которая может работать в 2 режимах. Сдавать в виде PR к этому репозиторию с инструкцией для сборки и запуска, и списком что было реализованно. 

## Доп. задания (не обязательно, в порядке усложнения): 
* написать реализацию запросов AND и NOT
* написать реализацию фразовых запросов
* применить оптимизации при построении индекса из лекции
* написать реализацию OR с задаваемым минимальным количеством вхождений (пример: есть запрос java OR scala OR kotlin, мы хотим все документы где есть минимум 2 слова)

## Срок сдачи

31.01.2019 23:59
