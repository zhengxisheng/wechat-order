package com.xisheng.enums;

import lombok.Getter;

/**
 * Created by fanlinglong on 2018/3/23.
 */
@Getter
public enum ResultEnum {

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRODUCT_STOCK_ERROR(11,"商品库存不正确"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_FAIL(15, "订单更新失败"),

    ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为空"),

    ORDER_OWNER_ERROR(19, "该订单不属于当前用户"),

    WECHAT_MP_ERROR(20, "微信公众账号方面错误"),

    WXPAY_NOTIFY_MONEY_VERIFY_ERROR(21,"微信异步通知异常"),

    ORDER_CANCEL_SUCCESS(22,"取消订单成功"),

    ORDER_FINISH_SUCCESS(23,"完结订单成功"),

    PRODUCT_STATUS_ERROR(24,"商品状态不正确"),

    Seller_NAME_PASSWORD_NOT_EMPTY(25,"用户名或密码不能为空"),

    Seller_NAME_NOT_EXIT(26,"用户名不存在"),

    Seller_PASSWORD_ERROR(27,"用户密码不对"),

    LOGOUT_SUCCESS(28,"注销成功"),

    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
