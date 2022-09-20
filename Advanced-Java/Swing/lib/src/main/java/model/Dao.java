package model;

import java.util.List;
import java.util.Optional;

//DAO (Data Access Object) pattern
public interface Dao<T> {
	void save(T t);
	Optional<T> findById(int id);
	void update(T t);
	void delete(T t);
	List<T> getAll();
}
