package lk.ijse.supermarketfx.bo.custom.impl;

import lk.ijse.supermarketfx.bo.custom.PlaceOrderBO;
import lk.ijse.supermarketfx.dao.DAOFactory;
import lk.ijse.supermarketfx.dao.DAOTypes;
import lk.ijse.supermarketfx.dao.custom.ItemDAO;
import lk.ijse.supermarketfx.dao.custom.OrderDAO;
import lk.ijse.supermarketfx.dao.custom.OrderDetailsDAO;
import lk.ijse.supermarketfx.db.DBConnection;
import lk.ijse.supermarketfx.dto.OrderDTO;
import lk.ijse.supermarketfx.dto.OrderDetailsDTO;
import lk.ijse.supermarketfx.entity.Order;
import lk.ijse.supermarketfx.entity.OrderDetail;
import lk.ijse.supermarketfx.util.CrudUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/11/2025 10:25 AM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

public class PlaceOrderBOImpl implements PlaceOrderBO {
    private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOTypes.ORDER);

    private OrderDetailsDAO orderDetailsDAO =
            DAOFactory.getInstance().getDAO(DAOTypes.ORDER_DETAILS);
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOTypes.ITEM);

    @Override
    public boolean placeOrder(OrderDTO dto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);

            Order order = new Order();
            order.setId(dto.getOrderId());
            order.setCustomerId(dto.getCustomerId());
            order.setOrderDate(dto.getDate());

            boolean isOrderSaved = orderDAO.save(order);
            if (isOrderSaved) {
                boolean isSuccess = saveDetailsAndUpdateItem(dto.getCartList());
                if (isSuccess) {
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
//            throw new Exception("Fail somthing");
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean saveDetailsAndUpdateItem(List<OrderDetailsDTO> detailsList) throws SQLException {
        //            for (int i = 1; i < detailsList.size() - 1; i++) {
//                OrderDetailsDTO orderDetailsDTO = detailsList.get(i);
//            }
        for (OrderDetailsDTO detailsDTO : detailsList) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(detailsDTO.getOrderId());
            orderDetail.setItemId(detailsDTO.getItemId());
            orderDetail.setQuantity(detailsDTO.getQty());
            orderDetail.setPrice(BigDecimal.valueOf(detailsDTO.getPrice()));

            if (!orderDetailsDAO.save(orderDetail)) {
//                rollback
                return false;
            }
            boolean isItemUpdated = itemDAO.reduceQuantity(
                    detailsDTO.getItemId(),
                    detailsDTO.getQty()
            );
            if (!isItemUpdated) {
                return false;
            }
        }
        return true;
    }
}
