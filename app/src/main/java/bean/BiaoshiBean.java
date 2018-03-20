package bean;

/**
 * Created by 祝文 on 2018/3/12.
 */

public class BiaoshiBean {
    private String str;
    private boolean flag;

    @Override
    public String toString() {
        return "BiaoshiBean{" +
                "str='" + str + '\'' +
                ", flag=" + flag +
                '}';
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getStr() {
        return str;
    }

    public boolean isFlag() {
        return flag;
    }

    public BiaoshiBean(String str, boolean flag) {
        this.str = str;
        this.flag = flag;
    }
}
