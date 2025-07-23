<#include 'base.ftlh'/>
<#include 'top.ftlh'/>

<#macro fill>
    <@top/>
    <body>
    <div class="container mt-5">
        <h1 class="mb-4">Создание новой записи</h1>
        <form action="/blogs/create" method="post" enctype="multipart/form-data">

            <div class="mb-3">
                <label for="title" class="form-label">Заголовок поста</label>
                <input name="title" type="text" class="form-control" id="title" placeholder="Введите заголовок поста" required>
            </div>

            <div class="mb-3">
                <label for="text" class="form-label">Текст записи</label>
                <textarea name="text" class="form-control" id="text" rows="5" placeholder="Введите текст записи"></textarea>
            </div>

            <div class="mb-3">
                <label for="files" class="form-label">Добавить файлы</label>
                <input name="files" class="form-control" type="file" id="files" multiple>
                <ul id="fileList"></ul>
            </div>

            <div class="d-grid gap-2 d-md-block">
                <button type="submit" class="btn btn-primary me-md-2">Создать запись</button>
            </div>
        </form>
    </div>
    </body>
</#macro>

<@page/>



<script type="application/javascript">
    const fileInput = document.getElementById('files');
    const fileList = document.getElementById('fileList');

    let filesArray = [];

    fileInput.addEventListener('change', () => {
        filesArray = Array.from(fileInput.files);
        updateList();
    });

    function updateList() {
        fileList.innerHTML = '';
        filesArray.forEach((file, index) => {
            const li = document.createElement('li');
            li.textContent = file.name + ' ';
            const removeBtn = document.createElement('button');
            removeBtn.textContent = 'Удалить';
            removeBtn.onclick = () => {
                filesArray.splice(index, 1);
                syncInputFiles();
                updateList();
            };
            li.appendChild(removeBtn);
            fileList.appendChild(li);
        });
    }

    function syncInputFiles() {
        const dataTransfer = new DataTransfer();
        filesArray.forEach(file => dataTransfer.items.add(file));
        fileInput.files = dataTransfer.files;
    }
</script>