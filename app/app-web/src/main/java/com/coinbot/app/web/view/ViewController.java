package com.coinbot.app.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    /**
     * 메인 페이지
     */
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    /**
     * 매매 보고 페이지
     */
    @GetMapping("/report/trading")
    public String tradingReport() {
        return "trading.html";
    }
    
}
