package hello.hellospring.controller;

import hello.hellospring.model.UserProfile;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserProfileController {

    private Map<String, UserProfile> userMap;

    @PostConstruct
    public void init() {
        userMap = new HashMap<String, UserProfile>();
        userMap.put("1", new UserProfile("1","홍길동","111-1111","서울시 강남구 대치1동"));
        userMap.put("2", new UserProfile("2","송예인","11-2222","서울시 강남구 대치2동"));
        userMap.put("3", new UserProfile("3","홍길순","111-1133","서울시 강남구 대치3동"));
    }

    @GetMapping("/user/{id}")
    public UserProfile getUserProfile(@PathVariable("id") String id){
        return userMap.get(id);
    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList(){
        return new ArrayList<UserProfile>(userMap.values());
    }

    @PutMapping("/user/{id}")
    public void putUserProfile(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address) {
        UserProfile userProfile = new UserProfile(id, name, phone, address);
        userMap.put(id,userProfile);
    }
    @PostMapping("/user/{id}")
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("address") String address){
        UserProfile userProfile = userMap.get(id);
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);
    }
    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        userMap.remove(id);
    }


}
