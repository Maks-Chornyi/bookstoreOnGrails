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
        <p>Name<g:field type="text" name="name"></g:field>
        Address<g:field type="text" name="address"></g:field>
        Info<g:field type="text" name="info"></g:field>
        <g:field type="submit" name="info" value="Add"></g:field></p>
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