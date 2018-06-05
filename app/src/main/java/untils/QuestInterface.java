package untils;

import java.util.List;
import java.util.Observable;


import base.BaseBean;
import bean.Advanced_YX_Bean;
import bean.AnswerBean;
import bean.AreaBean;
import bean.CXEFCBean;
import bean.CampusBean;
import bean.CanSchoolBean;
import bean.CheckSchoolBean;
import bean.CityBean;
import bean.CollerMajorBean;
import bean.CollerSchoolBean;
import bean.EFCBean;
import bean.EsayBaoGaoBean;
import bean.EsaySdsBean;
import bean.FavourMajorBean;
import bean.FingerpostBean;
import bean.ForecastBean;
import bean.GailvBean;
import bean.GradePolyBean;
import bean.HelpBean;
import bean.HotBean;
import bean.InquireBean;
import bean.JobInforBean;
import bean.LXBean;
import bean.LuquXianBean;
import bean.MajorBean;
import bean.MajorSchoolBean;
import bean.MajorgkBean;
import bean.MajorstatXQBean;
import bean.MinlineBean;
import bean.MoreJobBean;
import bean.NewsBean;
import bean.NumBean;
import bean.OneTableBean;
import bean.OneTableXQBean;
import bean.ProviceBean;
import bean.ProvinceBean;
import bean.RanKingSchoolBean;
import bean.RenSumBean;
import bean.SchoolBean;
import bean.SchoolBrochuresBean;
import bean.SchoolEnrollBean;
import bean.SchoolIntroduceBean;
import bean.ScoreBean1;
import bean.ScoreBean2;
import bean.SearchBean;
import bean.SelectMajorBean;
import bean.SelectSchoolBean;
import bean.SerchZYBean;
import bean.ShijianBean;
import bean.SlideshowBean;
import bean.StartFl;
import bean.StudentFromBean;
import bean.StudentsinBean;
import bean.StudentsinNewsBean;
import bean.StudyBean;
import bean.TeachBean;
import bean.TijiaoBean;
import bean.TitleBean;
import bean.TuiJianBean;
import bean.UserBean;
import bean.VisionBean;
import bean.WeiXinBean;
import bean.XDingdanBean;
import bean.XGcsBean;
import bean.ZDXKBean;
import bean.ZYNumBean;
import bean.ZYTJBean;
import bean.jobStarBean;
import bean.univCompareBean;
import io.reactivex.Flowable;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 地地 on 2017/11/12.
 * 邮箱：461211527@qq.com.
 */

public interface QuestInterface {
      //学生来源
     @GET(" /app/stufrom/from")
     Flowable<BaseBean<List<StudentFromBean>>> studentfrom(@Query("name") String name);



     //EFC学业规划报告
     @GET("/app/wishfilling/getEFCData")
     Flowable<BaseBean<EFCBean>> getEFCData(@Header("token") String token);


    //保存收藏的专业
    @POST("app/wishfilling/updateFavourMajors")
    Flowable<BaseBean> updateFavourMajors(@Query("major") String major,@Header("token") String token);

    //往年专业录取分数
    @GET("/app/universityinfo/Universityinfo")
    Flowable<BaseBean<List<NumBean>>> Universityinfo(@Query("name") String name, @Query("classify") String classify, @Query("stuProvince") String stuProvince);

    //查询一分钱资格
    @GET(" /app/wishfilling/yfJudge")
    Flowable<BaseBean> yfJudge(@Query("wishType") String wishType,@Header("token") String token);

    //推荐学校
    @GET("/app/efc/getRecommendCollege")
    Flowable<BaseBean<TuiJianBean>> getRecommendCollege(@Query("college") String college,
                                                        @Query("time") String time,
                                                        @Query("t_stuprovince") String t_stuprovince,
                                                        @Query("t_classify") String t_classify,
                                                        @Query("t_score") String t_score,
                                                        @Header("token") String token);

    //获取院校专业录取分数
    @GET("/app/universityinfo/UniversityinfoScores")
    Flowable<BaseBean<List<ZYNumBean>>> UniversityinfoScores(@Query("name") String name, @Query("classify") String classify, @Query("stuProvince") String stuProvince, @Query("major") String major);

    //获取MBTI简单分析报告
    @GET("/app/sdsmbti/getsdsE")
    Flowable<BaseBean<List<EsaySdsBean>>> getsdsE(@Query("testCode") String testCode);

    //获取MBTI简单分析报告
    @GET("/app/sdsmbti/getmbtiE")
    Flowable<BaseBean<List<EsayBaoGaoBean>>> getmbtiE(@Query("testCode") String testCode);
    //倒计时
    @GET(" /app/wishfilling/gettime")
    Flowable<BaseBean<String>> gettime(@Header("token") String token);

    //判断有没有使用EFC使用资格
    @GET(" /app/wishfilling/jzjudge")
    Flowable<BaseBean<String>> jzjudge(@Header("token") String token);



    //判断有无使用次数
    @GET(" /app/wishfilling/jznumber")
    Flowable<BaseBean> jznumber(@Header("token") String token);

    //历史报告
    @GET("/app/wishfilling/getHistoryInfo")
    Flowable<BaseBean<List<CXEFCBean>>>  getHistoryInfo(@Header("token") String token);

    //查看简单的历史报告
    @GET("/app/wishfilling/getHistoryInfo")
    Flowable<BaseBean<List<CXEFCBean>>> getHistoryInfonew(@Query("wishType") String wishType,@Query("token") String token);
    //查询当前EFC精准结果
    @GET("/app/wishfilling/getEFCResult")
    Flowable<BaseBean<CXEFCBean>> getEFCResult(@Query("token") String token);

    //一份一段表详情
    @GET("/app/onetoone/getonetoone")
    Flowable<BaseBean<List<OneTableXQBean>>> onetableXQ(@Query("type") String type, @Query("province") String province, @Query("year") String year);



    //职业星空
    @POST("/app/efc/jobsStarMobil")
    Flowable<BaseBean<List<StartFl>>> jobsStarMobil(@Query("classify") String classify,@Query("type") String type,@Query("fenlei") String fenlei);


    //提交心理测试答案
    @POST("/app/psychologicalevaluation/commitAnswer")
    @Headers("Content-Type:application/x-www-form-urlencoded;charset=utf-8")
    @FormUrlEncoded
    Flowable<TijiaoBean> tijiao(@Field("wishType") String wishType,@Field("answer") String answer, @Header("token") String token);

    //保存高考信息
    @POST("/app/userinfo/saveGaokaoInfo")
    Flowable<BaseBean> saveGaokaoInfo(@Query("examProvince") String examProvince,
                                  @Query("examScore") String examScore,
                                  @Query("examRank") String examRank,
                                  @Query("examYear") String examYear,
                                  @Query("stuType") String stuType,
                                  @Query("examBatch") String examBatch,
                                  @Header("token") String token);

    //星空专业详情
    @GET("/app/efc/jobStarInfo")
    Flowable<BaseBean<List<MajorstatXQBean>>> zyxq(@Query("major_id") String major_id);


    //心理测试题
    @GET("/app/psychologicalevaluation/query ")
    Flowable<BaseBean<List<AnswerBean>>> answer(@Query("pe_type") String pe_type);

 //版本更新
 @GET("app/version/getversion")
 Flowable<BaseBean<List<VisionBean>>> getversioninfo(@Query("versionType") String version_type);

 //成绩折线图
 @GET("/app/result/getUserResultPng")
 Flowable<BaseBean<List<GradePolyBean>>> getUserResultPng(@Query("testType") int testType, @Query("course") String course, @Header("token") String token );

    // 获取批次、录取概率、预测分
    @GET("/app/universitytimescore/getscoreCompareMobil")
    Flowable<BaseBean<ForecastBean>> getscoreCompareMobil(@Query("province") String province, @Query("classify") String classify, @Query("university") String university,@Query("score") String score);

    //一本录取率
  //  @GET("/app/universitytimescore/getscoreCompareMobil")
    //Flowable<BaseBean<List<GailvBean>>> getscoreCompareMobil(@Query("province") String province,@Query("classify") String classify,@Query("university") String university);




 //学校info
 @GET("/app/scientificteach/teach")
 Flowable<BaseBean<List<TeachBean>>> schoolteach(@Query("name") String name);
 //查询帮助和购买
 @GET("/app/help/helping")
 Flowable<BaseBean<List<HelpBean>>> helping(@Query("type") String type, @Query("pid") String pid);

    //招生简章
    @GET("/app/admissionplanmobile/adInfoMobil")
    Flowable<BaseBean<List<SchoolBrochuresBean>>> schoolbrochures(@Query("name") String name);

    //获取录取分数线
    @GET("/app/universitytimescore/scores")
    Flowable<BaseBean<List<LuquXianBean>>> getlqx(@Query("province") String province,@Query("university") String university,@Query("classify") String classify,@Query("time") String time,@Query("line") String line);

    //特长生艺考资讯
    @GET("/app/news/newsInfo")
    Flowable<BaseBean<StudentsinNewsBean>> studentsnews(@Query("category") String category, @Query("province") String  province, @Query("page") String  page, @Query("limit") String  limit);

    //查询专业是否收藏
    @GET("/app/major/getMajorThreeById")
    Flowable<BaseBean<List<CollerMajorBean>>> getiscollet(@Query("majorId") String majorid, @Header("token") String token);

    //大学录取专业招生计划
    @GET("/app/enrolmentinfo/num")
    Flowable<BaseBean<List<SchoolEnrollBean>>> schoolenroll(@Query("name") String name, @Query("province") String province, @Query("type") String type);

    //查询学校是否收藏
    @GET("/app/university/getUnivCollection")
    Flowable<BaseBean<List<CollerSchoolBean>>> getchoolisscollet(@Query("name") String name, @Query("token") String token);

    //获取订单号
    @POST("/app/productorder/getOutTradeInfo")
    Flowable<BaseBean<XDingdanBean>> productorder(@Query("token")String token,@Query("productId")String productId,@Query("payWay")String payWay,@Query("payType")String payType);

    //支付宝支付
    @POST("/alipay/appPay")
    Flowable<BaseBean<String>> ZFBpay(@Query("outTradeNo")String outTradeNo,@Query("payType")String payType);


    //提交职业
    @POST("/app/wishfilling/updateJobs")
    Flowable<BaseBean> tjzy(@Query("job")String job,@Query("token")String token);


    //获取EFC进度
    @GET("/app/wishfilling/getprocess")
    Flowable<BaseBean<String>> hqjd(@Header("token") String token);

    //保存专业
    @POST("/app/wishfilling/updateMajors")
    Flowable<BaseBean> bczy(@Query("majorGai")String majorGai,@Query("token")String token);


    // 保存精准筛选信息
    @POST("/app/wishfilling/update")
    Flowable<BaseBean> update(@Query("stuType") String stuType,
                              @Query("sourceArea") String sourceArea,
                              @Query("ceeScore") String ceeScore,
                              @Query("proRank") String proRank,
                              @Query("credit") String credit,
                              @Query("wishCity") String wishCity,
                              @Query("first") String first,
                              @Query("budget") String budget,
                              @Query("poorFlag") String poorFlag,
                              @Query("collegeClass") String collegeClass,
                              @Query("batch") String batch,
                              @Query("collegeArrangement")String collegeArrangement,
                              @Query("graduationDirection") String graduationDirection,
                              @Query("cwb") String cwb,
                              @Query("token")String token);

    //保存EFC心理测评结果
    @POST("/app/wishfilling/save")
    Flowable<BaseBean> save(@Query("wishType") String wishType,@Query("testCode") String testCode,@Query("token") String token);


    //提交专业
    @POST("/app/wishfilling/updateFavourMajors")
    Flowable<BaseBean<List<jobStarBean>>> tjzhuany(@Query("major")String major,@Header("token") String token );

    //高级志愿表筛选
    @POST("/app/efc/getEfcCollege")
    Call<BaseBean<List<Advanced_YX_Bean>>> shaixuan(@Query("priority") String priority,
                                                    @Query("time") String time,
                                                    @Query("tags") String tags,
                                                    @Query("type") String type,
                                                    @Query("city") String city,
                                                    @Query("direction") String direction,
                                                    @Query("tuition") String tuition,
                                                    @Query("t_stuprovince") String t_stuprovince,
                                                    @Query("t_classify") String t_classify,
                                                    @Query("t_score") String t_score,
                                                    @Query("cwb") String cwb,
                                                    @Query("major_gai") String major_gai,
                                                    @Query("token") String token);

    //搜索专业
    @GET("/app/search/major")
    Flowable<BaseBean<List<SerchZYBean>>> SerchZY(@Query("name") String name);

    // 获取MBTI复杂分析报告
    @GET("/app/sdsmbti/getmbti")
    Flowable<BaseBean<List<XGcsBean>>> getmbti(@Query("testCode") String testCode);

    //获取星空测评结合专业
    @GET("/app/efc/majorStars")
    Call<BaseBean<List<jobStarBean>>> jobsStarMajorMobil(@Header("token") String token);

    //系统时间
    @GET("/app/countdown/getsystemtime")
    Flowable<ShijianBean> getsystemtime();
    //微信支付
    @POST("/weixinMobile/appdopay")
    Flowable<BaseBean<WeiXinBean>> WXpay(@Query("outTradeNo")String outTradeNo,@Query("payType")String payType);

    //创建订单
    @POST("/app/productorder/createNewOrder ")
    Flowable<String> createNewOrder(
            @Query("body") String body,
            @Query("spbillCreateIp") String spbillCreateIp,
            @Query("attach") String attach,
            @Query("frontUrl") String frontUrl,
            @Query("token") String token,
            @Query("outTradeNo") String outTradeNo,
            @Query("productId") String productId,
            @Query("totalFee") String totalFee,
            @Query("payWay") String payWay,
            @Query("payType") String payType,
            @Query("subject") String subject
                                     );

    //职业详情
    @GET("/app/jobinfo/getjobinfo")
    Flowable<BaseBean<List<JobInforBean>>> getjobinfo(@Query("jobName") String jobName);

    //高考头条新闻接口
    @GET("/app/news/newsTopLine")
    Flowable<BaseBean<TitleBean>> TitleNews(@Query("page") String page, @Query("limit") String limit);


    //特长生艺术院校
    @GET("/app/school/check")
    Flowable<BaseBean<List<StudentsinBean>>> studentsschool(@Query("address") String address, @Query("schooltype") String  schooltype);

   //添加收藏

    @POST("/app/collection/insertCollection")
    Flowable<BaseBean> collect(@Query("type") String type,@Query("name") String name,@Header("token") String token);

    //一份一段表
    @GET("/app/onetoone/getallonetoone")
    Flowable<BaseBean<List<OneTableBean>>> onetable(@Query("type") String type, @Query("province") String province);

    //获取大学
    @GET("/app/university/univCompareOneMobil")
    Flowable<BaseBean<List<ScoreBean1>>> score1(@Query("province") String province, @Query("classify") String classify);

    //获取分数
    @GET("/app/universitytimescore/proscoreMobil")
    Flowable<BaseBean<List<ScoreBean2>>> score2(@Query("province") String province, @Query("university") String university);

    //省控线查询
    @GET("/app/universityprovincescore/proscoreMobil")
    Flowable<BaseBean<List<ProvinceBean>>> province(@Query("province") String province);


    //专业详情概况
    @GET("/app/major/getMajorIntroduction")
    Flowable<BaseBean<MajorgkBean>> getmajorgk(@Query("majorId") String majorId);

    //专业详情学院
    @GET("/app/major/getMajorSchoolByMajorId")
    Flowable<BaseBean<List<MajorSchoolBean>>> getmajorschool(@Query("majorId") String majorId,@Query("year") String year,@Query("page") int  page);

    //学习资料
    @GET("/app/studymaterials/querymaterials")
    Flowable<BaseBean<StudyBean>> study(@Query("category") String category, @Query("province") String province, @Query("subject") String subject, @Query("grade") String grade, @Query("page") String page, @Query("limit") String limit);
    //获取重点专业或特色专业
    @GET("/app/scientificteach/getTZmajor")
    Flowable<BaseBean<List<ZYTJBean>>> getTZmajor(@Query("name") String name, @Query("type") int type);



    //获取重点专业或重点实验室
    @GET("/app/scientificteach/getUnivImportant")
    Flowable<BaseBean<ZDXKBean>> getUnivImportant(@Query("flag") String flag, @Query("name") String name);

    //获取院校概况
    @GET("/app/universityinfo/UnInfoMobil")
    Flowable<BaseBean<List<CampusBean>>> UnInfoMobil(@Query("name") String name);

    //大学排序
    @GET("/app/hotlist/queryHot")
    Flowable<BaseBean<List<HotBean>>> queryHot();
    @POST("/app/hotlist/save")
    Flowable<BaseBean> hotsave(@Query("hot_name") String name);
    //院校库
    @GET("/app/school/searchCollege")
    Flowable<BaseBean<List<CheckSchoolBean>>> checkschool(@Query("address") String address, @Query("schooltype") String schooltype, @Query("two") String two, @Query("nine") String nine, @Query("plan") String plan, @Query("student") String student, @Query("recruit") String recruit, @Query("page") String page, @Query("limit") String limit);

    //大学排序
    @GET("/app/university/selUnivMobil")
    Flowable<BaseBean<RanKingSchoolBean>> ranking(@Query("page") int page, @Query("limit") int limit);

    //首页查询
    @GET("/app/search/majorCollege")
    Flowable<BaseBean<List<SearchBean>>> searchmajorCollege(@Query("name") String name);
    //倒计时
    @GET("/app/countdown/query")
    Flowable<BaseBean> Countdown();

    //专业库
    @GET("/app/major/getAllMajorForMoble")
    Flowable<BaseBean<List<SelectMajorBean>>> selectAllMajor(@Query("majorType") String majorType);

    //职业库
    @GET("/app/job/getAllJobForMobile")
    Flowable<BaseBean<List<MoreJobBean>>> selectAllJob();

    //轮播图接口
    @GET("/app/boardpicture/queryInfo")
    Flowable<BaseBean<List<SlideshowBean>>> Slideshow(@Query("board_id") int board_id);

    //新闻接口
    @GET("/app/news/newsInfo")
    Flowable<BaseBean<NewsBean>> News(@Query("category") String category, @Query("province") String province, @Query("page") String page, @Query("limit") String limit);

    //登录接口
    @POST("/app/userinfo/modifyUserinfo")
    Flowable<BaseBean> modifyUserinfo(@Body RequestBody requestBody );

    @POST("/app/loginByMobilePwd")
    @FormUrlEncoded
    Flowable<BaseBean<UserBean>> loginByMobilePwd(@Field("mobile") String mobile,@Field("password") String password);
    //获取登录验证码
    @GET("/app/mobileLoginCaptcha")
    Flowable<BaseBean> LogCaptcha(@Query("mobile") String mobile);
    //获取注册验证码
    @GET("/app/mobileRegisterCaptcha")
    Flowable<BaseBean> RegisterCaptcha(@Query("mobile") String mobile);
    //忘记密码验证码
    @GET("/app/mobileFindPwdCaptcha")
    Flowable<BaseBean> UpdateCaptcha(@Query("mobile") String mobile);
    //手机号验证码登录
    @POST("/app/MobileLoginByCaptcha")
    @FormUrlEncoded
    Flowable<BaseBean<UserBean>> phoneLogin(@Field("mobile") String mobile,@Field("captcha") String captcha);
    //找回密码
    @POST("/app/findPwdByMobile")
    @FormUrlEncoded
    Flowable<BaseBean> findPwdByMobile(@Field("mobile") String mobile,@Field("captcha") String captcha,@Field("newpassword") String newpasswprd);
    //注册接口
    @POST("/app/registerByMobile")
    @FormUrlEncoded
    Flowable<BaseBean<UserBean>> register(@Field("mobile") String mobile,@Field("password") String password,@Field("captcha") String captcha);


    //修改手机号验证码
    @GET("/app/mobileUpdateCaptcha")
    Flowable<BaseBean> mobileUpdateCaptcha(@Query("mobile") String mobile);


    //联系方式
    @GET("/app/university/getUnivContact")
    Flowable<BaseBean<List<LXBean>>> getUnivPhone(@Query("name") String name);


    //获取省份
    @POST("/app/provinces/prov")
    Flowable<BaseBean<List<ProviceBean>>> getprovinces();


    @POST("/alipay/mobilePay")
    @FormUrlEncoded
    io.reactivex.Observable<String> getpayhfive(
          /*  @Query("productId") String productId,
            @Query("subject") String subject,
            @Query("body") String body,
            @Query("totalFee") String totalFee,
            @Query("outTradeNo") String outTradeNo,
            @Query("spbillCreateIp") String spbillCreateIp,
            @Query("attach") String attach,
            @Query("payType") String payType,
            @Query("payWay") String payWay,
            @Query("frontUrl") String frontUrl,
            @Query("map") String map*/
    );
    //获取城市
    @GET("/app/cities/cityMobil")
    Flowable<BaseBean<List<CityBean>>> getcitys(@Query("provinceid") String provinceid);
    //获取城市byname
    @GET("/app/cities/getcitybyname")
    Flowable<BaseBean<List<CityBean>>> getcitybyname(@Query("province") String province);

    //喜欢的职业
    @GET("/app/efc/favourMajor")
    Flowable<BaseBean<FavourMajorBean>> favourMajor(@Header("token") String token);


    //获取最近一年的本科最低省控线
    @GET("/app/universityprovincescore/getMinline")
    Flowable<BaseBean<List<MinlineBean>>> getMinline(@Query("province") String province, @Query("classify") String classify);

    //获取大学批次
    @GET("/app/universitytimescore/univCompare21")
    Flowable<BaseBean<List<univCompareBean>>> univCompare(@Query("university") String university);

    //修改用户信息
    @POST("/app/userinfo/modifyUserinfoMoble")
    Flowable<BaseBean> modifyUserinfoMoble(@Query("provice") String provice,@Query("city") String city,@Query("area") String area,@Query("midSchool") String midSchool,@Query("grade") String grade,@Query("schoolClass") String schoolClass,@Query("name") String name,@Query("sex") String sex,@Query("examYear") String examYear,@Query("stuType") String stuType,@Query("isSpecial") boolean isSpecial,@Header("token") String token);

    //获取学校
    @GET("/app/highschool/selhighschoolMobil")
    Flowable<BaseBean<List<SelectSchoolBean>>> getschools(@Query("province") String province,@Query("city") String city,@Query("area") String area);







    //填写成绩表
    @POST("/app/result/insertUserResult")
    Flowable<BaseBean>  grade(@Query("testType")Integer testType,@Query("time")Integer time,@Query("chinese")Double chinese,@Query("math")Double math,@Query("languages")Double languages,@Query("physics")Double physics,@Query("chemistry")Double chemistry,@Query("biology")Double biology,@Query("history")Double history,@Query("geography")Double geography,@Query("politics")Double politics,@Query("specialty")Double specialty,@Header("token") String token);

    //查询成绩表
    @GET("/app/result/getResult")
    Flowable<BaseBean<InquireBean>>  inquiregrade(@Query("testType")Integer testType, @Query("testTime")Integer testTime, @Header("token") String token);

    //保存职业筛选条件
    @POST("/app/wishfilling/updateStuInfo")
    Flowable<BaseBean> updateStuInfo(@Query("ceeScore") String ceeScore,@Query("name") String name,@Query("stuType") String stuType,@Query("collegeType") String collegeType,@Query("gender") String gender,@Query("sourceArea") String sourceArea,@Query("city") String city,@Query("token") String token);


    //能上的学校
    @GET("/app/universitytimescore/timescoreMobil")
    Flowable<BaseBean<CanSchoolBean>>  canschool(@Query("province")String province, @Query("classify")String classify, @Query("score_min") String score_min, @Query("score_max") String score_max, @Query("page") String page, @Query("limit") String limit);


 //能上的学校
 @GET("/app/major/getUniversity")
 Flowable<BaseBean<CanSchoolBean>>  completecanschool(@Query("score_min")String minScore,
                                                      @Query("score_max")String maxScore,
                                                      @Query("cityType") String cityType,
                                                      @Query("isAccept") String isAccept,
                                                      @Query("schoolType") String schoolType,
                                                      @Query("province") String province,
                                                      @Query("classify") String classify
                                                     );




    //校园生活
    @GET("/app/schoollife/lifeMobil ")
    Flowable<BaseBean<List<CampusBean>>> campus(@Query("name") String name);

    //报考指南
    @GET("/app/guidemobil/guiMobil")
    Flowable<BaseBean<List<FingerpostBean>>> fingerpost(@Query("name") String name);

    //校园介绍
    @GET("/app/universityinfomobile/UnInfoMobil")
    Flowable<BaseBean<List<SchoolIntroduceBean>>> schoolIntroduce(@Query("name") String name);

 //获取曲县
    @GET("/app/areas/areaMobil")
    Flowable<BaseBean<List<AreaBean>>> getareas(@Query("cityid") String cityid);

    //查询学校收藏
    @GET("/app/collection/getCollection")
    Flowable<BaseBean<List<SchoolBean>>> getCollection(@Query("type") int type, @Header("token") String token);


    //查询学校收藏
    @GET("/app/collection/getCollection")
    Flowable<BaseBean<List<MajorBean>>>   getmajorCollection(@Query("type") int type, @Header("token") String token);

    //志愿表轮播图
    @GET("/app/boardpicture/queryInfo")
    Flowable<BaseBean<List<SlideshowBean>>> Wish(@Query("board_id") int board_id);

    //提交建议的接口
    @POST("/app/proposal/pro")
    @Headers("Content-Type:application/x-www-form-urlencoded; charset=utf-8")
    @FormUrlEncoded
    Flowable<BaseBean> suggest(@Field("proposal") String proposal,@Field("contactInformation") String contactInformation );

    //返回服务人数
    @GET("/app/countdown/getServicenum")
    Flowable<RenSumBean> getServicenum();
    //获取用户信息
    @POST("/app/userinfo/getUserinfo")
    Flowable<BaseBean<UserBean>> getUserinfo(@Header("token") String token);









    //验证token超时
    @POST("/app/refreshToken")
    Flowable<BaseBean> checkToken(@Header("token") String token);

    @POST("/app/updateMobileVerifyOld")
    Flowable<BaseBean> updateMobileVerifyOld(@Query("mobile")String mobile,@Query("captcha")String captcha,@Header("token") String token);


    @POST("/app/updateMobile")

    Flowable<BaseBean>  updateMobile(@Query("newmobile")String mobile,@Query("captcha")String captcha,@Header("token") String token);


/*
    @GET("/quarter/getJokes")
    Observable<Basebean<List<Duanzibean>>> getdata(@Query("page") int page);

    @POST("user/getUserInfo")
    @FormUrlEncoded
    Observable<Basebean<UserBean>> getuser(@FieldMap Map<String, Object> maps);



    @Multipart
    @POST("/quarter/publishJoke")
    Observable<Basebean> faduanzi(@Part() List<MultipartBody.Part> file);

    @Multipart
    @POST("/file/upload")
    Observable<Basebean> changeicon(@Part() List<MultipartBody.Part> file);


    @POST("/user/updateNickName")
    @FormUrlEncoded
    Observable<Basebean> xiunicheng(@Field("uid") int uid, @Field("nickname") String nickname);

    @GET("/quarter/getAd")
    Observable<Basebean<List<Guanggao>>> getad();


    @GET("/quarter/getVideos")
    Observable<Basebean<List<TuijianBean>>> gettuijian(@Query("uid") String uid, @Query("type") int type, @Query("page") int page);


    @Multipart
    @POST("/quarter/publishVideo")
    Observable<Basebean> upvideo(@Part() List<MultipartBody.Part> file);

    @GET("/quarter/getHotVideos")
    Observable<Basebean<List<TuijianBean>>> spremen(@Query("page") int page);


    @POST("/quarter/getUserVideos")
    @FormUrlEncoded
    Flowable<Basebean<List<TuijianBean>>> getuserdata(@Field("uid") int uid, @Field("page") int page);

     @GET("/quarter/getVersion")
     Flowable<Basebean<VisionBean>> upvision();

    @GET("/quarter/follow")
    Flowable<Basebean> guanzhu(@Query("uid") int uid, @Query("followId") String followId);

     @GET("/quarter/getFollowUsers")
     Flowable<Basebean<List<GuanzhuBean>>> Iguanzhudata(@Query("uid") int uid);

    @GET("/quarter/searchFriends")
    Flowable<Basebean<List<GuanzhuBean>>> sousuo(@Query("keywords") String keywords, @Query("page") String page);

    @GET("/quarter/getVideoDetail")
    Flowable<Basebean<TuijianBean>> Xiangqing(@Query("wid") int wid);

    @POST("/quarter/comment")
    @FormUrlEncoded
    Flowable<Basebean> pinglun(@FieldMap Map<String, Object> maps);*/


}