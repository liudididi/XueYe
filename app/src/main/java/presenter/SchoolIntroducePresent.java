package presenter;

import java.util.List;

import base.BaseBean;
import base.Basepresent;
import bean.CampusBean;
import bean.FingerpostBean;
import bean.LXBean;
import bean.SchoolIntroduceBean;
import bean.ZDXKBean;
import moudle.SchoolIntroduceMoudle;
import view.SchoolIntroduceView;
import view.School_SummaryView;

/**
 * Created by 祝文 on 2018/2/25.
 */

public class SchoolIntroducePresent extends Basepresent {
    private SchoolIntroduceView schoolIntroduceView;
    private SchoolIntroduceMoudle schoolIntroduceMoudle;

    public SchoolIntroducePresent(Object view) {
        super(view);
        if(schoolIntroduceMoudle==null)
        {
            schoolIntroduceMoudle=new SchoolIntroduceMoudle();
        }
        schoolIntroduceView= (SchoolIntroduceView) view;
    }
    //校园介绍
    public void SchoolIntroducePresent(String name)
    {
        schoolIntroduceMoudle.Introduce(name, new SchoolIntroduceMoudle.IntroduceBack() {
            @Override
            public void Introducesuccess(BaseBean<List<SchoolIntroduceBean>> listBaseBean) {
                schoolIntroduceView.Introducesuccess(listBaseBean);
            }

            @Override
            public void Introducefail(Throwable t) {
                schoolIntroduceView.Introducefail(t);
            }
        });
    }
    //报考指南
    public void FingerpostPresent(String name)
    {
        schoolIntroduceMoudle.Fingerpost(name, new SchoolIntroduceMoudle.FingerpostBack() {
            @Override
            public void Fingerpostsuccess(BaseBean<List<FingerpostBean>> listBaseBean) {
                schoolIntroduceView.Fingerpostsuccess(listBaseBean);
            }

            @Override
            public void Fingerpostfail(Throwable t) {
                schoolIntroduceView.Fingerpostfail(t);
            }
        });
    }

    //获取院校概况
    public void CampusPresent(String name)
    {
        schoolIntroduceMoudle.Campus(name, new SchoolIntroduceMoudle.CampusBack() {
            @Override
            public void Campussuccess(BaseBean<List<CampusBean>> listBaseBean) {
                schoolIntroduceView.Campussuccess(listBaseBean);
            }

            @Override
            public void Campusfail(Throwable t) {
                schoolIntroduceView.Campusfail(t);
            }
        });
    }

    //获取重点专业或重点实验室
    public void ZDXKPresent(String flag,String name)
    {
        schoolIntroduceMoudle.getUnivImportant(flag, name, new SchoolIntroduceMoudle.UnivImportantBack() {
            @Override
            public void UnivImportantssuccess(BaseBean<ZDXKBean> zdxkBeanBaseBean) {
                schoolIntroduceView.UnivImportantssuccess(zdxkBeanBaseBean);
            }

            @Override
            public void UnivImportantfail(Throwable t) {
                schoolIntroduceView.UnivImportantfail(t);
            }
        });
    }

    //联系方式
    public void LianXIPresent(String name)
    {
        schoolIntroduceMoudle.getUnivPhone(name, new SchoolIntroduceMoudle.LianXiBack() {
            @Override
            public void LianXisuccess(BaseBean<List<LXBean>> lxBeanBaseBean) {
                schoolIntroduceView.LianXisuccess(lxBeanBaseBean);
            }

            @Override
            public void LianXifail(Throwable t) {
                schoolIntroduceView.LianXifail(t);
            }
        });
    }
    public   void  onDestory(){
        this.onDeach();
        schoolIntroduceMoudle.onDestory();
    }
}
