package com.eazybytes.sboot.controller;

import com.eazybytes.sboot.model.Holiday;
import com.eazybytes.sboot.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Controller
public class HolidaysController {
    @Autowired
    private HolidaysRepository holidaysRepository;

//    @RequestParam(required = false) boolean festival,
//    @RequestParam(required = false) boolean federal,
    @GetMapping("/holidays/{dispaly}")
    public String displayHolidays( @PathVariable String dispaly, Model model) {
//        model.addAttribute("federal",federal);
//        model.addAttribute("festival",festival);

        if( null != dispaly && dispaly.equals("all")){
            model.addAttribute("federal",true);
            model.addAttribute("festival",true);
        } else if (null != dispaly && dispaly.equals("federal")) {
            model.addAttribute("federal",true);
//            model.addAttribute("festival",false);
        } else if (null != dispaly && dispaly.equals("festival")) {
            model.addAttribute("festival",true);
//            model.addAttribute("federal",false);
        }
        Iterable<Holiday> holidays= holidaysRepository.findAll();
        List<Holiday> holidayList= StreamSupport.stream(holidays.spliterator(), false).toList();
        Holiday.Type[] types=Holiday.Type.values();
        for(Holiday.Type type : types){
            model.addAttribute(type.toString(),(holidayList.stream().filter(holiday -> holiday.getType().equals(type)).
                    collect(Collectors.toList())));
        }
        return "holidays.html";
    }

}
