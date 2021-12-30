package com.rctecnologia.aciot;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.rctecnologia.aciot.model.Point;
import com.rctecnologia.aciot.repository.PointRepository;

/**
 * Classe utilizada para configuração e serviço do broker mqtt
 */

@Controller
public class GreetingController {

	String topic1 = "teste/espee";
    String topic2 = "device_2";
    String topic3 = "device_3";
    int qos = 0;
    String broker = "tcp://192.168.1.8:1883";
    
    Point point;
      
    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private final SimpMessagingTemplate simpMessagingTemplate;

    public GreetingController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    String ClientId = MqttAsyncClient.generateClientId();

    {
        try {
            MqttClient mqttClient = new MqttClient(broker, ClientId);
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            connectOptions.isCleanSession();
            connectOptions.isAutomaticReconnect();

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {

                }

                @Override
                public void messageArrived(String topico, MqttMessage message) throws Exception {
             
                    System.out.println("\tTópico: " + topico);
                    System.out.println("\tMensagem: " + message);
                    System.out.println("");                          
            		
            		Gson gson = new Gson();            		
         		
            		Point pointsaved = gson.fromJson(message.toString(), Point.class);
            		
            		Point point = new Point();
            		//point.setId(10011L);
            		point.setName(pointsaved.getName());
            		point.setTypepoint(pointsaved.getTypepoint());
            		point.setValue(pointsaved.getValue());           		
            		
            		System.out.println("name i = "+point.getName()+ ", Typepoint = "+point.getTypepoint()+" Value  = "+ point.getValue());            		
            		
            		try {
            	       pointRepository.save(point);
            	       System.out.println("Dados Salvos");
            		}catch(Exception e) {
            			e.printStackTrace();            		
            		}
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });
            mqttClient.connect(connectOptions);
            mqttClient.subscribe(topic1, qos);
            //mqttClient.subscribe(topic2, qos);
            //mqttClient.subscribe(topic3, qos);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
