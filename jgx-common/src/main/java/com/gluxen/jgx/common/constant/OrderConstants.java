package com.gluxen.jgx.common.constant;

public class OrderConstants {

	public static final int WAIT_TO_PAY = 101;//待付款
	
	public static final int WAIT_TO_CONFIRM = 200;//待确认
	
	public static final int WAIT_TO_SEND = 201;//待发货
	
	public static final int WAIT_TO_RECIEVE = 202;//待收货
	
	public static final int HAVE_CONFIRM = 203;//已确认
	
	public static final int HAVE_RECIEVE = 303;//已收货
	
	public static final int HAVE_IN_BAG = 301;//转入背包
	
	public static final int HAVE_USE = 302;//消费使用


	public static final int HAVE_EVA = 304;//已评价
	
	public static final int CANCEL_BY_SELLER = 401;//取消订单（商家）
	
	public static final int CANCEL_BY_BUYER = 402;//取消订单（买家）
	
	public static final int CANCEL_BY_ADMIN = 403;//取消订单（管理员）
	
	public static final int CANCEL_BY_SYS = 404;//取消订单（系统）
	
	public static final String CREATE_ORDER = "create";//创建订单
	
	public static final String PAY = "pay";//支付
	
	public static final String CONFIRM = "confirm";//确认订单
	
	public static final String SEND = "send";//发货
	
	public static final String RECIEVE = "recieve";//收货
	
	public static final String TO_BAG = "toBag";//转入背包
	
	public static final String USE = "use";//消费使用
	
	public static final String CANCEL_ORDER = "cancel";//取消订单
	
	public static final String APPRAISE = "appraise";//评价
}

