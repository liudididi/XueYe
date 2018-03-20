package bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 地地 on 2018/3/12.
 * 邮箱：461211527@qq.com.
 */

public class WeiXinBean {


    /**
     * appid : wx1152aee306087394
     * noncestr : 1411028658
     * package : Sign=WXPay
     * partnerid : 1500307402
     * prepayid : wx201803171411004e924c79720793939314
     * sign : 9FDFFB0770A39E07288340CFFDC54C43
     * timestamp : 1521267062
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
