package my.projects.recipebook;

import org.springframework.beans.factory.annotation.Value;

public class RecipeBookDAO {
    @Value("${spring.datasource.url}")
    private String url = "jdbc:postgresql://localhost:5432/RecipeBook";
    @Value("${spring.datasource.username}")
    private String username = "postgres";
    @Value("${spring.datasource.password}")
    private String password = "postgres";

    public RecipeBookDAO() {
    }

    public RecipeBookDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
}
