package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> suppliers = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    public static void setInstance(SupplierDaoMem instance) {
        SupplierDaoMem.instance = instance;
    }

    @Override
    public void add(Supplier supplier) {
        supplier.setId(suppliers.size() + 1);
        suppliers.add(supplier);
    }

    @Override
    public Supplier find(int id) {
        return suppliers.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void remove(int id) {
        suppliers.remove(find(id));
    }

    @Override
    public List<Supplier> getAll() {
        return suppliers;
    }

    @Override
    public String toString() {
        return "SupplierDaoMem{" +
                "suppliers=" + suppliers +
                '}';
    }
}
