<!DOCTYPE html>

<html lang="en">
<head>
    <title>Author`s index page</title>
    <asset:stylesheet src="style.css"/>
    <asset:javascript src="jquery-3.3.1.min.js"/>
    <asset:javascript src="my_jQuery.js"/>
</head>

<body>
<h1>Info about ${author.name}</h1>
<div class="general-info">
    <g:if test="${author.books.size() > 0}">
        <p>${author.name} has written <storng>${countOfAuthorsBooks}</storng> book<g:if test="${countOfAuthorsBooks > 1}">s</g:if></p>
        <p>Most successful book is: <strong>${mostSuccessfulBook.title}</strong> (<strong>${countOfCopiesOfMostSuccessfulBook}</strong> copies)</p>
        <p>Unsuccessful book is: <strong>${unsuccessfulBook.title}</strong> (<strong>${countofCopiesOfUnsuccessfulBook}</strong> copies)</p>
        <p>First published book is: <storng>${firstBookOfAuthor.title}</storng> (published at <strong>${publishDateOfFirstBook}</strong></p>
        <p>Last published book is: <strong>${lastBookOfAuthor.title}</strong> (published at <strong>${publishDateOfLastBook}</strong></p>
    </g:if>
    <g:else>
        <p>${author.name} hasn't written any books yet</p>
    </g:else>
</div>

<div class="edit-author">
    <g:form controller="author" action="edit">
        <table class="edit-table-author">
            <tr>
                <td>Name</td>
                <td><g:textField name="name" value="${author.name}"/></td>
            </tr>
            <tr>
                <td>Birthday</td>
                <td><g:datePicker name="birthday" precision="day" value="${author.birthday}"/></td>
            </tr>
             <tr>
                 <td>Address</td>
                 <td><g:textField name="address" value="${author.address}"/></td>
             </tr>
             <tr></tr>
            <tr>
                <td>Author info</td>
                <td><g:textField name="authorInfo" value="${author.authorInfo}"/></td>
            </tr>
        </table>
        <g:select name="books"
                  from="${bookstoreongrails.Book.list()}"
                  value=""
                  optionKey="id"
                  optionValue="title"
                  multiple="true">

        </g:select>
        <g:submitButton name="submit" value="Save"/>
    </g:form>
</div>

</body>

</html>