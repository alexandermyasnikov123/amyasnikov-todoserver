const apiBaseUrl = 'http://localhost:8080/v1/todo';

function formatDate(dateString) {
    return new Date(dateString).toLocaleString('ru-RU');
}

document.getElementById('createTodoButton').addEventListener('click', function() {
    const text = document.getElementById('todoTitle').value;

    fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ text: text })
    })
    .then(response => {
        console.log("Response " + JSON.stringify(response))
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        const element = data.data;
        document.getElementById('result').innerHTML = `Todo created at ${formatDate(element.createdAt)}: id - ${element.id}, text - ${element.text}, last update - ${formatDate(element.updatedAt)}`;
        document.getElementById('todoTitle').value = '';
    })
    .then(a => {
        document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});

document.getElementById('getTodosButton').addEventListener('click', function() {
    const page = document.getElementById('page').value;
    const perPage = document.getElementById('perPage').value;
    const status = document.getElementById('status').value;

    fetch(`${apiBaseUrl}?page=${page}&perPage=${perPage}&status=${status}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log(data);
            const todosTableBody = document.getElementById('todosTableBody');
            const todoCount = document.getElementById('todoCount');
            const element = data.data;
            todosTableBody.innerHTML = '';

            const todos = element.content;
            if (Array.isArray(todos)) {
                todos.forEach(todo => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${todo.id}</td>
                        <td>${todo.text}</td>
                        <td>${todo.status ? 'Completed' : 'Not Completed'}</td>
                        <td>${formatDate(todo.createdAt)}</td>
                        <td>${formatDate(todo.updatedAt)}</td>
                    `;
                    todosTableBody.appendChild(row);
                });
                todoCount.innerHTML = `Total Todos: ${element.numberOfElements}, completed: ${element.ready}, not completed: ${element.notReady}`;
            } else {
                console.error('API response content is not an array:', todos);
            }
        })
        .catch(error => {
            document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
        });
});

document.getElementById('deleteAllCompletedButton').addEventListener('click', function() {
    fetch(apiBaseUrl, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'All completed todos deleted: ' + JSON.stringify(data);
    })
    .then(a => {
            document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});

document.getElementById('updateAllStatusButton').addEventListener('click', function() {
    const newStatus = document.getElementById('newStatus').value;

    fetch(apiBaseUrl, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ status: newStatus })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'All todos status updated: ' + JSON.stringify(data);
    })
    .then(a => {
            document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});

document.getElementById('deleteTodoButton').addEventListener('click', function() {
    const id = document.getElementById('todoId').value;

    fetch(`${apiBaseUrl}/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'Todo deleted: ' + JSON.stringify(data);
        document.getElementById('todoId').value = '';
    })
    .then(a => {
            document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});

document.getElementById('updateTodoStatusButton').addEventListener('click', function() {
    const id = document.getElementById('updateStatusTodoId').value;
    const newStatus = document.getElementById('newStatusForTodo').value;

    fetch(`${apiBaseUrl}/status/${id}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ status: newStatus })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'Todo status updated: ' + JSON.stringify(data);
        document.getElementById('updateStatusTodoId').value = '';
    })
    .then(a => {
            document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});

document.getElementById('updateTodoTextButton').addEventListener('click', function() {
    const id = document.getElementById('updateTextTodoId').value;
    const newText = document.getElementById('newText').value;

    fetch(`${apiBaseUrl}/text/${id}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ text: newText })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'Todo text updated: ' + JSON.stringify(data);
        document.getElementById('updateTextTodoId').value = '';
        document.getElementById('newText').value = '';
    })
    .then(a => {
        document.getElementById('getTodosButton').click();
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});
