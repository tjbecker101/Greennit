package com.greennit.CS3141.testPage;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "\n" +
                "<head>\n" +
                "\n" +
                "  <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T\" crossorigin=\"anonymous\">\n" +
                "\n" +
                "  <meta charset=\"utf-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
                "  <meta name=\"description\" content=\"\">\n" +
                "  <meta name=\"author\" content=\"\">\n" +
                "\n" +
                "  <title>Blog Home - Start Bootstrap Template</title>\n" +
                "\n" +
                "  <!-- Bootstrap core CSS -->\n" +
                "  <link href=\"vendor/bootstrap/css/bootstrap.min.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "  <!-- Custom styles for this template -->\n" +
                "  <link href=\"css/blog-home.css\" rel=\"stylesheet\">\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "  <!-- Navigation -->\n" +
                "  <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark fixed-top\">\n" +
                "    <div class=\"container\">\n" +
                "      <a class=\"navbar-brand\" href=\"#\">Start Bootstrap</a>\n" +
                "      <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarResponsive\" aria-controls=\"navbarResponsive\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                "        <span class=\"navbar-toggler-icon\"></span>\n" +
                "      </button>\n" +
                "      <div class=\"collapse navbar-collapse\" id=\"navbarResponsive\">\n" +
                "        <ul class=\"navbar-nav ml-auto\">\n" +
                "          <li class=\"nav-item active\">\n" +
                "            <a class=\"nav-link\" href=\"#\">Home\n" +
                "              <span class=\"sr-only\">(current)</span>\n" +
                "            </a>\n" +
                "          </li>\n" +
                "          <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"#\">About</a>\n" +
                "          </li>\n" +
                "          <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"#\">Services</a>\n" +
                "          </li>\n" +
                "          <li class=\"nav-item\">\n" +
                "            <a class=\"nav-link\" href=\"#\">Contact</a>\n" +
                "          </li>\n" +
                "        </ul>\n" +
                "      </div>\n" +
                "    </div>\n" +
                "  </nav>\n" +
                "\n" +
                "  <!-- Page Content -->\n" +
                "  <div class=\"container\">\n" +
                "\n" +
                "    <div class=\"row\">\n" +
                "\n" +
                "      <!-- Blog Entries Column -->\n" +
                "      <div class=\"col-md-8\">\n" +
                "\n" +
                "        <h1 class=\"my-4\">Page Heading\n" +
                "          <small>Secondary Text</small>\n" +
                "        </h1>\n" +
                "\n" +
                "        <!-- Blog Post -->\n" +
                "        <div class=\"card mb-4\">\n" +
                "          <img class=\"card-img-top\" src=\"http://placehold.it/750x300\" alt=\"Card image cap\">\n" +
                "          <div class=\"card-body\">\n" +
                "            <h2 class=\"card-title\">Post Title</h2>\n" +
                "            <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>\n" +
                "            <a href=\"#\" class=\"btn btn-primary\">Read More &rarr;</a>\n" +
                "          </div>\n" +
                "          <div class=\"card-footer text-muted\">\n" +
                "            Posted on January 1, 2020 by\n" +
                "            <a href=\"#\">Start Bootstrap</a>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Blog Post -->\n" +
                "        <div class=\"card mb-4\">\n" +
                "          <img class=\"card-img-top\" src=\"http://placehold.it/750x300\" alt=\"Card image cap\">\n" +
                "          <div class=\"card-body\">\n" +
                "            <h2 class=\"card-title\">Post Title</h2>\n" +
                "            <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>\n" +
                "            <a href=\"#\" class=\"btn btn-primary\">Read More &rarr;</a>\n" +
                "          </div>\n" +
                "          <div class=\"card-footer text-muted\">\n" +
                "            Posted on January 1, 2020 by\n" +
                "            <a href=\"#\">Start Bootstrap</a>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Blog Post -->\n" +
                "        <div class=\"card mb-4\">\n" +
                "          <img class=\"card-img-top\" src=\"http://placehold.it/750x300\" alt=\"Card image cap\">\n" +
                "          <div class=\"card-body\">\n" +
                "            <h2 class=\"card-title\">Post Title</h2>\n" +
                "            <p class=\"card-text\">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reiciendis aliquid atque, nulla? Quos cum ex quis soluta, a laboriosam. Dicta expedita corporis animi vero voluptate voluptatibus possimus, veniam magni quis!</p>\n" +
                "            <a href=\"#\" class=\"btn btn-primary\">Read More &rarr;</a>\n" +
                "          </div>\n" +
                "          <div class=\"card-footer text-muted\">\n" +
                "            Posted on January 1, 2020 by\n" +
                "            <a href=\"#\">Start Bootstrap</a>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Pagination -->\n" +
                "        <ul class=\"pagination justify-content-center mb-4\">\n" +
                "          <li class=\"page-item\">\n" +
                "            <a class=\"page-link\" href=\"#\">&larr; Older</a>\n" +
                "          </li>\n" +
                "          <li class=\"page-item disabled\">\n" +
                "            <a class=\"page-link\" href=\"#\">Newer &rarr;</a>\n" +
                "          </li>\n" +
                "        </ul>\n" +
                "\n" +
                "      </div>\n" +
                "\n" +
                "      <!-- Sidebar Widgets Column -->\n" +
                "      <div class=\"col-md-4\">\n" +
                "\n" +
                "        <!-- Search Widget -->\n" +
                "        <div class=\"card my-4\">\n" +
                "          <h5 class=\"card-header\">Search</h5>\n" +
                "          <div class=\"card-body\">\n" +
                "            <div class=\"input-group\">\n" +
                "              <input type=\"text\" class=\"form-control\" placeholder=\"Search for...\">\n" +
                "              <span class=\"input-group-append\">\n" +
                "                <button class=\"btn btn-secondary\" type=\"button\">Go!</button>\n" +
                "              </span>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Categories Widget -->\n" +
                "        <div class=\"card my-4\">\n" +
                "          <h5 class=\"card-header\">Categories</h5>\n" +
                "          <div class=\"card-body\">\n" +
                "            <div class=\"row\">\n" +
                "              <div class=\"col-lg-6\">\n" +
                "                <ul class=\"list-unstyled mb-0\">\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">Web Design</a>\n" +
                "                  </li>\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">HTML</a>\n" +
                "                  </li>\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">Freebies</a>\n" +
                "                  </li>\n" +
                "                </ul>\n" +
                "              </div>\n" +
                "              <div class=\"col-lg-6\">\n" +
                "                <ul class=\"list-unstyled mb-0\">\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">JavaScript</a>\n" +
                "                  </li>\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">CSS</a>\n" +
                "                  </li>\n" +
                "                  <li>\n" +
                "                    <a href=\"#\">Tutorials</a>\n" +
                "                  </li>\n" +
                "                </ul>\n" +
                "              </div>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <!-- Side Widget -->\n" +
                "        <div class=\"card my-4\">\n" +
                "          <h5 class=\"card-header\">Side Widget</h5>\n" +
                "          <div class=\"card-body\">\n" +
                "            You can put anything you want inside of these side widgets. They are easy to use, and feature the new Bootstrap 4 card containers!\n" +
                "          </div>\n" +
                "        </div>\n" +
                "\n" +
                "      </div>\n" +
                "\n" +
                "    </div>\n" +
                "    <!-- /.row -->\n" +
                "\n" +
                "  </div>\n" +
                "  <!-- /.container -->\n" +
                "\n" +
                "  <!-- Footer -->\n" +
                "  <footer class=\"py-5 bg-dark\">\n" +
                "    <div class=\"container\">\n" +
                "      <p class=\"m-0 text-center text-white\">Copyright &copy; Your Website 2020</p>\n" +
                "    </div>\n" +
                "    <!-- /.container -->\n" +
                "  </footer>\n" +
                "\n" +
                "  <!-- Bootstrap core JavaScript -->\n" +
                "  <script src=\"vendor/jquery/jquery.min.js\"></script>\n" +
                "  <script src=\"vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n" +
                "\n" +
                "  <script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\" integrity=\"sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo\" crossorigin=\"anonymous\"></script>\n" +
                "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\" integrity=\"sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1\" crossorigin=\"anonymous\"></script>\n" +
                "  <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\" integrity=\"sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM\" crossorigin=\"anonymous\"></script>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n");

    }

    public void destroy() {
    }
}