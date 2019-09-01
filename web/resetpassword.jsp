<%-- 
    Document   : login
    Created on : Aug 30, 2019, 9:46:43 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COBA JAVA WEB</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="DataTables/css/datatables.min.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">
            <!--Inisialisasi session-->
            <%
                String token = (String) session.getAttribute("token");
                out.println(token);
            %>
            <!--End Inisialisasi session-->

           
            <div class="shadow-lg p-3 mb-5 bg-white rounded" style="margin-top:-12em; margin-left: -15em; position: fixed; width: 30em; height: 20em; top: 50%;left:50%;">
                <center><h3>Reset Password</h3></center>
                <form action="verifikasi" method="POST">
                    <div class="form-group">
                        <label>New Password</label>
                        <input class="form-control" name="newPassword" id="newPassword" type="text">
                        <input class="form-control" name="token" id="token" type="hidden" value="<%=token%>">
                    </div>
                    <div class="form-group">
                        <label>Confirm Password</label>
                        <input class="form-control" name="confirmPass" id="confirmPass" type="password">
                    </div>
                    <button class="btn btn-warning" type="submit" style="margin-bottom: 15px;">Reset Password</button>

                </form>
               </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="DataTables/js/datatables.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <%
//            if (status != null) {
//                if (status.equalsIgnoreCase("Gagal Login") || status.equalsIgnoreCase("Email Anda Tidak Terdaftar!")) {
//                    out.println("<script type=\"text/javascript\">;");
//                    out.println("swal(\"Good job!\", \"" + status + "\", \"error\");");
//                    out.println("</script>;");
//                }else if(status.equalsIgnoreCase("Email Reset Password Telah Dikirimkan!")){
//                    out.println("<script type=\"text/javascript\">;");
//                    out.println("swal(\"Good job!\", \"" + status + "\", \"success\");");
//                    out.println("</script>;");                    
//                }
//            }
//            session.removeAttribute("status");
        %>
    </body>

</html>
