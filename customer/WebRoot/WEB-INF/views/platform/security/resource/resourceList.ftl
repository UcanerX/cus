<#assign shiro=JspTaglibs["/WEB-INF/tlds/shiro.tld"]/>
<html>
<body> 
<!--content str-->
    <div class="operation-button">
       <@shiro.hasPermission name="sys:resource:list:create"> 
          <a href="javascript:toAdd();" class="operation-button-a">新增资源</a>        
       </@shiro.hasPermission>
       <@shiro.hasPermission name="sys:resource:list:update"> 
          <a href="javascript:toEdit();" class="operation-button-a">修改资源</a>        
       </@shiro.hasPermission>
       <@shiro.hasPermission name="sys:resource:list:delete"> 
          <a href="javascript:toDelete();" class="operation-button-a">删除资源</a>        
       </@shiro.hasPermission>
    </div>
     <div class="container-fluid">
         <div class="row-fluid">
          <!--内容 标题 start-->
            <div class="content-tit clearfix">
                 <div class="cur_set">
                    <div class="pull-right" id="search">
                         <input type="text" class="" id="searchValue" maxlength="20" placeholder="请输入资源编码或名称">
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
                        <th>资源编号</th>
                        <th>资源名称</th>
                        <th>父资源</th>
                        <th>资源类型</th>
                        <th>操作类型</th>
                        <th>资源操作值</th>
                    </tr>
                    </thead>
                    <tbody class="list-data"></tbody>
                </table>
         </div>
         <div class="pagination pagination-right pagination-style">
         </div>
    </div>
</body>
</html>
<script type="text/javascript" src="${basePath}/platform/js/security/resource.js?v=${js_version}"></script>