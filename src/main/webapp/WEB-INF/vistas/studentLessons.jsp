<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Language" content="es">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap"
          rel="stylesheet">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap theme -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- Styles -->
    <link href="css/styles.css" rel="stylesheet">
</head>
<body>
<header class="alumno-color">
    <h1>Bienvenido Alberto!</h1>
    <img src="assets/profile.jpg">
</header>


<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {
        $('select[name="states"]').change(function () {
            $.ajax({
                url: '/lessonsByState?idState=' + $(this).val(),
                type: 'GET',
                success: function (response) {
                    var $responseHtml = $(response);
                    var $newBodyContent = $responseHtml.find('#lessonsContainer').html();
                    $('#lessonsContainer').html($newBodyContent);
                },
                error: function (xhr, status, error) {
                    console.error(error);
                }
            });
        });
    });
</script>


<main>
    <section class="first-section">
        <div class="container table-responsive" style="width: 90%;">
            <label>Filtrar por estado:</label>
            <select id="state" name="states">
                <option value=0>Mostrar todas</option>
                <option value=1>Pendiente</option>
                <option value=2>En Curso</option>
                <option value=3>Finalizada</option>
                <option value=4>Cancelada</option>
            </select>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">Fecha</th>
                    <th scope="col">Lugar</th>
                    <th scope="col">Disciplina</th>
                    <th scope="col">Hora comienzo</th>
                    <th scope="col">Hora final</th>
                    <th scope="col">Lugar</th>
                    <th scope="col">Dificultad</th>
                    <th scope="col">Capacidad</th>
                    <th scope="col">Estado</th>
                    <th scope="col">Cancelar</th>
                    <th scope="col">Calificar</th>
                </tr>
                </thead>
                <tbody id="lessonsContainer">
                <c:forEach var="clase" items="${lessons}">
                    <tr>
                        <td><fmt:formatDate value="${clase.date}" pattern="dd-MM"/></td>
                        <td>${clase.place.name}</td>
                        <td>${clase.discipline.description}</td>
                        <td style="text-align: center">${clase.detail.startHour}</td>
                        <td style="text-align: center">${clase.detail.endHour}</td>
                        <td>${clase.place.name}</td>
                        <td>${clase.difficulty.description}</td>
                        <td style="text-align: center">${clase.detail.capacity}</td>
                        <td>${clase.state.description}</td>
                        <td>
                            <c:if test="${clase.state.description == 'PENDIENTE'}">
                                <button type="button" class="btn btn-primary btn-sm cancel-button"
                                        name="${clase.idClass}" style="margin: 0">X
                                </button>
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${clase.state.description == 'FINALIZADA' && clase.calificated == false}">
                                <a href="calificate?lessonId=${clase.idClass}" type="button" class="btn btn-primary btn-sm calification-button">
                                    ★
                                </a>
                            </c:if>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </section>

</main>

<footer>
    <p>Derechos de autor &copy; 2023 | Mi Página de Inicio</p>
</footer>

</body>
</html>
