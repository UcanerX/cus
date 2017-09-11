$(function() {
//	 $.ajax({
//     	 url: basePath + '/platform/role/findResourceByParentCode',
//     	 data:'code=jsgl',
//         type: 'POST',
//         success: function (data) {
//              $(".operation-button").html(data);
//         }
//     });
	// search();
	var searchValue = $("#searchValue").val();
	$(".pagination.pagination-right.pagination-style").pagination({
		url : basePath + "/platform/role/findListByPage",
		params : {
			"params['searchValue']" : searchValue
		},
		successCallback : function(data) {
			var k = 0;
			var tbody = $(".list-data");
			var html="";
			// 清空
			tbody.empty();
			var ul = "<ul>";
			$(data.list).each(function(i, n) {
				k++;
				var d = new Date(n.ct);    //根据时间戳生成的时间对象
				var date = (d.getFullYear()) + "-" + 
				           (d.getMonth() + 1) + "-" +
				           (d.getDate()) + " " + 
				           (d.getHours()) + ":" + 
				           (d.getMinutes()) + ":" + 
				           (d.getSeconds());
				html+='<tr>';
				html+='<td>' + "<input type='checkbox' name='check' value='"+n.id+"'>" + "</td>";
				html+='<td>' + k + "</td>";
				html+='<td>' + n.roleCode + "</td>";
				html+='<td>' + n.roleName + "</td>";
				html+='<td>' + date + "</td>";
				html+='</tr>';
			});
			tbody.append(html);
		}
	});
});

/**
 * 搜索
 */

$("#search").click(function(){  
	var searchValue = $("#searchValue").val();
	$(".pagination.pagination-right.pagination-style").pagination({
		url : basePath + "/platform/role/findListByPage",
		params : {
			"params['searchValue']" : searchValue
		},
		successCallback : function(data) {
			var k = 0;
			var tbody = $(".list-data");
			var html="";
			// 清空
			tbody.empty();
			var ul = "<ul>";
			$(data.list).each(function(i, n) {
				k++;
				var d = new Date(n.ct);    //根据时间戳生成的时间对象
				var date = (d.getFullYear()) + "-" + 
				           (d.getMonth() + 1) + "-" +
				           (d.getDate()) + " " + 
				           (d.getHours()) + ":" + 
				           (d.getMinutes()) + ":" + 
				           (d.getSeconds());
				html+='<tr>';
				html+='<td>' + "<input type='checkbox' name='check' value='"+n.id+"'>" + "</td>";
				html+='<td>' + k + "</td>";
				html+='<td>' + n.roleCode + "</td>";
				html+='<td>' + n.roleCode + "</td>";
				html+='<td>' + date + "</td>";
				html+='</tr>';
			});
			tbody.append(html);
		}
	});  
});  

function toDelete(){
	var i = 0;
    var ids = ""; 
    $("input[name='check']:checked").each(function(){
       ids+=$(this).val()+",";
       i++;
    })
    if(i == 0){
      alert("请选择选择一条记录");
      return;
    }
    ids=ids.substring(0,ids.length-1);
	if(confirm("确定删除"))
	 {
		$.ajax({
			url : basePath + "/platform/role/toDelete?id="+ids,
	        type: 'POST',
	        error: function(request) {
	            alert("操作失败");
	         },
	        success: function (data) {
	                 var returnurl = '/platform/role/list'
	                 alert(data.message);
	                 openUrl(returnurl);
	        }
	    });
	 }
	
}

function toAdd(){
	$.ajax({
		url : basePath + "/platform/role/toAdd",
        type: 'POST',
        error: function(request) {
            alert("操作失败");
         },
        success: function (data) {
           $("#content").html(data);
        }
    });
}

function toEdit(){
	   var i = 0;
       var ids = ""; 
       $("input[name='check']:checked").each(function(){
          ids+=$(this).val()+",";
          i++;
       })
       if(i>1){
         alert("只能选择一条记录");
         return;
       }
       if(i == 0){
         alert("请选择选择一条记录");
         return;
       }
       ids=ids.substring(0,ids.length-1);
       $.ajax({
     	 url: basePath + "/platform/role/toEdit?id="+ids,
         type: 'POST',
         error: function(request) {
            alert("操作失败");
         },
         success: function (data) {
              $("#content").html(data);
         }
     });
}


