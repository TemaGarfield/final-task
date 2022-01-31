package by.kotik.please_work_jwt.controller;

import by.kotik.please_work_jwt.model.Topic;
import by.kotik.please_work_jwt.pojo.CollectionRequest;
import by.kotik.please_work_jwt.pojo.CollectionResponse;
import by.kotik.please_work_jwt.pojo.MessageResponse;
import by.kotik.please_work_jwt.service.CollectionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final CollectionService collectionService;

    public UserController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/get_all")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CollectionResponse>> getAllCollections() {
        return new ResponseEntity<>(collectionService.getAllCollections(), HttpStatus.OK);
    }

    @GetMapping("/get_all_topics")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Topic>> getAllTopics() {
        return new ResponseEntity<>(collectionService.getAllTopics(), HttpStatus.OK);
    }

    @GetMapping("/get_collection_by_id/{id}")
    public ResponseEntity<CollectionResponse> getCollectionById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(collectionService.getCollectionById(id), HttpStatus.OK);
    }

    @GetMapping("/get_all_user_collections/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<CollectionResponse>> getUserCollections(@PathVariable("id") Long id) {
        return new ResponseEntity<>(collectionService.getUserCollections(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteCollection(@PathVariable("id") Long id) {
        try {
            collectionService.deleteCollection(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save_collection")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveCollection(@RequestBody CollectionRequest collectionRequest) {
        collectionService.saveCollection(collectionRequest);
        return ResponseEntity.ok(new MessageResponse("Collection created"));
    }

    @PostMapping("/edit_collection/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> editCollection(@PathVariable("id") Long idCollection, @RequestBody CollectionRequest collectionRequest) {
        collectionService.editCollection(idCollection, collectionRequest);
        return ResponseEntity.ok(new MessageResponse("Collection updated"));
    }
}
