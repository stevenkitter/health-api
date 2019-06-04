package org.spring.springboot.controller;


import org.spring.springboot.Utile.DateOperation;
import org.spring.springboot.Utile.Tools;
import org.spring.springboot.jpa.Card;
import org.spring.springboot.jpa.File;
import org.spring.springboot.models.AddCardRequest;
import org.spring.springboot.models.Response;
import org.spring.springboot.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class CardController {


    @Autowired
    private CardRepository cardRepository;

    @PostMapping("/card/add")
    public Response<String> addCard(@RequestHeader(value="Token") Long token, @RequestBody AddCardRequest request) {
        Card card = new Card();
        card.setContent(request.getContent());
        card.setFileId(request.getFileId());
        card.setUserId(token);
        cardRepository.save(card);
        return new Response<>(200, "打卡成功", "");
    }

    @GetMapping("/card/my")
    public Response<List<Card>> myCards(@RequestHeader(value="Token") Long token) throws ParseException {

        List<Card> list = cardRepository.findByUserId(token);
        for (Card card : list) {
            System.out.print(card.getId());
            File file = card.getFile();
            String filename = file.getFileName();
            String path = Tools.filePath(filename);
            file.setFileName(path);
            card.setFile(file);

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:m:s");
            Date date = format.parse(card.getCreated_at().toString());
            card.setCreated(DateOperation.format(date));
        }
        return new Response<>(200, "", list);
    }


}
