import junit.framework.TestCase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by jonathon lancaster on 7/25/2017.
 */
public class PasswordEncoder extends TestCase{

    public void testBCryptHash() {
        String password = "pass";
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);
        System.out.println(hashedPassword);
    }
}
