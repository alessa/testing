package app.ui;

import config.WebConsoleConfig;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * User: aconstantin
 * Date: 5/19/14 9:03 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { WebConsoleConfig.class })
public class AccountControllerIntegrationTest {
    public static final String CONSOLE_CONTENT_TYPE = "application/json;charset=UTF-8";

    public static final String ACCOUNT_NUMBER = "12345678901234";

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }

    @Test
    public void testGetAccountByNumber() throws Exception {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("number", "12345678901234");
        String jsonArray = JSONObject.toJSONString(map);

        this.mockMvc.perform(post("/app/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonArray))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alexandra"))
                .andExpect(jsonPath("$.moneyAmount").value(100.00))
                .andExpect(jsonPath("$.number").value(ACCOUNT_NUMBER));
    }

    @Test
    public void testGetAccountByName() throws Exception {


        this.mockMvc.perform(post("/app/accountsByName").param("name" ,"Alexandra" )
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Alexandra"))
                .andExpect(jsonPath("$[0].moneyAmount").value(100.00))
                .andExpect(jsonPath("$[0].number").value(ACCOUNT_NUMBER));
    }

}

