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
                $("#authddorInfo").html(data.id);
            });
}


$(document).ready(function() {

});