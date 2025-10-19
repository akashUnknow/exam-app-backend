    package com.codesnippet.OAuthDemo.controller;

    import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api")
    public class DemoController {

        @GetMapping("/public")
        public String publicApi() {
            return "This is a public API";
        }

        @GetMapping("/private")
        public String privateApi(OAuth2AuthenticationToken token) {
            return "Authenticated "+token.getPrincipal().getAttribute("email");
        }
        @GetMapping("/")
        public String publicApiHome() {
            return "home";
        }
    }
