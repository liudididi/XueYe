package bean;

/**
 * Created by 祝文 on 2018/1/28.
 */

public class CanSchoolBean3 {
    private String imgurl;
    private String name;
    private String address;
    private String father;

    private String two;
    private String nine;
    private String defense_student;
    private String graduate;
    private String recruit;
    private String preeminent_plan;
    private String com_rank;

    @Override
    public String toString() {
        return "CanSchoolBean3{" +
                "imgurl='" + imgurl + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", father='" + father + '\'' +
                ", two='" + two + '\'' +
                ", nine='" + nine + '\'' +
                ", defense_student='" + defense_student + '\'' +
                ", graduate='" + graduate + '\'' +
                ", recruit='" + recruit + '\'' +
                ", preeminent_plan='" + preeminent_plan + '\'' +
                ", com_rank='" + com_rank + '\'' +
                '}';
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public void setNine(String nine) {
        this.nine = nine;
    }

    public void setDefense_student(String defense_student) {
        this.defense_student = defense_student;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public void setRecruit(String recruit) {
        this.recruit = recruit;
    }

    public void setPreeminent_plan(String preeminent_plan) {
        this.preeminent_plan = preeminent_plan;
    }

    public void setCom_rank(String com_rank) {
        this.com_rank = com_rank;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getFather() {
        return father;
    }

    public String getTwo() {
        return two;
    }

    public String getNine() {
        return nine;
    }

    public String getDefense_student() {
        return defense_student;
    }

    public String getGraduate() {
        return graduate;
    }

    public String getRecruit() {
        return recruit;
    }

    public String getPreeminent_plan() {
        return preeminent_plan;
    }

    public String getCom_rank() {
        return com_rank;
    }

    public CanSchoolBean3(String imgurl, String name, String address, String father, String two, String nine, String defense_student, String graduate, String recruit, String preeminent_plan, String com_rank) {
        this.imgurl = imgurl;
        this.name = name;
        this.address = address;
        this.father = father;
        this.two = two;
        this.nine = nine;
        this.defense_student = defense_student;
        this.graduate = graduate;
        this.recruit = recruit;
        this.preeminent_plan = preeminent_plan;
        this.com_rank = com_rank;
    }
}
