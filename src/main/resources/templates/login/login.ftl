<html>
    <#include "../common/header.ftl">
    <BODY>
    <div class="container" style="margin-top: 250px">
        <div class="row clearfix">
            <div class="col-md-4 column">
            </div>
            <div class="col-md-5 column">
                <form class="form-horizontal" role="form" action="/sell/seller/login/login" method="post">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" name="username"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-8">
                            <input type="password" class="form-control" name="password" />
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label><input type="checkbox" />Remember me</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-4 column">
            </div>
        </div>
    </div>
    </BODY>
</html>