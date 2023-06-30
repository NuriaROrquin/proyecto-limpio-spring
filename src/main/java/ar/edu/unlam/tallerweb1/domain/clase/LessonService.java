package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.List;

public interface LessonService {

    List<Clase> getLessonsByStudentId(Long idStudent);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor, Long idProfessor);

    List<Clase> getLessonsDependingStateFromProfessor(Long id, Long idState);

    List<Clase> cancelLesson(Long lessonId, Long userId);
}
