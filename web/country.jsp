<%-- 
    Document   : country
    Created on : Aug 27, 2019, 2:35:31 PM
    Author     : asus
--%>


<%@page import="models.Region"%>
<%@page import="models.Country"%>
<%@page import="java.util.List"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>COBA JAVA WEB</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="DataTables/css/datatables.min.css" rel="stylesheet">
        
    </head>
    <body>
        <nav class="navbar navbar-inverse navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapse" data-toggle="collapse" data-target="#navbar" aria-expanded ="false" aria-controls="navbar">
                        <span class="sr-only"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand">JAVA WEB COBA</a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">HOME</a></li>
                        <li><a href="region.jsp">REGION</a></li>
                        <li><a href="country.jsp">COUNTRY</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        </br>
        </br>
        </br>
        <div class="container">
            <!--Initialize-->
        <%
            List<Country> countries = (List<Country>) session.getAttribute("countries");
            List<Region> regions = (List<Region>) session.getAttribute("regions");
            String status = (String) session.getAttribute("status");
            out.println(status);
            if (countries == null)
                response.sendRedirect("countryservlet");
            else {
        %>
        <!--End of Initialize-->
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
                                <h4 class="modal-title">FORM COUNTRY</h4>
                            </div>
                            <div class="modal-body">
                                <!--Buka Form-->
                                <form class="form-horizontal" action="countryservlet" method="POST">
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">ID :</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" type="text" id="countryid" name="countryid" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">Name :</label>
                                        <div class="col-lg-8">
                                            <input class="form-control" type="text" id="countryname" name="countryname" />
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">Region :</label>
                                        <div class="col-lg-8">
                                            <select class="form-control" name="regionid" id="regionid">
                                                <%for (Region r : regions) { %>
                                                <option value="<%=r.getId()%>" ><%=r.getName()%></option>    
                                                <%} %>
                                            </select>
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
                <table id="tablecountry" class="table table-striped">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Region</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for (Country c : countries) {
                        %>
                        <tr>
                            <td><%=c.getId()%></td>
                            <td><%=c.getName()%></td>
                            <td><%=c.getRegion().getName()%></td>
                            <td>
                                <button onclick="getData('<%=c.getId()%>', '<%=c.getName()%>','<%=c.getRegion().getId()%>')" type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                                    EDIT</button>
                            <a onclick='setAlert("<%=c.getId()%>")' class="btn btn-danger">HAPUS</a></td>
                        </tr>
                        <% }%>        
                    </tbody>
                </table>
                <!--End of Show Data-->
            </div>
        </div>
        </br>   

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="jQuery/jquery-3.3.1.min.js"></script>
        <script src="DataTables/js/datatables.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#tablecountry').DataTable();
            });
        </script>
        <%
            if (status != null) {
                if (status.equalsIgnoreCase("Data Berhasil Dihapus") || status.equalsIgnoreCase("Data Berhasil Disimpan")) {
                    out.println("<script type=\"text/javascript\">;");
                    out.println("swal(\"Good job!\", \""+status+"\", \"success\");");
                    out.println("</script>;");
                } else {
                    out.println("<script type=\"text/javascript\">;");
                    out.println("swal(\"GAGAL!\", \""+status+"\", \"error\");");
                    out.println("</script>;");
                }
            }
        %>
        <script>
            function getData(id, name,regionid) {
                document.getElementById("countryid").value = id;
                document.getElementById("countryname").value = name;
                document.getElementById("regionid").value = regionid;
                console.log(regionid);
                if (id !== '') {
                    document.getElementById("countryid").readOnly = true;
                } else {
                    document.getElementById("countryid").readOnly = false;
                }
            }

            function setAlert(id) {
                swal({
                    title: "Apakah Anda Yakin?",
                    text: "Tekan Ok, Jika Anda Yakin Untuk Menghapus Data!",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true
                }).then((willDelete) => {
                    if (willDelete) {
                        window.location.href = "countryservlet?action=delete&&id=" + id;
                    } else {
                        swal("Anda Membatalkan Mengahpus Data!");
                    }
                });
            }
        </script>

    </body>

    <!--Start of Destroy Session-->
    <%
        }
        session.removeAttribute("countries");
        session.removeAttribute("regions");
        session.removeAttribute("status");
    %>
    <!--End of Destroy Session-->

</html>