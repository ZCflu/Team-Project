package StaffClasses;

public class loginCredentials {
    private String login;
    private String password;

    public loginCredentials(String login, String password){
        this.login = login;
        this.password = password;
    }
      
    public boolean checkCredentials(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }
}
