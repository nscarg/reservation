package nsc.com.mail.infraestructure;


public interface QueueInfraestructure {
    String sendMessage(String urlQueue, Object object);
}