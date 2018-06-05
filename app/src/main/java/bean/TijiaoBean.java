package bean;

/**
 * Created by 祝文 on 2018/3/18.
 */

public class TijiaoBean {


    /**
     * msg : success
     * code : 0
     * data : {"SDS":{"result":{"A":0,"C":0,"E":0,"I":0,"R":0,"S":0},"resultStr":"SRI"},"MBTI":{"result":{"E":0,"S":0,"T":0,"J":0,"I":0,"N":0,"F":0,"P":0},"resultStr":"INFP"}}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * SDS : {"result":{"A":0,"C":0,"E":0,"I":0,"R":0,"S":0},"resultStr":"SRI"}
         * MBTI : {"result":{"E":0,"S":0,"T":0,"J":0,"I":0,"N":0,"F":0,"P":0},"resultStr":"INFP"}
         */

        private SDSBean SDS;
        private MBTIBean MBTI;

        public SDSBean getSDS() {
            return SDS;
        }

        public void setSDS(SDSBean SDS) {
            this.SDS = SDS;
        }

        public MBTIBean getMBTI() {
            return MBTI;
        }

        public void setMBTI(MBTIBean MBTI) {
            this.MBTI = MBTI;
        }

        public static class SDSBean {
            /**
             * result : {"A":0,"C":0,"E":0,"I":0,"R":0,"S":0}
             * resultStr : SRI
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
                 * A : 0
                 * C : 0
                 * E : 0
                 * I : 0
                 * R : 0
                 * S : 0
                 */

                private int A;
                private int C;
                private int E;
                private int I;
                private int R;
                private int S;

                public int getA() {
                    return A;
                }

                public void setA(int A) {
                    this.A = A;
                }

                public int getC() {
                    return C;
                }

                public void setC(int C) {
                    this.C = C;
                }

                public int getE() {
                    return E;
                }

                public void setE(int E) {
                    this.E = E;
                }

                public int getI() {
                    return I;
                }

                public void setI(int I) {
                    this.I = I;
                }

                public int getR() {
                    return R;
                }

                public void setR(int R) {
                    this.R = R;
                }

                public int getS() {
                    return S;
                }

                public void setS(int S) {
                    this.S = S;
                }
            }
        }

        public static class MBTIBean {
            /**
             * result : {"E":0,"S":0,"T":0,"J":0,"I":0,"N":0,"F":0,"P":0}
             * resultStr : INFP
             */

            private ResultBeanX result;
            private String resultStr;

            public ResultBeanX getResult() {
                return result;
            }

            public void setResult(ResultBeanX result) {
                this.result = result;
            }

            public String getResultStr() {
                return resultStr;
            }

            public void setResultStr(String resultStr) {
                this.resultStr = resultStr;
            }

            public static class ResultBeanX {
                /**
                 * E : 0
                 * S : 0
                 * T : 0
                 * J : 0
                 * I : 0
                 * N : 0
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
    }
}
