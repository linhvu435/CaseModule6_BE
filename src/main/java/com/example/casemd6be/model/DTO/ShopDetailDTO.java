package com.example.casemd6be.model.DTO;

import com.example.casemd6be.model.Product;
import com.example.casemd6be.model.ShopAddress;

import java.util.List;

public class ShopDetailDTO implements Comparable<ShopDetailDTO>{

    private long id;

    private long totalproductsell;
    private String name;
    private ShopAddress shopAddress;

    private List<Product> products;

    public ShopDetailDTO(long id, long totalproductsell, String name, ShopAddress shopAddress, List<Product> products) {
        this.id = id;
        this.totalproductsell = totalproductsell;
        this.name = name;
        this.shopAddress = shopAddress;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTotalproductsell() {
        return totalproductsell;
    }

    public void setTotalproductsell(long totalproductsell) {
        this.totalproductsell = totalproductsell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ShopAddress getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(ShopAddress shopAddress) {
        this.shopAddress = shopAddress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public int compareTo(ShopDetailDTO compareAmountSell ) {
        long amountsell = ((ShopDetailDTO) compareAmountSell).getTotalproductsell();
        return (int) (amountsell - this.totalproductsell);
    }
}
