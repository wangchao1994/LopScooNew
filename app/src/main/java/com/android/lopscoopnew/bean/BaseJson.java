package com.android.lopscoopnew.bean;


import com.android.lopscoopnew.net.NetConfig;

import java.io.Serializable;

public class BaseJson<T> implements Serializable{
    private T data;
    private String message;
    private String msg;
    private int delay;
    @Override
    public String toString() {
        return "BaseJson{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }


    public T getData() {
        return data;
    }

    public String getCode() {
        return message;
    }

    public String getMsg() {
        return msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * 请求是否成功
     * @return
     */
    public boolean isSuccess() {
        if (message.equals(NetConfig.RequestSuccess)) {
            return true;
        } else {
            return false;
        }
    }
}
