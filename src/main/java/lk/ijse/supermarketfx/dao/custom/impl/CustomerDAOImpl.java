package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.dao.custom.CustomerDAO;
import lk.ijse.supermarketfx.entity.Customer;

import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 10:51 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public List<Customer> getAll() {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public boolean save(Customer customer) {
        return false;
    }

    @Override
    public boolean update(Customer customer) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<String> getAllIds() {
        return List.of();
    }

    @Override
    public Optional<Customer> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Customer> search(String text) {
        return List.of();
    }
}
