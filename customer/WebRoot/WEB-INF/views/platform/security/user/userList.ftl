<#assign shiro=JspTaglibs["/WEB-INF/tlds/shiro.tld"]/>
<html>
<head>
    <title>用户管理</title>
</head>
      
<body> 
<!--content str-->
    <div class="operation-button">
       <@shiro.hasPermission name="sys:user:list:create"> 
          <a href="javascript:toAdd();" class="operation-button-a">新增用户</a>        
       </@shiro.hasPermission>
       <@shiro.hasPermission name="sys:user:list:update"> 
          <a href="javascript:toEdit();" class="operation-button-a">修改用户</a>        
       </@shiro.hasPermission>
       <@shiro.hasPermission name="sys:user:list:delete"> 
          <a href="javascript:toDelete();" class="operation-button-a">删除用户</a>        
       </@shiro.hasPermission>
    </div>
    <div class="container-fluid">
         <div class="row-fluid">
          <!--内容 标题 start-->
            <div class="content-tit clearfix">
                 <div class="cur_set">
                    <div class="pull-right" id="search">
                         <input type="text" class="" id="searchValue" maxlength="20" placeholder="请输入用户账号或姓名">
                         <button id="search" class="tip-bottom" type="submit">
                              <i class="icon-search icon-white"></i>
                          </button>
                    </div>
                </div>
            </div>
                <table class="table table-bordered table-textCenter table-striped table-hover">
                    <thead>
                    <tr>
                     <th width="60" ><input id="checkAll" onclick="selectAll()" type="checkbox"></th>
                        <th width="60" >序号</th>
                        <th>用户账号</th>
                        <th>用户姓名</th>
                        <th>所属组织</th>
                        <th>创建日期</th>
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
<script type="text/javascript" src="${basePath}/platform/js/security/user.js?v=${js_version}"></script>