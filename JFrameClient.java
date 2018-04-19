package clientes;

import com.sun.messaging.ConnectionConfiguration;
import javax.jms.*;

public class JFrameClient {

	
	public static void main(String[] args){
		
		try{
			// Pruebas simples Java JMS
			ConnectionFactory myConnFactory;
			Queue myQueue;
			
			myConnFactory = new com.sun.messaging.ConnectionFactory();
			Connection myConn = myConnFactory.createConnection();
			Session mySess = myConn.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Creamos el Productor de mensajes
			myQueue = mySess.createQueue("clase");
			MessageProducer myMsgProducer = mySess.createProducer(myQueue);
			// Creamos mensaje a enviar
           		TextMessage myTextMsg = mySess.createTextMessage();
            		myTextMsg.setText("Hola clase");
            		// Productor envía el mensaje a su sesión
            		myMsgProducer.send(myTextMsg);
            
		    	// Creamos el Consumidor
			MessageConsumer myMsgConsumer = mySess.createConsumer(myQueue);
			myConn.start();
			
			// Comprobamos el tipo de mensaje recibido
			Message msg = myMsgConsumer.receive();
			if(msg instanceof TextMessage){
				TextMessage txt = (TextMessage) msg;
				System.out.println("Lee Mensaje : " + txt.getText());				
			}
			
			mySess.close();
			myConn.close();
			
		}catch(Exception jmse) {
            System.out.println("Exception occurred : " + jmse.toString());
            jmse.printStackTrace();
		}
	}
	
	
}
