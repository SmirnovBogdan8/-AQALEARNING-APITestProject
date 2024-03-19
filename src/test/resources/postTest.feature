#language: ru
Функционал: Проверить методы POST

  Сценарий: CREATE
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "name": "morpheus",
        "job": "leader"
    }
    """
    Когда Методом POST получить данные по эндпоинту "/api/users"
    Тогда Статус-код ответа должен быть 201

  Сценарий: REGISTER - SUCCESSFUL
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "email": "eve.holt@reqres.in",
        "password": "pistol"
    }
    """
    Когда Методом POST получить данные по эндпоинту "/api/register"
    Тогда Статус-код ответа должен быть 200

  Сценарий: REGISTER - UNSUCCESSFUL
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "email": "sydney@fife"
    }
    """
    Когда Методом POST получить данные по эндпоинту "/api/register"
    Тогда Статус-код ответа должен быть 400

  Сценарий: LOGIN - SUCCESSFUL
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "email": "eve.holt@reqres.in",
        "password": "cityslicka"
    }
    """
    Когда Методом POST получить данные по эндпоинту "/api/login"
    Тогда Статус-код ответа должен быть 200

  Сценарий: LOGIN - UNSUCCESSFUL
    Дано Установить URL "https://reqres.in"
    Дано Передать тело запроса в переменную 'requestBody'
    """
    {
        "email": "peter@klaven"
    }
    """
    Когда Методом POST получить данные по эндпоинту "/api/login"
    Тогда Статус-код ответа должен быть 400