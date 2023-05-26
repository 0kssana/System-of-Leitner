# System-of-Leitner
ITMO, CT. Экзамен по дисциплине "Введение в программирование" 1 семестр.

Билет 12. Система Лейтнера

Реализуйте интервальное повторение переводов слов на основе системы Лейтнера.

Во входном файле даны пары слово(словосочетание) — перевод

В системе Лейтнера используются 10 корзин. Вначале все слова находятся в первой корзине.

Вероятность вытащить каждое слово из корзины n пропорциональна 1.5^(-n).

Если пользователь ввел правильный перевод, то слово перемещается в корзину с номером на единицу больше (или остается в 10 корзине). Если пользователь ошибся, то слово
перемещается в первую корзину.

Если пользователь ввел пустой ответ, то программа должна завершиться, и при повторном запуске продолжить с того же места, с сохранением распределения слов по корзинам.
