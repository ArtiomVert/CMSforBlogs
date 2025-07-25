1) Minio реализован в MinioService, конфигурация бина клиента в MinioConfig и настройки в application.properties.
2) Kafka producer для отправки сообщений о новых постах реализован в NotificationService, а вызывается в методе BlogService.sendMessages(). Конфигурация производится автоматически через application.properties.
3) Админка на Vaadin реализована в AdminMainView и UsersVaadin. Очень удобная штука, позволяет делать минимальный фронт без особых усердий и знаний шаблонизаторов, а также прям здесь можно реализовывать логику приложения вместо ajax запросов и js. 
