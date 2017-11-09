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
package cn.ucaner.pattern.create.builder;

/**
 * x战警通用属性
 * @author Chayne_Shen 2017/3/10 0010.
 */

public class XMan {
    private String xFactor;
    private String lover;

    public String getxFactor() {
        return xFactor;
    }

    public XMan setxFactor(String xFactor) {
        this.xFactor = xFactor;
        return this;
    }

    public String getLover() {
        return lover;
    }

    public XMan setLover(String lover) {
        this.lover = lover;
        return this;
    }
}
