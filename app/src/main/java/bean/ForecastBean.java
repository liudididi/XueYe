package bean;

/**
 * Created by 祝文 on 2018/2/23.
 */

public class ForecastBean {


    /**
     * TIME : 本科一批
     * lqgai : 0.83
     * fscore : 660
     */

    private String TIME;
    private double lqgai;
    private int fscore;

    public String getTIME() {
        return TIME;
    }

    public void setTIME(String TIME) {
        this.TIME = TIME;
    }

    public double getLqgai() {
        return lqgai;
    }

    public void setLqgai(double lqgai) {
        this.lqgai = lqgai;
    }

    public int getFscore() {
        return fscore;
    }

    public void setFscore(int fscore) {
        this.fscore = fscore;
    }
}
