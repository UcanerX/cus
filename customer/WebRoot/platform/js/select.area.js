/**
 * 2016年5月10日 14:52:17 党参更改provinceChange函数 value值html标签对象
 * @param obj
 * @param cityTag
 * @param areaTag
 */
//选择省下拉框change事件
function provinceChange(obj,cityTag,areaTag){
    var cityObj =  document.getElementById(cityTag);	//市
    var areaObj =  document.getElementById(areaTag);	//区
    var provinceValue = $(obj).find("option:selected").val();
    var provinceName = $(obj).find("option:selected").text();
    $("#provinceName").val(provinceName);
    var datas = {"provinceCode":provinceValue,"cityCode":""};
	 $.ajax({
     	 url: basePath + '/platform/hospital/getCityItems',
     	 data:datas,
         type: 'POST',
         success: function (data) {
             if(data != undefined &&  data.length > 0)
         	{
            	cleanOptions(cityObj);
             	//默认选中第0项
            	cityObj[0] = new Option("请选择城市", "");
            	cityObj[0].selected = true;  
            	var index = 1;
             	for(var i=0; i < data.length; i++){
             		cityObj[index] = new Option(data[i].text, data[i].value);
             		index++;
             	}
         	}else{
         		 cleanOptions(cityObj);
         	  	//默认选中第0项
         	 	cityObj[0] = new Option("请选择城市", "");
         	 	var cityName = $(cityTag).find("option:selected").text();
         	    $("#cityName").val(cityName);
         	    
         	    cleanOptions(areaObj);
         	    areaObj[0] = new Option("请选择区县", ""); 
         	   $("#districtName").val($(areaObj).find("option:selected").text());
         	}
         }
     });
	
}
/**
 *  2016年5月10日 14:52:17 党参更改cityChange函数 value值html标签对象
 * @param obj
 * @param areaTag
 */
//选择省市下拉框change事件
function cityChange(obj,areaTag){
    var areaObj =  document.getElementById(areaTag);	//区县
    var cityValue = $(obj).find("option:selected").val();
    var cityName = $(obj).find("option:selected").text();
    $("#cityName").val(cityName);
    var datas = {"cityCode":cityValue,"areaCode":""};
	 $.ajax({
    	 url: basePath + '/platform/hospital/getAreaItems',
    	 data:datas,
        type: 'POST',
        success: function (data) {  	
        	if(data == null || data == ''){
        		cleanOptions(areaObj);
        		areaObj[0] = new Option("请选择区县", ""); 
        		areaObj[0].selected = true;  
        		$("#districtName").val($(areaObj).find("option:selected").text());
        	}
            if(data != undefined &&  data.length > 0)
         	{
            	cleanOptions(areaObj);
             	//默认选中第0项
            	areaObj[0] = new Option("请选择区县", ""); 
            	areaObj[0].selected = true;   
            	var index = 1;
             	for(var i=0; i < data.length; i++){
             		areaObj[index] = new Option(data[i].text, data[i].value);
             		index++;
             	}
         	}
        }
    });
}

/**
 * 2016年5月10日 14:52:17 党参增加select标签onChange事件函数
 * 用于设置districtName的值
 * @param obj
 */
function districtChange(obj){
	$("#districtName").val($(obj).find("option:selected").text());
}

//清理下拉框的option选项
function cleanOptions(obj){
    if ((obj != undefined) && (obj.options != undefined)) {
        var len = obj.options.length;
        for (var i = 0; i < len; i++) {
        	obj.options[0] = null;
        }
    }
}