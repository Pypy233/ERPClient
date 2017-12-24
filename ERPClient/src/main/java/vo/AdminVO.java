package vo;

public class AdminVO {
    /**
     * 管理员的id
     */
    private int id;

    /**
     * 管理员的名字
     */

    private String name;

    /**
     * 管理员的密码
     */
    private String password;

    public AdminVO() {
    }

    public AdminVO(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
