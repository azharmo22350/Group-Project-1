package lk.ijse.dep11;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private String CustomerId;
    private String CustomerName;
    private String ContactNumber;

    ArrayList<Item> ItemList ;

}


class Item implements Serializable{

    private String ItemName;
    private int ItemQuntity;


}
