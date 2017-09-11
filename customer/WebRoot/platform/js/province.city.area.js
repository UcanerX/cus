$(function (){
	initArea('province', 'city', 'district', province_array, city_array, area_array);
});

//初始化省、市、区县相关的数据
function initArea(proTag,cityTag,areaTag,proArray,cityArray,areaArray){

    var proObj =  document.getElementById(proTag); 	//省
    var cityObj =  document.getElementById(cityTag);	//市
    var areaObj =  document.getElementById(areaTag);	//区
    
    var proIndex = proObj.value;
    var cityIndex = cityObj.value; 
    var areaIndex = areaObj.value;
    
    if (proArray != undefined) {		//省数据
    	//获取省数据索引
        if (proIndex != undefined) {
        	proIndex = parseInt(proIndex);  //省数组索引
        }
        else {
        	proIndex = 0;   
        }
      
        var i = 0;
        var optionIndex = 0;
        cleanOptions(proObj);
        for (i = 0; i < proArray.length; i++) {    //生成省下拉框选项，并设置默认值
            if (proArray[i] == undefined) {
                continue;
            }
            proObj[optionIndex] = new Option(proArray[i], i);
            if (proIndex == i) {
                proObj[optionIndex].selected = true;
            }
            optionIndex++;
        }
    }
    
    if (cityArray[proIndex] != undefined) {	 //市数据
        //获取市数据索引
        if (cityIndex != undefined) {
        	cityIndex = parseInt(cityIndex);   //市数组索引
        }
        else {
        	cityIndex = 0;
        }
    	optionIndex = 0; 
    	cleanOptions(cityObj);
        for (i = 0; i < cityArray[proIndex].length; i++) {    //生成市下拉框选项，并设置默认值
            if (cityArray[proIndex][i] == undefined) { 
            	continue; 
            }
            cityObj[optionIndex] = new Option(cityArray[proIndex][i], i);
            if (cityIndex == i) { 
            	cityObj[optionIndex].selected = true; 
            }
            optionIndex++;
        }
    }
    
    if (areaArray[cityIndex] != undefined) {		//区县数据	
        //获取区县数据索引
        if (areaIndex != undefined) {
        	areaIndex = parseInt(areaIndex);   //区县数组索引
        }
        else {
        	areaIndex = 0;
        }
    	optionIndex = 0; 
    	cleanOptions(areaObj);
        for (i = 0; i < areaArray[cityIndex].length; i++) {    //生成区县下拉框选项，并设置默认值
            if (areaArray[cityIndex][i] == undefined) { 
            	continue; 
            }
            areaObj[optionIndex] = new Option(areaArray[cityIndex][i], i);
            if (areaIndex == i) { 
            	areaObj[optionIndex].selected = true; 
            }
            optionIndex++;
        }
    }
}

//选择省下拉框change事件
function provinceChange(provinceValue,cityArray,cityTag,areaTag){
    var cityObj =  document.getElementById(cityTag);	//市
    var areaObj =  document.getElementById(areaTag);	//区
    var proIndex = parseInt(provinceValue);
    var i = 0;
    var optionIndex = 0;
    if (cityArray[proIndex] != undefined) {		//生成市下拉框选项，并设置默认值
    	cleanOptions(cityObj);
    	optionIndex = 0; 
        for (i = 0; i < cityArray[proIndex].length; i++) {
            if (cityArray[proIndex][i] == undefined) { 
            	continue; 
            }
            cityObj[optionIndex] = new Option(cityArray[proIndex][i], i);
            if (i == 0) { 
            	cityObj[optionIndex].selected = true; 
            }
            optionIndex++;
        }
    }
    cleanOptions(areaObj);
    areaObj[0] = new Option("请选择", 0); 
}

//选择省市下拉框change事件
function cityChange(cityValue,areaArray,areaTag){
    var areaObj =  document.getElementById(areaTag);	//区县
    var cityIndex = parseInt(cityValue);
    var i = 0;
    var optionIndex = 0;
    if (areaArray[cityIndex] != undefined) {		//生成区县下拉框选项，并设置默认值
    	cleanOptions(areaObj);
    	optionIndex = 0; 
        for (i = 0; i < areaArray[cityIndex].length; i++) {
            if (areaArray[cityIndex][i] == undefined) { 
            	continue; 
            }
            areaObj[optionIndex] = new Option(areaArray[cityIndex][i], i);
            if (i == 0) { 
            	areaObj[optionIndex].selected = true; 
            }
            optionIndex++;
        }
    }
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