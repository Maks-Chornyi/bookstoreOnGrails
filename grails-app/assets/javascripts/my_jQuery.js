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


function openAuthorInfo(id) {
        $.post('getAuthorInfoById', {
                id : id
            },
            function (data, status) {
                $("#authorNameDialog").html('Name: ' + data.name);
                $("#authorInfoDialog").html('Info: ' + data.authorInfo);
                $("#addressDialog").html('Address: ' + data.address);
                $("#countOfBooksDialog").html('Amount of books: ' + data.books.length);
                $("#birthdayDialog").html('Bidthday: ' + data.birthday);
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

/*
function deleteBook(id) {
    var confirmation = confirm('Do you really wanna delete this author');
    if(confirmation) {
        $('#deleteAuthorInput').attr("value",id);
        $('.deleteAuthorForm').submit();
    } else {
        console.log("You decided don't delete this author =)");
    }
}*/
