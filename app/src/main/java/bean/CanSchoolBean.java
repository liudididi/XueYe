package bean;

import java.util.List;

/**
 * Created by 祝文 on 2018/1/27.
 */

public class CanSchoolBean {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * nine : 985
         * address : 广东省广州市
         * father : 教育部
         * typeRank : 理工10
         * univType : 大学
         * two : 211
         * defense_student : 国防生
         * url : /images/school/201202170134108906.jpg
         * com_rank : A-
         * graduate : 研究生院
         * recruit : 自主招生
         * name : 华南理工大学
         * ranking : 全国27
         * preeminent_plan : 卓越计划
         * time : 本科一批
         */

        private String nine;
        private String address;
        private String father;
        private String typeRank;
        private String univType;
        private String two;
        private String defense_student;
        private String url;
        private String com_rank;
        private String graduate;
        private String recruit;
        private String name;
        private String ranking;
        private String preeminent_plan;
        private String time;

        public String getNine() {
            return nine;
        }

        public void setNine(String nine) {
            this.nine = nine;
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

        public String getTypeRank() {
            return typeRank;
        }

        public void setTypeRank(String typeRank) {
            this.typeRank = typeRank;
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

        public String getCom_rank() {
            return com_rank;
        }

        public void setCom_rank(String com_rank) {
            this.com_rank = com_rank;
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

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
