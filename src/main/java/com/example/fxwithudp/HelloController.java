package com.example.fxwithudp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

public class HelloController {


    private static final int SERVICE_PORT = 8192;
    private static final byte[] MESSAGE = {0x02, 0x1F, 0x00, 0x03, 0x61, 0x38};
    public Label label1;
    public TextArea inputIP;

    @FXML
    public void button0() {
        // Создаем UDP сокет
        try (MulticastSocket socket = new MulticastSocket()) {

            InetAddress ipAddress = InetAddress.getByName(inputIP.getText());
            InetSocketAddress mainAddress = new InetSocketAddress(ipAddress, SERVICE_PORT);
            socket.setSoTimeout(1000);

            //Запоминаем сообщение
            DatagramPacket messagePacket = new DatagramPacket(MESSAGE, MESSAGE.length, mainAddress);

            // Отправляем сообщение
            socket.send(messagePacket);

            byte[] receiveBuffer = new byte[256];

            // Пакет для получения сообщения
            DatagramPacket outputPacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(outputPacket);

            int packetLength = outputPacket.getLength();

            //Получаем сообщение
            label1.setText(convertByteToHex(receiveBuffer, packetLength));

        } catch (IOException e) {
            label1.setText(e.getMessage());
        }
    }

    // Перевод byte в hex
    public static String convertByteToHex(byte[] arraysMessage, int packetL) {
        if(packetL > 256)
            throw new RuntimeException("Buffer overflowing");

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < packetL; i++) {
            hex.append(String.format("%02X", arraysMessage[i]));
        }

        return hex.toString();
    }


    public void button1() {
    }
}