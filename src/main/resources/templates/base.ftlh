<#include 'bootstrap.ftlh'>

<#macro fill></#macro>
<#macro css></#macro>
<#macro page>
    <!DOCTYPE html>
    <#import "spring.ftl" as spring/>
    <html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Главная страница</title>
        <@bootstrap/>
        <@css/>
    </head>
    <@fill/>
    </html>
</#macro>