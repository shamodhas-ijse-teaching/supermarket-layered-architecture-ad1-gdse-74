package lk.ijse.supermarketfx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:27 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    private String id;
    private String name;
    private int quantity;
//    private double price;
    private BigDecimal price;
}
