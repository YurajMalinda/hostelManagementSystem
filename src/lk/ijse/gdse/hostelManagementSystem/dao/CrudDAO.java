package lk.ijse.gdse.hostelManagementSystem.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {
    public ArrayList<T> getAll();
    public boolean delete(String id);
    public boolean add(T entity);
    public boolean update(T entity);
    public String getCurrentId();
}
