<%-- 
    Document   : region
    Created on : Aug 27, 2019, 2:35:31 PM
    Author     : asus
--%>

<%@page import="models.Region"%>
<%@page import="java.util.List"%>
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
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active"><a href="dashboard.jsp" class="nav-link">HOME</a></li>
                    <li class="nav-item active"><a href="#" class="nav-link">REGION</a></li>
                    <li class="nav-item active"><a href="country.jsp" class="nav-link">COUNTRY</a></li>
                    <li class="nav-item active"><button class="btn btn-danger" onClick="logout()">LOGOUT</button></li>
                </ul>
            </div>
        </nav>
        </br>
        </br>
        </br>
            <!--Initialize-->
            <%

                List<Region> regions = (List<Region>) session.getAttribute("regions");
                String status = (String) session.getAttribute("status");
                String username = (String) session.getAttribute("sessionlogin");
                out.println(status +"  ->"+username);
                if (username == null) {
                    out.println("<script>alert(\"Anda Harus Login Terlebih Dulu!\")</script>");
                    out.println("<script>window.location.replace(\"login.jsp\");</script>");
                } else {
                    if (regions == null)
                        response.sendRedirect("regionservlet");
                    else {
            %>
            <!--End of Initialize-->
        <div class="container">
            <div class="starter-template">
                <div style="margin-left: 1080px">
                    <button type="button" class="btn btn-primary" onclick="getData('', '')" data-toggle="modal" data-target="#myModal">ADD</button>
                </div>
                <div id="myModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">ADD REGION</h4>
                            </div>
                            <div class="modal-body">
                                <!--Buka Form-->
                                <form class="form-horizontal" action="regionservlet" method="POST">
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">ID :</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" type="number" id="regionId" name="regionId" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">Name :</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" type="text" id="regionName" name="regionName" />
                                        </div>
                                    </div>
                            </div>
                            <div class="modal-footer">
                                <input type="submit" class="btn btn-success" value="Save" />
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </form>
                                <!--Tutup Form Input-->
                            </div>
                        </div>
                    </div>
                </div>
                </br>

            
                <!--Show Data-->
                <table id="tableregion" class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Delete Action</th>
                            <th>Edit Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Region r : regions) {
                        %>
                        <tr>
                            <td><%=r.getId()%></td>
                            <td><%=r.getName()%></td>
                            <td>
                                <button onclick="getData('<%=r.getId()%>', '<%=r.getName()%>')" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                    EDIT</button>
                            </td>
                            <td><a onclick='setAlert(<%=r.getId()%>)' class="btn btn-danger">HAPUS</a></td>
                        </tr>
                        <% }%>        
                    </tbody>
                </table>
                <!--End of Show Data-->
            </div>
        </div>
        </br>   

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="DataTables/js/datatables.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        

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
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tableregion').DataTable();
            });
        </script>

        <script>
            function getData(id, name) {
                document.getElementById("regionId").value = id;
                document.getElementById("regionName").value = name;
                console.log(id);
                if (id !== '') {
                    document.getElementById("regionId").readOnly = true;
                } else {
                    document.getElementById("regionId").readOnly = false;
                }
            }

            function setAlert(id) {
                swal({
                    title: "Apakah Anda Yakin?",
                    text: "Tekan Ok, Jika Anda Yakin Untuk Menghapus Data!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true,
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location.href = "regionservlet?action=delete&&id=" + id;
                    } else {
                        swal("Anda Membatalkan Mengahpus Data!");
                    }
                });
            }
        </script>
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

    </body>

    <!--Start of Destroy Session-->
    <%
            }
        }
        session.removeAttribute("regions");
        session.removeAttribute("status");
    %>
    <!--End of Destroy Session-->

</html>