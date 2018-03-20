package bean;

/**
 * Created by 祝文 on 2018/3/18.
 */

public class TijiaoBean {

    /**
     * result : {"E":1,"S":0,"T":0,"J":0,"I":0,"N":1,"F":0,"P":0}
     * resultStr : ENFP
     */

    private ResultBean result;
    private String resultStr;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getResultStr() {
        return resultStr;
    }

    public void setResultStr(String resultStr) {
        this.resultStr = resultStr;
    }

    public static class ResultBean {
        /**
         * E : 1
         * S : 0
         * T : 0
         * J : 0
         * I : 0
         * N : 1
         * F : 0
         * P : 0
         */

        private int E;
        private int S;
        private int T;
        private int J;
        private int I;
        private int N;
        private int F;
        private int P;

        private int A;
        private int C;
        private int R;

        public void setA(int a) {
            A = a;
        }

        public void setC(int c) {
            C = c;
        }

        public void setR(int r) {
            R = r;
        }

        public int getA() {
            return A;
        }

        public int getC() {
            return C;
        }

        public int getR() {
            return R;
        }

        public int getE() {
            return E;
        }

        public void setE(int E) {
            this.E = E;
        }

        public int getS() {
            return S;
        }

        public void setS(int S) {
            this.S = S;
        }

        public int getT() {
            return T;
        }

        public void setT(int T) {
            this.T = T;
        }

        public int getJ() {
            return J;
        }

        public void setJ(int J) {
            this.J = J;
        }

        public int getI() {
            return I;
        }

        public void setI(int I) {
            this.I = I;
        }

        public int getN() {
            return N;
        }

        public void setN(int N) {
            this.N = N;
        }

        public int getF() {
            return F;
        }

        public void setF(int F) {
            this.F = F;
        }

        public int getP() {
            return P;
        }

        public void setP(int P) {
            this.P = P;
        }
    }
}
