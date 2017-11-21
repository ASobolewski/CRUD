package com.recruitment.crud;

import java.util.List;
import java.util.function.Predicate;

public interface Database<T> {
   void insert(T entity);
   void update(T entity);
   void delete(T entity);
   List<T> select(Predicate<T> p);
}
