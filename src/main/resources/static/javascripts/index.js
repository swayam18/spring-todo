var createNewTask = function(event) {
    event.preventDefault();
    var task = $("#task").val();
    var csrf = getCsrfToken("#new-todo-form")
    $.post("/api/todo", { task: task, _csrf: csrf })
        .done(function(data) {
            $("#task").val("");
            addTodo(data);
        });
}

var deleteTask = function (event) {
    event.preventDefault();

    var csrf = getCsrfToken("#new-todo-form")
    var id = $(this).data("id");
    $.ajax({
        url: "/api/todo/"+ id,
        method: "DELETE",
        headers: {
            "X-CSRF-TOKEN": csrf
        }
    }).done(function(data) {
        $("#todo-item-"+id).remove();
    });
}

var todoTemplate;
var addTodo = function(todo) {
    var startingIndex = $("#todo-list li").length;
    var context = {todos: [todo]};

    $("#todo-list").append(todoTemplate(context));
}

$(document).ready(function() {
    console.log("Hello, World!");
    var source   = $("#todo-template").html();
    todoTemplate = Handlebars.compile(source);

    $("#new-todo-form").on("submit", createNewTask );
    $(document).on("click", ".delete-link", deleteTask);
});

var getCsrfToken = function(formSelector) {
    return $(formSelector + " input[name=_csrf]").val();
}
