package my.projects.recipebook.controllers;

import my.projects.recipebook.models.AppUser;
import my.projects.recipebook.services.appuser.AppUserService;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(AppUserController.class)
class AppUserControllerTest {

    @Mock
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    //In this case this is some dummy data
    private List<AppUser> userList = null;
    private AppUser userOne = null;
    private String userJsonString = null;

    @Before
    public void setUp(){
        appUserService.add()
        this.userList = Arrays.asList(new AppUser(), new AppUser(), new AppUser());

        this.studentOne = new Student(1, Master, firstName1, className1);
        this.studentJsonString = new ObjectMapper().writeValueAsString(studentOne);
    }


    @org.junit.jupiter.api.Test
    void findAll() {
    }

    @org.junit.jupiter.api.Test
    void findById() {
    }

    @org.junit.jupiter.api.Test
    void add() {
    }

    @org.junit.jupiter.api.Test
    void update() {
    }

    @org.junit.jupiter.api.Test
    void groceryListUpdate() {
    }
}