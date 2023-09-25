package lk.ijse.dep11;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep11.tb.Order;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerViewController {
    public TableView<Order> tblOrder;
    public Button btnComplete;

    public void initialize(){

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("ItemQuntity"));


    }

    private void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            while (true){
                System.out.println("Waiting for client connection");
                Socket localSocket = serverSocket.accept();
                System.out.println("Client connected: " + localSocket);
                new Thread(()->{
                    try {
                        InputStream is = localSocket.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        while (true) {
                            Customer customer = (Customer) ois.readObject();

                           //getOrderReffrence(customer); -get the order reffrence

                            ArrayList<Item> itemlist= customer.ItemList;

                            for(Item item: itemlist)
                            {
                                Order order = new Order();
                                order.setCustomerId(customer.getCustomerId());
                                order.setItemName(item.getItemName());
                                order.setItemQuntity(item.getItemQuntity());

                                Platform.runLater( ()->

                                        tblOrder.getItems().add( order)
                                );

                            }

                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        private Order getOrderReffrence(Customer customer) {

        Order order=new Order();
        order.setCustomerId(customer.getCustomerId());


        for(Item items : customer.ItemList )
        {

        }

    }

     */


    public void btnCompleteOnAction(ActionEvent actionEvent) {


    }
}
