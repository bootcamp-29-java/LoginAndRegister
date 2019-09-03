<%-- 
    Document   : login.jsp
    Created on : Aug 30, 2019, 11:02:41 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Belajar Java Web - Register</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <style>
            body{
                background: url("http://cdn.backgroundhost.com/backgrounds/subtlepatterns/arches.png");
            }

            .absolute-center {
                margin: auto;
                position: absolute;
                top: 0; left: 0; bottom: 0; right: 0;
            }

            .absolute-center.is-responsive {
                width: 50%; 
                height: 50%;
                min-width: 200px;
                max-width: 600px;
                padding: 0px;
            }
        </style>

        <script>
            function validasi() {
                var password = document.form.password.value;
                var confirm = document.form.confirm.value;
                if (password != confirm) {
                    swal({
                        title: "Gagal !",
                        text: "Password dan Konfirmasi Password Tidak Sama !",
                        icon: "error",
                    }).then(function () {
                        document.getElementById("registerForm").reset();
                        grecaptcha.reset();
                        document.form.employeeId.focus();
                    });
                    return false;
                }
            }
        </script>

    </head>
    <body>
        <div class="container">
            <%
                String status = (String) session.getAttribute("status");
                out.println(status);
            %>
            <div class="absolute-center is-responsive">
                <div class="row">
                    <div class="col-md">
                        <form name="form" action="registerservlet" method="POST" id="registerForm" onsubmit="return validasi()">
                            <div class="form-group">
                                <h3 class="text-center">Register Account</h3>
                                <hr>
                            </div>
                            <div class="row">
                                <div class="col-md-3">
                                    <div class="form-group">
                                        <input type="text" class="form-control" id="employeeId" name="employeeId" onkeypress="javascript:return isNumber(event)" placeholder="Employee ID" required>     
                                    </div>
                                </div>
                                <div class="col-md-9">
                                    <div class="form-group">
                                        <input type="text" minlength="6" id="username" name="username" class="form-control" name='username' placeholder="Username" pattern="^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$" oninvalid="this.setCustomValidity('Username Harus Kombinasi Huruf Besar, Huruf kecil dan Angka')" oninput="setCustomValidity('')"  required/>          
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group">
                                        <input type="email" id="email" class="form-control" name="email" placeholder="Email" required/>          
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="password" id="password" name="password" minlength="6" class="form-control" placeholder="Password" required>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <input type="password" id="confirm" name="confirm" minlength="6" class="form-control" placeholder="Confirm Password" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="g-recaptcha" data-sitekey="6LdDwLUUAAAAAFLpOpQCbMoIAF1SxSRqaiIVWwNG" data-callback="correctCaptcha"></div>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block" >Register</button>
                            </div>
                            <div class="form-group text-center">
                                <a href="login.jsp">< Back To Login <%out.println(status);%></a>
                            </div>
                        </form>    
                    </div>  
                </div>    
            </div>
        </div>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <%
            if (status != null) {
                if (!status.equalsIgnoreCase("Cek Email Untuk Verifikasi Akun !")){
                    out.println("<script type=\"text/javascript\">;");
                    out.println("swal(\"GAGAL !\", \"" + status + "\", \"error\");");
                    out.println("</script>;");
                }
            }
        %>
    </body>
    <%
        session.removeAttribute("status");
    %>
</html>
