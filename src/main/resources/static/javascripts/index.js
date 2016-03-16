var createNewTask = function(event) {
    event.preventDefault();
    var task = $("#task").val();
    var csrf = getCsrfToken("#new-todo-form")
    $.post("/api/todo", { task: task, _csrf: csrf })
        .done(function(data) {
            addTodo(data);
        });
}

var todoTemplate;
var addTodo = function(todo) {
    var startingIndex = $("#todo-list li").length;
    var context = {startingIndex: startingIndex, todos: [todo]};

    $("#todo-list").append(todoTemplate(context));
}

$(document).ready(function() {
    console.log("Hello, World!");
    var source   = $("#todo-template").html();
    todoTemplate = Handlebars.compile(source);

    $("#new-todo-form").on("submit", createNewTask );
});

var getCsrfToken = function(formSelector) {
    return $(formSelector + " input[name=_csrf]").val();
}
