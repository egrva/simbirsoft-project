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
                <form class="text-center border border-light p-5" action="login" method = "post">

                    <p class="h4 mb-4">Join us!</p>

                    <!-- Login -->
                    <input type="text" id="defaultLoginFormLogin" class="form-control mb-4" placeholder="login"
                           name="login">

                    <!-- Password -->
                    <input type="password" id="defaultLoginFormPassword" class="form-control mb-4"
                           placeholder="Password"
                           name="password">

                    <div class="d-flex justify-content-around">
                        <div>
                            <!-- Remember me -->
                            <div class="custom-control custom-checkbox">
                                <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember"
                                       value="Yes"
                                       name="checkbox">
                                <label class="custom-control-label" for="defaultLoginFormRemember">Remember
                                    me</label>
                            </div>
                        </div>
                    </div>

                    <!-- Sign in button -->
                    <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

                    <!-- Register -->
                    <p>Have not you join us yet?
                        <a href="/signUp">Let's do it</a>
                    </p>
                </form>
            </div>
        </div>
        <div class="col-sm">
        </div>
    </div>
</div>
</#macro>
<@main/>