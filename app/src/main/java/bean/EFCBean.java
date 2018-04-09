package bean;

import java.util.List;

/**
 * Created by 地地 on 2018/4/8.
 * 邮箱：461211527@qq.com.
 */

public class EFCBean {


    /**
     * sds_code : EAR:A5:C4:E5:I3:S3:R4
     * majorGai : ["光伏材料制备技术:0.7654:0.8700000047683716:530605:暂无数据:1:暂无数据","高分子材料工程技术:0.7654:0.8700000047683716:530602:暂无数据:1:暂无数据","材料工程技术:0.736:0.8:530601:暂无数据:1:暂无数据","市场营销:0.6687:0.6399999856948853:630701:暂无数据:1:暂无数据","网络营销:0.6687:0.6399999856948853:630803:暂无数据:1:暂无数据","中小企业创业与经营:0.6687:0.6399999856948853:630607:暂无数据:1:暂无数据","汽车营销与服务:0.6687:0.6399999856948853:630702:暂无数据:1:暂无数据","建筑经济管理:0.5722:0.41:540503:暂无数据:1:暂无数据","物业管理:0.5722:0.41:540703:暂无数据:1:暂无数据","工业分析技术:0.5722:0.41:570207:暂无数据:1:暂无数据","卫生监督:0.5722:0.41:620603:暂无数据:1:暂无数据","税务:0.5722:0.41:630102:暂无数据:1:暂无数据","政府采购管理:0.5722:0.41:630104:暂无数据:1:暂无数据","国际金融:0.5722:0.41:630202:暂无数据:1:暂无数据","保险:0.5722:0.41:630205:暂无数据:1:暂无数据","信用管理:0.5722:0.41:630207:暂无数据:1:暂无数据","会计:0.5722:0.41:630302:暂无数据:1:暂无数据","会计信息管理:0.5722:0.41:630304:暂无数据:1:暂无数据","统计与会计核算:0.5722:0.41:630402:暂无数据:1:暂无数据","国际经济与贸易:0.5722:0.41:630502:暂无数据:1:暂无数据"]
     * test_code : INFP:E5:S9:T8:J5:I16:N17:F16:P17,EAR:A5:C4:E5:I3:S3:R4
     * mbti_code : INFP:E5:S9:T8:J5:I16:N17:F16:P17
     */

    private String sds_code;
    private String test_code;
    private String mbti_code;
    private List<String> majorGai;

    public String getSds_code() {
        return sds_code;
    }

    public void setSds_code(String sds_code) {
        this.sds_code = sds_code;
    }

    public String getTest_code() {
        return test_code;
    }

    public void setTest_code(String test_code) {
        this.test_code = test_code;
    }

    public String getMbti_code() {
        return mbti_code;
    }

    public void setMbti_code(String mbti_code) {
        this.mbti_code = mbti_code;
    }

    public List<String> getMajorGai() {
        return majorGai;
    }

    public void setMajorGai(List<String> majorGai) {
        this.majorGai = majorGai;
    }
}
