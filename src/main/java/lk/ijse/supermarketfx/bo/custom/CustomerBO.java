package lk.ijse.supermarketfx.bo.custom;

import lk.ijse.supermarketfx.bo.SuperBO;
import lk.ijse.supermarketfx.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/11/2025 10:22 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public interface CustomerBO extends SuperBO {
    List<CustomerDTO> getAllCustomer() throws SQLException;
}
