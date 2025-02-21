// package com.rest.springapp.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.rest.springapp.model.Message;
// import com.rest.springapp.service.MessageService;

// @RestController
// public class MessageController {
//     @Autowired
//     MessageService obj;
//     @PostMapping("/postdata")
//     public ResponseEntity<Message> addMessage(@RequestBody Message a)
//     {
//         return new ResponseEntity<>(obj.add(a),HttpStatus.ACCEPTED);
//     }

//     @GetMapping("/messages")
//     public ResponseEntity<List<Message>> getAllMessages() {
//         return new ResponseEntity<>(obj.getAllMessages(), HttpStatus.OK);
//     }

//     @GetMapping("/messages/{id}")
//     public ResponseEntity<Message> getMessageById(@PathVariable int id) {
//         return obj.getMessageById(id)
//                   .map(message -> new ResponseEntity<>(message, HttpStatus.OK))
//                   .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//     }

//     @PutMapping("/messages/{id}")
//     public ResponseEntity<Message> updateMessage(@PathVariable int id, @RequestBody Message newMessage) {
//         try {
//             return new ResponseEntity<>(obj.updateMessage(id, newMessage), HttpStatus.OK);
//         } catch (RuntimeException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @DeleteMapping("/messages/{id}")
//     public ResponseEntity<Void> deleteMessage(@PathVariable int id) {
//         try {
//             obj.deleteMessage(id);
//             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//         } catch (RuntimeException e) {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

// }
