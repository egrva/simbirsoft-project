<#ftl encoding="UTF-8"/>
<#include "base.ftlh"/>
<#macro content>
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

    <title>Registration</title>
    </head>
    <body>
    <main class="my-form">
        <div class="cotainer">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Registration</div>
                        <div class="card-body">
                            <form name="my-form" onsubmit="return validform()" action="/signUp" method="post">

                                <div class="form-group row">
                                    <label for="login" class="col-md-4 col-form-label text-md-right">login</label>
                                    <div class="col-md-6">
                                        <input type="text" id="login" class="form-control" name="login">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="firstName"
                                           class="col-md-4 col-form-label text-md-right">first name</label>
                                    <div class="col-md-6">
                                        <input type="text" id="firstName" class="form-control" name="firstName">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="lastName"
                                           class="col-md-4 col-form-label text-md-right">last name</label>
                                    <div class="col-md-6">
                                        <input type="text" id="lastName" class="form-control" name="lastName">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="middleName"
                                           class="col-md-4 col-form-label text-md-right">middle name</label>
                                    <div class="col-md-6">
                                        <input type="text" id="middleName" class="form-control" name="middleName">
                                    </div>
                                </div>
                                <div class="form-group row">
                                    <label for="birthDate"
                                           class="col-md-4 col-form-label text-md-right">birth date</label>
                                    <div class="col-md-6">
                                        <input type="text" id="birthDate" class="form-control" name="birthDate"
                                               placeholder="dd-MM-yyyy">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">Password</label>
                                    <div class="col-md-6">
                                        <input type="password" id="password" class="form-control" name="password">
                                    </div>
                                </div>

                                <div class="form-group row">
                                    <label for="password" class="col-md-4 col-form-label text-md-right">Role</label>
                                    <div class="col-md-6">
                                        <p><select name="role">
                                                <option selected="selected" value="USER">USER</option>
                                                <option value="ADMIN">ADMIN</option>
                                            </select>
                                        </p>
                                    </div>
                                </div>


                                <div class="col-md-6 offset-md-4">
                                    <button class="btn btn-info btn-block my-4" type="submit">Join us</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>

    </main>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    </body>
    </html>
</#macro>
<@main/>