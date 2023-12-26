package com.springbootstart.controller;

import com.springbootstart.dto.ChatMessage;
import com.springbootstart.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @MessageMapping("/{roomId}")
    @SendTo("/chat/room/{roomId}")
    public ChatMessage massageProvider(@De)
}
