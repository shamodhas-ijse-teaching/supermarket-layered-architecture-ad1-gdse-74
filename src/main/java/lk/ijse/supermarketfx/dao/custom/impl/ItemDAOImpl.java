package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.dao.custom.ItemDAO;
import lk.ijse.supermarketfx.entity.Item;

import java.util.List;
import java.util.Optional;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 12:43 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<Item> getAll() {
        return List.of();
    }

    @Override
    public String getNextId() {
        return "";
    }

    @Override
    public boolean save(Item item) {
        return false;
    }

    @Override
    public boolean update(Item item) {
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
    public Optional<Item> findById(String id) {
        return Optional.empty();
    }

    @Override
    public boolean reduceQuantity(String id, int qty) {
        return false;
    }
}
