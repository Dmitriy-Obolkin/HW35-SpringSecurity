package ua.ithillel.repo;

import lombok.RequiredArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ua.ithillel.model.entity.Order;
import ua.ithillel.util.HibernateTransactionManager;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderMySqlJpaRepo implements OrderRepo{
    private final HibernateTransactionManager transactionManager;

    @Override
    public List<Order> getAll() {
        return transactionManager.executeInsideTransaction(session -> {
            Query<Order> query = session.createQuery("from Order", Order.class);
            return query.getResultList();
        });
    }

    @Override
    public Order getById(Integer id) {
        validateId(id);
        return transactionManager.executeInsideTransaction(session -> session.find(Order.class, id));
    }

    @Override
    public Order add(Order order) {
        return transactionManager.executeInsideTransaction(session -> {
            Order managedOrder = session.merge(order);
            session.flush();
            return managedOrder;
        });
    }

    @Override
    public boolean remove(Order order) {
        return transactionManager.executeInsideTransaction(session -> {
            session.remove(order);
            return true;
        });
    }

    private void validateId(Integer id) {
        if (id < 0) {
            throw new IllegalArgumentException("Invalid ID: ID should not be negative. Given ID: " + id);
        }
    }
}
