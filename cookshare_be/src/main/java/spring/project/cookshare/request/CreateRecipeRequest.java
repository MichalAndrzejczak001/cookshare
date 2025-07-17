package spring.project.cookshare.request;

import lombok.Data;
import spring.project.cookshare.model.Recipe;
import spring.project.cookshare.model.User;

@Data
public class CreateRecipeRequest {
    private Recipe recipe;
    private User user;
}
