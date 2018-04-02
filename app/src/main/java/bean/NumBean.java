package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/3/31.
 */

public class NumBean {


    /**
     * classify : 文科
     * address : 江苏省苏州市
     * majors : [{"major":"工商管理类(中外合作办学)(含经济、会计、信管、市场"},{"major":"工商管理类(中外合作办学)(含经济、会计、信管、市场"},{"major":"外国语言文学类"},{"major":"数学类"},{"major":"建筑类"},{"major":"工商管理类"}]
     * father : 江苏省教育厅
     * name : 西交利物浦大学
     * time : 1898年
     * stuProvince : 北京
     */

    private String classify;
    private String address;
    private String father;
    private String name;
    private String time;
    private String stuProvince;
    private List<MajorsBean> majors;

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStuProvince() {
        return stuProvince;
    }

    public void setStuProvince(String stuProvince) {
        this.stuProvince = stuProvince;
    }

    public List<MajorsBean> getMajors() {
        return majors;
    }

    public void setMajors(List<MajorsBean> majors) {
        this.majors = majors;
    }

    public static class MajorsBean {
        /**
         * major : 工商管理类(中外合作办学)(含经济、会计、信管、市场
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
