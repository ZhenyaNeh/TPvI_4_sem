package org.example.laba_14.task04;

import javax.annotation.Resource;
import javax.jms.*;
public class ConsoleReceiver
{
    @Resource(mappedName = "jms/GlassFishConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName ="jms/GlassFishTopic")
    private static Topic topic;
    public void getMessages()
    {
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(topic);
            connection.start();
            System.out.println("Waiting for messages...");
            textMessage = (TextMessage) messageConsumer.receive();
            System.out.println(textMessage.toString());
            messageConsumer.close();
            session.close();
            connection.close();
        }
        catch (JMSException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args)
    {
        new ConsoleReceiver().getMessages();
    }
}