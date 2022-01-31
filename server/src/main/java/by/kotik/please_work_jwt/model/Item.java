package by.kotik.please_work_jwt.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    @ManyToOne
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(
            mappedBy = "toItem",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
//    @Setter(AccessLevel.PRIVATE)
    private List<Comment> comments;

    public Item() { }

    public Item(String name, Collection collection) {
        this.name = name;
        this.collection = collection;
    }

    public Item(Long id, String name, Collection collection) {
        this.id = id;
        this.name = name;
        this.collection = collection;
    }

    public Item(Long id, String name, Collection collection, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.collection = collection;
        this.comments = comments;
    }
}
