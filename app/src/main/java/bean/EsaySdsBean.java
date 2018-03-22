package bean;

/**
 * Created by 祝文 on 2018/3/22.
 */

public class EsaySdsBean {

    /**
     * test_analysis : 艺术型(Artistic)具有此类倾向的个体，对具有创造、想象及自我表现空间的工作显示出明显偏好他们和研究型倾向的个体相同之处在于创造倾向明显，对于结构化程度较高的任务及环境都不太喜欢，对于机械性及程式化的工作了无兴趣也比较喜欢独立行事，不太合群但两者所不同的是艺术倾向明显的个体好自我表现，重视自己的感性，直觉力较好，情绪变化较大。
     * standby1 : 艺术型(A)
     * test_job : 适合职业方向：美术雕刻以及工艺方面，舞蹈、戏剧等。
     * id : 3
     * test_code : A
     */

    private String test_analysis;
    private String standby1;
    private String test_job;
    private int id;
    private String test_code;

    public String getTest_analysis() {
        return test_analysis;
    }

    public void setTest_analysis(String test_analysis) {
        this.test_analysis = test_analysis;
    }

    public String getStandby1() {
        return standby1;
    }

    public void setStandby1(String standby1) {
        this.standby1 = standby1;
    }

    public String getTest_job() {
        return test_job;
    }

    public void setTest_job(String test_job) {
        this.test_job = test_job;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTest_code() {
        return test_code;
    }

    public void setTest_code(String test_code) {
        this.test_code = test_code;
    }
}
