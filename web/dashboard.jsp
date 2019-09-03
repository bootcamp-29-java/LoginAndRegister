<%-- 
    Document   : dashboard
    Created on : Sep 2, 2019, 1:01:21 AM
    Author     : Lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    response.setHeader("Cache-Control", "no-cache");
    response.setHeader("Cache-Control", "no-store");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
    String status = (String) session.getAttribute("status");
    String username = (String) session.getAttribute("sessionlogin");
    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1 
    response.setHeader("Pragma", "no-cache"); //HTTP 1.0 
    response.setDateHeader("Expires", 0);
//    out.println(status + "  ->" + username);
    if (username == null) {
        out.println("<script>alert(\"Anda Harus Login Terlebih Dulu!\")</script>");
        out.println("<script>window.location.replace(\"login.jsp\");</script>");
    } else {
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active"><a href="index.jsp" class="nav-link">HOME</a></li>
                    <li class="nav-item active"><a href="region.jsp" class="nav-link">REGION</a></li>
                    <li class="nav-item active"><a href="country.jsp" class="nav-link">COUNTRY</a></li>
                    <li><button class="btn btn-danger" onClick="logout()">LOGOUT</button></li>
                </ul>
            </div>
        </nav>
        <h1>Hello World!</h1>


        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
                        function logout() {
                            swal({
                                title: "Apakah Anda Yakin?",
                                text: "Tekan Ok untuk logout!",
                                icon: "warning",
                                buttons: true,
                                dangerMode: true
                            }).then((willDelete) => {
                                if (willDelete) {
                                    window.location.href = "loginservlet?action=logout";
                                } else {
                                    swal("Anda Batal Logout!");
                                }
                            });
                        }
        </script>
        <%
            if (status != null) {
                if (status.equalsIgnoreCase("data berhasil dihapus") || status.equalsIgnoreCase("data berhasil disimpan") || status.equalsIgnoreCase("Berhasil Login")) {
                    out.println("<script type=\"text/javascript\">;");
                    out.println("swal(\"Good job!\", \"" + status + "\", \"success\");");
                    out.println("</script>;");
                } else {
                    out.println("<script type=\"text/javascript\">;");
                    out.println("swal(\"GAGAL!\", \"" + status + "\", \"error\");");
                    out.println("</script>;");
                }
            }
        %>
    </body>
    <%
        }

        session.removeAttribute("status");
    %>
</html>
