package bean;

/**
 * Created by 祝文 on 2018/2/23.
 */

public class ForecastBean {
    /**
     * time : 本科一批
     * lqgai : 0
     * fscore : 652
     */
    private String time;
    private double lqgai;
    private int fscore;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
