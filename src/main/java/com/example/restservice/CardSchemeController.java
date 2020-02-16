package com.example.restservice;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CardSchemeController {
    private final AtomicLong counter = new AtomicLong();
    private final Map<String, Long> statMap = new HashMap<String, Long>(); 


    @GetMapping("/card-scheme/verify/")
    @ResponseBody
    public String verify() {
        counter.incrementAndGet();
        return "Card No: not provided";
    }

    @GetMapping("/card-scheme/verify/{card}")
    public VerifyResponse verify(@PathVariable final String card) {
        final String uri = "https://lookup.binlist.net/" + card;
        Payload payload;
        Boolean success = false;

        counter.incrementAndGet();
        final Long count = statMap.containsKey(card) ? statMap.get(card) : 0;
        statMap.put(card, count + 1);

        try {
            final RestTemplate restTemplate = new RestTemplate();
            final Map<String, Object> response = restTemplate.getForEntity(uri, Map.class).getBody();

            final String scheme = (String) response.get("scheme");
            final String type = (String) response.get("type");
            final Map<String, String> bank = (Map<String, String>) response.get("bank");

            success = true;
            payload = new Payload(scheme, type, bank.get("name"));
        } catch (final Exception e) {
            System.out.println("ERROR: " + e);
            payload = null;
        }

        return new VerifyResponse(success, payload);
    }

    @GetMapping("/card-scheme/stats")
    public Stats stats(@RequestParam("start") final int start, @RequestParam("limit") final int limit) {
        final int size = statMap.size();
        final Map<String, Object> payload = new HashMap<String,Object>();

        final Iterator i = statMap.entrySet().iterator();
        int index = 1;
        while (i.hasNext()) {
            final Map.Entry me = (Map.Entry) i.next();            

            if (index >= start && payload.size() < limit){
                payload.put(
                    (String)me.getKey(), 
                    (Object)me.getValue());    
            }  
            index++;
        }

        return new Stats(start,limit,size,payload);
	}
}
