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
function openAuthorInfo(id) {
        $.post('getAuthorInfoById', {
                id : id
            },
            function (data, status) {
                var dt = data;
                $("#authorNameDialog").html('Name: ' + data.name);
                $("#authorInfoDialog").html('Info: ' + data.authorInfo);
                $("#addressDialog").html('Address: ' + data.address);
                $("#countOfBooksDialog").html('Amount of books: ' + data.books.length);
                $("#birthdayDialog").html('Bidthday: ' + data.birthday);

                console.log();

            });
        $('#dialog').dialog('open');
}


$(document).ready(function() {
    $( "#dialog" ).dialog({
        autoOpen: false
    });
});