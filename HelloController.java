package com.example.clientserver;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HelloController {
    @FXML
    private TextField answerfield;

    @FXML
    private TextField namefield;

    @FXML
    void onanswerbtn(ActionEvent event){
        try(Socket serverSocket = new Socket("localhost",1012)) {//połączenie
            //pobranie i nadanie odpowiedzi
            byte[] data = answerfield.getText().getBytes();
            byte[] name = namefield.getText().getBytes();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(name);
            outputStream.write("|".getBytes());
            outputStream.write(data);
            serverSocket.getOutputStream().write(outputStream.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}