package com.example.lb1servlet;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Этот класс представляет собой простой сервлет, который принимает категорию и номер изображения,
// и возвращает путь к соответствующему изображению.
@WebServlet(name = "ImageServlet", urlPatterns = {"/image-servlet"}, loadOnStartup = 1)
public class HelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Массив строк, содержащий пути к изображениям природы
    private final String[] tableImages = {"img/tables/placemats.jpeg", "img/tables/tree.jpeg", "img/tables/glass.jpeg", "img/tables/ceramique.jpeg", "img/tables/metal.jpeg"};
    // Массив строк, содержащий пути к изображениям автомобилей
    private final String[] carImages = {"img/cars/lada.jpeg", "img/cars/toyota.jpeg", "img/cars/bmw.jpeg", "img/cars/kia.jpeg", "img/cars/hyundai.jpeg"};
    // Массив строк, содержащий пути к изображениям животных
    private final String[] animalImages = {"img/animals/chinchilla.jpeg", "img/animals/cat.jpeg", "img/animals/dog.jpeg", "img/animals/capybara.jpeg", "img/animals/zebra.jpeg"};

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Получаем параметры категории и номера изображения из запроса
        String selectedCategory = req.getParameter("selectedCategory");
        int chosenNumber = Integer.parseInt(req.getParameter("chosenNumber"));

        // Получаем путь к изображению, соответствующий выбранной категории и номеру
        String imagePath = getImagePath(selectedCategory, chosenNumber);

        // Устанавливаем путь к изображению как атрибут запроса
        req.setAttribute("imagePath", imagePath);
        // Перенаправляем запрос на JSP-страницу для отображения изображения
        req.getRequestDispatcher("/imageDisplay.jsp").forward(req, resp);
    }

    // Метод для получения пути к изображению по выбранной категории и номеру
    private String getImagePath(String category, int number) {
        switch (category) {
            case "trees":
                return tableImages[number];
            case "cars":
                return carImages[number];
            case "animals":
                return animalImages[number];
            default:
                return null; // Возвращаем null, если категория не распознана
        }
    }
}