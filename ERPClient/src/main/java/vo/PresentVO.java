package vo;

public class PresentVO {
    /**
     * id
     */
    public int id;
    /**
     * 赠送商品数量
     */
    public int number;

    /**
     * 赠送商品
     */
    public GoodsVO vo;

    public PresentVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public GoodsVO getVo() {
        return vo;
    }

    public void setVo(GoodsVO vo) {
        this.vo = vo;
    }
}
