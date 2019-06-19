package com.zm.v13.pojo;

import com.zm.v13.entity.TProduct;
import com.zm.v13.entity.TProductDesc;

import java.io.Serializable;

public class TProductVO implements Serializable {
    private TProduct product;
    private String productDesc;

    public TProductVO() {
    }

    public TProductVO(TProduct product, String productDesc) {
        this.product = product;
        this.productDesc = productDesc;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Override
    public String toString() {
        return "TProductVO{" +
                "product=" + product +
                ", productDesc='" + productDesc + '\'' +
                '}';
    }
}
