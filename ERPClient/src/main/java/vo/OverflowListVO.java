package vo;

import java.util.Set;

public class OverflowListVO {
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

    /**
     *
     */
    public Set<GoodsOverflowVO> set;

    public OverflowListVO() {
    }

    public OverflowListVO(int id, String date, String operator, Set<GoodsOverflowVO> set) {
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

    public Set<GoodsOverflowVO> getSet() {
        return set;
    }

    public void setSet(Set<GoodsOverflowVO> set) {
        this.set = set;
    }
}
