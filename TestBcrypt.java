import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBcrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println("Matches: " + encoder.matches("password", "$2a$10$wY1txMsnoDzEEhE3UvQv3e3L.08s6d65w5wD0o7Jz8V9.8pP9W72W"));
    }
}
