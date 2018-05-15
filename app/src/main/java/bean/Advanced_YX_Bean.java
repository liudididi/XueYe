package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/3/14.
 */

public class Advanced_YX_Bean {

    private boolean selecked;

    /**
     * name : 东华理工大学
     * recommendIndex : 0.29700002
     * num : 0
     * yxscore : 460
     * yxGai : 0.99
     * time : 本科二批
     * year : null
     * info : {"name":"东华理工大学","ranking":"全国269","typeRank":"理工96","address":"江西省抚州市","father":"江西省教育厅","univType":"大学","two":"","nine":"","graduate":"","recruit":"","defenseStudent":"国防生","preeminentPlan":"卓越计划"}
     * major : [{"name":null,"major":null,"majorId":null,"yxscore":null,"zyscore":null,"yxGai":null,"zyGai":null,"time":null,"year":null}]
     */

    private String name;
    private String recommendIndex;
    private String num;
    private String yxscore;
    private String yxGai;
    private String time;
    private Object year;
    private InfoBean info;
    private List<MajorBean> major;

    @Override
    public String toString() {
        return "Advanced_YX_Bean{" +
                "selecked=" + selecked +
                '}';
    }

    public void setSelecked(boolean selecked) {
        this.selecked = selecked;
    }

    public boolean isSelecked() {
        return selecked;
    }

    public Advanced_YX_Bean(boolean selecked) {
        this.selecked = selecked;
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

    public String getYxscore() {
        return yxscore;
    }

    public void setYxscore(String yxscore) {
        this.yxscore = yxscore;
    }

    public String getYxGai() {
        return yxGai;
    }

    public void setYxGai(String yxGai) {
        this.yxGai = yxGai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getYear() {
        return year;
    }

    public void setYear(Object year) {
        this.year = year;
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
         * name : 东华理工大学
         * ranking : 全国269
         * typeRank : 理工96
         * address : 江西省抚州市
         * father : 江西省教育厅
         * univType : 大学
         * two :
         * nine :
         * graduate :
         * recruit :
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
         * name : null
         * major : null
         * majorId : null
         * yxscore : null
         * zyscore : null
         * yxGai : null
         * zyGai : null
         * time : null
         * year : null
         */

        private Object name;
        private Object major;
        private Object majorId;
        private Object yxscore;
        private Object zyscore;
        private Object yxGai;
        private Object zyGai;
        private Object time;
        private Object year;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public Object getMajor() {
            return major;
        }

        public void setMajor(Object major) {
            this.major = major;
        }

        public Object getMajorId() {
            return majorId;
        }

        public void setMajorId(Object majorId) {
            this.majorId = majorId;
        }

        public Object getYxscore() {
            return yxscore;
        }

        public void setYxscore(Object yxscore) {
            this.yxscore = yxscore;
        }

        public Object getZyscore() {
            return zyscore;
        }

        public void setZyscore(Object zyscore) {
            this.zyscore = zyscore;
        }

        public Object getYxGai() {
            return yxGai;
        }

        public void setYxGai(Object yxGai) {
            this.yxGai = yxGai;
        }

        public Object getZyGai() {
            return zyGai;
        }

        public void setZyGai(Object zyGai) {
            this.zyGai = zyGai;
        }

        public Object getTime() {
            return time;
        }

        public void setTime(Object time) {
            this.time = time;
        }

        public Object getYear() {
            return year;
        }

        public void setYear(Object year) {
            this.year = year;
        }
    }
}
