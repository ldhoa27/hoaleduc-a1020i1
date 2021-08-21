package com.teamvietdev.service;

import com.teamvietdev.dao.ReceiptDAO;
import com.teamvietdev.model.Receipt;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TVD
 */
@Service
public class ReceiptServiceImpl implements ReceiptService {

    @Autowired
    private ReceiptDAO receiptDAO;

    @Override
    public boolean create(Receipt object) {
        return receiptDAO.create(object);
    }

    @Override
    public boolean update(Receipt object) {
        return receiptDAO.update(object);
    }

    @Override
    public boolean delete(Receipt object) {
        return receiptDAO.delete(object);
    }

    @Override
    public Receipt findById(long receiptId) {
        return receiptDAO.findById(receiptId);
    }

    @Override
    public List<Receipt> getAll() {
        return receiptDAO.getAll();
    }

}
