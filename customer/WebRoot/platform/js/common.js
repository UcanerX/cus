// 导航展缩
/*
$(document).on('click', '.sidebar-height > ul>li>a', function() {
	var num = $(this).parent("li").find('ul').length;
	if(!$(this).hasClass("active")) {
		$(".sidebar-height li ul").slideUp();
		$(".sidebar-height > ul>li>a,.sidebar-height ul ul>li>a").removeClass('active').removeClass('rotateRight');
		$(this).addClass("active")
		if(num > 0) {
		   $(this).parent("li").find("ul").slideDown(200);
		}else {
		   $(this).addClass('active rotateRight');
		}
	}
});
*/

// 选中二级
$(document).on('click', '.sidebar-height ul ul li > a', function() {
    if(!$(this).hasClass('active')) {
    	 $(this).addClass('active').parent('li').siblings().find('a').removeClass('active')
    }
});

// 选中二级
$(document).on('click', '.radio-inline', function() {
    if(!$(this).hasClass('on')) {
    	$(this).addClass("on").siblings().removeClass('on').find("input[type='radio']").attr("checked",true);  
    }
});



(function($) {
	/**
	 * 分页插件
	 * 
	 * @param type
	 *            请求类型,get或者post,默认post
	 * @param url
	 *            请求地址
	 * @param successCallback
	 *            请求成功后的回调函数,用于自己实现分页数据列表展示或其他处理
	 * @param errorCallback
	 *            请求失败后的回调函数,用于错误提示或其他处理
	 * @param dataType
	 *            数据类型,默认json
	 * @params params 请求参数,不需要携带分页参数,只需设定你的查询参数即可
	 */
	$.fn.pagination = function(options) {
		var settings = {
			type : "post",
			url : "",
			successCallback : {},
			errorCallback : function() {
				alert("系统内部错误,请联系管理员。");
			},
			dataType : "json",
			params : {}
		};
		var obj = $(this);
		settings = $.extend(settings, options || {}); // 合并默认和用户配置
		var pageInfo = {
			"pageNum" : $('input[name="pageNum"]').val(),
			"pageSize" : isNull($('input[name="pageSize"]').val())?15:$('input[name="pageSize"]').val()
		};
		var params = $.extend({}, pageInfo, options.params);
		// 请求分页数据
		$.ajax({
			type : settings.type,
			url : settings.url,
			data : params,
			dataType : settings.dataType,
			success : function(data) {
				// 请求成功后调用成功回调函数
				settings.successCallback(data);
				// 设置分页信息
				pagingInfo(obj, data);
				// 解除事件绑定
				$(".pagingInfo").unbind("click");
				$(".goto.pagingInfoGoto").unbind("click");
				$(".prePagingInfo").unbind("click");
				$(".nextPagingInfo").unbind("click");
				$(".pagingInfo").unbind("click");
				// 为页码上的超链接绑定点击事件
				$(".pagingInfo").bind("click", function() {
					// 获取点击的页码值
					var pageNum = $(this).text();
					// 校正页码
					changePage(pageNum);
					// 调用分页插件以响应点击事件
					obj.pagination(options);

				});
				// 为跳转超链接绑定点击事件
				$(".goto.pagingInfoGoto").bind("click", function() {
					// 获取输入的页码值
					var pageNum = $('input[name="gotoInput"]').val();
					// 校正页码
					changePage(pageNum);
					// 调用分页插件以响应点击事件
					obj.pagination(options);
				});
				// 为上一页,下一页绑定点击事件
				$(".prePagingInfo").bind("click", function() {
					// 获取上一页页码值
					var pageNum = $('input[name="prePage"]').val();

					// 校正页码
					changePage(pageNum);
					// 调用分页插件以响应点击事件
					obj.pagination(options);

				});
				$(".nextPagingInfo").bind("click", function() {
					// 获取下一页页码值
					var pageNum = $('input[name="nextPage"]').val();
					// 校正页码
					changePage(pageNum);
					// 调用分页插件以响应点击事件
					obj.pagination(options);
				});
			},
			error : function(XmlHttpRequest, textStatus, errorThrown) {
				settings.errorCallback();
			}
		});
	};
	// 分页信息
	pagingInfo = function(obj, data) {
		var root = obj;
		root.empty();
		var ul = "<div class='pagination-recording'>共有 <span class='colorblue'>" + data.total + "</span> 条记录   <span class='qrnum'>" + data.pages + "</span>页</div>";
		ul += "<ul>";
		if (data.prePage == 0) {
			ul += "<li class='disabled'>上一页</li>";
		} else {
			ul += "<li><a class='prePagingInfo' href='javascript:void(0);'>上一页</a></li>";
		}

		if (data.pages != 0) {
			// 控制页码显示数量
			var pageNum = 6;// 显示多少个页码
			var pageStart, pageEnd;
			if (data.pageNum <= pageNum / 2 + 1) {
				pageStart = 1;
				pageEnd = pageNum;
				if (pageEnd > data.pages) {
					pageEnd = data.pages;
				}
			} else if (data.pageNum > pageNum / 2 + 1) {
				pageStart = data.pageNum - pageNum / 2;
				pageEnd = data.pageNum + pageNum / 2 - 1;
				if (pageEnd > data.pages) {
					pageEnd = data.pages;
				}
				// 向左补页码
				if ((pageEnd - pageStart) + 1 < pageNum) {
					pageStart = pageStart - (pageNum - (pageEnd - pageStart + 1));
					if (pageStart < 1) {
						pageStart = 1;
					}
				}
			}
			for (var i = pageStart; i <= pageEnd; i++) {
				if (i == data.pageNum) {
					ul += "<li class='active'><span>" + i + "</span></li>";
				} else {
					ul += "<li><a class='pagingInfo' href='javascript:void(0);' >" + i + "</a></li>";
				}
			}
		} else {
			ul += "<li class='active'><span>1</span></li>";
		}
		if (data.nextPage == 0) {
			ul += "<li class='disabled'>下一页</li>";
		} else {
			ul += "<li><a class='nextPagingInfo' href='javascript:void(0);'>下一页</a></li>";
		}

		ul += "<div class='pageGoto'>";
		ul += "<span>转到第</span> <input type='text' name='gotoInput' class='goto_input'/><span>页</span>";
		ul += "<a href='javascript:void(0);' class='goto pagingInfoGoto'>跳转</a>";
		ul += "</div>";
		ul += "<input type='hidden' name='prePage' value='" + data.prePage + "'/>";
		ul += "<input type='hidden' name='nextPage' value='" + data.nextPage + "'/>";
		ul += "<input type='hidden' name='pageNum' value='" + data.pageNum + "'/>";
		ul += "<input type='hidden' name='pageSize' value='" + data.pageSize + "'/>";
		ul += "<input type='hidden' name='pages' value='" + data.pages + "'/>";
		ul += "<input type='hidden' name='listSize' value='" + data.list.length + "'/>";
		ul += "</ul>";
		root.append(ul);
	};
	// 校正页码
	changePage = function(pageNum) {
		if (pageNum) {
			var pages = $('input[name="pages"]').val();
			var pageNumInput = $('input[name="pageNum"]');

			// 如果输入的页数是非数字，则还是跳到当前页
			if (isNaN(pageNum)) {
				pageNum = pageNumInput.val();
			}

			pages = Number(pages);
			pageNum = Number(pageNum);

			// 如果页数大于总页数，则跳至最后一页，如页数小于最小页数，则跳至第一页
			if (pageNum >= pages) {
				pageNum = pages;
			} else if (pageNum <= 1) {
				pageNum = 1;
			}
			pageNumInput.val(pageNum);
		}
	}

})(jQuery);

// 表单赋值
$.fn.formEdit = function(data) {
	return this.each(function() {
		var elementDom;
		var elementDomName;
		if (data == null) {
			this.reset();
			return;
		}
		for (var i = 0; i < this.length; i++) {
			elementDom = this.elements[i];
			elementDomName = elementDom.name;
			if (data[elementDomName] == undefined) {
				continue;
			}
			elementDom.value = data[elementDomName];
		}
	});
};

function isNull(str) {
	var flag = false;
	if (str === window.undefined || str == null || str == "" || str.length == 0 || str == "null") {
		flag = true;
	}
	return flag;
}

(function($) {
	$.dateFormat = function(format, obj) {
		if (isNull(obj)) {
			obj = new Date();
		}
		var date = {
			"M+" : obj.getMonth() + 1,
			"d+" : obj.getDate(),
			"H+" : obj.getHours(),
			"m+" : obj.getMinutes(),
			"s+" : obj.getSeconds(),
			"q+" : Math.floor((obj.getMonth() + 3) / 3),
			"S+" : obj.getMilliseconds()
		};
		if (/(y+)/i.test(format)) {
			format = format.replace(RegExp.$1, (obj.getFullYear() + '').substr(4 - RegExp.$1.length));
		}
		for ( var k in date) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
			}
		}
		return format;
	}
})(jQuery);

var regBox = {
	regEmail : /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/,// 邮箱
	regName : /^[a-zA-Z0-9_-]{3,16}$/,// 用户名
	regMobile : /^0?1[3|4|5|7|8][0-9]\d{8}$/,// 手机
	regTel : /^0[\d]{2,3}-[\d]{7,8}$/,
	regRealName : /^[\u4e00-\u9fa5a-zA-Z0-9]{2,8}$/,
	regCode:/^[a-zA-Z:]{2,20}$/
}
function selectAll() {
	var checklist = document.getElementsByName("check");
	if (document.getElementById("checkAll").checked) {
		for (var i = 0; i < checklist.length; i++) {
			checklist[i].checked = 1;
		}
	} else {
		for (var j = 0; j < checklist.length; j++) {
			checklist[j].checked = 0;
		}
	}
}

function openUrl(url) {
	$.ajax({
		url : basePath + url,
		type : 'POST',
		error : function(request) {
			alert("跳转失败！");
		},
		success : function(data) {
			$("#content").html(data);
		}
	});
}

function openEnCodeUrl(url, urlEnCode) {
	url = url + encodeURI(encodeURI(urlEnCode));
	openUrl(url);
}

function validateNum(numStr) {
	var reg = new RegExp("^[0-9]*$");
	if (!reg.test(numStr)) {
		return false;
	}
	if (!/^[0-9]*$/.test(numStr)) {
		return false;
	}
	return true;
}

//禁止Enter键
function ifenter(){ 
	if(event.keyCode==13){
	   return   false;
	}
} 

window.$Y = function() {
};

// 初始化
$Y.init = function() {
	var wHeight = window.innerHeight;
	var bodyHeight = $('body').outerHeight();
	var copyrightHeight = 44;

	if (bodyHeight < wHeight) {
		var h = wHeight - copyrightHeight;
		$('body').css({
			'min-height' : h
		});
	}
}
/**
 * loading 加载菊花 例子 ：$Y.loading.show(); $Y.loading.show('加载中'); $Y.loading.hide();
 * 
 */
$Y.loading = {
	setHTML : function(str) {
		var htmltext = '<div class="am-loading am-loading-show" id="loading_warp">' + '<div class="am-loading-mark"></div><div class="am-loading-text">' + '<span class="am-icon-loading"></span><b id="am-loading_txt" style="font-weight: normal">' + str + '</b></div></div>';
		$('body').append(htmltext);
	},
	show : function(str) {
		var str = str || '加载中...';
		if ($('#loading_warp').length > 0) {
			$('#am-loading_txt').html(str);
			$('#loading_warp').show();
		} else {
			this.setHTML(str);
		}
	},
	hide : function() {
		$('#loading_warp').hide();
		$('#am-loading_txt').html('加载中');
	}
}

/* 选中列 */
$(document).on('click', '.table-select tr', function() {
	var ps = $(this);
		if (ps.hasClass('select-check')) {
			setTimeout(function(){
				ps.removeClass('select-check');
				ps.find("input").prop('checked',false);
			},500)
		} else {
			$(".table-select tr").removeClass('select-check');
			$(".table-select tr").find("input").prop('checked',false);
			ps.addClass('select-check');
			ps.find("input").prop('checked',true);
		}
	
});



/* 2016-5-14 add */
(function() {
    window.$Y = function() {
    };

    $Y.init = function() {

    }

    $Y.loading = {
        setHTML : function(str) {
            var htmltext = '<div class="am-loading am-loading-show" id="loading_warp">' + '<div class="am-loading-mark"></div><div class="am-loading-text">' + '<span class="am-icon-loading"></span><b id="am-loading_txt" style="font-weight: normal">' + str + '</b></div></div>';
            $('body').append(htmltext);
        },
        show : function(str) {
            var str = str || '加载中...';
            if ($('#loading_warp').length > 0) {
                $('#am-loading_txt').html(str);
                $('#loading_warp').show();
            } else {
                this.setHTML(str);
            }
        },
        hide : function() {
            $('#loading_warp').hide();
            $('#am-loading_txt').html('加载中');
        }
    }

    $Y.tips = function(str, url, time) {
        var t = null;
        var time = time || 1000;
        if (str) {
            var html = '<div id="tips">' + '<div class="tips_content">' + '<div class="tips_bg"></div>' + '<div class="tips_content_txt"  style="display:none;">' + str + '</div></div>' + '</div>'
            if ($('#tips').size() > 0) {
                $('#tips .tips_content_txt').html(str);
            } else {
                $('body').append(html)
                $(".tips_content_txt").slideDown(100);
            }
            setTimeout(function() {
                $(".tips_content_txt").slideUp(100, function() {
                    $("#tips").remove();
                });
            }, time)
        }
        if (url) {
            setTimeout(function() {
                window.location = url
            }, time)
        }

    }
    
}(window.jQuery))
$(function() {
    $Y.init();
})
