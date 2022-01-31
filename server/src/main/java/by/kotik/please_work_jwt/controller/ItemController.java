package by.kotik.please_work_jwt.controller;

import by.kotik.please_work_jwt.pojo.CommentRequest;
import by.kotik.please_work_jwt.pojo.ItemRequest;
import by.kotik.please_work_jwt.pojo.ItemResponse;
import by.kotik.please_work_jwt.pojo.MessageResponse;
import by.kotik.please_work_jwt.service.CommentService;
import by.kotik.please_work_jwt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test/item")
@CrossOrigin(origins = "*")
public class ItemController {

    private final ItemService itemService;
    private final CommentService commentService;

    @Autowired
    public ItemController(ItemService itemService, CommentService commentService) {
        this.itemService = itemService;
        this.commentService = commentService;
    }

    @GetMapping("/get_item/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(itemService.getItemById(id), HttpStatus.OK);
    }

    @DeleteMapping("/delete_item/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/save_item")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveItem(@RequestBody ItemRequest itemRequest) {
        itemService.saveItem(itemRequest);

        return ResponseEntity.ok(new MessageResponse("Item created"));
    }

    @PostMapping("/edit_item")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editItem(@RequestBody ItemRequest itemRequest) {
        itemService.editItem(itemRequest);

        return ResponseEntity.ok(new MessageResponse("Item updated"));
    }

    @PostMapping("/save_comment/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveComment(@PathVariable("id") Long id, @RequestBody CommentRequest commentRequest) {
        commentService.saveComment(id, commentRequest);
        return ResponseEntity.ok(new MessageResponse("Comment created"));
    }
}
