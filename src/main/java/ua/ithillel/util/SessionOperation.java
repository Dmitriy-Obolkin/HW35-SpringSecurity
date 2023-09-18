package ua.ithillel.util;

import org.hibernate.Session;

public interface SessionOperation<T> {
    T execute(Session session);
}
