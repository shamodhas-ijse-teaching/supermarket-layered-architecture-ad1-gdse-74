package lk.ijse.supermarketfx.bo.custom.impl;

import lk.ijse.supermarketfx.bo.custom.CustomerBO;
import lk.ijse.supermarketfx.dao.DAOFactory;
import lk.ijse.supermarketfx.dao.DAOTypes;
import lk.ijse.supermarketfx.dao.custom.CustomerDAO;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/11/2025 10:24 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

// model mapper
public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException {
        List<Customer> customers = customerDAO.getAll();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    customer.getId(),
                    customer.getName(),
                    customer.getNic(),
                    customer.getEmail(),
                    customer.getPhone()
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
}
