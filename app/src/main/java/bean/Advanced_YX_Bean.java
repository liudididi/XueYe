package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/3/14.
 */

public class Advanced_YX_Bean {





    private boolean selecked;
    /**
     * name : 清华大学
     * recommendIndex : 0.7685
     * yxGai: 0.99,
     * num : 1
     * info : {"name":"清华大学","ranking":"全国2","typeRank":"理工1","address":"北京市","father":"教育部","univType":"大学","two":"211","nine":"985","graduate":"研究生院","recruit":"自主招生","defenseStudent":"国防生","preeminentPlan":"卓越计划"}
     * major : [{"name":"清华大学","major":"英语","majorId":"050201","yxscore":"665","zyscore":"679","yxGai":"0.975","zyGai":"0.905","time":"本科一批"}]
     */

    private String name;
    private String recommendIndex;
    private String num;
    private String yxGai;
    private InfoBean info;
    private List<MajorBean> major;


    public String getYxGai() {
        return yxGai;
    }

    public void setYxGai(String yxGai) {
        this.yxGai = yxGai;
    }

    public Advanced_YX_Bean(String yxGai) {
        this.yxGai = yxGai;
    }

    @Override
    public String toString() {
        return "Advanced_YX_Bean{" +
                "yxGai='" + yxGai + '\'' +
                '}';
    }

    public void setSelecked(boolean selecked) {
        this.selecked = selecked;
    }

    public boolean isSelecked() {
        return selecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(String recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
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
         * name : 清华大学
         * ranking : 全国2
         * typeRank : 理工1
         * address : 北京市
         * father : 教育部
         * univType : 大学
         * two : 211
         * nine : 985
         * graduate : 研究生院
         * recruit : 自主招生
         * defenseStudent : 国防生
         * preeminentPlan : 卓越计划
         */

        private String name;
        private String ranking;
        private String typeRank;
        private String address;
        private String father;
        private String univType;
        private String two;
        private String nine;
        private String graduate;
        private String recruit;
        private String defenseStudent;
        private String preeminentPlan;

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

        public String getTypeRank() {
            return typeRank;
        }

        public void setTypeRank(String typeRank) {
            this.typeRank = typeRank;
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

        public String getUnivType() {
            return univType;
        }

        public void setUnivType(String univType) {
            this.univType = univType;
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

        public String getDefenseStudent() {
            return defenseStudent;
        }

        public void setDefenseStudent(String defenseStudent) {
            this.defenseStudent = defenseStudent;
        }

        public String getPreeminentPlan() {
            return preeminentPlan;
        }

        public void setPreeminentPlan(String preeminentPlan) {
            this.preeminentPlan = preeminentPlan;
        }
    }

    public static class MajorBean {
        /**
         * name : 清华大学
         * major : 英语
         * majorId : 050201
         * yxscore : 665
         * zyscore : 679
         * yxGai : 0.975
         * zyGai : 0.905
         * time : 本科一批
         */

        private String name;
        private String major;
        private String majorId;
        private String yxscore;
        private String zyscore;
        private String yxGai;
        private String zyGai;
        private String time;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getMajorId() {
            return majorId;
        }

        public void setMajorId(String majorId) {
            this.majorId = majorId;
        }

        public String getYxscore() {
            return yxscore;
        }

        public void setYxscore(String yxscore) {
            this.yxscore = yxscore;
        }

        public String getZyscore() {
            return zyscore;
        }

        public void setZyscore(String zyscore) {
            this.zyscore = zyscore;
        }

        public String getYxGai() {
            return yxGai;
        }

        public void setYxGai(String yxGai) {
            this.yxGai = yxGai;
        }

        public String getZyGai() {
            return zyGai;
        }

        public void setZyGai(String zyGai) {
            this.zyGai = zyGai;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
