document.addEventListener("DOMContentLoaded", loadTasks);

function addTask() {
    const input = document.getElementById("taskInput");
    const taskText = input.value.trim();
    if (taskText === "") return;

    const task = {
        text: taskText,
        completed: false
    };

    let tasks = getTasks();
    tasks.push(task);
    saveTasks(tasks);
    input.value = "";
    renderTasks(tasks);
}

function toggleTask(index) {
    let tasks = getTasks();
    tasks[index].completed = !tasks[index].completed;
    saveTasks(tasks);
    renderTasks(tasks);
}

function deleteTask(index) {
    let tasks = getTasks();
    tasks.splice(index, 1);
    saveTasks(tasks);
    renderTasks(tasks);
}

function getTasks() {
    return JSON.parse(localStorage.getItem("tasks")) || [];
}

function saveTasks(tasks) {
    localStorage.setItem("tasks", JSON.stringify(tasks));
}

function renderTasks(tasks) {
    const taskList = document.getElementById("taskList");
    taskList.innerHTML = "";

    tasks.forEach((task, index) => {
        const li = document.createElement("li");
        li.className = task.completed ? "completed" : "";

        li.innerHTML = `
            <span onclick="toggleTask(${index})">${task.text}</span>
            <button onclick="deleteTask(${index})">X</button>
        `;

        taskList.appendChild(li);
    });
}

function loadTasks() {
    const tasks = getTasks();
    renderTasks(tasks);
}
