package com.itheima.reggie_takeout.dao.dto;

import com.itheima.reggie_takeout.dao.entity.OrderDetail;
import com.itheima.reggie_takeout.dao.entity.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;
	
}
