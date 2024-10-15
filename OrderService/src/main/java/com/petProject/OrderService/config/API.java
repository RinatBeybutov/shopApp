package com.petProject.OrderService.config;

public class API {
    private API(){};

    private static final String prefix = "/api/order-service";

    public static final String CATEGORIES_API = prefix + "/categories";
    public static final String ORDERS_API = prefix + "/orders";
    public static final String PRODUCTS_API = prefix + "/products";
}
