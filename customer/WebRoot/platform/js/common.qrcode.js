
(function(){
    window.$Y = function(){};
/*  $Y.isWeiXin = /MicroMessenger/.test(navigator.userAgent); //是否是微信
    $Y.isAlipay = /Alipay/.test(navigator.userAgent); //是否是支付宝
    $Y.isMacOS = /Mac\s+OS/.test(navigator.userAgent); //是否是支付宝
    $Y.isMiui = /Miui|XiaoMi/.test(navigator.userAgent); //是否是小米手机
*/
    //初始化
    $Y.init =function(){
        var wHeight = window.innerHeight;
        var bodyHeight = $('body').outerHeight();
        var copyrightHeight = 44;

        if(bodyHeight < wHeight){
            var h = wHeight-copyrightHeight;
              $('body').css({'min-height':h});
        }

        //下拉初始化
        $('.select_box').each(function(){
            var o = $(this);
            var index = o.find('select')[0].selectedIndex;
            var text = o.find('select option').eq(index).html();
            o.find('span.text').html(text)
        })


        //绑定全局单选
        $(document).on('click','.radio-list li',function(){
            var radio = $(this).find('.icon-radio');
            if(radio.size()>0){
              if(!$(this).hasClass('disable')){
                $(this).addClass('active').siblings()
                    $(this).siblings().each(function(){
                        var r = $(this).find('.icon-radio');
                        if(r.size()>0){
                            $(this).removeClass('active');
                        }
                    })
              }
            }

        })

        //绑定全局 是否按钮
    /*    $(document).on('click','.icon-switch',function(){
            if($(this).hasClass('icon-switch-right')){
                $(this).removeClass('icon-switch-right')
            }else{
                $(this).addClass('icon-switch-right')
            }
        })
*/
        $Y.hover();
    }

    /**
     *  loading 加载菊花
     * 例子 ：$Y.loading.show();
          $Y.loading.show('加载中');
          $Y.loading.hide();
  
     */
    $Y.loading = {
        setHTML: function (str) {
            var htmltext = '<div class="am-loading am-loading-show" id="loading_warp">'+
                '<div class="am-loading-mark"></div><div class="am-loading-text">'+
                '<span class="am-icon-loading"></span><b id="am-loading_txt" style="font-weight: normal">'+
                str+
                '</b></div></div>';
            $('body').append(htmltext);
        },
        show:function(str){
            var str = str || '加载中...';
            if($('#loading_warp').length>0){
                $('#am-loading_txt').html(str);
                $('#loading_warp').show();
            }else{
                this.setHTML(str);
            }
        },
        hide:function(){
            $('#loading_warp').hide();
            $('#am-loading_txt').html('加载中');
        }
    }


    $Y.mask =function(){};
    $Y.mask.show =function(){
        var html = '<div class="myMask" style="position: fixed;z-index: 9999;height: 100%;width: 100%;top: 0;right: 0;bottom: 0;left: 0;box-sizing: border-box;background: transparent"></div>';
        $('body').append(html);
    }
    $Y.mask.remove = function(){
        $('.myMask').remove();
    }

    /*dialog 弹窗*/

    /**
     * 例子1：  没有按钮，没有标题只有内容的弹窗
     *
var  myBox  =  new $Y.confirm({
  ok:{
  title:"确定",
  click:function(){        //参数可为空，没有默认方法,不会自动关闭窗体，可用  myBox.close()来关闭  
      alert('这是确定')  
    }
  },
  cancel:{                   
  title:"取消",
    click:function(){       //参数可为空, 当为空时默认方法关闭窗体
      alert('这是取消') 
    }
  }
  ,callback:function(){ 
      //窗体显示后的回调
      }
  )
  
// ok 和   cancel 可以单独存在（只有一个按钮），或同时存(两个按钮)

     */


    $Y.confirm = function(opts){
        if(typeof opts != "object"){
            return false;
        }
        var d = new Date().getTime();
        this.id = 'dialog'+d;

        var me  = $Y.confirm;
        var op = {};
        op.btnSize = 0;
        op.id =  'dialog'+d;
        me.prototype.init =function () {
            var that = this;
            op.title = opts.title || null;
            op.toolBtn = opts.toolBtn || null;
            op.style = opts.style || '';

            op.ok = opts.ok || {};

            if(op.ok.title){
                op.okTitle = opts.ok.title || '提示';
                op.okEvent = opts.ok.click || this.close;
                op.btnSize++
            }

            op.cancel = opts.cancel || {};
            if(op.cancel.title){
                op.cancelTitle = opts.cancel.title || '取消';
                op.cancelEvent = opts.cancel.click || this.close;
                op.btnSize++
            }

            op.content = opts.content || "";
            op.callback = opts.callback || $.noop();
            op.load = opts.load || '';
            op.vertical = opts.vertical || '';
            op.closebut = opts.closebut || '';
           
            var html ="";
            html += '<div  id="'+op.id+'" class="modal">';
            
            if(op.title){
                html+= '<h2 class="modal-h2 optTit">'+ op.title +'<!-- <a class="icon-close-click"><i class="icon icon-close middle"></i></a>--></h2>';
            }

            html+=  '<div class="dialogOptCon modal-con middle">';
            
            html+=  '<div class="modal-middle optTic">'+ op.content +'</div></div>';
            if(op.btnSize){
                html+=  '<div class="button row optBtn">';
                var btnBlock = op.btnSize<2?' btn2-block':'';
                if(op.okTitle) {
                    html += '<div class="but-save col btn2 btn2-ok'+btnBlock+'">' + op.okTitle + '</div>';
                }

                if(op.cancelTitle){
                    html+= '<div class="but-save col but-cancel btn2 btn2-cancel'+btnBlock+'">'+op.cancelTitle+'</div>';
                }
            }
            html+=   ' </div>';
            html+=   ' </div>';
            html+=  '<div class="backdrop"></div>';
            $("body").append(html);
            $(".backdrop").fadeIn(200); 

            $("#"+that.id).slideDown(400,function(){ $("#"+that.id).show(); }); 

            if(!op.vertical) {
                $("#"+that.id).find(".modal-con").removeClass("middle");
            }
            else {
                $("#"+that.id).find(".modal-con").addClass("middle");
            }
            if(op.closebut) {
                $("#"+that.id).find(".modal-h2").append('<a class="icon-close-click"><i class="icon icon-close middle"></i></a>')
            }
            else {
                //$("#modal .modal-con").addClass("middle");
            }

            setTimeout(function(){
                that.bind();
                console.log(op.style)
                that.css(op.style)
            },250)
        };
        me.prototype.bind  =function(){
            var that = this;
            var okClick = function(){
                if(typeof  op.okEvent =="function"){
                    op.okEvent(that);
                }
            };
            var cancelClick = function(){
                            
                if(typeof  op.cancelEvent =="function"){
                    op.cancelEvent(that);;
                }
                
            };
            
            var closeClick = function(){
                $("#"+that.id).slideUp(300);
                $("#"+that.id).next(".backdrop").fadeOut(700,function(){ $("#"+that.id).next(".backdrop").remove();$("#"+that.id).remove(); });       
            };
            
             //$('.icon-close-click').on('click',function(that){ cancelClick(that) })
            $("#"+that.id).find('.btn2-ok').on('click',function(that){okClick(that)})
            $("#"+that.id).find('.btn2-cancel').on('click',function(that){cancelClick(that)})
            $("#"+that.id).find('.icon-close-click').on('click',function(that){ closeClick(that) })


            if(op.load){
                that.css({transition: 'all ease 0.5s','-webkit-transition': 'all ease 0.5s','-moz-transition': 'all ease 0.5s'});
                that.content('<div class="am-loading-text" style="text-align:center;"><span class="am-icon-loading"></span><b id="am-loading_txt" style="font-weight: normal">加载中...</b></div>')
                $.ajax({
                    url:op.load,
                    dataType:'html',
                    type:'post',
                    timeout:1000*30,
                    success:function(res){
                        if(res){
                            that.content(res);
                            that.setCenter()
                        }
                    },
                    error:function(err){
                        that.content(err);
                        that.setCenter()
                    }
                })
            }
        }

        me.prototype.close =function(){
            var that = this;

            if($('.modal-con').size()<2){
               // $('#dialogOpt').remove();
                $("#"+that.id).slideUp(300);
                $("#"+that.id).next(".backdrop").fadeOut(700,function(){ $("#"+that.id).next(".backdrop").remove();$("#"+that.id).remove(); }); 

            }else{
              
                $("#"+that.id).remove();
            }
        }
        me.prototype.content =function(str){
            var that = this;
            $("#"+that.id).find('.optTic').html(str)

        }
        me.prototype.css =function(style){
            var that = this;
            if(typeof  style  == "object"){
                $("#"+that.id).css(style)
            }
        }

        me.prototype.setCenter =function(){
            var that = this;
            var _h = $("#"+that.id).height();
            var _top = (window.innerHeight - _h)/2;
            if(_top<=10){
                _top = 30;
            }
            if(_h >= window.innerHeight -10 ){
                var _bottom = 30;
                $("#"+that.id).css({"top":_top+'px',"bottom":_bottom+'px'})
                $("#"+that.id).find('.optTic').css({height:'100%', overflow: 'auto'});
            }else{
                $("#"+that.id).css({"top":_top+'px','overflow':'auto'})
            }


        }

        //初始化
        this.init();
    }

    $Y.tips =function(str,url,time){
        var t = null;
        var time = time || 110500;
        if(str){
            var html = '<div id="tips" style="display:none;">' +
                '<div class="tips_fff"></div>' +
                '<div class="tips_content">' +
                '<div class="tips_bg"></div>' +
                '<i class="icon-close" onclick="closeTips()"></i>' +
                
                '<div class="tips_content_txt">'+str+'</div></div>' +
            '</div>'
            if($('#tips').size()>0){
                $('#tips .tips_content_txt').html(str);
            }else{
                $('body').append(html)
                $("#tips").fadeIn(300); 
            }
            setTimeout(function(){
                $("#tips").fadeOut(300,function(){ $("#tips").remove(); }); 
            },time)
        }
        if(url){
           setTimeout(function(){
                window.location = url
            },time)
        }

    }

    /**
     * 名称：增加点击交互效果
     * */
    $Y.hover = function(){
        var me = $Y.hover;
        var O = null,t=null;
        me.init =function(){
            $('.touch,.btn').attr({
                'ontouchstart':'$Y.hover.TouchOn(this)',
                'ontouchend':'$Y.hover.TouchOut(this)'
            })
        }
        me.TouchOn =function(obj){
            O = $(obj);

        }
        me.TouchOut =function(obj){
            clearTimeout(t);
            if(!O) return;
            $(obj).addClass('hover');
            t =setTimeout(function(){
                $(obj).removeClass('hover');
            },200);
            O=null
        }
        me.TouchMove =function(e){
            var y = e.touches[0].pageY;
            if(!O) return;
            O.removeClass('hover');
            O=null;
            clearTimeout(t);
        }
        $('body').attr({'ontouchmove':'$Y.hover.TouchMove(event)'});
        //$('body').attr({'ontouchmove':'$Y.hover.TouchMove(event)','ontouchstart':'$Y.pushLoad.touchstart(event)','ontouchend':'$Y.pushLoad.touchend(event)'} );
        setTimeout(function(){
            $Y.hover.init();
        },200)
    }

}(window.jQuery))
function closeTips(){
    $("#tips").fadeOut(300,function(){ $("#tips").remove(); }); 
}


