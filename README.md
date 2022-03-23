#Javarush-Project-1
##Задача проекта
Создать программу, которая работает с шифром Цезаря.
###Краткое описание
Данная программа принимает на вход файл с русским текстом и выполняет слудующие действия:
* зашифровывает (используется шифр Цезаря)
* расшифровывает 
* взламывает зашифрованный текст (метод Brute force)

###Краткое описание классов
В корневой пакете проекта `com.javarush.cryptoanalyser` находятся 3 класса CharacterData, Main и BruteForce, содержащие в себе алфавит и
точки входа в приложение.
###Примеры использования и результаты выполнения программы
После запуска класс **Main** требует:

для шифрования текста
* ввести абсолютный путь файла в формате .txt, в котором находится оригинальный текст на русском языке
* ввести ключ шифрования (число для сдвига алфавита)
* ввести абсолютный путь файла в формате .txt для создания и последующей записи зашифрованного текста

для расшифрования текста
* ввести абсолютный путь файла в формате .txt, в котором находится зашифрованный текст
* ввести абсолютный путь файла в формате .txt для создания и последующей записи расшифрованного текста
* ввести ключ расшифрования

***Пример***

>Введите путь файла для шифрования текста:
>
>`C:\Users\demde\Desktop\originalText.txt`
>
>Введите ключ для шифрования ввиде цифры:
>
>`3`
>
>Введите путь для создания файла и записи зашифрованного текста:
>
>`C:\Users\demde\Desktop\encryptText.txt`
>
>Введите путь для создания файла и записи расшифрованного текста:
>
>`C:\Users\demde\Desktop\decryptText.txt`
>
>Введите ключ для расшифрования ввиде цифры:
>
>`3`

После запуска класс **BruteForce** требует

для взлома текста
* ввести абсолютный путь файла в формате .txt, в котором находится зашифрованный текст
* ввести размер текста ввиде строки (маленький текст - "small", средний текст - "medium", большой текст - "large")
* ввести абсолютный путь файла в формате .txt для создания и последующей записи взломанного текста

***Пример***

>Введите путь файла для взлома текста:
>
>`C:\Users\demde\Desktop\originalText.txt`
>
>Введите размер зашифрованного текста
>
>(маленький текст - введите "small"
>
>средний текст - введите "medium"
>
>большой текст - введите "large"):
>
>Введите путь для создания файла и записи расшифрованного текста:
>
>`small`
>
>Введите путь для создания файла и записи взломанного текста:
>
>`C:\Users\demde\Desktop\bruteForceText.txt`