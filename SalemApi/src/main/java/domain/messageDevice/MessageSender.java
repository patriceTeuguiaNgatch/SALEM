package domain.messageDevice;

public interface MessageSender {
     void sendMessage( String recipient, String emailText ) throws Exception;
}
