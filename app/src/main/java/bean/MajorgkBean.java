package bean;

import java.util.List;

/**
 * Created by 地地 on 2018/2/2.
 * 邮箱：461211527@qq.com.
 */

public class MajorgkBean  {


    /**
     * direction_employment : 该专业毕业生可在国家机关、文教事业、新闻媒体、公司企业等部门从事行政、宣传、管理、教学、科研工作；亦可在工商、外贸、金融、保险、证券、旅游、房地产等企事业单位从事企业营销管理、客户资源管理、网络营销管理、营销策划、营销诊断、市场调查和咨询等工作；该专业适合公务员岗位及升学考研。
     * year_school : 四年
     * major_id : 010101
     * practice_teaching : 哲学是人文科学领域内的基础学科，是对基本和普遍之问题的研究。在希腊文中，哲学是爱智慧的意思。学哲学，就是学习智慧。哲学的爱智，无论是对自然的惊讶，还是认识人自己，都不仅仅是一种对知识的追求，更重要的是一种对生活意义的关切，对生活境界的陶冶。哲学，是使人崇高起来的学问。哲学的爱智，还是一种反思的、批判的思想活动，它要追究各种知识的根据，思考历史进步的尺度，询问真善美的标准，探索生活信念的前提。
     * training_target : 哲学专业是培养具有一定马克思主义哲学理论素养和系统的专业基础知识，能运用科学的世界观和方法论分析当代世界与中国的现实问题的应用型、复合型高级专门人才的学科
     * training_requirements : 他们具有一定的哲学理论思维能力、创新能力、口头与文字表达能力、社会活动能力和一定的科研能力，具有较高外语水平的理论研究人才以及能在国家机关、文教事业新闻出版、企业等部门从事实际工作。
     * course : 哲学概论、马克思主义哲学原理、中国哲学史、西方哲学史、科学技术哲学、伦理学、宗教学、美学、逻辑学、心理学、中外哲学原著导读等。
     * bigType : 哲学类
     * employment_prospects : 兼职神秘访客、兼职神秘顾客、演讲讲师、业务代表、编辑、销售总监、销售经理、图书编辑、行政人事专员、文案策划、房地产文案、董事长秘书等。
     * id : 1470
     * award_degree : 哲学学士
     * ability : [{"id":null,"majorId":null,"abilityId":1,"graduatesAbility":"比较系统地掌握马克思主义哲学、中国哲学和西方哲学的理论和历史"},{"id":null,"majorId":null,"abilityId":2,"graduatesAbility":"具有一定的社会科学、人文科学。自然科学、思维科学的相关知识"},{"id":null,"majorId":null,"abilityId":3,"graduatesAbility":"掌握哲学学科的基本研究方法、治学方法和相应的社会调查能力"},{"id":null,"majorId":null,"abilityId":4,"graduatesAbility":"了解国内外哲学界最重要的理论前沿和发展动态"},{"id":null,"majorId":null,"abilityId":5,"graduatesAbility":"了解国内外最重大的实践问题和发展动态"},{"id":null,"majorId":null,"abilityId":6,"graduatesAbility":"具有分析和解决社会现实问题的初步能力。"}]
     * job : [{"id":4658,"majorId":"010101","jobname":"课程开发专员"},{"id":4659,"majorId":"010101","jobname":"高校辅导员"},{"id":4660,"majorId":"010101","jobname":"教育产品开发教师等。"},{"id":4661,"majorId":"010101","jobname":"期刊文字编辑"},{"id":4662,"majorId":"010101","jobname":"图书编辑"}]
     * averagesalary : 5314
     */

    private String direction_employment;
    private String year_school;
    private String major_id;
    private String practice_teaching;
    private String training_target;
    private String training_requirements;
    private String course;
    private String bigType;
    private String employment_prospects;
    private int id;
    private String award_degree;
    private int averagesalary;
    private List<AbilityBean> ability;
    private List<JobBean> job;

    public String getDirection_employment() {
        return direction_employment;
    }

    public void setDirection_employment(String direction_employment) {
        this.direction_employment = direction_employment;
    }

    public String getYear_school() {
        return year_school;
    }

    public void setYear_school(String year_school) {
        this.year_school = year_school;
    }

    public String getMajor_id() {
        return major_id;
    }

    public void setMajor_id(String major_id) {
        this.major_id = major_id;
    }

    public String getPractice_teaching() {
        return practice_teaching;
    }

    public void setPractice_teaching(String practice_teaching) {
        this.practice_teaching = practice_teaching;
    }

    public String getTraining_target() {
        return training_target;
    }

    public void setTraining_target(String training_target) {
        this.training_target = training_target;
    }

    public String getTraining_requirements() {
        return training_requirements;
    }

    public void setTraining_requirements(String training_requirements) {
        this.training_requirements = training_requirements;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBigType() {
        return bigType;
    }

    public void setBigType(String bigType) {
        this.bigType = bigType;
    }

    public String getEmployment_prospects() {
        return employment_prospects;
    }

    public void setEmployment_prospects(String employment_prospects) {
        this.employment_prospects = employment_prospects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAward_degree() {
        return award_degree;
    }

    public void setAward_degree(String award_degree) {
        this.award_degree = award_degree;
    }

    public int getAveragesalary() {
        return averagesalary;
    }

    public void setAveragesalary(int averagesalary) {
        this.averagesalary = averagesalary;
    }

    public List<AbilityBean> getAbility() {
        return ability;
    }

    public void setAbility(List<AbilityBean> ability) {
        this.ability = ability;
    }

    public List<JobBean> getJob() {
        return job;
    }

    public void setJob(List<JobBean> job) {
        this.job = job;
    }

    public static class AbilityBean {
        /**
         * id : null
         * majorId : null
         * abilityId : 1
         * graduatesAbility : 比较系统地掌握马克思主义哲学、中国哲学和西方哲学的理论和历史
         */

        private Object id;
        private Object majorId;
        private int abilityId;
        private String graduatesAbility;

        public Object getId() {
            return id;
        }

        public void setId(Object id) {
            this.id = id;
        }

        public Object getMajorId() {
            return majorId;
        }

        public void setMajorId(Object majorId) {
            this.majorId = majorId;
        }

        public int getAbilityId() {
            return abilityId;
        }

        public void setAbilityId(int abilityId) {
            this.abilityId = abilityId;
        }

        public String getGraduatesAbility() {
            return graduatesAbility;
        }

        public void setGraduatesAbility(String graduatesAbility) {
            this.graduatesAbility = graduatesAbility;
        }
    }

    public static class JobBean {
        /**
         * id : 4658
         * majorId : 010101
         * jobname : 课程开发专员
         */

        private int id;
        private String majorId;
        private String jobname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMajorId() {
            return majorId;
        }

        public void setMajorId(String majorId) {
            this.majorId = majorId;
        }

        public String getJobname() {
            return jobname;
        }

        public void setJobname(String jobname) {
            this.jobname = jobname;
        }
    }
}
