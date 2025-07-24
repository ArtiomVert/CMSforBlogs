<#macro top>
    <nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
        <a class="navbar-brand" href="">SAFE LIFE</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" role="button" data-bs-toggle="dropdown" aria-expanded="false">Мой блог</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="/blogs/my">Просмотр записей</a></li>
                        <li><a class="dropdown-item" href="/blogs/create">Создать запись</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link" href="/blogs" aria-expanded="false">Все блоги</a>
                </li>
            </ul>

            <#if user_login??>
                <div class="d-flex">
                    <a methods="POST" href="/logout" class="btn btn-outline-danger">Log Out</a>
                </div>
            <#else>
                <div class="d-flex">
                    <a href="/login" class="btn btn-outline-danger">Sign In</a>
                </div>
                <div class="d-flex">
                    <a href="/signUp" class="btn btn-outline-danger">Sign Up</a>
                </div>
            </#if>
        </div>
    </nav>
</#macro>
