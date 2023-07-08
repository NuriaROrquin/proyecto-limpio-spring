package ar.edu.unlam.tallerweb1.infrastructure;

import ar.edu.unlam.tallerweb1.domain.association.entities.Preferencias;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("preferencesRepository")
public class PreferencesRepositoryImpl implements PreferencesRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public PreferencesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public void create(Usuario user, Disciplina discipline) {

        Preferencias preferences = new Preferencias();

        preferences.setUser(user);
        preferences.setDiscipline(discipline);

        sessionFactory.getCurrentSession().save(preferences);
    }

    @Override
    public Preferencias getPreferencesByUser(Usuario user){
        return null;
    }
}