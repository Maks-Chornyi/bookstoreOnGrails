console.log("I'm your jQuery");

function deleteAuthor(id) {
    var confirmation = confirm('Do you really wanna delete this author');
    if(confirmation) {
        $('#deleteAuthorInput').attr("value",id);
        $('.deleteAuthorForm').submit();
    } else {
        console.log("You decided don't delete this author =)");
    }
}

function deleteBook(id) {
    var confirmation = confirm('Delete this book?');
    if(confirmation) {
        $.post('deleteBook', {
            id: id
        },
            function (data, status) {
                window.location.reload()
            });
    } else {
        console.log("You decided don't delete this author =)");
    }
}

function deleteAllLogs() {
    var answ = confirm('Do you really wanna delete all logs? This action can\'t be revert');
    if(answ) {
        $.get('deleteLogs', function (status) {
            console.log(status)
        });
    }

}


function openAuthorInfo(id) {
        $.post('getAuthorInfoById', {
                id : id
            },
            function (data) {
                $("#authorNameDialog").html(data.name);
                $("#authorInfoDialog").html(data.authorInfo);
                $("#addressDialog").html(data.address);
                $("#countOfBooksDialog").html(data.books.length);
                $("#birthdayDialog").html(data.birthday);
            });
        $('#dialog').dialog('open');
}

function openDialogForAddingBook() {
    $('#addNewBookButton').dialog('open');
}

$(document).ready(function() {
    $("#dialog").dialog({
        autoOpen: false
    });

    $("#addNewBookButton").dialog({
        autoOpen: false,
        width: "500px"
    });
});