<%@page contentType="text/html; UTF-8" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>登陆</title>

    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="/css/signin.css" rel="stylesheet">


    <!--[if lt IE 9]>
    <script src="/js/es5-shim-master/es5-shim.min.js"></script>
    <script src="/js/es5-shim-master/es5-sham.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">

    <form class="form-signin" action="" method="post">
        <label for="inputUsername" class="sr-only">User name</label> <input
            type="text" name="username" id="inputUsername" class="form-control"
            placeholder="用户名" required> <label
            for="inputPassword" class="sr-only">Password</label> <input
            type="password" name="password" id="inputPassword" class="form-control"
            placeholder="密码" required>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登 录</button>
    </form>

</div>
<!-- /container -->
<div class="error">${error}</div>

</body>
</html>
