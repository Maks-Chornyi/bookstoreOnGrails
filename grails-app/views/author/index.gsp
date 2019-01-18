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
            <label>Address: </label><g:textField name="address" />
            <label>Info: </label><g:textField name="authorInfo" />
            <g:actionSubmit value="Add" />
        </g:form>
    </div>

    <table>
        <thead>
            <tr>
                <th>first</th>
                <th>second</th>
                <th>third</th>
                <th>fourth</th>
            </tr>
        </thead>
        <tbody>
        <g:each var="author" in="${authors}">
            <tr>
                <td>${author.name}</td>
                <td>${author.address}</td>
                <td>${author.authorInfo}</td>
                <td>smth else</td>
            </tr>
        </g:each>
        </tbody>
    </table>
<div class="authors-info">
    <p>1</p>
    <p>2</p>
    <p>3</p>
    <p>1</p>
</div>


</body>

</html>