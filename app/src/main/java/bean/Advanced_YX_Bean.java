package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/3/14.
 */

public class Advanced_YX_Bean {





    private boolean selecked=true;
    /**
     * major : [{"yxGai":"0.915","major":"经济学类","zyscore":678,"name":"北京大学","yxscore":677,"time":"本科一批","major_id":"020101","zyGai":"0.91"},{"yxGai":"0.915","major":"英语","zyscore":675,"name":"北京大学","yxscore":677,"time":"本科一批","major_id":"050201","zyGai":"0.925"}]
     * num : 2
     * name : 北京大学
     * recommendIndex : 0.8095
     * info : {"name":"北京大学","ranking":"全国1","type_rank":null,"address":"北京市","father":"教育部","univ_type":null,"two":"211","nine":"985","graduate":"研究生院","recruit":"自主招生","defense_student":null,"preeminent_plan":null}
     */

    private int num;
    private String name;
    private double recommendIndex;
    private InfoBean info;
    private List<MajorBean> major;

    public void setSelecked(boolean selecked) {
        this.selecked = selecked;
    }

    public boolean isSelecked() {
        return selecked;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(double recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public InfoBean getInfo() {
        return info;
    }

    public void setInfo(InfoBean info) {
        this.info = info;
    }

    public List<MajorBean> getMajor() {
        return major;
    }

    public void setMajor(List<MajorBean> major) {
        this.major = major;
    }


    public static class InfoBean {
        /**
         * name : 北京大学
         * ranking : 全国1
         * type_rank : null
         * address : 北京市
         * father : 教育部
         * univ_type : null
         * two : 211
         * nine : 985
         * graduate : 研究生院
         * recruit : 自主招生
         * defense_student : null
         * preeminent_plan : null
         */

        private String name;
        private String ranking;
        private Object type_rank;
        private String address;
        private String father;
        private Object univ_type;
        private String two;
        private String nine;
        private String graduate;
        private String recruit;
        private Object defense_student;
        private Object preeminent_plan;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRanking() {
            return ranking;
        }

        public void setRanking(String ranking) {
            this.ranking = ranking;
        }

        public Object getType_rank() {
            return type_rank;
        }

        public void setType_rank(Object type_rank) {
            this.type_rank = type_rank;
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

        public Object getUniv_type() {
            return univ_type;
        }

        public void setUniv_type(Object univ_type) {
            this.univ_type = univ_type;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public String getNine() {
            return nine;
        }

        public void setNine(String nine) {
            this.nine = nine;
        }

        public String getGraduate() {
            return graduate;
        }

        public void setGraduate(String graduate) {
            this.graduate = graduate;
        }

        public String getRecruit() {
            return recruit;
        }

        public void setRecruit(String recruit) {
            this.recruit = recruit;
        }

        public Object getDefense_student() {
            return defense_student;
        }

        public void setDefense_student(Object defense_student) {
            this.defense_student = defense_student;
        }

        public Object getPreeminent_plan() {
            return preeminent_plan;
        }

        public void setPreeminent_plan(Object preeminent_plan) {
            this.preeminent_plan = preeminent_plan;
        }
    }

    public static class MajorBean {
        /**
         * yxGai : 0.915
         * major : 经济学类
         * zyscore : 678
         * name : 北京大学
         * yxscore : 677
         * time : 本科一批
         * major_id : 020101
         * zyGai : 0.91
         */

        private String yxGai;
        private String major;
        private int zyscore;
        private String name;
        private int yxscore;
        private String time;
        private String major_id;
        private String zyGai;

        public String getYxGai() {
            return yxGai;
        }

        public void setYxGai(String yxGai) {
            this.yxGai = yxGai;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public int getZyscore() {
            return zyscore;
        }

        public void setZyscore(int zyscore) {
            this.zyscore = zyscore;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getYxscore() {
            return yxscore;
        }

        public void setYxscore(int yxscore) {
            this.yxscore = yxscore;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getMajor_id() {
            return major_id;
        }

        public void setMajor_id(String major_id) {
            this.major_id = major_id;
        }

        public String getZyGai() {
            return zyGai;
        }

        public void setZyGai(String zyGai) {
            this.zyGai = zyGai;
        }
    }
}
