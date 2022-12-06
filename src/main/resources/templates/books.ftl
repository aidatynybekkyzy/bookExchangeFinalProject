<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book Exchange</title>
</head>
<body>
<h2>Book Exchange</h2>
<#list books as book>
    <div>
        <p><b>${book.title}</b>${book.author} | <a href="exchange/books/"${book.id}>Details...</a></p>
    </div>
</#list>
<hr>
    <h3>Add new Book</h3>
    <form action="/exchange/books/save" method="post">
        Book title : <input type="text" name="title"/> <br><br>
        ISBN : <input type="text" name="isbn"/> <br><br>
        Author: <input type="text" name="author"/> <br><br>
        Genre : <input type="text" name="genre"/> <br><br>
        Description : <input type="text" name="description"/> <br><br>
        <input type="submit" value="Add book ">
    </form>
</body>
</html>