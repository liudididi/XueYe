package bean;

/**
 * Created by 祝文 on 2018/3/16.
 */

public class SerchZYBean {

    /**
     * introduce :
     <p>
     <strong>专业培养目标：</strong>本专业培养具有一定马克思主义哲学理论素养和系统的专业基础知识，有进一步培养潜质的哲学专门人才，以及能在国家机关、文教事业、新闻出版、企业等部门从事实际工作的应用型、复合型高级专门人才。</p>
     <p>
     <strong>专业培养要求：</strong>本专业学生主要学习马克思主义的基本理论与历史，以及社会科学、自然科学和思维科学的基础知识，受到中西方哲学的基本理论和发展线索的系统教育，以及创造性思维的培养和业务能力的训练。</p>
     <p>
     <strong>毕业生应获得以下几方面的知识和能力：</strong><br>
     1.比较系统地掌握马克思主义哲学、中国哲学和西方哲学的理论和历史；<br>
     2.具有一定的社会科学、人文科学、自然科学、思维科学的相关知识；<br>
     3.掌握哲学学科的基本研究方法、治学方法和相应的社会调查能力；<br>
     4.了解国内外哲学界最重要的理论前沿和发展动态；<br>
     5.了解国内外最重大的实践问题和发展动态；<br>
     6.具有分析和解决社会现实问题的初步能力。</p>
     <p>
     <strong>主干学科：</strong>哲学</p>
     <p>
     <strong>主要课程：</strong>哲学概论、马克思主义哲学原理、中国哲学史、西方哲学史、科学技术哲学、伦理学、宗教学、美学、逻辑学、心理学、中外哲学原著导读等。</p>
     <p>
     <strong>主要实践性教学环节：</strong>包括社会实习、社会调查、社会公益活动等，一般安排10周左右。</p>
     <p>
     <strong>修业年限：</strong>四年</p>
     <p>
     <strong>授予学位：</strong>哲学学士</p>


     * name : 哲学
     * id : 010101
     * major_type : 0
     */

    private String introduce;
    private String name;
    private String id;
    private int major_type;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMajor_type() {
        return major_type;
    }

    public void setMajor_type(int major_type) {
        this.major_type = major_type;
    }
}
