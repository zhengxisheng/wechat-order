<html>
<#include "../common/header.ftl">
<BODY>
<div id="wrapper" class="toggled">
<#--边栏sidebar-->
<#include "../common/nav.ftl">
    <div id="page-content-wrapper">
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div class="alert alert-success alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                        <h4>
                            错误!
                        </h4> <strong>${msg}</strong><a href="${url}" class="alert-link">3s后自动跳转</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    setTimeout('location.href="${url}"', 3000);
</script>
</BODY>
</html>