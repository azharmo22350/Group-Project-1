package lk.ijse.dep11.tb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Order {

    private String CustomerId;
    private String ItemName;
    private int ItemQuntity;

}
