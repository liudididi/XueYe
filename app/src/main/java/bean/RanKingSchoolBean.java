package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/1/30.
 */

public class RanKingSchoolBean {


    /**
     * totalCount : 2237
     * pageSize : 10
     * totalPage : 224
     * currPage : 1
     * list : [{"nine":"985","type_rank":"综合1","address":"北京市","father":"教育部","shuoshi":"312","two":"211","defense_student":"国防生","url":"/images/school/201202161100413470.jpg","graduate":"研究生院","recruit":"自主招生","name":"北京大学","ranking":"全国1","preeminent_plan":"","boshi":"263"},{"nine":"985","type_rank":"理工1","address":"北京市","father":"教育部","shuoshi":"271","two":"211","defense_student":"国防生","url":"/images/school/201201090552434112.jpg","graduate":"研究生院","recruit":"自主招生","name":"清华大学","ranking":"全国2","preeminent_plan":"卓越计划","boshi":"253"},{"nine":"985","type_rank":"综合2","address":"湖北省武汉市","father":"教育部","shuoshi":"347","two":"211","defense_student":"国防生","url":"/images/school/201202170954230781.jpg","graduate":"研究生院","recruit":"自主招生","name":"武汉大学","ranking":"全国3","preeminent_plan":"卓越计划","boshi":"250"},{"nine":"985","type_rank":"综合3","address":"上海市","father":"教育部","shuoshi":"219","two":"211","defense_student":"国防生","url":"/images/school/201202080125539491.jpg","graduate":"研究生院","recruit":"自主招生","name":"复旦大学","ranking":"全国4","preeminent_plan":"","boshi":"153"},{"nine":"985","type_rank":"综合4","address":"浙江省杭州市","father":"教育部","shuoshi":"326","two":"211","defense_student":"国防生","url":"/images/school/201202080249332772.jpg","graduate":"研究生院","recruit":"自主招生","name":"浙江大学","ranking":"全国5","preeminent_plan":"卓越计划","boshi":"296"},{"nine":"985","type_rank":"综合5","address":"上海市","father":"教育部","shuoshi":"298","two":"211","defense_student":"国防生","url":"/images/school/201202080126204647.jpg","graduate":"","recruit":"自主招生","name":"上海交通大学","ranking":"全国6","preeminent_plan":"卓越计划","boshi":"203"},{"nine":"985","type_rank":"综合6","address":"江苏省南京市","father":"教育部","shuoshi":"213","two":"211","defense_student":"国防生","url":"/images/school/201202080142356522.jpg","graduate":"研究生院","recruit":"自主招生","name":"南京大学","ranking":"全国7","preeminent_plan":"卓越计划","boshi":"147"},{"nine":"985","type_rank":"综合7","address":"北京市","father":"教育部","shuoshi":"183","two":"211","defense_student":"国防生","url":"/images/school/201202020436067980.jpg","graduate":"研究生院","recruit":"自主招生","name":"中国人民大学","ranking":"全国8","preeminent_plan":"","boshi":"124"},{"nine":"985","type_rank":"综合8","address":"吉林省长春市","father":"教育部","shuoshi":"311","two":"211","defense_student":"国防生","url":"/images/school/201202081126476835.jpg","graduate":"研究生院","recruit":"自主招生","name":"吉林大学","ranking":"全国9","preeminent_plan":"卓越计划","boshi":"240"},{"nine":"985","type_rank":"理工2","address":"湖北省武汉市","father":"教育部","shuoshi":"214","two":"211","defense_student":"国防生","url":"/images/school/201202160122407532.jpg","graduate":"研究生院","recruit":"自主招生","name":"华中科技大学","ranking":"全国10","preeminent_plan":"卓越计划","boshi":"175"}]
     */

    private int totalCount;
    private int pageSize;
    private int totalPage;
    private int currPage;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * nine : 985
         * type_rank : 综合1
         * address : 北京市
         * father : 教育部
         * shuoshi : 312
         * two : 211
         * defense_student : 国防生
         * url : /images/school/201202161100413470.jpg
         * graduate : 研究生院
         * recruit : 自主招生
         * name : 北京大学
         * ranking : 全国1
         * preeminent_plan :
         * boshi : 263
         */

        private String nine;
        private String type_rank;
        private String address;
        private String father;
        private String shuoshi;
        private String two;
        private String defense_student;
        private String url;
        private String graduate;
        private String recruit;
        private String name;
        private String ranking;
        private String preeminent_plan;
        private String boshi;

        public String getNine() {
            return nine;
        }

        public void setNine(String nine) {
            this.nine = nine;
        }

        public String getType_rank() {
            return type_rank;
        }

        public void setType_rank(String type_rank) {
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

        public String getShuoshi() {
            return shuoshi;
        }

        public void setShuoshi(String shuoshi) {
            this.shuoshi = shuoshi;
        }

        public String getTwo() {
            return two;
        }

        public void setTwo(String two) {
            this.two = two;
        }

        public String getDefense_student() {
            return defense_student;
        }

        public void setDefense_student(String defense_student) {
            this.defense_student = defense_student;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public String getPreeminent_plan() {
            return preeminent_plan;
        }

        public void setPreeminent_plan(String preeminent_plan) {
            this.preeminent_plan = preeminent_plan;
        }

        public String getBoshi() {
            return boshi;
        }

        public void setBoshi(String boshi) {
            this.boshi = boshi;
        }
    }
}
