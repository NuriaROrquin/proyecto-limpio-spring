package ar.edu.unlam.tallerweb1.delivery;

import ar.edu.unlam.tallerweb1.delivery.models.DataCalification;
import ar.edu.unlam.tallerweb1.delivery.models.DataLesson;
import ar.edu.unlam.tallerweb1.domain.association.entities.AlumnoClase;
import ar.edu.unlam.tallerweb1.domain.association.entities.Calificacion;
import ar.edu.unlam.tallerweb1.domain.lesson.LessonService;
import ar.edu.unlam.tallerweb1.domain.lesson.entities.*;
import ar.edu.unlam.tallerweb1.domain.user.entities.Rol;
import ar.edu.unlam.tallerweb1.domain.user.entities.Usuario;
import ar.edu.unlam.tallerweb1.helpers.BasicData;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

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

public class LessonControllerTest {

    private LessonController lessonController;
    private LessonService lessonService;
    private HttpServletRequest request;
    private HttpSession session;

    @Before
    public void init(){
        lessonService = mock(LessonService.class);
        session = mock(HttpSession.class);
        request = mock(HttpServletRequest.class);
        lessonController = new LessonController(this.lessonService);
    }

    @Test
    public void havingAProfessorIdShouldShowTheirLessons(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createLesson(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(professor.getId());
        when(session.getAttribute("ROLE")).thenReturn(role.getIdRole());
        ModelAndView view = lessonController.getLessons(request);
        when(lessonService.getLessonsByProfessorId(professor.getId())).thenReturn(expectingLessons);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");

    }

    @Test
    public void havingALessonStateShouldShowLessonsReferingThatStateWithRoleProfessor(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"profesor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"Pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        Clase lesson2 = data.createLesson(new Date(2023,11,10), new Date(2023,11,10),new Date(2024,05,30), detail, place, difficulty, discipline, professor, state);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);
        expectingLessons.add(lesson2);
        DataLesson dataLesson = new DataLesson();
        dataLesson.setIdState(1L);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(professor.getId());
        when(session.getAttribute("ROLE")).thenReturn(role.getIdRole());
        when(lessonService.getLessonsByState(professor.getId(),state.getIdState())).thenReturn(expectingLessons);
        ModelAndView view = lessonController.getLessonsByStateId(request, dataLesson);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void havingAStudentIdShouldShowTheirLessons(){
        BasicData data = new BasicData();
        Rol role = data.createRole(1L,"profesor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = data.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = data.createDifficulty(1L, "Avanzado");
        Disciplina discipline = data.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = data.setHourMinutes(2,30);
        LocalTime endTime = data.setHourMinutes(4,00);
        Detalle detail = data.createDetail(1L,startTime,endTime,50 );
        Estado state = data.createState(1L,"pendiente");
        Clase lesson = data.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        Rol roleStudent = data.createRole(2L,"alumno");
        Usuario student = data.createUser(1L,"alumno@unlam.com","1234","Juan", roleStudent, true);

        List<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        // Metodos
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(student.getId());
        when(session.getAttribute("ROLE")).thenReturn(roleStudent.getIdRole());
        when(lessonService.getLessonsByStudentId(student.getId())).thenReturn(expectingLessons);

        ModelAndView view = lessonController.getLessons(request);

        // Asserts
        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("studentLessons");

    }

    @Test
    public void havingALessonIdAndProfessorIdShouldCancelTheLesson(){
        BasicData data = new BasicData();
        Rol role = data.createRole(3L,"professor");
        Usuario professor = data.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute(any())).thenReturn(professor.getId());
        ModelAndView view = lessonController.cancelLesson(request, new DataLesson());

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("professorLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void havingALessonStateShouldShowLessonsReferingThatStateWithRoleStudent(){
        BasicData basicData = new BasicData();
        Rol role = basicData.createRole(3L,"profesor");
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", role, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);

        DataLesson dataLesson = new DataLesson();
        dataLesson.setIdState(3L);

        ArrayList<Clase> expectingLessons = new ArrayList<>();
        expectingLessons.add(lesson);

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(1L);
        when(session.getAttribute("ROLE")).thenReturn(2L);
        ModelAndView view = lessonController.getLessonsByStateId(request, dataLesson);
        when(lessonService.getLessonsByState(any(),any())).thenReturn(expectingLessons);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("studentLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();

    }

    @Test
    public void wantingToCalificateALessonShouldLetCalitificateItByStudent(){
        BasicData basicData = new BasicData();
        Rol professorRole = basicData.createRole(3L,"profesor");
        Rol studentRole = basicData.createRole(2L,"alumno");
        Usuario student = basicData.createUser(2L, "alumno@unlam.com","1234","Pepe",studentRole,true);
        Usuario professor = basicData.createUser(1L,"profesor@unlam.com","1234","Juan", professorRole, true);
        Lugar place = basicData.createPlace(1L,34615743L, 58503336L, "Un lugar unico","Club Buenos Aires");
        Dificultad difficulty = basicData.createDifficulty(1L, "Avanzado");
        Disciplina discipline = basicData.createDiscipline(1L,"Crossfit", "Entrena tu cuerpo al maximo", 18, 40);
        LocalTime startTime = basicData.setHourMinutes(2,30);
        LocalTime endTime = basicData.setHourMinutes(4,00);
        Detalle detail = basicData.createDetail(1L,startTime,endTime,50 );
        Estado state = basicData.createState(1L,"Pendiente");
        Clase lesson = basicData.createLesson(new Date(2023,12,30), new Date(2023,10,20),new Date(2024,12,31), detail, place, difficulty, discipline, professor, state);
        AlumnoClase studentLesson = basicData.createStudentLesson(1,student,lesson);
        Calificacion calification = basicData.createCalification(1L,"Muy buena clase!",5,student,lesson);

        List<Clase> lessons = new ArrayList<>();
        lessons.add(lesson);

        DataLesson dataLesson = new DataLesson();
        dataLesson.setLessonId(lesson.getIdClass());

        DataCalification dataCalification = new DataCalification();
        dataCalification.setCalificationId(calification.getIdCalification());

        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("USER_ID")).thenReturn(1L);
        when(lessonService.calificateLessonByStudent(any(),any(),any())).thenReturn(lessons);
        ModelAndView view = lessonController.calificateLessonByStudent(request, dataLesson, dataCalification);

        assertThat(view).isNotNull();
        assertThat(view.getViewName()).isNotEmpty();
        assertThat(view.getViewName()).isEqualTo("studentLessons");
        assertThat(view.getModelMap()).isNotNull();
        assertThat(view.getModelMap()).isNotEmpty();
    }
}


