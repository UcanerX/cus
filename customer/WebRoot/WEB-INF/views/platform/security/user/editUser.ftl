<#assign shiro=JspTaglibs["/WEB-INF/tlds/shiro.tld"]/>
		<link rel="stylesheet" href="${basePath}/platform/css/tree/metroStyle.css" type="text/css">
	<script type="text/javascript" src="${basePath}/platform/js/tree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${basePath}/platform/js/tree/jquery.ztree.excheck.min.js"></script>
    <div id="content-header">
        <div class="widget-title radius-3"><h3 class="title">修改账户</h3></div>
    </div>
    <div class="container-fluid">
         <div class="row-fluid">
            <div class="widget-content radius-3">
                <!--内容-->
               <form id="formBean" class="form-horizontal">  
                    <input type="hidden" id="id" name="id" value="${user.id}" /> 
                    <input type="hidden" id="status" name="status" value="${user.status}" />       
                    <input type="hidden" id="roleIds" name="roleIds" value="" />
                    <input type="hidden" id="deptId" name="deptId" value="${user.deptId}" />     
                    <div class="control-group">
                         <label class="control-label" ><span class="mandatory">*</span>账户名</label>
                         <div class="controls"><input type="text" class="span12" value="${user.account}" id="account" name="account"/></div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" >姓名</label>
                         <div class="controls"><input type="text" class="span12" value="${user.name}" name="name" /></div>
                    </div>
                     <div class="control-group">
                         <label class="control-label" >电话号码</label>
                         <div class="controls"><input type="text" class="span12" value="${user.mobile}" id="mobile" name="mobile" /></div>
                    </div>
                    <div class="control-group">
                         <label class="control-label">选择角色</label>
                         <div class="controls">
                              <div class="role-select span12">
                               <#list roleList as role>
                                   <label class="inline-block mw120">
                                        <#if role.hasUser==1>
                                        	<input class="states" type="checkbox" checked="checked" name="checkbox" value="${role.id}" />${role.roleName}
                                        <#else>
                                        	<input class="states" type="checkbox" name="checkbox" value="${role.id}" />${role.roleName}
                                        </#if>   
                                    </label>
                                </#list>
                              </div>
                         </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">所属部门</label>
                        <div class="controls" id="deptNameDiv">
                             <input type="text" id="deptName" name="deptName" value="${user.deptName}" class="span11" readonly onClick="showDeptTree()" />
                          	 <button class="btn btn-save" onClick="showDeptTree();return false;" style="width:90px">选择</button>
                        </div>
                    </div>
                    
                </form>
                <!--内容 end-->
            </div>
         </div>
         <div class="row-fluid mt10">
         	<div class="button-style bgfff radius-3">
         		<@shiro.hasPermission name="sys:user:edit:save"> 
          			<button class="btn btn-save" onClick="saveOrUpdate()">保存</button>    
       			</@shiro.hasPermission>
       			<@shiro.hasPermission name="sys:user:edit:cancel"> 
          			<button class="btn btn-remove" onClick="openUrl('platform/user/list')">取消</button>
       			</@shiro.hasPermission>
       		</div>
         </div>
    </div>
    <div id="menuContent"  style="display:none; position: absolute;background-color:#ffffff;overflow-y:auto;overflow-x:hidden;border:1px solid;">
		<ul id="deptTree" class="ztree" style="margin-top:0; width:100%; height: 100px;"></ul>
	</div>
<script>
var setting = {
	async : {
		enable : true,
		url : basePath + "/platform/common/loadDepts",
		autoParam : [ "id=pId", "name", "level" ],
		otherParam : {
		    "userId"   : '${user.id}',
			"loadType" : "1"
		},
		dataFilter : null
	},
	check : {
		enable : true,
		chkStyle : "radio",
		radioType : "all"
	},
	view : {
		dblClickExpand : false
	},
	data : {
		simpleData : {
			enable : true
		}
	},
	callback : {
		onClick : onClick,
		onCheck : onCheck
	}
};

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("deptTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("deptTree"),
		nodes = zTree.getCheckedNodes(true),
		name = "";
	value = "";
	for (var i = 0, l = nodes.length; i < l; i++) {
		name += nodes[i].name + ",";
		value += nodes[i].id + ",";
	}
	if (name.length > 0)
		name = name.substring(0, name.length - 1);
	if (value.length > 0)
		value = value.substring(0, value.length - 1);
	var deptName = $("#deptName");
	deptName.attr("value", name);
	var deptId = $("#deptId");
	deptId.attr("value", value);
}

function showDeptTree() {
	var deptName = $("#deptName");
	var deptNameOffset = $("#deptName").offset();
	var deptNameDiv = $("#deptNameDiv")
	$("#menuContent").css({
		left : "145px",
		top : deptNameOffset.top + deptName.outerHeight() + "px",
		width : deptNameDiv.width() + "px"
	}).slideDown("fast");
	$("body").bind("mousedown", onBodyDown);
	return false;
}

function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "deptName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length > 0)) {
		hideMenu();
	}
}

$(document).ready(function() {
	$.fn.zTree.init($("#deptTree"), setting);
});

function saveOrUpdate() {
	var unflag = regBox.regName.test($("#account").val());

	if (!unflag) {
		alert("用户账号输入有误！");
		return;
	}
	var mflag = regBox.regMobile.test($("#mobile").val());
	var tflag = regBox.regTel.test($("#mobile").val());
	if ($("#mobile").val() != '' && !mflag && !tflag) {
		alert("电话号码输入有误！");
		return;
	}

	var roleIdArray = [];
	$('input[name="checkbox"]:checked').each(function() {
		roleIdArray.push($(this).val())
	});
	$("#roleIds").val(JSON.stringify(roleIdArray));

	$.ajax({
		type : "POST",
		url : "${basePath}/platform/user/saveOrUpdate",
		data : $('#formBean').serialize(),
		error : function(request) {
			alert("操作失败");
		},
		success : function(data) {
			if (data.status == "OK") {
				alert("操作成功");
				var returnurl = "/platform/user/list";
				openUrl(returnurl);
			} else {
				alert(data.message);
			}
		}
	});
}
</script>