/**
 * <html>
 * <body>
 *  <P> Copyright 1994 JsonInternational</p>
 *  <p> All rights reserved.</p>
 *  <p> Created on 19941115</p>
 *  <p> Created by Jason</p>
 *  </body>
 * </html>
 */
package cn.ucaner.pattern.action.chainOfResponsibility.abs;

/**
* @Package：cn.ucaner.pattern.action.chainOfResponsibility.abs   
* @ClassName：Handler   
* @Description：   <p> 责任链模式 </p>
* @Author： - DaoDou 
* @CreatTime：2017年10月26日 下午1:39:52   
* @Modify By：   
* @ModifyTime：  
* @Modify marker：   
* @version    V1.0
 */
public abstract class Handler {
	
    //下一个执行者
    private Handler nextHandler;


    //当前执行者对请求做处理
    public final boolean handlerRequst(int  handLeve){
        boolean bReq=false;
        //判断自己是否可以审核
        if(getLevel()>=handLeve){
            System.out.println(getnName()+" :可以审批");
            return true;
        }else {
            if(this.nextHandler!=null){
                System.out.println(getnName()+"向"+nextHandler.getnName()+"递交请求");
                //向上一级请求
                bReq=nextHandler.handlerRequst(handLeve);
                //根据上级的答复做出回应
                if(bReq){
                    System.out.println(getnName()+" :"+nextHandler.getnName()+"说可以审批");
                }else {
                    System.out.println(getnName()+" :"+nextHandler.getnName()+"说不可以审批");
                }
            }else {
                //到这边已经没有下一个处理者了
                System.out.println(getnName()+" :金额太大了");
            }
        }
        return bReq;
    }


    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
    //可以审批的金额
    public abstract int getLevel();
    public abstract String getnName();

}
