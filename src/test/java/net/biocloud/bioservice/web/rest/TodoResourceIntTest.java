package net.biocloud.bioservice.web.rest;

import net.biocloud.bioservice.Application;
import net.biocloud.bioservice.repository.TodoRepository;
import net.biocloud.bioservice.service.TodoService;
import net.biocloud.bioservice.util.TestUtil;
import net.biocloud.bioservice.web.api.CreateTodoRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.inject.Inject;

/**
 * Test class for TodoResource REST controller.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class TodoResourceIntTest {

    @Inject
    private TodoService todoService;

    private MockMvc restUserMockMvc;

    @Before
    public void setup() {
        TodoResource todoResource = new TodoResource();
        ReflectionTestUtils.setField(todoResource, "todoService", todoService);
        this.restUserMockMvc = MockMvcBuilders.standaloneSetup(todoResource).build();
    }

    @Test
    public void testCreateTodo() throws Exception {
        CreateTodoRequest createTodoRequest = new CreateTodoRequest();
        createTodoRequest.setTask("Test");

        restUserMockMvc.perform(MockMvcRequestBuilders
                .post("/api/todo")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(createTodoRequest))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    public void testListTodoes() throws Exception {
        restUserMockMvc.perform(MockMvcRequestBuilders
                .get("/api/todo")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }
}
