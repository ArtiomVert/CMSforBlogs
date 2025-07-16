<#include 'base.ftlh'/>
<#include 'top.ftlh'/>

<#macro fill>
<@top/>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-lg-8 mx-auto text-center">
            <h1 class="display-4">CMS для вашего блога</h1>
            <a href="/blog" class="btn btn-primary btn-lg">Мои записи</a>
            <a href="/blog/create" class="btn btn-primary btn-lg">Создать запись</a>
        </div>
    </div>
</div>
</body>
</#macro>

<@page/>