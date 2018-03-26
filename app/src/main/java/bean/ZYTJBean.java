package bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 祝文 on 2018/3/24.
 */

public class ZYTJBean {

    @SerializedName("count(major)")
    private int _$CountMajor79; // FIXME check this code
    private String name;
    private int type;
    private List<MajorinfoBean> majorinfo;
    private boolean jizhu=true;

    public void setJizhu(boolean jizhu) {
        this.jizhu = jizhu;
    }

    public boolean isJizhu() {
        return jizhu;
    }

    public int get_$CountMajor79() {
        return _$CountMajor79;
    }

    public void set_$CountMajor79(int _$CountMajor79) {
        this._$CountMajor79 = _$CountMajor79;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<MajorinfoBean> getMajorinfo() {
        return majorinfo;
    }

    public void setMajorinfo(List<MajorinfoBean> majorinfo) {
        this.majorinfo = majorinfo;
    }

    public static class MajorinfoBean {
        /**
         * major : 考古学
         */

        private String major;

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }
    }
}
