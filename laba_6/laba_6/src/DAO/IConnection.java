package DAO;

import java.util.List;

public interface IConnection {
    public List<String> getProperties();
    public Boolean getConnection();
    public  void closeConnection();
}
