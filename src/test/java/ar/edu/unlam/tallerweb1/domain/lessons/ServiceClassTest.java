package ar.edu.unlam.tallerweb1.domain.lessons;


import ar.edu.unlam.tallerweb1.domain.clase.LessonServiceImpl;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Clase;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Detalle;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Disciplina;
import ar.edu.unlam.tallerweb1.domain.clase.entities.Lugar;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Rol;
import ar.edu.unlam.tallerweb1.infrastructure.*;
import ar.edu.unlam.tallerweb1.domain.usuarios.entities.Usuario;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceClassTest {

    private LessonRepository lessonRepository;
    private RepositorioUsuario userRepository;
    private RepositorioDetalle servicioDetalleDao;
    private RepositorioDisciplina servicioDisciplinaDao;
    private RepositorioDificultad servicioDificultadDao;
    private PlaceRepository servicePlaceDao;
    private HttpServletRequest request;
    private HttpSession sesion;
    private LessonServiceImpl classService;
    @Before
    public void init() {
        lessonRepository = mock(LessonRepository.class);
        userRepository = mock(RepositorioUsuario.class);
        servicioDetalleDao = mock(RepositorioDetalle.class);
        servicioDisciplinaDao = mock(RepositorioDisciplina.class);
        servicioDificultadDao = mock(RepositorioDificultad.class);
        sesion = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        classService = new LessonServiceImpl(this.lessonRepository, this.userRepository, this.servicioDetalleDao, this.servicioDisciplinaDao, this.servicioDificultadDao, this.servicePlaceDao);
    }

    @Test
    public void getLessonsFromProfessor(){

        // Rol Profesor
        Rol roleProfessor = new Rol();
        roleProfessor.setIdRole(3L);
        roleProfessor.setDescription("profesor");

        String email = "pabloantunez@otmial.com";
        String password = "1234";

        // Profesor
        Usuario professor = new Usuario();
        professor.setId(1L);
        professor.setEmail(email);
        professor.setPassword(password);
        professor.setRol(roleProfessor);

        //disciplina
        Disciplina discipline = new Disciplina();
        discipline.setName("Crossfit");

        //detalle
        Detalle detail = new Detalle();
        detail.setStartHour(LocalTime.of(8, 00));
        detail.setEndHour(LocalTime.of(9, 00));

        //clase1
        Clase lesson = new Clase();
        lesson.setIdClass(1);
        lesson.setDiscipline(discipline);
        lesson.setDate(new Date(2023, 06, 24));
        lesson.setDetail(detail);
        lesson.setProfesor(professor);

        //clase2
        Clase lesson2 = new Clase();
        lesson2.setIdClass(1);
        lesson2.setDiscipline(discipline);
        lesson2.setDate(new Date(2024, 10, 30));
        lesson2.setDetail(detail);
        lesson2.setProfesor(professor);

        //lugar
        Lugar place = new Lugar();
        place.setIdPlace(1L);

        List<Clase> lessonList = new ArrayList<>();
        lessonList.add(lesson);
        lessonList.add(lesson2);
        when(userRepository.getUserById(professor.getId())).thenReturn(professor);
        when(lessonRepository.getClassesByProfessorId(professor)).thenReturn(lessonList);
        List<Clase> lessonResult = classService.getLessonsByProfessorId(professor.getId());

        assertThat(lessonResult).isNotNull();
        assertThat(lessonResult).isNotEmpty();
        assertThat(lessonResult).contains(lesson);
        assertThat(lessonResult).extracting("professor").contains(professor);

    }
}