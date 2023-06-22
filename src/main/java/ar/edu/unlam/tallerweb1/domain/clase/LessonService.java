package ar.edu.unlam.tallerweb1.domain.clase;

import ar.edu.unlam.tallerweb1.delivery.models.DatosRegisterLessonProfessor;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;

import java.util.List;

public interface LessonService {

    List<AlumnoClase> getClassesByIdAlumno(Usuario alumno);

    List<Clase> getLessonsByProfessorId(Long id);

    void registerLesson(DatosRegisterLessonProfessor datosRegisterLessonProfessor, Long idProfessor);
}