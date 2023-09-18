package ua.ithillel.util;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HibernateTransactionManager {
    private final SessionFactory sessionFactory;

    public <T> T executeInsideTransaction(SessionOperation<T> operation){
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            T result = operation.execute(session);

            transaction.commit();

            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }
}
