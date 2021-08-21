package com.teamvietdev.dao;

import com.teamvietdev.model.Receipt;
import java.util.List;

/**
 *
 * @author TVD
 */
public interface ReceiptDAO {
    
    // create
    public boolean create(Receipt object);

    // update
    public boolean update(Receipt object);

    // delete
    public boolean delete(Receipt object);

    // find by id
    public Receipt findById(long receiptId);

    // load list receipt
    public List<Receipt> getAll();
    
}
