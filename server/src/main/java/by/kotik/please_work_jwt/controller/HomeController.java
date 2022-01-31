package by.kotik.please_work_jwt.controller;

import by.kotik.please_work_jwt.pojo.CollectionResponse;
import by.kotik.please_work_jwt.pojo.ItemResponse;
import by.kotik.please_work_jwt.service.CollectionService;
import by.kotik.please_work_jwt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test/home")
@CrossOrigin(origins = "*")
public class HomeController {

    private final CollectionService collectionService;
    private final ItemService itemService;

    @Autowired
    public HomeController(CollectionService collectionService, ItemService itemService) {
        this.collectionService = collectionService;
        this.itemService = itemService;
    }

    @GetMapping("/get_max_items_collections")
    public ResponseEntity<List<CollectionResponse>> getMaxItemsCollections() {

        return new ResponseEntity<>(collectionService.getMaxItemsCollections(), HttpStatus.OK);
    }

    @GetMapping("/get_last_items")
    public ResponseEntity<List<ItemResponse>> getLastAddedItems() {
        return new ResponseEntity<>(itemService.getLastAddedItems(), HttpStatus.OK);
    }
}
