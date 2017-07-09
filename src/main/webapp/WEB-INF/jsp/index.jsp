<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="/js/es5-shim-master/es5-shim.min.js"></script>
    <script src="/js/es5-shim-master/es5-sham.min.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->
    <title>SMART</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="/vendors/font-awesome/css/font-awesome.min.css" rel="stylesheet">
    <!-- toastr -->
    <link href="/vendors/toastr/toastr.min.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="/css/custom.css" rel="stylesheet">
</head>

<body class="nav-md">
<div class="container body">
    <div class="main_container">
        <div class="col-md-3 left_col">
            <div class="left_col scroll-view">
                <div class="navbar nav_title" style="border: 0;">
                    <a href="#" class="site_title">
                        <span>SMART</span>
                    </a>
                </div>

                <div class="clearfix"></div>
                <br/>

                <!-- sidebar menu -->
                <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
                    <div class="menu_section">
                        <%--<h3 data-bind="text: empName"></h3> --%>
                        <ul class="nav side-menu">
                            <li>
                                <a>
                                    <i class="fa fa-gear"></i> 系统管理
                                    <span class="fa fa-chevron-down"></span>
                                </a>
                                <ul class="nav child_menu">
                                    <li>
                                        <a href="#" url="/page/user.html">用户管理</a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- /sidebar menu -->

                <!-- /menu footer buttons -->
                <div class="sidebar-footer hidden-small">
                    <a data-toggle="tooltip" data-placement="top" title="Settings">
                        <span class="glyphicon glyphicon-cog"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                        <span class="glyphicon glyphicon-fullscreen"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Lock">
                        <span class="glyphicon glyphicon-eye-close"></span>
                    </a>
                    <a data-toggle="tooltip" data-placement="top" title="Logout">
                        <span class="glyphicon glyphicon-off"></span>
                    </a>
                </div>
                <!-- /menu footer buttons -->
            </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">

            <div class="nav_menu">
                <nav class="" role="navigation">
                    <div class="nav toggle" style="width: auto">
                        <a id="menu_toggle">
                            <i class="fa fa-bars"></i>
                            <h3 style="display: inline"></h3>
                        </a>
                    </div>

                    <ul class="nav navbar-nav navbar-right">
                        <li class="">
                            <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                                <img src="images/img.jpg" alt="">
                                <span class=" fa fa-angle-down"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-usermenu pull-right">
                                <li>
                                    <a href="/logout">
                                        <i class="fa fa-sign-out pull-right"></i>退出系统
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </nav>
            </div>

        </div>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" style="position: absolute; top: 58px; bottom: 0; right: 0; left: 0; display: block">
            <iframe class="" id="mainpart" style="width: 100%; height: 100%; position: relative; top: 0; bottom: 0; left: 0; right: 0; display: block; overflow: hidden; overflow-y: auto;" frameborder="0"></iframe>
        </div>
        <!-- /page content -->
    </div>
</div>
<!-- modal end-->
<!-- jQuery -->
<script src="/js/jquery/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="/js/bootstrap/bootstrap.min.js"></script>
<!-- knockout -->
<script src="/js/knockout.js"></script>
<script src="/js/knockout-paging.js"></script>
<script src="/js/commonUtils.js"></script>
<!-- toastr -->
<script src="/vendors/toastr/toastr.min.js"></script>
<!-- Custom Theme Scripts -->
<script src="/js/custom.js"></script>
<script>
    var lk = $(".child_menu a");
    $(lk).each(function(i, r) {
        var t = $(r).attr("url");
        var title = $(r).text();
        $(r).click(function() {
            $('#menu_toggle h3').text(title)
            $("#mainpart").prop("src", t);
        });
    });
</script>
</body>
</html>
