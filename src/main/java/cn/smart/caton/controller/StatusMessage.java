package cn.smart.caton.controller;

/**
 * Created by user on 2017/7/8.
 */
import java.io.Serializable;

public class StatusMessage implements Serializable {


    /**
     * @Fields serialVersionUID : TODO
     */

    private static final long serialVersionUID = -240076134340681223L;

    public StatusMessage(String message){
        this.message = message;
        this.status = false;
    }

    private boolean status;

    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}

