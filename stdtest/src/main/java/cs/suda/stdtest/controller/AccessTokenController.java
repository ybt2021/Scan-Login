package cs.suda.stdtest.controller;

import cs.suda.stdtest.entity.AccessToken;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
public class AccessTokenController {
    AccessToken accessToken = new AccessToken();
    @GetMapping(value = "/login")
    public String index(){
        accessToken.setAccessToken();
        return "login";
    }
    @GetMapping(value = "/getCode")
    public String getCode(@RequestParam("code") String code,@RequestParam("state") String state,Model model){
        JSONObject jsonObject = accessToken.getUserInformation(code);
        model.addAttribute("name", jsonObject.getString("name"));
        model.addAttribute("avatar", jsonObject.getString("avatar"));
        return "head_portrait";
    }
    @GetMapping(value = "/two_dim_code")
    public String getTwoDimCode(){
        return "two_dim_code";
    }
}
