package lk.ijse.supermarketfx.bo.custom.impl;

import lk.ijse.supermarketfx.bo.custom.CustomerBO;
import lk.ijse.supermarketfx.bo.exception.DuplicateException;
import lk.ijse.supermarketfx.bo.exception.InUseException;
import lk.ijse.supermarketfx.bo.exception.NotFoundException;
import lk.ijse.supermarketfx.dao.DAOFactory;
import lk.ijse.supermarketfx.dao.DAOTypes;
import lk.ijse.supermarketfx.dao.custom.CustomerDAO;
import lk.ijse.supermarketfx.dao.custom.OrderDAO;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private final OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);

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

    @Override
    public void saveCustomer(CustomerDTO dto) throws DuplicateException, Exception {
//        dto -> entity
//        send to dao layer and save

//        id duplicate
//        nic duplicate
//        phone number duplicate
        Optional<Customer> optionalCustomer = customerDAO.findById(dto.getCustomerId());
        if (optionalCustomer.isPresent()) {
//            duplicate id
            throw new DuplicateException("Duplicate customer id");
        }

        Optional<Customer> customerByNicOptional = customerDAO.findCustomerByNic(dto.getNic());
        if (customerByNicOptional.isPresent()) {
            throw new DuplicateException("Duplicate customer nic");
        }

        if (customerDAO.existsCustomerByPhoneNumber(dto.getPhone())) {
            throw new DuplicateException("Duplicate customer phone number");
        }

        Customer customer = new Customer();

        customer.setId(dto.getCustomerId());
        customer.setNic(dto.getNic());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhone(dto.getPhone());

        boolean save = customerDAO.save(customer);
    }

    @Override
    public boolean deleteCustomer(String id) throws InUseException, Exception {
//        id
        Optional<Customer> optionalCustomer = customerDAO.findById(id);
        if (optionalCustomer.isEmpty()) {
            throw new NotFoundException("Customer not found..!");
        }

        // customer have orders ?
        if (orderDAO.existOrdersByCustomerId(id)){
            throw new InUseException("Customer has orders");
        }

        try {
            boolean delete = customerDAO.delete(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
