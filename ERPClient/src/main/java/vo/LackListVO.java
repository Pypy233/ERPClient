package vo;

import java.util.HashSet;
import java.util.Set;

public class LackListVO{
    /**
     * id
     */
         public int id;

        /**
         * 日期
         */
        public String date;

        /**
         *操作人员
         */
        public String operator;

        public Set<GoodsLackVO> set = new HashSet<>();

    public LackListVO() {
    }

    public LackListVO(int id, String date, String operator, Set<GoodsLackVO> set) {
        this.id = id;
        this.date = date;
        this.operator = operator;
        this.set = set;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Set<GoodsLackVO> getSet() {
        return set;
    }

    public void setSet(Set<GoodsLackVO> set) {
        this.set = set;
    }
}
