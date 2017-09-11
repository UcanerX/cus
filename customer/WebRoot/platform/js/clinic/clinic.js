$(function() {
	search();
});

function openAssociateDoctor(){
   $('#associateDoctorModal').modal()
}

function changeListstatus(obj){
   var parentClass = $(obj).closest('.panel-list');
   if(parentClass.hasClass('panel-disabled')){
       parentClass.removeClass('panel-disabled');
       $(obj).html("启用");
       $Y.tips("启用成功!");
   }else {
       parentClass.addClass('panel-disabled');
       $(obj).html("停用");
       $Y.tips("停用成功!");
   }
}

function clearVal(){
	$("#searchValue").val("");
}

function search(){
	var map={};
	map["params['searchValue']"]=$("#searchValue").val();
	$(".pagination.pagination-right.pagination-style").pagination({
		url :basePath + "/platform/clinic/findListByPage",
		params : map,
		successCallback : function(data) {
			var htmls='';
			// 清空
			$("#myClinics").empty();
			$(data.list).each(function(i, n) {
				var html='';
				var ares=n.provinceName+n.cityName+n.districtName;
				var status=n.clinicStatus==0?'停用':'启用';
				html += '<div class="col-sm-6 col-md-4 col-lg-3">';
				html += '<div class="panel panel-list">';
				html += '<div class="panel-top">';
				html += '<div class="row"><div class="col-sm-4">';
				html += '<a href="javascript:void(0)"><img src="" class="img-sm img-border img-radius"></a>';
				html += '</div><div class="col-sm-8 pl0">';
				html += '<h3><a href="" class="name-nowrap">'+n.clinicName+'</a></h3>';
				html += '<span class="text-nowrap text-overflow" title="'+ares+'">'+ares+'</span>';
				html += '<span><b class="status-text" onclick="changeListstatus(this)">'+status+'</b></span>';
				html += '</div></div></div><div class="panel-bottom text-center">';
				html += '<div class="row"><div class="col-list col-sm-6">';
				html += '<a  href="javascript:void(0)" onclick="openAssociateDoctor();" class="panel-link-a">关联医生</a>';
				html += '</div><div class="col-list col-sm-6">';
				html += '<a href="javascript:void(0)" class="panel-link-a">查看资料</a>'; 
				html += '</div></div></div></div></div>';
				htmls += html;
			});
			$("#myClinics").append(htmls);
			
		}
	});
}
