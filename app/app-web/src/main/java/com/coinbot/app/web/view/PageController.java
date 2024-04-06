package com.coinbot.app.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    /**
     * 메인페이지
     */
    @GetMapping("/")
    public String mainPage() {
        return null;
    }

    /**
     * 매매 보고 페이지
     */
    @GetMapping("/report/trading")
    public String tradingReport() {
        return null;
    }
    
}
