package vo;

public class UserVO {

    /**
     * 用户职位
     */
    private String type;

    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     **登录状态
     */
    private boolean login;


    public UserVO() {
    }

    public UserVO(String type, String name, String password, boolean login) {
        this.type = type;
        this.name = name;
        this.password = password;
        this.login = login;
    }

    public UserVO(String type, String name, String password) {
        this.type = type;
        this.name = name;
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
