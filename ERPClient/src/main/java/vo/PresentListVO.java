package vo;

import java.util.Set;

public class PresentListVO {
    /**
     * id
     */
    public int id;
    /**
     * 日期
     */
    String date;

    /**
     * 赠送商品
     */
    Set<PresentVO> set;


    /**
     * 操作员
     */
    String operator;

    /**
     * 客户
     */
    String client;

    public PresentListVO() {
    }

    public PresentListVO(int id, String date, Set<PresentVO> set, String operator, String client) {
        this.id = id;
        this.date = date;
        this.set = set;
        this.operator = operator;
        this.client = client;
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

    public Set<PresentVO> getSet() {
        return set;
    }

    public void setSet(Set<PresentVO> set) {
        this.set = set;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
