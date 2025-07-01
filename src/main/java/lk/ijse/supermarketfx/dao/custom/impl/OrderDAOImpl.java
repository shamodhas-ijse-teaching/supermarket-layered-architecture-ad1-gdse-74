package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.dao.custom.OrderDAO;
import lk.ijse.supermarketfx.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 1:01 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> getAll() {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public boolean save(Order order) {
        return false;
    }

    @Override
    public boolean update(Order order) {
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
    public Optional<Order> findById(String id) {
        return Optional.empty();
    }
}
