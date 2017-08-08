package cn.sxh.snowfox.API;

/**
 * Created by snow on 2017/8/8.
 */

public class HttpResult<T> {
    private boolean success;
    private int code;
    private String reason;
    private double time;
    private T result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T data) {
        this.result = data;
    }
}
