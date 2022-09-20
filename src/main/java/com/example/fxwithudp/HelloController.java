package com.example.fxwithudp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HexFormat;

public class HelloController {

    private static final Logger LOGGER = LogManager.getLogger(HelloController.class);
    private static final int SERVICE_PORT = 8192;
    private static final byte[] MESSAGE = {0x02, 0x1F, 0x00, 0x03, 0x61, 0x38};
    public Label label1;
    public TextArea inputIP;

    @FXML
    public void button0() {

        LOGGER.info("Нажата кнопка opendoor =0.");

        // Создаем UDP сокет
        LOGGER.info("Создание сокета.");
        try (MulticastSocket socket = new MulticastSocket()) {
            InetAddress ipAddress = InetAddress.getByName(inputIP.getText());

            LOGGER.info("Чтение ip: " + ipAddress + ":" + SERVICE_PORT);
            InetSocketAddress mainAddress = new InetSocketAddress(ipAddress, SERVICE_PORT);
            socket.setSoTimeout(1000);

            //Запоминаем сообщение
            DatagramPacket messagePacket = new DatagramPacket(MESSAGE, MESSAGE.length, mainAddress);

            // Отправляем сообщение
            LOGGER.info("Отправка данных: " + Arrays.toString(MESSAGE) + "." + " От: " + InetAddress.getLocalHost());
            socket.send(messagePacket);

            byte[] receiveBuffer = new byte[1024];

            // Пакет для получения сообщения
            DatagramPacket outputPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(outputPacket);
            int packetLength = outputPacket.getLength();

            String hex = HexFormat.of().formatHex(receiveBuffer, 0 , packetLength);

            LOGGER.info("Проверка на объем пришедшего пакета. Получено: " +
                    packetLength +
                    " byte. Ответ: " +
                    hex +
                    "." +
                    " От " +
                    outputPacket.getAddress() +
                    ":" +
                    outputPacket.getPort());

            if (packetLength > 256)
                throw new RuntimeException("Полученные данные превышают размер буфера. Размер " + packetLength + " byte.");

            //Получаем сообщение
            label1.setText("Message: " + hex);
            LOGGER.info("Закрытие сокета.");
            //label1.setText("Message: " + convertByteToHex(receiveBuffer, packetLength));

        } catch (IOException e) {
            LOGGER.error("Ошибка: " + e);
            label1.setText(e.getMessage());
        }
    }

    // Перевод byte в hex
//    public static String convertByteToHex(byte[] arraysMessage, int packetL) {
//        StringBuilder hex = new StringBuilder();
//        try {
//            LOGGER.info("Конвертация BYTE в HEX.");
//            for (int i = 0; i < packetL; i++) {
//                hex.append(String.format("%02X", arraysMessage[i]));
//            }
//        } catch (Exception ex) {
//            LOGGER.error(ex);
//        }
//        return hex.toString();
//    }


    public void button1() {
    }
}