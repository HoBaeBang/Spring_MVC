package beginners.project.controller;

import beginners.project.domain.User;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class UserController {

    @GetMapping
    public JSONPObject homeLogin(@RequestBody User user) {

        
        JSONPObject jsonpObject = new JSONPObject("userStatus", "welcome");
        return jsonpObject;
    }
}
