package com.hzj.ribbon.consumer.ribbonconsumer.enums;

/*
 * @author <a>huangzijian</a>
 * @version 1.0, 2019-04-23
 * @description 
 */
public enum StatusEnumUntil {

    ENABLE(0,"启用"),
    FORBIDDEN(1,"禁用"),
    False(400,"失败"),
    SUCCESS(200,"成功");

    private int status;
    private String message;

    private StatusEnumUntil(int status, String message){
        this.status=status;
        this.message=message;
    }

    public int getStatus() {
        return status;
    }


    public String getMessage() {
        return message;
    }
}
