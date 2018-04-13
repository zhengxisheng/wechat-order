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
                        <form role="form" method="post" action="/sell/seller/category/save">
                            <div class="form-group">
                                <label>类目名称</label>
                                <input name="categoryName" type="text" class="form-control" value="${(category.categoryName)!''}"/>
                            </div>
                            <div class="form-group">
                                <label>类目编号</label>
                                <input name="categoryType" type="number" class="form-control" value="${(category.categoryType)!''}"/>
                            </div>
                            <input hidden type="text" name="categoryId" value="${(category.categoryId)!''}">
                            <button type="submit" class="btn btn-default">提交</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </BODY>
</html>