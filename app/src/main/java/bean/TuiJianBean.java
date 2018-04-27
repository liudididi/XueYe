package bean;

import java.util.List;

/**
 * Created by 地地 on 2018/4/2.
 * 邮箱：461211527@qq.com.
 */

public class TuiJianBean {


    /**
     * name : 北京大学
     * collegeBadge : /images/school/201202161100413470.jpg
     * year : 2017
     * time : 本科一批
     * minScore : 660
     * recommend : ["本科一批","一线城市"]
     * vipRecommend : ["含适合兴趣专业","含适合性格专业","录取概率为0.0%，建议谨慎填报"]
     * efcRecommendMajorEntity : [{"major":"人文科学试验班(艺术史论、戏剧影视文学、文化产业管理)","majorId":null,"gai":null,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":null,"year":null,"time":null,"score":null,"planType":"非定向","classification":"文史","number":"3","planYear":"2017"},{"major":"文科试验班类(元培学院)","majorId":null,"gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":null,"year":2016,"time":"本科一批","score":685,"planType":"非定向","classification":"文史","number":"5","planYear":"2017"},{"major":"中国语言文学类","majorId":null,"gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":null,"year":2016,"time":"本科一批","score":676,"planType":"非定向","classification":"文史","number":"5","planYear":"2017"},{"major":"公共管理类","majorId":null,"gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":null,"year":2016,"time":"本科一批","score":678,"planType":"非定向","classification":"文史","number":"3","planYear":"2017"},{"major":"历史学类","majorId":"060101","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"历史学类","year":2016,"time":"本科一批","score":684,"planType":"非定向","classification":"文史","number":"5","planYear":"2017"},{"major":"哲学类","majorId":"010101","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"哲学类","year":2016,"time":"本科一批","score":675,"planType":"非定向","classification":"文史","number":"5","planYear":"2017"},{"major":"工商管理类","majorId":"120201","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"工商管理类","year":2016,"time":"本科一批","score":685,"planType":"非定向","classification":"文史","number":"4","planYear":"2017"},{"major":"新闻传播学类","majorId":null,"gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":null,"year":2016,"time":"本科一批","score":675,"planType":"非定向","classification":"文史","number":"3","planYear":"2017"},{"major":"经济学类","majorId":"020101","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"经济学类","year":2016,"time":"本科一批","score":678,"planType":"非定向","classification":"文史","number":"4","planYear":"2017"},{"major":"信息管理与信息系统","majorId":"120102","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"管理科学与工程类","year":2016,"time":"本科一批","score":674,"planType":"非定向","classification":"文史","number":"3","planYear":"2017"},{"major":"法学","majorId":"030101","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":"特色专业","majorType":"法学类","year":2016,"time":"本科一批","score":682,"planType":"非定向","classification":"文史","number":"4","planYear":"2017"},{"major":"考古学","majorId":"060103","gai":0,"efcFlag":null,"importantFlag":"重点专业","featureFlag":null,"majorType":"历史学类","year":2014,"time":"本科一批","score":664,"planType":"非定向","classification":"文史","number":"5","planYear":"2017"},{"major":"社会学","majorId":"030301","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"社会学类","year":2016,"time":"本科一批","score":676,"planType":"非定向","classification":"文史","number":"4","planYear":"2017"},{"major":"英语","majorId":"050201","gai":0,"efcFlag":null,"importantFlag":null,"featureFlag":null,"majorType":"外国语言文学类","year":2016,"time":"本科一批","score":675,"planType":"非定向","classification":"文史","number":"3","planYear":"2017"},{"major":"国际政治","majorId":"030202","gai":0,"efcFlag":null,"importantFlag":"重点专业","featureFlag":null,"majorType":"政治学类","year":2016,"time":"本科一批","score":675,"planType":"非定向","classification":"文史","number":"4","planYear":"2017"}]
     * efcState : 1
     * countDown : null
     */

    private String name;
    private String collegeBadge;
    private String year;
    private String time;
    private int minScore;
    private String efcState;
    private String countDown;
    private List<String> recommend;
    private List<String> vipRecommend;
    private List<EfcRecommendMajorEntityBean> efcRecommendMajorEntity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCollegeBadge() {
        return collegeBadge;
    }

    public void setCollegeBadge(String collegeBadge) {
        this.collegeBadge = collegeBadge;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMinScore() {
        return minScore;
    }

    public void setMinScore(int minScore) {
        this.minScore = minScore;
    }

    public String getEfcState() {
        return efcState;
    }

    public void setEfcState(String efcState) {
        this.efcState = efcState;
    }

    public String getCountDown() {
        return countDown;
    }

    public void setCountDown(String countDown) {
        this.countDown = countDown;
    }

    public List<String> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<String> recommend) {
        this.recommend = recommend;
    }

    public List<String> getVipRecommend() {
        return vipRecommend;
    }

    public void setVipRecommend(List<String> vipRecommend) {
        this.vipRecommend = vipRecommend;
    }

    public List<EfcRecommendMajorEntityBean> getEfcRecommendMajorEntity() {
        return efcRecommendMajorEntity;
    }

    public void setEfcRecommendMajorEntity(List<EfcRecommendMajorEntityBean> efcRecommendMajorEntity) {
        this.efcRecommendMajorEntity = efcRecommendMajorEntity;
    }

    public static class EfcRecommendMajorEntityBean {
        /**
         * major : 人文科学试验班(艺术史论、戏剧影视文学、文化产业管理)
         * majorId : null
         * gai : null
         * efcFlag : null
         * importantFlag : null
         * featureFlag : null
         * majorType : null
         * year : null
         * time : null
         * score : null
         * planType : 非定向
         * classification : 文史
         * number : 3
         * planYear : 2017
         */

        private String major;
        private String majorId;
        private Object gai;
        private String efcFlag;
        private String importantFlag;
        private String featureFlag;
        private String majorType;
        private String year;
        private String time;
        private String score;
        private String planType;
        private String classification;
        private String number;
        private String planYear;

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

        public Object getGai() {
            return gai;
        }

        public void setGai(Object gai) {
            this.gai = gai;
        }

        public Object getEfcFlag() {
            return efcFlag;
        }

        public void setEfcFlag(String efcFlag) {
            this.efcFlag = efcFlag;
        }

        public String getImportantFlag() {
            return importantFlag;
        }

        public void setImportantFlag(String importantFlag) {
            this.importantFlag = importantFlag;
        }

        public String getFeatureFlag() {
            return featureFlag;
        }

        public void setFeatureFlag(String featureFlag) {
            this.featureFlag = featureFlag;
        }

        public String getMajorType() {
            return majorType;
        }

        public void setMajorType(String majorType) {
            this.majorType = majorType;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public String getPlanType() {
            return planType;
        }

        public void setPlanType(String planType) {
            this.planType = planType;
        }

        public String getClassification() {
            return classification;
        }

        public void setClassification(String classification) {
            this.classification = classification;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPlanYear() {
            return planYear;
        }

        public void setPlanYear(String planYear) {
            this.planYear = planYear;
        }
    }
}
