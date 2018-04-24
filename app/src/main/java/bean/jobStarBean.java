package bean;

/**
 * Created by 地地 on 2018/3/12.
 * 邮箱：461211527@qq.com.
 */

public class jobStarBean {


    /**
     * major : 机械设计制造及其自动化
     * majorId : 080202
     * gai : 0.429
     * averagesalary : 7815
     * trainingTarget : 机械设计制造及其自动化专业培养具备机械设计制造基础知识与应用能力，能在工业主产第一线从事机械制造领域内的设计制造、科技开发、应用研究、运行管理和经营销售等方面工作的高级工程技术人才。
     * ranking : 第 55 名 (工学)
     * rank : 所有专业1110个，工学类共170个本科专业，在“工学”中就业排名第55
     * needAddress : 深圳
     * proAddress : 专业需求量最多的地区是“深圳”，占26%
     * needMajor : 机械/设备/重工
     * proJob : 专业需求量最多的行业是“机械/设备/重工”，占25%
     * directionEmployment : 学本专业学生毕业后可在工业生产第一线（各种机械厂，比如三一重工、徐州重工，还有一些汽车制造厂）从事机械制造领域内的设计制造、科技开发、应用研究、运行管理和经营销售等方面工作，发动机设计、机械设计、模具设计、设备维护、生产管理、销售等。该专业适合升学考研。
     */

    private String major;
    private String majorId;
    private double gai;
    private String averagesalary;
    private String trainingTarget;
    private String ranking;
    private String rank;
    private String needAddress;
    private String proAddress;
    private String needMajor;
    private String proJob;
    private String directionEmployment;
    public   boolean  xh;

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

    public double getGai() {
        return gai;
    }

    public void setGai(double gai) {
        this.gai = gai;
    }

    public String getAveragesalary() {
        return averagesalary;
    }

    public void setAveragesalary(String averagesalary) {
        this.averagesalary = averagesalary;
    }

    public String getTrainingTarget() {
        return trainingTarget;
    }

    public void setTrainingTarget(String trainingTarget) {
        this.trainingTarget = trainingTarget;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getNeedAddress() {
        return needAddress;
    }

    public void setNeedAddress(String needAddress) {
        this.needAddress = needAddress;
    }

    public String getProAddress() {
        return proAddress;
    }

    public void setProAddress(String proAddress) {
        this.proAddress = proAddress;
    }

    public String getNeedMajor() {
        return needMajor;
    }

    public void setNeedMajor(String needMajor) {
        this.needMajor = needMajor;
    }

    public String getProJob() {
        return proJob;
    }

    public void setProJob(String proJob) {
        this.proJob = proJob;
    }

    public String getDirectionEmployment() {
        return directionEmployment;
    }

    public void setDirectionEmployment(String directionEmployment) {
        this.directionEmployment = directionEmployment;
    }
}
