#language: ru
Функционал: Проверить методы PATCH

  Сценарий: UPDATE
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "name": "morpheus",
        "job": "zion resident"
    }
    """
    Когда Методом PATCH получить данные по эндпоинту "/api/users/2"
    Тогда Статус-код ответа должен быть 201