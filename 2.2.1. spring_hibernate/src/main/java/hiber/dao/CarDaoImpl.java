package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImpl implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car");
        return query.getResultList();
    }

    @Override
    public User getUserByCar(Car car) {
        List<Car> list = sessionFactory.getCurrentSession().createQuery("from Car where model='" + car.getModel() + "' and series='" + car.getSeries() + "'").getResultList();
        if (list.size() == 0) {
            System.out.println("Авто с такими данными отсутсвует в БД");
            return null;
        } else {
            Car car1 = list.get(0);
            return car1.getUser();
        }
    }
}
