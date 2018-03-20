package com.example.login_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.alipay.sdk.widget.a;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.ChineseMedicineReportAdapter;
import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import bean.AnswerBean;
import bean.TijiaoBean;
import butterknife.BindView;
import butterknife.OnClick;
import moudle.EFCJGBacCunMoudle;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import presenter.AnswerPresent;
import presenter.EFCJGBaoCunPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.QuestInterface;
import untils.Question;
import untils.SPUtils;
import view.AnswerView;
import view.EFCJGBaoCunView;

import static com.alipay.sdk.widget.a.a;

public class AnswerActivity extends BaseActivity implements GestureDetector.OnGestureListener, EFCJGBaoCunView{

    @BindView(R.id.viewFlipper)
    ViewFlipper viewFlipper;
    @BindView(R.id.answer_pb)
    ProgressBar answer_pb;

    @BindView(R.id.tv_leixing)
    TextView tv_leixing;
    //1.定义手势检测器对象
    GestureDetector mGestureDetector;
    //2.定义一个动画数组，用于为ViewFilpper指定切换动画效果。
    Animation[] animations = new Animation[4];
    //3.定义手势两点之间的最小距离
    final int FLIP_DISTANCE = 50;
    List<Question> mQuestion = new ArrayList<>();
    ChineseMedicineReportAdapter adapter;
    @BindView(R.id.answer_rl_iv)
    ImageView answerRlIv;
    private TextView bt;
    private AnswerPresent answerPresent;
    private List<AnswerBean> data;
    private String XX=null;
    private HashMap<String, String> map;
    //TODO 这是个变量
    private static String type="MBTI";
    private EFCJGBaoCunPresenter efcjgBaoCunPresenter;
    private String token;

    @Override
    public int getId() {
        return R.layout.activity_answer;
    }

    @Override
    public void InIt() {
        token = (String) SPUtils.get(MyApp.context, "token", "");

        String ceshi = getIntent().getStringExtra("ceshi");
         if(ceshi!=null){
             type=ceshi;
         }
        //1.构建手势检测器
        mGestureDetector = new GestureDetector(this, this);
        if(type.equals("MBTI"))
        {
            tv_leixing.setText("MBTI特质测试");
        }
        if(type.equals("SDS"))
        {
            tv_leixing.setText("霍兰德兴趣特质测试");
        }
        efcjgBaoCunPresenter = new EFCJGBaoCunPresenter(this);
        //2准备数据
        initData();
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

        return false;
    }

    private void initData() {

        map = new HashMap<String,String>();
        final List<Question> questions = new ArrayList<>();
        answerPresent = new AnswerPresent(new AnswerView() {
            @Override
            public void Answersuccess(BaseBean<List<AnswerBean>> listBaseBean) {
                answer_pb.setVisibility(View.GONE);
                data = listBaseBean.data;
                if(data !=null&& data.size()>0)
                {
                    if(type.equals("SDS_E"))
                    {
                        for (int i = 0; i < data.size(); i++) {
                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            String peBody = answerBean.getPeBody();
                            String peId = answerBean.getPeId();
                            q.setPeId(peId);
                            q.setQuestion(peBody);
                            List<Question.Answer> mA = new ArrayList<>();
                            Question.Answer a1 = new Question.Answer();
                            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
                            Question.Answer a2 = new Question.Answer();
                            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
                            mA.add(a1);
                            mA.add(a2);
                            q.setAnswer(mA);
                            questions.add(q);
                        }
                    }
                    if(type.equals("MBTI_E"))
                    {
                        for (int i = 1; i < data.size(); i++) {
                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            String peBody = answerBean.getPeBody();
                            String peId = answerBean.getPeId();
                            q.setPeId(peId);
                            q.setQuestion(peBody);
                            List<Question.Answer> mA = new ArrayList<>();
                            Question.Answer a1 = new Question.Answer();
                            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
                            Question.Answer a2 = new Question.Answer();
                            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
                            mA.add(a1);
                            mA.add(a2);
                            q.setAnswer(mA);
                            questions.add(q);
                        }
                    }

                    if(type.equals("MBTI"))
                    {
                        for (int i = 0; i < data.size(); i++) {
                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            if(answerBean.getPeTitle().equals("t"))
                            {
                                String peBody = answerBean.getPeBody();
                                String peId = answerBean.getPeId();
                                 q.setQuestion(peBody);
                                q.setPeId(peId);
                                List<Question.Answer> mA = new ArrayList<>();
                                Question.Answer a1 = new Question.Answer();
                                a1.setAnswerMessage("A:"+answerBean.getAnswerA());
                                Question.Answer a2 = new Question.Answer();
                                a2.setAnswerMessage("B:"+answerBean.getAnswerB());
                                mA.add(a1);
                                mA.add(a2);
                                q.setAnswer(mA);
                                questions.add(q);
                            }

                        }
                    }
                    if(type.equals("SDS"))
                    {
                        for (int i = 1; i < data.size(); i++) {
                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                                String peBody = answerBean.getPeBody();
                                String peId = answerBean.getPeId();
                                q.setQuestion(peBody);
                                q.setPeId(peId);
                                List<Question.Answer> mA = new ArrayList<>();
                                Question.Answer a1 = new Question.Answer();
                                a1.setAnswerMessage("A:"+answerBean.getAnswerA());
                                Question.Answer a2 = new Question.Answer();
                                a2.setAnswerMessage("B:"+answerBean.getAnswerB());
                                mA.add(a1);
                                mA.add(a2);
                                q.setAnswer(mA);
                                questions.add(q);


                        }
                    }

                    mQuestion.addAll(questions);
                    //3.为ViewFilpper添加子控件。
                    for (int i = 0; i < mQuestion.size(); i++) {
                        Question question = mQuestion.get(i);
                        viewFlipper.addView(addQuestionView(question));
                    }
                    //4.初始化Animation数组
                    animations[0] = AnimationUtils.loadAnimation(AnswerActivity.this, R.anim.left_in);
                    animations[1] = AnimationUtils.loadAnimation(AnswerActivity.this, R.anim.left_out);
                    animations[2] = AnimationUtils.loadAnimation(AnswerActivity.this, R.anim.right_in);
                    animations[3] = AnimationUtils.loadAnimation(AnswerActivity.this, R.anim.right_out);
                }
            }
            @Override
            public void Answerfail(Throwable t) {

            }
        });
        answerPresent.AnswerPresent(type);
    }

    private View addQuestionView(final Question question) {
        View view = View.inflate(this, R.layout.activity_chnihealthreport, null);
        TextView tes =view.findViewById(R.id.tv_question);
        ListView listview =view.findViewById(R.id.lv_question_answer);
        final TextView tv_dq= view.findViewById(R.id.tv_dq);
        TextView tv_zong = view.findViewById(R.id.tv_zong);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);

        bt = view.findViewById(R.id.tv_shang);
        adapter = new ChineseMedicineReportAdapter(this, question);
        listview.setAdapter(adapter);
        tes.setText(question.getQuestion());
        if(type.equals("SDS_E"))
        {
            tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(data.size()+"");
            progressBar.setMax(data.size());
            progressBar.setProgress(Integer.parseInt(question.getPeId()));
        }
        if(type.equals("MBTI_E"))
        {
            tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(data.size()-1+"");
            progressBar.setMax(data.size()-1);
            progressBar.setProgress(Integer.parseInt(question.getPeId()));
        }
        if(type.equals("MBTI"))
        {
            tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(data.size()-4+"");
            progressBar.setMax(data.size()-4);
            progressBar.setProgress(Integer.parseInt(question.getPeId()));
        }
        if(type.equals("SDS"))
        {
            tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(data.size()-1+"");
            progressBar.setMax(data.size()-1);
            progressBar.setProgress(Integer.parseInt(question.getPeId()));
        }
        adapter.setItemOnClick(new ChineseMedicineReportAdapter.ItemOnClick() {
            @Override
            public void SX(View view, final int position) {
                TextView tv_answer=view.findViewById(R.id.tv_answer);
                tv_answer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //选项
                        //Toast.makeText(AnswerActivity.this, position + "", Toast.LENGTH_SHORT).show();
                        if(position==0)
                        {
                           // Toast.makeText(AnswerActivity.this,   "A", Toast.LENGTH_SHORT).show();
                            XX="A";
                        }
                        if(position==1)
                        {
                            XX="B";
                            //Toast.makeText(AnswerActivity.this,   "B", Toast.LENGTH_SHORT).show();
                        }
                        map.put(question.getPeId()+"",XX);
                        if (viewFlipper.getDisplayedChild() == mQuestion.size() - 1) {
                            //Toast.makeText(AnswerActivity.this, "最后一个题", Toast.LENGTH_SHORT).show();
                            //得到MBTI测试得到的结果
                            if(type.equals("MBTI"))
                            {
                                jiexi();
                                 return;
                            }
                            //得到SDS测试得到的结果
                            if(type.equals("SDS"))
                            {
                                jiexi();
                                startActivity(new Intent(AnswerActivity.this,EvaluatingActivity.class));
                                finish();
                                return;
                            }
                            //得到MBTI_E测试得到的结果
                            if(type.equals("MBTI_E"))
                            {
                                 jiexi();
                                return;
                            }
                            //得到MBTI_E测试得到的结果
                            if(type.equals("SDS_E"))
                            {
                                jiexi();
                                return;
                            }

                        } else {
                            viewFlipper.setInAnimation(animations[0]);
                            viewFlipper.setOutAnimation(animations[1]);
                            viewFlipper.showNext();
                            //Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewFlipper.getDisplayedChild() == 0) {
                    viewFlipper.stopFlipping();
                   // Toast.makeText(AnswerActivity.this, "第一个题", Toast.LENGTH_SHORT).show();
                    return;
                }
                viewFlipper.setInAnimation(animations[2]);
                viewFlipper.setOutAnimation(animations[3]);
                viewFlipper.showPrevious();
                //Toast.makeText(AnswerActivity.this, "上一题", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    //解析数据
    public void jiexi() {
        viewFlipper.stopFlipping();
        Gson gson=new Gson();
        String route= gson.toJson(map);
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseApi.Api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestInterface questInterface = retrofit.create(QuestInterface.class);
        RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        Call<BaseBean<TijiaoBean>> tijiao = questInterface.tijiao(type, body);
        tijiao.enqueue(new Callback<BaseBean<TijiaoBean>>() {
            @Override
            public void onResponse(Call<BaseBean<TijiaoBean>> call, Response<BaseBean<TijiaoBean>> response) {
                TijiaoBean shuju = response.body().data;
                if(type.equals("MBTI_E"))
                {
                    String resultStr = shuju.getResultStr();
                    TijiaoBean.ResultBean result = shuju.getResult();
                    int e = result.getE();
                    int s = result.getS();
                    int t = result.getT();
                    int j = result.getJ();
                    int i = result.getI();
                    int n = result.getN();
                    int f = result.getF();
                    int p = result.getP();
                    String mbtiString = resultStr+":"+"E"+e+":"+"S"+s+":"+"T"+t+":"+"J"+j+":"+"I"+i+":"+"N"+n+":"+"F"+f+":"+"P"+p;
                    System.out.println("MBTI_E++"+mbtiString);

                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("MBTI_E",mbtiString,token);
                    Intent intent=new Intent(AnswerActivity.this,MBI_CSActivity.class);
                    startActivity(intent);
                     return;
                }
                if(type.equals("SDS_E"))
                {
                    String resultStr = shuju.getResultStr();
                    TijiaoBean.ResultBean result = shuju.getResult();
                    int a = result.getA();
                    int c = result.getC();
                    int e = result.getE();
                    int i = result.getI();
                    int s = result.getS();
                    int r = result.getR();
                    String mbtiString =resultStr+":"+"A"+a +":"+"C"+c+":"+"E"+e+":"+"I"+i+":"+"S"+s+":"+"R"+r;
                    System.out.println("SDS_E++"+mbtiString);
                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("SDS_E",mbtiString,token);
                    //TODO 跳转到霍兰德报告界面
                    return;
                }
                if(type.equals("MBTI"))
                {
                    String resultStr = shuju.getResultStr();
                    TijiaoBean.ResultBean result = shuju.getResult();
                    int e = result.getE();
                    int s = result.getS();
                    int t = result.getT();
                    int j = result.getJ();
                    int i = result.getI();
                    int n = result.getN();
                    int f = result.getF();
                    int p = result.getP();
                    String mbtiString = resultStr+":"+"E"+e+":"+"S"+s+":"+"T"+t+":"+"J"+j+":"+"I"+i+":"+"N"+n+":"+"F"+f+":"+"P"+p;
                     type="SDS";
                    SharedPreferences mbti = getSharedPreferences("mbti", MODE_PRIVATE);
                    SharedPreferences.Editor edit = mbti.edit();
                    edit.putString("mb",mbtiString);
                    edit.commit();
                    intent(AnswerActivity.this,AnswerActivity.class);
                    return;
                }
                if(type.equals("SDS"))
                {
                    String resultStr = shuju.getResultStr();
                    TijiaoBean.ResultBean result = shuju.getResult();
                    int a = result.getA();
                    int c = result.getC();
                    int e = result.getE();
                    int i = result.getI();
                    int s = result.getS();
                    int r = result.getR();
                    SharedPreferences mbti = getSharedPreferences("mbti", MODE_PRIVATE);
                    String mb = mbti.getString("mb", "");
                    String mbtiString =mb+","+resultStr+":"+"A"+a +":"+"C"+c+":"+"E"+e+":"+"I"+i+":"+"S"+s+":"+"R"+r;
                    System.out.println("SDS++"+mbtiString);
                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("精准",mbtiString,token);
                    return;
                }

            }

            @Override
            public void onFailure(Call<BaseBean<TijiaoBean>> call, Throwable t) {

            }
        });
     }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将Activity上的触发的事件交个GestureDetector处理
        return this.mGestureDetector.onTouchEvent(event);
    }
    @OnClick(R.id.answer_rl_iv)
    public void onViewClicked() {
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        answerPresent.onDestory();
        efcjgBaoCunPresenter.onDestory();
    }

    @Override
    public void Savesuccess(BaseBean baseBean) {
        String msg = baseBean.msg;
        System.out.println("成功++"+msg);
    }

    @Override
    public void Savefail(Throwable t) {
        System.out.println("失败++"+t);

    }
}
