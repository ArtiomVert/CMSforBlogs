<#include 'base.ftlh'/>
<#include 'top.ftlh'/>

<#macro fill>
    <@top/>
    <body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6 text-center">
                <div class="card border-danger">
                    <div class="card-header bg-danger text-white">
                        <h1 class="card-title">Ошибка ${statusCode}</h1>
                    </div>
                    <div class="card-body">
                        <i class="bi bi-exclamation-triangle-fill text-danger" style="font-size: 4rem;"></i>
                        <p class="lead mt-3">${errorMessage}</p>
                        <a href="/" class="btn btn-primary mt-3">
                            <i class="bi bi-house-door"></i> Вернуться на главную
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</#macro>

<@page/>