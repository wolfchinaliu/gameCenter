package weixin.liuliangbao.jsonbean.ViewBean;

/**
 * Created by aa on 2015/12/16.
 */
public class BusinessCommonJsonBean  implements java.io.Serializable {
    /**
     * code : 1
     * msg : 操作成功
     * balance : 2258.75
     */

    private int code;
    private String msg;
    private double balance;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public double getBalance() {
        return balance;
    }
}

