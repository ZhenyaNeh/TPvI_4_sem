package org.example.laba_14.task04;

import javax.annotation.Resource;
import javax.jms.*;
public class MessageSender
{
    @Resource(mappedName = "jms/GlassFishConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/GlassFishBookTopic")
    private static Topic topic;

    public void produceMessages(String message)
    {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(topic);
            textMessage = session.createTextMessage();
            textMessage.setText(message);
            messageProducer.send(textMessage);
            messageProducer.close();
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
        new MessageSender().produceMessages("Hello, world!");
    }
}