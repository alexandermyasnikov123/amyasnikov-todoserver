const apiBaseUrl = 'http://localhost:8080/v1/todo';

document.getElementById('createTodoButton').addEventListener('click', function() {
    const text = document.getElementById('todoTitle').value; // Changed from title to text

    fetch(apiBaseUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ text: text }) // Updated to send text
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById('result').innerHTML = 'Todo created: ' + JSON.stringify(data);
        document.getElementById('todoTitle').value = ''; // Clear input field
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
            console.log(data); // Inspect the response data
            const todosTableBody = document.getElementById('todosTableBody');
            const todoCount = document.getElementById('todoCount');
            todosTableBody.innerHTML = ''; // Clear table before adding new items

            // Access the content from the new response structure
            const todos = data.data.content; // Updated to access the new structure
            if (Array.isArray(todos)) {
                todos.forEach(todo => {
                    const row = document.createElement('tr'); // Create a new table row
                    row.innerHTML = `
                        <td>${todo.id}</td>
                        <td>${todo.text}</td>
                        <td>${todo.status ? 'Completed' : 'Not Completed'}</td>
                        <td>${new Date(todo.createdAt).toLocaleString()}</td>
                        <td>${new Date(todo.updatedAt).toLocaleString()}</td>
                    `;
                    todosTableBody.appendChild(row); // Append the row to the table body
                });
                todoCount.innerHTML = `Total Todos: ${data.data.numberOfElements}`; // Display the total count of todos
            } else {
                console.error('API response content is not an array:', todos);
            }
        })
        .catch(error => {
            document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
        });
});

// Other event listeners remain unchanged
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
        document.getElementById('todoId').value = ''; // Clear input field
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
        document.getElementById('updateStatusTodoId').value = ''; // Clear input field
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
        document.getElementById('updateTextTodoId').value = ''; // Clear input field
        document.getElementById('newText').value = ''; // Clear input field
    })
    .catch(error => {
        document.getElementById('result').innerHTML = '<span class="error">Error: ' + error.message + '</span>';
    });
});
