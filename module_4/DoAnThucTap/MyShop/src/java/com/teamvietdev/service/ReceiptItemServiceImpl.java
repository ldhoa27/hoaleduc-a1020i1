package com.teamvietdev.service;

import com.teamvietdev.dao.ReceiptItemDAO;
import com.teamvietdev.model.ReceiptItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TVD
 */
@Service
public class ReceiptItemServiceImpl implements ReceiptItemService {

    @Autowired
    private ReceiptItemDAO receiptItemDAO;

    @Override
    public boolean create(ReceiptItem object) {
        return receiptItemDAO.create(object);
    }

    @Override
    public boolean update(ReceiptItem object) {
        return receiptItemDAO.update(object);
    }

    @Override
    public boolean delete(ReceiptItem object) {
        return receiptItemDAO.delete(object);
    }

    @Override
    public ReceiptItem findById(long receiptItemId) {
        return receiptItemDAO.findById(receiptItemId);
    }

    @Override
    public List<ReceiptItem> getListByReceipt(int getListByReceipt) {
        return receiptItemDAO.getListByReceipt(getListByReceipt);
    }

}