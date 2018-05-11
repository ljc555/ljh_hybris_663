package com.mandy.b2c.storefront.forms;

/**
 * @author ljh
 * @version 1.0.0
 * @PackageName com.mandy.b2c.storefront.forms
 * @ClassName QuestionnaireForm
 * @Description 问卷调查Form
 * @Date 2018/5/2 15:50
 */
public class QuestionnaireForm {
    /** 调查Code **/
    private String code;
    /** 调查productCode **/
    private String productCode;
    /** 调查productName **/
    private String productName;
    /** 价格 **/
    private Double price;
    /** 产品厂家 **/
    private String manufactor;
    /** 信息来源 **/
    private String source;
    /** 是否购买 **/
    private Boolean ifBuy;
    /** 备注 **/
    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getManufactor() {
        return manufactor;
    }

    public void setManufactor(String manufactor) {
        this.manufactor = manufactor;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Boolean getIfBuy() {
        return ifBuy;
    }

    public void setIfBuy(Boolean ifBuy) {
        this.ifBuy = ifBuy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
