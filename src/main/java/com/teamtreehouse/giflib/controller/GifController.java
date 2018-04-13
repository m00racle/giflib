package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.GifRepository;
import com.teamtreehouse.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GifController {
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    //Response body is deprecated
    public String listGifs(ModelMap modelMap) {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs", allGifs);
        return "home";
    }

    @RequestMapping(value = "/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap){
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif", gif);
        return "gif-details";
    }

    @RequestMapping(value = "/favorites")
    public String gifFavorites(ModelMap modelMap){
        List<Gif> allFavorites = gifRepository.getAllFavorites();
        modelMap.put("gifs", allFavorites);
        return "favorites";
    }

    @RequestMapping("/search")
    public String searchResult(@RequestParam String q, ModelMap modelMap){
        List<Gif> searchResults = gifRepository.findByKeyword(q);
        modelMap.put("gifs", searchResults);
        return "home";
    }
}
