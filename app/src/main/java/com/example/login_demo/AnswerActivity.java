package com.example.login_demo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.GestureDetector;
import android.view.LayoutInflater;
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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import adapter.ChineseMedicineReportAdapter;
import base.BaseActivity;
import base.BaseApi;
import base.BaseBean;
import bean.AnswerBean;
import bean.RenSumBean;
import bean.TijiaoBean;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.RequestBody;
import presenter.AnswerPresent;
import presenter.EFCJGBaoCunPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import untils.Dianji;
import untils.MyQusetUtils;
import untils.QuestInterface;
import untils.Question;
import untils.SPUtils;
import view.AnswerView;
import view.EFCJGBaoCunView;

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

    private  String type="SDS+MBTI";
    private EFCJGBaoCunPresenter efcjgBaoCunPresenter;
    private String token;
    private String pd;
    private String SDSpd;
    @Override
    public int getId() {
        return R.layout.activity_answer;
    }

    @Override
    public void InIt() {
        token = (String) SPUtils.get(MyApp.context, "token", "");
      /*  String ceshi = getIntent().getStringExtra("ceshi");
         if(ceshi!=null){
             type=ceshi;
         }*/
        //1.构建手势检测器
        mGestureDetector = new GestureDetector(this, this);
     /*   if(type.equals("MBTI"))
        {
            tv_leixing.setText("MBTI特质测试");
        }
        if(type.equals("SDS"))
        {
            tv_leixing.setText("霍兰德兴趣特质测试");
        }*/
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
                   /* if(type.equals("SDS_E"))
                    {

                        for (int i = 0; i < data.size(); i++) {

                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            if(answerBean.getPeTitle().equals("t"))
                            {

                                String peBody = answerBean.getPeBody();
                                String peId = answerBean.getPeId();
                                int answerUnm = answerBean.getAnswerUnm();
                                q.setAnswerUnm(answerUnm);
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
                    }
                    if(type.equals("MBTI_E"))
                    {
                        for (int i = 1; i < data.size(); i++) {

                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            if(answerBean.getPeTitle().equals("t"))
                            {
                                String peBody = answerBean.getPeBody();
                                String peId = answerBean.getPeId();
                                int answerUnm = answerBean.getAnswerUnm();
                                q.setAnswerUnm(answerUnm);
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
                                int answerUnm = answerBean.getAnswerUnm();
                                int torder = answerBean.getTorder();
                                q.setTorder(torder);
                                q.setQuestion(peBody);
                                q.setPeId(peId);
                                q.setAnswerUnm(answerUnm);

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
                            else
                            {
                                ShiTi(answerBean,q,questions);
                            }
                        }
                    }
                    if(type.equals("SDS"))
                    {
                        for (int i = 0; i < data.size(); i++) {
                            Question q = new Question();
                            AnswerBean answerBean = data.get(i);
                            if(answerBean.getPeTitle().equals("t")) {
                                String peBody = answerBean.getPeBody();
                                String peId = answerBean.getPeId();
                                int answerUnm = answerBean.getAnswerUnm();
                                int torder = answerBean.getTorder();
                                q.setTorder(torder);
                                q.setQuestion(peBody);
                                q.setPeId(peId);
                                q.setAnswerUnm(answerUnm);
                                List<Question.Answer> mA = new ArrayList<>();
                                Question.Answer a1 = new Question.Answer();
                                a1.setAnswerMessage("A:" + answerBean.getAnswerA());
                                Question.Answer a2 = new Question.Answer();
                                a2.setAnswerMessage("B:" + answerBean.getAnswerB());
                                mA.add(a1);
                                mA.add(a2);
                                q.setAnswer(mA);
                                questions.add(q);
                            }
                            else
                            {
                                ShiTi(answerBean,q,questions);
                            }
                        }
                    }*/

                    for (int i = 0; i < data.size(); i++) {
                        Question q = new Question();
                        AnswerBean answerBean = data.get(i);
                        if(answerBean.getPeTitle().equals("t")) {
                            String peBody = answerBean.getPeBody();
                            String peId = answerBean.getPeId();
                            int answerUnm = answerBean.getAnswerUnm();
                            int torder = answerBean.getTorder();
                            q.setTorder(torder);
                            q.setQuestion(peBody);
                            q.setPeId(peId);
                            q.setAnswerUnm(answerUnm);
                            List<Question.Answer> mA = new ArrayList<>();
                            Question.Answer a1 = new Question.Answer();
                            a1.setAnswerMessage("A:" + answerBean.getAnswerA());
                            Question.Answer a2 = new Question.Answer();
                            a2.setAnswerMessage("B:" + answerBean.getAnswerB());
                            mA.add(a1);
                            mA.add(a2);
                            q.setAnswer(mA);
                            questions.add(q);
                        }
                        else
                        {
                            ShiTi(answerBean,q,questions);
                        }
                    }
                    mQuestion.addAll(questions);
                    //3.为ViewFilpper添加子控件。
                    for (int i = 0; i < mQuestion.size(); i++) {
                        Question question = mQuestion.get(i);
                        if(question!=null)
                        {
                            viewFlipper.addView(addQuestionView(question));
                        }
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
                answerPresent.AnswerPresent(type);
            }
        });
        answerPresent.AnswerPresent(type);
    }

    private void ShiTi(AnswerBean answerBean,Question q, List<Question> questions) {
        String peBody = answerBean.getPeBody();
        String peId = answerBean.getPeId();
        int answerUnm = answerBean.getAnswerUnm();
        int torder = answerBean.getTorder();
        q.setTorder(torder);
        q.setQuestion(peBody);
        q.setPeId(peId);
        q.setAnswerUnm(answerUnm);
        List<Question.Answer> mA = new ArrayList<>();
        if(answerUnm==1)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
        }
        if(answerUnm==2)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
            Question.Answer a2 = new Question.Answer();
            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
            mA.add(a2);
        }
        if(answerUnm==3)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
            Question.Answer a2 = new Question.Answer();
            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
            mA.add(a2);
            Question.Answer a3 = new Question.Answer();
            a3.setAnswerMessage("C:"+answerBean.getAnswerC());
            mA.add(a3);
        }
        if(answerUnm==4)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
            Question.Answer a2 = new Question.Answer();
            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
            mA.add(a2);
            Question.Answer a3 = new Question.Answer();
            a3.setAnswerMessage("C:"+answerBean.getAnswerC());
            mA.add(a3);
            Question.Answer a4 = new Question.Answer();
            a4.setAnswerMessage("D:"+answerBean.getAnswerD());
            mA.add(a4);
        }
        if(answerUnm==5)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
            Question.Answer a2 = new Question.Answer();
            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
            mA.add(a2);
            Question.Answer a3 = new Question.Answer();
            a3.setAnswerMessage("C:"+answerBean.getAnswerC());
            mA.add(a3);
            Question.Answer a4 = new Question.Answer();
            a4.setAnswerMessage("D:"+answerBean.getAnswerD());
            mA.add(a4);
            Question.Answer a5 = new Question.Answer();
            a5.setAnswerMessage("E:"+answerBean.getAnswerE());
            mA.add(a5);
        }
        if(answerUnm==6)
        {
            Question.Answer a1 = new Question.Answer();
            a1.setAnswerMessage("A:"+answerBean.getAnswerA());
            mA.add(a1);
            Question.Answer a2 = new Question.Answer();
            a2.setAnswerMessage("B:"+answerBean.getAnswerB());
            mA.add(a2);
            Question.Answer a3 = new Question.Answer();
            a3.setAnswerMessage("C:"+answerBean.getAnswerC());
            mA.add(a3);
            Question.Answer a4 = new Question.Answer();
            a4.setAnswerMessage("D:"+answerBean.getAnswerD());
            mA.add(a4);
            Question.Answer a5 = new Question.Answer();
            a5.setAnswerMessage("E:"+answerBean.getAnswerE());
            mA.add(a5);
            Question.Answer a6 = new Question.Answer();
            a6.setAnswerMessage("F:"+answerBean.getAnswerF());
            mA.add(a6);
        }
        q.setAnswer(mA);
        questions.add(q);
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
        if(adapter!=null)
        {
            listview.setAdapter(adapter);
            tes.setText(question.getQuestion());
        }
     /*   if(type.equals("SDS_E"))
        {
            tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(90+"");
            progressBar.setMax(90);
            if(question.getAnswerUnm()==2&&question.getPeId().indexOf(".")==-1)
            {
                progressBar.setProgress(Integer.parseInt(question.getPeId()));
            }
         }
        if(type.equals("MBTI_E"))
        {
             tv_dq.setText(question.getPeId()+"/");
            tv_zong.setText(93+"");
            progressBar.setMax(93);

            if(question.getAnswerUnm()==2&&question.getPeId().indexOf(".")==-1)
            {
                progressBar.setProgress(Integer.parseInt(question.getPeId()));
            }

        }
        if(type.equals("MBTI"))
        {
            tv_zong.setText(183+"");
            progressBar.setMax(183);
            tv_dq.setText(question.getTorder()+"/");
            progressBar.setProgress(question.getTorder());
           *//* if(question.getAnswerUnm()==2&&question.getPeId().indexOf(".")==-1)
            {
                tv_dq.setText(question.getPeId()+"/");
                String peId = question.getPeId();
                 pd=peId;
                progressBar.setProgress(Integer.parseInt(pd));
            }
            else
            {
                if(pd!=null)
                {
                     progressBar.setProgress(Integer.parseInt(pd));
                }
                tv_dq.setText(question.getPeId()+"/");
            }
*//*


        }
        if(type.equals("SDS"))
        {
            tv_zong.setText(183+"");
            progressBar.setMax(183);
            tv_dq.setText(question.getTorder()+"/");
            progressBar.setProgress(question.getTorder());
            //progressBar.setProgress(Integer.parseInt(question.getPeId()));

         *//*   if(question.getAnswerUnm()==2&&question.getPeId().indexOf(".")==-1)
            {
                tv_dq.setText(question.getPeId()+"/");
                String peId = question.getPeId();
                SDSpd=peId;
                progressBar.setProgress(Integer.parseInt(SDSpd));
            }
            else
            {
                if(SDSpd!=null)
                {
                    progressBar.setProgress(Integer.parseInt(SDSpd));
                }
                tv_dq.setText(question.getPeId()+"/");
            }*//*
        }*/
        if(type.equals("SDS+MBTI"))
        {
            tv_zong.setText(183+"");
            progressBar.setMax(183);
            tv_dq.setText(question.getTorder()+"/");
            progressBar.setProgress(question.getTorder());
        }

        if(adapter!=null)
        {
        adapter.setItemOnClick(new ChineseMedicineReportAdapter.ItemOnClick() {
            @Override
            public void SX(View view, final int position) {
                final TextView tv_answer=view.findViewById(R.id.tv_answer);
                tv_answer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(Dianji.isNotFastClick())
                        {
                           /*  int answerUnm = question.getAnswerUnm();
                            String peId = question.getPeId();
                           if(answerUnm==2&&peId.indexOf(".")==-1)
                                {
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

                            }*/
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
                            if(position==2)
                            {
                                // Toast.makeText(AnswerActivity.this,   "A", Toast.LENGTH_SHORT).show();
                                XX="C";
                            }
                            if(position==3)
                            {
                                // Toast.makeText(AnswerActivity.this,   "A", Toast.LENGTH_SHORT).show();
                                XX="D";
                            }
                            if(position==4)
                            {
                                // Toast.makeText(AnswerActivity.this,   "A", Toast.LENGTH_SHORT).show();
                                XX="E";
                            }
                            if(position==5)
                            {
                                // Toast.makeText(AnswerActivity.this,   "A", Toast.LENGTH_SHORT).show();
                                XX="F";
                            }
                            map.put(question.getTorder()+"",XX);
                            if (viewFlipper.getDisplayedChild() == mQuestion.size() - 1) {
                                //Toast.makeText(AnswerActivity.this, "最后一个题", Toast.LENGTH_SHORT).show();
                                //得到MBTI测试得到的结果
                             /*   if(type.equals("MBTI"))
                                {
                                    jiexi(tv_answer);
                                    return;
                                }
                                //得到SDS测试得到的结果
                                if(type.equals("SDS"))
                                {
                                    jiexi(tv_answer);
                                    return;
                                }
                                //得到MBTI_E测试得到的结果
                                if(type.equals("MBTI_E"))
                                {
                                    jiexi(tv_answer);
                                    return;
                                }
                                //得到MBTI_E测试得到的结果
                                if(type.equals("SDS_E"))
                                {
                                    jiexi(tv_answer);
                                    return;
                                }*/
                             if(type.equals("SDS+MBTI"))
                             {
                                 jiexi(tv_answer);

                             }

                            } else {
                                viewFlipper.setInAnimation(animations[0]);
                                viewFlipper.setOutAnimation(animations[1]);
                                viewFlipper.showNext();
                                //Toast.makeText(AnswerActivity.this, "下一题", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }

                });
            }
        });

        }
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewFlipper.getDisplayedChild() == 0) {
                    viewFlipper.stopFlipping();
                   Toast.makeText(AnswerActivity.this, "已经是第一题", Toast.LENGTH_SHORT).show();
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
    public void jiexi(final TextView tv_answer) {
        viewFlipper.stopFlipping();
        Gson gson=new Gson();
        String route= gson.toJson(map);
        System.out.println("答案+++"+route.toString());
      MyQusetUtils.getInstance().getQuestInterface().tijiao(type, route.toString(),token)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(new DisposableSubscriber<TijiaoBean>() {
                  @Override
                  public void onNext(TijiaoBean tijiaoBeanBaseBean) {
                      tv_answer.setEnabled(true);
                      startActivity(new Intent(AnswerActivity.this,EvaluatingActivity.class));
                      finish();
                  }
                  @Override
                  public void onError(Throwable t) {
                   }
                  @Override
                  public void onComplete() {

                  }
              });


       /* Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(BaseApi.Api)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        QuestInterface questInterface = retrofit.create(QuestInterface.class);
       // RequestBody body=RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"),route);
        Call<BaseBean<TijiaoBean>> tijiao = questInterface.tijiao(type, route,token);
        tijiao.enqueue(new Callback<BaseBean<TijiaoBean>>() {
            @Override
            public void onResponse(Call<BaseBean<TijiaoBean>> call, Response<BaseBean<TijiaoBean>> response) {
                tv_answer.setEnabled(true);
                //TijiaoBean shuju = response.body().data;
                 startActivity(new Intent(AnswerActivity.this,EvaluatingActivity.class));
                    finish();
                    return;

               *//* if(type.equals("MBTI_E"))
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
                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("MBTI_E",mbtiString,token);
                    Intent intent=new Intent(AnswerActivity.this,MBI_CSActivity.class);
                    startActivity(intent);
                    finish();
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

                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("SDS_E",mbtiString,token);
                     Intent intent=new Intent(AnswerActivity.this,HuoLanDeEsayActivity.class);
                    startActivity(intent);
                    finish();
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
                    SharedPreferences mbti = getSharedPreferences("mbti", MODE_PRIVATE);
                    SharedPreferences.Editor edit = mbti.edit();
                    edit.putString("mb",mbtiString);
                    edit.commit();
                    intent(AnswerActivity.this,AnswerActivity.class);
                    type="SDS";
                    finish();
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
                    efcjgBaoCunPresenter.EFCJGBaoCunPresenter("精准",mbtiString,token);
                    startActivity(new Intent(AnswerActivity.this,EvaluatingActivity.class));
                    finish();
                    return;
                }*//*
            }
            @Override
            public void onFailure(Call<BaseBean<TijiaoBean>> call, Throwable t) {

             }
        });*/
     }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将Activity上的触发的事件交个GestureDetector处理
        return this.mGestureDetector.onTouchEvent(event);
    }
    @OnClick(R.id.answer_rl_iv)
    public void onViewClicked() {
        View view = LayoutInflater.from(this).inflate(R.layout.tankuang, null);
        final AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(view).show();
         TextView dilog_queren=  view.findViewById(R.id.dilog_queren);
         TextView dilog_quxiao=  view.findViewById(R.id.dilog_quxiao);
        dilog_queren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dilog_quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        answerPresent.onDestory();
        efcjgBaoCunPresenter.onDestory();
    }

    @Override
    public void Savesuccess(BaseBean baseBean) {

    }

    @Override
    public void Savefail(Throwable t) {


    }
}
