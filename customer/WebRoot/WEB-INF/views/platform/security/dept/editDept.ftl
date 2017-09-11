	<link rel="stylesheet" href="${basePath}/platform/css/tree/metroStyle.css" type="text/css">
	<script type="text/javascript" src="${basePath}/platform/js/tree/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${basePath}/platform/js/tree/jquery.ztree.excheck.min.js"></script>
    <div id="content-header">
        <div class="widget-title radius-3"><h3 class="title">修改角色</h3></div>
    </div>
    <div class="container-fluid">
         <div class="row-fluid">
            <div class="widget-content radius-3">
                <!--内容-->
                <form id="deptForm" class="form-horizontal">  
                    <input type="hidden" id="id" name="id" value="${dept.id}" /> 
                    <input type="hidden" id="oldName" name="oldName" value="${dept.name}" /> 
                    <input type="hidden" id="parentId" name="parentId" value="${dept.parentId}" /> 
                    <input type="hidden" id="remark" name="remark" value="${dept.remark}" />
                    <div class="control-group">
                         <label class="control-label" ><span class="mandatory">*</span>部门名称</label>
                         <div class="controls"><input type="text" id="name" name="name" value="${dept.name}" class="span12" /></div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" ><span class="mandatory">*</span>部门编号</label>
                         <div class="controls"><input type="text" id="code" name="code" value="${dept.code}" class="span12" placeholder="3-16个字母或数字"/></div>
                    </div>
                    <div class="control-group">
                        <label class="control-label">上级部门</label>
                        <div class="controls" id="parentNameDiv">
                             <input type="text" id="parentName" name="parentName" value="${dept.parentName}" class="span11" readonly onClick="showDeptTree()" />
                          	 <button class="btn btn-save" onClick="showDeptTree();return false;" style="width:90px">选择</button>
                        </div>
                    </div>
                    <div class="control-group">
                         <label class="control-label" >排序号</label>
                         <div class="controls"><input type="number" step="1" id="sortNum" name="sortNum" value="${dept.sortNum}" class="span12"/></div>
                    </div>
                    <div class="control-group">
                     <label class="control-label" >部门备注</label>
                     <div class="controls">
                     <textarea style="height:150px;" placeholder="点击输入备注 内容" 
                           class="span12" name="remark_temp" id="remark_temp" value="${dept.remark}"></textarea>
                     </div>
                    </div>
                </form>
                <!--内容 end-->
            </div>
         </div>
         <div class="row-fluid mt10">
            <div class="button-style bgfff radius-3"><button class="btn btn-save" onClick="saveOrUpdate()">保存</button><button class="btn btn-remove" onClick="openUrl('platform/dept/list')">取消</button></div>
         </div>
    </div>
    <div id="menuContent"  style="display:none; position: absolute;background-color:#ffffff;overflow-y:auto;overflow-x:hidden;border:1px solid;">
		<ul id="deptTree" class="ztree" style="margin-top:0; width:100%; height: 100px;"></ul>
	</div>
    <script>    
        $(function() {
			$("textarea").each(function() {
				var obj = $("#" + this.id.substring(0, this.id.indexOf("_temp")));
				if (obj.length > 0) {
					var val = $(obj).val().replace(/<br>/g, "\n");
					$(this).val(val);
				}
			});
		});
        
		function saveOrUpdate() {
			var rcflag = regBox.regName.test($("#code").val());
			if (!rcflag) {
				alert("部门编码输入有误");
				return;
			}
			if (isNull($("#name").val())) {
				alert("部门名称不能为空");
				return;
			}
		    $("#deptForm").find("textarea").each(function() {
				var content = $(this).val().replace(/\n/g, "<br>"); 
				$("#" + this.id.substring(0, this.id.indexOf("_temp"))).val(content);
			});
			
			var parentId = $("#parent").val();
			if(!isNull(parentId)){
				$("#parentId").val(parentId);
				var parentName = $("#parent").find("option:selected").text();
				$("#parentName").val(parentName);
			}
			$.ajax({
				type : "POST",
				url : "${basePath}/platform/dept/saveOrUpdate",
				data : $('#deptForm').serialize(),
				error : function(request) {
					alert("操作失败");
				},
				success : function(data) {
					alert(data.result.msg);
					if (data.result.isSuccess) {
						openUrl("/platform/dept/list");
					}
				}
			});
		}
		
		var setting = {
			async: {
				enable: true,
				url: basePath + "/platform/common/loadDepts",
				autoParam:["id=pId", "name", "level"],
				otherParam:{"loadType":"1"},
				dataFilter: null
			},
			check: {
				enable: true,
				chkStyle: "radio",
				radioType: "all"
			},
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onClick: onClick,
				onCheck: onCheck
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
			for (var i=0, l=nodes.length; i<l; i++) {
				name += nodes[i].name + ",";
				value += nodes[i].id + ",";
			}
			if (name.length > 0 ) name = name.substring(0, name.length-1);
			if (value.length > 0 ) value = value.substring(0, value.length-1);
			var parentName = $("#parentName");
			parentName.attr("value", name);
			var parentId = $("#parentId");
			parentId.attr("value", value);
		}

		function showDeptTree() {
			var parentName = $("#parentName");
			var parentNameOffset = $("#parentName").offset();
			var parentNameDiv = $("#parentNameDiv")
			$("#menuContent").css({left:"145px", top:parentNameOffset.top + parentName.outerHeight() + "px",width:parentNameDiv.width() + "px"}).slideDown("fast");
			$("body").bind("mousedown", onBodyDown);
			return false;
		}
		
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "parentName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#deptTree"), setting);
		});
	</script>
    
    
