Домашнее задание 10

Переписать приложение с использованием AJAX и REST-контроллеров
Цель: Цель: использовать Spring MVC для разработки современных AJAX/SPA приложений c помощью Spring MVC Результат: современное приложение на стеке Spring
Домашнее задание выполняется на основе предыдущего.

1. Переписать приложение с классических View на AJAX архитектуру и REST-контроллеры.
2. Минимум: получение одной сущности и отображение её на странице с помощью XmlHttpRequest, fetch api или jQuery
3. Опционально максимум: полноценное SPA приложение на React/Vue/Angular, только REST-контроллеры.

В случае разработки SPA - рекомендуется вынести репозиторий с front-end. Используйте proxy при разработке (настройки CORS не должно быть).


Домашнее задание 16
Использовать метрики, healthchecks и logfile
Цель: Цель: реализовать production-grade мониторинг и прозрачность в приложении
Результат: приложение с применением Spring Boot Actuator
Данное задание выполняется на основе одного из реализованных Web-приложений

1. Подключить Spring Boot Actuator в приложение.
2. Включить метрики, healthchecks и logfile.
3. Реализовать свой собственный HealthCheck индикатор
4. UI для данных от Spring Boot Actuator реализовывать не нужно.
5. Опционально: переписать приложение на HATEOAS принципах с помощью Spring Data REST Repository

Домашнее задание 17
Обернуть приложение в docker-контейнер
Цель: Цель: деплоить приложение в современном DevOps-стеке
Результат: обёртка приложения в Docker
Внимание! Задание выполняется на основе любого сделанного Web-приложения

1. Обернуть приложение в docker-контейнер. Dockerfile принято располагать в корне репозитория. 
В image должна попадать JAR-приложения. 
Сборка в контейнере рекомендуется, но не обязательна.
2. БД в собственный контейнер оборачивать не нужно (если только Вы не используете кастомные плагины)
3. Настроить связь между контейнерами, с помощью docker-compose
4. Опционально: сделать это в локальном кубе.
5. Приложение желательно реализовать с помощью всех Best Practices Docker (логгирование в stdout и т.д.)
