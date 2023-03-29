
/**
 * @author SakurazawaRyoko
 * @version 1.0
 * @date 1/6/2023 1:47 PM
 * @description TODO
 */
public class User {
    private String username,password;
    private int level;

    public User() {
    }

    public User(String username, String password, int level) {
        this.username = username;
        this.password = password;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
