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
package cn.ucaner.components.queue;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public interface IMessageQueue {

    public final Logger logger = LogManager.getLogger(IMessageQueue.class);


    public boolean publish (String channel,Object o);


    public void subscribe(String channel,QueueHandler queueHandler) throws Exception;


    public void put(String channel,Object o) throws InterruptedException;
}
