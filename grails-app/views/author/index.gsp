<!DOCTYPE html>

<html lang="en">
<head>
    <title>Author`s index page</title>
    <asset:stylesheet src="style.css"/>
</head>

<body>
    <h1>Authors</h1>

    <div class="add-author">
        <h3>Add new Author</h3>
        <g:form controller="author" action="save">
            <label>Name: </label><g:textField name="name"/>
            <label>Birthday: </label><g:datePicker precision="day" name="birthday"/>
            <label>Address: </label><g:textField name="address"/>
            <label>Info: </label><g:textField name="authorInfo"/>
            <g:actionSubmit name="save" value="Save" />
        </g:form>
    </div>

    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Birthday</th>
                <th>Address</th>
                <th>Info</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
        <g:each var="author" in="${authors}">
            <tr>
                <td>${author.name}</td>
                <td><g:formatDate format="yyyy/MM/dd" date="${author.birthday}"/></td>
                <td>${author.address}</td>
                <td>${author.authorInfo}</td>
                <td>smth else</td>
            </tr>
        </g:each>
        </tbody>
    </table>
<div class="authors-info">
    <p>We have <strong>${authorsAmount}</strong> author <g:if test="${authors.size() > 1}">s</g:if> in DB</p>
    <p>Most successful author is <strong>${mostSuccessfulAuthor.name}</strong>, author has <strong>${countOfBooksOfMostsuccessfulAuthor}</strong> books </p>
    <p>Unsuccessful author is <strong>${unsuccessfulAuthor.name}</strong>, author has <strong>${countOfBooksOfUnsuccessfulAuthor}</strong> books </p>
</div>


</body>

</html>