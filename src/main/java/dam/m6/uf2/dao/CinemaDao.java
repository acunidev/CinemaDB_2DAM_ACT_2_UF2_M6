package dam.m6.uf2.dao;

import java.io.Serializable;
import java.util.List;

public interface CinemaDao<T, K extends Serializable> {

  int add(T type);

  void delete(K id);

  T getData(K id);

  List<T> getAllData();

  void update(T type);

  boolean checkIfExistData(T type);

}
