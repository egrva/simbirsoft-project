<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
<#macro content>
<meta charset="UTF-8">
<title>Login</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link href="/public/css/registration.css" rel="stylesheet">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<!-- Required meta tags -->
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Fonts -->
<link rel="dns-prefetch" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css?family=Raleway:300,400,600" rel="stylesheet" type="text/css">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm">
        </div>
        <div class="col-sm second">
            <div class="d-flex p-2 bd-highlight">
                <!-- Default form login -->
                <form class="text-center border border-light p-5" action = "http://localhost:8080/swagger-ui.html#/" method = "get">

                    <p class="h4 mb-4">Ура, вы успешно вошли в нашу систему!</p>
                    <p class="h4 mb-4">Чтобы посмотреть методы, вы можете открыть Swagger!</p>

                    <!-- Sign in button -->
                    <button class="btn btn-info btn-block my-4" type="submit">Перейти в Swagger</button>
                </form>
            </div>
        </div>
        <div class="col-sm">
        </div>
    </div>
</div>
</#macro>
<@main/>