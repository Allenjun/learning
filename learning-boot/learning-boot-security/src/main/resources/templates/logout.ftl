<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
          crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet"
          crossorigin="anonymous"/>
    <title>LOGOUT</title>
</head>
<body>
<form class="form-signin" method="post" action="/logout">
    <input name="_csrf" type="hidden" value="${_csrf.token}"/>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Logout</button>
</form>
</body>
</html>