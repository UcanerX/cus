<#assign shiro=JspTaglibs["/WEB-INF/tlds/shiro.tld"]/>
<html>
<head>
    <title>部门管理</title>
</head>
<body> 
<!--content str-->
    <div class="operation-button">
       
    </div>
    <div class="container-fluid">
         <div class="row-fluid">
          <!--内容 标题 start-->
            <div class="content-tit clearfix">
                 <div class="cur_set">
                    <div class="pull-right" id="search">
                         <input type="text" class="" id="searchValue" maxlength="20" placeholder="请输入部门编码或名称">
                         <button id="search" class="tip-bottom" type="submit">
                              <i class="icon-search icon-white"></i>
                          </button>
                    </div>
                </div>
            </div>
            <!--内容 标题 end-->
                <table class="table table-bordered table-textCenter table-striped table-hover">
                    <thead>
                    <tr>
                     <th width="60" ><input id="checkAll" onclick="selectAll()" type="checkbox"></th>
                        <th width="60" >序号</th>
                        <th>部门名称</th>
                        <th>部门编码</th>
                        <th>上级部门</th>
                    </tr>
                    </thead>
                    <tbody class="list-data">
                    </tbody>
                  </table>
         </div>
         <div class="pagination pagination-right pagination-style">
         </div>
    </div>
</body>
</html>
<script type="text/javascript" src="${basePath}/platform/js/security/dept.js?v=${js_version}"></script>