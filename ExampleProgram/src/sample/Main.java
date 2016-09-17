package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #000000");

        HBox top = new HBox();
        top.setMinHeight(50);
        top.setMinWidth(600);
        top.setStyle("-fx-background-color: #F36699");

        TextField input = new TextField();
        input.setMinSize(550,50);

        //changed

        TextField output = new TextField();
        output.setPrefHeight(550);
        output.setPrefWidth(600);

        root.getChildren().addAll(top,output);

        Button go = new Button("Go");
        go.setMinSize(50,50);
        go.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("first");
                String url = input.getText();
                try {
                    output.setText(getRequest(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        top.getChildren().addAll(input, go);

        primaryStage.setTitle("Example");
        primaryStage.setScene(new Scene(root, 600, 600));
        primaryStage.show();
    }

    private String getRequest(String url) throws Exception {
        System.out.println("worked");
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println("we made it"+response.toString());
        return response.toString();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
