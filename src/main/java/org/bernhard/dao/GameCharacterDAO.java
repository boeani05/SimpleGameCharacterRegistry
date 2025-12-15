package org.bernhard.dao;

import org.bernhard.game.GameCharacter;
import org.bernhard.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class GameCharacterDAO {
    public void save(GameCharacter character) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(character);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("\nGame Character could not been found!");
        }
    }

    public GameCharacter findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.find(GameCharacter.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<GameCharacter> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<GameCharacter> query = session.createQuery("from game_character", GameCharacter.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(GameCharacter character) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(character);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            GameCharacter character = session.find(GameCharacter.class, id);
            if (character != null) {
                session.remove(character);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
