<#include 'base.ftlh'/>
<#include 'top.ftlh'/>

<#macro file_element f>
    <#if "${f.type}"=="file" >
        <a href="/files?name=${f.nameInMinio}"
           class="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
           download>
            ${f.nameInMinio}
            <span class="badge bg-primary rounded-pill">Download</span>
        </a>
    <#elseif "${f.type}"=="img">
        <div class="img-container" style="max-width: 100%; max-height: 100vh; overflow: hidden;">
            <img src="/files?name=${f.nameInMinio}" class="img-fluid"
                 style="object-fit: cover; width: 100%; height: 100%;" alt="image"/>
        </div>
    <#elseif "${f.type}"=="vid">
        <div class="ratio ratio-16x9">
            <video controls>
                <source src="/files?name=${f.nameInMinio}" type="video/mp4">
                Your browser does not support the video tag.
            </video>
        </div>
    <#else >
        <p>${f.nameInMinio}</p>
    </#if>
</#macro>

<#macro fill>
    <@top/>
    <body>
    <div class="container my-4 w-50 mx-auto">
        <h1 class="mb-4">Список блогов</h1>

        <#list posts as post>
            <div class="card mb-4">
                <div class="card-body">
                    <a class="card-text" href="blogs/${post.user.id}">Автор: ${post.user.username}</a>

                    <h2 class="card-title">${post.title}</h2>

                    <#assign limit = 300>
                    <#if (post.text?length > limit)>
                        <pre id="short-text-${post.id}" class="card-text"
                             style="white-space: pre-wrap;">${post.text?substring(0, limit)} ...</pre>
                        <pre id="full-text-${post.id}" class="card-text d-none"
                             style="white-space: pre-wrap;">${post.text}</pre>
                        <button class="btn btn-link p-0" type="button" onclick="toggleText(${post.id})"
                                id="btn-text-${post.id}">
                            Читать далее
                        </button>
                    <#else>
                        <pre id="full-text-${post.id}" class="card-text"
                             style="white-space: pre-wrap;">${post.text}</pre>
                    </#if>

                    <hr/>

                    <#assign files = post.materials>
                    <#assign shortFiles = []>
                    <#list files as file>
                        <#if shortFiles?size < 3>
                            <#assign shortFiles = shortFiles + [file]>
                        </#if>
                    </#list>
                    <ul id="files-short-${post.id}" class="list-group list-group-flush">
                        <#list shortFiles as f>
                            <li class="list-group-item"><@file_element f/></li>
                        </#list>
                    </ul>
                    <ul id="files-full-${post.id}" class="list-group list-group-flush d-none">
                        <#list files as f>
                            <li class="list-group-item"><@file_element f/></li>
                        </#list>
                    </ul>
                    <#if (files?size > 3) >
                        <button class="btn btn-link p-0 mt-2" type="button" onclick="toggleFiles(${post.id})"
                                id="btn-files-${post.id}">
                            Показать все файлы
                        </button>
                    </#if>

                    <#if (post.tegs?size > 0)>
                        <p>Выбранные теги:
                            <#list post.tegs as teg>
                                #${teg.teg.toString()}
                            </#list>
                        </p>
                    <#else>
                        <p>Теги не выбраны</p>
                    </#if>

                </div>
            </div>
        </#list>
    </div>
    </body>
</#macro>

<@page/>


<script type="application/javascript">
    function toggleText(id) {
        const shortText = document.getElementById(`short-text-` + id);
        const fullText = document.getElementById(`full-text-` + id);
        const btn = document.getElementById(`btn-text-` + id);

        if (shortText.classList.contains('d-none')) {
            shortText.classList.remove('d-none');
            fullText.classList.add('d-none');
            btn.textContent = 'Читать далее';
        } else {
            shortText.classList.add('d-none');
            fullText.classList.remove('d-none');
            btn.textContent = 'Свернуть';
        }
    }

    function toggleFiles(id) {
        const shortFiles = document.getElementById(`files-short-` + id);
        const fullFiles = document.getElementById(`files-full-` + id);
        const btn = document.getElementById(`btn-files-` + id);

        if (shortFiles.classList.contains('d-none')) {
            shortFiles.classList.remove('d-none');
            fullFiles.classList.add('d-none');
            btn.textContent = 'Показать все файлы';
        } else {
            shortFiles.classList.add('d-none');
            fullFiles.classList.remove('d-none');
            btn.textContent = 'Скрыть файлы';
        }
    }
</script>