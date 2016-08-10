package com.stunny.vogel.campusvirtual.Logica;



/**
 * Created by alex on 5/08/16.
 */
public interface DefaultContent {
    String default_subjects = "[\n" +
            "  {\n" +
            "    \"name\" : \"Fotografia\",\n" +
            "    \"descripcion\": \"Nociones y teoría acerca de la fotografia.\",\n" +
            "    \"iconPath\": \"\",\n" +
            "    \"themes\":[\n" +
            "      {\"theme_name\": \"Planos\"},\n" +
            "      {\"theme_name\": \"Tipos de cámara\"},\n" +
            "      {\"theme_name\": \"Historia\"},\n" +
            "      {\"theme_name\": \"Lentes\"},\n" +
            "      {\"theme_name\": \"ISO\"},\n" +
            "      {\"theme_name\": \"Exposicion\"}\n" +
            "    ],\n" +
            "    \"students\": [\n" +
            "      {\"student_name\": \"Mary Jane Watson\"},\n" +
            "      {\"student_name\": \"Slade Wilson\"}\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Android\",\n" +
            "    \"descripcion\": \"Fundamentos basicos y avanzados acerca de la programación de aplicaciones para el SO Android.\",\n" +
            "    \"iconPath\": \"\",\n" +
            "    \"themes\":[\n" +
            "      {\"theme_name\": \"Layouts\"},\n" +
            "      {\"theme_name\": \"Navegacion\"},\n" +
            "      {\"theme_name\": \"Fragmentacion\"},\n" +
            "      {\"theme_name\": \"Componentes visuales\"},\n" +
            "      {\"theme_name\": \"Debugging\"},\n" +
            "      {\"theme_name\": \"APK building\"}\n" +
            "    ],\n" +
            "    \"students\": [\n" +
            "      {\"student_name\": \"Bruce Wayne\"},\n" +
            "      {\"student_name\": \"John Doe\"}\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"D.P.O.O.\",\n" +
            "    \"descripcion\": \"Diseño y programación orientados a objetos con Java.\",\n" +
            "    \"iconPath\": \"\",\n" +
            "    \"themes\":[\n" +
            "      {\"theme_name\": \"Orientación a objetos\"},\n" +
            "      {\"theme_name\": \"MVC\"},\n" +
            "      {\"theme_name\": \"AWT/Swing\"},\n" +
            "      {\"theme_name\": \"Subprocesamiento multiple\"},\n" +
            "      {\"theme_name\": \"Interconexión mediante sockets\"},\n" +
            "      {\"theme_name\": \"Excepciones\"}\n" +
            "    ],\n" +
            "    \"students\": [\n" +
            "      {\"student_name\": \"Jane Doe\"},\n" +
            "      {\"student_name\": \"Slade Wilson\"}\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Metodología y tecnología de la programación\",\n" +
            "    \"descripcion\": \"Iniciación a la programación con el lenguaje C.\",\n" +
            "    \"iconPath\": \"\",\n" +
            "    \"themes\":[\n" +
            "      {\"theme_name\": \"Pseudocodigo\"},\n" +
            "      {\"theme_name\": \"Diagramas\"},\n" +
            "      {\"theme_name\": \"C basico\"},\n" +
            "      {\"theme_name\": \"Programacion estructurada\"},\n" +
            "      {\"theme_name\": \"Memoria dinamica\"},\n" +
            "      {\"theme_name\": \"Recursividad\"}\n" +
            "    ],\n" +
            "    \"students\": [\n" +
            "      {\"student_name\": \"Bruce Wayne\"},\n" +
            "      {\"student_name\": \"John Doe\"}\n" +
            "    ]\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\" : \"Bases de Datos\",\n" +
            "    \"descripcion\": \"Diseño e implementación de bases de datos con el SGBD MySQL.\",\n" +
            "    \"iconPath\": \"\",\n" +
            "    \"themes\":[\n" +
            "      {\"theme_name\": \"Modelo E:R\"},\n" +
            "      {\"theme_name\": \"Modelo Relacional\"},\n" +
            "      {\"theme_name\": \"Modelo fisico\"},\n" +
            "      {\"theme_name\": \"Seguridad\"},\n" +
            "      {\"theme_name\": \"Procedimientos\"},\n" +
            "      {\"theme_name\": \"Concurrencia\"}\n" +
            "    ],\n" +
            "    \"students\": [\n" +
            "      {\"student_name\": \"Slade Wilson\"},\n" +
            "      {\"student_name\": \"Mary Jane Watson\"}\n" +
            "    ]\n" +
            "  }\n" +
            "]";
    String default_students = "[\n" +
            "  {\n" +
            "    \"name\": \"Slade Wilson\",\n" +
            "    \"birth_day\": 5,\n" +
            "    \"birth_month\": 10,\n" +
            "    \"birth_year\": 1963,\n" +
            "    \"degree\": \"Multimedia\",\n" +
            "    \"photoPath\": \"\",\n" +
            "    \"gender\": \"Masculino\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"John Doe\",\n" +
            "    \"birth_day\": 1,\n" +
            "    \"birth_month\": 1,\n" +
            "    \"birth_year\": 1990,\n" +
            "    \"degree\": \"Informatica\",\n" +
            "    \"photoPath\": \"\",\n" +
            "    \"gender\": \"Masculino\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Jane Doe\",\n" +
            "    \"birth_day\": 2,\n" +
            "    \"birth_month\": 2,\n" +
            "    \"birth_year\": 1992,\n" +
            "    \"degree\": \"A.D.E.\",\n" +
            "    \"photoPath\": \"\",\n" +
            "    \"gender\": \"Femenino\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Bruce Wayne\",\n" +
            "    \"birth_day\": 27,\n" +
            "    \"birth_month\": 5,\n" +
            "    \"birth_year\": 1939,\n" +
            "    \"degree\": \"Electronica\",\n" +
            "    \"photoPath\": \"\",\n" +
            "    \"gender\": \"Masculino\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"name\": \"Mary Jane Watson\",\n" +
            "    \"birth_day\": 7,\n" +
            "    \"birth_month\": 11,\n" +
            "    \"birth_year\": 1986,\n" +
            "    \"degree\": \"Animacion\",\n" +
            "    \"photoPath\": \"\",\n" +
            "    \"gender\": \"Masculino\"\n" +
            "  }\n" +
            "]";
    String default_exams = "[\n" +
            "  {\n" +
            "    \"dia\": 21,\n" +
            "    \"mes\": 8,\n" +
            "    \"año\": 2016,\n" +
            "    \"hora\": 23,\n" +
            "    \"minuto\": 55,\n" +
            "    \"segundo\": 0,\n" +
            "    \"grado\": \"Informatica\",\n" +
            "    \"subject_name\": \"Android\",\n" +
            "    \"aula\": \"Aula 4\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"dia\": 3,\n" +
            "    \"mes\": 9,\n" +
            "    \"año\": 2016,\n" +
            "    \"hora\": 12,\n" +
            "    \"minuto\": 0,\n" +
            "    \"segundo\": 0,\n" +
            "    \"grado\": \"Multimedia\",\n" +
            "    \"subject_name\": \"Fotografia\",\n" +
            "    \"aula\": \"Aula 1\"\n" +
            "  }\n" +
            "]\n";
}
