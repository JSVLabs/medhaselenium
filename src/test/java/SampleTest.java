import com.libraries.pages.Login;
import org.junit.Test;

public class SampleTest{

    @Test
    public void Login(){
        Login login = new Login();

        login.enterLoginInfo();
        login.clickLogin();

    }
}
