// Step 1: Define a Service Interface
interface MessageService {
    void sendMessage(String message, String recipient);
}

// Step 2: Create Concrete Implementations of the Service Interface
class EmailService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("Email sent to " + recipient + " with message: " + message);
    }
}

class SMSService implements MessageService {
    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("SMS sent to " + recipient + " with message: " + message);
    }
}

// Step 3: Create a Consumer Class That Depends on the Service
class NotificationService {
    private final MessageService messageService;

    // Constructor Injection for Dependency
    public NotificationService(MessageService messageService) {
        this.messageService = messageService;
    }

    public void notifyUser(String message, String recipient) {
        messageService.sendMessage(message, recipient);
    }
}

// Step 4: Main Class
public class DependencyInjectionDemo {
    public static void main(String[] args) {
        // Inject EmailService Dependency
        MessageService emailService = new EmailService();
        NotificationService emailNotification = new NotificationService(emailService);
        emailNotification.notifyUser("Hello via Email!", "email@example.com");

        // Inject SMSService Dependency
        MessageService smsService = new SMSService();
        NotificationService smsNotification = new NotificationService(smsService);
        smsNotification.notifyUser("Hello via SMS!", "123-456-7890");
    }
}
//class should be DependencyInjectionDemo.java