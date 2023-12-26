package com.springbootstart.service;

import com.springbootstart.entity.Chat;
import com.springbootstart.entity.Room;
import com.springbootstart.repository.ChatRepository;
import com.springbootstart.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final RoomRepository roomRepository;
    private final ChatRepository chatRepository;

    public List<Room> findAllRoom() {
        return roomRepository.findAll();
    }

    public Room findRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public Room createRoom(String name) {
        return roomRepository.save(Room.createRoom(name));
    }

    public Chat createChat(Long roomId, String sender, String message) {
        Room room = roomRepository.findById(roomId).orElseThrow();
        return chatRepository.save(Chat.createChat(room, sender, message));
    }

    public List<Chat> findAllChatByRoomId(Long roomId) {
        return chatRepository.findAllByRoomId(roomId);
    }
}
